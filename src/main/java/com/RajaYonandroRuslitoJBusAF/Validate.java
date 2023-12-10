package com.RajaYonandroRuslitoJBusAF;

import java.util.ArrayList;

/**
 * The Validate class provides methods for filtering Price objects based on certain criteria.
 */
public class Validate {

    /**
     * Filters an array of Price objects based on a specified value and condition.
     *
     * @param list  The array of Price objects to be filtered.
     * @param value The value used for comparison.
     * @param less  A boolean flag indicating whether to filter prices less than the specified value (true) or greater than (false).
     * @return An ArrayList containing prices that meet the specified condition.
     */
    public static ArrayList filter(Price[] list, int value, boolean less) {
        ArrayList<Double> array = new ArrayList<>();
        if (less == true) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].price < value) {
                    array.add(list[i].price);
                }
            }
        } else if (less == false) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].price > value) {
                    array.add(list[i].price);
                }
            }
        }
        return array;
    }
}
