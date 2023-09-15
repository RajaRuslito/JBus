package RajaYonandroRuslitoJBusAF;


class Price{
    public double rebate;
    public double price;
    public int discount;
    
    public Price (double price) {
        this.price = price;
        this.discount = 0;
        this.rebate = 0;
    }
    public Price (double price, int discount) {
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }
    public Price (double price, double rebate){
        this.price = price;
        this.rebate = rebate;
        this.discount = 0;
    }
    private double getDiscountedPrice(){
        double disc;
        if(discount >= 100){
            discount = 100;
            return 0.0f;
        }
        else{
            disc = price - (price * discount);
        }
        return disc;
    }
    private double getRebatedPrice(){
        if(rebate > price){
            if(rebate < 0){
                return 0.0f;
            }
            else{
                return price - rebate; 
            }
        }
        else{
            return price - rebate;
        }
    }
}
