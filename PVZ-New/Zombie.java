//Zombie.java
//ICS 4U FSE
//This makes the zombie objects, the antagonists which try to get to our house in our game. 
//They can eat our defense and they come in waves
//There are 4 types of zombies and they come continously
//Normal, speed type, female, and boss zombie
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Zombie {
	//Velocity of the zombie, it's health, the damage it deals,, its x, y location, the cycle for sprites, the type
	//The slowed down velocity when hit by a ice bullet, the eating state and the cycle of the eating movements
	private int v,hp, damage, x, y, cycle, type,s, attack, cycl;
	private Rectangle rect;//The rect of the zombie
	private Image pik;//The picture of the zombie
	//The different arraylists for the pictures to make our sprites
	private ArrayList<Image> pics = new ArrayList<Image>();
	private ArrayList<Image> attak = new ArrayList<Image>();
	
    public Zombie(int xx, int yy, int n){
    	x = xx;
    	y = yy;
    	cycle = 0;
    	type = n;
    	attack = 0;
    	if(type == 0){//Different types
    		v = 4;
    		hp = 100;
    		damage = 20;
    		for(int i=0;i<6;i++){//Loading the sprite
				pik = new ImageIcon("normal"+i+".png").getImage();
				pik = pik.getScaledInstance(60,110,Image.SCALE_SMOOTH);
				pics.add(pik);
			}
    	}
    	else if(type == 1){
    		v = 5;
    		hp = 100;
    		damage = 20;
    		for(int i=0;i<10;i++){
				pik = new ImageIcon("Walk"+(i+1)+".png").getImage();
				pik = pik.getScaledInstance(75,100,Image.SCALE_SMOOTH);
				pics.add(pik);
			}
			for(int i=0;i<8;i++){
				pik = new ImageIcon("Attack"+i+".png").getImage();
				pik = pik.getScaledInstance(75,100,Image.SCALE_SMOOTH);
				attak.add(pik);
			}
    	}
    	else if(type == 2){
    		v = 7;
    		hp = 100;
    		damage = 20;
    		for(int i=0;i<5;i++){
				pik = new ImageIcon("run"+i+".png").getImage();
				pik = pik.getScaledInstance(60,110,Image.SCALE_SMOOTH);
				pics.add(pik);
			}
    	}
    	else if(type == 3){
    		v = 4;
    		hp = 1000;
    		damage = 100;
    		for(int i=0;i<3;i++){
				pik = new ImageIcon("boss"+i+".png").getImage();
				pik = pik.getScaledInstance(50,50,Image.SCALE_SMOOTH);
				pics.add(pik);
			}
    	}
    	s = v-2;//Ice slowed speed
    	rect = new Rectangle(x,y+20,50,75);//The rectangle of the zombie
    	
    }
    public boolean shot(Bullet ball){//Checks if it got shot by a bullwt
    	if(rect.contains(ball.getX(),ball.getY())){
    		hp -= ball.getDam();//Health loses the damage by the bullet
    		if(ball.getType() == 2){//Ice bullet
    			v = s;//New slowed speed
    		}
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    public int getV(){//Gets the velocity of the zombie
    	return v;
    }
    public int getType(){//Gets the type of the zombie
    	return type;
    }
    public int getHP(){//Gets the health of the zombie
    	return hp;
    }
    public int getDam(){//Gets the damage that the zombie deals
    	return damage;
    }
    public int getX(){//Gets the x-cordinate of the zombie
    	return x;
    }
    public int getY(){//Gets the y-coordinate of the zombie
    	return y;
    }
    public void draw(Graphics g){//Draws the zombie from the pictures by the sprite cycle in the arraylist of the arraylist
    	if(attack == 0){//Non-eating movement (Normal walking)
	    	if(type == 3){//Boss zombie
	    		g.drawImage(pics.get(cycle/20),x,y+25,null);
	    	}
	    	else{//Others
	    		g.drawImage(pics.get(cycle/20),x,y,null);
	    	}
    	}
    	else{//Eating movement
	    	if(type == 1){//Female zombie
	    		g.drawImage(attak.get(cycl/20),x,y,null);//Cycle of the eating sprites
	    	}
    	}
    }
    public void sprite(){//Sprite movement
    	if(attack == 0){//Non-eating movement (Normal walking)
    		cycle += 1;//Add to the cycle of drawing
    		//Checks what type of zombie it is and checks the threshold of the cycle and whether 
    		//It can still draw the sprites before renewing
	    	if(type == 0){
	    		if(cycle == 100 && hp > 0){
	    			cycle = 0;
	    		}
	    	}
	    	else if(type == 1){
	    		if(cycle == 200 && hp > 0){
	    			cycle = 0;
	    		}
	    	}
	    	else if(type == 2){
	    		if(cycle == 80 && hp>0){
	    			cycle = 0;
	    		}
	    	}
	    	else{
	    		if(cycle == 40 && hp > 0){
	    			cycle = 0;
	    		}
	    	}
	    	if(cycle == 1 || cycle == 59){
	    		//Moving the zombie by its speed
	    		x -= v;
	    		rect.setLocation(x,y+20);//Updating the position of the zombie by its position
	    	}
    	}
    	else{//Eating movement cycle of when to draw sprites
    		cycl += 1;
    		if(type == 1){
    			if(cycl == 160){
    				cycl = 0;
    			}
    		}
    	}
   	}
   	public boolean hits(Zombie zomb){//Sees if a zombie hit a fellow zombie
   		return rect.intersects(zomb.getWrekt());
   	}
    public Rectangle getWrekt(){//Gets the rectangle of which the zombie is enclosed in
    	return rect;
    }
    public int getS(){//Gets the cycle of which to draw the sprites
    	return cycle;
    }
    public int getC(){//Gets the cycle of which to draw the sprites when eating
    	return cycl;
    }
    public int getAt(){//Gets the attack of the zombie
    	return attack;
    }
    public void setS(int n){//Sets the cycle of drawing sprites
    	cycle = n;
    }
    public void setHP(int n){//Sets the health of the zombie
    	hp = n;
    }
    public void hits(Plant plan){//Sees if the zombie hit a plant
		if(rect.intersects(plan.getWrekt())){
			if(plan.getType()==5){//If plant is cherry bomb
				hp -= plan.getDam();//Zombie is dealt the damage by it
			}
			else{//They start to eat
				attack = 1;
			}
			if(type == 1){//Female zombie
				if(cycl == 80){//After the eating movement has been performed 4 times
					plan.eaten(damage);//The plant loses health from the damage by the zombie
				}
			}
			if(plan.getHP() <= 0){//If the plant is dead
				attack= 0;//Non-eating movement (Normal walking)
			}
		}
		
	}
    
}
