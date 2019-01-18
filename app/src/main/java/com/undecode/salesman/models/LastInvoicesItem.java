package com.undecode.salesman.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class LastInvoicesItem extends RealmObject {

	@PrimaryKey
	@SerializedName("invoiceID")
	private int invoiceID;

	@SerializedName("invoiceValue")
	private int invoiceValue;

	@SerializedName("invoiceDate")
	private String invoiceDate;

	public void setInvoiceID(int invoiceID){
		this.invoiceID = invoiceID;
	}

	public int getInvoiceID(){
		return invoiceID;
	}

	public void setInvoiceValue(int invoiceValue){
		this.invoiceValue = invoiceValue;
	}

	public int getInvoiceValue(){
		return invoiceValue;
	}

	public void setInvoiceDate(String invoiceDate){
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceDate(){
		return invoiceDate;
	}

	@Override
 	public String toString(){
		return 
			"LastInvoicesItem{" + 
			"invoiceID = '" + invoiceID + '\'' + 
			",invoiceValue = '" + invoiceValue + '\'' + 
			",invoiceDate = '" + invoiceDate + '\'' + 
			"}";
		}
}