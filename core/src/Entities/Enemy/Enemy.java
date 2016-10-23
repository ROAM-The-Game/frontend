package Entities.Enemy;

import com.badlogic.gdx.graphics.Texture;

import Entities.Entity;

public class Enemy extends Entity {
	
	public enum Type {
		Test
	}
	Type type;
	public Enemy(Type type) {
		this.type = type;
		switch(type) {
			case Test: texture = new Texture("enemy/B_001.png"); break;
		}
	}

}
