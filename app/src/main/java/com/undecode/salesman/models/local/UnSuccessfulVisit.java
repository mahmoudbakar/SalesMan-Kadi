package com.undecode.salesman.models.local;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class UnSuccessfulVisit extends RealmObject
{

	@SerializedName("notes")
	private String notes;

	@SerializedName("reasonID")
	private int reasonID;

	private String reason;

	@SerializedName("saved")
	private boolean saved;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("customerID")
	private int customerID;

	@SerializedName("transDate")
	private String transDate;

	@PrimaryKey
	@SerializedName("id")
	private int id;

	@SerializedName("longitude")
	private double longitude;

	public void setNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setReasonID(int reasonID){
		this.reasonID = reasonID;
	}

	public int getReasonID(){
		return reasonID;
	}

	public void setSaved(boolean saved){
		this.saved = saved;
	}

	public boolean isSaved(){
		return saved;
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

	public void setTransDate(String transDate){
		this.transDate = transDate;
	}

	public String getTransDate(){
		return transDate;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	@Override
 	public String toString()
	{
		return 
			"UnSuccessfulVisit{" + 
			"notes = '" + notes + '\'' + 
			",reasonID = '" + reasonID + '\'' + 
			",saved = '" + saved + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",customerID = '" + customerID + '\'' + 
			",transDate = '" + transDate + '\'' + 
			",id = '" + id + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}