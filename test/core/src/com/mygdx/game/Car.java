package com.mygdx.game;

import utility.DynamicObject;

public class Car extends DynamicObject{

	private long tiempoIn;
	private long tiempoOut;
	private int id;
	
	public Car(int id, String texturePath) {
		super(texturePath);
		this.id = id;
	}
	
	public Car() {
		
	}
	public long getTiempoIn() {
		return tiempoIn;
	}
	public void setTiempoIn() {
		this.tiempoIn = System.currentTimeMillis();;
	}
	public long getTiempoOut() {
		return tiempoOut;
	}
	public void setTiempoOut() {
		this.tiempoOut = System.currentTimeMillis();;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long tiempoEnSimulaccion() {
		return this.tiempoOut - this.tiempoIn;
	}
	
}
