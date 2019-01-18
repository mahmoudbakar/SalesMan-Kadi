package com.undecode.salesman.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class Suppliers extends RealmObject {

	@PrimaryKey
	@SerializedName("SupplierID")
	private int supplierID;

	@SerializedName("SupplierNameE")
	private String supplierNameE;

	@SerializedName("SupplierNameA")
	private String supplierNameA;

	public void setSupplierID(int supplierID){
		this.supplierID = supplierID;
	}

	public int getSupplierID(){
		return supplierID;
	}

	public void setSupplierNameE(String supplierNameE){
		this.supplierNameE = supplierNameE;
	}

	public String getSupplierNameE(){
		return supplierNameE;
	}

	public void setSupplierNameA(String supplierNameA){
		this.supplierNameA = supplierNameA;
	}

	public String getSupplierNameA(){
		return supplierNameA;
	}

	@Override
 	public String toString(){
		return 
			"Suppliers{" + 
			"supplierID = '" + supplierID + '\'' + 
			",supplierNameE = '" + supplierNameE + '\'' + 
			",supplierNameA = '" + supplierNameA + '\'' + 
			"}";
		}
}