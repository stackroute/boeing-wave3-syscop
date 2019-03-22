package com.stackroute.monitorservice.model;

public class Range {

    private String tableName;
    private String fromDate;
    private String toDate;

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Range(String tableName, String toDate, String fromDate) {
        this.tableName = tableName;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }


    public Range() {
    }
}
