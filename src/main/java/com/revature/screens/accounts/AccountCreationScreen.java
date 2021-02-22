package com.revature.screens.accounts;

import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.ResourcePersistenceException;
import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.screens.Screen;
import com.revature.service.AccountService;

import java.io.IOException;

import static com.revature.BankingApp.app;

/**
 * Renders the Account Creation page. Takes in an account name and an account type from a user, then uses AccountService
 * to create it.
 */
public class AccountCreationScreen extends Screen {

    // The account service to use
    private AccountService accountService;

    /**
     * Sets the accountService to the given one. Sets name to AccountCreationScreen and route to /createAccount
     * @param accountService the accountService to use
     */
    public AccountCreationScreen(AccountService accountService) {
        super("AccountCreationScreen", "/createAccount");
        this.accountService = accountService;
    }

    /**
     * Takes in an account name and an account type from the user, and creates a new account with those values. Then
     * uses AccountServices to create the new account. After success or failure, returns to Dashboard.
     */
    @Override
    public void render() {
        String accountName;
        String accountType;
        AccountType type = null;

        System.out.println("+-------------------------");

        try {
            System.out.println("Create a new account!");
            System.out.println("Account name: ");
            System.out.print("> ");
            accountName = app().getConsole().readLine();

            System.out.println("Please select an account type: ");
            int count = 1;
            for (AccountType accounts : AccountType.values()) {
                System.out.println(count+") "+accounts);
                count++;
            }
            System.out.print("> ");
            accountType = app().getConsole().readLine();

            switch (accountType) {
                case "1":
                    type = AccountType.SAVINGS;
                    break;
                case "2":
                    type = AccountType.CHECKING;
                    break;
                case "3":
                    type = AccountType.JOINT;
                    break;
                default:
                    System.out.println("Please select one of the given options, navigating back dashboard");
                    app().getRouter().navigate("/dashboard");
            }

            Account account = new Account(accountName, type);

            try{
                accountService.createAccount(account);
            } catch (InvalidRequestException inv) {
                System.out.println("Invalid account name or type given. Navigating back to Dashboard. Please select " +
                        "create account to try again");
                app().getRouter().navigate("/dashboard");
            } catch (ResourcePersistenceException r) {
                System.out.println("You already have an account with that name. Navigating back to Dashboard, please" +
                        " select create account to try again.");
                app().getRouter().navigate("/dashboard");
            }

            System.out.println("Creation of "+account.getAccountType()+" Account "+account.getName()+" successful.");
            System.out.println("Account begins with zero dollars. Please select deposit to increase the account balance.");
            System.out.println("\n Returning to dashboard");
            app().getRouter().navigate("/dashboard");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[FATAL] - Fatal error encountered, closing app");
            app().setAppRunning(false);
        }
    }
}
