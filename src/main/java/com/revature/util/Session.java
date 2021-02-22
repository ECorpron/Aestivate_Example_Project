package com.revature.util;

import com.revature.models.AppUser;

import java.sql.Connection;

/**
 * Stores information about the session. Begins when a user successfully logs into the application. Controls the
 * Connection to the database.
 */
public class Session {

    // The user in the session
    private AppUser sessionUser;

    // the connection to the data base
    private Connection connection;

    /**
     * Takes in the session user AppUser and the Connection for the database. Throws an ExceptionInInitializerError if
     * either the given AppUser or the Connection is null.
     * @param sessionUser the user using the session
     * @param conn the connection to the database
     */
    public Session(AppUser sessionUser, Connection conn) {

        if (sessionUser == null || conn == null) {
            throw new ExceptionInInitializerError("Cannot establish user session, null values provided!");
        }

        this.sessionUser = sessionUser;
        this.connection = conn;
    }

    /**
     * Sets the session values to null
     */
    public void endSession() {
        sessionUser = null;
        connection = null;
    }

    /**
     * Getter method for returning the session user
     * @return returns the session user
     */
    public AppUser getSessionUser() {
        return sessionUser;
    }

    /**
     * Setter method for setting the session user
     * @param sessionUser the user to set the session to
     */
    public void setSessionUser(AppUser sessionUser) {
        this.sessionUser = sessionUser;
    }

    /**
     * Getter method of the connection
     * @return returns the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Setter for connection of the session
     * @param connection What set the connection to
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
