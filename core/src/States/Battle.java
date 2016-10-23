package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.heavyhanded.roam.Game;

import Entities.Player;
import Entities.Enemy.Enemy;

public class Battle extends State {
	
	Player player;
	static Enemy enemy;
	ImageTextButton attack;
	ImageTextButton inventory;
	
	enum GameAction {
		CHOOSE,
		ATTACK,
		ITEMS
	}
	
	GameAction action;
	
	public Battle() {
		stage = new Stage();
		action = GameAction.CHOOSE;
		player = Game.player;
		ImageTextButtonStyle style = new ImageTextButtonStyle();
		style.font = new BitmapFont();
		attack = new ImageTextButton("Attack", style);
		
		inventory = new ImageTextButton("Items", style);
	}
	
	@Override
	public void show() {
		stage.addActor(player);
		player.setX(Gdx.graphics.getWidth()*0.1f);
		player.setY(Gdx.graphics.getHeight()/2);
		
		stage.addActor(enemy);
		enemy.setX(Gdx.graphics.getWidth()*0.9f - enemy.getWidth());
		enemy.setY(Gdx.graphics.getHeight()/2);
		
		stage.addActor(attack);
		attack.setX(Gdx.graphics.getWidth()*0.1f);
		attack.setY(Gdx.graphics.getHeight()*0.1f);
		attack.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Attack!");
				return true;
			}
		});
		
		Gdx.input.setInputProcessor(stage);

	}
	
	@Override
	public void render(float delta) {
		gui();
		stage.act(delta);
		stage.draw();
	}
	
	void gui() {
		switch(action) {
			case CHOOSE: 
				attack.setVisible(true);
				break; 
			case ATTACK:
				break;
			case ITEMS: 
				break;
		}
	}

}
