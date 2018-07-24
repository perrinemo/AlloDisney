package fr.perrine.essaiallodisney.Servlet;

import com.mysql.jdbc.PreparedStatement;
import fr.perrine.essaiallodisney.Singleton.SingletonBDD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "InscriptionServlet", urlPatterns = "/inscription")
public class InscriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());

        String emailInscription = request.getParameter("email_inscription");
        String passwordInscription = request.getParameter("password_inscription");
        String pseudoInscription = request.getParameter("pseudo_inscription");

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("INSERT INTO users VALUES(null, ?, ?, ?, null)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, emailInscription);
            preparedStatement.setString(2, passwordInscription);
            preparedStatement.setString(3, pseudoInscription);
            preparedStatement.executeUpdate();

            // génère l'id de l'insert into
            int id = 0;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", pseudoInscription);
            session.setAttribute("user_id", String.valueOf(id));
            response.sendRedirect("/index");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());
        bdd.getAvatar(request, response);

        this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
    }
}
