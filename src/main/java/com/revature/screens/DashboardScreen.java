package com.revature.screens;

import com.revature.exceptions.AuthenticationException;
import com.revature.models.AppUser;
import com.revature.service.UserService;
import com.revature.util.Session;

import java.io.IOException;

import static com.revature.BankingApp.app;

/**
 * Renders the dashboard screen for the logged in user. Allows them to View their accounts, create a new account, or
 * log out and return to the home screen.
 */
public class DashboardScreen extends Screen{

    // The UserService to connect to the database
    private UserService userService;

    /**
     * Constructor that takes in the userService to use. Sets name to Dashboard Screen and route to /dashboard
     * @param userService the userService to connect to
     */
    public DashboardScreen(UserService userService) {
        super("Dashboard Screen", "/dashboard");
        this.userService = userService;
    }

    /**
     * Allows the user to select to view accounts, make a new account, or logout. View accounts takes the user to the
     * ViewAccounts screen, create account takes the user to the AccountCreation screen, logging out invalidates the
     * session and takes them back to the home screen.
     */
    @Override
    public void render() {
        if (!app().isSessionValid()) {
            throw new AuthenticationException("Accessing Dashboard with invalid Session");
        }

        // Since we are at the Dashboard, we know that there is a session.
        Session session = app().getCurrentSession();
        AppUser currentUser = session.getSessionUser();

        System.out.println("+-------------------------\n");

        System.out.println("Welcome back "+ currentUser.getFirstName()+" "+currentUser.getLastName()+"!");
        System.out.println("Please select one of the options below!\n");

        System.out.println("1) View Accounts");
        System.out.println("2) Create a new Account");
        System.out.println("3) Logout and return to home screen");

        try {
            System.out.print("> ");
            String userSelection = app().getConsole().readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to Account Viewing");
                    app().getRouter().navigate("/viewAccounts");
                    break;
                case "2":
                    System.out.println("Navigating to Account Creation");
                    app().getRouter().navigate("/createAccount");
                    break;
                case "3":
                    System.out.println("Logging out...");
                    app().getCurrentSession().endSession();
                    app().invalidateCurrentSession();
                    app().getRouter().navigate("/home");
                    break;
                default:
                    System.out.println("Invalid Selection! Please enter a valid selection!");
                    app().getRouter().navigate("/dashboard");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[FATAL] - Fatal error encountered, closing app");
            app().setAppRunning(false);
        }


    }
}
