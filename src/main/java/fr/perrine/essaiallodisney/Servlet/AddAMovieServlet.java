package fr.perrine.essaiallodisney.Servlet;

import fr.perrine.essaiallodisney.Singleton.SingletonBDD;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddAMovieServlet", urlPatterns = "/addamovie")
@MultipartConfig
public class AddAMovieServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(IndexServlet.class.getCanonicalName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());

        Map<String, String[]> map = request.getParameterMap();

        String title = request.getParameter("title");
        String duration = request.getParameter("duration");
        String year = request.getParameter("year");
        String resume = request.getParameter("resume");
        String trailer = request.getParameter("trailer");

        StringBuffer sb = new StringBuffer();

        ArrayList<String> songs = new ArrayList<>();
        ArrayList<String> url = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            if (entry.getKey().startsWith("song")) {
                songs.add(entry.getValue()[0]);
            }
            if (entry.getKey().startsWith("url")) {
                url.add(entry.getValue()[0]);
            }
        }

        String path = request.getSession().getServletContext().getRealPath("/img");
        new File(path).mkdirs();
        final Part filePart = request.getPart("file");
        final String filename = getFileName(filePart);
        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(path + File.separator + filename));
            filecontent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            if (trailer == null || trailer.isEmpty()) {
                trailer = "";
            } else {
                trailer = trailer.replace("watch?v=", "embed/");
            }

            try {
                PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                        .prepareStatement("INSERT INTO movies VALUES(null, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, year);
                preparedStatement.setString(3, duration);
                preparedStatement.setString(4, resume);
                preparedStatement.setString(5, filename);
                preparedStatement.setString(6, trailer);
                preparedStatement.executeUpdate();

                int movieId = 0;

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        movieId = generatedKeys.getInt(1);
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

                for (int i = 0; i < songs.size(); i++) {
                    String addSong = songs.get(i);
                    String urlVideo = "";
                    if (!url.get(i).isEmpty()) {
                        urlVideo = url.get(i);
                    } else {
                        urlVideo = null;
                    }
                    try {
                        PreparedStatement preparedStatement1 = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                                .prepareStatement("INSERT INTO songs VALUES(null, ?, ?, ?)");

                        preparedStatement1.setString(1, addSong);
                        preparedStatement1.setString(2, urlVideo);
                        preparedStatement1.setInt(3, movieId);
                        preparedStatement1.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


                response.sendRedirect("/listmovie");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            writer.println("You either did not specify a file to upload or are trying to upload a file to a protected " +
                    "or nonexistent location");
            writer.println("<br />ERROR: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", new Object[]{e.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPseudo = (String) request.getSession().getAttribute("user");
        if (userPseudo == null || userPseudo.isEmpty()) {
            response.sendRedirect("/");
            return;
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/addamovie.jsp").forward(request, response);
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
