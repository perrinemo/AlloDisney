package fr.perrine.essaiallodisney.Servlet;

import com.mysql.jdbc.PreparedStatement;
import fr.perrine.essaiallodisney.Helper.RedirectHelper;
import fr.perrine.essaiallodisney.Model.MovieModel;
import fr.perrine.essaiallodisney.Model.SongModel;
import fr.perrine.essaiallodisney.Singleton.SingletonBDD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "EditAMovieServlet", urlPatterns = "/editamovie")
public class EditAMovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String duration = request.getParameter("duration");
        String year = request.getParameter("year");
        String resume = request.getParameter("resume");
        String trailer = request.getParameter("trailer");

        if (trailer == null || trailer.isEmpty()) {
            trailer = "";
        } else {
            trailer = trailer.replace("watch?v=", "embed/");
        }

        try {
            java.sql.PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("UPDATE movies SET title = ?, year = ?, duration = ?, resume = ?, trailer = ? WHERE id = ?");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, year);
            preparedStatement.setString(3, duration);
            preparedStatement.setString(4, resume);
            preparedStatement.setString(5, trailer);
            preparedStatement.setString(6, id);
            preparedStatement.executeUpdate();
            response.sendRedirect(request.getContextPath() +"/moviepage?id=" + Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());
        bdd.getAMovie(request, response);
        bdd.getAvatar(request, response);

        RedirectHelper.redirect(request, response);

        this.getServletContext().getRequestDispatcher("/WEB-INF/editamovie.jsp").forward(request, response);
    }
}
