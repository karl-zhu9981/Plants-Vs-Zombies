import java.util.*;
import java.awt.*;
public class WaterObstacle {
    private int x, y, v, width, height, type;
    private Rectangle ob;
    private String pic;
    public WaterObstacle(int tipe, int pos){
    	// 1 is turtle in lane 1, 2 is log lane 2, 3 is turtle lane 3, 4 is log lane 4
    	if(tipe == 1){
    		type = tipe;
    		x=850;
	    	y=230;
	    	v=1;
	    	width=40;
	    	height=40;
	    	pic = "turtle.png";
    	}
    	if(tipe == 2){
    		type = tipe;
    		x=-50;
	    	y=170;
	    	v=2;
	    	width=100;
	    	height=40;
	    	pic="log.png";
    	}
    	if(tipe == 3){
    		type = tipe;
    		x=850;
	    	y=130;
	    	v=2;
	    	width=40;
	    	height=40;
	    	pic="turtle.png";

    	}
    	if(tipe == 4){
    		type = tipe;
    		x=-50;
	    	y=70;
	    	v=1;
	    	width=100;
	    	height=40;
	    	pic="log.png";
    	}
    	if(pos == 2){
    		x = 266;
    	}
    	else if(pos == 3){
    		x = 533;
    	}
    	ob = new Rectangle(x,y,width,height+10);
    }

    public void moveObstacle(){
        if(type%2 == 0){
    		x += v;
    		if(x > 850){
    		x = -50;
    		}
    	}
    	else{
    		x -= v;
    		if(x<-50){
    			x = 850;
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
    public int getV(){
    	return v;
    }
    public Rectangle getR(){
    	return ob;
    }
    
}
