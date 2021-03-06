package States;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.heavyhanded.roam.Game;
import com.heavyhanded.roam.map.Map;

import Entities.Player;
import Entities.Enemy.Enemy;
import States.StateManager.Screen;

public class MapExplore extends State {
	
	Player player;
	//Enemy test;
	ArrayList<Enemy> enemies;
	Map map;
	boolean mapInit = false;
	public MapExplore() {
		stage = new Stage();
		player = Game.player;
		enemies = new ArrayList<Enemy>();
		enemies.add(new Enemy(Enemy.Type.Test));
		map = new Map();
	}
	
	@Override
	public void show() {
		stage.addActor(map);
		stage.addActor(player);
		for(final Enemy enemy: enemies) {
			stage.addActor(enemy);
			enemy.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					Battle.enemy = enemy;
					StateManager.change(Screen.BATTLE);
					return true;
				}
			});
		}
		for(int i = 0; i < 10; i++) {
			final Enemy enemy = new Enemy(Enemy.Type.Test);
			enemy.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					Battle.enemy = enemy;
					StateManager.change(Screen.BATTLE);
					return true;
				}
			});
			enemy.setPosition((float)Math.random()*1200 - 600, (float)Math.random()*1200 - 600);
			stage.addActor(enemy);
		}
		Gdx.input.setInputProcessor(stage);
		((OrthographicCamera) stage.getCamera()).zoom = 0.5f;
	}
	
	@Override
	public void render(float delta) {
		if(Gdx.input.isKeyPressed(Input.Keys.W))
			player.setY(player.getY() + player.getSpeed());
		if(Gdx.input.isKeyPressed(Input.Keys.A))
			player.setX(player.getX() - player.getSpeed());
		if(Gdx.input.isKeyPressed(Input.Keys.S))
			player.setY(player.getY() - player.getSpeed());
		if(Gdx.input.isKeyPressed(Input.Keys.D))
			player.setX(player.getX() + player.getSpeed());
		updateMap();
		stage.getCamera().position.x = player.getX();
		stage.getCamera().position.y = player.getY();
		stage.act(delta);
		stage.draw();
	}

	private void updateMap() {
		if(!mapInit) {
			if(Game.initPosSet) {
				map.init();
				mapInit = true;
			}
		}
	}

	@Override
	public void hide() {
		stage.dispose();
	}
	

}
