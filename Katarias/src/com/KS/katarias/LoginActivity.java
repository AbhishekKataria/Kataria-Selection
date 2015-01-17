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
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	public static Activity myIs = null;
	public static String email_global = "false";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
         Log.i("class name",this.getClass().getName());
		 myIs = this;
		   Typeface TF = Typeface.createFromAsset(getAssets(), "fonts/Nautik-Bold.otf");

			TextView email = (TextView) findViewById(R.id.email_reg_label);
			email.setTypeface(TF);
			email.setTextSize(18);
			
			TextView pass = (TextView) findViewById(R.id.pass_reg_label);
			pass.setTypeface(TF);
			pass.setTextSize(18);
		 
            TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
            registerScreen.setTypeface(TF);
            registerScreen.setTextColor(getResources().getColor(R.color.black));
 
        // Listening to register new account link
        Log.i("hari", "before onClick login activity");
        registerScreen.setOnClickListener(new OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
        
        Log.i("hari", "after onClick login activity");
        Button loginbt = (Button) findViewById(R.id.bt_login);
        loginbt.setTypeface(TF);
        loginbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					authenticate();		
		}
	});	
	}
	
	private void authenticate()
	{
		Log.i("authenticate","coming here");
		EditText email = (EditText) findViewById(R.id.email_txt);
		EditText pass = (EditText) findViewById(R.id.password_txt);
		Log.i("authenticate","coming here");
		new LoginAuthenticator().execute(email.getText().toString(),pass.getText().toString());
	}
	
	public static void setLoginDetails(String result)
	{
		if(result.equalsIgnoreCase("false")){
			Toast.makeText(myIs.getApplicationContext(), "Invalid login credentials", Toast.LENGTH_SHORT).show();
			myIs.setTitle("Kataria's");
			email_global = result;
		}
		else{
			Toast.makeText(myIs.getApplicationContext(), "Hii "+result+"!! :)" , Toast.LENGTH_SHORT).show();
			myIs.setTitle("Hii "+result+" !! Welcome to Katarias");
			email_global = ((EditText)myIs.findViewById(R.id.email_txt)).getText().toString();
		}
		Log.i("email set",email_global);
	}
}
