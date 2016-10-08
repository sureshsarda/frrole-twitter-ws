package com.frrole.twitter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileCredentialProvider implements CredentialProvider {

	Properties properties = new Properties();

	public FileCredentialProvider() {
		InputStream is = null;
		try {
			is = FileCredentialProvider.class.getClassLoader().getResourceAsStream("twitter-api.properties");
			properties.load(is);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getConsumerKey() {
		return properties.getProperty("CONSUMER_KEY");
	}

	public String getConsumerSecret() {
		return properties.getProperty("CONSUMER_SECRET");
	}

	public String getAccessToken() {
		return properties.getProperty("ACCESS_TOKEN");
	}

	public String getAccessTokenSecret() {
		return properties.getProperty("ACCESS_TOKEN_SECRET");
	}

	// public static final String CONSUMER_KEY = "5425BXbGRpIYUYd74QxAVMtCp";
	// public static final String CONSUMER_SECRET =
	// "nyeE6M9zcxN04P62I9ojbsMCfsguRfnGHNbp3QT762rpmPqA83";
	//
	// public static final String ACCESS_TOKEN =
	// "716096337599549440-KKpz1i7GsyxRnGYuXaOZmnvOFbY26Sq";
	// public static final String ACCESS_TOKEN_SECRET =
	// "g8Rw5UtFyC5uccakTZkfbWo7cE3wZKgr3X81Jqswbd6dD";

}
