package com.RajaYonandroRuslitoJBusAF;

import com.RajaYonandroRuslitoJBusAF.dbjson.Serializable;


/**
 * Represents a review associated with a particular entity.
 */
public class Review extends Serializable {
    public String date;
    public String desc;

    /**
     * Constructs a Review object with the given ID, date, and description.
     *
     * @param id   The unique identifier for the review.
     * @param date The date when the review was created.
     * @param desc The description or content of the review.
     */
    public Review(int id, String date, String desc) {
        super();
        this.date = date;
        this.desc = desc;
    }

    /**
     * Returns a string representation of the Review object.
     *
     * @return A string containing the ID, date, and description of the review.
     */
    public String toString() {
        return " ID : " + id + "\n Date : " + date + "\n Description : " + desc;
    }
}
