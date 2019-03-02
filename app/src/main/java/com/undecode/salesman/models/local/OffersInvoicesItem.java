package com.undecode.salesman.models.local;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class OffersInvoicesItem{

	@SerializedName("OfferID")
	private int offerID;

	@SerializedName("OfferNameE")
	private String offerNameE;

	@SerializedName("DiscPercent")
	private int discPercent;

	@SerializedName("DiscValue")
	private int discValue;

	@SerializedName("OfferNameA")
	private String offerNameA;

	@SerializedName("FromDate")
	private String fromDate;

	@SerializedName("ToDate")
	private String toDate;

	@SerializedName("Notes")
	private String notes;

	@SerializedName("TotalInvoice")
	private int totalInvoice;

	public void setOfferID(int offerID){
		this.offerID = offerID;
	}

	public int getOfferID(){
		return offerID;
	}

	public void setOfferNameE(String offerNameE){
		this.offerNameE = offerNameE;
	}

	public String getOfferNameE(){
		return offerNameE;
	}

	public void setDiscPercent(int discPercent){
		this.discPercent = discPercent;
	}

	public int getDiscPercent(){
		return discPercent;
	}

	public void setDiscValue(int discValue){
		this.discValue = discValue;
	}

	public int getDiscValue(){
		return discValue;
	}

	public void setOfferNameA(String offerNameA){
		this.offerNameA = offerNameA;
	}

	public String getOfferNameA(){
		return offerNameA;
	}

	public void setFromDate(String fromDate){
		this.fromDate = fromDate;
	}

	public String getFromDate(){
		return fromDate;
	}

	public void setToDate(String toDate){
		this.toDate = toDate;
	}

	public String getToDate(){
		return toDate;
	}

	public void setNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setTotalInvoice(int totalInvoice){
		this.totalInvoice = totalInvoice;
	}

	public int getTotalInvoice(){
		return totalInvoice;
	}
}