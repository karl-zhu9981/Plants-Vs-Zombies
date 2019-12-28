//Bullet.java
//ICS 4U FSE
//This makes the bullets objects, the projectiles that are shot by our plants in our game. 
//Ice peashooter shoots ice pellets which slows down zombies
//Double peashooter shoots it twice as fast
//Single peashooter shoots it at the normal speed
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Bullet {
	private int x,y,v,damage, type;//x, y location, the damage it deals, and the type of the bullet
	private Image pic;//The picture of the bullet
    public Bullet(int xx, int yy, int dam, Image pik, int tipe){
    	x = xx;
    	y = yy;
    	damage = dam;
    	v = 3;
    	pic = pik;
    	type = tipe;
    }
    public void draw(Graphics g){//Draws the bullet
		g.drawImage(pic,x,y,null);
	}
    public void move(){//Moves the bullet by the speed
    	x += v;
    }
    public int getX(){//Gets the x-coordinate of the bullet
    	return x;
    }
    public int getY(){//Gets the y-coordinate of the bullet
    	return y;
    }
    public int getV(){//Gets the velocity of the bullet
    	return v;
    }
    public int getDam(){//Gets the damage that bullet deals
    	return damage;
    }
    public int getType(){//Gets the type of the bullet
    	return type;
    }
}




