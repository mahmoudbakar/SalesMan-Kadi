package com.undecode.salesman.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("com.robohorse.robopojogenerator")
public class LoginResponse extends RealmObject
{

	@PrimaryKey
	private int id;

	private String user;

	private String password;

	@SerializedName("Phone1")
	private String phone1;

	@SerializedName("Phone3")
	private String phone3;

	@SerializedName("Phone2")
	private String phone2;

	@SerializedName("AccessToken")
	private String accessToken;

	@SerializedName("Email1")
	private String email1;

	@SerializedName("Email2")
	private String email2;

	@SerializedName("Email3")
	private String email3;

	public void setPhone1(String phone1){
		this.phone1 = phone1;
	}

	public String getPhone1(){
		return phone1;
	}

	public void setPhone3(String phone3){
		this.phone3 = phone3;
	}

	public String getPhone3(){
		return phone3;
	}

	public void setPhone2(String phone2){
		this.phone2 = phone2;
	}

	public String getPhone2(){
		return phone2;
	}

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setEmail1(String email1){
		this.email1 = email1;
	}

	public String getEmail1(){
		return email1;
	}

	public void setEmail2(String email2){
		this.email2 = email2;
	}

	public String getEmail2(){
		return email2;
	}

	public void setEmail3(String email3){
		this.email3 = email3;
	}

	public String getEmail3(){
		return email3;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"phone1 = '" + phone1 + '\'' + 
			",phone3 = '" + phone3 + '\'' + 
			",phone2 = '" + phone2 + '\'' + 
			",accessToken = '" + accessToken + '\'' + 
			",email1 = '" + email1 + '\'' + 
			",email2 = '" + email2 + '\'' + 
			",email3 = '" + email3 + '\'' + 
			"}";
		}
}