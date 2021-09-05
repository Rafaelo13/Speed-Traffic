package utility;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Semaforo extends DynamicObject{
    Texture imgLoaded2;
    Sprite img2;

    Texture imgLoaded3;
    Sprite img3;
    public Semaforo(String texturePath, String texturePath2, String texturePath3){
        super(texturePath);
        imgLoaded2 = new Texture(texturePath2);
        imgLoaded3 = new Texture(texturePath3);
        
        img2 = new Sprite(imgLoaded2);
        img3 = new Sprite(imgLoaded3);

    }

    public Texture getImage2(){
        return imgLoaded2;
    }

    public Texture getImage3(){
        return imgLoaded3;        
    }

    public Sprite getSprite2(){
        return img2;
    }

    public Sprite getSprite3(){
        return img3;
    }

    @Override
    public void setX(float x){
        img.setX(x);
        img2.setX(x);
        img3.setX(x);
        rect.x = x;
        
    }   

    
    public void setY(float y){
        img.setY(y);
        img2.setY(y);
        img3.setY(y);
        rect.y = y;
        
    }

    public void setOriginCenter(){
        img.setOrigin(img.getWidth()/2, img.getHeight()/2);
        img2.setOrigin(img2.getWidth()/2, img2.getHeight()/2);
        img3.setOrigin(img3.getWidth()/2, img3.getHeight()/2);
    }


    public void rotate(float degrees){
        img.rotate(degrees);
        img2.rotate(degrees);
        img3.rotate(degrees);
    }

    
    public void draw(SpriteBatch batch){
        img.draw(batch);
        img2.draw(batch);
        img3.draw(batch);
    }

    
    public void dispose(){
        imgLoaded.dispose();
        imgLoaded2.dispose();
        imgLoaded3.dispose();
    }

    public void setRojo(){
        img.setAlpha(1);
        img2.setAlpha(0);
        img3.setAlpha(0);
    }

    public void setAmarillo(){
        img.setAlpha(0);
        img2.setAlpha(1);
        img3.setAlpha(0);
    }

    public void setVerde(){
        img.setAlpha(0);
        img2.setAlpha(0);
        img3.setAlpha(1);
    }
    
}
