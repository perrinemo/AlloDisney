package fr.perrine.essaiallodisney.Servlet;

import fr.perrine.essaiallodisney.Model.MovieModel;
import fr.perrine.essaiallodisney.Model.UserModel;
import fr.perrine.essaiallodisney.Singleton.SingletonBDD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ProfilServlet", urlPatterns = "/profil")
public class ProfilServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPseudo = (String) request.getSession().getAttribute("user");
        if (userPseudo == null || userPseudo.isEmpty()) {
            response.sendRedirect("/");
            return;
        }

        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());
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

        this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
    }
}
