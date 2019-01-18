package com.undecode.salesman.models.local;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Visit extends RealmObject
{
    @PrimaryKey
    private int visitID;
    private int customerID;
    private String name;
    private String result;
    private String transDate;

    public Visit()
    {

    }

    public Visit(int visitID, int customerID, String name, String time, String result)
    {
        this.visitID = visitID;
        this.customerID = customerID;
        this.name = name;
        this.transDate = time;
        this.result = result;
    }

    public int getVisitID() {
        return visitID;
    }

    public void setVisitID(int visitID) {
        this.visitID = visitID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
