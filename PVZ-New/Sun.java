//Sun.java
//ICS 4U FSE
//This makes the sun objects, our currency in our game. 
//Users can obtain them just by clicking the suns 
//The suns random spawn in our game or the sunflowers will produce them
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Sun {
	private int x,y;//The x, y location of the sun
	private Rectangle rect;//The rectangle of the sun
	private Image pic;//The picture of the sun
	public Sun(int xx, int yy){
		x = xx;
		y = yy;
		pic = new ImageIcon("Sun.png").getImage();
		pic = pic.getScaledInstance(75,75,Image.SCALE_SMOOTH);
	}
	public void draw(Graphics g){//Draws the picture of the sun
		g.drawImage(pic,x,y,null);
	}
	public Rectangle getRect(){//Returns a rectangle of which the sun is enclosed in
		return new Rectangle (x, y, 75, 75);
	}
	public String toString(){//The stats of the rectangle
		return "" + getRect();
	}
}