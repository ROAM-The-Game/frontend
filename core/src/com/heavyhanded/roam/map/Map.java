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
import com.heavyhanded.roam.Game;

public class Map extends Actor {
	
	Texture texture;
	String key = "AIzaSyCssDPParnc2DGjOOXvXIPMjeveSe31Vns";
	String base = "https://maps.googleapis.com/maps/api/staticmap?";
	ArrayList<MapTile> tiles;
	Texture loading = new Texture("gui/loginbg.png");
	public static float tileSize;
	public Map() {
		float screenSize = Math.max(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		tileSize = screenSize;

		tiles = new ArrayList<MapTile>();
	}

	public void init() {
		System.out.println("Make first map");
		MapTile tile = new MapTile(Game.lat, Game.lon, 0, 0); // 25.7785716f, -80.189455f
		tiles.add(tile);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		for(MapTile tile : tiles) {
			if(tile.texture != null)
				batch.draw(tile.texture, tile.centerX-tile.width/2, tile.centerY-tile.height/2, tile.width, tile.height);
			//if(tile.texture != null)
				//System.out.println(tile.texture.getWidth());
		}
		if(tiles.size() == 0) {
			batch.draw(loading, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
		//System.out.println(tiles.get(0));
	}

}
