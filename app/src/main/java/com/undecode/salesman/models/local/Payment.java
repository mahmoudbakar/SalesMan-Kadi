package com.undecode.salesman.models.local;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class Payment extends RealmObject
{

	@PrimaryKey
	@SerializedName("id")
	private int id;

	@SerializedName("paymentValue")
	private double paymentValue;

	@SerializedName("notes")
	private String notes;

	@SerializedName("saved")
	private boolean saved;

	@SerializedName("paymentID")
	private int paymentID;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("customerID")
	private int customerID;

	@SerializedName("recieptNO")
	private String recieptNO;

	@SerializedName("paymentDate")
	private String paymentDate;

	@SerializedName("paymentType")
	private int paymentType;

	@SerializedName("longitude")
	private double longitude;

	public void setPaymentValue(double paymentValue){
		this.paymentValue = paymentValue;
	}

	public double getPaymentValue(){
		return paymentValue;
	}

	public void setNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setSaved(boolean saved){
		this.saved = saved;
	}

	public boolean isSaved(){
		return saved;
	}

	public void setPaymentID(int paymentID){
		this.paymentID = paymentID;
	}

	public int getPaymentID(){
		return paymentID;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setCustomerID(int customerID){
		this.customerID = customerID;
	}

	public int getCustomerID(){
		return customerID;
	}

	public void setRecieptNO(String recieptNO){
		this.recieptNO = recieptNO;
	}

	public String getRecieptNO(){
		return recieptNO;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPaymentDate(String paymentDate){
		this.paymentDate = paymentDate;
	}

	public String getPaymentDate(){
		return paymentDate;
	}

	public void setPaymentType(int paymentType){
		this.paymentType = paymentType;
	}

	public int getPaymentType(){
		return paymentType;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"Payment{" + 
			"paymentValue = '" + paymentValue + '\'' + 
			",notes = '" + notes + '\'' + 
			",saved = '" + saved + '\'' + 
			",paymentID = '" + paymentID + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",customerID = '" + customerID + '\'' + 
			",recieptNO = '" + recieptNO + '\'' + 
			",id = '" + id + '\'' + 
			",paymentDate = '" + paymentDate + '\'' + 
			",paymentType = '" + paymentType + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}