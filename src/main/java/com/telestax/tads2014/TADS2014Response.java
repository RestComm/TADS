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
	String roamingString;
	
	PhoneNumberInformation phoneNumberInformation;
	
	public TADS2014Response(String birthDate, String favDrink,
			PhoneNumberInformation phoneNumberInformation) {
		super();
		this.birthDate = birthDate;
		this.roamingString = "which is not currently roaming.";
		this.favDrink = favDrink;
		isPortedString = "false";
		if(phoneNumberInformation != null && phoneNumberInformation.isPorted()) {
			this.isPortedString = "true";
		}
		if(phoneNumberInformation != null && phoneNumberInformation.isRoaming()) {
			this.roamingString = "which is roaming on the " + phoneNumberInformation.getRonName() + " network in " + phoneNumberInformation.getRonCountry() + ".";
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

	public String getRoamingString() {
		return roamingString;
	}

	public void setRoamingString(String roamingString) {
		this.roamingString = roamingString;
	}
}
