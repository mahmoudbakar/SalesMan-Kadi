package com.undecode.salesman.models.local;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@SerializedName("saved")
	private boolean saved;

	@SerializedName("id")
	private int id;

	public void setSaved(boolean saved){
		this.saved = saved;
	}

	public boolean isSaved(){
		return saved;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"saved = '" + saved + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}