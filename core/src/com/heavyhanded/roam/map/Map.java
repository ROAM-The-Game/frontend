package com.heavyhanded.roam.map;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Map extends Actor {
	
	Texture texture;
	String key = "AIzaSyCssDPParnc2DGjOOXvXIPMjeveSe31Vns";
	String base = "https://maps.googleapis.com/maps/api/staticmap?";
	ArrayList<MapTile> tiles;
	public Map() {
		tiles = new ArrayList<MapTile>();
		MapTile tile = new MapTile(25.7785716f, -80.189455f, 0, 0);
		tiles.add(tile);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		for(MapTile tile : tiles) {
			if(tile.texture != null)
				batch.draw(tile.texture, tile.centerX-tile.width/2, tile.centerY-tile.height/2, tile.width, tile.height);
		}
	}

}
