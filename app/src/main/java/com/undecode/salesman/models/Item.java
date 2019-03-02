package com.undecode.salesman.models;

import android.content.Context;

import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.undecode.salesman.models.local.OffersItemsItem;
import com.undecode.salesman.models.local.OffersResponse;
import com.undecode.salesman.utils.MyPreferance;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class Item extends RealmObject {

	@SerializedName("SupplierID")
	private int supplierID;

	@SerializedName("Price2")
	private double price2;

	@SerializedName("Img")
	private String img;

	@SerializedName("Price1")
	private double price1;

	@SerializedName("UnitID")
	private int unitID;

	@SerializedName("ItemNameA")
	private String itemNameA;

	@PrimaryKey
	@SerializedName("ItemID")
	private int itemID;

	@SerializedName("ItemNameE")
	private String itemNameE;

	@SerializedName("GroupID")
	private int groupID;

	private int quantity;

	private Unit unit;

	@Ignore
	private MyPreferance preferance;
	@Ignore
	private Gson gson;
	@Ignore
	private OffersResponse offers;

	public Item()
	{
	}

	public Item(Context context)
	{
		preferance = new MyPreferance(context);
		gson = new Gson();
		offers = gson.fromJson(preferance.getOffers(), OffersResponse.class);
	}

	public Item(int supplierID, double price2, String img, double price1, int unitID, String itemNameA, int itemID, String itemNameE, int groupID, int quantity, Unit unit, Context context)
	{
		this.supplierID = supplierID;
		this.price2 = price2;
		this.img = img;
		this.price1 = price1;
		this.unitID = unitID;
		this.itemNameA = itemNameA;
		this.itemID = itemID;
		this.itemNameE = itemNameE;
		this.groupID = groupID;
		this.quantity = quantity;
		this.unit = unit;
		preferance = new MyPreferance(context);
		gson = new Gson();
		offers = gson.fromJson(preferance.getOffers(), OffersResponse.class);
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}


	public Double getTotal()
	{
		double quantityDiscount = 0;
		for (OffersItemsItem offersItemsItem : offers.getOffersItems())
		{
			if (itemID == offersItemsItem.getItemID())
			{
				if (offersItemsItem.getDiscPercent() > 0 && quantity >= offersItemsItem.getQuantity())
				{
					price1 = price1 - ((price1 / 100) * offersItemsItem.getDiscPercent());
				}else if (offersItemsItem.getDiscValue() > 0 && quantity >= offersItemsItem.getQuantity())
				{
					price1 -= offersItemsItem.getDiscValue();
				}else if (offersItemsItem.getDiscQuantity() > 0 && quantity >= offersItemsItem.getQuantity())
				{
					price1 -= offersItemsItem.getDiscQuantity();
				}
			}
		}
		return (price1 * quantity) - quantityDiscount;
	}

	public Unit getUnit()
    {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    public void setSupplierID(int supplierID){
		this.supplierID = supplierID;
	}

	public int getSupplierID(){
		return supplierID;
	}

	public void setPrice2(double price2){
		this.price2 = price2;
	}

	public double getPrice2(){
		return price2;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setPrice1(double price1){
		this.price1 = price1;
	}

	public double getPrice1(){
		return price1;
	}

	public void setUnitID(int unitID){
		this.unitID = unitID;
	}

	public int getUnitID(){
		return unitID;
	}

	public void setItemNameA(String itemNameA){
		this.itemNameA = itemNameA;
	}

	public String getItemNameA(){
		return itemNameA;
	}

	public void setItemID(int itemID){
		this.itemID = itemID;
	}

	public int getItemID(){
		return itemID;
	}

	public void setItemNameE(String itemNameE){
		this.itemNameE = itemNameE;
	}

	public String getItemNameE(){
		return itemNameE;
	}

	public void setGroupID(int groupID){
		this.groupID = groupID;
	}

	public int getGroupID(){
		return groupID;
	}

    public String getItemName()
    {
        if (MyPreferance.getInstance().getLang().equals("ar"))
        {
            return getItemNameA();
        }else
        {
            return getItemNameE();
        }
    }

	@Override
 	public String toString(){
		return 
			"Item{" + 
			"supplierID = '" + supplierID + '\'' + 
			",price2 = '" + price2 + '\'' + 
			",img = '" + img + '\'' + 
			",price1 = '" + price1 + '\'' + 
			",unitID = '" + unitID + '\'' + 
			",itemNameA = '" + itemNameA + '\'' + 
			",itemID = '" + itemID + '\'' + 
			",itemNameE = '" + itemNameE + '\'' + 
			",groupID = '" + groupID + '\'' + 
			"}";
		}
}