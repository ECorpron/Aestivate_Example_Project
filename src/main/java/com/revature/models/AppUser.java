package com.revature.models;

import com.revature.aestivate.model.BaseModel;
import com.revature.aestivate.model.SQLConstraints;
import com.revature.aestivate.util.ColumnField;

import java.util.Objects;

/**
 * Stores a User's information
 */
public class AppUser extends BaseModel<AppUser> {

    // The user id
    private int id;

    // the user's first name
    private String firstName;

    // the user's last name
    private String lastName;

    // The user's username
    private String username;

    // The user's password
    private String password;

    /**
     * A no args constructor that sets all fields to null
     */
    public AppUser() {

    }

    @Override
    protected ColumnField[] setColumns() {
        ColumnField[] columns = new ColumnField[5];
        columns[0] = new ColumnField("id", "serial", SQLConstraints.PRIMARY_KEY);
        columns[1] = new ColumnField("username", "varchar", SQLConstraints.UNIQUE);
        columns[2] = new ColumnField("password", "varchar", SQLConstraints.NOT_NULL);
        columns[3] = new ColumnField("firstName", "varchar", SQLConstraints.NOT_NULL);
        columns[4] = new ColumnField("lastName", "varchar", SQLConstraints.NOT_NULL);

        return columns;
    }

    @Override
    protected String setTableName() {
        return "app_users";
    }

    /**
     * Constructor that sets the username and password
     * @param username the user's username
     * @param password the user's password
     */
    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor that sets the firstname, lastname, username, and password
     * @param firstName the user's firstname
     * @param lastName the user's lastname
     * @param username the user's username
     * @param password The user's password
     */
    public AppUser(String firstName, String lastName, String username, String password) {
        this(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructor that sets the firstname, lastname, username, password, and id
     * @param id the user id
     * @param firstName the user's firstname
     * @param lastName the user's lastname
     * @param username the user's username
     * @param password the user's password
     */
    public AppUser(int id, String firstName, String lastName, String username, String password) {
        this(firstName, lastName, username, password);
        this.id = id;
    }

    /**
     * A copy constructor that copies this user information from another user
     * @param copy the user to be copying information from
     */
    public AppUser(AppUser copy) {
        this(copy.id, copy.firstName, copy.lastName, copy.username, copy.password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id && Objects.equals(firstName, appUser.firstName) && Objects.equals(lastName, appUser.lastName) && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, password);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
