/**
 * 
 */
package com.telestax.tads2014;


/**
 * @author jean
 *
 */
public class ODS2014Response {
	String birthDate;
	String role;
	String distribution;
	String environment;
	
	PhoneNumberInformation phoneNumberInformation;
	
	public ODS2014Response(String birthDate,
			PhoneNumberInformation phoneNumberInformation) {
		super();
		this.birthDate = birthDate;
		this.phoneNumberInformation = phoneNumberInformation;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public PhoneNumberInformation getPhoneNumberInformation() {
		return phoneNumberInformation;
	}
	public void setPhoneNumberInformation(
			PhoneNumberInformation phoneNumberInformation) {
		this.phoneNumberInformation = phoneNumberInformation;
	}
}
