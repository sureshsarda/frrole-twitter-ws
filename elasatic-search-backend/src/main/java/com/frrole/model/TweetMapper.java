package com.frrole.model;

import java.util.List;

public class TweetMapper {

	public static String asJsonString(List<Tweet> tweets) {
		StringBuilder sb = new StringBuilder("{\"tweets\": [");
		for (Tweet tweet : tweets) {
			sb.append(tweet.asJsonString());
			sb.append(System.lineSeparator());
		}
		sb.append("]}");
		return sb.toString();
	}

	public static String asXmlString(List<Tweet> tweets) {
		StringBuilder sb = new StringBuilder("<Tweets>");
		for (Tweet tweet : tweets) {
			sb.append(tweet.asXmlString());
			sb.append(System.lineSeparator());
		}
		sb.append("</Tweets>");
		return sb.toString();
	}

	public static String asCsvString(List<Tweet> tweets) {
		StringBuilder sb = new StringBuilder();
		for (Tweet tweet : tweets) {
			sb.append(tweet.asCsvString());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
}
