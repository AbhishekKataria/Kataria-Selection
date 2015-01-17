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


public class Register extends AsyncTask<String,Void,String> {

	private static String TAG_UNAME   = "username";
	private static String TAG_PASS    = "password";
	private static String TAG_EMAIL   = "email";

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String str = null;
		HttpClient httpClient = new DefaultHttpClient();
		
	    HttpPost httppost = new HttpPost("http://flogofury.gofreeserve.com/kataria_connect/register.php");
	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
    	nameValuePairs.add(new BasicNameValuePair(TAG_UNAME, params[0]));	    
    	nameValuePairs.add(new BasicNameValuePair(TAG_EMAIL, params[1]));
    	nameValuePairs.add(new BasicNameValuePair(TAG_PASS, params[2]));
    	try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpClient.execute(httppost);
			 str = inputStreamToString(response.getEntity().getContent()).toString();
			Log.i("hari",str);
			System.out.println("STR length is "+str.length());
			for(int i=0;i<str.length();i++)
				System.out.println("position is "+i+" "+str.charAt(i));
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
		Log.i("string",total.toString());
	  return total;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		RegisterActivity.displayMessage(result);
		
	}
		
		


}
