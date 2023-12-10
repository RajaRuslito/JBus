package com.RajaYonandroRuslitoJBusAF;


/**
 * Represents a rating system with count, total, and average functionalities.
 */
public class Rating {
    private long count;
    private long total;

    /**
     * Constructs a Rating object with initial count and total set to zero.
     */
    public Rating() {
        this.count = 0;
        this.total = 0;
    }

    /**
     * Inserts a new rating into the system, updating the count and total.
     *
     * @param rating The rating to be inserted.
     */
    public void insert(int rating) {
        this.total += rating;
        this.count += 1;
    }

    /**
     * Retrieves the total sum of ratings.
     *
     * @return The total sum of ratings.
     */
    public long getTotal() {
        return this.total;
    }

    /**
     * Retrieves the count of ratings.
     *
     * @return The count of ratings.
     */
    public long getCount() {
        return this.count;
    }

    /**
     * Calculates the average rating based on the count and total.
     *
     * @return The average rating, or 0 if there are no ratings.
     */
    public double getAverage() {
        if (this.count == 0) {
            return 0;
        } else {
            return (this.total / this.count);
        }
    }

    /**
     * Returns a string representation of the Rating object.
     *
     * @return A string containing the count and total information.
     */
    public String toString() {
        return " Count : " + count + "\n Total : " + total;
    }
}