package com.heavyhanded.roam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heavyhanded.roam.map.Map;
import com.heavyhanded.roam.networking.Online;

import Entities.Player;
import States.StateManager;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Map map;
	public static Player player;
	StateManager stateMgr;
	public static float lat, lon;
	public static boolean initPosSet = false;
	@Override
	public void create () {
		player = new Player();
		batch = new SpriteBatch();
		img = new Texture("player/Fallout_Survivor.png");
		stateMgr = new StateManager();
		Online.hackConnect();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stateMgr.render(Gdx.graphics.getDeltaTime());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
