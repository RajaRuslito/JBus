package com.RajaYonandroRuslitoJBusAF;

import com.RajaYonandroRuslitoJBusAF.dbjson.Serializable;

/**
 * Represents a renter or a company that can rent buses.
 */
public class Renter extends Serializable {
    public String address;
    public String companyName;
    public String phoneNumber;
    private final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{3,19}$";
    private final String REGEX_PHONE = "^[0-9]{9,12}$";

    /**
     * Constructs a Renter object with the given company name.
     *
     * @param companyName The name of the company.
     */
    public Renter(String companyName) {
        super();
        this.address = " ";
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }

    /**
     * Constructs a Renter object with the given company name, phone number, and address.
     *
     * @param companyName The name of the company.
     * @param phoneNumber The phone number of the renter.
     * @param address     The address of the renter.
     */
    public Renter(String companyName, String phoneNumber, String address) {
        super();
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }

    /**
     * Constructs a Renter object with the given company name and phone number.
     *
     * @param companyName The name of the company.
     * @param phoneNumber The phone number of the renter.
     */
    public Renter(String companyName, String phoneNumber) {
        super();
        this.address = " ";
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }

    /**
     * Validates the company name and phone number based on regular expressions.
     *
     * @return {@code true} if the company name and phone number are valid, otherwise {@code false}.
     */
    public boolean validate() {
        return companyName.matches(REGEX_NAME) && phoneNumber.matches(REGEX_PHONE);
    }

}
