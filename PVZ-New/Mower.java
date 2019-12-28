//Mower.java
//ICS 4U FSE
//This makes the lawnmovers objects, the last line of defense in our game. 
//If a zombie gets to it, it will run over all the zombies in their lane
//There are only 4 and can only be used once
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Mower{
	private int x,y,v,damage,flag,slow;//x, y location, velocity, damage it deals, activating variable, and acceleration
	private Rectangle rect;//The rectangle of the sun
	private Image pic;//The picture of the lawnmover 
	public Mower(int yy, Image p){
		x = 155;
		y = yy;
		v = 2;
		pic = p;
		damage = 1000;
		flag = 0;
		slow = 0;
		rect = new Rectangle(x,y,60,60);
	}
	public void draw(Graphics g){//Drawing the lawnmover
		g.drawImage(pic,x,y,null);
	}
	public void cut(){//Running through the respective lane
		if(flag == 1){
			slow += 1;//Acceleration factor
			if(slow == 10){
				x += v;//It moves by the velocity of the lawnmover
				rect.setLocation(x,y);//New location of the lawnmover
				slow = 0;//Back to accelerating
			}
		}
	}
	public boolean hits(Zombie z){//Checks if the lawnmover hit a zombie
		return rect.intersects(z.getWrekt());
	}
	public int getX(){//Gets the x-coordinate of the lawnmover
		return x;
	}
	public int getY(){//Gets the y-coordinate of the lawnmover
		return y;
	}
	public int getDam(){//Gets the amount of damage it deals
		return damage;
	}
	public int getFlag(){//Gets the activation variable
		return flag;
	}
	public void setFlag(){//Sets the activation variable to move across the lane
		flag = 1;
	}
	public Rectangle getWrekt(){//Gets the rectangle of which the lawnmover is enclosed in
		return rect;
	}
	
}