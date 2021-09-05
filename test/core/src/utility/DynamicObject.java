package utility;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;



public class DynamicObject {
    Texture imgLoaded;
    Sprite img;
    Rectangle rect;

    public DynamicObject(String texturePath){
        imgLoaded = new Texture(texturePath);
        img = new Sprite(imgLoaded);
        rect = new Rectangle();

        rect.setWidth(img.getWidth());
        rect.setHeight(img.getHeight());
    }
    
    public DynamicObject() {
    	
    }

    //GETTTERS
    
    public void setImage(String texturePath) {
    	imgLoaded = new Texture(texturePath);
    	
    }
    public Texture getImage(){
        return imgLoaded;
    }

    public Sprite getSprite(){
        return img;
    }

    public Rectangle getRect(){
        return rect;
    }


    public void setX(float x){
        img.setX(x);
        rect.x = x;
        
    }   

    public void setY(float y){
        img.setY(y);
        rect.y = y;
        
    }

    public float getX(){
        return img.getX();
    }

    public float getY(){
        return img.getY();
    }

    public void setPosition(float x, float y){
        setX(x);
        setY(y);
    }

    

    public void draw(SpriteBatch batch){
        img.draw(batch);
    }

    public boolean overlaps(DynamicObject otherObject){
        return overlaps(otherObject);
    }

    public void dispose(){
        imgLoaded.dispose();
    }
}
