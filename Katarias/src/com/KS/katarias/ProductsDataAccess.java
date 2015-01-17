package com.KS.katarias;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ProductsDataAccess {
	public ArrayList<Product> products = new ArrayList<Product>();
	private final String TAG_ID = "id";
	private final String TAG_DESC = "description";
	private final String TAG_PRICE = "price";
	
	public void getDetails()
	{
		JSONArray jArray = null;
		String str = "",desc = null,id = null;
		int price = 0;
		HttpClient httpClient = new DefaultHttpClient();
		
	    HttpPost httppost = new HttpPost("http://flogofury.gofreeserve.com/kataria_connect/product_details.php");	    
    	
    	try {
    		
    		
			 HttpResponse response = httpClient.execute(httppost);
			 str = inputStreamToString(response.getEntity().getContent()).toString();
			 //str='['+str+']';
			 Log.i("hari",str);
			 jArray = new JSONArray(str);
		     JSONObject json_data = null;
		     Log.i("array length",jArray.length()+"");
		     for(int i=0;i<jArray.length();i++){
		    	//jArray1 = jArray.getJSONArray(i);
		    	json_data = jArray.getJSONObject(i);
		    	id = json_data.getString(TAG_ID);
		    	desc = json_data.getString(TAG_DESC);
		    	price = json_data.getInt(TAG_PRICE);
		    	products.add(new Product(id, price, desc));
		    }
		Log.i("hari",str);
    	}
    	
    	catch(JSONException je){
    		Log.i("JSONException",je.getMessage());
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
	}
	
	private StringBuilder inputStreamToString(InputStream is){
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

	
	public Product getProductWithId(String id)
	{
		for(Product p:products)
			if(p.getId().equalsIgnoreCase(id))
				return p;
		return null;
	}
	
	public void printDetails()
	{
		for(Product p:products)
			System.out.println(p.getId());
	}
}
