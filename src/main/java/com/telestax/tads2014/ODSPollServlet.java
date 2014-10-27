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
 * The servlet is registered and mapped to /ODSPoll using the {@linkplain WebServlet
 * @HttpServlet}. The {@link ODSPollService} is injected by CDI.
 * </p>
 * 
 * @author Jean Deruelle
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/ODSPoll")
public class ODSPollServlet extends HttpServlet {

	@Inject
	ODSPollService odsPollService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fromNumber = req.getParameter("from");
		String toNumber = req.getParameter("to");
		String role = req.getParameter("role");
		String distribution = req.getParameter("distribution");
		String environment = req.getParameter("environment");
		String checkCLI = req.getParameter("checkCLI");

		String birthDate = odsPollService.getBirthDate(toNumber);
//		String currentRole = odsPollService.getRole(role);
//		String currentDistribution = odsPollService.getDistribution(distribution);
//		String currentEnvironment = odsPollService.getEnvironment(environment);
		
		if(checkCLI == null) {
			
			String location = "France";
			PhoneNumberInformation phoneNumberInformation = null;
			try {
				phoneNumberInformation = odsPollService
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
					+ ", role " + role + ", distribution " + distribution + ", environment " + environment);
//			System.out.println("birthDate " + birthDate + ", location " + location
//					+ ", current Role " + currentRole + ", current Distribution " + currentDistribution + ", current Environment " + currentEnvironment);

			ODS2014Response ods2014Response = new ODS2014Response(birthDate,
					phoneNumberInformation);

			try {
				boolean updated = odsPollService.updateDashingRole(role);
				System.out.println("dashing role udpated ? " + updated);
				updated = odsPollService.updateDashingDistribution(distribution);
				System.out.println("dashing distribution udpated ? " + updated);
				updated = odsPollService.updateDashingEnvironment(environment);
				System.out.println("dashing environment udpated ? " + updated);
				updated = odsPollService.updateDashingHeatMap(ods2014Response);
				System.out.println("dashing heatmap udpated ? " + updated);
			} catch (Exception e) {
				e.printStackTrace();
				resp.sendError(500);
				return;
			}
			resp.setContentType("application/json");
			PrintWriter writer = resp.getWriter();
			Gson gson = new Gson();
			writer.println(gson.toJson(ods2014Response));
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
			writer.println("{\"isRealNumber\"=\""+isRealNumber+"\",\"birthDate\"=\""+birthDate+"\"}");
			writer.close();
		}
	}

}
