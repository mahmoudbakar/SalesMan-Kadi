package com.undecode.salesman.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import com.undecode.salesman.utils.MyPreferance;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class Unit extends RealmObject {

	@SerializedName("UnitNameA")
	private String unitNameA;

	@PrimaryKey
	@SerializedName("UnitID")
	private int unitID;

	@SerializedName("UnitNameE")
	private String unitNameE;

	public void setUnitNameA(String unitNameA){
		this.unitNameA = unitNameA;
	}

	public String getUnitNameA(){
		return unitNameA;
	}

	public void setUnitID(int unitID){
		this.unitID = unitID;
	}

	public int getUnitID(){
		return unitID;
	}

	public void setUnitNameE(String unitNameE){
		this.unitNameE = unitNameE;
	}

	public String getUnitNameE(){
		return unitNameE;
	}

	public String getUnitName()
	{
		if (MyPreferance.getInstance().getLang().equals("ar"))
		{
			return getUnitNameA();
		}else
		{
			return getUnitNameE();
		}
	}

	@Override
 	public String toString(){
		return 
			"Unit{" + 
			"unitNameA = '" + unitNameA + '\'' + 
			",unitID = '" + unitID + '\'' + 
			",unitNameE = '" + unitNameE + '\'' + 
			"}";
		}
}