package com.frrole.aws;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.frrole.model.Tweet;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;

public class AwsElasticSearchClient {

	public static final String URL = "http://search-frrole-poc-twinjqlg4lptpul62thucuwa24.us-east-1.es.amazonaws.com/";
	public static final String INDEX = "twitter";
	public static final String TYPE = "tweet";

	private JestClient client;

	public AwsElasticSearchClient() {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(URL).multiThreaded(true).build());
		client = factory.getObject();
	}

	public List<Tweet> search(String query) throws IOException {

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("text", query));

		Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX).addType(TYPE).build();

		SearchResult result = client.execute(search);

		List<Tweet> list = new LinkedList<Tweet>();
		for (Hit<Tweet, Void> item : result.getHits(Tweet.class)) {
			list.add(item.source);
		}
		return list;
	}

	public int getCount() throws IOException {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(0);
		Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX).addType(TYPE).build();
		SearchResult result = client.execute(search);
		return result.getTotal();

	}
}
