//Plant.java
//ICS 4U FSE
//This makes the plant objects, the protagonists which try to stop the zombies in our game. 
//They can shoot, stop, generate money or blow up the zombies
//There are 6 types of plnats and they come continously
//Sunflower, peashooter, double peashooter, ice peashooter, cherrybomb, and walnut
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Plant{
	//It's health, the damage it deals,, its x, y location, the cycle for sprites, the type
	//The location relative to the field's rectangles and its cost
	private int hp,damage,x,y,cycle,attack,type,spot,cost;
	private int sunwait = 1;//The sun counter for the sunflower to spawn a sun
	private Rectangle rect;//The rectangle of the plant
	//The pictures for sprites for our plants
	private ArrayList<Image> p = new ArrayList<Image>();
	private Image plant;
	
	public Plant(int xx, int yy, int n, int s){
		//4=walnut, 1=peashooter, 3=doublepeas, 2=icepea, 5=cherry, 0=sunflower
		cycle = 0;
		attack = 0;
		type = n;
		spot = s;
    	if(n == 4){
    		x = xx;
    		y = yy;
    		cost = 50;
    		hp = 300;
    		damage = 0;
    		attack = 0;
    		plant = new ImageIcon("walnut.png").getImage();
			plant = plant.getScaledInstance(140,180,Image.SCALE_SMOOTH);
			p.add(plant);
    	}
    	else if(n == 1){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 20;
    		cost = 100;
    		for(int i=0;i<3;i++){//Loading the sprites
				plant = new ImageIcon("peashooting"+i+".png").getImage();
				plant = plant.getScaledInstance(50,60,Image.SCALE_SMOOTH);
				p.add(plant);
			}
    		
    	}
    	else if(n == 3){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 20;
    		cost = 200;
    		for(int i=0;i<3;i++){
				plant = new ImageIcon("dpea"+i+".png").getImage();
				plant = plant.getScaledInstance(50,60,Image.SCALE_SMOOTH);
				p.add(plant);
			}
    	}
    	else if(n == 2){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 20;
    		cost = 175;
    		for(int i=0;i<3;i++){
				plant = new ImageIcon("ipeashoot"+i+".png").getImage();
				plant = plant.getScaledInstance(50,60,Image.SCALE_SMOOTH);
				p.add(plant);
			}
    	}
    	else if(n == 5){
    		x = xx;
    		y = yy;
    		hp = 50;
    		damage = 150;
    		attack = 1;
    		cost = 150;
    		for(int i=0;i<15;i++){
				plant = new ImageIcon("cbomb"+i+".png").getImage();
				plant = plant.getScaledInstance(50,60,Image.SCALE_SMOOTH);
				p.add(plant);
			}
			rect = new Rectangle(x-75,y-75,200,200);
    		
    	}
    	else if(n == 0){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 0;
    		attack = 0;
    		cost = 50;
    		plant = new ImageIcon("sunflower.gif").getImage();
    		p.add(plant);
    	}
    	rect = new Rectangle(x,y,50,50);//Sets the rectangle for each sprite
	}
	public void suns(ArrayList<Sun>sunCollect){//Generates suns from our sunflowers
		if(type == 0){//Only for sunflowers
			sunwait -=1;//Timer to see if it is time to create a new sun
			//System.out.println(sunwait);
			Random rand = new Random();
			Random rant = new Random();
			if (sunwait == 0){
				Sun s1 = new Sun(x,y);//Creates a sun at that location
				sunCollect.add(s1);//In our collection of suns
				sunwait = (int)(Math.random()*700)+800;//Sets a time to wait again before spawning more
			}
		}
	}
    public int getHP(){//Gets the health of the plant
    	return hp;
    }
    public int getCost(){//Gets the cost of the plant
    	return cost;
    }
    public int setHP(int n){//Sets the health for our plants
    	return hp -= n;
    }
    public int getSpot(){//Gets the location of our plant on the field
    	return spot;
    }
    public ArrayList<Image> getPics(){//Gets the arraylist of the pics used for our plant sprites
    	return p;
    }
    public void draw(Graphics g){//Draws the from the pictures by the sprite cycle in the arraylist of pictures
    	if(type == 4){//Walnut
    		g.drawImage(p.get(cycle/50),x-40,y-70,null);
    	}
    	else{
    		g.drawImage(p.get(cycle/50),x,y,null);
    	}
    }
    public int getType(){//Gets the type of the plant
    	return type;
    }
    public int getDam(){//Gets the damage dealt by the plant
    	return damage;
    }
    public int getX(){//Gets the x-coordinate of the plant
    	return x;
    }
    public int getY(){//Gets the y-coordinate of the plant
    	return y;
    }
    public int getS(){//Gets the cycle of which to draw the sprites
    	return cycle;
    }
    public void setS(){//Sets the cycle of drawing sprites
    	cycle = 0;
    }
    public void eaten(int n){//The zombie is eating the plant and the plants loses health
    	hp -= n;
    }
    public void sprite(){//Sprite movement
    	if(type == 3 || type == 5){//Double peashooter and cherrybomb
    	//Adds to the cycle to draw the movement of the plants
    		cycle += 2;
    	}
    	else{
    		cycle += 1;
    	}
    }
    public void setAt(int n){//Sets the attack state for the plant
    	attack = n;
    }
    public int getAt(){//Gets the attack of the plant
    	return attack;
    }
    public Rectangle getWrekt(){//Gets the rectangle of which the plant is enclosed in
    	return rect;
    }
}
