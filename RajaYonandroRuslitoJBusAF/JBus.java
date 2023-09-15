package RajaYonandroRuslitoJBusAF;

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

class Rating{
    long count;
    long total;
    
    public Rating(){
        this.count = 0;
        this.total = 0;
    }
}

public class Jbus{ 
    public static void main(String [] args){
        
    }
    public static int getBusId(){
        return 0;
    }
    public static String getBusName(){
        return "Bus";
    }
    public static boolean isDiscount(){
        return true;
    }
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        
        if(beforeDiscount < afterDiscount){
            return 0.0f;
        }
        else if(afterDiscount == 0){
            if (beforeDiscount == 0){
                return 0.0f;
            }
            else{
                return 100.0f;
            }
        }
        else{
            return 10 * ((beforeDiscount - afterDiscount)*10/beforeDiscount);
        }
        
    }
    public static float getDiscountedPrice(int price, float discountPercentage){
        float afterDisc;
        if(discountPercentage > 100.0f){
            afterDisc = 0.0f;
        }
        else{
        afterDisc = (price - (price * (discountPercentage/100)));
        }
        return afterDisc;
    }
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        int discPrice;
        return discPrice = (int)((100/(100.f-discountPercentage) * (float)(discountedPrice)));
    }
    public static float getAdminFeePercentage(){
        return 0.05f;
    }
    public static int getAdminFee(int price){
        float adminCut = getAdminFeePercentage();
        int adminFee;
        adminFee = (int)(price * adminCut);
        return adminFee;
    }
    public static int getTotalPrice(int price, int numberOfSeat){
        int adminFee = getAdminFee(price * numberOfSeat);
        return price * numberOfSeat + adminFee;
    }
} 