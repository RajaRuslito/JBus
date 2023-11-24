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


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    @GetMapping
    String index() {
        return "account page";
    }

    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    @JsonAutowired(value = Account.class, filepath = "D:\\oop\\JBus\\src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\accountDatabase.json")
        public static JsonTable<Account> accountTable;

    static {
        try {
            accountTable = new JsonTable<>(Account.class, "D:\\oop\\JBus\\src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\accountDatabase.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/register")
    BaseResponse<Account> register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        Account newaccount = new Account(name, email, password);
        /*if (!newaccount.validate() || (name.isBlank() || email.isBlank() || password.isBlank()) || Algorithm.exists(accountTable, newaccount)){
            return new BaseResponse<>(false, "Gagal Register", null);
        }*/

        if(name.isBlank()){
            return new BaseResponse<>(false, "Gagal Register, Harap Masukkan Nama", null);
        }
        else if(email.isBlank()){
            return new BaseResponse<>(false, "Gagal Register, Harap Masukkan Email", null);
        }
        else if(password.isBlank()){
            return new BaseResponse<>(false, "Gagal Register, Harap Masukkan Password", null);
        }
        else if(!newaccount.validate()){
            return new BaseResponse<>(false, "Gagal Register, Email/Password Invalid", null);
        }
        else if(Algorithm.exists(accountTable, newaccount)){
            return new BaseResponse<>(false, "Gagal Register, Email Sudah Terpakai", null);
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();;

            StringBuilder strB = new StringBuilder();

            for(int i = 0; i < bytes.length;i++){
                strB.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        accountTable.add(newaccount);
        return new BaseResponse<>(true, "Berhasil Register", newaccount);
    }

    @PostMapping("/login")
    BaseResponse<Account> login(@RequestParam String email, @RequestParam String password) {
        for (Account accounts : accountTable) {
            if (accounts.email.equals(email) && accounts.password.equals(password)) {
                return new BaseResponse<>(true, "Berhasil Login", accounts);
            }
            else if(!accounts.email.equals(email)){
                return new BaseResponse<>(false, "Gagal Login, Email Salah", null);
            }
            else if(!accounts.password.equals(password)){
                return new BaseResponse<>(false, "Gagal Login, Password Salah", null);
            }
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();;

            StringBuilder strB = new StringBuilder();

            for(int i = 0; i < bytes.length;i++){
                strB.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return new BaseResponse<>(false, "Gagal Login", null);
    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(@PathVariable int id, @RequestParam String companyName, @RequestParam String phoneNumber, @RequestParam String address) {
        for (Account accounts : accountTable) {
            if (accounts.id == id && accounts.company == null) {
                Renter renter = new Renter(companyName, phoneNumber, address);
                accounts.company = renter;
                return new BaseResponse<>(true, "Berhasil Register Renter", renter);
            }
            else if(accounts.id != id){
                return new BaseResponse<>(false, "Gagal Register Renter, Id Salah", null);
            }
        }
        return new BaseResponse<>(false, "Gagal Register Renter", null);
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(@PathVariable int id, @RequestParam double amount) {
        System.out.printf("Enter here\n");
        for(Account account : accountTable) {
            if(/*account.id == id && */account.topUp(amount)) {
                return new BaseResponse<>(true, "Berhasil Top Up", amount);
            }
        }
        return new BaseResponse<>(false, "Gagal Top Up", 0.0D);
    }
    /*@GetMapping("/{id}")
    String getById(@PathVariable int id) {
        return "account id " + id + " not found!";
    }*/

}
