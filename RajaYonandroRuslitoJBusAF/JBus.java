package RajaYonandroRuslitoJBusAF;

public class JBus{ 
    public static void main(String[] args){
        Bus testBus = createBus();
        
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);
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
    private static int getTotalPrice(int price, int numberOfSeat){
        int adminFee = getAdminFee(price * numberOfSeat);
        return price * numberOfSeat + adminFee;
    }
    private static Bus createBus(){
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25);
        return bus;
    }
} 