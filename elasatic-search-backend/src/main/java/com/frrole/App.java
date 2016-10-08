package com.frrole;

import java.io.IOException;
import java.util.List;

import com.frrole.aws.AwsElasticSearchClient;
import com.frrole.model.Tweet;
import com.frrole.model.TweetMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        List<Tweet> res = new AwsElasticSearchClient().search("modi");
    	System.out.println(TweetMapper.asCsvString(res));
    	System.out.println(TweetMapper.asXmlString(res));
    	System.out.println(TweetMapper.asJsonString(res));
    }
}
