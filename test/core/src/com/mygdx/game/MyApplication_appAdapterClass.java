package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;


public class MyApplication_appAdapterClass extends ApplicationAdapter{

    @Override
    public void create() {
        
        System.out.println("Crear");
        
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Redimiensionar");
        
    }

    @Override
    public void render() {
        
        //EJECUCION
        System.out.println("Ejectutar");
    }


    @Override
    public void dispose() {        
        //ULTIMA PAUSA Y CERRAR EL APP
        System.out.println("Cerrar");
    }

}
