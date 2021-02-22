package com.revature.screens.accounts;

import com.revature.models.Account;
import com.revature.models.AppUser;
import com.revature.screens.Screen;
import com.revature.service.AccountService;

import java.io.IOException;
import java.util.ArrayList;

import static com.revature.BankingApp.app;

/**
 * Renders the accounts view to the user. Uses the logged in user's information to grab all accounts that they are
 * owners of, and shows information about them. Also allows the user to either select an account to deposit or
 * withdraw money from or head back to the dashboard.
 */
public class ViewAccountsScreen extends Screen {

    // The account service to be used
    private AccountService accService;

    /**
     * Constructor that sets the account Service to the inputted one. Also sets the name to AccountViewingScreen and the
     * route to /viewAccounts.
     * @param accService
     */
    public ViewAccountsScreen(AccountService accService) {
        super("AccountViewingScreen", "/viewAccounts");
        this.accService = accService;
    }

    /**
     * If the user has no accounts, sends them back to the dashboard. If they have one 1 or more accounts, it lists
     * all accounts that they own. Allows them to then select an account to move into the AccountScreen with, or select
     * to go back to the dashboard.
     */
    @Override
    public void render() {
        AppUser user = app().getCurrentSession().getSessionUser();
        ArrayList<Account> accounts = accService.getAccounts(user);

        System.out.println("+-----------------");
        System.out.println("\n");

        if (accounts.size() == 0) {
            System.out.println("It looks like you have no accounts. Please go to Account Creation and create an " +
                    "an account.\n\n");
            System.out.println("Heading back to dashboard...");
            app().getRouter().navigate("/dashboard");
        }

        System.out.println("You have "+accounts.size()+" active accounts.\n");
        System.out.println("Please select an account to modify: ");

        int counter = 1;
        for (Account account: accounts) {
            System.out.print(counter+") "+account.getAccountType()+" Account "+account.getName());
            System.out.println(": balance: "+account.getBalanceAsString());
            counter++;
        }

        System.out.println("Otherwise select ");
        System.out.println((accounts.size()+1)+") to return to Dashboard");

        Account selected = null;

        try {
            System.out.print("> ");
            String userSelection = app().getConsole().readLine();

            Integer integerRep = Integer.parseInt(userSelection);

            if (integerRep > accounts.size() + 1 || integerRep < 1) {
                System.out.println("Invalid Selection! Please enter a valid selection!");
                System.out.println("Returning to Dashboard, please reselect View Accounts to try again.");
                app().getRouter().navigate("/dashboard");
            } else {
                if (integerRep == accounts.size() + 1) {
                    System.out.println("Heading back to dashboard...");
                    app().getRouter().navigate("/dashboard");
                } else {
                    selected = accounts.get(integerRep - 1);
                    AccountScreen accountView = new AccountScreen(accService, selected);
                    app().getRouter().removeByName(accountView.getName());
                    app().getRouter().addScreen(accountView);
                    System.out.println("\n Navigating to the account screen...");
                    app().getRouter().navigate("/account");
                }
            }
        } catch (NumberFormatException n) {
            System.out.println("Please enter an Integer. Returning to dashboard, please select View Accounts to " +
                    "try again.");
            app().getRouter().navigate("/dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[FATAL] - Fatal error encountered, closing app");
            app().setAppRunning(false);
        }
    }
}
