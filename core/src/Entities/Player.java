package Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Player extends Entity {
	
	public Class playerClass;
	
	public Player() {
		playerClass = Class.Technician;
		texture = new Texture("player/Fallout_Survivor.png");
		speed = 5;
		health = maxHealth = 20;
		isAlive = true;
		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pix.drawPixel(0, 0, Color.argb8888(0, 0, 255, 1));
		healthBar = new Texture(pix);
		pix.dispose();
		setWidth(texture.getWidth());
		setHeight(texture.getHeight());
	}
	
	public Player(Class pclass) {
		this();
		this.playerClass = pclass;
	}
	
	public enum Class {
		Medic,
		Technician,
		Trooper
	}
	
	@Override
	public void act(float delta) {
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		//switch(playerClass) {
			//case Medic: batch.draw(texture, getX(), getY()); break;
		//}
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
		batch.draw(healthBar, getX() + getWidth()/2, getY() + getHeight() + 10, (health/maxHealth)*100, 10);
	}

}
