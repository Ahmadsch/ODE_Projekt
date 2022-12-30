package org.fhtw.entity;

public class Message {
    //12331;ahmad;update;11-04-2015 22:01:33:023;11-04-2015 05:07:20:02
    //          "EmployeeID", "Name", "Task", "Date-from", "Date-to"
    private int employeeId;
    private String employeeName;
    private String task;
    private String dateFrom;
    private String dateTo;

    public Message(int employeeId, String employeeName, String task, String dateFrom, String dateTo) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.task = task;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
    public String[]  getAllData(){
        return new String[]{String.valueOf(getEmployeeId()),getEmployeeName(),getTask(),getDateFrom(),getDateTo() };
    }
}
