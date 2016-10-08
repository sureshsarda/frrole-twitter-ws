package com.frrole;

public class SearchCriterion {

	private String query;
	private String format;

	@Override
	public String toString() {
		return "SearchCriterion [query=" + query + ", format=" + format + "]";
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
