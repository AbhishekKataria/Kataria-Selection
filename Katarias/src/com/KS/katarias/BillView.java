package com.KS.katarias;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class BillView extends Activity {
	
	public static Activity myIs = null;
	
	Typeface fontbold = null;
	
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub

		super.onCreate(bundle);
		setContentView(R.layout.bill_view);
		fontbold = Typeface.createFromAsset(getAssets(), "fonts/Nautik-Bold.otf");
		myIs = this;

		
		TextView sno = (TextView) findViewById(R.id.billview_sno);
		sno.setTypeface(fontbold);
		sno.setTextSize(22);
		
		TextView item = (TextView) findViewById(R.id.billview_item);
		item.setTypeface(fontbold);
		item.setTextSize(22);
		
		TextView price = (TextView) findViewById(R.id.billview_price);
		price.setTypeface(fontbold);
		price.setTextSize(22);
		
		TableLayout tl = (TableLayout) findViewById(R.id.table_layout);
		
		TableLayout.LayoutParams llr = new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		llr.setMargins(2, 2, 2, 2); 
		

		LinearLayout.LayoutParams llc = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		llc.setMargins(0, 0, 2, 0); 
		
		for(int i=0;i<MainActivity.cartObj.getNOProducts();i++)
		{
			Product p = MainActivity.cartObj.getProduct(i);
			TableRow tb = new TableRow(this);
		    tb.setBackgroundColor(getResources().getColor(R.color.black));
			tb.setLayoutParams(llr);
 
			addColumn(tb,i+1+"",llc);
			addColumn(tb, p.getId(),llc);
			addColumn(tb,p.getPrice()+"",llc);
		  	   			
		    tl.addView(tb);
		}		
			
	    TableRow tb = new TableRow(this);
	    tb.setBackgroundColor(getResources().getColor(R.color.black));
		tb.setLayoutParams(llr);
	    addColumn(tb,"Bill No:"+MainActivity.cartObj.getBillId(),llc);
	    addColumn(tb,"Total:"+getResources().getString(R.string.Rs),llc);
	    addColumn(tb,MainActivity.cartObj.getTotal()+"",llc);   
	    tl.addView(tb);
	 
	    Button purchase_bt = (Button) findViewById(R.id.bt_purchase);
	    purchase_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
					authenticate();
			}
		});
	}
	
	private void authenticate()
	{
		EditText address = (EditText) findViewById(R.id.bill_address);
		EditText phone   = (EditText) findViewById(R.id.bill_phone);
		
		if(address.getText().toString().trim().equalsIgnoreCase(""))
			Toast.makeText(getApplicationContext(), "Please enter address for delivery", Toast.LENGTH_SHORT).show();
		else if(phone.getText().toString().length()!=10)
			Toast.makeText(getApplicationContext(), "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
		else
	    {
			new Purchase().execute(MainActivity.cartObj);
			new BillData().execute(MainActivity.cartObj.getBillId()+"",MainActivity.cartObj.getCartId()+"",LoginActivity.email_global,
					               address.getText().toString(),phone.getText().toString(),MainActivity.cartObj.getTotal()+""
					               );
			
	    }
	}
	
	private void addColumn(TableRow tb,String text,LinearLayout.LayoutParams llc)
	{
		TextView temp = new TextView(this);
		temp.setBackgroundColor(getResources().getColor(R.color.white));
		temp.setPadding(30,10,20,10);
		temp.setTypeface(fontbold);
		temp.setTextSize(16);
		temp.setText(text);
		tb.addView(temp);
	}
	
	public static void resultDisplay(String str)
	{
		while(true)
		{
			if(MainActivity.flag_billdata==1 && MainActivity.flag_cartdata==1)
				break;
		}
			if(str.trim().equalsIgnoreCase("false") || Purchase.res_count.trim().equalsIgnoreCase("0"))
			Toast.makeText(myIs.getApplicationContext(), "Request not processed..please try again", Toast.LENGTH_SHORT).show();
		else
			{
			Toast.makeText(myIs.getApplicationContext(), "Purchase done", Toast.LENGTH_SHORT).show();
			}
	  
	}

}
