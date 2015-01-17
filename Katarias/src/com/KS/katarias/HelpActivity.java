package com.KS.katarias;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		
		TextView tv = (TextView) findViewById(R.id.helplabel);
		tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Nautik-Bold.otf"));
		tv.setTextSize(20);

	}
	
}
