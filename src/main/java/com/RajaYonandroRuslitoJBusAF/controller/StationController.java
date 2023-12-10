package com.RajaYonandroRuslitoJBusAF.controller;


import com.RajaYonandroRuslitoJBusAF.*;
import com.RajaYonandroRuslitoJBusAF.City;
import com.RajaYonandroRuslitoJBusAF.Station;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonAutowired;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.List.*;

/**
 * Controller class handling station-related operations.
 * Implements BasicGetController for basic GET operations.
 *
 * @RestController Indicates that this class is a controller handling HTTP requests.
 * @RequestMapping("/station") Specifies the base URI path for mapping to this controller.
 */
@RestController
@RequestMapping("/station")
public class StationController implements BasicGetController<Station> {

    private List<Station> stationList = new ArrayList<>();
    private int selectedDeptStationID;
    private int selectedArrStationID;

    /**
     * JsonTable for storing and retrieving Station objects.
     *
     * @JsonAutowired Custom annotation indicating JSON file details for automatic wiring.
     */
    public static @JsonAutowired(value = Station.class, filepath = "src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\station.json") JsonTable<Station> stationTable;

    /**
     * Static block to initialize the stationTable with JSON file details.
     * Throws a RuntimeException if an IOException occurs during initialization.
     */
    @Override
    public JsonTable<Station> getJsonTable() {
        return stationTable;
    }

    static {
        try {
            JsonTable<Station> station = new JsonTable<>(Station.class, "src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\station.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles HTTP POST request for creating a new station.
     *
     * @param stationName Name of the station.
     * @param city        City of the station.
     * @param address     Address of the station.
     * @return BaseResponse containing the result of the station creation operation.
     */
    //Add new Station
    @PostMapping("/create")
    public BaseResponse<Station> createStation(
            @RequestParam String stationName,
            @RequestParam String city,
            @RequestParam String address
    ) {
        try {
            // Validate parameters
            if (stationName.isBlank() || city == null || address.isBlank()) {
                return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
                //City cityNew = City.valueOf(city);
            } else {

                // Validate city as a valid enum value
                City cityEnum = City.valueOf(city.toUpperCase());

                // Create a new station using the provided details
                Station newStation = new Station(stationName, cityEnum, address);

                // Add the new station to the stationTable
                stationTable.add(newStation);

                //Success response message
                return new BaseResponse<>(true, "Station Added Successfully", newStation);
            }
        } catch (IllegalArgumentException e) {
            // Handle invalid enum value
            return new BaseResponse<>(false, "Invalid City Value", null);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new BaseResponse<>(false, "An Error Occurred While Adding the Station", null);
        }
    }

    /**
     * Handles HTTP GET request for retrieving all stations.
     *
     * @return List of all Station objects.
     */
    @GetMapping("/getAll")
    public List<Station> getAllStation() {
        return getJsonTable();
    }

}


