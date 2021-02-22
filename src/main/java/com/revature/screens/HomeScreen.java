package com.revature.screens;

import com.revature.exceptions.ResourcePersistenceException;

import java.io.IOException;

import static com.revature.BankingApp.app;

/**
 * Manages the home screen of the application. Allows points to the Login and Register screen, or allows Users to exit
 * the app.
 */
public class HomeScreen extends Screen{

    /**
     * No args constructor that sets name to Homescreen and route to /home
     */
    public HomeScreen() {
        super("HomeScreen", "/home");
    }

    /**
     * Renders the home screen. Allows users to select to go to either the Login screen, or the register screen, or to
     * exit the application. If another screen is selected, uses app().getRouter().navigate(ScreenRoute) to change
     * screens.
     */
    @Override
    public void render(){
        System.out.println("+------------------------------------");
        System.out.println("Welcome to BankingApp!\n");

        System.out.println("Please select one of the options below!\n");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit application");

        try {
            System.out.print("> ");
            String userSelection = app().getConsole().readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to login screen");
                    app().getRouter().navigate("/login");
                    break;
                case "2":
                    System.out.println("Navigate to register screen");
                    app().getRouter().navigate("/register");
                    break;
                case "3":
                    System.out.println("Exiting the application...");
                    app().setAppRunning(false);
                    app().getRouter().removeAll();
                    break;
                default:
                    System.out.println("Invalid selection! Please select one of the given options.");
            }

            if (!app().isAppRunning()) throw new ResourcePersistenceException("App should be closed!");
        } catch (IOException e) {
            e.printStackTrace();
            app().setAppRunning(false);
        }
    }
}
