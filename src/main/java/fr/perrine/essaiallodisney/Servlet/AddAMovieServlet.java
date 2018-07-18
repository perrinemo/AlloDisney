package fr.perrine.essaiallodisney.Servlet;

import fr.perrine.essaiallodisney.Singleton.SingletonBDD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AddAMovieServlet", urlPatterns = "/addamovie")
public class AddAMovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SingletonBDD bdd = SingletonBDD.getInstance();

        String title = request.getParameter("title");
        String duration = request.getParameter("duration");
        String year = request.getParameter("year");
        String resume = request.getParameter("resume");

        ArrayList<String> songs = new ArrayList<>();
        songs.add(request.getParameter("song"));

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("INSERT INTO movies VALUES(null, ?, ?, ?, ?)");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, year);
            preparedStatement.setString(3, duration);
            preparedStatement.setString(4, resume);
            preparedStatement.executeUpdate();

            for (int i = 0; i < songs.size(); i++) {
                String addSong = songs.get(i);
                try {
                    PreparedStatement preparedStatement1 = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                            .prepareStatement("INSERT INTO songs VALUES(null, ?, null)");

                    preparedStatement1.setString(1, addSong);
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
