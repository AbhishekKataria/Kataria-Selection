package com.KS.katarias;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
	public static Activity myIs = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myIs = this;
        // Set View to register.xml
        setContentView(R.layout.register);
        
        Typeface TF = Typeface.createFromAsset(getAssets(), "fonts/Nautik-Bold.otf");
 
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        loginScreen.setTypeface(TF);
        loginScreen.setTextColor(getResources().getColor(R.color.black));
        
        TextView fname = (TextView) findViewById(R.id.fullname_label);
        fname.setTypeface(TF);
        fname.setTextSize(18);
        
        TextView email = (TextView) findViewById(R.id.email_reg_label);
        email.setTypeface(TF);
        email.setTextSize(18);
        
        TextView pass = (TextView) findViewById(R.id.pass_reg_label);
        pass.setTypeface(TF);
        pass.setTextSize(18);
        
 
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new OnClickListener() {
 
            public void onClick(View arg0) {
                                // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
        
        Button regbt = (Button) findViewById(R.id.btnRegister);	
        regbt.setTypeface(TF);
        regbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkFields())
				register();
				else
					displayError();
			}
		});
    }
    
    
    private boolean checkFields()
    {
    	EditText mail_txt = (EditText) myIs.findViewById(R.id.reg_email);
    	EditText na_txt = (EditText) myIs.findViewById(R.id.reg_fullname);
    	EditText pass_txt = (EditText) myIs.findViewById(R.id.reg_password);
    	
    	return((mail_txt != null)&&(android.util.Patterns.EMAIL_ADDRESS.matcher(mail_txt.getText().toString()).matches())
    	    &&(na_txt != null)&&(pass_txt!=null));
  	
    }
    
    private void displayError()
    {
    	Toast.makeText(getApplicationContext(), "Enter valid details", Toast.LENGTH_SHORT).show();
    }
    
    protected static void displayMessage(String result)
    {
    	EditText na_txt = (EditText) myIs.findViewById(R.id.reg_fullname);
    	Log.i("RESULT LENGTH",result.length()+"");
    	if(result.trim().equalsIgnoreCase("User registered"))
    	{
    		Toast.makeText(myIs.getApplicationContext(), "Welcome "+na_txt.getText().toString(), Toast.LENGTH_SHORT).show();
    		myIs.setTitle("Hii "+na_txt.getText().toString()+" !! Welcome to Katarias");
			LoginActivity.email_global = ((EditText)myIs.findViewById(R.id.reg_email)).getText().toString();
    	}
    	else
    	{
    		Toast.makeText(myIs.getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
    		LoginActivity.email_global = "false";
    	}
    }
    
    private void register(){
    	EditText username = (EditText) findViewById(R.id.reg_fullname);
		EditText email = (EditText) findViewById(R.id.reg_email);
		EditText pass = (EditText) findViewById(R.id.reg_password);
		Log.i("register","coming here");
		new Register().execute(username.getText().toString(),email.getText().toString(),pass.getText().toString());
	
    	
    	}
}
