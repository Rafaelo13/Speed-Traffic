package com.mygdx.game;


//import org.apache.commons.math3.primes.Primes;
import java.util.Random;

public class HashFunction {
	private float loadFactor;
	private int a, b, p,m; // m: tamano del arreglo; a,b : valores de hash ; p: big pryme 
	private Random random = new Random(); //Generador aleatorio
	
	public HashFunction(int m, int l) {
		this.m = m;
		//this.p = Primes./*nextPrime*/((int)Math.pow(10,l));
		this.p = 101;
		this.a = random.nextInt(p)+1; //max+min -> p-1+1 -> p 
		this.b = random.nextInt(p-1); // max+min -> p-1+0 -> p
	}
	
	public long hash(int x) {
		long val = (a*x+b);
		if(val < 0) {
			return Math.abs((val%(-1*p))%(-1*m)); 
		}else {
			return Math.abs((val%(p))%(m));
		}	
	}
	/*
	public void reHash(int m, int l) {
		this.m = m;
		this.p = Primes.nextPrime((int)Math.pow(10,l));
		this.a = random.nextInt(p)+1; //max+min -> p-1+1 -> p 
		this.b = random.nextInt(p-1); // max+min -> p-1+0 -> p
	}

*/
	public float getLoadFactor() {
		return loadFactor;
	}

	public void setLoadFactor(float loadFactor) {
		this.loadFactor = loadFactor;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}
	
	
}
