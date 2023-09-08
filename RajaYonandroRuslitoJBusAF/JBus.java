package RajaYonandroRuslitoJBusAF;

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
        float percentage;
        if(beforeDiscount < afterDiscount){
            percentage = 0.0f;
        }
        else if(afterDiscount == 0){
            percentage = 100.0f;
        }
        else{
            percentage = (beforeDiscount - afterDiscount)/beforeDiscount;
        }
        return percentage;
    }
    public static float getDiscountedPrice(int price, float discountPercentage){
        float afterDisc;
        if(discountPercentage > 100.0f){
            afterDisc = 0.0f;
        }
        else if(price == 0){
            afterDisc = 0.0f;
        }
        else{
            afterDisc = price - (price * discountPercentage);
        }
        return afterDisc;
    }
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        int discPer = (int)discountPercentage;
        return discountedPrice - ((100 - discPer) * 100);
    }
    public static float getAdminFeePercentage(){
        return 0.05f;
    }
    public static int getAdminFee(int price){
        return price;
    }
    public static int getTotalPrice(int price, int numberOfSeat){
        return price * numberOfSeat;
    }
} 