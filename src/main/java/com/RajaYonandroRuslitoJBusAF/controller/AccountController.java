package com.RajaYonandroRuslitoJBusAF.controller;

import com.RajaYonandroRuslitoJBusAF.Account;
import com.RajaYonandroRuslitoJBusAF.Renter;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonAutowired;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonTable;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    @JsonAutowired(value = Account.class, filepath = )
    /*@GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        return new Account(name, email, password);
    }*/

    /*@GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }*/
    @GetMapping("/register")
    BaseResponse<Account> register(@RequestParam String name, @RequestParam String email, @RequestParam String password){

    }
    @GetMapping("/register")
    BaseResponse<Double> register(@RequestParam int id, @RequestParam double amount){

    }
    @GetMapping("/login")
    BaseResponse<Account> login(@RequestParam String email, @RequestParam String password){

    }
    @GetMapping("/registerRenter")
    BaseResponse<Renter> registerRenter(@RequestParam int id, @RequestParam String companyName, @RequestParam String address, @RequestParam String phoneNumber){

    }


    @Override
    public JsonTable<Account> getJsonTable() {
        return null;
    }
}
