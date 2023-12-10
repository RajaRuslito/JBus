package com.RajaYonandroRuslitoJBusAF;

import com.RajaYonandroRuslitoJBusAF.dbjson.Serializable;

/**
 * The Voucher class represents a discount or cash voucher that can be applied to a Price object.
 */
public class Voucher extends Serializable {
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;

    /**
     * Constructs a new Voucher object with the specified parameters.
     *
     * @param id      The ID of the voucher.
     * @param name    The name of the voucher.
     * @param code    The unique code assigned to the voucher.
     * @param type    The type of the voucher.
     * @param minimum The minimum price required for the voucher to be applied.
     * @param cut     The amount or percentage to be deducted from the price.
     */
    public Voucher(int id, String name, int code, Type type, double minimum, double cut) {
        super();
        this.name = name;
        this.minimum = minimum;
        this.cut = cut;
        this.type = type;
        this.code = code;
    }

    /**
     * Applies the voucher to a Price object and returns the discounted or adjusted price.
     *
     * @param price The Price object to which the voucher is applied.
     * @return The discounted or adjusted price.
     */
    public double apply(Price price) {
        this.used = true;
        if (this.type == Type.DISCOUNT) {
            if (cut >= 100) {
                cut = 100;
            }
            return price.price * (int) (100 - cut) / 100;
        } else {
            if (cut > price.price) {
                return 0;
            }
            return price.price - cut;
        }
    }

    /**
     * Checks if the voucher has been used.
     *
     * @return true if the voucher has been used, false otherwise.
     */
    public boolean isUsed() {
        return this.used;
    }

    /**
     * Checks if the voucher can be applied to a given Price object.
     *
     * @param price The Price object to check against the voucher.
     * @return true if the voucher can be applied, false otherwise.
     */
    public boolean canApply(Price price) {
        if (price.price > this.minimum && !this.used) {
            return true;
        } else {
            return false;
        }
    }

    public boolean read(String obj) {
        return false;
    }

    public Object write() {
        return null;
    }
}
