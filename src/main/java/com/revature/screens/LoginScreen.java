package com.revature.screens;

import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.service.UserService;

import java.io.IOException;

import static com.revature.BankingApp.app;

/**
 *  Renders the Login Screen. Allows users to enter a username and password, and then uses UserService to check if
 *  there is a corresponding account with that information.
 */
public class LoginScreen extends Screen{

    // The user service to access the database
    private UserService userService;

    /**
     * Constructor that takes in the userService. Sets name to Login Screen and route to /login
     * @param userService the userService to be connected to
     */
    public LoginScreen(UserService userService) {
        super("Login Screen", "/login");
        this.userService = userService;
    }

    /**
     * Takes in a username and password from the user and uses UserService to check if there is a correpsonding account.
     * If not, goes back to dashboard. If there is, then enters dashboard and updates the session to be of the logged in
     * user.
     */
    @Override
    public void render() {
        String username;
        String password;

        try {
            System.out.println("+-----------------");

            System.out.println("Please input your username and password!");
            System.out.println("Username: ");
            System.out.print("> ");
            username = app().getConsole().readLine();

            System.out.println("Password: ");
            System.out.print("> ");
            password = app().getConsole().readLine();

            try {
                userService.authenticate(username, password);
            } catch (InvalidRequestException e) {
                System.out.println("Please enter a non-null or non-empty username and password. Please reselect Login if" +
                        " you want to try again");
            } catch (AuthenticationException a) {
                System.out.println("We do not have a registered account with that username and password combination." +
                        " Please reselect login from the home screen to try again, or register to create a new " +
                        "account");
            }

            if (app().isSessionValid()) {
                System.out.println("Login successful, navigating to dashboard...");
                app().getRouter().navigate("/dashboard");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
