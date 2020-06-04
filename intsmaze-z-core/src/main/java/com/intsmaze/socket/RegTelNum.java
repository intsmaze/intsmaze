package com.intsmaze.socket;import java.util.regex.Pattern;

public class RegTelNum
{
	public static void main(String[] args)
	{
		System.out.println(Pattern.matches("[0-9]{3,4}\\-?[0-9]+", "010-95555"));
		System.out.println(Pattern.matches("[0-9]{3,4}\\-?[0-9]+", "01095555"));
		System.out.println(Pattern.matches("[0-9]{3,4}\\-?[0-9]+", "95555"));
		System.out.println(Pattern.matches("[0-9]{3,4}\\-?[0-9]+", "0755-95555"));
		System.out.println(Pattern.matches("[0-9]{3,4}\\-?[0-9]+", "123456-95555"));
		System.out.println(Pattern.matches("[0-9]{3,4}\\-?[0-9]+", "spam@mail.com"));
	}
}
