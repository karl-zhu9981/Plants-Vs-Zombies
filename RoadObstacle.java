//Karl Zhu and Ibraheem Aloran
//ICS 4U
//RoadObstacle.java
//This class makes our road obstacle objects and the information that it goes with, the obstacles in the game that hinders your gameplay
//If your frog touches the road obstacles, you will lose a life
import java.util.*;
import java.awt.*;
public class RoadObstacle {
    private int x, y, v, width, height, type;//The information for each road obstacle
    private String pic;//The picture of the road obstacle 
    public RoadObstacle(int tipe, int pos){//The lane the onstacle is in and the position it comes in the chain to appear on the screen
    	// 1 is car in lane 1, 2 is truck lane 2, 3 is car lane 3, 4 is truck lane 4
    	//Initial stats for the road obstacle
    	//Lane number, starting location, speed, size, and picture
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
	    	v=2;
	    	width=40;
	    	height=60;
	    	pic="car1Right.png";

    	}
    	if(tipe == 4){
    		type = tipe;
    		x=-50;
	    	y=350;
	    	v=4;
	    	width=40;
	    	height=80;
	    	pic="truck1Right.png";
    	}
    	//The initial x coordinate values for the suceeding obstacles
    	if(pos == 2){
    		x = 266;
    	}
    	else if(pos == 3){
    		x = 533;
    	}
    }
    public void drive(){//Moves the cars in the lanes
    	if(type%2 == 0){//Lanes 2, 4
    		x += v;//Goes right
    		if(x > 850){//Makes sure it goes in a continous rotation
    		x = 0;
    		}
    	}
    	else{
    		x -= v;//Goes left
    		if(x<-50){//Makes sure it goes in a continous rotation
    			x = 800;
    		}
    	}

    }
    public String pik(){//Returns the picture of the road obstacle
    	return pic;
    }
    public int gitx(){//Gets the x coordinate of the obstacle
    	return x;
    }
    public int gity(){//Gets the y coordinate of the obstacle
    	return y;
    }
    public int getw(){//Gets the width of the obstacle
    	return width;
    }
    public int geth(){//Gets the height of the obstacle
    	return height;
    }
    public boolean roadCollision(Rectangle p){//Gets the rectangle of the road obstacle onject
    	Rectangle ob = new Rectangle(x,y,height,width);
    	//System.out.println(p+ " ==== " + ob);
    	if (p.intersects(ob)){
    		 return true;
    	}
    	return false;
    }


}
