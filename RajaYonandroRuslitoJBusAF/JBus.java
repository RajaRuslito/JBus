package RajaYonandroRuslitoJBusAF;

public class JBus{ 
    public static void main(String[] args){        
        /*Payment testPayment = new Payment(1, 1, 1, "A", 1, "A", "A");
        Invoice testInvoice = new Invoice(2, 2, 2, "B");
        Station testStation = new Station(3, "C", City.DEPOK);*/
        /*Review testReview = new Review(1, "23 August 2023", "Bad Quality");
        Price testPrice = new Price(100000, 20000);
        Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
        Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
        Bus testBus = new Bus(1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
        Account testAccount = new Account(1, "Bob", "bob@gmail.com", "Bob");
        Rating testRating = new Rating();
        
        System.out.println(testReview);   
        System.out.println(testBus);
        System.out.println(testAccount);   
        System.out.println(testPrice);
        System.out.println(testRating);  
        */
        Price[] unfilteredArray = new Price[5];
        for(int i = 0; i < unfilteredArray.length; i++){
            int j = 5000;
            unfilteredArray[i] = new Price((i + 1) * j);
        }
        System.out.println("Price List");
        for(Price price : unfilteredArray){
            System.out.println(price.price);
        }
        System.out.println("Below 12000.0");
        System.out.println(Validate.filter(unfilteredArray, 12000, true));
        System.out.println("Above 10000.0");
        System.out.println(Validate.filter(unfilteredArray, 10000, false));
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
    /*private static Bus createBus(){
        Price price = new Price(750000, 5);
        Bus bus = new Bus(20, "Netlab Bus", Facility.LUNCH, price, 25);
        return bus;
    }*/
} 