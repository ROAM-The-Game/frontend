package Entities.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

import Entities.Entity;
import States.Battle;

public class Enemy extends Entity {
	
	public enum Type {
		Test
	}
	Type type;

	boolean attacking;
	ArrayList<Texture> frames;
	int currFrame;
	float time;
	
	public Enemy(Type type) {
		this.type = type;
		currFrame = 0;
		frames = new ArrayList<Texture>();
		switch(type) {
			case Test:
				frames.add(new Texture("enemy/B_001.png"));
				frames.add(new Texture("enemy/B_002.png"));
				frames.add(new Texture("enemy/B_003.png"));
				frames.add(new Texture("enemy/B_004.png"));

				frames.add(new Texture("enemy/B_Atk01.png")); //4
				frames.add(new Texture("enemy/B_Atk02.png"));
				frames.add(new Texture("enemy/B_Atk03.png"));
				frames.add(new Texture("enemy/B_Atk04.png"));
				frames.add(new Texture("enemy/B_Atk05.png"));
				break;
		}
		setUp();
		setWidth(frames.get(0).getWidth());
		setHeight(frames.get(0).getHeight());
	}

	public void act(float delta) {
		if(attacking) { //attacking
			switch (type) {
				case Test://robot samurai
					if (time < 0.1) {
						setX(getX() - 160);
						setY(getY() - 10);
						currFrame = 4;
					} else if (time < 0.2) {
						setX(getX() - 80);
						setY(getY() - 5);
						currFrame = 5;
					} else if (time < 0.3) {
						setX(getX() + 80);
						setY(getY() + 5);
						currFrame = 6;
					} else if (time < 0.4) {
						setX(getX() + 159);
						setY(getY() + 10);
						currFrame = 7;
					} else if (time < 0.5) {
						currFrame = 8;
						time = 0;
						attacking = false;
						Battle.timer.time = Battle.timer.fullTime;
						Battle.timer.setVisible(true);
						Battle.playerTurn = true;
					}
					if(getX() >= Gdx.graphics.getWidth()  - getWidth()) {
						setX(Gdx.graphics.getWidth() - getWidth());
					}
					time += delta;
					break;
			}
		} else {
			switch(type) {
				case Test:
					if(time < 0.2){
						currFrame = 0;
					} else if(time < 0.4) {
						currFrame = 1;
					} else if(time < 0.6) {
						currFrame = 1;
						time = 0;
					}
					time += delta;
					break;
			}
		}
	}

	public void attack() {
		attacking = true;
	}

	public void draw(Batch batch, float parentAlpha) {
		setBounds(getX(), getY(), getWidth(), getHeight());
		batch.draw(frames.get(currFrame), getX(), getY(), getWidth(), getHeight());
	}
	

}
