package com.frrole.tweet;

import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;

import com.frrole.SearchCriterion;
import com.frrole.aws.AwsElasticSearchClient;
import com.frrole.model.Tweet;
import com.frrole.model.TweetMapper;

@Stateless
public class TweetImpl {

	private AwsElasticSearchClient client = new AwsElasticSearchClient();

	public String search(SearchCriterion criterion) throws IOException {
		List<Tweet> result = client.search(criterion.getQuery());

		if ("JSON".equalsIgnoreCase(criterion.getFormat())) {
			return TweetMapper.asJsonString(result);
		} else if ("XML".equalsIgnoreCase(criterion.getFormat())) {
			return TweetMapper.asXmlString(result);
		} else if ("CSV".equalsIgnoreCase(criterion.getFormat())) {
			return TweetMapper.asCsvString(result);
		} else {
			throw new IllegalArgumentException(
					"Format Specified is not supported. Supported formats are: XML, CSV, JSON");
		}
	}
	
	public int count(SearchCriterion criterion) throws IOException {
		return client.getCount(criterion.getQuery());
	}
}
