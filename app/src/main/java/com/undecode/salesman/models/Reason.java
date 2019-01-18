package com.undecode.salesman.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class Reason extends RealmObject {

	@SerializedName("ReasonNameA")
	private String reasonNameA;

	@SerializedName("ReasonNameE")
	private String reasonNameE;

	@PrimaryKey
	@SerializedName("ReasonID")
	private int reasonID;

	public void setReasonNameA(String reasonNameA){
		this.reasonNameA = reasonNameA;
	}

	public String getReasonNameA(){
		return reasonNameA;
	}

	public void setReasonNameE(String reasonNameE){
		this.reasonNameE = reasonNameE;
	}

	public String getReasonNameE(){
		return reasonNameE;
	}

	public void setReasonID(int reasonID){
		this.reasonID = reasonID;
	}

	public int getReasonID(){
		return reasonID;
	}

	@Override
 	public String toString(){
		return reasonNameE;
		}
}