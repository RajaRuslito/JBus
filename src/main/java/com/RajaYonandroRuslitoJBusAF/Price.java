package com.RajaYonandroRuslitoJBusAF;

/**
 * Represents the price of an item, potentially with a rebate.
 */
public class Price {
    public double rebate;
    public double price;
    //public int discount;

    /**
     * Constructs a Price object with the given price and no rebate.
     *
     * @param price The original price before any rebate.
     */
    public Price(double price) {
        this.price = price;
        //this.discount = 0;
        this.rebate = 0;
    }

    /**
     * Constructs a Price object with the given price and rebate.
     *
     * @param price  The original price before any rebate.
     * @param rebate The amount of rebate applied to the price.
     */
    public Price(double price, double rebate) {
        this.price = price;
        this.rebate = rebate;
        //this.discount = 0;
    }
    /*
    public double getDiscountedPrice(){
        double disc;
        if(discount >= 100){
            return 0;
        }
        else{
            disc = price * (int)(100 - discount) / 100;
        }
        return disc;
    }
    public double getRebatedPrice(){
        if(rebate > price){
            return 0;
        }
        else{
            return price - rebate;
        }
    }
    */

    /**
     * Returns a string representation of the Price object.
     *
     * @return A string containing the price and rebate information.
     */
    public String toString() {
        return " Price : " + price + "\n Rebate : " + rebate;
    }
}
