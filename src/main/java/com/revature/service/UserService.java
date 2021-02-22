package com.revature.service;

import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.ResourcePersistenceException;
import com.revature.models.AppUser;
import com.revature.util.ConnectionFactory;
import com.revature.util.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.revature.BankingApp.app;

/**
 * Handles user functions that require accessing the UserRepository, such as registration and validity checks.
 */
public class UserService {

    /**
     * Constructor for the UserService, that takes in the UserRepository
     */
    public UserService() {
    }

    /**
     * Registers a new AppUser by saving them into the app_user table. Throws an InvalidRequestException if an
     * AppUser is given that has invalid information, and a ResourcePersistenceException if the username is already
     * being used.
     * @param newUser the new user to be saved
     */
    public void register(AppUser newUser) {
        if (!isUserValid(newUser)) throw new InvalidRequestException("Invalid new user provided!");

        Map<String, Object> search = new HashMap<>();
        search.put("username", newUser.getUsername());

        if (newUser.exists() || newUser.find(search).size() > 0) {
            throw new ResourcePersistenceException("The provided username is already in use!");
        }

        newUser.save();
    }

    /**
     * Sees if there is a corresponding account for the given username and password. Throws an InvalidRequestException
     * if the credentials passes are null or empty strings. If the user is invalid, throws an AuthenticationException.
     * If the user is valid, sets current session to a new session passing in the current user and the current
     * Connection.
     * @param username the username of the account
     * @param password the password of the account
     */
    public void authenticate(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credentials provided (null or empty strings)!");
        }

        Map<String, Object> search = new HashMap<>();
        search.put("username", username);
        search.put("password", password);

        AppUser dummy = new AppUser();
        ArrayList<AppUser> auth = dummy.find(search);

        if (auth.size() != 1) {
            throw new AuthenticationException();
        }

        app().setCurrentSession(new Session(auth.get(0), ConnectionFactory.getInstance().getConnection()));
    }

    /**
     * Checks if the AppUser credentials are valid. If the passed in AppUser is null, or if any of the AppUser fields
     * are null or contain an empty string, then returns false.
     * @param user The AppUser to be validated
     * @return returns true if the user is valid, returns false if the user, or user fields are null or contain empty
     *          strings
     */
    public boolean isUserValid(AppUser user) {
        if (user == null ) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;
    }
}
