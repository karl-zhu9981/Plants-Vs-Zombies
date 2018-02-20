//Karl Zhu and Ibraheem Aloran
//ICS 4U
//Water obstacle.java
//This class makes our obstacle object and the information that it goes with, the the obstacles in the game that helps your gameplay
//If you jump and land on the water obstacles, you will travel with the obstacle
import java.util.*;
import java.awt.*;
public class WaterObstacle {
    private int x, y, v, width, height, type;//The information for each road obstacle
    private String pic;//The picture of the water obstacle
    private Rectangle ob;//The rectangle set by each water obstacle
    public WaterObstacle(int tipe, int pos){//The lane the onstacle is in and the position it comes in the chain to appear on the screen
    	// 1 is turtle in lane 1, 2 is log lane 2, 3 is turtle lane 3, 4 is log lane 4
    	//Initial stats for the water obstacle
    	//Lane number, starting location, speed, size, and picture
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
    	//The initial x coordinate values for the suceeding obstacles
    	if(pos == 2){
    		x = 266;
    	}
    	else if(pos == 3){
    		x = 533;
    	}
    	//The rectangle set by the water obstacle
    	ob = new Rectangle(x,y,width,height+10);
    }

    public void moveObstacle(){//Moves the water obstacles in the lanes
        if(type%2 == 0){//Lanes 2 and 4
    		x += v;//Goes right
    		if(x > 800){//Makes sure it goes in a continous rotation
    		x = 0;
    		}
    	}
    	else{
    		x -= v;//Goes left
    		if(x<=0){//Makes sure it goes in a continous rotation
    			x = 800;
    		}
    	}
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
    public String pik(){//Returns the picture of the road obstacle
    	return pic;
    }
    public int getV(){//Gets the speed of the obstacle
    	return v;
    }
    public Rectangle getR(){//Gets the rectangle of the water obstacle onject
    	return ob;
    }

}
