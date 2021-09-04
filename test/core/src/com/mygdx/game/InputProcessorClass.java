package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.ScreenUtils;

public class InputProcessorClass extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	Texture img;
	Texture img2;
	int clicks;
	Sprite sprite;

	@Override
	public void create() {

		Gdx.input.setInputProcessor(this); //para poder usar los metodos de la interfaz input processor

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		clicks = 0;
		sprite = new Sprite(img);

		sprite.setPosition(50, 50);
	}

	@Override
	public void render() {
		/*
		 * SI LA TECLA ESTÁ PRESIONADA (CADA TECLA TIENE UN VALOR ENTERO ASIGNADO)
		 * if(Gdx.input.isKeyPressed(Input.Keys.A)){
		 * System.out.println("La tecla A está presionada."); }
		 */

		// SI LA TECLA HA SIDO PRESIONADA UNA VEZ
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			System.out.println("La tecla A ha sido presionada.");
		}

		// SI EL MOUSE HA SIDO CLICKEADO O SI SE HA TOCADO LA PANTALLA (TACTILES)
		if (Gdx.input.justTouched()) {
			System.out.println("Has tocado la pantalla/mouse");
			clicks++;
		}

		// SE USA PARA MOVER LA IMAGEN A DONDE ESTÉ EL MOUSE CON UN CLICK
		/*
		 * if(Gdx.input.justTouched()){ int mouseX = Gdx.input.getX(); int mouseY =
		 * Gdx.input.getY(); int mouseYUp = Gdx.graphics.getHeight()-mouseY; //permite
		 * evitar que al dar click la posicion vertical se invierta
		 * sprite.setPosition(mouseX-sprite.getWidth()/2,
		 * mouseYUp-sprite.getHeight()/2); //con el get height se puede elegir la
		 * posicion de un objeto desde su centro
		 * 
		 * }
		 */

		 //sin condicion, el objeto seguirá al mouse a donde este vaya sin necesidad de dar click
		//int mouseX = Gdx.input.getX();
		//int mouseY = Gdx.input.getY();
		//int mouseYUp = Gdx.graphics.getHeight() - mouseY; // permite evitar que al dar click la posicion vertical se
		// invierta
		//sprite.setPosition(mouseX - sprite.getWidth() / 2, mouseYUp - sprite.getHeight() / 2); // con el get height se
		// puede elegir la
		// posicion de un objeto
		// desde su centro

		ScreenUtils.clear(0, 0.3f, 0.5f, 1);
		batch.begin();
		sprite.draw(batch);
		// batch.draw(img, 50, 50);
		batch.end();

	}

	@Override
	public void dispose() {
		System.out.println(clicks);
		// todos los elementos deben ser borrados al final
		batch.dispose();
		img.dispose();
	}

//METODOS DEL INPUT PROCESSOR

	@Override
	public boolean keyDown(int keycode) { //cuando se presiona una tecla
		System.out.println("Tecla presionada");		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) { //cuando se suelta una tecla
		System.out.println("Tecla suelta");		
		return false;
	}

	@Override
	public boolean keyTyped(char character) { //cuando una tecla se mantiene presionada
		System.out.println("Tecla se mantiene presionada");		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) { //cuando se presiona el click o la pantalla
		System.out.println("Click / pantalla presionada");		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) { //cuando se deja de presionar el mouse o la pantalla
		System.out.println("Click / pantalla suelta");
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {	 // cuando se mantiene presionado el mouse o la pantalla
		System.out.println("Movimiento de mouse o dedo con presion");
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {		//cuando se mueve el mouse(solo Desktop)
		System.out.println("Movimiento de mouse");
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {		//cuando se presiona el scroll del mouse (solo desktop)		
		System.out.println("Scroll");
		return false;
	}
}
 