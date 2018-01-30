import java.util.*;
import java.awt.image.*;
public class RoadObstacle {
    private int x, y, v, width, height, type;
    private int [] par = {width,height};
    private String pic;
    public RoadObstacle(int tipe, int pos){
    	// 1 is car in lane 1, 2 is truck lane 2, 3 is car lane 3, 4 is truck lane 4
    	if(tipe == 1){
    		type = tipe;
    		x=850;
	    	y=500;
	    	v=3;
	    	width=40;
	    	height=60;
	    	pic = "car1Right.png";
    	}
    	if(tipe == 2){
    		type = tipe;
    		x=-50;
	    	y=450;
	    	v=2;
	    	width=40;
	    	height=80;
	    	pic="truck1Right.png";
    	}
    	if(tipe == 3){
    		type = tipe;
    		x=850;
	    	y=400;
	    	v=4;
	    	width=40;
	    	height=60;
	    	pic="car1Right.png";
	    	
    	}
    	if(tipe == 4){
    		type = tipe;
    		x=-50;
	    	y=350;
	    	v=3;
	    	width=40;
	    	height=80;
	    	pic="truck1Right.png";
    	}
    	if(pos == 2){
    		x = 266;
    	}
    	else if(pos == 3){
    		x = 533;
    	}
    	
    }
    public void drive(){
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
