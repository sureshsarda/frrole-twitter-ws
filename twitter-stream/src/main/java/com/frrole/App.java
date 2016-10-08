package com.frrole;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.frrole.twitter.PublicTwitterStream;

/**
 * Hello world!
 *
 */
public class App {
	
	private static final int MAX_TWEETS = 10;

	public static void main(String[] args) {
		PublicTwitterStream stream = new PublicTwitterStream();
		stream.run(readKeywords(), MAX_TWEETS);
	}

	public static List<String> readKeywords() {
		InputStream is = App.class.getClassLoader().getResourceAsStream("keywords");

		Scanner scn = new Scanner(is);
		scn.useDelimiter(System.lineSeparator());

		List<String> keywords = new LinkedList<String>();
		while (scn.hasNextLine()) {
			String keyword = scn.nextLine().trim();

			if (!keyword.isEmpty() && !keyword.startsWith("#")) {
				keywords.add(keyword);
			}
		}
		scn.close();

		return keywords;
	}
}
