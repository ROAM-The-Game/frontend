package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Entity extends Actor {
	
	protected Texture texture;
	protected Texture healthBar;
	protected float speed;
	protected int health;
	protected int maxHealth;
	boolean isAlive;
	
	public Entity() {
		if(texture != null) {
			setWidth(texture.getWidth());
			setHeight(texture.getHeight());
		}
	}

	public void setUp() {
		if(texture != null) {
			setWidth(texture.getWidth());
			setHeight(texture.getHeight());
		}
	}
	
	public Entity(String texture) {
		this.texture = new Texture(texture);
	}
	
	public Entity(float x, float y) {
		setX(x);
		setY(y);
	}
	
	@Override
	public void act(float delta) {
		;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		setBounds(getX(), getY(), getWidth(), getHeight());
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
	}
	
	public float getSpeed() {
		return speed;
	}

	public Texture getTexture() {
		return texture;
	}

}
