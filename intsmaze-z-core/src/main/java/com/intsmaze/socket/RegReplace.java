package com.intsmaze.socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegReplace
{
	public static void main(String[] args)
	{
		String regEx = "a+";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher("okaaaa LetmeAseeaaa aa booa");
		String s = m.replaceAll("A");
		System.out.println(s);
	}
}
