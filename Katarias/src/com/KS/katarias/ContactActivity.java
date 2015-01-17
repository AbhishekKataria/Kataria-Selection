package com.KS.katarias;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends Activity {
	EditText sender,msg,sub;
	private final String TO_EMAIL[] = {"harishankar.gopalan@ymail.com"};
	public static Activity myIs = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		
		Typeface TF_BOLD = Typeface.createFromAsset(getAssets(), "fonts/Nautik-Bold.otf");
		
		TextView email = (TextView) findViewById(R.id.email_email);
		TextView subject = (TextView) findViewById(R.id.email_subject);
		TextView message = (TextView) findViewById(R.id.email_message);
		TextView address = (TextView) findViewById(R.id.address);

		
		
		email.setTypeface(TF_BOLD);
		email.setTextSize(18);

		subject.setTypeface(TF_BOLD);
		subject.setTextSize(18);
		
		message.setTypeface(TF_BOLD);
		message.setTextSize(18);
		
		address.setTypeface(TF_BOLD);
		address.setTextSize(18);
		
		
		sender = (EditText) findViewById(R.id.email);
		msg = (EditText) findViewById(R.id.message);
		sub = (EditText) findViewById(R.id.subject);

		myIs = this;
		ImageButton mapbutton = (ImageButton) findViewById(R.id.mapbutton);
		
		mapbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new  Intent(getApplicationContext() ,  MyMapActivity.class);
				startActivity(i);
			}
		});
		
		Button send_bt = (Button) findViewById(R.id.bt_sendmsg);
		send_bt.setTypeface(TF_BOLD);
		send_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "mail aaya", Toast.LENGTH_SHORT).show();
				sendEmail();
			}
		});
		
	}
	private void sendEmail(){
		
		Log.i("send","coming here");
		EditText email = (EditText) findViewById(R.id.email);
		EditText subject = (EditText) findViewById(R.id.subject);

		EditText message = (EditText) findViewById(R.id.message);
		Log.i("send","coming here");
		
		
		new ContactData().execute(email.getText().toString(),subject.getText().toString(),message.getText().toString());
	
	}
	protected static void displayResult(String result)
	{
		if(result.trim().equalsIgnoreCase("added"))
			Toast.makeText(myIs.getApplicationContext(), "Messgae sent,store will contact u asap!", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(myIs.getApplicationContext(), "Messgae not sent", Toast.LENGTH_SHORT).show();
	}
}