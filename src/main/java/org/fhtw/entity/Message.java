package org.fhtw.entity;

public class Message {
    //12331;ahmad;update;11-04-2015 22:01:33:023;11-04-2015 05:07:20:02
    //          "EmployeeID", "Name", "Task", "Date-from", "Date-to"
    private int EmployeeId;
    private String EmployeeName;
    private String Task;
    private String DateFrom;
    private String DateTo;

    public Message(int employeeId, String employeeName, String task, String dateFrom, String dateTo) {
        this.EmployeeId = employeeId;
        this.EmployeeName = employeeName;
        this.Task = task;
        this.DateFrom = dateFrom;
        this.DateTo = dateTo;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }
    public String[]  getAllData(){
        return new String[]{String.valueOf(getEmployeeId()),getEmployeeName(),getTask(),getDateFrom(),getDateTo() };
    }
}
