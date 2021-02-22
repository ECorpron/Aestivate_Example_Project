package com.revature.screens;

import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.ResourcePersistenceException;
import com.revature.models.AppUser;
import com.revature.service.UserService;

import static com.revature.BankingApp.app;

/**
 * Class for managing registration of new accounts. Takes in user info, and attempts create an AppUser with the info.
 */
public class RegisterScreen extends Screen{

    // The UserService that handles database operations
    private UserService userService;

    /**
     * Takes in the UserService, and sets name to Register Screen, and route to /register
     * @param userService
     */
    public RegisterScreen(UserService userService) {
        super("Register Screen", "/register");
        this.userService = userService;
    }

    /**
     * Takes in user input for first name, last name, username, and password, and attempts to create an account with
     * those values using UserService.
     */
    @Override
    public void render() {
        String firstName;
        String lastName;
        String username;
        String password;

        try {
            System.out.println("+--------------------");

            System.out.println("Sign up for a new account!");
            System.out.println("First name: ");
            System.out.print("> ");
            firstName = app().getConsole().readLine();

            System.out.println("Last Name: ");
            System.out.print("> ");
            lastName = app().getConsole().readLine();

            System.out.println("Username: ");
            System.out.print("> ");
            username = app().getConsole().readLine();

            System.out.println("Password: ");
            System.out.print("> ");
            password = app().getConsole().readLine();

            AppUser newUser = new AppUser(firstName, lastName, username, password);

            try {
                userService.register(newUser);
            } catch (ResourcePersistenceException e) {
                System.out.println("That username is already in use. Please re-select Register and try again!");
            } catch (InvalidRequestException ire) {
                System.out.println("Invalid account information given. Please reselect Register and resubmit account" +
                        " info.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[FATAL] - Fatal error encountered, closing app");
            app().setAppRunning(false);
        }
    }
}
