package com.RajaYonandroRuslitoJBusAF;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;
import java.text.*;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JBus {

    public static void main(String[] args) throws InterruptedException{

        SpringApplication.run(JBus.class, args);

/*

        try {
            //String filepath = "D:\\oop\\JBus\\data\\buses_CS.json";
            String filepath = "D:\\oop\\JBus\\data\\accountDatabase.json";

            */
/*JsonTable<Bus> busList = new JsonTable<>(Bus.class, filepath);
            List<Bus> filteredBus = filterByDeparture(busList, City.JAKARTA, 0,3);
            filteredBus.forEach(bus -> System.out.println(bus.toString()));*//*


            JsonTable<Account> accountList = new JsonTable<>(Account.class, filepath);
            accountList.add(new Account("Lito", "Lito@gmail.com", "Lito123"));
            //System.out.println(accountList.toString());
            accountList.writeJson();

            accountList = new JsonTable<>(Account.class, filepath);
            accountList.forEach(account -> System.out.println(account.toString()));
        }
        catch (Throwable t) {
            t.printStackTrace();
        }

        Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);
        try {
            for (int i = 0; i < 10; i++) {
                BookingThread thread = new BookingThread("Thread" + i, bus, Timestamp.valueOf("2023-07-27 19:00:00"));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bus.schedules.forEach(Schedule::printSchedule);
*/

    }
    /*public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }


    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize) {
        List<Bus> list = new ArrayList<>();
        for (Bus bus : buses) {
            if (bus.departure.city == departure) {
                list.add(bus);
            }
        }
        List<Bus> pagingList = Algorithm.paginate(list, page, pageSize, (e)-> {
            return true;
        });
        return pagingList;
    }

    public static List<Bus> filterByPrice (List<Bus> buses, int min, int max){
        List<Bus> sortedPrice = Algorithm.<Bus>collect(buses, (e) -> {
            return e.price.price >= min && e.price.price <= max;
        });
        return sortedPrice;
    }

    public static Bus filterBusId(List<Bus> buses, int id){
        Bus busesID = Algorithm.<Bus>find(buses, (e) -> {
            return e.id == e.id;
        });
        return busesID;
    }

    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page, int pageSize){
        List<Bus> list = new ArrayList<>();
        for(Bus bus : buses){
            if(bus.arrival.city == arrival && bus.departure.city == departure){
                list.add(bus);
            }
        }
        List<Bus> pagingList = Algorithm.<Bus>paginate(list, page, pageSize, (e) -> {
            return true;
        });
        return pagingList;
    }*/

}