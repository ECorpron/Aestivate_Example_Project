package com.revature.models;

import com.revature.aestivate.model.BaseModel;
import com.revature.aestivate.model.SQLConstraints;
import com.revature.aestivate.util.ColumnField;

import java.text.NumberFormat;
import java.util.Objects;

/**
 * Stores the account information.
 */
public class Account extends BaseModel<Account> {

    // The account id
    private int id;

    // the account name
    private String accountName;

    // the account type
    private AccountType accountType;

    // the owner id
    private int ownerId;

    // the balance of the account
    private Double balance;

    // The max value of the account
    public static final Double ACCOUNT_MAX = 99999999.99;

    /**
     * No args constructor that sets all values to null
     */
    public Account() {

    }

    @Override
    protected ColumnField[] setColumns() {
        ColumnField[] columns = new ColumnField[5];
        columns[0] = new ColumnField("id", "serial", SQLConstraints.PRIMARY_KEY);
        columns[1] = new ColumnField("accountName", "varchar", SQLConstraints.NOT_NULL);
        columns[2] = new ColumnField("accountType", "int", SQLConstraints.NOT_NULL);
        columns[3] = new ColumnField("ownerId", "int", SQLConstraints.NOT_NULL);
        columns[4] = new ColumnField("balance", "numeric(10, 2)", SQLConstraints.NOT_NULL);
        return columns;
    }

    @Override
    protected String setTableName() {
        return "accounts";
    }

    /**
     * Constructor that sets the account name and type
     * @param name the name of the account
     * @param accountType the account type
     */
    public Account(String name, AccountType accountType) {
        this.accountName = name;
        this.accountType = accountType;
    }

    public Account(String accName, AccountType type, int ownerId, Double balance) {
        this(accName, type);
        this.ownerId = ownerId;
        this.balance = balance;
    }

    /**
     * Constructor that sets the account id, name, type, and owner id
     * @param id the account id
     * @param name the name id
     * @param accountType the account type
     * @param ownerId the owner id
     */
    public Account(int id, String name, AccountType accountType, int ownerId) {
        this(name, accountType);
        this.id = id;
        this.ownerId = ownerId;
    }

    /**
     * Constructor that sets the account id, name type, owner id and balance
     * @param id the account id
     * @param name the account name
     * @param accountType the account type
     * @param ownerId the owner id
     * @param balance the account balance
     */
    public Account(int id, String name, AccountType accountType, int ownerId, Double balance) {
        this(id, name, accountType, ownerId);
        this.balance = balance;
    }

    /**
     * Returns this account balance as a string formatted for US currencies
     * @return a string with the balance in US currency format
     */
    public String getBalanceAsString() {
        return getDoubleAsMoneyString(balance);
    }

    /**
     * Converts a double amount into a string that has the amount formatted for US currency
     * @param amount the amount to be turned into a string
     * @return the string representation of the amount
     */
    public String getDoubleAsMoneyString(Double amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return accountName;
    }

    public void setName(String name) {
        this.accountName = name;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Objects.equals(accountName, account.accountName) && accountType == account.accountType && Objects.equals(ownerId, account.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountName, accountType, ownerId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + accountName + '\'' +
                ", accountType=" + accountType +
                ", ownerId=" + ownerId +
                '}';
    }
}
