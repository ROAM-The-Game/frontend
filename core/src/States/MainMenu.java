package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.heavyhanded.roam.GUI.BattleActions;
import com.heavyhanded.roam.Game;

import States.StateManager.Screen;

public class MainMenu extends State {
	
	Image bg;
	TextField username;
	TextField password;
	ImageTextButton login;
	
	public MainMenu() {
		bg = new Image(new Texture("gui/loginbg.png"));
		bg.setWidth(Gdx.graphics.getWidth());
		bg.setHeight(Gdx.graphics.getHeight());
		
		TextField.TextFieldStyle style = new TextField.TextFieldStyle();
		style.font = new BitmapFont();
		style.fontColor = Color.WHITE;
		style.background = new TextureRegionDrawable(new TextureRegion(new Texture("gui/login_fill.png")));
		style.cursor = new TextureRegionDrawable(new TextureRegion(new Texture("gui/cursor.png")));
		
		username = new TextField("", style);
		password = new TextField("", style);
		
		ImageTextButtonStyle loginStyle = new ImageTextButtonStyle();
		loginStyle.font =  new BitmapFont();
		loginStyle.down = BattleActions.background;
		loginStyle.up = BattleActions.background;
		
		login = new ImageTextButton("Login", loginStyle);
		login.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				//if(Game.initPosSet)
					StateManager.change(Screen.MAPEXPLORE);
				return true;
			}
		});
		
		stage = new Stage();
	}
	
	@Override
	public void show() {
		stage.addActor(bg);
		stage.addActor(username);
		username.setWidth(Gdx.graphics.getWidth()*0.2f);
		username.setHeight(Gdx.graphics.getHeight()*0.1f);
		username.setX(Gdx.graphics.getWidth()/2-username.getWidth()/2);
		username.setY(Gdx.graphics.getHeight()*0.4f);
		
		stage.addActor(password);
		password.setWidth(Gdx.graphics.getWidth()*0.2f);
		password.setHeight(Gdx.graphics.getHeight()*0.1f);
		password.setX(Gdx.graphics.getWidth()/2-password.getWidth()/2);
		password.setY(Gdx.graphics.getHeight()*0.2f);
		
		stage.addActor(login);
		login.setWidth(Gdx.graphics.getWidth()*0.15f);
		login.setHeight(Gdx.graphics.getHeight()*0.1f);
		login.setX(Gdx.graphics.getWidth()/2-login.getWidth()/2);
		login.setY(Gdx.graphics.getHeight()*0.0f);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

}
