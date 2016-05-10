package com.example.cassandra.simple_client;

import java.util.Calendar;
import java.util.TimeZone;

public class App1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new App1().run();
	}
	
	private void run()
	{
		Calendar c =  Calendar.getInstance();
		TimeZone tzone = TimeZone.getTimeZone("America/Los_Angeles");
		c.setTimeZone(tzone);
		int offsetinmillisec = c.getTimeZone().getRawOffset();
		int offsetinminutes = offsetinmillisec/(60*1000);
		System.out.println(offsetinminutes);
		int dstAdjustmentinmillisec = c.getTimeZone().getDSTSavings();
		int dstAdjustmentinMinutes= dstAdjustmentinmillisec/(60*1000);
		System.out.println(dstAdjustmentinMinutes);
		offsetinminutes+=dstAdjustmentinMinutes;
		System.out.println(offsetinminutes);
	}

}
