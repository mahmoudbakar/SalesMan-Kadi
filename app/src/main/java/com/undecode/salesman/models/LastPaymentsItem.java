package com.undecode.salesman.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class LastPaymentsItem extends RealmObject {

	@SerializedName("paymentValue")
	private double paymentValue;

	@PrimaryKey
	@SerializedName("paymentID")
	private int paymentID;

	@SerializedName("paymentDate")
	private String paymentDate;

	public void setPaymentValue(double paymentValue){
		this.paymentValue = paymentValue;
	}

	public double getPaymentValue(){
		return paymentValue;
	}

	public void setPaymentID(int paymentID){
		this.paymentID = paymentID;
	}

	public int getPaymentID(){
		return paymentID;
	}

	public void setPaymentDate(String paymentDate){
		this.paymentDate = paymentDate;
	}

	public String getPaymentDate(){
		return paymentDate;
	}

	@Override
 	public String toString(){
		return 
			"LastPaymentsItem{" + 
			"paymentValue = '" + paymentValue + '\'' + 
			",paymentID = '" + paymentID + '\'' + 
			",paymentDate = '" + paymentDate + '\'' + 
			"}";
		}
}