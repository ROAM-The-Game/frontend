package com.heavyhanded.roam.networking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import Entities.Player;

public class Online {
	
	static boolean connectionAlive;
	static String base;
	
	public static void authUser(String username, String password) {
		
	}
	
	public static void loadPlayer(String username, Player player) {
		
	}
	
	public static void getMapTile(float lat, float lon) {
		
	}
	
	public static void hackConnect() {
		connectionAlive = true;
		new Thread() {
			public void run() {
				Json json;
				JsonValue val;
				JsonReader reader = new JsonReader();
				while(connectionAlive) {
					try {
						HttpRequest httpRequest = new HttpRequest(Net.HttpMethods.POST);
						httpRequest.setUrl(base);
						Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {

							@Override
							public void handleHttpResponse(Net.HttpResponse httpResponse) {
								System.out.println("handle resp");
								final int statusCode = httpResponse.getStatus().getStatusCode();
								System.out.println(statusCode);
								if (statusCode !=200) return;

								final byte[] rawImageBytes = httpResponse.getResult();
								Gdx.app.postRunnable(new Runnable() {
									public void run () {
										Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
									}
								});
							}

							@Override
							public void failed(Throwable t) {
								System.out.println(t.getMessage());
							}

							public void handleHttpResponse1(Net.HttpResponse httpResponse) {
								// TODO Auto-generated method stub

							}

							@Override
							public void cancelled() {
								// TODO Auto-generated method stub

							}
						});
						Thread.sleep(16);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	static void update(JsonValue val) {
		
	}
	
	

}
