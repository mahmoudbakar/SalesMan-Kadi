package com.undecode.salesman.models.local;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class OffersGroupsItem{

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

	@SerializedName("Quantity")
	private int quantity;

	@SerializedName("FromDate")
	private String fromDate;

	@SerializedName("ToDate")
	private String toDate;

	@SerializedName("Notes")
	private String notes;

	@SerializedName("GroupID")
	private int groupID;

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

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
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

	public void setGroupID(int groupID){
		this.groupID = groupID;
	}

	public int getGroupID(){
		return groupID;
	}
}