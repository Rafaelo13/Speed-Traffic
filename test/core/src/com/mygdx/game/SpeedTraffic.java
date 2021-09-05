package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpeedTraffic extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture img2;

	Sprite sprite;
	//posiciones de objeto
	float x;
	float y;

	//dimensiones de objeto
	float h;
	float l;

	float scale;

	float alpha;

	float rotate;

	float origin;


	//colores de objeto o fondo
	float red, green, blue; //se manejan por espectro rgb por porcentajes, de 0 a 1

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		img2 = new Texture("2Y2SX6EXZJHBZND5SFKCSH7KUA.jpg");

		sprite = new Sprite(img2);
		sprite.setSize(img.getWidth()/1.3f, img.getHeight()/1.3f); //se pueden obtener las dimensiones originales de una imagen
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2); //SE PUEDE MODIFICAR EL PUNTO DE ORIGEN DE LA IMAGEN desde su centro
		sprite.setPosition(0, 0);

		alpha = 1;

		//posiciones
		x = 50;
		y = 50;

		//tamaño

		h = 200;
		l = 200;

		scale = 1;

		rotate = 1;

		//color
		red = 0;
		green = 0;
		blue = 0;
		
	}

	@Override
	public void render () {
		ScreenUtils.clear(red, green, blue, 1);

		sprite.setScale(scale);
		//sprite.setPosition(x, y);
		//sprite.setAlpha(alpha);
		//sprite.setRotation(rotate);
		
		x+=2;
		y+=1;
		
		/*modificar el tamaño
		l-= 2;
		h-= 2;
		if(l <= 0){
			l = 0;
		}
		if(h <= 0){
			h = 0;
		}
		*/

		/*
		alpha -= 0.002; //modifica la opacidad

		if(alpha <= 0){
			alpha = 0;
		}
		*/
		 //modifica la escala de 0 a 1
		scale -= 0.002;
		if(scale <= 0){
			scale = 0;
		}
		
		/*
		rotate ++; //rota la imagen en grados, con sumas en en sentido anti-horario, con restas en sentido horario
		
		if(rotate >= 90){
			rotate = 90;
		} else if(rotate <= -90) {
			rotate = -90;
		}
		*/
		red += 0.001f;
		green += 0.003f;
		blue += 0.002f;
		batch.begin();

		sprite.draw(batch);
		//batch.draw(img, x, y, h, l);
		batch.end();

		
	}
	
	@Override
	public void dispose () {

		//todos los elementos deben ser borrados al final
		batch.dispose();
		img.dispose();
	}
}
