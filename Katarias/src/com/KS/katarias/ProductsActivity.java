package com.KS.katarias;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DecodingType;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.FailReason;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoadingListener;

public class ProductsActivity extends Activity {

	public ImageLoader imageLoader = ImageLoader.getInstance();
	private ViewPager gallery;
	private int currentPage = 0;
	private FrameLayout layouts[];
	Typeface TF =null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("NEW","coming here");
		super.onCreate(savedInstanceState);
		TF = Typeface.createFromAsset(getAssets(), "fonts/Nautik-Bold.otf");
		Log.i("NEW","coming here");
		setContentView(R.layout.products);
		Log.i("hari", "first1");
		String[] imageUrls = getResources().getStringArray(R.array.images);
		layouts = new FrameLayout[imageUrls.length];
		
		gallery = (ViewPager) findViewById(R.id.pager);
		gallery.setAdapter(new ImagePagerAdapter(imageUrls));
		gallery.setCurrentItem(0);
		gallery.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int index) {
				// TODO Auto-generated method stub
				currentPage = index;
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		Log.i("hari", "second");
	}
	
	private class ImagePagerAdapter extends PagerAdapter{
		
		private String[] images;
		private LayoutInflater inflater;
		
		public ImagePagerAdapter(String[] images) {
			// TODO Auto-generated constructor stub
			this.images = images;
			inflater = getLayoutInflater();
		}
		
		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager)container).removeView((View) object);
		}
		
		@Override
		public void finishUpdate(View container) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return images.length;
		}
		
		@Override
		public Object instantiateItem(View view, int position) {
			// TODO Auto-generated method stub
			final FrameLayout imageLayout = (FrameLayout) inflater.inflate(R.layout.item_gallery_image, null);
			final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
			final ProgressBar loading = (ProgressBar) imageLayout.findViewById(R.id.loading);
			
			layouts[position]=imageLayout;
			
			Log.i("hari",images[position]+"");
			
			final TextView id = (TextView) imageLayout.findViewById(R.id.prod_desc_id);
			final TextView desc = (TextView) imageLayout.findViewById(R.id.desc_txt);
			final TextView price = (TextView) imageLayout.findViewById(R.id.prod_price_id);
			
			id.setTypeface(TF);
			desc.setTypeface(TF);
			price.setTypeface(TF);
			
			id.setText(convertURLtoId(images[position]));
			
			
			
			//new ProductsDescription().execute(id.getText().toString());
			
			DisplayImageOptions options = new DisplayImageOptions.Builder()
			       .cacheInMemory()
			       .cacheOnDisc()
			       .decodingType(DecodingType.MEMORY_SAVING)
			       .build();
			
			imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
			
			imageLoader.displayImage(images[position], imageView, options, new ImageLoadingListener() {
				
				@Override
				public void onLoadingStarted() {
					// TODO Auto-generated method stub
					loading.setVisibility(View.VISIBLE);
					Product p = MainActivity.prod_data.getProductWithId(id.getText().toString().trim());
					if(p==null)
						Log.i("P","p is null");
					else
					{desc.setText(p.getDescription());
				    	price.setText(String.valueOf(p.getPrice()));
					}
				}
				
				@Override
				public void onLoadingFailed(FailReason failReason) {
					// TODO Auto-generated method stub
					loading.setVisibility(View.GONE);
					imageView.setImageResource(android.R.drawable.ic_delete);
					
					switch(failReason){
					case MEMORY_OVERFLOW:
						    imageLoader.clearMemoryCache();
						    break;
					}
				}
				
				@Override
				public void onLoadingComplete() {
					// TODO Auto-generated method stub
					loading.setVisibility(View.GONE);
					
				}
			});
			
			((ViewPager)view).addView(imageLayout, 0);
			return imageLayout;
			
		}
		 
		
		private String convertURLtoId(String url)
		{
			return url.substring(url.lastIndexOf('/')+1,url.lastIndexOf('.'));
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view.equals(object);
		}
		
		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
	        return null;
		}
		
		@Override
		public void startUpdate(View container) {
			// TODO Auto-generated method stub
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.products, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		TextView p_id = (TextView) layouts[currentPage].findViewById(R.id.prod_desc_id);
		TextView p_pri = (TextView) layouts[currentPage].findViewById(R.id.prod_price_id);
		Product p;
		switch(item.getItemId()){
		   
		  case R.id.addToCart: 
			  if(LoginActivity.email_global.equalsIgnoreCase("false"))
				   Toast.makeText(getApplicationContext(), "Please login to add products to cart", Toast.LENGTH_SHORT).show();
			  else
			  {    
		      MainActivity.cartObj.addProduct(new Product(p_id.getText().toString(),
						  									Integer.parseInt(p_pri.getText().toString())
					                    	              )
		                                     );
			  Toast.makeText(getApplicationContext(), "cart made", Toast.LENGTH_SHORT).show();
			  }
			  return true;
		  case R.id.removeProduct:
			  //p_id = (TextView)myIs.findViewById(R.id.prod_desc_id);
			  if((p=MainActivity.cartObj.searchProduct(p_id.getText().toString()))!=null)
			  {
				  MainActivity.cartObj.removeProduct(p);
				  Toast.makeText(getApplicationContext(), "Product removed..!!", Toast.LENGTH_SHORT).show();
			  }
			  else Toast.makeText(getApplicationContext(), "Product not yet added to cart.!!", Toast.LENGTH_SHORT).show();
			  return true;
		  case R.id.generateBill:
			  if(MainActivity.cartObj.getNOProducts()>0)
			  {
				  Toast.makeText(getApplicationContext(), "bill made", Toast.LENGTH_SHORT).show();
				  Intent myI = new Intent(getApplicationContext(), BillView.class);
				  startActivity(myI);
			  }
			  else
				  Toast.makeText(getApplicationContext(), "Please add products to cart", Toast.LENGTH_SHORT).show();
			  //MainActivity.cartObj.printDetails();
			  return true;
		  default:
			  return super.onOptionsItemSelected(item);	  
		}
	}

}
