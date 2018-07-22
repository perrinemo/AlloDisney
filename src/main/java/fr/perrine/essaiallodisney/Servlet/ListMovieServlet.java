package fr.perrine.essaiallodisney.Servlet;

import fr.perrine.essaiallodisney.Model.MovieModel;
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

@WebServlet(name = "ListMovieServlet", urlPatterns = "/listmovie")
public class ListMovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());

        ArrayList<MovieModel> models = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("SELECT * FROM movies ORDER BY movies.year DESC");
            ResultSet resultSet = null;
            try {
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }



            while (resultSet.next()) {
                MovieModel movie = new MovieModel();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setYear(resultSet.getInt("year"));
                models.add(movie);
            }

            request.setAttribute("listMovie", models);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("nbMovies", models.size());

        this.getServletContext().getRequestDispatcher("/WEB-INF/listmovie.jsp").forward(request, response);
    }
}
