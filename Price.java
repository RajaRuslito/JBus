class Price{
    double rebate;
    double price;
    int discount;
    
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
        this.rebate = discount;
        this.discount = 0;
    }
}