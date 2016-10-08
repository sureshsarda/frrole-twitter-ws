package com.frrole.model;

public class Tweet {

	private String text;

	private static final String CSV_STRING = "\"%s\",";
	private static final String XML_STRING = "<Tweet><text>%s</text></Tweet>";
	private static final String JSON_STRING = "{\"text\" : \"%s\"}";

	public Tweet(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String asJsonString() {
		return String.format(JSON_STRING, text);
	}

	public String asCsvString() {
		return String.format(CSV_STRING, text);
	}

	public String asXmlString() {
		return String.format(XML_STRING, text);
	}

	@Override
	public String toString() {
		return "Tweet [text=" + text + "]";
	}

}
