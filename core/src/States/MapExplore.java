package States;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.heavyhanded.roam.Game;

import Entities.Player;
import Entities.Enemy.Enemy;

public class MapExplore extends State {
	
	Player player;
	//Enemy test;
	ArrayList<Enemy> enemies;
	public MapExplore() {
		stage = new Stage();
		player = Game.player;
		enemies = new ArrayList<Enemy>();
		enemies.add(new Enemy(Enemy.Type.Test));
		//test = new Enemy(Enemy.Type.Test);
	}
	@Override
	public void show() {
		stage.addActor(player);
		for(Enemy enemy: enemies) {
			
		}
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
