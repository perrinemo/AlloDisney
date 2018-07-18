package fr.perrine.essaiallodisney.Singleton;

import java.sql.Connection;
import com.mysql.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonBDD {
    private static SingletonBDD INSTANCE = null;
    private Connection mConnection = null;

    private SingletonBDD() {
        initConnection();
    }

    public static SingletonBDD getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonBDD();
        }
        return INSTANCE;
    }

    public Connection getConnection() {
        return mConnection;
    }


    private void initConnection() {
        Class driverClass = null;
        try {
            driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            mConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/disney?useUnicode=true&amp;characterEncoding=utf8", "root", "julien");
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
