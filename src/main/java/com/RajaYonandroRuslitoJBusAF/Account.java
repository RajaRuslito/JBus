package com.RajaYonandroRuslitoJBusAF;
import com.RajaYonandroRuslitoJBusAF.dbjson.Serializable;

import java.util.regex.Pattern;


public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    
    public Account(String name, String email, String password){
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.company = null;
        this.balance = 0.0D;
        validate();
    }
    public String toString(){
        return " Name : " + name + "\n Email : " + email + "\n Password : " + password + "\n\n";
    }

    public boolean validate() {
        boolean isEmailValid = Pattern.matches(REGEX_EMAIL, email);
        boolean isPasswordValid = Pattern.matches(REGEX_PASSWORD, password);

        return isEmailValid && isPasswordValid;
    }
    public boolean topUp(double amount){
        //this.balance = balance;
        if(amount > 0.0D){
            this.balance += amount;
            return true;
        }
        return false;
    }
}


