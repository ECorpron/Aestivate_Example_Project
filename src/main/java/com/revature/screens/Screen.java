package com.revature.screens;

/**
 * Abstract class that describes a screen. A screen refers to a specific screen that a user will interact with.
 *
 * Classes have to implement a render method, which is how they show themselves on the console, and how they handle
 * inputs from the user.
 */
public abstract class Screen {

    // The name of the screen
    protected String Name;

    // The route to the screen
    protected String Route;

    /**
     * Constructor that sets the name and route of the screen
     * @param name the name of the screen
     * @param route the route of the screen
     */
    public Screen(String name, String route) {
        Name = name;
        Route = route;
    }

    /**
     * Method that all screens need to implement. This is how the screen renders itself onto the console, and how
     * it handles input form the user.
     */
    public abstract void render();

    /**
     * Getter method for the screen name
     * @return returns the name of the screen
     */
    public String getName() {
        return Name;
    }

    /**
     * Getter method for the route of the screen
     * @return returns the route of the screen
     */
    public String getRoute() {
        return Route;
    }

}
