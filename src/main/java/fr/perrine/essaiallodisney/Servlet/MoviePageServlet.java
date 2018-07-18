package fr.perrine.essaiallodisney.Servlet;

import com.mysql.jdbc.PreparedStatement;
import fr.perrine.essaiallodisney.Model.MovieModel;
import fr.perrine.essaiallodisney.Singleton.SingletonBDD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "MoviePageServlet", urlPatterns = "/moviepage")
public class MoviePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());
        String id = request.getParameter("id");

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("SELECT * FROM movies WHERE id = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = null;
            try {
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ArrayList<MovieModel> models = new ArrayList<>();

            while(resultSet.next()) {
                MovieModel movieModel = new MovieModel();
                movieModel.setId(resultSet.getInt("id"));
                movieModel.setTitle(resultSet.getString("title"));
                movieModel.setYear(resultSet.getInt("year"));
                movieModel.setDuration(resultSet.getString("duration"));
                movieModel.setResume(resultSet.getString("resume"));
                models.add(movieModel);
            }
            request.setAttribute("models", models);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/moviepage.jsp").forward(request, response);
    }
}
