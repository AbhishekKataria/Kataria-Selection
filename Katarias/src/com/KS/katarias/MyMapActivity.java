package com.KS.katarias;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MyMapActivity extends MapActivity {

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.maplayout);
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    MapController mc = mapView.getController();
	    Log.i("MAP","got controller");
	    mapView.setBuiltInZoomControls(true);
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.marker);
	    HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable, this);
	    float lat = 13.046844f;
	    float lng = 80.254875f;
	    GeoPoint point = new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6));
	    mc.setCenter(point);
	    mc.setZoom(18);
	    OverlayItem overlayitem = new OverlayItem(point, "Hi guest !!", "This is kataria's selection");
	    itemizedoverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedoverlay);
	}
	

}
