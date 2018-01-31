package com.rain.TestClient;

import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.databene.benerator.anno.Source;
import org.databene.feed4testng.FeedTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCC extends FeedTest{
	
	@Test(dataProvider="feeder")
	@Source("./data/add.csv")
//	http://192.168.111.128:8080/TestServlet/TestServlet?a=1&b=2
	public void doGet(String a, String b, String c) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		URI uri = new URIBuilder()
				.setScheme("http")
				.setHost("192.168.111.128")
				.setPort(8080)
				.setPath("/TestServlet/TestServlet")
				.setParameter("a", a)
				.setParameter("b", b)
				.build();
		HttpGet request = new HttpGet(uri);
		CloseableHttpResponse response = client.execute(request);
		String actual = EntityUtils.toString(response.getEntity());
		
		Assert.assertEquals(actual, c);
		
		
		
	}
}
