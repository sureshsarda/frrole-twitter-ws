package com.frrole.twitter;

public interface CredentialProvider {

	public String getConsumerKey();

	public String getConsumerSecret();

	public String getAccessToken();

	public String getAccessTokenSecret();

	
}
