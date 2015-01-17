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

public class BillData extends AsyncTask<String, Void, String> {
	private static String TAG_BILL_ID = "id";
	private static String TAG_CART_ID = "cart_id";
	private static String TAG_EMAIL = "email";
	private static String TAG_ADDRESS = "address";
	private static String TAG_PHONE = "phone_no";
	private static String TAG_TOTAL = "total";

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String str = null;
	    HttpClient httpClient = new DefaultHttpClient();
		
	    HttpPost httppost = new HttpPost("http://flogofury.gofreeserve.com/kataria_connect/billdata.php");
	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
    	
	    nameValuePairs.add(new BasicNameValuePair(TAG_BILL_ID, params[0]));	    
    	nameValuePairs.add(new BasicNameValuePair(TAG_CART_ID, params[1]));
    	nameValuePairs.add(new BasicNameValuePair(TAG_EMAIL, params[2]));
    	nameValuePairs.add(new BasicNameValuePair(TAG_ADDRESS, params[3]));	    
    	nameValuePairs.add(new BasicNameValuePair(TAG_PHONE, params[4]));
    	nameValuePairs.add(new BasicNameValuePair(TAG_TOTAL, params[5]));
    	
    	try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpClient.execute(httppost);
			 str = inputStreamToString(response.getEntity().getContent()).toString();
			Log.i("hari",str);
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
		Log.i("POST",result);
		MainActivity.flag_billdata=1;
		BillView.resultDisplay(result);
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


}
