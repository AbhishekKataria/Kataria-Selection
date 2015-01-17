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

public class Purchase extends AsyncTask <Cart,Void,String> {
	private static String TAG_CART_ID = "id";
	private static String TAG_PRODUCTS ="products";
	protected static String res_count = "0";

	@Override
	protected String doInBackground(Cart... params) {
		
		String str = null;
	    HttpClient httpClient = new DefaultHttpClient();
		
	    HttpPost httppost = new HttpPost("http://flogofury.gofreeserve.com/kataria_connect/cartdata.php");
	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
    		    
    	nameValuePairs.add(new BasicNameValuePair(TAG_CART_ID, params[0].getCartId()+""));
    	String prod = convertProductsID(params[0]);
    	Log.i("PRODUCT ID",prod);
    	nameValuePairs.add(new BasicNameValuePair(TAG_PRODUCTS, prod));
    	
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
		// TODO Auto-generated method stub
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
		res_count=result;
		MainActivity.flag_cartdata=1;
		
	}
	private String convertProductsID(Cart obj)
	{
		String str = "";
		for(int i=0;i<obj.getNOProducts();i++)
			str=str+obj.getProduct(i).getId()+",";
	    return str.substring(0, str.length()-1);
	}

}
