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
import java.util.Map;

@WebServlet(name = "AddMovieSongsServlet", urlPatterns = "/addmoviesongs")
public class AddMovieSongsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());

        String userId = (String) request.getSession().getAttribute("user_id");
        String id = request.getParameter("id");

        Map<String, String[]> map = request.getParameterMap();
        ArrayList<String> songs = new ArrayList<>();
        ArrayList<String> url = new ArrayList<>();

        for (Map.Entry<String, String[]> entry: map.entrySet()) {
            if (entry.getKey().startsWith("song")) {
                songs.add(entry.getValue()[0]);
            }
            if (entry.getKey().startsWith("url")) {
                url.add(entry.getValue()[0]);
            }
        }

        for (int i = 0; i < songs.size(); i++) {
            String newSong = songs.get(i);
            String newUrl = "";
            if (!url.get(i).isEmpty()) {
                newUrl = url.get(i);
            } else {
                newUrl = null;
            }

            try {
                PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                        .prepareStatement("INSERT INTO songs VALUES(null, ?, ?, ?)");
                preparedStatement.setString(1, newSong);
                preparedStatement.setString(2, newUrl);
                preparedStatement.setInt(3, Integer.parseInt(id));
                preparedStatement.executeUpdate();
                response.sendRedirect(request.getContextPath() +"/moviepage?id=" + id);
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());
        bdd.getAMovie(request, response);
        bdd.getAvatar(request, response);

        this.getServletContext().getRequestDispatcher("/WEB-INF/addmoviesongs.jsp").forward(request, response);
    }
}
