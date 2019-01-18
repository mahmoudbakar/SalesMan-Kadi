package com.undecode.salesman.models.local;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class SalesOrderDtlsItem extends RealmObject {

    @PrimaryKey
	@SerializedName("itemID")
	private int itemID;

	@SerializedName("name")
	private String name;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("price")
	private double price;

	@SerializedName("unitID")
	private int unitID;

	public void setItemID(int itemID){
		this.itemID = itemID;
	}

	public int getItemID(){
		return itemID;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setUnitID(int unitID){
		this.unitID = unitID;
	}

	public int getUnitID(){
		return unitID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
 	public String toString(){
		return 
			"SalesOrderDtlsItem{" + 
			"itemID = '" + itemID + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",price = '" + price + '\'' + 
			",unitID = '" + unitID + '\'' + 
			"}";
		}
}