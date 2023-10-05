package RajaYonandroRuslitoJBusAF;

public class Price{
    public double rebate;
    public double price;
    //public int discount;
    
    public Price (double price) {
        this.price = price;
        //this.discount = 0;
        this.rebate = 0;
    }
    /*
    public Price (double price, int discount) {
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }
    */
    public Price (double price, double rebate){
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
    public String toString(){
        return " Price : " + price + "\n Rebate : " + rebate;
    }
}
