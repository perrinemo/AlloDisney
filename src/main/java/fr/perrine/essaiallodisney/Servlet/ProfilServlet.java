package fr.perrine.essaiallodisney.Servlet;

import fr.perrine.essaiallodisney.Helper.HashPassHelper;
import fr.perrine.essaiallodisney.Model.MovieModel;
import fr.perrine.essaiallodisney.Model.UserModel;
import fr.perrine.essaiallodisney.Singleton.SingletonBDD;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ProfilServlet", urlPatterns = "/profil")
@MultipartConfig
public class ProfilServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(IndexServlet.class.getCanonicalName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());

        String id = request.getParameter("id-edit");
        String pseudo = request.getParameter("pseudo-edit");
        String password = request.getParameter("password-edit");

        String path = request.getSession().getServletContext().getRealPath("/img");
        new File(path).mkdirs();
        final Part filePart = request.getPart("avatar-edit");
        final String avatar = getFileName(filePart);
        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(path + File.separator + avatar));
            filecontent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            try {
                com.mysql.jdbc.PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                        .prepareStatement("UPDATE users SET pseudo = ?, password = ?, avatar = ? WHERE id = ?");
                preparedStatement.setString(1, pseudo);
                preparedStatement.setString(2, HashPassHelper.hashPass(password));
                preparedStatement.setString(3, avatar);
                preparedStatement.setString(4, id);
                preparedStatement.executeUpdate();

                response.sendRedirect(request.getContextPath() +"/profil?id=" + Integer.parseInt(id));
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
            response.sendRedirect(request.getContextPath() +"/");
            return;
        }

        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());
        bdd.getAvatar(request, response);
        String id = request.getParameter("id");

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = null;
            try {
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ArrayList<UserModel> users = new ArrayList<>();

            while (resultSet.next()) {
                UserModel user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setPseudo(resultSet.getString("pseudo"));
                user.setAvatar(resultSet.getString("avatar"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            request.setAttribute("users", users);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("SELECT id FROM movies WHERE id_users = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = null;
            try {
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ArrayList<MovieModel> nbMovies = new ArrayList<>();

            while (resultSet.next()) {
                MovieModel movie = new MovieModel();
                movie.setId(resultSet.getInt("id"));
                nbMovies.add(movie);
            }
            request.setAttribute("nbMovie", nbMovies.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("SELECT * FROM movies WHERE id_users = ? ORDER BY id DESC LIMIT 1");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = null;
            try {
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ArrayList<MovieModel> lastmovie = new ArrayList<>();

            while (resultSet.next()) {
                MovieModel movie = new MovieModel();
                movie.setId(resultSet.getInt("id"));
                movie.setYear(resultSet.getInt("year"));
                movie.setTitle(resultSet.getString("title"));
                lastmovie.add(movie);
            }
            request.setAttribute("lastmovie", lastmovie);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
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
