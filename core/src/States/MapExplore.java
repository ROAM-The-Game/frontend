package States;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
		Gdx.input.setInputProcessor(stage);
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
		
		stage.getCamera().position.x = player.getX();
		stage.getCamera().position.y = player.getY();
		stage.act(delta);
		stage.draw();
	}
	

}
