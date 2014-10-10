/**
 * 
 */
package com.telestax.tads2014;

import com.google.gson.annotations.SerializedName;

/**
 * @author jean
 *
 */
public class PhoneNumberInformation {
	@SerializedName("original-country")
	String location;
	@SerializedName("pon-name")
	String ponName;
	@SerializedName("is-roaming")
	boolean isRoaming;
	@SerializedName("ron-name")
	String ronName;
	@SerializedName("ron-country")
	String ronCountry;
	@SerializedName("is-ported")
	boolean isPorted;
	@SerializedName("orn-name")
	String ornName;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPonName() {
		return ponName;
	}
	public void setPonName(String ponName) {
		this.ponName = ponName;
	}
	public boolean isRoaming() {
		return isRoaming;
	}
	public void setRoaming(boolean isRoaming) {
		this.isRoaming = isRoaming;
	}
	public String getRonName() {
		return ronName;
	}
	public void setRonName(String ronName) {
		this.ronName = ronName;
	}
	public String getRonCountry() {
		return ronCountry;
	}
	public void setRonCountry(String ronCountry) {
		this.ronCountry = ronCountry;
	}
	public boolean isPorted() {
		return isPorted;
	}
	public void setPorted(boolean isPorted) {
		this.isPorted = isPorted;
	}
	public String getOrnName() {
		return ornName;
	}
	public void setOrnName(String ornName) {
		this.ornName = ornName;
	}
}
