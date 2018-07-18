package fr.perrine.essaiallodisney.Singleton;

import java.sql.Connection;
import com.mysql.jdbc.Driver;

import javax.servlet.ServletContext;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
