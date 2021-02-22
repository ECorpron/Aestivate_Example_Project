package com.revature.service;

import com.revature.exceptions.*;
import com.revature.models.Account;
import com.revature.models.AppUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.revature.BankingApp.app;

/**
 * Handles account functions that requires accessing AccountRepository, such as creating new accounts
 */
public class AccountService {

    /**
     * Constructor that takes in the accountRepository to connect to
     */
    public AccountService() {
    }

    /**
     * Takes an inputted account and attempts to save it to the database. Thows an InvalidRequestException if the
     * account is invalid, or a ResourcePersistenceException if the account already exists. Otherwise creates the
     * account with an initial balance of zero.
     * @param account the account to be created
     */
    public void createAccount(Account account) {
        if (!isAccountValid(account)) throw new InvalidRequestException("Invalid new account given!");

        account.setOwnerId(app().getCurrentSession().getSessionUser().getId());

        Map<String, Object> fields = new HashMap<>();
        fields.put("accountName", account.getName());
        fields.put("ownerId", account.getOwnerId());

        if (account.find(fields).size() > 0) {
            throw new ResourcePersistenceException("The provided account name already exists under that user!");
        }

        // accounts start with no balance
        account.setBalance(0.00);

        account.save();
    }

    /**
     * Returns a set of all accounts that the given user has.
     * @param user The user to be getting information about
     * @return returns a set of all accounts the user has
     */
    public ArrayList<Account> getAccounts(AppUser user) {
        Map<String, Object> field = new HashMap<>();
        field.put("ownerId", user.getId());

        Account dummy = new Account();

        return dummy.find(field);
    }

    /**
     * Deposits the given amount into the given account.
     *
     * Throws an InvalidRequestException if the deposit amount is
     * null, an InvalidDepositException if the deposit amount is negative or contains more than 2 decimal places, a
     * ResourcePersistanceException if the deposit causes the account to go past the account limit, and an
     * AuthenticationException of the account can't be found in the database.
     *
     * Else it deposits that amount in the account
     * @param account the account to be depositing in
     * @param deposit the amount to be deposited
     */
    public void deposit(Account account, Double deposit) {
        if (deposit == null) throw new InvalidRequestException("Deposits must be numbers!");

        if (deposit <= 0 || BigDecimal.valueOf(deposit).scale() > 2) {
            throw new InvalidDepositException("Invalid deposit amount!");
        }

        if (deposit + account.getBalance() > Account.ACCOUNT_MAX) {
            throw new ResourcePersistenceException("Deposit goes over account limit!");
        }

        Map<String, Object> field = new HashMap<>();
        field.put("id", account.getId());

        if (account.find(field).size() == 0) throw new AuthenticationException("That account doesn't exist!");

        account.setBalance(account.getBalance()+deposit);
        account.save();
    }

    // balance needs to be non-null, more than zero, no more than two decimal places, and must withdraw more than the
    // account balance

    /**
     * Withdraws the given amount from the given account.
     *
     * Throws an InvalidRequestException if the withdraw amount is null, an InvalidWithdrawalException if the withdraw
     * amount is negative or has more than two decimal places, a ResourcePersistenceException if the withdraw amount
     * causes an overdraft, and an AuthenticationException if the account can't be found.
     *
     * @param account The account to be withdrawn from
     * @param withdraw the withdrawal amount
     */
    public void withdraw(Account account, Double withdraw) {
        if (withdraw == null) throw new InvalidRequestException("Withdrawals must be numbers!");

        if (withdraw <= 0 || BigDecimal.valueOf(withdraw).scale() > 2) {
            throw new InvalidWithdrawalException("Invalid withdrawal amount!");
        }

        if (account.getBalance() - withdraw < 0) {
            throw new ResourcePersistenceException("Withdrawal overdrafts the account!");
        }

        Map<String, Object> field = new HashMap<>();
        field.put("id", account.getId());

        if (account.find(field).size() == 0) throw new AuthenticationException("That account doesn't exist!");

        account.setBalance(account.getBalance()-withdraw);

        account.save();
    }

    /**
     * Checks if the given account is valid by checking if the account is null, or if the account name or type is null
     * or empty strings. If any of those are true, returns false. Else returns true
     * @param account The account to be checked
     * @return returns false if the account or the account type is null, or the account name is null or an empty string.
     *          Otherwise returns true.
     */
    public boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getName() == null || account.getName().trim().equals("")) return false;
        if (account.getAccountType() == null) return false;
        return true;
    }
}
