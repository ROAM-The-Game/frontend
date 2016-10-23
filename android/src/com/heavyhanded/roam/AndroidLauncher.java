package com.heavyhanded.roam;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.heavyhanded.roam.Game;
import com.heavyhanded.roam.map.Map;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		LocationListener locationListener = new LocationListener() {
			float MAP_WIDTH, MAP_HEIGHT;
			double oldLat = 0, oldLon = 0;
			public void onLocationChanged(Location location) {
				MAP_HEIGHT = MAP_WIDTH = Map.tileSize;
				//MAP_HEIGHT = MAP_WIDTH = 640;
				Game.initPosSet = true;
				// Called when a new location is found by the network location provider.
				//makeUseOfNewLocation(location);
				System.out.println("lat: " + location.getLatitude() + " lon: " + location.getLongitude());
				Game.lat = (float)location.getLatitude();
				Game.lon = (float)location.getLongitude();
				//double oldLat = 0, oldLon = 0;
				if(Game.player != null) {
					System.out.println(Map.tileSize);
					if(oldLon != location.getLongitude() && oldLon != 0) {
						Game.player.setX((float) ((256f/360f)*(location.getLongitude()-oldLon)*Math.pow(2, 18)) + Game.player.getX());
					}
					if(oldLat != location.getLatitude() && oldLat != 0) {
						Game.player.setY((float) ((256f/360f)*(location.getLatitude()-oldLat)*Math.pow(2, 18)) + Game.player.getY());
					}

					//Game.player.setX((float) ((256f/360f)*location.getLongitude()*Math.pow(2, 18)) );
					//Game.player.setY((float) ((256f/360f)*location.getLatitude()*Math.pow(2, 18)) );
					//Adjust(location.getLatitude()*Math.PI/180, location.getLongitude(), 0, 0, 18);
					oldLat = location.getLatitude();
					oldLon = location.getLongitude();
					Toast.makeText(getContext(), "x: " + Game.player.getX() + " y: " + Game.player.getY(), Toast.LENGTH_SHORT).show();
				}
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {}

			public void onProviderEnabled(String provider) {}

			public void onProviderDisabled(String provider) {}
		};
		Criteria locCrit = new Criteria();
		locCrit.setAccuracy(Criteria.ACCURACY_FINE);
		//locationManager.getBestProvider(locCrit, true)
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Game(), config);
	}

}
