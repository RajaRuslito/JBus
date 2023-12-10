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

/**
 * Controller class handling payment-related operations.
 * Implements BasicGetController for basic GET operations.
 *
 * @RestController Indicates that this class is a controller handling HTTP requests.
 * @RequestMapping("/payment") Specifies the base URI path for mapping to this controller.
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {

    /**
     * JsonTable for storing and retrieving Payment objects.
     *
     * @JsonAutowired Custom annotation indicating JSON file details for automatic wiring.
     */
    public static @JsonAutowired(value = Payment.class, filepath = "D:\\oop\\JBus\\src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\payment.json") JsonTable<Payment> paymentTable;

    /**
     * Static block to initialize the paymentTable with JSON file details.
     * Throws a RuntimeException if an IOException occurs during initialization.
     */
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    /**
     * Retrieves the JsonTable instance for Payment objects.
     *
     * @return JsonTable instance for Payment objects.
     */
    static {
        try {
            paymentTable = new JsonTable<>(Payment.class, "D:\\oop\\JBus\\src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\payment.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles HTTP POST request for making a booking.
     *
     * @param buyerId       ID of the buyer.
     * @param renterId      ID of the renter.
     * @param busId         ID of the bus.
     * @param busSeats      List of bus seats to be booked.
     * @param departureDate Departure date and time in string format.
     * @return BaseResponse containing the result of the booking operation.
     */
    @RequestMapping(value = "/makeBooking", method = RequestMethod.POST)
    BaseResponse<Payment> makeBooking(
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

        Account buyer = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.id == buyerId);
        Account renter = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.company.id == renterId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, b -> b.id == busId);
        Schedule schedule = Algorithm.<Schedule>find(bus.schedules, s -> {
            return s.departureSchedule.equals(Timestamp.valueOf(departureDate));
        });
        boolean temp = Algorithm.<Payment>exists(paymentTable, seats -> seats.busSeats.equals(busSeats));


        if (buyer != null && schedule != null && !temp) {
            if (Payment.makeBooking(Timestamp.valueOf(departureDate), busSeats, bus)) {
                Payment p = new Payment(buyer, renter.company, busId, busSeats, Timestamp.valueOf(departureDate));
                p.status = Invoice.PaymentStatus.WAITING;
                paymentTable.add(p);
                return new BaseResponse<>(true, "Booking Successful", p);
            } else if (buyer.balance <= bus.price.price) {
                return new BaseResponse<>(false, "Not Enough Balance, Failed to Book", null);
            }
            return new BaseResponse<>(false, "Failed to Book, Couldn't Find Any Matches", null);
        }
        return new BaseResponse<>(false, "Failed to Book", null);
    }

    /**
     * Handles HTTP POST request for accepting a payment.
     *
     * @param id ID of the payment to be accepted.
     * @return BaseResponse containing the result of the accept operation.
     */
    @PostMapping("/{id}/accept")
    BaseResponse<Payment> accept(
            @PathVariable int id
    ) {
        Account account = Algorithm.<Account>find(AccountController.accountTable, e -> e.balance == e.balance);
        Payment payment = Algorithm.<Payment>find(paymentTable, (p) -> {
            return p.id == id;
        });
        Bus bus = Algorithm.<Bus>find(BusController.busTable, e -> e.price.price == e.price.price);
        if (payment != null) {
            payment.status = Invoice.PaymentStatus.SUCCESS;
            account.balance = account.balance - (double) bus.price.price;
            return new BaseResponse<>(true, "Payment Accepted", payment);
        } else if (account.balance <= (double) bus.price.price) {
            return new BaseResponse<>(false, "Accepting Payment Failed, Not Enough Balance", null);
        }
        return new BaseResponse<>(false, "Accepting Payment Failed", null);
    }

    /**
     * Handles HTTP POST request for canceling a payment.
     *
     * @param id ID of the payment to be canceled.
     * @return BaseResponse containing the result of the cancel operation.
     */
    @PostMapping("/{id}/cancel")
    BaseResponse<Payment> cancel(
            @PathVariable int id
    ) {
        Payment payment = Algorithm.<Payment>find(paymentTable, (p) -> {
            return p.id == id;
        });
        if (payment != null) {
            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<Payment>(true, "Payment Cancelled", payment);
        }
        return new BaseResponse<Payment>(false, "Canceling Payment Failed", null);
    }

    /**
     * Handles HTTP GET request for retrieving payments of a specific buyer.
     *
     * @param buyerId ID of the buyer.
     * @return List of Payment objects belonging to the specified buyer.
     */
    @GetMapping("/getMyPayment")
    List<Payment> getMyPayment(@RequestParam int buyerId) {
        return Algorithm.<Payment>collect(getJsonTable(), b -> b.buyerId == buyerId);
    }

}

