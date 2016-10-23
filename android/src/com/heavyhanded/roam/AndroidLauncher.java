package com.heavyhanded.roam;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
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
			public void onLocationChanged(Location location) {
				MAP_HEIGHT = MAP_WIDTH = Map.tileSize;
				//MAP_HEIGHT = MAP_WIDTH = 640;
				Game.initPosSet = true;
				// Called when a new location is found by the network location provider.
				//makeUseOfNewLocation(location);
				System.out.println("lat: " + location.getLatitude() + " lon: " + location.getLongitude());
				Game.lat = (float)location.getLatitude();
				Game.lon = (float)location.getLongitude();
				if(Game.player != null) {
					System.out.println(Map.tileSize);
					System.out.println("x: " + (MAP_WIDTH/360.0) * (180 + location.getLatitude()) + " y: " + (MAP_HEIGHT/180.0) * (90 - location.getLongitude()));
					//Game.player.setX((float) ((MAP_WIDTH/360.0) * (180 + location.getLatitude())) - MAP_WIDTH/2);
					Game.player.setX((float) ((location.getLongitude()+180)*(MAP_WIDTH/360)) - MAP_WIDTH/4 - Game.player.getTexture().getWidth()/2);// Game.player.getTexture().getWidth()
					//Game.player.setY((float) (((1 - Math.log(Math.tan(location.getLatitude() * Math.PI / 180) + 1 / Math.cos(location.getLatitude() * Math.PI / 180)) / Math.PI) / 2 * Math.pow(2, 0)) * MAP_HEIGHT/4)
							//- Game.player.getTexture().getHeight());
					double latRad = location.getLatitude()*Math.PI/180;
					double mercN = Math.log10(Math.abs(Math.tan((Math.PI/4)+latRad*2)) );
					//System.out.println("tan " + Math.abs(Math.tan((Math.PI/4)+latRad*2)) );
					//System.out.println("log10 " +Math.log10(Math.abs(Math.tan((Math.PI/4)+latRad*2))) );
					//System.out.println("nan" + Math.abs(Math.log10(Math.tan((Math.PI/4)+latRad*2))));
					//Game.player.setY((float)((MAP_HEIGHT/2)-(MAP_WIDTH*mercN/(2*Math.PI))) - MAP_HEIGHT/4 - Game.player.getTexture().getHeight()/2);

					Adjust(location.getLatitude()*Math.PI/180, location.getLongitude(), 0, 0, 18);
					Toast.makeText(getContext(), "x: " + Game.player.getX() + " y: " + Game.player.getY(), Toast.LENGTH_SHORT).show();
				}
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {}

			public void onProviderEnabled(String provider) {}

			public void onProviderDisabled(String provider) {}
		};
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Game(), config);
	}

	static double offset = 268435456;
	static double radius = offset / Math.PI;
	// X,Y ... location in degrees
	// xcenter,ycenter ... center of the map in degrees (same value as in
	// the google static maps URL)
	// zoomlevel (same value as in the google static maps URL)
	// xr, yr and the returned Point ... position of X,Y in pixels relativ
	// to the center of the bitmap
	static Point Adjust(double X, double Y, double xcenter, double ycenter,
						int zoomlevel)
	{
		int xr = (LToX(X) - LToX(xcenter)) >> (21 - zoomlevel);
		int yr = (LToY(Y) - LToY(ycenter)) >> (21 - zoomlevel);
		Game.player.setX(xr);
		Game.player.setY(yr);
		Point p = new Point(xr, yr);
		return p;
	}

	static int LToX(double x)
	{
		return (int)(Math.round(offset + radius * x * Math.PI / 180));
	}

	static int LToY(double y)
	{
		return (int)(Math.round(offset - radius * Math.log((1 +
				Math.sin(y * Math.PI / 180)) / (1 - Math.sin(y *
				Math.PI / 180))) / 2));
	}
}
