package com.KS.katarias;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	
    public static ProductsDataAccess prod_data;
	public static Cart cartObj;
	public static int flag_cartdata,flag_billdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		flag_cartdata=0;
		flag_billdata=0;
		Runnable obj=new Runnable() {
		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i("CART OBJ","coming here");
				cartObj = new Cart();
				Log.i("CART OBJ","coming here");
				prod_data = new ProductsDataAccess();
				prod_data.getDetails();	
	            prod_data.printDetails();
			}
		};
		
		Thread t = new Thread(obj);
		t.start();

		TabHost th = getTabHost();
		th.setup();
	
		th.addTab(th.newTabSpec("home").setIndicator("HOME").setContent(new Intent(this, HomeActivity.class)));
		Log.i("hari","coming here");
		th.addTab(th.newTabSpec("products").setIndicator("PRODUCTS").setContent(new Intent(this, ProductsActivity.class)));
		th.addTab(th.newTabSpec("contact").setIndicator("CONTACT").setContent(new Intent(this, ContactActivity.class)));
		//th.addTab(th.newTabSpec("help").setIndicator("HELP").setContent(new Intent(this, HelpActivity.class)));
		
		th.setCurrentTab(0);
		
		TabWidget tw = getTabWidget();
		tw.setVisibility(View.VISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	switch (item.getItemId()) 
	{
	case R.id.menu_help:
				Intent myI = new Intent(getApplicationContext(), HelpActivity.class);
		  startActivity(myI);
		  return true;
	default :return true;
	}
	
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
