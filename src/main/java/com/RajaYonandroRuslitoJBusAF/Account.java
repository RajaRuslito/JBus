package com.RajaYonandroRuslitoJBusAF;

import com.RajaYonandroRuslitoJBusAF.dbjson.Serializable;

import java.util.regex.Pattern;

/**
 * Represents an account entity with associated details such as name, email, password, company, and balance.
 * Extends Serializable for serialization purposes.
 */
public class Account extends Serializable {
    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    /**
     * Constructor to initialize an Account object with the provided details.
     *
     * @param name     Name of the account.
     * @param email    Email of the account.
     * @param password Password of the account.
     */
    public Account(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.company = null;
        this.balance = 0.0D;
        validate();
    }

    public String toString() {
        return " Name : " + name + "\n Email : " + email + "\n Password : " + password + "\n\n";
    }

    /**
     * Validates the email and password format using regular expressions.
     *
     * @return true if both email and password are valid, false otherwise.
     */
    public boolean validate() {
        boolean isEmailValid = Pattern.matches(REGEX_EMAIL, email);
        boolean isPasswordValid = Pattern.matches(REGEX_PASSWORD, password);

        return isEmailValid && isPasswordValid;
    }

    /**
     * Adds the specified amount to the account balance.
     *
     * @param amount Amount to be added to the balance.
     * @return true if the amount is positive and added successfully, false otherwise.
     */
    public boolean topUp(double amount) {
        //this.balance = balance;
        if (amount > 0.0D) {
            this.balance += amount;
            return true;
        }
        return false;
    }
}


