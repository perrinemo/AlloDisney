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
import java.sql.SQLException;

@WebServlet(name = "InscriptionServlet", urlPatterns = "/inscription")
public class InscriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());

        String emailInscription = request.getParameter("email_inscription");
        String passwordInscription = request.getParameter("password_inscription");
        String pseudoInscription = request.getParameter("pseudo_inscription");

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("INSERT INTO users VALUES(null, ?, ?, ?)");
            preparedStatement.setString(1, emailInscription);
            preparedStatement.setString(2, passwordInscription);
            preparedStatement.setString(3, pseudoInscription);
            preparedStatement.executeUpdate();
            HttpSession session = request.getSession();
            session.setAttribute("user", emailInscription);
            response.sendRedirect("/index");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
    }
}
