package com.RajaYonandroRuslitoJBusAF.controller;

import com.RajaYonandroRuslitoJBusAF.*;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonAutowired;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {

    public static @JsonAutowired(value = Payment.class, filepath = "src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\payment.json") JsonTable<Payment> paymentTable;
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    //@PostMapping("/makeBooking")
    @RequestMapping(value="/makeBooking", method = RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeat,
            @RequestParam String departureDate
    ){
        Account buyer = Algorithm.<Account>find(AccountController.accountTable, e -> e.id == buyerId);
        Account renter = Algorithm.<Account>find(AccountController.accountTable, e -> e.company.id == renterId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, e -> (e.id == busId) && (e.accountId == renterId));

        if((buyer != null) && (bus != null)){
            if(Payment.makeBooking(Timestamp.valueOf(departureDate), busSeat, bus)){
                Payment payment = new Payment(buyer, renter.company, busId, busSeat, Timestamp.valueOf(departureDate));
                payment.status = Invoice.PaymentStatus.WAITING;
                paymentTable.add(payment);
                return new BaseResponse<>(true, "Berhasil Booking", payment);
            }
            return new BaseResponse<>(false, "Gagal Booking", null);
        }
        return new BaseResponse<>(false, "Gagal Booking", null);
    }


    @RequestMapping(value="/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id){
        Payment payment = Algorithm.<Payment>find(paymentTable, e -> e.id == id);
        if(payment != null){
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Berhasil Bayar", payment);
        }
        return new BaseResponse<>(false, "Gagal Bayar", null);
    }
    @RequestMapping(value="/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id){
        Payment payment = Algorithm.<Payment>find(paymentTable, e -> e.id == id);
        if(payment != null){
            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Berhasil Cancel Bayar", payment);
        }
        return new BaseResponse<>(false, "Gagal Cancel Bayar", null);
    }

}