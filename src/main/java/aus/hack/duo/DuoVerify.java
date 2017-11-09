package aus.hack.duo;

/*
 * Demo of the Duo Verify API
 *
 * Documentation: http://www.duosecurity.com/docs/duoverify
 */

import org.json.JSONObject;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import aus.hack.duo.util.Http;

public class DuoVerify {
	private static String MESSAGE = "The PIN is <pin>";

	String apiHost = "api-dddffa5b.duosecurity.com";
	String iKey = "DIREAY3GAKM3CA2Z6SOT";
	String sKey = "JVxymkZaQukRog4gqN0JU0E1ppKw4eWaBt8zTtK6";

	public DuoVerify() {
		super();
	}

	public static void main(String[] args) {

		DuoVerify dv = new DuoVerify();
//		System.out.println("Login staus for jai:" + dv.login("jai"));
//		System.out.println("Login staus for abc:" + dv.login("abc"));
		System.out.println("enrollment status:" + dv.enrollQR("test456"));
	}

	public boolean login(String userName, String capability){

		try{
			Http request = new Http("POST",
					apiHost,
					"/auth/v2/auth");

			request.addParam("username",userName);
			request.addParam("factor", capability);
			request.addParam("device", "mobile");
			request.signRequest(iKey,
					sKey,
					2);

			JSONObject result = (JSONObject)request.executeRequest();
			System.out.println(" Dome with preauth"  + result);
			if(((String)result.get("status_msg")).contains("Success")){
				return true;
			}
		}
		catch(Exception e) {
			System.out.println("error making request");
			System.out.println(e.toString());
		}
		return false;
	}

	public boolean enroll(String name){

		try{
			Http request = new Http("POST",
					apiHost,
					"/auth/v2/enroll");

			request.addParam("username",name);
			request.signRequest(iKey,
					sKey,
					2);

			JSONObject result = (JSONObject)request.executeRequest();
			System.out.println(" Dome with enroll"  + result);
		}
		catch(Exception e) {
			System.out.println("error making request");
			System.out.println(e.toString());
			return false;
		}
		return true;
	}

	public String enrollQR(String name){

		try{
			Http request = new Http("POST",
					apiHost,
					"/auth/v2/enroll");

			request.addParam("username",name);
			request.signRequest(iKey,
					sKey,
					2);

			JSONObject result = (JSONObject)request.executeRequest();
			System.out.println(" Dome with enroll"  + result);
			return "" +result;
		}

		catch(Exception e) {
			System.out.println("error making request");
			System.out.println(e.toString());
			return "error with enrolling";
		}

	}
}