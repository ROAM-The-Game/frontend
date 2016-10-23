package com.heavyhanded.roam.GUI;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.heavyhanded.roam.Game;

import States.Battle;
import States.StateManager;

public class BattleActions {
	
	public static TextureRegionDrawable background = new TextureRegionDrawable(new TextureRegion(new Texture("gui/squarebutton.png")));
	
	public static ArrayList<ImageButton> getTechnician() {
		ArrayList<ImageButton> buttons = new ArrayList<ImageButton>(); 
		ImageButtonStyle style = new ImageButtonStyle();
		style.up           = background;
		style.down         = background;
		style.checked      = background;
		
		style.imageUp      = new TextureRegionDrawable(new TextureRegion(new Texture("gui/emp.png")));
		style.imageDown    = new TextureRegionDrawable(new TextureRegion(new Texture("gui/emp.png")));
		ImageButton button = new ImageButton(style);
		buttons.add(button);
		button.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				//lower enemy health
				Battle.timer.time -= 2;
				//StateManager.change(StateManager.Screen.MAPEXPLORE);
				//Game.player.setWidth(64);
				//Game.player.setHeight(64);
				return true;
			}
		});
		
		style.imageUp      = new TextureRegionDrawable(new TextureRegion(new Texture("gui/emp.png")));
		style.imageDown    = new TextureRegionDrawable(new TextureRegion(new Texture("gui/emp.png")));
		button = new ImageButton(style);
		buttons.add(button);
		button.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				//lower enemy health
				//StateManager.change(StateManager.Screen.MAPEXPLORE);
				//Game.player.setWidth(64);
				//Game.player.setHeight(64);
				return true;
			}
		});
		
		style.imageUp      = new TextureRegionDrawable(new TextureRegion(new Texture("gui/emp.png")));
		style.imageDown    = new TextureRegionDrawable(new TextureRegion(new Texture("gui/emp.png")));
		button = new ImageButton(style);
		buttons.add(button);
		button.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				//lower enemy health
				StateManager.change(StateManager.Screen.MAPEXPLORE);
				Game.player.setWidth(64);
				Game.player.setHeight(64);
				return true;
			}
		});
		
		style.imageUp      = new TextureRegionDrawable(new TextureRegion(new Texture("gui/emp.png")));
		style.imageDown    = new TextureRegionDrawable(new TextureRegion(new Texture("gui/emp.png")));
		button = new ImageButton(style);
		buttons.add(button);
		button.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				//lower enemy health
				StateManager.change(StateManager.Screen.MAPEXPLORE);
				Game.player.setWidth(64);
				Game.player.setHeight(64);
				return true;
			}
		});

		return buttons;

	}

}
