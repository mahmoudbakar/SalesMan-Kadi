package com.undecode.salesman.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import com.undecode.salesman.utils.MyPreferance;

import io.realm.RealmObject;
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

	public Item() {
	}

	public Item(int supplierID, double price2, String img, double price1, int unitID, String itemNameA, int itemID, String itemNameE, int groupID, int quantity, Unit unit)
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
		return price1 * quantity;
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