package com.RajaYonandroRuslitoJBusAF.controller;

import com.RajaYonandroRuslitoJBusAF.Account;
import com.RajaYonandroRuslitoJBusAF.Algorithm;
import com.RajaYonandroRuslitoJBusAF.Renter;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonAutowired;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonTable;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * Controller class handling account-related operations.
 * Implements BasicGetController for basic GET operations.
 *
 * @RestController Indicates that this class is a controller handling HTTP requests.
 * @RequestMapping("/account") Specifies the base URI path for mapping to this controller.
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    @GetMapping
    String index() {
        return "account page";
    }

    /**
     * Retrieves the JsonTable instance for Account objects.
     *
     * @return JsonTable instance for Account objects.
     */
    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    /**
     * JsonTable for storing and retrieving Account objects.
     *
     * @JsonAutowired Custom annotation indicating JSON file details for automatic wiring.
     */
    public static @JsonAutowired(value = Account.class, filepath = "D:\\oop\\JBus\\src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\accountDatabase.json") JsonTable<Account> accountTable;

    /**
     * Static block to initialize the accountTable with JSON file details.
     * Throws a RuntimeException if an IOException occurs during initialization.
     */
    static {
        try {
            accountTable = new JsonTable<>(Account.class, "D:\\oop\\JBus\\src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\accountDatabase.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles HTTP POST request for user registration.
     *
     * @param name     Name of the user.
     * @param email    Email of the user.
     * @param password Password of the user.
     * @return BaseResponse containing the result of the registration operation.
     */
    @PostMapping("/register")
    BaseResponse<Account> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ) {
        Account newaccount = new Account(name, email, password);
        if (name.isBlank()) {
            return new BaseResponse<>(false, "Fail to Register, Name Cannot Be Blank!", null);
        } else if (email.isBlank()) {
            return new BaseResponse<>(false, "Fail to Register, Email Cannot Be Blank!", null);
        } else if (password.isBlank()) {
            return new BaseResponse<>(false, "Fail to Register, Password Cannot Be Blank!", null);
        } else if (!newaccount.validate()) {
            return new BaseResponse<>(false, "Fail to Register, Email/Password Invalid", null);
        } else if (Algorithm.exists(accountTable, newaccount)) {
            return new BaseResponse<>(false, "Fail to Register, Email Already Exist", null);
        }
        String passwordHash = password;
        String generatePassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(passwordHash.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatePassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        newaccount.password = generatePassword;
        /*try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            ;

            StringBuilder strB = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                strB.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
        accountTable.add(newaccount);
        return new BaseResponse<>(true, "Register Success", newaccount);
    }

    /**
     * Handles HTTP POST request for user login.
     *
     * @param email    Email of the user.
     * @param password Password of the user.
     * @return BaseResponse containing the result of the login operation.
     */
    @PostMapping("/login")
    BaseResponse<Account> login(
            @RequestParam String email,
            @RequestParam String password
    ) {
        String generatedPass = password;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(generatedPass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPass = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String finalGeneratedPass = generatedPass;
        Account accounts = Algorithm.<Account>find(AccountController.accountTable, acc -> Objects.equals(acc.email, email) && acc.password.equals(finalGeneratedPass));
        if (accounts != null) {
            return new BaseResponse<>(true, "Login Success", accounts);
        } else {
            return new BaseResponse<>(false, "Login Failed, Wrong Email/Password", null);
        }

    }

    /**
     * Handles HTTP POST request for registering as a renter.
     *
     * @param id          ID of the user.
     * @param companyName Name of the renter's company.
     * @param phoneNumber Phone number of the renter.
     * @param address     Address of the renter.
     * @return BaseResponse containing the result of the renter registration operation.
     */
    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(@PathVariable int id, @RequestParam String companyName, @RequestParam String phoneNumber, @RequestParam String address) {
        Account accounts = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.id == id);
        if (accounts.id == id && accounts.company == null) {
            Renter renter = new Renter(companyName, phoneNumber, address);
            accounts.company = renter;
            return new BaseResponse<>(true, "Successfully Registered as Renter", renter);
        } else if (accounts.id != id) {
            return new BaseResponse<>(false, "Failed to Register as Renter, Wrong Id", null);
        }
        return new BaseResponse<>(false, "Register Renter Failed", null);
    }

    /**
     * Handles HTTP POST request for topping up the account balance.
     *
     * @param id     ID of the user.
     * @param amount Amount to be topped up.
     * @return BaseResponse containing the result of the top-up operation.
     */
    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(@PathVariable int id, @RequestParam double amount) {
        System.out.printf("Enter here\n");
        for (Account account : accountTable) {
            if (account.id == id && account.topUp(amount)) {
                return new BaseResponse<>(true, "Top Up Success", amount);
            }
        }
        return new BaseResponse<>(false, "Top Up Failed", 0.0D);
    }

    /**
     * Handles HTTP GET request for retrieving the user's ID.
     *
     * @param id ID of the user.
     * @return BaseResponse containing the user's ID.
     */
    @GetMapping("/{id}/getMyId")
    BaseResponse<Account> getMyId(@RequestParam int id) {
        Account acc = Algorithm.<Account>find(getJsonTable(), (e) -> {
            return e.id == id;
        });
        return new BaseResponse<>(true, "id", acc);
    }

}
