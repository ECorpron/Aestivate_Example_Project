package com.revature.screens.accounts;

import com.revature.exceptions.*;
import com.revature.models.Account;
import com.revature.screens.Screen;
import com.revature.service.AccountService;

import java.io.IOException;

import static com.revature.BankingApp.app;

/**
 * Renders the account screen. Takes an account to render information about, and uses AccountService to access the
 * database.
 */
public class AccountScreen extends Screen {

    // The service that access the accounts in the backend
    AccountService accService;

    // The account being viewed
    Account account;

    /**
     * Sets the AccountService to the inputted one, and sets the showed account to the inputted account
     * @param accService The account service to use
     * @param account the account to be shown
     */
    public AccountScreen(AccountService accService, Account account) {
        super("AccountScreen", "/account");
        this.accService = accService;
        this.account = account;
    }

    /**
     * Shows the given account information to the user, then lets the user to select to deposit, withdraw, or leave
     * the account viewing screen. If they select deposit, they move to the deposit method, if they select withdraw they
     * move to the withdraw method. If they select to leave they go back to the View Accounts screen.
     */
    @Override
    public void render() {
        System.out.println("\n +--------------------------------");
        if (account == null) {
            System.out.println("[LOG] Error encountered, heading back to account selection...");
            app().getRouter().navigate("/viewAccounts");
        }

        System.out.println("You have selected to view "+account.getAccountType()+" Account "+account.getName());
        System.out.println("\n The account info is: ");
        System.out.println("Account Type: "+account.getAccountType());
        System.out.println("Account Name: "+account.getName());
        System.out.println("Account ID: "+account.getId());
        System.out.println("Account Balance: "+account.getBalanceAsString());

        System.out.println("\nWould you like to: ");
        System.out.println("1) Deposit Money");
        System.out.println("2) Withdraw Money");
        System.out.println("3) Return to Account Selection");

        try {
            System.out.print("> ");
            String userSelection = app().getConsole().readLine();

            switch (userSelection) {
                case "1":
                    deposit();
                    break;
                case "2":
                    withdraw();
                    break;
                case "3":
                    System.out.println("Returning to Account Selection...");
                    app().getRouter().navigate("/viewAccounts");
                    break;
                default:
                    System.out.println("Invalid Selection! Please enter a valid selection!");
                    System.out.println("Returning to Account Viewing, please reselect an account and try again.");
                    app().getRouter().navigate("/viewAccounts");
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Please enter an Integer. Restarting Account screen.");
            app().getRouter().navigate("/account");

        } catch (IOException e) {
            System.out.println("[LOG] Error encountered, heading back to dashboard...");
            app().getRouter().navigate("/dashboard");
        }

    }

    /**
     * Allows the user to input how much money to withdraw, and sends that to the accService. If they give an invalid
     * input, sends them back to the render method. If the account can't be reached, they are sent back to the view
     * accounts page.
     */
    private void withdraw() {
        System.out.println("+------------------------------");

        System.out.println("Please enter the amount you wish to withdraw.");
        System.out.println("Your account currently has a balance of "+account.getBalanceAsString());
        System.out.println("Please note that withdrawals can't be enough to make the account balance negative.");

        try {
            System.out.print("> ");
            String userSelection = app().getConsole().readLine();

            double withdraw = Double.parseDouble(userSelection);

            try {
                accService.withdraw(account, withdraw);

            } catch (InvalidWithdrawalException iwe) {
                System.out.println("Invalid withdrawal amount. The withdraw amount must be a positive number that doesn't" +
                        " have fractions of a cent.");
                System.out.println("Returning to account page.\n");
                app().getRouter().navigate("/account");
            } catch (AuthenticationException rpe) {
                System.out.println("The account can't be reached. Returning to View Accounts");
                app().getRouter().navigate("/viewAccounts");
            } catch (InvalidRequestException ire) {
                System.out.println("The withdraw amount must be submitted in numeric form. Returning to account page.");
                app().getRouter().navigate("/account");
            } catch (ResourcePersistenceException rpe) {
                System.out.println("Withdraw amount causes overdraft. Returning to account screen. Please try again.");
                app().getRouter().navigate("/account");
            }

            System.out.println("Successfully withdrew funds from the account.");
            System.out.println("The account now has a balance of "+account.getBalanceAsString());
            System.out.println("Returning to the Account Screen...");
            app().getRouter().navigate("/account");

        } catch (IOException e) {
            System.out.println("[LOG] Error encountered, loading account again...");
            app().getRouter().navigate("/account");
        }
    }

    /**
     * Allows the user to input how much money to deposit, and sends that to the accService. If they give an invalid
     * input, sends them back to the render method. If the account can't be reached, they are sent back to the view
     * accounts page.
     */
    private void deposit() {
        System.out.println("+---------------------------");

        System.out.println("Please enter the amount you wish to deposit.");
        System.out.println("Your account currently has a balance of "+account.getBalanceAsString());
        System.out.println("Please note that accounts have a max balance " +
                "of "+account.getDoubleAsMoneyString(Account.ACCOUNT_MAX));

        try {
            System.out.print("> ");
            String userSelection = app().getConsole().readLine();

            double deposit = Double.parseDouble(userSelection);

            try {
                accService.deposit(account, deposit);

            } catch (InvalidDepositException ide) {
                System.out.println("Invalid deposit amount. The deposit amount must be a positive number that doesn't" +
                        " have fractions of a cent.");
                System.out.println("Returning to account page.\n");
                app().getRouter().navigate("/account");
            } catch (AuthenticationException ire) {
                System.out.println("The account can't be reached. Returning to View Accounts");
                app().getRouter().navigate("/viewAccounts");
            } catch (InvalidRequestException ire) {
                System.out.println("The deposit amount must be submitted in numeric form. Returning to account page.");
                app().getRouter().navigate("/account");
            } catch (ResourcePersistenceException rpe) {
                System.out.println("Deposit amount exceeds account limit. Returning to account screen. Please try again.");
                app().getRouter().navigate("/account");
            }

            System.out.println("Successfully added funds to the account.");
            System.out.println("The account now has a balance of "+account.getBalanceAsString());
            System.out.println("Returning to the Account Screen...");
            app().getRouter().navigate("/account");

        } catch (IOException e) {
            System.out.println("[LOG] Error encountered, loading account again...");
            app().getRouter().navigate("/account");
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid number. Returning to account page.");
            app().getRouter().navigate("/account");
        }

    }
}
