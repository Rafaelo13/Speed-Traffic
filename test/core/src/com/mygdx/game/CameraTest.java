package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import utility.ViewPort;

public class CameraTest extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;

	Sprite sprite;

	OrthographicCamera camera;
	float zoom; // sirve para el zoom

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		img = new Texture("via.jpg");
		sprite = new Sprite(img);

		sprite.setSize(ViewPort.width, ViewPort.height);
		sprite.setPosition(0, 0);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, ViewPort.width, ViewPort.height);

		zoom = 1;

	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 1, 1);

		// camera.position.x --;
		// camera.position.y --;

		// camera.position.z -= 0.2; NO FUNCIONA
		// camera.rotate(1);

		camera.zoom = zoom;
		
		if (Gdx.input.isKeyPressed(51)) {
			zoom -= 0.02;
			if (zoom <= 0.5) {
				zoom = 0.5f;
			}
		} else if(Gdx.input.isKeyPressed(47) ){
			zoom += 0.02;
			if (zoom >= 1) {
				zoom = 1;
			}
		}
		//movimiento camara a la izquierda-----------------------------------------------------		
		if(Gdx.input.isKeyPressed(21)){
			camera.position.x -=2; 
									
		}

		if(camera.position.x<= (ViewPort.width/2 * zoom)){
			camera.position.x = (ViewPort.width/2 * zoom);
		}
		//FIN movimiento camara a la izquierda

		//movimiento camara a la derecha-----------------------------------------------------
		if(Gdx.input.isKeyPressed(22)){
			camera.position.x +=2; 						
		}

		

		if(camera.position.x>= (ViewPort.width) - ((ViewPort.width/2 * zoom))){
			camera.position.x = (ViewPort.width) - ((ViewPort.width/2 * zoom));
		}

		//FIN movimiento camara a la derecha


		//MOVIMIENTO CAMARA ARRIBA-----------------------------------------------------
		if(Gdx.input.isKeyPressed(19)){
			camera.position.y ++; 
									
		}

		if(camera.position.y>= (ViewPort.height) - ((ViewPort.height/2 * zoom))){
			camera.position.y = (ViewPort.height) - ((ViewPort.height/2 * zoom));
		}

		//FIN MOVIMIENTO CAMARA ARRIBA


		//MOVIMIENTO CAMARA ABAJO-----------------------------------------------------
		if(Gdx.input.isKeyPressed(20)){
			camera.position.y --; 
			
		}

		if(camera.position.y <= (ViewPort.height/2 * zoom)){
			camera.position.y = (ViewPort.height/2 * zoom);
		}
		//FIN MOVIMIENTO CAMARA ABAJO
		//System.out.println(camera.position.x + " - " + camera.position.y);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		sprite.draw(batch);
		batch.end();

	}

	@Override
	public void dispose() {

		// todos los elementos deben ser borrados al final		
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		System.out.println(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {

		return false;
	}
}
