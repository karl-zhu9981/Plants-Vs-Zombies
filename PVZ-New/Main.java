//Main.java
//Karl Zhu, Ibraheem Aloran and Musbah Khalaff
//ICS 4U FSE
//Plants Vs Zombies
//This game allows users to choose a plant from a choice of 6, and place it in our map to defend the house
//Then waves of zombies come and try to reach the house
//The plants are defending against the zombies by shooting, blowing up or just slowing them down
//There are 4 types of zombies that are trying to attack and if they reach the house, you will lose

//Importing from the different libraries that we will be using
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.Random;
public class Main extends JFrame implements MouseListener, ActionListener{//Our main class. Checks for actions and mouse movements
	private Image menuPic, instructionsPic;//Our menu picture
	JPanel cards;   	//a panel that uses CardLayout
    CardLayout cLayout = new CardLayout();
	Timer myTimer;
	GamePanel game;
	int page = 0;//Which page the game is on
    public Main(){
    	super("Plants Vs Zombies");//Our caption and using the super class
    	game = new GamePanel();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(1000,600);//Size of our screen
		myTimer = new Timer(10,this);
		myTimer.start();
		menuPic = new ImageIcon("menuPic.png").getImage();//Menu image
		menuPic = menuPic.getScaledInstance(1000,600,Image.SCALE_SMOOTH);
		JLabel backLabel = new JLabel(new ImageIcon(menuPic));

		instructionsPic = new ImageIcon("instructions.jpg").getImage();//Instructions image
		instructionsPic = instructionsPic.getScaledInstance(1000,600,Image.SCALE_SMOOTH);
		JLabel backLabel1 = new JLabel(new ImageIcon(instructionsPic));

		JLayeredPane mPage=new JLayeredPane(); 	// LayeredPane allows my to control what shows on top--menu page
		mPage.setLayout(null);

		JLayeredPane iPage=new JLayeredPane(); 	// Instructions page
		iPage.setLayout(null);

		backLabel.setSize(1000,600);
		backLabel.setLocation(0,0);
		backLabel1.setSize(1000,600);
		backLabel1.setLocation(0,0);
		mPage.add(backLabel,1);					// The numbers I use when adding to the LayeredPane
		iPage.add(backLabel1,1);				// are just relative to one another. Higher numbers on top.

		cards = new JPanel(cLayout);
		cards.add(mPage, "menu");
		cards.add(iPage, "instructions");
		cards.add(game, "game");
		add(cards);

    	addMouseListener(this);
    	//add(game);
    	setResizable(false);
    	setVisible(true);
    }
    public static void main(String[] args) {
		new Main();//Call the game to run
    }
    public void actionPerformed(ActionEvent evt){
    	Object source =evt.getSource();
		if(source==myTimer){
			//Runs the main methods to operate our game
			game.checkCollisions();
			game.setPlants();
			game.bullz();
			game.suns();
   			game.repaint();
    	}
    }
    @Override
	public void mouseClicked(MouseEvent e) {//The mouse is clicked
		//The game methods that can take place when mouse is clicked
		game.pick(e.getX(),e.getY());//Picking plants
		game.place(e.getX(),e.getY());//Placing plants
		game.getShovel(e.getX(), e.getY());//Shovelling plants
		game.shovelPlant(e.getX(), e.getY());
		game.sunClick(e.getX(),e.getY());//Clicking suns
		//System.out.println(e.getX());
		//System.out.println(e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e){//The mouse is entered
	}

	@Override
	public void mousePressed(MouseEvent e){//The mouse is pressed
		page +=1; //Goes to the next page after the menu if mouse is pressed
		if (page == 1){
			cLayout.show(cards,"instructions");//Instructions page is started
		}
		if (page >=2){//After our instructions, the focus is on the game
			cLayout.show(cards,"game");//Game is started
			myTimer.start();
			requestFocus();
			game.coming();
		 }
	}

	@Override
	public void mouseReleased(MouseEvent e){}//Mouse is released

	@Override
	public void mouseExited(MouseEvent e){}//Mouse is exited
}

class GamePanel extends JPanel{
	//Our various images that we use in our game
	private Image map, plant, circle, Czombie, pea4, lawn,selPic1, selPic2, selPic3, sunflower, ibul, shovel, sun,over;
	//The different rectangles of plant selection and tool selection
	private Rectangle sunRect, peaRect, iceRect, nutRect, cherryRect, dubRect, shovelRect;
	//The different integers that are needed as states and counters
	private int glower = -1;
	private int gameState = 1;
	private int placer = -1;
	private int wave = 0;
	private int flag = 0;
	private int money = 0;
	private int sunwait = 1;
	private String tool = "";//The tool that we are on now
	/*The arraylist that contains our selection menu of plants, the different lanes to place our plants,
	The enemies that are attacking, the bullets, the plants that we have to defend, the lawnmowers, the collection of suns,
	the spots on the field, place values of the field, and the images for our sprites*/
	private ArrayList<Rectangle> selection = new ArrayList<Rectangle>();
	private ArrayList<Integer> lanes = new ArrayList<Integer>();
	private ArrayList<Zombie> enemys = new ArrayList<Zombie>();
	private ArrayList<Bullet> bulls = new ArrayList<Bullet>();
	private ArrayList<Plant> defense = new ArrayList<Plant>();
	private ArrayList<Mower> lawns = new ArrayList<Mower>();
	private ArrayList<Sun> sunCollect = new ArrayList<Sun>();
	private ArrayList<Rectangle> spots = new ArrayList<Rectangle>();
	private ArrayList<Integer> field = new ArrayList<Integer>();
	private ArrayList<Image> pea2 = new ArrayList<Image>();
	private ArrayList<Image> pea3 = new ArrayList<Image>();
	private ArrayList<Image> cone = new ArrayList<Image>();

	public GamePanel(){
		//Loading all of our images and sprites
		over = new ImageIcon("over.png").getImage();
		over = over.getScaledInstance(1000,600,Image.SCALE_SMOOTH);
		sun = new ImageIcon("Sun.png").getImage();
		sun = sun.getScaledInstance(125,125,Image.SCALE_SMOOTH);
		selPic1= new ImageIcon("selecting1.jpg").getImage();
		selPic1 = selPic1.getScaledInstance(80,200,Image.SCALE_SMOOTH);
		selPic2= new ImageIcon("selecting2.jpg").getImage();
		selPic2 = selPic2.getScaledInstance(80,70,Image.SCALE_SMOOTH);
		selPic3= new ImageIcon("selecting3.jpg").getImage();
		selPic3 = selPic3.getScaledInstance(80,130,Image.SCALE_SMOOTH);
		lawn = new ImageIcon("lawn.png").getImage();
		lawn = lawn.getScaledInstance(75,65,Image.SCALE_SMOOTH);
		pea4 = new ImageIcon("Walnut.png").getImage();
		pea4 = pea4.getScaledInstance(145,160,Image.SCALE_SMOOTH);
		map = new ImageIcon("Background1.jpg").getImage();
		map = map.getScaledInstance(1000,600,Image.SCALE_SMOOTH);
		circle = new ImageIcon("green-circle-md.png").getImage();
		circle = circle.getScaledInstance(25,25,Image.SCALE_SMOOTH);
		ibul = new ImageIcon("icepea.png").getImage();
		ibul = ibul.getScaledInstance(35,35,Image.SCALE_SMOOTH);
		sunflower = new ImageIcon("sunflower.gif").getImage();
		shovel = new ImageIcon("Shovel.png").getImage();
		shovel = shovel.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		for(int i=0;i<3;i++){
			plant = new ImageIcon("ipeashoot"+i+".png").getImage();
			plant = plant.getScaledInstance(50,60,Image.SCALE_SMOOTH);
			pea2.add(plant);
		}
		for(int i=0;i<11;i++){
			Czombie = new ImageIcon("normal"+i+".png").getImage();
			Czombie = Czombie.getScaledInstance(60,110,Image.SCALE_SMOOTH);
			cone.add(Czombie);
		}
		//Adding the first row of the field as rectangles
		spots.add(new Rectangle(235,80,65,110));
		spots.add(new Rectangle(305,80,65,110));
		spots.add(new Rectangle(378,80,65,110));
		spots.add(new Rectangle(452,80,65,110));
		spots.add(new Rectangle(527,80,65,110));
		spots.add(new Rectangle(600,80,65,110));
		spots.add(new Rectangle(672,80,65,110));
		spots.add(new Rectangle(742,80,65,110));
		spots.add(new Rectangle(815,80,65,110));
		//Creating the selection menu for our plants
		sunRect = new Rectangle(25, 150, 80, 50);
		peaRect = new Rectangle(25, 220, 80, 50);
		iceRect = new Rectangle(20, 290, 80, 50);
		dubRect = new Rectangle(20, 355, 80, 50);
		nutRect = new Rectangle(20, 425, 80, 50);
		cherryRect = new Rectangle(20,490,80, 50);
		shovelRect = new Rectangle(900,530,50,50);
		//Adding the rectangles in the remaining 3 rows for the field
		for(int i=0;i<9;i++){
			Rectangle rect = new Rectangle((int)(spots.get(i).getX()),(int)(spots.get(i).getY()),65,110);
			for(int j=0;j<3;j++){
				spots.add(new Rectangle((int)(rect.getX()),(int)(rect.getY()+(115*(j+1))),65,110));
			}
		}//Adding a place holder in the field to see if there is a plant there or not
		for(int i=0;i<36;i++){
			field.add(0);
		}
		//The y coordinates of the lawn mowers
		lanes.add(408);
		lanes.add(293);
		lanes.add(178);
		lanes.add(63);
		//Creating the 4 lawn mowers
		for(int i=0;i<lanes.size();i++){
			lawns.add(new Mower(lanes.get(i)+50,lawn));
		}
		//Adding the selection rects into our selection menu
		selection.add(sunRect);
		selection.add(peaRect);
		selection.add(iceRect);
		selection.add(dubRect);
		selection.add(nutRect);
		selection.add(cherryRect);
	}
	public void pick(int x, int y){//Pick a plant that we want to use with our mouse
		for(int i=0;i<selection.size();i++){
			if(selection.get(i).contains(x,y)==true){//Checks where our mouse is on the selection menu
				if(money - new Plant(0,0,i,0).getCost()>= 0){//We can afford such plant
					placer = i;//Placer state becomes the type that we want
				}
			}
		}
	}
	public void place(int x, int y){//Placing the plants relative to where the mouse is
		if(placer != -1){
			for(int i=0;i<spots.size();i++){
				if(spots.get(i).contains(x,y)){//Checks where our mouse wants to place the plant
					if(field.get(i) == 0){//If there was not a plant there
						field.set(i,1);//We set a place value to show there is now
						//We add a new plant at that location to our defense
						defense.add(new Plant((int)(spots.get(i).getX()),(int)(spots.get(i).getY()+33),placer,i));
						money -= new Plant(0,0,placer,0).getCost();//We subtract the cost of that plant from our money
						placer = -1;//No plant is selected now
					}
				}
			}
		}
	}
	public void getShovel(int mx, int my){//Activating the shovel tool
		if (shovelRect.contains(mx, my)){//If our mouse is in the shovelRect
			tool = "shovel";//The tool is now shovel
			//System.out.println("shovel");
			//System.out.println(defense.size());
		}
	}
	public void shovelPlant(int mx, int my){//Shoveling the plant and removing it from the game
		if (tool == "shovel"){
        	for(int i=0;i<spots.size();i++){
				if(spots.get(i).contains(mx,my)){//Checks where our mouse wants to remove something
					if(field.get(i) == 1){//If there is a plant there
						field.set(i,0);//We set a place value to show there is now not
						for(int pi = defense.size()-1; pi>=0; pi--){
							Plant p = defense.get(pi);
							if (p.getX()==(int)(spots.get(i).getX())&&p.getY()==(int)(spots.get(i).getY()+33)){
								//We get the plant that was at the spot that we clicked
								defense.remove(p);//Removes the plant from the defense
								tool = "";//Tool is now back to nothing
							}
						}
					}
				}
			}
		}
	}
	public void coming(){//Zombies are spawning and coming towards the house
		if(enemys.size() == 0){//If current wave is all dead
			wave += 1;//Next wave
			flag = 1;//Time to spawn more
		}
		if(flag == 1){
			//Spawning the different types of zombies in random lanes
			for(int i=0;i<(wave*5)+5;i++){
				Random rand = new Random();
				Random rant = new Random();
				enemys.add(new Zombie(1000,lanes.get(rant.nextInt(4)),rand.nextInt(2)));
			}
			if(wave%5 == 0){//Boss zombie
				Random rant = new Random();
				for(int i=0;i<wave/5;i++){
					enemys.add(new Zombie(1000,lanes.get(rant.nextInt(4)),3));
				}
			}
			if(wave%3 == 0){//Speed zombie
				Random rant = new Random();
				for(int i=0;i<wave/3;i++){
					enemys.add(new Zombie(1000,lanes.get(rant.nextInt(4)),2));
				}
			}
			flag = 0;
		}
	}
	public void setPlants(){//Setting the behaviours of the different types of plants
		for(int i=0;i<defense.size();i++){
			Plant plan = defense.get(i);
			//Cherry bomb
        	if(plan.getType() == 5){
        		plan.sprite();//Blows up
        		if(plan.getS()==750){//Then it is removed from the field
        			field.set(plan.getSpot(),0);
        			defense.remove(plan);
        		}
        	}
        	else{
	        	if(plan.getAt() == 1 && plan.getDam()>0){
	        		//Cherry bomb's attack and damage scene
	        		plan.sprite();
	        		if(plan.getS()==150){
						plan.setS();
					}
					if(plan.getS()==100){
						if(plan.getType() == 2){//Ice pea
							//They will shoot ice bullets
							bulls.add(new Bullet(plan.getX()+15,plan.getY()-10,plan.getDam(),ibul,plan.getType()));
						}
						else{
							//Normal type of bullets
							bulls.add(new Bullet(plan.getX()+15,plan.getY(),plan.getDam(),circle,plan.getType()));
						}
					}
	        	}
        	}
        }
	}
	public void bullz(){//Bullets and their actions
		for(int j=bulls.size()-1 ;j>=0;j--){
			Bullet bul = bulls.get(j);
        	bul.move();//Each bullet will move
        	//g.drawImage(circle,(int)(bulls.get(j).getX()),(int)(bulls.get(j).getY()),this);
        	if(bul.getX()>1100){//If it goes beyond the screen
        		//They are removed
        		bulls.remove(bul);
        	}
        	for(int i=enemys.size()-1 ;i>=0;i--){
        		//If a bullet shot a zombie, that bullet is now removed from the collection
        		Zombie zom = enemys.get(i);
        		if(zom.shot(bul)==true){
        			//System.out.println(bulls.get(j).getX());
        			bulls.remove(bul);
        		}

        	}
        }
	}

	public void checkCollisions(){//Checks for collision and critical events in the game
	        for(int j=defense.size()-1 ;j>=0;j--){
	        	Plant plan = defense.get(j);
	        	plan.setAt(0);//Setting the attack of the plant to be 0 if there are no zombies in their lane on the field
	        }
	        for(int i=enemys.size()-1 ;i>=0;i--){
        		Zombie zomb = enemys.get(i);
        		if(zomb.getX()<=100){//If a zombie reaches the house, gameState will change to signal loss
        			gameState = 0;
        		}
    			if(zomb.getHP() <= 0){//If a zombie is dead, he is removed from the enemy caucus
    				enemys.remove(zomb);
    				continue;
    			}
	        	for(int a=lawns.size()-1;a>=0;a--){
					Mower mow = lawns.get(a);
					if(mow.hits(zomb)){//If a zombie reaches a lawnmower or vice versa, that zombie is dead
		        		zomb.setHP(0);
		        		mow.setFlag();//Lawnmover gets ready to move
		        	}
		        	if(mow.getFlag()==1){
		        		mow.cut();//Lawnmover moves in their respective lane and hits all of the zombies in that lane
		        	}
		        	if(mow.getX()>1100){//If it reaches the end of the lane, the lawnmover is removed from comission and use
		        		lawns.remove(mow);
		        	}
        		}
		        for(int j=defense.size()-1 ;j>=0;j--){
		        	Plant plan = defense.get(j);
		        	if(zomb.getX()<860){//If a zombie appears on the field
		        		if(zomb.getY()+50 == plan.getY()){//If a zombie is in the same lane as a plant
		        			plan.setAt(1);//The plant is to attack
		        		}
		        	}
		        	zomb.hits(plan);//Zombie is right by the plant and starting to eat plant
		        	if(plan.getHP() <= 0){//If the zombie has eaten the plant, the plant is dead and removed
		        		defense.remove(plan);
		        	}
		        }
	        }

	}
	public void suns(){//Creating suns and securing a random position to place them
		sunwait -=1;//Timer to see if it is time to create a new sun
		//System.out.println(sunwait);
		Random rand = new Random();
		Random rant = new Random();
		if (sunwait == 0){
			//A random location
			int x = rand.nextInt(600);
			int y = rand.nextInt(400);
			Sun s1 = new Sun(rand.nextInt(600)+250, rant.nextInt(400));//Creates a sun at that location
			sunCollect.add(s1);//In our collection of suns
			sunwait = (int)(Math.random()*500)+800;//Sets a time to wait again before spawning more
		}
	}
	public void sunClick(int mx, int my){//Checks to see if sun is clicked on
		for(int i = sunCollect.size()-1; i>=0; i--){
			Sun s1 = sunCollect.get(i);
			//System.out.println(s1+"  "+mx+","+my);
			if (s1.getRect().contains(mx, my)){//Mouse is on the sun
				money+=50;//Add 50 in currency
				sunCollect.remove(s1);//Sun is decomissioned after clicked
			}
		}
	}
	public void paintComponent(Graphics g){
		g.drawImage(map,0,0,this);//Draws the map
		g.drawImage(sun,5,0,this);//Draws the sun counter
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Impact",Font.PLAIN,40));
		g.drawString(Integer.toString(money),130,100);//Draws the amount of money we have
		//The selection menu
        g.drawImage(selPic1,20, 120, this);
		g.drawImage(selPic2,20, 320, this);
		g.drawImage(selPic3,20, 390, this);
		g.drawImage(shovel, 900, 510, this);//Draws the shovel tool
		for(Plant plan:defense){
        	plan.draw(g);//Draws each plant sprite
        	plan.suns(sunCollect);//Sunflower spawning suns
        }
        for(Zombie zomb:enemys){
	        	zomb.sprite();//The cycle of zombies moving
	        	zomb.draw(g);//Draws each zombie sprite
	        	//System.out.println(zomb.getAt());
        }
        for(Mower mow : lawns){
        	mow.draw(g);//Draws each lawnmower
        }
        for(Bullet bil:bulls){
        	bil.draw(g);//Draws each bullet
        }
        for(Sun s:sunCollect){
        	s.draw(g);//Draws each sun
        }
        if(gameState == 0){//If the game's state is 0, game is over and all is lost
        	g.drawImage(over,0,0,this);
        }

     }

}
