package com.RajaYonandroRuslitoJBusAF.controller;

import com.RajaYonandroRuslitoJBusAF.*;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonAutowired;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {

    public static @JsonAutowired(value = Payment.class, filepath = "D:\\oop\\JBus\\src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\payment.json") JsonTable<Payment> paymentTable;

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }
    static {
        try {
            paymentTable = new JsonTable<>(Payment.class, "D:\\oop\\JBus\\src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\payment.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*@Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }*/

    //@PostMapping("/makeBooking")
    @RequestMapping(value = "/makeBooking", method = RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate

    ) {

        JsonTable<Account> accounts;
        JsonTable<Bus> buses;
        try {
            accounts = new JsonTable<>(Account.class, AccountController.accountTable.filepath);
            buses = new JsonTable<>(Bus.class, BusController.busTable.filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Account buyer = Algorithm.<Account>find(accounts, (e) -> {
            return e.id == buyerId;
        });

        Bus bus = Algorithm.<Bus>find(buses, (e) -> {
            boolean bal_check = buyer == null ? false : e.price.price < buyer.balance;
            return e.id == busId && bal_check && e.schedules.contains(Timestamp.valueOf(departureDate));
        });

        if(buyer != null && bus != null && buyer.company.id == renterId) {
            Renter renter = buyer.company;
            Payment payment = new Payment(buyer, renter, busId, busSeats, Timestamp.valueOf(departureDate));

            return new BaseResponse<>(true, "Successful", payment);
        }

        return new BaseResponse<>(false, "Unsuccessful", null);
    }
    @PostMapping("/{id}/accept")
    BaseResponse<Payment> accept(
            @PathVariable int id
    ) {
        Payment payment =Algorithm.<Payment>find(paymentTable, e -> e.id==id);
        if (payment != null){
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Payment Accepted", payment);
        }
        return new BaseResponse<>(false, "Cannot Accept Payment", null);
    }
    @PostMapping("/{id}/cancel")
    BaseResponse<Payment> cancel(
            @PathVariable int id
    ) {
        Payment payment =Algorithm.<Payment>find(paymentTable, e -> e.id==id);
        if (payment != null){
            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<Payment>(true, "Payment Cancelled", payment);
        }
        return new BaseResponse<Payment>(false, "Cannot cancel the payment", null);
    }

}

/*

        import org.springframework.web.bind.annotation.*;

        import java.sql.Timestamp;
        import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{
    public static @JsonAutowired(value = Bus.class, filepath = "C:\\Java\\JBus\\src\\main\\java\\com\\MuhammadSesarafliAljagraJBusBR\\json\\payment.json") JsonTable<Payment> paymentTable;
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    //Add new Station
    @PostMapping("/makeBooking")
    BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ) {
        Account buyers = Algorithm.<Account>find(AccountController.accountTable, e -> e.id==buyerId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, e -> e.id==busId);

        if (buyers != null && bus != null) {
            Payment.makeBooking(Timestamp.valueOf(departureDate), busSeats, bus);
            Payment payment = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
            paymentTable.add(payment);
            return new BaseResponse<>(true, "Success Make a book!", payment);
        }
        return new BaseResponse<>(false, "Failed To book!", null);

    }
    @PostMapping("/{id}/accept")
    BaseResponse<Payment> accept(
            @PathVariable int id
    ) {
        Payment payment =Algorithm.<Payment>find(paymentTable, e -> e.id==id);
        if (payment != null){
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Payment Accepted", payment);
        }
        return new BaseResponse<>(false, "Cannot Accept Payment", null);
    }
    @PostMapping("/{id}/cancel")
    BaseResponse<Payment> cancel(
            @PathVariable int id
    ) {
        Payment payment =Algorithm.<Payment>find(paymentTable, e -> e.id==id);
        if (payment != null){
            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<Payment>(true, "Payment Cancelled", payment);
        }
        return new BaseResponse<Payment>(false, "Cannot cancel the payment", null);
    }
}
//!Algorithm.<Payment>exists(paymentTable, e-> e.busSeats.equals(busSeats)
//!Algorithm.<Payment>exist(Algorithm.<Schedule>find(busTable, e -> e.schedules==departureDate).bus)*/
