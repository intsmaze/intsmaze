package com.intsmaze.socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

	public class RegEmail
	{
		public static void main(String[] args)
		{
			validateEmail("email@mail.com");
			validateEmail("www.sohu.com");
		}

		private static void validateEmail(String mail)
		{
			Pattern p = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");

			Matcher m = p.matcher(mail);
			if (m.matches())
			{
				System.out.println("合法的email");
			} else
			{
				System.out.println("非法的email");
			}
		}
	}
