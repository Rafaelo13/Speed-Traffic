package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AverageTime {
	private BitmapFont font;
	
	CharSequence str;

	public AverageTime() {
	    font = new BitmapFont();
	}
	
	public void drawTime(SpriteBatch batch, long tiempo) {	    
	    str = "Tiempo promedio: " + "N.A";
	    font.draw(batch, str , 600 , 720);
	}
	public void drawFinalTime(SpriteBatch batch, long tiempo) {	    
	    str = "Tiempo promedio: " + tiempo + " ms";
	    font.draw(batch, str , 600 , 720);
	}
	
	

}
