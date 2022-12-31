package org.fhtw.entity;

public class Message {
    //2023;Mohamad;Create;11-04-2015 22:01:33:023;11-04-2015 09:07:20:02
    //2023;Mohamad;Create;11-04-2015 22:01:33:023;11-04-2015 09:07:20:02
    //1;Mohamad;Create;11-04-2015 22:01:33:023;11-04-2015 09:07:20:02
    //0000;Mohamad;Create;11-04-2015 22:01:33:023;11-04-2015 09:07:20:02
    //"0001";Mohamad;Create;11-04-2015 22:01:33:023;11-04-2015 09:07:20:02
    //2023;Mohamad;Create;11-04-2015 22:01:33:023;11-04-2015 09:07:20:02;ABC
    //2023;Mohamad;Create;11-04-2015 22:01:33:023;
    //2023;Mohamad;Create;11-04-2015 22:01:33:023;11-04-2015
    //12331;ahmad;   ;11-04-2015 22:01:33:023;11-04-2015 05:07:20:02
    //12331;   ;update;11-04-2015 22:01:33:023;11-04-2015 05:07:20:02
    //dasdas;ahmad;update;11-04-2015 22:01:33:023;11-04-2015 05:07:20:02
    //    ;ahmad;update;11-04-2015 22:01:33:023;11-04-2015 05:07:20:02
    //          "EmployeeID", "Name", "Task", "Date-from", "Date-to"
    private final String employeeId;
    private final String employeeName;
    private final String task;
    private final String dateFrom;
    private final String dateTo;

    public Message(String employeeId, String employeeName, String task, String dateFrom, String dateTo) {
        if (employeeId == null || employeeName == null || task == null || dateFrom == null || dateTo == null)
            throw new NullPointerException("Non of the attributes can be null. Message is not valid");

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.task = task;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getTask() {
        return task;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String[] getAllData() {
        return new String[]{String.valueOf(getEmployeeId()), getEmployeeName(), getTask(), getDateFrom(), getDateTo()};
    }
}
