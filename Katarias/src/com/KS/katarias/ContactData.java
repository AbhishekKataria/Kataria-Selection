package com.KS.katarias;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class ContactData extends AsyncTask<String, Void, String> {

	private static String TAG_EMAIL   = "email";
	private static String TAG_SUBJECT   = "subject";
	private static String TAG_MESSAGE   = "message";
	
	protected StringBuilder inputStreamToString(InputStream is){
		String line = " ";
		StringBuilder total = new StringBuilder();
		
		try
		{
		BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF8"));
			while((line = rd.readLine()) != null){
				total.append(line);
			}
		  }
		  catch(UnsupportedEncodingException e)
		  {
			  Log.i("FORMAT", e.getMessage());
		  }
	       catch(IOException e){
	    	   Log.i("STRING BUILDER", "Error in string buildasdfsf");
	       }
	  return total;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.i("send", "do in bacgorund");
		HttpClient httpClient = new DefaultHttpClient();
		String str = null;
	    HttpPost httppost = new HttpPost("http://flogofury.gofreeserve.com/kataria_connect/message_send.php");
	    Log.i("hari", "do in bacgorund");
	    
	    try{
	    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
	    	nameValuePairs.add(new BasicNameValuePair(TAG_EMAIL, params[0]));
	    	nameValuePairs.add(new BasicNameValuePair(TAG_SUBJECT, params[1]));
	    	nameValuePairs.add(new BasicNameValuePair(TAG_MESSAGE, params[2]));
	    	httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    	Log.i("send", "inside try");
	    	HttpResponse response = httpClient.execute(httppost);
	    	Log.i("send","Hey coming here");
	    	str = inputStreamToString(response.getEntity().getContent()).toString();
	    	str.trim();
	    	Log.i("send",str);
	    }
	    	catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}	
			return str;
	}
	@Override
	protected void onPostExecute(String result) {
	// TODO Auto-generated method stub
	super.onPostExecute(result);
	ContactActivity.displayResult(result);
	}
	
}
