package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;

public class CarCount implements TextInputListener {
	int cars = -1;
	//boolean 
	
	@Override
	public void input (String text) {
		this.cars = Integer.parseInt(text) - 1;
	}
	
	@Override
	public void canceled () {
		Gdx.app.exit();
		System.out.println("Holi");
	}
	
	public int getCars() {
		return cars;
	}
}
