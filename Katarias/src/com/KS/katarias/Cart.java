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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Cart {
	private int cart_id,bill_id;
	private ArrayList<Product> products;
	private static String TAG_CART_ID = "cart_id";
	private static String TAG_BILL_ID = "bill_id";
	
	public Cart(){
		Log.i("constructor","construct");
		initialiseCartBillId();
		Log.i("constructor","after construct");
		products = new ArrayList<Product>();
	
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

	
	private void initialiseCartBillId(){
		
		JSONArray jArray = null;
		String str = null;
		HttpClient httpClient = new DefaultHttpClient();
		
		Log.i("constructor","inside initialise");
		
	    HttpPost httppost = new HttpPost("http://flogofury.gofreeserve.com/kataria_connect/retrieve_id.php");
	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);	    
    	
    	try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpClient.execute(httppost);
			 str = inputStreamToString(response.getEntity().getContent()).toString();
			 str='['+str+']';
			 Log.i("constructor",str);
			 
			 jArray = new JSONArray(str);
		     JSONObject json_data = null;
		     Log.i("array length",jArray.length()+"");
		     for(int i=0;i<jArray.length();i++){
		    	json_data = jArray.getJSONObject(i);
		    	cart_id = Integer.parseInt(json_data.getString(TAG_CART_ID))+1;
		    	bill_id = Integer.parseInt(json_data.getString(TAG_BILL_ID))+1;
		    }
			Log.i("hari",str);
    	} 
    	catch(JSONException je){
    		Log.i("JSONException",je.getMessage());
    	}
    	catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.i("Client protocol",e.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Log.i("unsupported",e.getMessage());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i("ioexception",e.getMessage());
		}	
	}
	
	public void addProduct(Product product)
	{
		products.add(product);
	}
	
	public void removeProduct(Product product)
	{
		products.remove(product);
	}
	
	public int getTotal()
	{
		int total = 0;
	    for(Product p:products)
	    	total+= p.getPrice();
	    return total;
	}
	
	public int getCartId()
	{
		return cart_id;
	}
	
	public int getBillId()
	{
		return bill_id;
	}	
	
	public Product searchProduct(String id)
	{
		for(Product p:products)
			if(p.getId().equalsIgnoreCase(id))
				return p;
		return null;
	}
	
	public Product getProduct(int index)
	{
		return products.get(index);
	}
	
	public int getNOProducts()
	{
		return products.size();
	}
	
	public void printDetails()
	{
		System.out.println("cart_id ="+cart_id+";bill_id ="+bill_id);
		for(Product p:products)
			System.out.println(p.getId());
		System.out.println(getTotal());
	}
}
