package com.RajaYonandroRuslitoJBusAF;

import com.RajaYonandroRuslitoJBusAF.dbjson.Serializable;

import java.sql.Timestamp;

/**
 * Represents an invoice entity with associated details such as time, buyer ID, renter ID, rating, and payment status.
 * Extends Serializable for serialization purposes.
 */
public class Invoice extends Serializable {
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;

    /**
     * Enumeration representing possible bus ratings.
     * Values: NONE, NEUTRAL, GOOD, BAD.
     */
    public enum BusRating {
        NONE,
        NEUTRAL,
        GOOD,
        BAD;
    }

    /**
     * Enumeration representing possible payment statuses.
     * Values: FAILED, WAITING, SUCCESS.
     */
    public enum PaymentStatus {
        FAILED,
        WAITING,
        SUCCESS;
    }

    /**
     * Protected constructor for initializing an Invoice object with the provided buyer and renter IDs.
     *
     * @param buyerId  ID of the buyer associated with the invoice.
     * @param renterId ID of the renter associated with the invoice.
     */
    protected Invoice(int buyerId, int renterId) {
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    /**
     * Constructor to initialize an Invoice object with the provided buyer and renter objects.
     *
     * @param buyer  Buyer associated with the invoice.
     * @param renter Renter associated with the invoice.
     */
    public Invoice(Account buyer, Renter renter) {
        super();
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public String toString() {
        return "\n ID : " + id + "\n Buyer ID : " + buyerId + "\n Renter ID : " + renterId + "\n Time : " + time.getTime() + "\n Rating : " + rating + "\n Status : " + status;
    }
}
