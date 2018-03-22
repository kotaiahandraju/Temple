package com.aurospaces.neighbourhood.util;

import java.util.Date;
import java.util.Locale;
import java.text.*;

public class KKK {
	public  static void main(String k[]){
		/*String s ="http://enterprise.smsgupshup.com/GatewayAPI/rest?method=SendMessage&send_to=9010410484&msg=Your%20Booking%20is%20confirmed%20for%202015-06-04%20at%2012%20PM-04%20PM.%20Discount%2010%.%20You%20will%20pay%20Rs.%201080.%20Vendor%20name%20Dr%20NarayanaPrasad%20%20Ph%20No.%209945026999.%20Thank%20You&msg_type=TEXT&userid=2000143438&auth_scheme=plain&password=DlMCnLhGe&v=1.1&format=text";
		
		String s2="http://enterprise.smsgupshup.com/GatewayAPI/rest?method=SendMessage&send_to=919886937482&msg=Dear%20Customer,%20Your%20Mr.Auro%20Professional%20Assigned%20to%20you%20for%20order%20a3bce4.%20Name:%20Thyrocare%20koti%20Mobile:9010410484%20www.aurospaces.com&msg_type=TEXT&userid=2000143438&auth_scheme=plain&password=DlMCnLhGe&v=1.1&format=text";
		System.out.println(s.charAt(181));
		System.out.println(s.charAt(183));
		System.out.println(s.charAt(184));
		System.out.println(s.charAt(185));
		System.out.println(s.charAt(222));*/
		
		String dt = "29-August-2017";
		
		try{
			DateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy", Locale.ENGLISH); 
			Date startDate = (Date)formatter.parse(dt);
			System.out.println(startDate);
			System.out.println((Date)formatter.parse(dt));
		}
		catch(Exception e){
			
		}
		/*char[] c = s.toCharArray();
		System.out.println(c.length);
		System.out.println(c[202]);
		for(int i=200;i <c.length; i++ ){
			System.out.println(c[i]);
		}*/
	}
	
	
}
//Dear Customer, Your Mr.Auro Professional Assigned to you for order _order_. Name: _name_ Mobile:_vmobile_ www.aurospaces.com