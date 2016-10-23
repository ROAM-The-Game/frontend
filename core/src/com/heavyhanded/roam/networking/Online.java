package com.heavyhanded.roam.networking;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
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
