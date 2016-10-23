package States;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.heavyhanded.roam.Game;
import com.heavyhanded.roam.GUI.BattleActions;

import Entities.Player;
import Entities.Enemy.Enemy;

public class Battle extends State {
	
	Player player;
	static Enemy enemy;
	ImageTextButton attack;
	ImageTextButton inventory;
	ImageTextButton goBack;
	
	Image bg;
	
	enum GameAction {
		CHOOSE,
		ATTACK,
		ITEMS
	}
	
	GameAction action;
	
	ArrayList<ImageButton> attakcBtns;
	
	public Battle() {
		stage = new Stage();
		action = GameAction.CHOOSE;
		player = Game.player;
		ImageTextButtonStyle style = new ImageTextButtonStyle();
		style.font = new BitmapFont();
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture("gui/squarebutton.png")));
		style.up = new TextureRegionDrawable(new TextureRegion(new Texture("gui/squarebutton.png"))) ;
		attack = new ImageTextButton("Attack", style);
		
		inventory = new ImageTextButton("Items", style);
		
		goBack = new ImageTextButton("← Back", style);
		goBack.setY(Gdx.graphics.getHeight()*0.05f);
		goBack.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					action = GameAction.CHOOSE;
				return true;
			}
		});
		
		bg = new Image(new Texture("gui/battlebg.png"));
		bg.setWidth(Gdx.graphics.getWidth());
		bg.setHeight(Gdx.graphics.getHeight());
	}
	
	@Override
	public void show() {
		stage.addActor(bg);
		stage.addActor(player);
		player.setX(Gdx.graphics.getWidth()*0.15f);
		player.setY(Gdx.graphics.getHeight()*0.27f);
		
		stage.addActor(enemy);
		enemy.setX(Gdx.graphics.getWidth()*0.9f - enemy.getWidth());
		enemy.setY(Gdx.graphics.getHeight()*0.44f);
		
		stage.addActor(attack);
		attack.setX(Gdx.graphics.getWidth()*0.1f);
		attack.setY(Gdx.graphics.getHeight()*0.05f);
		attack.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Attack!");
				action = GameAction.ATTACK;
				return true;
			}
		});
		attakcBtns = null;
		switch(player.playerClass) {
			case Technician: attakcBtns = BattleActions.getTechnician(); break;
		}
		
		int x = 1;
		
		stage.addActor(goBack);
		for(ImageButton btn : attakcBtns) {
			btn.setX(Gdx.graphics.getWidth()*(0.15f*x++) + Gdx.graphics.getWidth()*0.075f);
			btn.setY(Gdx.graphics.getHeight()*0.05f);
			stage.addActor(btn);
			btn.setVisible(false);
		}
		
		stage.addActor(inventory);
		inventory.setX(Gdx.graphics.getWidth()*0.3f);
		inventory.setY(Gdx.graphics.getHeight()*0.05f);
		
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
				goBack.setVisible(false);
				inventory.setVisible(true);
				for(ImageButton btn : attakcBtns) {
					btn.setVisible(false);
				}
				break; 
			case ATTACK:
				attack.setVisible(false);
				goBack.setVisible(true);
				inventory.setVisible(false);
				for(ImageButton btn : attakcBtns) {
					btn.setVisible(true);
				}
				break;
			case ITEMS: 
				attack.setVisible(false);
				goBack.setVisible(true);
				inventory.setVisible(false);
				for(ImageButton btn : attakcBtns) {
					btn.setVisible(false);
				}
				break;
		}
	}

}
