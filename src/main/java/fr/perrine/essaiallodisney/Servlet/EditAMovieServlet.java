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
import java.util.ArrayList;

@WebServlet(name = "EditAMovieServlet", urlPatterns = "/editamovie")
public class EditAMovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingletonBDD bdd = SingletonBDD.getInstance(getServletContext());
        MoviePageServlet.getAMovie(request, response);
    }
}
