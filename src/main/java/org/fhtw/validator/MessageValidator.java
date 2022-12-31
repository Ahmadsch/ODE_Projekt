package org.fhtw.validator;


import org.fhtw.entity.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class MessageValidator {
    final private String EMPLOYEES_FILENAME = "ALL_EMP";

    public void validateMessage(Message message) {
        if (message == null)
            throw new NullPointerException("Message Object is Null");

        if (!isNumber(message.getEmployeeId()))
            throw new IllegalArgumentException("employee ID has to contain Only Integers");

        if (isEmpty(message.getEmployeeName()))
            throw new IllegalArgumentException("employee name can not be empty");

        if (isEmpty(message.getTask()))
            throw new IllegalArgumentException("Task description can not be empty");

        try {
           isValidDate(message.getDateFrom());
           isValidDate(message.getDateTo());
        } catch (ParseException pe) {
            throw new IllegalArgumentException("The date has to be a Time stamp: dd-MM-yyyy HH:mm:ss:ms",pe);
        }


    }

    private void isValidDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:ms");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
    }

    private boolean isNumber(String number) {
        return Pattern.matches("[0-9]+", number);
    }

    private boolean isEmpty(String line) {
        return line.isEmpty() || line.isBlank();
    }

}
