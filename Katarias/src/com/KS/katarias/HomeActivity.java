package com.KS.katarias;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.home);
		TextView name = (TextView) findViewById(R.id.homename);
		name.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/BremenBoldBT.ttf"));
		name.setTextSize(20);
		
		TextView desc = (TextView) findViewById(R.id.homedesc);
		desc.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Nautik-Bold.otf"));
		desc.setTextSize(18);

		ImageButton login = (ImageButton) findViewById(R.id.login_bt);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new  Intent(getApplicationContext() ,LoginActivity.class);
				startActivity(i);
			}
		});
		
		
		ImageButton register = (ImageButton) findViewById(R.id.register_bt);
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
			}
		});
		
	}
}
