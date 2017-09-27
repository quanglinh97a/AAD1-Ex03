package com.quanglinhit;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormHandle {
	public HashMap<String, String> validate(String birthday, String phone){
		HashMap<String, String> erros=new HashMap<>();
		if(birthday.isEmpty()){erros.put("Birthday", "Ngày sinh ko được để trống!");}
		if(phone.isEmpty()){erros.put("Phone", "SĐT ko được để trống!");}
		
		if (birthday.isEmpty()==false) {
			String ptBirthday = "\\d{4}\\/\\d{2}\\/\\d{2}";
			Pattern pattern = Pattern.compile(ptBirthday);
	        Matcher matcher = pattern.matcher(birthday);
	        erros.put("Birthday", "Ngày sinh ko đúng định dạng!");
		}
		if (birthday.isEmpty()==false) {
			String ptPhone = "(\\+84|0)\\d{9,10}";
			Pattern pattern = Pattern.compile(ptPhone);
	        Matcher matcher = pattern.matcher(phone);
	        erros.put("Phone", "SĐT ko đúng định dạng!");
		}

		return erros;
	}
}
