import java.util.*;
import java.awt.*;
public class Frog{
    private int x, y, width, height, v, lives;
    private String pic;

    public Frog(){
    	x = 400;
    	y = 560;
    	width = 35;
    	height = 35;
    	pic = "RetroFrog_100W.png";
    	v = 2;
    	lives = 3;

    }
    public void jump(int direction){
    	//left = 1, up = 2, right = 3, down = 4

    	if(direction == 1){
    		if (x-v>=0){
    			x -= v;
    		}
    	}
    	if(direction == 2){
    		if (y-v>=0){
    			y -= v;
    		}
    	}
    	if(direction == 3){
    		if (x+v<=800){
    			x += v;
    		}
    	}
    	if(direction == 4){
    		if (y+v<=600){
    			y+= v;
    		}
    	}
    }
    public String pik(){
    	return pic;
    }
    public int gitx(){
    	return x;
    }
    public int gity(){
    	return y;
    }
    public int getw(){
    	return width;
    }
    public int geth(){
    	return height;
    }
    public int life(){
    	return lives;
    }
    public int life(int x){
    	lives -= 1;
    	return lives;
    }
    public void reset(){
    	x = 400;
    	y = 560;
    }
	public Rectangle getR(){
		return new Rectangle(this.x, this.y, this.width, this.height);
	}


}
