package com.revature.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection factory handles connecting to the BankApp sql database. Uses the singleton pattern to ensure that there is always and
 * only one connection while the app is running.
 */
public class ConnectionFactory {

    // ConnectionFactory declared static to ensure that there is only one
    private static ConnectionFactory connFactory = new ConnectionFactory();

    // The Properties that are used to connect to the SQL database
    private Properties props = new Properties();

    // Statically checks that the Driver class exists
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor that attempts to load the properties files. Throws an exception if the file can't be found.
      */
    private ConnectionFactory() {
        try {
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Static getter for the ConnectionFactory
     * @return returns the connection factory
     */
    public static ConnectionFactory getInstance() {
        return connFactory;
    }

    /**
     * A getter for the Connection. Specifically this is the connection to the BankApp database. Throws an error if the
     * Database can't be reached.
     * @return returns the connection to the BankApp database.
     */
    public Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("admin-usr"),
                    props.getProperty("admin-pw")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
