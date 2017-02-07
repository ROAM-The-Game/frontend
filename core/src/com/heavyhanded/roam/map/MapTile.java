package com.heavyhanded.roam.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class MapTile {
	
	float centerX, centerY, width, height;
	Texture texture;
	String key = "AIzaSyCssDPParnc2DGjOOXvXIPMjeveSe31Vns";
	String base = "https://maps.googleapis.com/maps/api/staticmap?";
	public MapTile(float lat, float lon, float cx, float cy) {
		centerX = cx;
		centerY = cy;
		width = height = Map.tileSize;
		HttpRequest httpRequest = new HttpRequest(Net.HttpMethods.GET);
		//httpRequest.setUrl(base + "key=" + key + "&center=" + lat + "," + lon
		//+ "&size=" + "640x640" + "&zoom=18");

		httpRequest.setUrl(base + "key=" + key + "&center=" + lat + "," + lon
				+ "&zoom=16&size=640x640&maptype=terrain&style=feature:landscape.man_made|color:0x222211&style=feature:all|element:labels|visibility:off&style=feature:road|color:0x654321&style=feature:poi.business|color:0x186842&style=feature:poi.attraction|color:0xff0000&style=feature:poi.government|color:0x00ff00&style=feature:poi.medical|color:0x0000ff&style=feature:poi.park|color:0x000000&style=feature:poi.place_of_worship|color:0x631355&style=feature:poi.school|color:0x584788&style=feature:poi.sports_complex|color:0x797923");

		 Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {
             
             @Override
             public void handleHttpResponse(HttpResponse httpResponse) {
            	System.out.println("handle resp");
                final int statusCode = httpResponse.getStatus().getStatusCode();
                System.out.println(statusCode);
                if (statusCode !=200) return;
                
                final byte[] rawImageBytes = httpResponse.getResult();
                Gdx.app.postRunnable(new Runnable() {
                   public void run () {
                      Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
                      texture = new Texture(pixmap);
                   }
                });
             }
             
             @Override
             public void failed(Throwable t) {
                System.out.println(t.getMessage());
             }

			public void handleHttpResponse1(HttpResponse httpResponse) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void cancelled() {
				// TODO Auto-generated method stub
				
			}
          });
	}

}
