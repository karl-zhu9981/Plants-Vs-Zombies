import java.util.*;
import java.awt.image.*;
public class WaterObstacle {  
    private int x, y, v, width, height, type;
    private String pic;
    public WaterObstacle(int tipe, int pos){
    	// 1 is turtle in lane 1, 2 is log lane 2, 3 is turtle lane 3, 4 is log lane 4
    	if(tipe == 1){
    		type = tipe;
    		x=850;
	    	y=200;
	    	v=2;
	    	width=60;
	    	height=80;
	    	pic = "turtle.png";
    	}
    	if(tipe == 2){
    		type = tipe;
    		x=-50;
	    	y=150;
	    	v=4;
	    	width=70;
	    	height=100;
	    	pic="log.png";
    	}
    	if(tipe == 3){
    		type = tipe;
    		x=850;
	    	y=100;
	    	v=3;
	    	width=60;
	    	height=80;
	    	pic="turtle.png";
	    	
    	}
    	if(tipe == 4){
    		type = tipe;
    		x=-50;
	    	y=50;
	    	v=2;
	    	width=70;
	    	height=100;
	    	pic="log.png";
    	}
    	if(pos == 2){
    		x = 266;
    	}
    	else if(pos == 3){
    		x = 533;
    	}
    	
    }
    
    public void moveObstacle(){
        if(type%2 == 0){
    		x += v;
    		if(x > 850){
    		x = 0;
    		}
    	}
    	else{
    		x -= v;
    		if(x<-50){
    			x = 800;
    		}
    	}
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
    public String pik(){
    	return pic;
    }
}
