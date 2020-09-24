package com.rgp.de.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import com.rgp.de.beans.Envelope;

public class EnvelopeComparator implements Comparator<Envelope>{

	@Override
	public int compare(Envelope o1, Envelope o2) {
		
		Integer lengthOfFirstSeconds= (o1.getStatusChangedDateTime().length()) - (o1.getStatusChangedDateTime().lastIndexOf(".")+1);

		Integer lengthOfSecondseconds = (o2.getStatusChangedDateTime().length()) - (o2.getStatusChangedDateTime().lastIndexOf(".")+1);
		
		String format1="yyyy-MM-dd'T'HH:mm:ss.";
		String format2="yyyy-MM-dd'T'HH:mm:ss.";
		
		for(int i=0;i<lengthOfFirstSeconds;i++) {
			format1=format1+"S";
		}
		
		for(int i=0;i<lengthOfSecondseconds;i++) {
			format2=format2+"S";
		}
		
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(format1); 
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(format2); 
		
		LocalDateTime dateTime1 = LocalDateTime.parse(o1.getStatusChangedDateTime(), formatter1);
		LocalDateTime dateTime2 = LocalDateTime.parse(o2.getStatusChangedDateTime(), formatter2);
		
		
		 if(dateTime1.isBefore(dateTime2)) {
			 return 1;
		 }else if(dateTime1.isAfter(dateTime2)){
			 return -1;
		 }
		
		return 0;
	}

}
