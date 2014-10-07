
package com.telestax.tads2014;

/**
 * A simple CDI service which is able to say hello to someone
 * 
 * @author Jean Deruelle
 * 
 */
public class TADSPollService {

    String getBirthDate(String fromNumber) {
	if(fromNumber == null) {
		return "69";
	} 
        return fromNumber.substring(fromNumber.length()-2, fromNumber.length());
    }

    String getLocation(String fromNumber) {
	String location = "France";
	//TODO implement logic to query Truphone HRL REST API
	return location;
    }

    String getDrink(String drink) {
	if(drink == null) {
		return "no drink";
	}
	if(drink.equalsIgnoreCase("1")) {
		return "Beer";
	}
	if(drink.equalsIgnoreCase("2")) {
		return "Wine";
	}
	if(drink.equalsIgnoreCase("3")) {
		return "Soft Drink";
	}
	return "no drink";
    } 
}
