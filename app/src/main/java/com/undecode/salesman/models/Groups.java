package com.undecode.salesman.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import com.undecode.salesman.utils.MyPreferance;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class Groups extends RealmObject {

	@SerializedName("GroupNameE")
	private String groupNameE;

	@SerializedName("GroupNameA")
	private String groupNameA;

	@PrimaryKey
	@SerializedName("GroupID")
	private int groupID;

	private boolean checked;

	public Groups() {
	}

	public Groups(String groupNameE, String groupNameA, int groupID)
	{
		this.groupNameE = groupNameE;
		this.groupNameA = groupNameA;
		this.groupID = groupID;
		this.checked = false;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setGroupNameE(String groupNameE){
		this.groupNameE = groupNameE;
	}

	public String getGroupNameE(){
		return groupNameE;
	}

	public void setGroupNameA(String groupNameA){
		this.groupNameA = groupNameA;
	}

	public String getGroupNameA(){
		return groupNameA;
	}

	public void setGroupID(int groupID){
		this.groupID = groupID;
	}

	public int getGroupID(){
		return groupID;
	}

	public String getGroupName()
	{
		if (MyPreferance.getInstance().getLang().equals("ar"))
		{
			return groupNameA;
		}else
		{
			return groupNameE;
		}
	}

	@Override
 	public String toString(){
		return 
			"Groups{" + 
			"groupNameE = '" + groupNameE + '\'' + 
			",groupNameA = '" + groupNameA + '\'' + 
			",groupID = '" + groupID + '\'' + 
			"}";
		}
}