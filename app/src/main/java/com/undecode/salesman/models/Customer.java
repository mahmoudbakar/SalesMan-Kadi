package com.undecode.salesman.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class Customer extends RealmObject
{
	@SerializedName("balance")
	private double balance;

	@PrimaryKey
	@SerializedName("customerID")
	private int customerID;

	@SerializedName("customerNameA")
	private String customerNameA;

	@Ignore
	@SerializedName("lastInvoices")
	private List<LastInvoicesItem> lastInvoices;

	@Ignore
	@SerializedName("lastPayments")
	private List<LastPaymentsItem> lastPayments;

	private RealmList<LastInvoicesItem> lastInvoicesR;

	private RealmList<LastPaymentsItem> lastPaymentsR;

	@SerializedName("customerNameE")
	private String customerNameE;

	public void setBalance(double balance){
		this.balance = balance;
	}

	public double getBalance(){
		return balance;
	}

	public void setCustomerID(int customerID){
		this.customerID = customerID;
	}

	public int getCustomerID(){
		return customerID;
	}

	public void setCustomerNameA(String customerNameA){
		this.customerNameA = customerNameA;
	}

	public String getCustomerNameA(){
		return customerNameA;
	}

	public void setLastInvoices(List<LastInvoicesItem> lastInvoices){
		this.lastInvoices = lastInvoices;
	}

	public List<LastInvoicesItem> getLastInvoices(){
		return lastInvoices;
	}

	public void setLastPayments(List<LastPaymentsItem> lastPayments){
		this.lastPayments = lastPayments;
	}

	public List<LastPaymentsItem> getLastPayments(){
		return lastPayments;
	}

	public void setCustomerNameE(String customerNameE){
		this.customerNameE = customerNameE;
	}

	public String getCustomerNameE(){
		return customerNameE;
	}

	public RealmList<LastInvoicesItem> getLastInvoicesR()
	{
		return lastInvoicesR;
	}

	public void setLastInvoicesR(RealmList<LastInvoicesItem> lastInvoicesR)
	{
		this.lastInvoicesR = lastInvoicesR;
	}

	public RealmList<LastPaymentsItem> getLastPaymentsR()
	{
		return lastPaymentsR;
	}

	public void setLastPaymentsR(RealmList<LastPaymentsItem> lastPaymentsR)
	{
		this.lastPaymentsR = lastPaymentsR;
	}

	@Override
 	public String toString(){
		return 
			"Customer{" + 
			"balance = '" + balance + '\'' + 
			",customerID = '" + customerID + '\'' + 
			",customerNameA = '" + customerNameA + '\'' + 
			",lastInvoices = '" + lastInvoices + '\'' + 
			",lastPayments = '" + lastPayments + '\'' + 
			",customerNameE = '" + customerNameE + '\'' + 
			"}";
		}
}