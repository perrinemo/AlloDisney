package fr.perrine.essaiallodisney.Singleton;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
import fr.perrine.essaiallodisney.Model.MovieModel;
import fr.perrine.essaiallodisney.Model.SongModel;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SingletonBDD {
    private static SingletonBDD INSTANCE = null;
    private Connection mConnection = null;

    private SingletonBDD(ServletContext context) {
        initConnection(context);
    }

    public static SingletonBDD getInstance(ServletContext context) {
        if (INSTANCE == null) {
            INSTANCE = new SingletonBDD(context);
        }
        return INSTANCE;
    }

    public Connection getConnection() {
        return mConnection;
    }


    private void initConnection(ServletContext context) {
        Class driverClass = null;
        try {
            String mdp = context.getInitParameter("bdd_mdp");
            driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            mConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/disney?useUnicode=true&amp;characterEncoding=utf8", "root", mdp);
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getAMovie(HttpServletRequest request, HttpServletResponse response) {
        SingletonBDD bdd = SingletonBDD.getInstance(request.getServletContext());
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

            while (resultSet.next()) {
                MovieModel movieModel = new MovieModel();
                movieModel.setId(resultSet.getInt("id"));
                movieModel.setTitle(resultSet.getString("title"));
                movieModel.setYear(resultSet.getInt("year"));
                movieModel.setDuration(resultSet.getString("duration"));
                movieModel.setResume(resultSet.getString("resume"));
                movieModel.setImage(resultSet.getString("image"));
                movieModel.setTrailer(resultSet.getString("trailer"));
                models.add(movieModel);
            }
            request.setAttribute("models", models);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement = (com.mysql.jdbc.PreparedStatement) bdd.getConnection()
                    .prepareStatement("SELECT * FROM songs WHERE id_movie = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = null;
            try {
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ArrayList<SongModel> songs = new ArrayList<>();

            while (resultSet.next()) {
                SongModel song = new SongModel();
                song.setTitle(resultSet.getString("title_song"));
                song.setVideo(resultSet.getString("video_song"));
                songs.add(song);
            }

            if (songs.size() == 0) {
                request.setAttribute("no_song", "Pas de chanson");
            } else {
                request.setAttribute("songs", songs);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
