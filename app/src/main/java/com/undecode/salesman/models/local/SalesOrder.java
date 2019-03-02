package com.undecode.salesman.models.local;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class SalesOrder extends RealmObject
{
	@PrimaryKey
	private int id;

	private String customerName;

	@SerializedName("totalValue")
	private double totalValue;

	@Ignore
	@SerializedName("salesOrderDtls")
	private List<SalesOrderDtlsItem> salesOrderDtls;

	@SerializedName("discountValue")
	private double discountValue;

	@SerializedName("notes")
	private String notes;

	@SerializedName("saved")
	private boolean saved;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("customerID")
	private int customerID;

	@SerializedName("transDate")
	private String transDate;

	@SerializedName("longitude")
	private double longitude;

	private RealmList<SalesOrderDtlsItem> salesOrderDtls11;

	public void setTotalValue(double totalValue)
	{
		this.totalValue = totalValue;
	}

	public double getTotalValue()
	{
		return totalValue - discountValue;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setSalesOrderDtls(RealmList<SalesOrderDtlsItem> salesOrderDtls)
	{
		this.salesOrderDtls11 = salesOrderDtls;
	}

	public List<SalesOrderDtlsItem> getSalesOrderDtls()
	{
		return salesOrderDtls11;
	}

	public void setDiscountValue(double discountValue)
	{
		this.discountValue = discountValue;
	}

	public double getDiscountValue()
	{
		return discountValue;
	}


	public List<SalesOrderDtlsItem> getSalesOrderDtlsJson()
	{
		return salesOrderDtls;
	}

	public void setSalesOrderDtlsJson(List<SalesOrderDtlsItem> salesOrderDtlsJson)
    {
		this.salesOrderDtls = salesOrderDtlsJson;
	}

	public void setSalesOrderDtls(List<SalesOrderDtlsItem> salesOrderDtls) {
		this.salesOrderDtls = salesOrderDtls;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public RealmList<SalesOrderDtlsItem> getSalesOrderDtls11() {
		return salesOrderDtls11;
	}

	public void setSalesOrderDtls11(RealmList<SalesOrderDtlsItem> salesOrderDtls11) {
		this.salesOrderDtls11 = salesOrderDtls11;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	@Override
 	public String toString()
	{
		return 
			"SalesOrder{" + 
			"totalValue = '" + totalValue + '\'' + 
			",notes = '" + notes + '\'' +
			",saved = '" + saved + '\'' +
			",latitude = '" + latitude + '\'' +
			",customerID = '" + customerID + '\'' +
			",transDate = '" + transDate + '\'' +
			",id = '" + id + '\'' + 
			",salesOrderDtls = '" + salesOrderDtls + '\'' +
			",discountValue = '" + discountValue + '\'' + 
			",longitude = '" + longitude + '\'' +
			"}";
		}
}