package com.mygdx.game;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Timer {

	private BitmapFont font;
	private float deltaTime = 0;
	CharSequence str;

	public Timer() {
	    font = new BitmapFont();
	}
	
	public void drawTime(SpriteBatch batch) {
	    deltaTime += Gdx.graphics.getDeltaTime();
	    str = Float.toString(deltaTime);
	    font.draw(batch, str , 100 , 100);
	}
	
	public void stopTime(SpriteBatch batch) {
		deltaTime = deltaTime;
	    str = Float.toString(deltaTime);
	    font.draw(batch, str , 100 , 100);
	}
    
}
