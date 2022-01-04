package com.common.classes;

import java.util.Random;

public class RandomName {

	public static CharSequence str1;

	public static String generateRandomName(String str) 
	{	//Initialize the method for creating random string with help of reference string. 
		String chars = "0123456789";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < str.length()-3; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length()))); //adding characters via given statement.
		str1 = sb.toString();
		return (String) str1;
	}
}
