package com.heavyhanded.roam.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	
	Texture texture;
	String key = "AIzaSyCssDPParnc2DGjOOXvXIPMjeveSe31Vns";
	String base = "https://maps.googleapis.com/maps/api/staticmap?";
	public Map() {
		HttpRequest httpRequest = new HttpRequest(Net.HttpMethods.GET);
		httpRequest.setUrl(base + "key=" + key + "&center=" + "25.766790,-80.207033"
		+ "&size=" + "640x640" + "&zoom=18");
		 Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {
             
             @Override
             public void handleHttpResponse(HttpResponse httpResponse) {
                final int statusCode = httpResponse.getStatus().getStatusCode();
                if (statusCode !=200) return;
                
                final byte[] rawImageBytes = httpResponse.getResult();
                Gdx.app.postRunnable(new Runnable() {
                   public void run () {
                      //Texture.setEnforcePotImages(false);
                      Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
                      //entry.profilePictureTexture = new Texture(pixmap);
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
	
	public void render(SpriteBatch batch) {
		if(texture != null)
			batch.draw(texture, 0, 0, 400, 400);
	}

}
