package com.heavyhanded.roam.networking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.Map;

import Entities.Player;

public class Online {
	
	static boolean connectionAlive;
	static String base = "http://httpbin.org/post";
	static String JWT;
	static JsonReader reader = new JsonReader();
	public static void authUser(String username, String password) {
		HttpRequest httpRequest = new HttpRequest(Net.HttpMethods.POST);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", username);
		parameters.put("password", password);
		httpRequest.setUrl(base + "/login");
		httpRequest.setContent(HttpParametersUtils.convertHttpParameters(parameters));
		Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {

			@Override
			public void handleHttpResponse(Net.HttpResponse httpResponse) {
				final int statusCode = httpResponse.getStatus().getStatusCode();
				System.out.println(statusCode);
				if (statusCode !=200) return;

				final byte[] data = httpResponse.getResult();
				Gdx.app.postRunnable(new Runnable() {
					public void run () {
						String jsonString = new String(data);
						JsonValue val = reader.parse(jsonString);
						//System.out.println(val.get("form"));
						JWT = val.get("form").get("jwt_token").asString();
						System.out.println("The data: " + jsonString);
					}
				});
			}

			@Override
			public void failed(Throwable t) {
				System.out.println("Login Error!!! " + t.getMessage());
			}

			@Override
			public void cancelled() {

			}
		});
	}
	
	public static void loadPlayer(String username, Player player) {
		HttpRequest httpRequest = new HttpRequest(Net.HttpMethods.GET);
		httpRequest.setHeader("Authorization", "Bearer " + JWT);
		//Map<String, String> parameters = new HashMap<String, String>();
		//parameters.put("username", username);
		//httpRequest.setUrl(base + "/login");
		//httpRequest.setContent(HttpParametersUtils.convertHttpParameters(parameters));
		Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {

			@Override
			public void handleHttpResponse(Net.HttpResponse httpResponse) {
				final int statusCode = httpResponse.getStatus().getStatusCode();
				System.out.println(statusCode);
				if (statusCode !=200) return;

				final byte[] data = httpResponse.getResult();
				Gdx.app.postRunnable(new Runnable() {
					public void run () {
						String jsonString = new String(data);
						JsonValue val = reader.parse(jsonString);
						//System.out.println(val.get("form"));
						System.out.println("player data: " + jsonString);
					}
				});
			}

			@Override
			public void failed(Throwable t) {
				System.out.println("Login Error!!! " + t.getMessage());
			}

			@Override
			public void cancelled() {

			}
		});
	}
	
	public static void getMapTile(float lat, float lon) {
		
	}
	
	public static void hackConnect() {
		connectionAlive = true;
		new Thread() {
			public void run() {
				Json json;
				//JsonValue val;
				while(connectionAlive) {
					try {
						HttpRequest httpRequest = new HttpRequest(Net.HttpMethods.POST);
						httpRequest.setHeader("Authorization", "Bearer " + JWT);
						Map<String, String> parameters = new HashMap<String, String>();
						//parameters.put("x", "y")
						httpRequest.setUrl(base + "/maprender");
						httpRequest.setContent(HttpParametersUtils.convertHttpParameters(parameters));
						Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {

							@Override
							public void handleHttpResponse(Net.HttpResponse httpResponse) {
								System.out.println("handle resp");
								final int statusCode = httpResponse.getStatus().getStatusCode();
								System.out.println(statusCode);
								if (statusCode !=200) return;

								final byte[] data = httpResponse.getResult();
								Gdx.app.postRunnable(new Runnable() {
									public void run () {
										String jsonString = new String(data);
										JsonValue val = reader.parse(jsonString);
										System.out.println(val.get("form"));
										//System.out.println("The data: " + jsonString);
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
						Thread.sleep(1000);//16
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	static void attackEnemy() {

	}

	static void heal() {

	}
	
	static void update(JsonValue val) {
		
	}
	
	

}
