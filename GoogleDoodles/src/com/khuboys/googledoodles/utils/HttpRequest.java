package com.khuboys.googledoodles.utils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * This class support functions request to server
 * @author QUOC NGUYEN
 *
 */
public class HttpRequest {
	 
	static String response = null;
	
	 /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */
	public static String makeServiceCall(String url, int method, List<NameValuePair> params) {
	        try {
	            // http client
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpEntity httpEntity = null;
	            HttpResponse httpResponse = null;
	             
	            // Checking http request method type
	            if (method == Defs.POST) {
	                HttpPost httpPost = new HttpPost(url);
	                // adding post params
	                if (params != null) {
	                    httpPost.setEntity(new UrlEncodedFormEntity(params));
	                }
	 
	                httpResponse = httpClient.execute(httpPost);
	 
	            } else if (method == Defs.GET) {
	                // appending params to url
	                if (params != null) {
	                    String paramString = URLEncodedUtils
	                            .format(params, "utf-8");
	                    url += "?" + paramString;
	                }
	                HttpGet httpGet = new HttpGet(url);
	                httpResponse = httpClient.execute(httpGet);
	            }
	            httpEntity = httpResponse.getEntity();
	            response = EntityUtils.toString(httpEntity);
	 
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         
	        return response;
	 
	    }
}
