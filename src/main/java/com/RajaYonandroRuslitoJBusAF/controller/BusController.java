package com.RajaYonandroRuslitoJBusAF.controller;

import com.RajaYonandroRuslitoJBusAF.*;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonAutowired;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Controller class handling bus-related operations.
 * Implements BasicGetController for basic GET operations.
 *
 * @RestController Indicates that this class is a controller handling HTTP requests.
 * @RequestMapping("/bus") Specifies the base URI path for mapping to this controller.
 */
@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {

    /**
     * JsonTable for storing and retrieving Bus objects.
     *
     * @JsonAutowired Custom annotation indicating JSON file details for automatic wiring.
     */
    public static @JsonAutowired(value = Bus.class, filepath = "src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\bus.json") JsonTable<Bus> busTable;

    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }


    /**
     * Handles HTTP POST request for creating a new bus.
     *
     * @param accountId          ID of the account associated with the bus.
     * @param name               Name of the bus.
     * @param capacity           Capacity of the bus.
     * @param facilities         List of facilities available in the bus.
     * @param busType            Type of the bus.
     * @param price              Price of the bus.
     * @param stationDepartureId ID of the departure station.
     * @param stationArrivalId   ID of the arrival station.
     * @return BaseResponse containing the result of the bus creation operation.
     */
    @PostMapping("/create")
    public BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ) {
        Account account = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.id == accountId && acc.company != null);
        Station departure = Algorithm.<Station>find(StationController.stationTable, station -> station.id == stationDepartureId);
        Station arrival = Algorithm.<Station>find(StationController.stationTable, station -> station.id == stationArrivalId);

        if (account == null) {
            return new BaseResponse<>(false, "Couldn't Find Any Matching Account or Renter", null);
        }

        if (departure == null || arrival == null) {
            return new BaseResponse<>(false, "Departure/Arrival Not Found", null);
        }
        Bus newbus = new Bus(name, facilities, new Price(price), capacity, busType, departure, arrival, accountId);
        busTable.add(newbus);
        return new BaseResponse<>(true, "Bus Successfully Added", newbus);

    }

    /**
     * Handles HTTP POST request for adding a schedule to an existing bus.
     *
     * @param busId ID of the bus to which the schedule is added.
     * @param time  Time of the schedule in string format.
     * @return BaseResponse containing the result of the schedule addition operation.
     */
    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ) {
        Bus newbus = Algorithm.<Bus>find(busTable, e -> e.id == busId);
        Timestamp timestamp = Timestamp.valueOf(time);
        if (newbus != null) {
            try {
                newbus.addSchedule(timestamp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new BaseResponse<>(true, "Schedule Successfully Added", newbus);
        }
        return new BaseResponse<>(false, "Bus Not Found", null);
    }

    /**
     * Handles HTTP GET request for retrieving buses associated with a specific account.
     *
     * @param accountId ID of the account.
     * @return List of Bus objects associated with the specified account.
     */
    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b -> b.accountId == accountId);
    }

    /**
     * Handles HTTP GET request for retrieving all buses.
     *
     * @return BaseResponse containing the list of all buses.
     */
    @GetMapping("/getBuses")
    public BaseResponse<List<Bus>> getBuses() {
        return new BaseResponse<>(true, "Success", getJsonTable());
    }

}
