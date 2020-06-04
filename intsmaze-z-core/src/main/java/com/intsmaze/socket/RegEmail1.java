package com.intsmaze.socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEmail1
{
		public static void main(String[] args)
		{
			validateEmail("email@mail.com");
			validateEmail("www.sohu.com");
		}

		private static void validateEmail(String mail)
		{
			//写正则表达式，!!!!!
			Pattern p = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"  );
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

