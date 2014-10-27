package com.telestax.tads2014;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.google.gson.Gson;

/**
 * A simple CDI service which is able to say hello to someone
 * 
 * @author Jean Deruelle
 * 
 */
public class ODSPollService {

	static String TP_URL = "https://dev.tp.mu/holler/lookup_msisdn.php";
	static String DASHING_BASE_URL="http://ec2-54-216-246-177.eu-west-1.compute.amazonaws.com:80/";
	static String DASHING_ROLE_URL = DASHING_BASE_URL + "widgets/role";
	static String DASHING_DISTRIBUTION_URL = DASHING_BASE_URL + "widgets/distribution";
	static String DASHING_ENVIRONMENT_URL = DASHING_BASE_URL + "widgets/environment";
	static String DASHING_HEATMAP_URL = DASHING_BASE_URL + "widgets/heatmap";
	static String CHARSET = "UTF-8";
	static String TOKEN = "9YtXr4AhVKaqBWrTmNYj2Px";
	static String COUNTRY = "original-country";

	String getBirthDate(String fromNumber) {
		if (fromNumber == null) {
			return "69";
		}
		return fromNumber.substring(fromNumber.length() - 2,
				fromNumber.length());
	}

	PhoneNumberInformation getPhoneNumberInformation(String fromNumber) throws Exception {
		if (fromNumber == null) {
			return null;
		}
		if (fromNumber.startsWith("+")) {
			fromNumber = fromNumber.substring(1, fromNumber.length());
		} else if (fromNumber.startsWith("00")) {
			fromNumber = fromNumber.substring(2, fromNumber.length());
		} else if (fromNumber.startsWith("0")){
			fromNumber = fromNumber.substring(1, fromNumber.length());
			fromNumber = "44" + fromNumber;
		}

		String query = String.format("token=%s&msisdn=%s",
				URLEncoder.encode(TOKEN, CHARSET),
				URLEncoder.encode(fromNumber, CHARSET));

		System.out.println("url " + TP_URL + "?" + query);
		URLConnection connection = new URL(TP_URL + "?" + query)
				.openConnection();
		connection.setRequestProperty("Accept-Charset", CHARSET);

		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		int status = httpConnection.getResponseCode();

		System.out.println("status " + status);
		if (status >= 200 && status < 300) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String responseBody = "";
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println("response line " + inputLine);
				responseBody = responseBody.concat(inputLine);
			}
			in.close();
			System.out.println("responseBody " + responseBody);
			Gson gson = new Gson();
			return gson.fromJson(
					responseBody, PhoneNumberInformation.class);
		}
		return null;
	}

	boolean updateDashingRole(String role)
			throws Exception {
		HttpURLConnection con = (HttpURLConnection) new URL(DASHING_ROLE_URL)
				.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");

		String userIncrement = "0";
		String vendorIncrement = "0";
		String integratorIncrement = "0";
		String otherIncrement = "0";
		if(role != null) {
			if(role.trim().equalsIgnoreCase("1")) {
				userIncrement = "1";
			} else if(role.trim().equalsIgnoreCase("2")) {
				vendorIncrement = "1";
			} else if(role.trim().equalsIgnoreCase("3")) {
				integratorIncrement = "1";
			} else if(role.trim().equalsIgnoreCase("4")) {
				otherIncrement = "1";
			}
		}
		
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		String updateRequest = "{ \"auth_token\": \"YOUR_AUTH_TOKEN\", \"value\":[{ \"label\": \"User\", \"increment\": " + userIncrement + " }, "
				+ "{ \"label\": \"Vendor\", \"increment\": " + vendorIncrement + " }, { \"label\": \"Integrator\", \"increment\": " + integratorIncrement + " },"
						+ "{ \"label\": \"Other\", \"increment\": " + otherIncrement + " }] }";
		System.out.println(DASHING_ROLE_URL + " : Role request " + updateRequest);
		wr.write(updateRequest);
		wr.flush();

		int status = con.getResponseCode();
		if (status >= 200 && status < 300) {
			return true;
		}
		return false;
	}
	
	boolean updateDashingDistribution(String distribution)
			throws Exception {
		HttpURLConnection con = (HttpURLConnection) new URL(DASHING_DISTRIBUTION_URL)
				.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		
		String ubuntuIncrement = "0";
		String redhatIncrement = "0";
		String mirandisIncrement = "0";
		String hpIncrement = "0";
		String communityIncrement = "0";
		String otherIncrement = "0";
		String multipleIncrement = "0";
		if(distribution != null) {
			if(distribution.trim().equalsIgnoreCase("1")) {
				ubuntuIncrement = "1";
			} else if(distribution.trim().equalsIgnoreCase("2")) {
				redhatIncrement = "1";
			} else if(distribution.trim().equalsIgnoreCase("3")) {
				mirandisIncrement = "1";
			} else if(distribution.trim().equalsIgnoreCase("7")) {
				multipleIncrement = "1";
			} else if(distribution.trim().equalsIgnoreCase("4")) {
				hpIncrement = "1";
			} else if(distribution.trim().equalsIgnoreCase("5")) {
				communityIncrement = "1";
			} else if(distribution.trim().equalsIgnoreCase("6")) {
				otherIncrement = "1";
			} 
		}
		
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		String updateRequest = "{ \"auth_token\": \"YOUR_AUTH_TOKEN\", \"value\":[{ \"label\": \"ubuntu\", \"increment\": " + ubuntuIncrement + " }, "
				+ "{ \"label\": \"Red Hat\", \"increment\": " + redhatIncrement + " }, { \"label\": \"Mirandis\", \"increment\": " + mirandisIncrement + " },"
				+ "{ \"label\": \"HP\", \"increment\": " + hpIncrement + " }, { \"label\": \"Community\", \"increment\": " + communityIncrement + " }, "
				+ "{ \"label\": \"Other\", \"increment\": " + otherIncrement + " }, { \"label\": \"Multiple\", \"increment\": " + multipleIncrement + " }] }";
		System.out.println(DASHING_DISTRIBUTION_URL + " : Distribution request " + updateRequest);
		wr.write(updateRequest);
		wr.flush();
		
		int status = con.getResponseCode();
		if (status >= 200 && status < 300) {
			return true;
		}
		return false;
	}
	
	boolean updateDashingEnvironment(String environment)
			throws Exception {
		HttpURLConnection con = (HttpURLConnection) new URL(DASHING_ENVIRONMENT_URL)
				.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		
		String productionIncrement = "0";
		String preProductionIncrement = "0";
		String devIncrement = "0";
		String otherIncrement = "0";
		if(environment != null) {
			if(environment.trim().equalsIgnoreCase("1")) {
				productionIncrement = "1";
			} else if(environment.trim().equalsIgnoreCase("2")) {
				preProductionIncrement = "1";
			} else if(environment.trim().equalsIgnoreCase("3")) {
				devIncrement = "1";
			} else if(environment.trim().equalsIgnoreCase("4")) {
				otherIncrement = "1";
			}
		}
		
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		String updateRequest = "{ \"auth_token\": \"YOUR_AUTH_TOKEN\", \"value\":[{ \"label\": \"In Production\", \"increment\": " + productionIncrement + " }, "
				+ "{ \"label\": \"Pre Production\", \"increment\": " + preProductionIncrement + " }, { \"label\": \"Development\", \"increment\": " + devIncrement + " },"
						+ "{ \"label\": \"I wish I had it\", \"increment\": " + otherIncrement + " }] }";
		System.out.println(DASHING_ENVIRONMENT_URL + " : Environment request " + updateRequest);
		wr.write(updateRequest);
		wr.flush();
		
		int status = con.getResponseCode();
		if (status >= 200 && status < 300) {
			return true;
		}
		return false;
	}
	
	boolean updateDashingHeatMap(ODS2014Response ods2014Response)
			throws Exception {
		HttpURLConnection con = (HttpURLConnection) new URL(DASHING_HEATMAP_URL)
				.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		String updateRequest = "{ \"auth_token\": \"YOUR_AUTH_TOKEN\", \"country\": \""
				+ ods2014Response.getPhoneNumberInformation().getLocation() + "\" }";
		System.out.println(DASHING_HEATMAP_URL + " : HeatMap request " + updateRequest);
		wr.write(updateRequest);
		wr.flush();

		int status = con.getResponseCode();
		if (status >= 200 && status < 300) {
			return true;
		}
		return false;
	}
}
