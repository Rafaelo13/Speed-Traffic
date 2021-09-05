package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import utility.DynamicObject;
import utility.ViewPort;

public class CameraTest extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Timer timer;
	CarCount listener;

	// Elementos fondo
	DynamicObject fondo;

	// elementos carro horizontal

	float carroHMovX;
	float carroHMovY;

	Array<DynamicObject> carrosH;
	Array<DynamicObject> carrosV;

	boolean delete; //dice si se siguen sacando carros o no
 
	int carAcomulator; //controlla si la cantidad de carros es la misma que se estipuló en la interfaz

	// elementos carro vertical

	float carroVMovX;
	float carroVMovY;

	Long previousTime;

	OrthographicCamera camera;
	float zoom; // sirve para el zoom

	// figuras geometricas
	ShapeRenderer shapeR;

	// semaforos horizontales
	DynamicObject semaforoRojoH;
	DynamicObject semaforoAmarilloH;
	DynamicObject semaforoVerdeH;

	float currentTimeTraffic;

	Timer tiempoSemaforos;

	// semaforos horizontales
	DynamicObject semaforoV;
	DynamicObject semaforoRojoV;
	DynamicObject semaforoAmarilloV;
	DynamicObject semaforoVerdeV;

	@Override
	public void create() {

		Gdx.input.setInputProcessor(this); // permitir funcionalidades de teclas
		batch = new SpriteBatch(); // batch base

		carrosH = new Array<>();
		// DynamicObject carroHorizontal = createCarroH();
		// carrosH.add(carroHorizontal);

		carrosV = new Array<>();
		// DynamicObject carroVertical = createCarroV();
		// carrosV.add(carroVertical);

		delete = true; // BOOLEANO PARA VERIFICAR SI SE DEBEN SEGUIR CREANDO CARROS O NO

		// Fondo
		fondo = new DynamicObject("Mapa.jpg");
		fondo.getSprite().setSize(fondo.getImage().getWidth(), fondo.getImage().getHeight());
		fondo.setPosition(0, 0);

		// camara
		camera = new OrthographicCamera();
		camera.setToOrtho(false, ViewPort.width, ViewPort.height);
		zoom = 1;

		// figuras
		shapeR = new ShapeRenderer();

		// tiempo entre carros
		previousTime = (long) 0;
		timer = new Timer();
		listener = new CarCount();

		carAcomulator = -1; //acomulador de carros


		Gdx.input.getTextInput(listener, "Dialog Title", "", "Hint Value");

		// semaforos

		semaforoRojoH = createSemaforo("redTrafficLight.png");
		semaforoRojoH.getSprite().setSize(semaforoRojoH.getImage().getWidth() / 2.5f,
				semaforoRojoH.getImage().getHeight() / 2.5f);
		semaforoRojoH.setPosition(184, 420);

		semaforoAmarilloH = createSemaforo("yellowTrafficLight.png");
		semaforoAmarilloH.getSprite().setSize(semaforoAmarilloH.getImage().getWidth() / 2.5f,
				semaforoAmarilloH.getImage().getHeight() / 2.5f);
		semaforoAmarilloH.setPosition(184, 420);

		semaforoVerdeH = createSemaforo("greenTrafficLight.png");
		semaforoVerdeH.getSprite().setSize(semaforoVerdeH.getImage().getWidth() / 2.5f,
				semaforoVerdeH.getImage().getHeight() / 2.5f);
		semaforoVerdeH.setPosition(184, 420);

		currentTimeTraffic = 0;
	}

	public DynamicObject createSemaforo(String path) {
		DynamicObject semaforo = new DynamicObject(path);
		semaforo.getSprite().setSize(semaforo.getImage().getWidth(), semaforo.getImage().getHeight());
		semaforo.setPosition(200, 200);
		return semaforo;
	}

	public boolean luzSemaforo(DynamicObject semaforo) {
		return semaforo.getSprite().getColor() != Color.WHITE;
	}

	public DynamicObject createCarroH() {
		// carro horizontal
		float[] yDirections = { 387, 401 };
		DynamicObject carroHorizontal = new DynamicObject("carro.png");
		carroHorizontal.getSprite().setSize(25, 12);
		carroHMovX = -20;
		carroHMovY = yDirections[MathUtils.random(0, 1)];
		carroHorizontal.getRect().width = carroHorizontal.getSprite().getWidth();
		carroHorizontal.getRect().height = carroHorizontal.getSprite().getHeight();
		carroHorizontal.setPosition(carroHMovX, carroHMovY);

		return carroHorizontal;
	}

	public DynamicObject createCarroV() {
		// carro vertical
		float[] xDirections = { ViewPort.width/2 + 2, ViewPort.width/2 -13 };
		DynamicObject carroVertical = new DynamicObject("carroVertical.png");
		carroVertical.getSprite().setSize(12, 25);
		carroVMovX = xDirections[MathUtils.random(0, 1)];
		carroVMovY = 810;
		carroVertical.setPosition(carroVMovX, carroVMovY);
		carroVertical.getRect().width = carroVertical.getSprite().getWidth();
		carroVertical.getRect().height = carroVertical.getSprite().getHeight();

		return carroVertical;
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 1, 1);
		Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cameraMove();
		camera.update();

		Long currentTime = TimeUtils.nanoTime();		
		if (currentTime - previousTime >= Long.parseLong("1000000000") && carrosH.size <= listener.getCars()
				&& delete) {
			DynamicObject carroH = createCarroH();
			carrosH.add(carroH);
			

			DynamicObject carroV = createCarroV();
			carrosV.add(carroV);


			carAcomulator ++;
			previousTime = TimeUtils.nanoTime();
			if(carAcomulator >= listener.getCars()){
				delete = false;
			}

		}

		for (DynamicObject x : carrosH) {
			x.setX(x.getX() + 1);
		}

		for (DynamicObject y : carrosV) {
			y.setY(y.getY() - 1);
		}

		carroHMovX++;

		carroVMovY--;

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		fondo.draw(batch);

		semaforoRojoH.draw(batch);// dibujar semaforo
		semaforoAmarilloH.draw(batch);
		semaforoAmarilloH.getSprite().setAlpha(0);

		semaforoVerdeH.draw(batch);
		semaforoVerdeH.getSprite().setAlpha(0);



		shapeR.setProjectionMatrix(camera.combined);
		shapeR.begin(ShapeRenderer.ShapeType.Line);

		for (DynamicObject x : carrosH) {
			x.draw(batch);

			shapeR.rect(x.getRect().x, x.getRect().y, x.getRect().getWidth(), x.getRect().getHeight());
			shapeR.setColor(Color.BLUE);
		}

		for (DynamicObject y : carrosV) {
			y.draw(batch);

			shapeR.rect(y.getX(), y.getRect().y, y.getRect().getWidth(), y.getRect().getHeight());
			shapeR.setColor(Color.GREEN);

		}

		/////////// ELIMINACION DE CARROS

		for (DynamicObject carH : carrosH) {
			if (carH.getX() >= ViewPort.width) {
				

				carrosH.removeValue(carH, true);
				carH.dispose();
				System.out.println("Eliminado");
			}
		}

		for (DynamicObject carV : carrosV) {
			if (carV.getY() <= -12) {
				
				carrosV.removeValue(carV, true);
				carV.dispose();
				System.out.println("Eliminado");
			}
		}

		// CONTROL DE SEMAFOROS
		
		
		
		if (currentTimeTraffic == 10) {
			semaforoRojoH.getSprite().setColor(Color.WHITE);
			semaforoRojoH.getSprite().setAlpha(0);
			semaforoAmarilloH.getSprite().setAlpha(1);;
		}

		if (currentTimeTraffic % 11 == 0) {
			semaforoAmarilloH.getSprite().setColor(Color.WHITE);
			semaforoAmarilloH.getSprite().setAlpha(0);
			semaforoVerdeH.getSprite().setAlpha(1);
		}

		if(currentTimeTraffic %21 == 0){
			semaforoVerdeH.getSprite().setColor(Color.WHITE);
			semaforoVerdeH.getSprite().setAlpha(0);
			semaforoRojoH.getSprite().setAlpha(1);
			currentTimeTraffic = 0 ;
		}

		if (listener.getCars() > -1 && carrosV.size != 0 && carrosV.size != 0) {
			timer.drawTime(batch);
		} else {
			timer.stopTime(batch);
		}

		batch.end();

		// car rectangle horizontal

		// car rectangle vertical

		shapeR.end();

	}

	public void cameraMove() {

		// camera.position.x --;
		// camera.position.y --;

		// camera.position.z -= 0.2; NO FUNCIONA
		// camera.rotate(1);
		camera.zoom = zoom;

		if (Gdx.input.isKeyPressed(51)) {
			zoom -= 0.02;
			if (zoom <= 0.4) {
				zoom = 0.4f;
			}
		} else if (Gdx.input.isKeyPressed(47)) {
			zoom += 0.02;
			if (zoom >= 1) {
				zoom = 1;
			}
		}
		// movimiento camara a la
		// izquierda-----------------------------------------------------
		if (Gdx.input.isKeyPressed(21)) {
			camera.position.x -= 3;

		}

		if (camera.position.x <= (ViewPort.width / 2 * zoom)) {
			camera.position.x = (ViewPort.width / 2 * zoom);
		}
		// FIN movimiento camara a la izquierda

		// movimiento camara a la
		// derecha-----------------------------------------------------
		if (Gdx.input.isKeyPressed(22)) {
			camera.position.x += 3;
		}

		if (camera.position.x >= (ViewPort.width) - ((ViewPort.width / 2 * zoom))) {
			camera.position.x = (ViewPort.width) - ((ViewPort.width / 2 * zoom));
		}

		// FIN movimiento camara a la derecha

		// MOVIMIENTO CAMARA ARRIBA-----------------------------------------------------
		if (Gdx.input.isKeyPressed(19)) {
			camera.position.y += 3;

		}

		if (camera.position.y >= (ViewPort.height) - ((ViewPort.height / 2 * zoom))) {
			camera.position.y = (ViewPort.height) - ((ViewPort.height / 2 * zoom));
		}

		// FIN MOVIMIENTO CAMARA ARRIBA

		// MOVIMIENTO CAMARA ABAJO-----------------------------------------------------
		if (Gdx.input.isKeyPressed(20)) {
			camera.position.y -= 3;

		}

		if (camera.position.y <= (ViewPort.height / 2 * zoom)) {
			camera.position.y = (ViewPort.height / 2 * zoom);
		}
		// FIN MOVIMIENTO CAMARA ABAJO
		// System.out.println(camera.position.x + " - " + camera.position.y);

	}

	@Override
	public void dispose() {
		// todos los elementos deben ser borrados al final
		batch.dispose();
		fondo.dispose();

		semaforoRojoH.dispose();
		semaforoAmarilloH.dispose();
		semaforoVerdeH.dispose();
		for (DynamicObject x : carrosH) {
			x.dispose();
		}
		for (DynamicObject y : carrosV) {
			y.dispose();
		}
		shapeR.dispose();
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
