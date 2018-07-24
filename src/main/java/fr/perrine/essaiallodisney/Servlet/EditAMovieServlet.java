package fr.perrine.essaiallodisney.Servlet;

import com.mysql.jdbc.PreparedStatement;
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
                    .prepareStatement("UPDATE movies SET year = ?, duration = ?, resume = ?, trailer = ? WHERE id = ?");
            preparedStatement.setString(1, year);
            preparedStatement.setString(2, duration);
            preparedStatement.setString(3, resume);
            preparedStatement.setString(4, trailer);
            preparedStatement.setString(5, id);
            preparedStatement.executeUpdate();
            response.sendRedirect("/moviepage?id=" + Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());
        bdd.getAMovie(request, response);
        bdd.getAvatar(request, response);

        String userPseudo = (String) request.getSession().getAttribute("user");
        if (userPseudo == null || userPseudo.isEmpty()) {
            response.sendRedirect("/");
            return;
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/editamovie.jsp").forward(request, response);
    }
}
