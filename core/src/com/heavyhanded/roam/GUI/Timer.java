package com.heavyhanded.roam.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import Entities.Entity;
import States.Battle;

/**
 * Created by kerlinmichel on 10/23/16.
 */
public class Timer extends Entity {

    public float time, fullTime;

    public Timer() {
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.drawPixel(0, 0, Color.rgba8888(new Color(0,0,255,1)));
        texture = new Texture(pix);
        setWidth(Gdx.graphics.getWidth()*0.8f);
        setHeight(Gdx.graphics.getHeight()*0.1f);
        time = fullTime = 5;
    }

    public void act(float delta) {
        if(Battle.playerTurn) {
            time -= delta;
        }
        if(time <= 0 ) {
            Battle.playerTurn = false;
            setVisible(false);
        }
    }

    public void draw(Batch batch, float parentAlpha) {
         batch.draw(texture, Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getHeight()*0.9f, (time/fullTime)*getWidth(), getHeight());
    }

}
