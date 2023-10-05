package RajaYonandroRuslitoJBusAF;
import java.util.Calendar;
import java.sql.Timestamp;
import java.text.*;

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

        /*Price[] unfilteredArray = new Price[5];
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
        
        Bus testBus = createBus();
        
        Payment testPayment = new Payment(1, 1, 1, testBus.id, "S1");
        System.out.println(testPayment.getDepartureInfo());
        System.out.println(testPayment.getTime());
        
        Calendar schedule1 = Calendar.getInstance();
        testBus.addSchedule(schedule1);
        Calendar schedule2 = Calendar.getInstance();
        schedule2.add(Calendar.DAY_OF_MONTH, 3);
        testBus.addSchedule(schedule2);
        
        for(Schedule s: testBus.schedules){
            testBus.printSchedule(s);
        }*/
        /*Bus b = createBus();
        Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
        Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");
        b.addSchedule(schedule1, 12);
        b.addSchedule(schedule2, 12);
        b.schedules.forEach(Schedule::printSchedule);
        // Invalid date
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("Make booking at July 19, 2023 15:00:00 Seat AF12");
        System.out.println(Payment.makeBooking(t1, "AF12", b));
        // Valid date, invalid seat
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat AF20");
        System.out.println(Payment.makeBooking(t2, "AF20", b));
        // Valid date, valid seat
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat AF07");
        System.out.println(Payment.makeBooking(t2, "AF07", b));
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat AF01");
        System.out.println(Payment.makeBooking(t3, "AF01", b));
        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat AF01 again");
        System.out.println(Payment.makeBooking(t3, "AF01", b));
        // Check if the data changed
        System.out.println("\nUpdated Schedule\n");
        b.schedules.forEach(Schedule::printSchedule);
        */
        System.out.println("Hello from IntelliJ!");
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
        Bus bus = new Bus(20, "Netlab Bus", Facility.LUNCH, new Price(1000), 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
} 