/**
 * 
 */
package com.telestax.tads2014;


/**
 * @author jean
 *
 */
public class TADS2014Response {
	String birthDate;
	String favDrink;
	private String isPortedString;
	PhoneNumberInformation phoneNumberInformation;
	
	public TADS2014Response(String birthDate, String favDrink,
			PhoneNumberInformation phoneNumberInformation) {
		super();
		this.birthDate = birthDate;
		this.favDrink = favDrink;
		isPortedString = "false";
		if(phoneNumberInformation != null && phoneNumberInformation.isPorted()) {
			this.isPortedString = "true";
		}
		
		this.phoneNumberInformation = phoneNumberInformation;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getFavDrink() {
		return favDrink;
	}
	public void setFavDrink(String favDrink) {
		this.favDrink = favDrink;
	}
	public PhoneNumberInformation getPhoneNumberInformation() {
		return phoneNumberInformation;
	}
	public void setPhoneNumberInformation(
			PhoneNumberInformation phoneNumberInformation) {
		this.phoneNumberInformation = phoneNumberInformation;
	}

	public String getIsPortedString() {
		return isPortedString;
	}

	public void setIsPortedString(String isPortedString) {
		this.isPortedString = isPortedString;
	}
}
