package Entities.Enemy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class MeleeEnemy extends Enemy {
	
	Vector2 aggro;
	
	public MeleeEnemy(Type type) {
		super(type);
		speed = 4;
	}
	
	@Override
	public void act(float delta) {
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY());
		batch.draw(healthBar, getX(), getY() + texture.getHeight() + 10, (health/maxHealth)*100, 10);
	}

}
