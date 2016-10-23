package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

public class Projectile extends Entity {
	
	public enum Type {
		Linear
	}
	float angle;
	ArrayList<Texture> frames;
	int curr;
	float time;
	public Projectile(Texture texture, float x, float y, float angle, float speed) {
		//this.texture = texture;
		frames = new ArrayList<Texture>();
		curr = 0;
		frames.add(new Texture("effects/shock1.png"));
		frames.add(new Texture("effects/shock2.png"));
		frames.add(new Texture("effects/shock3.png"));
		frames.add(new Texture("effects/shock4.png"));
		setX(x);
		setY(y);
		this.speed = speed;
		this.angle = angle;
		setWidth(frames.get(0).getWidth());
		setHeight(frames.get(0).getHeight());
	}

	public void act(float delta) {
		setX(getX() + (float)Math.cos(Math.toRadians(angle))*speed);
		setY(getY() + (float)Math.sin(Math.toRadians(angle))*speed);
		if(getX() > Gdx.graphics.getWidth()*1.5f)
			this.remove();

		if(time < 0.02) {
			curr = 0;
		} else if(time < 0.04) {
			curr = 1;
		} else if(time < 0.06) {
			curr = 2;
		} else if(time < 0.08) {
			curr = 3;
		} else if(time < 0.1) {
			curr = 0;
			time = 0;
		}
		time+=delta;
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.draw(frames.get(curr), getX(), getY(), getWidth()*2, getHeight()*2);
	}

}
