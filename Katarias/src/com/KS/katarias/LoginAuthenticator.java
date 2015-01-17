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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class LoginAuthenticator extends AsyncTask<String, Void, String> {
	private static String TAG_UNAME   = "username";
	private static String TAG_PASS    = "password";
	private static String TAG_EMAIL   = "email";
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
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.i("hari", "do in bacgorund");
		HttpClient httpClient = new DefaultHttpClient();
	    JSONArray jArray = null;
	    String user = null;
	    
	    HttpPost httppost = new HttpPost("http://flogofury.gofreeserve.com/kataria_connect/login_new.php");
	    Log.i("hari", "do in bacgorund");
	    try{
	    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	    	nameValuePairs.add(new BasicNameValuePair(TAG_EMAIL, params[0]));
	    	nameValuePairs.add(new BasicNameValuePair(TAG_PASS, params[1]));
	    	httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    	Log.i("hari", "inside try");
	    	HttpResponse response = httpClient.execute(httppost);
	    	Log.i("hari","Hey coming here");
	    	String str = inputStreamToString(response.getEntity().getContent()).toString();
	    	str = '['+str+']';
	       	
	    	Log.i("hari",str);
	    	
	    	jArray = new JSONArray(str);
	    	JSONObject json_data = null;
	    	Log.i("array length",jArray.length()+"");
	    	for(int i=0;i<jArray.length();i++){
	    		json_data = jArray.getJSONObject(i);
	    		user = json_data.getString(TAG_UNAME);
	    	}
	    	/*if(email.equalsIgnoreCase(params[0]) && pass.equalsIgnoreCase(params[1]))
	    		isLoggedIn = true;
	    	else
	    		isLoggedIn = false;*/
	    	
	    		
	    	Log.i("hari","Hey coming here after json loop");
	    }
	    catch(JSONException je)
	    {
	    	Log.i("JSON ERROR",je.getMessage());
	    }
	    catch(ClientProtocolException e){
	    	Log.i("CLIENT PROTO", e.getMessage());
	    }
	    catch(IOException e){
	    	Log.i("IOException", e.getMessage());
	    }
	    Log.i("success","coming here");
	    return user;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(result.equalsIgnoreCase("false"))
			Log.i("async complete",result);
		else
			Log.i("async complete",result);
		LoginActivity.setLoginDetails(result);
  }

}
