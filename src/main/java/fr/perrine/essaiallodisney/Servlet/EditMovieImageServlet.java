package fr.perrine.essaiallodisney.Servlet;

import fr.perrine.essaiallodisney.Helper.FilenameHelper;
import fr.perrine.essaiallodisney.Helper.RedirectHelper;
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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "EditMovieImageServlet", urlPatterns = "/editmovieimage")
@MultipartConfig
public class EditMovieImageServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(IndexServlet.class.getCanonicalName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());

        String userId = (String) request.getSession().getAttribute("user_id");
        String id = request.getParameter("id");

        String path = request.getSession().getServletContext().getRealPath("/img");
        new File(path).mkdirs();
        Part filePart = request.getPart("file");
        String filename = FilenameHelper.getFileName(filePart);
        OutputStream out = null;
        InputStream filecontent = null;
        PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(path + File.separator + filename));
            filecontent = filePart.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            try {
                PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                        .prepareStatement("UPDATE movies SET image = ? WHERE id = ?");
                preparedStatement.setString(1, filename);
                preparedStatement.setString(2, id);
                preparedStatement.executeUpdate();
                response.sendRedirect(request.getContextPath() +"/moviepage?id=" + Integer.parseInt(id));
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
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());
        bdd.getAMovie(request, response);
        bdd.getAvatar(request, response);

        RedirectHelper.redirect(request, response);

        this.getServletContext().getRequestDispatcher("/WEB-INF/editmovieimage.jsp").forward(request, response);
    }
}
