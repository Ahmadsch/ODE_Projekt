package org.fhtw.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A data transfer object class for holding the request information for getting all associates for a manager by a given range of dates.
 */
public class GetAllAssociatesByManagerIdRequest {
    private final String token;
    private final String startDate;
    private final String endDate;

    /**
     * Creates a new {@code GetAllAssociatesByManagerIdRequest} instance.
     *
     * @param token     the manager's token
     * @param startDate the start date for the query (in the format "yyyy-MM-dd")
     * @param endDate   the end date for the query (in the format "yyyy-MM-dd")
     * @throws ParseException           if the startDate or endDate cannot be parsed into a {@link Date} object
     * @throws IllegalArgumentException if the startDate is not before the endDate
     */
    public GetAllAssociatesByManagerIdRequest(String token, String startDate, String endDate) throws ParseException {
        // Set the date format you expect the input to be in
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Parse the start and end date strings into Date objects
        Date start = dateFormat.parse(startDate);
        Date end = dateFormat.parse(endDate);

        // Check if the start date is before the end date
        if (start.before(end)) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.token = token;
        } else {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
    }

    /**
     * Gets the start date for the query.
     *
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date for the query.
     *
     * @return the end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Gets the manager's token.
     *
     * @return the manager's token
     */
    public String getToken() {
        return token;
    }
}