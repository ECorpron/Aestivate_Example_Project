package com.revature.util;

import com.revature.screens.DashboardScreen;
import com.revature.screens.HomeScreen;
import com.revature.screens.LoginScreen;
import com.revature.screens.RegisterScreen;
import com.revature.screens.accounts.AccountCreationScreen;
import com.revature.screens.accounts.ViewAccountsScreen;
import com.revature.service.AccountService;
import com.revature.service.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Manages the state of the app.
 *
 * Holds the buffered reader that reads the console input, the ScreenRouter for storing screens and handling screen
 * transitions, and the Session that holds the current session information.
 *
 * Statically instantiated in BankingApp, so on app startup the BufferedReader, ScreenRouter, and Session are already
 * initialized.
 */
public class AppState {

    // how the app parses input in the console from the user.
    private BufferedReader console;

    // The screenrouter that holds the screens the user may need, and handles transitioning to them.
    private ScreenRouter router;

    // Current session information. Isn't initialized until another class sets it to be not null.
    private Session currentSession;

    // boolean that tells if the app is running
    private boolean appRunning;

    /**
     * No args constructor that instantiates the bufferedreader, UserRepository, UserService, AccountRepository,
     * AccountService, and the ScreenRouter. ScreenRouter is given screens that the user might go to.
     */
    public AppState() {
        //System.out.println("[LOG] - Initializing application...");

        this.appRunning = true;
        this.console = new BufferedReader(new InputStreamReader(System.in));

        final UserService userService = new UserService();

        final AccountService accService = new AccountService();

        router = new ScreenRouter();
        router.addScreen(new HomeScreen())
                .addScreen(new RegisterScreen(userService))
                .addScreen(new LoginScreen(userService))
                .addScreen(new DashboardScreen(userService))
                .addScreen(new AccountCreationScreen(accService))
                .addScreen(new ViewAccountsScreen(accService));

        //System.out.println("[LOG] - Application initialized");
    }

    /**
     * Getter for the ScreenRouter
     * @return returns the ScreenRouter
     */
    public ScreenRouter getRouter() {
        return router;
    }

    /**
     * Getter for isAppRunning
     * @return returns isAppRunning
     */
    public boolean isAppRunning(){
        return appRunning;
    }

    /**
     * Setter for isAppRunning
     * @param running sets isAppRunning
     */
    public void setAppRunning(boolean running) {
        appRunning = running;
    }

    /**
     * Getter for the console
     * @return returns the console
     */
    public BufferedReader getConsole() {
        return console;
    }

    /**
     * sets currentSession
     * @param currentSession what to set currentSession to
     */
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    /**
     * sets currentSession to null
     */
    public void invalidateCurrentSession() {
        this.currentSession = null;
    }

    /**
     * Returns true if currentSession isn't null, otherwise returns false
     * @return returns true if currentSession isn't null, otherwise returns false
     */
    public boolean isSessionValid() {
        return (this.currentSession != null);
    }

    /**
     * Getter for currentSession
     * @return returns currentSession
     */
    public Session getCurrentSession() {
        return currentSession;
    }
}
