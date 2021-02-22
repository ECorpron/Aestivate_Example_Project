package com.revature;

import com.revature.exceptions.ResourcePersistenceException;
import com.revature.util.AppState;

/**
 * Handles app start up.
 *
 * Statically starts AppState in order to make sure that there is only ever one AppState. Has one
 * method to statically access the AppState so that all classes in the app have access to it.
 *
 * AppState starts with isAppRunning = true, and begins by navigating to the Home Screen.
 *
 * Now uses Aestivate to handle connections to the database instead of managing its own connections, connections
 * factory, and CRUD repository.
 *
 * @version 2.0
 * @author Eli Corpron
 */
public class BankingApp {

    // Initializes AppState upon BankingApp being loaded
    private static AppState app = new AppState();

    /**
     * Static method so that the AppState is always accessible.
     * @return returns the AppState
     */
    public static AppState app() {
        return app;
    }

    /**
     * Begins the app by navigating to the home screen. If an action is taken that causes isAppRunning to be false,
     * the app then closes.
     * @param args Not used
     */
    public static void main(String[] args) {

        // Start the application
        while (app.isAppRunning()) {
            // Begin at the home screen.
            try {
                app.getRouter().navigate("/home");
            } catch (ResourcePersistenceException r) {
                app().setAppRunning(false);
            }
        }

        System.out.println("Thank you for using the Banking App!");
    }
}
