package com.frrole.twitter;

import java.util.List;

import com.frrole.aws.AwsElasticSearchClient;
import com.frrole.model.Tweet;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class PublicTwitterStream {

	private TwitterStream twitterStream = null;

	private AwsElasticSearchClient elasticSearchClient = new AwsElasticSearchClient();

	public PublicTwitterStream() {
		twitterStream = new TwitterStreamFactory(getConfiguration()).getInstance();
	}

	public void run(List<String> keywords, final int maxCount) {
		FilterQuery fq = new FilterQuery();

		fq.track(keywords.toArray(new String[keywords.size()]));
		fq.language("en");

		twitterStream.addListener(new StatusListener() {

			int count = 0;

			public void onStatus(Status status) {
				Tweet tweet = new Tweet(status.getText());
				elasticSearchClient.post(tweet);
				count += 1;
				if (count > maxCount) {
					twitterStream.shutdown();
				}
			}

			public void onException(Exception arg0) {
			}

			public void onTrackLimitationNotice(int arg0) {
			}

			public void onStallWarning(StallWarning arg0) {
			}

			public void onScrubGeo(long arg0, long arg1) {
			}

			public void onDeletionNotice(StatusDeletionNotice arg0) {
			}
		});

		twitterStream.filter(fq);

	}

	private Configuration getConfiguration() {
		CredentialProvider cp = new FileCredentialProvider();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(cp.getConsumerKey());
		cb.setOAuthConsumerSecret(cp.getConsumerSecret());
		cb.setOAuthAccessToken(cp.getAccessToken());
		cb.setOAuthAccessTokenSecret(cp.getAccessTokenSecret());
		return cb.build();
	}

}
