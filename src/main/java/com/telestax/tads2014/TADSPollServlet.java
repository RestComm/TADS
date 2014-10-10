package com.telestax.tads2014;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * <p>
 * A simple servlet taking advantage of features added in 3.0.
 * </p>
 * 
 * <p>
 * The servlet is registered and mapped to /TADSPoll using the {@linkplain WebServlet
 * @HttpServlet}. The {@link TADSPollService} is injected by CDI.
 * </p>
 * 
 * @author Jean Deruelle
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/TADSPoll")
public class TADSPollServlet extends HttpServlet {

	@Inject
	TADSPollService tadsPollService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fromNumber = req.getParameter("from");
		String toNumber = req.getParameter("to");
		String drink = req.getParameter("drink");
		String checkCLI = req.getParameter("checkCLI");

		String birthDate = tadsPollService.getBirthDate(toNumber);
		String favDrink = tadsPollService.getDrink(drink);
		
		if(checkCLI == null) {
			
			String location = "France";
			PhoneNumberInformation phoneNumberInformation = null;
			try {
				phoneNumberInformation = tadsPollService
						.getPhoneNumberInformation(fromNumber);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(phoneNumberInformation == null) {
				resp.sendError(500);
				return;
			}
			location = phoneNumberInformation.getLocation();

			System.out.println("from " + fromNumber + ", to " + toNumber
					+ ", drink " + drink);
			System.out.println("birthDate " + birthDate + ", location " + location
					+ ", favDrink " + favDrink);

			TADS2014Response tads2014Response = new TADS2014Response(birthDate,
					favDrink, phoneNumberInformation);

			try {
				boolean updated = tadsPollService.updateDashingDashboard(tads2014Response);
				System.out.println("dashing udpated ? " + updated);
				updated = tadsPollService.updateDashingHeatMap(tads2014Response);
				System.out.println("dashing heatmap udpated ? " + updated);
				updated = tadsPollService.updateDashingDrink(drink);
				System.out.println("dashing drink udpated ? " + updated);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setContentType("application/json");
			PrintWriter writer = resp.getWriter();
			Gson gson = new Gson();
			writer.println(gson.toJson(tads2014Response));
			writer.close();
		} else {
			String isRealNumber = "true";

			if(fromNumber == null || fromNumber.trim().length() < 1) {
				isRealNumber = "false";
			} else {
			      if(fromNumber.startsWith("+") && fromNumber.substring(1).matches("[0-9]+")) {
			    	  isRealNumber = "true";
			      } else if(fromNumber.matches("[0-9]+")) {
			    	  isRealNumber = "true";
			      } else {
			    	  isRealNumber = "false";
			      }
			}
			
			resp.setContentType("application/json");
			PrintWriter writer = resp.getWriter();
			writer.println("{\"isRealNumber\"=\""+isRealNumber+"\",\"birthDate\"=\""+birthDate+"\",\"favDrink\"=\""+favDrink+"\"}");
			writer.close();
		}
	}

}
