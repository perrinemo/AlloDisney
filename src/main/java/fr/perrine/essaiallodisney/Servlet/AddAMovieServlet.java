package fr.perrine.essaiallodisney.Servlet;

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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "AddAMovieServlet", urlPatterns = "/addamovie")
public class AddAMovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());

        Map<String, String[]> map = request.getParameterMap();

        String title = request.getParameter("title");
        String duration = request.getParameter("duration");
        String year = request.getParameter("year");
        String resume = request.getParameter("resume");

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

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("INSERT INTO movies VALUES(null, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, year);
            preparedStatement.setString(3, duration);
            preparedStatement.setString(4, resume);
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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/addamovie.jsp").forward(request, response);
    }
}
