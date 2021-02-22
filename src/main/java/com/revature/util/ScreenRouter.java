package com.revature.util;

import com.revature.screens.Screen;

import java.util.ArrayList;

/**
 * Manages and stores the variety of screens that a user will need to access.
 */
public class ScreenRouter {

    //A set of screens. A set will ensure that only one screen is ever rendered
    private ArrayList<Screen> screens = new ArrayList<>();

    /**
     * Adds a screen to the screens set.
     * @param screen the screen to be added
     * @return returns the ScreenRouter with the added set
     */
    public ScreenRouter addScreen(Screen screen) {
        //System.out.println("[LOG] - Loading "+screen.getName()+" into router");
        screens.add(screen);
        return this;
    }

    /**
     * Removes a screen with the given name
     * @param screenName the name of the screen to be removed
     * @return returns true if the screen was found and removed, else returns false
     */
    public boolean removeByName(String screenName) {
        for (Screen screen: screens) {
            if (screen.getName().equals(screenName)) {
                return screens.remove(screen);
            }
        }
        return false;
    }

    /**
     * Deletes all screens
     */
    public void removeAll() {
        screens = null;
    }

    /**
     * Navigates to the Screen with the inputed route. Doesn't change screen if the screen is not found.
     * @param route the route of the Screen to be navigated to.
     */
    public void navigate(String route) {
        for (Screen screen: screens) {
            if (screen.getRoute().equals(route)) {
                screen.render();
            }
        }
    }

    /**
     * Getter method for the Set of screens
     * @return returns the Set of screens
     */
    public ArrayList<Screen> getScreens() {
        return screens;
    }
}
