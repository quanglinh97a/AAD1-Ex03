package com.quanglinhit;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormHandle {
	public HashMap<String, String> validate(String birthday, String phone){
		HashMap<String, String> erros=new HashMap<>();
		if(birthday.isEmpty()){erros.put("Birthday", "Account ko duoc de trong!");}
		if(phone.isEmpty()){erros.put("Phone", "Account ko duoc de trong!");}
		
		if (birthday.length()!=0) {
			String ptBirthday = "\\d{4}\\/\\d{2}\\/\\d{2}";
			Pattern pattern = Pattern.compile(ptBirthday);
	        Matcher matcher = pattern.matcher(birthday);
	        erros.put("Birthday", "Account ko dung dinh dang!");
		}
		if (phone.length()!=0) {
			String ptPhone = "(\\+84|0)\\d{9,10}";
			Pattern pattern = Pattern.compile(ptPhone);
	        Matcher matcher = pattern.matcher(phone);
	        erros.put("Phone", "Account ko duoc de trong!");
		}

		return erros;
	}
}
