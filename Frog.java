import java.util.*;
import java.awt.image.*;
public class Frog{
    private int x, y, width, height, v, lives;
    private String pic;
    public Frog(){
    	x = 400;
    	y = 550;
    	width = 35;
    	height = 35;
    	pic = "RetroFrog_100W.png";
    	v = 2;
    	lives = 3;
    }
    public void jump(int direction){
    	//left = 1, up = 2, right = 3, down = 4
    	if(direction == 1){
    		x -= v;
    	}
    	if(direction == 2){
    		y -= v;
    	}
    	if(direction == 3){
    		x += v;
    	}
    	if(direction == 4){
    		y += v;
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
    
    
    
}
