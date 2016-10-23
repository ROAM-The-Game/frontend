package com.heavyhanded.roam.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Building extends Actor {
	
	Texture texture;
	
	public Building(float x, float y) {
	
	}
	
	@Override
	public void act(float delta) {
		setBounds(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
	}

}
