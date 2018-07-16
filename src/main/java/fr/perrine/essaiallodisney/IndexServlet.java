package fr.perrine.essaiallodisney;

import com.mysql.jdbc.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance();

        String pseudoConnexion = request.getParameter("pseudo_connexion");
        String passwordConnexion = request.getParameter("password_connexion");

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("SELECT users.pseudo, users.password FROM users");
            ResultSet resultSet = null;
            try {
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ArrayList<UserModel> userModels = new ArrayList<>();

            while (resultSet.next()) {
                UserModel user = new UserModel();
                user.setPseudo(resultSet.getString("pseudo"));
                user.setPassword(resultSet.getString("password"));
                userModels.add(user);
            }

            for (int i = 0; i < userModels.size(); i++) {
                if (pseudoConnexion.equals(userModels.get(i).getPseudo()) &&
                        passwordConnexion.equals(userModels.get(i).getPassword())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", pseudoConnexion);
                    response.sendRedirect("/index");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance();

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("SELECT * FROM movies");
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
                    movieModel.setResume(resultSet.getString("resume"));
                    models.add(movieModel);
            }
            request.setAttribute("models", models);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/homepage.jsp").forward(request, response);
    }
}
