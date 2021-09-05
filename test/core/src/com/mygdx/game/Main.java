package com.mygdx.game;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner reader  =  new Scanner(System.in);
		FileWriter fichero = null;
		PrintWriter pw = null;
		System.out.println();
		
		try {
			fichero =  new FileWriter(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss").format(LocalDateTime.now())+".txt");
			pw =  new PrintWriter(fichero);
			
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		
		
		
		System.out.println("Cant carros:");
		int cant =  reader.nextInt();
		
		long startTime = System.currentTimeMillis();
		System.out.println("---------PRUEBA ADD");
		
		HashTable ejemplo = new HashTable(5,2);
		ejemplo.setPw(pw);
		for(int i =0;i<cant;i++) {
			//Car nuevo = new Car(i);
			//ejemplo.add(nuevo);
		}
		System.out.println("Yo ya me voy");
		ejemplo.print();
		try {
			pw.println("Total Time: "+(System.currentTimeMillis()-startTime)+" ms");
			fichero.close();
		} catch (Exception e) {
			// TODO: handle exception
		}		
	
	}

}
