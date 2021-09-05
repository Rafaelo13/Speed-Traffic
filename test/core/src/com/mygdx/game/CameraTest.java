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
import utility.Semaforo;
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
	HashTable hashH;
	HashTable hashV;

	boolean delete; // dice si se siguen sacando carros o no

	int carAcomulator; // controla si la cantidad de carros es la misma que se estipul√≥ en la interfaz

	// elementos carro vertical

	float carroVMovX;
	float carroVMovY;

	Long previousTime;

	OrthographicCamera camera;
	float zoom; // sirve para el zoom

	// figuras geometricas
	ShapeRenderer shapeR;

	// semaforos horizontales

	Semaforo semaforoH;
	Semaforo semaforoV;

	DynamicObject xd;

	boolean semRojoH;
	boolean semRojoV;

	int contadorH;
	int contadorV;
	
	@Override
	public void create() {
		contadorH = 0;
		contadorV = 0;
		hashH = new HashTable(5, 2);
		hashV = new HashTable(5, 2);
		
		
		
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

		carAcomulator = -1; // acomulador de carros

		Gdx.input.getTextInput(listener, "Bienvenido!", "", "Ingrese la cantidad de carros por vÌa");

		// semaforos

		semaforoH = new Semaforo("redTrafficLight.png", "yellowTrafficLight.png", "greenTrafficLight.png");

		semaforoH.getSprite().setSize(semaforoH.getImage().getWidth() / 2.5f, semaforoH.getImage().getHeight() / 2.5f);
		semaforoH.getSprite2().setSize(semaforoH.getImage2().getWidth() / 2.5f,
				semaforoH.getImage2().getHeight() / 2.5f);
		semaforoH.getSprite3().setSize(semaforoH.getImage3().getWidth() / 2.5f,
				semaforoH.getImage3().getHeight() / 2.5f);

		semaforoH.setPosition(358, 419);

		semaforoV = new Semaforo("redTrafficLight.png", "yellowTrafficLight.png", "greenTrafficLight.png");
		semaforoV.getSprite().setSize(semaforoV.getImage().getWidth() / 2.5f, semaforoV.getImage().getHeight() / 2.5f);
		semaforoV.getSprite2().setSize(semaforoV.getImage2().getWidth() / 2.5f,
				semaforoV.getImage2().getHeight() / 2.5f);
		semaforoV.getSprite3().setSize(semaforoV.getImage3().getWidth() / 2.5f,
				semaforoV.getImage3().getHeight() / 2.5f);

		semaforoV.setPosition(428, 410);
		semaforoV.setOriginCenter();
		semaforoV.rotate(-90);

		semRojoH = true;
		semRojoV = false;
	}

	public Car createCarroH(int a) {
		// carro horizontal
		float[] yDirections = { 387, 401 };
		Car carroHorizontal = new Car(a, "carro.png");		
		carroHorizontal.getSprite().setSize(25, 12);
		carroHMovX = -20;
		carroHMovY = yDirections[MathUtils.random(0, 1)];
		carroHorizontal.getRect().width = carroHorizontal.getSprite().getWidth();
		carroHorizontal.getRect().height = carroHorizontal.getSprite().getHeight();
		carroHorizontal.setPosition(carroHMovX, carroHMovY);

		return carroHorizontal;
	}

	public Car createCarroV(int a) {
		// carro vertical
		float[] xDirections = { ViewPort.width / 2 + 2, ViewPort.width / 2 - 13 };
		Car carroVertical = new Car(a, "carroVertical.png");
		carroVertical.getSprite().setSize(12, 25);
		carroVMovX = xDirections[MathUtils.random(0, 1)];
		carroVMovY = 810;
		carroVertical.setPosition(carroVMovX, carroVMovY);
		carroVertical.getRect().width = carroVertical.getSprite().getWidth();
		carroVertical.getRect().height = carroVertical.getSprite().getHeight();
		
		return carroVertical;
	}

	/*
	 * public boolean checkOverlaps(DynamicObject car) { for (DynamicObject x :
	 * carrosH) { //System.out.println("overlap"); return x.overlaps(car); } return
	 * false; }
	 */
	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 1, 1);
		Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cameraMove();
		camera.update();

		//TODO
		Long currentTime = TimeUtils.nanoTime();
		if (currentTime - previousTime >= Long.parseLong("1000000000") && hashH.getNodos() <= listener.getCars()
				&& delete) {
			contadorH++;
			Car carroH = createCarroH(contadorH);
			hashH.add(carroH);

			contadorV++;
			Car carroV = createCarroV(contadorV);
			hashV.add(carroV);

			carAcomulator++;
			previousTime = TimeUtils.nanoTime();
			if (carAcomulator >= listener.getCars()) {
				delete = false;
			}

		}

		for (int i = 0; i < hashH.getNodos(); i++) {
			if (i == 0) {
				if ((semRojoH && (hashH.get(i).getX() == 360))) {
					/*
					 * || || checkOverlaps(carrosH.get(i) if(i > 0 && (carrosH.get(i-1).getX() <
					 * carrosH.get(i).getX())){ }
					 */
					continue;
				}
			} else {
				if ((semRojoH && (hashH.get(i).getX() == 360))
						|| hashH.get(i).getX() + 25 >= hashH.get(i - 1).getX()) {
					/*
					 * || || checkOverlaps(carrosH.get(i) if(i > 0 && (carrosH.get(i-1).getX() <
					 * carrosH.get(i).getX())){ }
					 */
					continue;
				}

			}
			
			if(hashH.get(i) != null) {
				hashH.get(i).setX(hashH.get(i).getX() + 1);
			}
			
		}

		for (int i = 1; i < hashV.getNodos(); i++) {
			if (i == 1) {
				if ((semRojoV && (hashV.get(i).getY() == 420))) {
					/*
					 * || || checkOverlaps(carrosH.get(i) if(i > 0 && (carrosH.get(i-1).getX() <
					 * carrosH.get(i).getX())){ }
					 */
					continue;
				}
			} else {
				if ((semRojoV && (hashV.get(i).getY() == 420))
						|| hashV.get(i).getY() - 25 <= hashV.get(i - 1).getY()) {
					/*
					 * || || checkOverlaps(carrosH.get(i) if(i > 0 && (carrosH.get(i-1).getX() <
					 * carrosH.get(i).getX())){ }
					 */
					continue;
				}

			}

			hashV.get(i).setY(hashV.get(i).getY() - 1);
		}

		//
		// carroVMovY--;

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		fondo.draw(batch);

		// dibujar semaforo
		semaforoH.draw(batch);
		semaforoV.draw(batch);

		semaforoH.setVerde();
		semaforoV.setRojo();

		shapeR.setProjectionMatrix(camera.combined);
		shapeR.begin(ShapeRenderer.ShapeType.Line);

		for (int i = 0 ; i < hashH.getNodos() ; i++ ) {
			hashH.get(i).draw(batch);

			shapeR.rect(hashH.get(i).getRect().x, hashH.get(i).getRect().y, hashH.get(i).getRect().getWidth(), hashH.get(i).getRect().getHeight());
			shapeR.setColor(Color.BLUE);
		}

		for (int i = 0 ; i < hashV.getNodos() ; i++) {
			hashV.get(i).draw(batch);

			shapeR.rect(hashV.get(i).getX(), hashV.get(i).getRect().y, hashV.get(i).getRect().getWidth(), hashV.get(i).getRect().getHeight());
			shapeR.setColor(Color.GREEN);

		}

		/////////// ELIMINACION DE CARROS

		for (int i = 0 ; i < hashH.getNodos() ; i++) {
			if (hashH.get(i).getX() >= ViewPort.width) {

				hashH.remove(hashH.get(i));
				hashH.get(i).dispose();
				// System.out.println("Eliminado");
			}
		}

		for (int i = 0 ; i < hashV.getNodos() ; i++) {
			if (hashV.get(i).getY() <= -12) {

				hashV.remove(hashV.get(i));
				hashV.get(i).dispose();
				// System.out.println("Eliminado");
			}
		}

		// TODO CONTROL DE SEMAFOROS

		if ((int) timer.getDeltaTime() % 5 == 0 && (int) timer.getDeltaTime() % 10 != 0) {
			semRojoH = true;
			semRojoV = false;
		}
		if ((int) timer.getDeltaTime() % 10 == 0) {
			semRojoH = false;
			semRojoV = true;

		}

		if (semRojoH && !semRojoV) {
			semaforoH.setRojo();
			semaforoV.setVerde();
		} else {
			semaforoH.setVerde();
			semaforoV.setRojo();
		}

		if (listener.getCars() > -1 && carrosV.size != 0 || hashH.getNodos() != 0) {
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

		semaforoH.dispose();
		for (int i = 0 ; i < hashH.getNodos() ; i++) {
			hashH.get(i).dispose();
		}
		for (int i = 0 ; i < hashV.getNodos() ; i++) {
			hashV.get(i).dispose();
		}
		shapeR.dispose();
		
		//Main.run();
	}

	@Override
	public boolean keyDown(int keycode) {

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
