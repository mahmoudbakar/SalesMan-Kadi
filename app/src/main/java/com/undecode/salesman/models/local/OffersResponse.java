package com.undecode.salesman.models.local;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class OffersResponse{

	@SerializedName("offersGroups")
	private List<OffersGroupsItem> offersGroups;

	@SerializedName("offersInvoices")
	private List<OffersInvoicesItem> offersInvoices;

	@SerializedName("offersItems")
	private List<OffersItemsItem> offersItems;

	public void setOffersGroups(List<OffersGroupsItem> offersGroups){
		this.offersGroups = offersGroups;
	}

	public List<OffersGroupsItem> getOffersGroups(){
		return offersGroups;
	}

	public void setOffersInvoices(List<OffersInvoicesItem> offersInvoices){
		this.offersInvoices = offersInvoices;
	}

	public List<OffersInvoicesItem> getOffersInvoices(){
		return offersInvoices;
	}

	public void setOffersItems(List<OffersItemsItem> offersItems){
		this.offersItems = offersItems;
	}

	public List<OffersItemsItem> getOffersItems(){
		return offersItems;
	}
}