//Karl Zhu and Ibraheem Aloran
//ICS 4U
//Frog.java
//This class makes our frog object and the information that it goes with, the controllable character by the users
//They contain the position of the frog, the width and height of the frog and the number of lives that frog has.
import java.util.*;
import java.awt.*;
public class Frog{
    private int x, y, width, height, v, lives;//The information for the frog
    private String pic;//The picture of the frog character

    public Frog(){//Constructor for our frog
    //Initial stats for the frog
    //Starting location, size, pic, speed, and number of lives
    	x = 400;
    	y = 560;
    	width = 25;
    	height = 25;
    	pic = "RetroFrog_100W.png";
    	v = 2;
    	lives = 3;

    }
    public void jump(int direction){//Allows the frog to move based on the direction it wants to go
    	//left = 1, up = 2, right = 3, down = 4
		//Movement by the speed of each action in the desired direction
    	if(direction == 1){
    		if (x-v>=0){//Makes sure frog is within the boundaries
    			x -= v;
    		}
    	}
    	if(direction == 2){
    		if (y-v>=0){//Makes sure frog is within the boundaries
    			y -= v;
    		}
    	}
    	if(direction == 3){
    		if (x+v<=800){//Makes sure frog is within the boundaries
    			x += v;
    		}
    	}
    	if(direction == 4){
    		if (y+v<=600){//Makes sure frog is within the boundaries
    			y+= v;
    		}
    	}
    }
    public String pik(){//Returns the picture of the frog
    	return pic;
    }
    public int gitx(){//Gets the x coordinate of the frog
    	return x;
    }
    public int gitx(int n){//Increases the x coordinate by n pixels and gets the x coordinate of the frog
    	x += n;
    	return x;
    }
    public int gity(){//Gets the y coordinate of the frog
    	return y;
    }
    public int getw(){//Gets the width of the frog
    	return width;
    }
    public int geth(){//Gets the height of the frog
    	return height;
    }
    public int life(){//Gets the number of lives for the frog
    	return lives;
    }
    public int life(int x){//Decreases the number of lives by 1 and gets the number of lives for the frog
    	lives -= 1;
    	return lives;
    }
    public void reset(){//When the frog dies, and we need to reset the frog back to its initial position
    	x = 400;
    	y = 560;
    }
	public Rectangle getR(){//Gets the rectangle of the frog onject
		return new Rectangle(x+3,y+3,width, height);
	}


}
