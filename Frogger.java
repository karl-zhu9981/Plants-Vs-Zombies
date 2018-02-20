//Frogger.java
//Karl Zhu and Ibraheem Aloran
//ICS 4U
//Frogger Simple Game
//This game allows users to move a frog to the end of the map, in any direction (up, down, left, right)
//There are many obstacles in the map, some are trying to hinder your progress and some can help you
//Road Obstacles- Touch one, you will lose a life
//Water Obstacles- Jump and land on one, you will travel with the obstacle
//If you get to one of the lilypads at the end, you win.
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Frogger extends JFrame implements ActionListener,KeyListener{//Our main class. Checks for actions and keys
	JPanel cards;   	//a panel that uses CardLayout
    CardLayout cLayout = new CardLayout();
	Timer myTimer;
	GamePanel game;//The framework for our game
	private Image menuPic;//Our menu picture
    public Frogger(){
    	super("Frogger");//Our caption and using the super class
    	game = new GamePanel();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(800,600);//Size of our screen
		myTimer = new Timer(10,this);
		myTimer.start();

		menuPic = new ImageIcon("images.jpg").getImage();//Background image
		menuPic = menuPic.getScaledInstance(800,600,Image.SCALE_SMOOTH); 
		JLabel backLabel = new JLabel(new ImageIcon(menuPic));
		JLayeredPane mPage=new JLayeredPane(); 	// LayeredPane allows my to control what shows on top
		mPage.setLayout(null);

		backLabel.setSize(800,600);
		backLabel.setLocation(0,0);
		mPage.add(backLabel,1);					// The numbers I use when adding to the LayeredPane
												// are just relative to one another. Higher numbers on top.
		cards = new JPanel(cLayout);
		cards.add(mPage, "menu");
		cards.add(game, "game");
		add(cards);

    	addKeyListener(this);
    	setResizable(false);
    	setVisible(true);
    }
    public static void main(String[] args) {
		new Frogger();//Call the game to run


    }
    public void actionPerformed(ActionEvent evt){
    	Object source =evt.getSource();
		if(source==myTimer){
			//Runs the main methods to operate our game
			game.move();
    		game.traffic();
    		game.gameState();
   			game.repaint();
    	}
    	if(game.state()==1){
    		cLayout.show(cards,"menu");
		    myTimer.start();
		    requestFocus();//Gets the focus of the program to our game
    	}
    }
    public void keyTyped(KeyEvent e){}//Check the key typed
    public void keyPressed(KeyEvent e){//Check the keys being pressed
    	game.setKey(e.getKeyCode(),true);//Sets the key boolean to true
    	if (e.getKeyCode()==KeyEvent.VK_ENTER){//If enter is pressed, the game will start
            cLayout.show(cards,"game");
		    myTimer.start();
		    requestFocus();
        }
    }
     public void keyReleased(KeyEvent e) {//Sets key boolean to false
    	game.setKey(e.getKeyCode(),false);
    }

}

class GamePanel extends JPanel{
	//Our objects- Cars, Trucks, Turtles, Logs and the user's frog
	private RoadObstacle car, car1, car2, car3, car4, car5;
	private RoadObstacle truck, truck1, truck2, truck3, truck4, truck5;
	private WaterObstacle turtle, turtle1, turtle2, turtle3, turtle4, turtle5;
	private WaterObstacle log, log1, log2, log3, log4;
	private Frog frog;
	////The arraylists which contains our objects (road and water obstacles, frog sprite pics and the winning rectangles)
	private ArrayList<RoadObstacle> rObstacles = new ArrayList<RoadObstacle> ();
	private ArrayList<WaterObstacle> wObstacles = new ArrayList<WaterObstacle> ();
	private ArrayList<Image> frogPics = new ArrayList<Image>();
	private ArrayList<Rectangle> winRects = new ArrayList<Rectangle>();
	//The arraylists of the winning locations by the winning rectangles and the integers of the whether the frog lands on the water obstacles
	private ArrayList<Integer> won = new ArrayList<Integer>();
	private ArrayList<Integer> checker = new ArrayList<Integer>();
	//The 4 winning rectangles by the lilypads
	Rectangle base1 = new Rectangle(665,5,70,40);
	Rectangle base2 = new Rectangle(470,5,70,40);
	Rectangle base3 = new Rectangle(295,5,65,40);
	Rectangle base4 = new Rectangle(105,5,70,40);
	
	private boolean [] keys;//Determines which keys have been pressed down
	private Image back, frogs, cars, trucks, turtles, logs, fPics, heart;//The images that we need for our objects and background
	private int end, count, landed, score;//Variables for game state, which water obstacle we are on and score 
	//Variables for sprites
	private int rotate = 0;
	private int cycle = 0;
	private int info = 20;
	public GamePanel(){
		//Declaring all of the objects 
		car = new RoadObstacle(1,1);
		//car1 = new RoadObstacle(1,2);
		car2 = new RoadObstacle(1,3);
		car3 = new RoadObstacle(3,1);
		//car4 = new RoadObstacle(3,2);
		car5 = new RoadObstacle(3,3);
		truck = new RoadObstacle(2,1);
		//truck1 = new RoadObstacle(2,2);
		truck2 = new RoadObstacle(2,3);
		//truck3 = new RoadObstacle(4,1);
		truck4 = new RoadObstacle(4,2);
		//truck5 = new RoadObstacle(4,3);
		turtle = new WaterObstacle(1,1);
		turtle1 = new WaterObstacle(1,2);
		turtle2 = new WaterObstacle(1,3);
		turtle3 = new WaterObstacle(3,1);
		//turtle4 = new WaterObstacle(3,2);
		turtle5 = new WaterObstacle(3,3);
		log = new WaterObstacle(2,1);
		log1 = new WaterObstacle(4,2);
		log2 = new WaterObstacle(4,1);
		log3 = new WaterObstacle(4,3);
		log4 = new WaterObstacle(2,3);
		//Adds all the objects into its spearate arraylists
		rObstacles.add(car);
		//rObstacles.add(car1);
		rObstacles.add(car2);
		rObstacles.add(car3);
		//rObstacles.add(car4);
		rObstacles.add(car5);
		rObstacles.add(truck);
		//rObstacles.add(truck1);
		rObstacles.add(truck2);
		//rObstacles.add(truck3);
		rObstacles.add(truck4);
		//rObstacles.add(truck5);
		wObstacles.add(turtle);
		wObstacles.add(turtle1);
		wObstacles.add(turtle2);
		wObstacles.add(turtle3);
		wObstacles.add(turtle5);
		wObstacles.add(log);
		wObstacles.add(log1);
		wObstacles.add(log2);
		wObstacles.add(log3);
		wObstacles.add(log4);
		System.out.println(wObstacles.size());
		winRects.add(base1);
		winRects.add(base2);
		winRects.add(base3);
		winRects.add(base4);
		frog = new Frog();//Our frog object is initialized
		keys = new boolean[KeyEvent.KEY_LAST+1];//Array of boolean values for keys
		back= new ImageIcon("background.jpg").getImage();//Background picture of our game
		back = back.getScaledInstance(800,600,Image.SCALE_SMOOTH);
		//Sets the images of the objects in our game
		frogs = new ImageIcon(frog.pik()).getImage();
		frogs = frogs.getScaledInstance(35,35,Image.SCALE_SMOOTH);
		cars = new ImageIcon(car.pik()).getImage();
		cars = cars.getScaledInstance(car.geth(),car.getw(),Image.SCALE_SMOOTH);
		trucks = new ImageIcon(truck.pik()).getImage();
		trucks = trucks.getScaledInstance(truck.geth(),truck.getw(),Image.SCALE_SMOOTH);
		turtles = new ImageIcon(turtle.pik()).getImage();
		turtles = turtles.getScaledInstance(turtle.getw(),turtle.geth(),Image.SCALE_SMOOTH);
		logs = new ImageIcon("log.png").getImage();
		logs = logs.getScaledInstance(log.getw(),log.geth(),Image.SCALE_SMOOTH);
		heart = new ImageIcon("heart.png").getImage();
		heart = heart.getScaledInstance(20,20,Image.SCALE_SMOOTH); 
		for(int i=0;i<8;i++){//Adds the frog sprite pictures to an arraylist
			fPics = new ImageIcon((i)+".png").getImage();
			fPics = fPics.getScaledInstance(35,35,Image.SCALE_SMOOTH);
			frogPics.add(new ImageIcon(fPics).getImage());
		}
		System.out.println(frogPics.size());
		setSize(800,600);//Our screen size
	}
	public void setKey(int k, boolean v){//Set the key boolean method
		keys[k] = v;
	}
	public void traffic(){//The movement of the road and water obstacles in thier lanes
		for(int i=0;i<rObstacles.size();i++){
			rObstacles.get(i).drive();
		}
		for(int i=0;i<wObstacles.size();i++){
			wObstacles.get(i).moveObstacle();
		}
	}
	public void move(){//Moving the frog to our desired location
		if(keys[KeyEvent.VK_RIGHT] ){//Goes right
			cycle = 6;//Variable for sprites (Determine the set of the images to move)
			frog.jump(3);//Calls the jump method by the frog
		}
		else if(keys[KeyEvent.VK_LEFT] ){//Goes left
			cycle = 4;
			frog.jump(1);
		}
		else if(keys[KeyEvent.VK_UP] ){//Goes up
			cycle = 0;
			frog.jump(2);
			score+=10;//Score
		}
		else if(keys[KeyEvent.VK_DOWN] ){//GOes down
			cycle = 2;
			frog.jump(4);
			score-=10;//Score
		}
		else{//Stationary movement
			cycle = 8;
		}
	}
	public void gameState(){
		/*if(winRects.size() == 0){
			end = 1;
		}*/
		if(frog.life()==0){//If the frog has no more lives, then they lose
			System.out.println("LOSE");
			end = 1;
		}	
		else{//Checks for intersection among the frog and road obstacles, and confinement between the frog and water obstacles
			for (int i=0; i<rObstacles.size(); i++){//Touches the road obstacles, then they lose a life and we reset it
				if (rObstacles.get(i).roadCollision(frog.getR())){
					frog.reset();
					System.out.println("Lives: "+frog.life(1));

				}
			}
			if(frog.gity()<274){
				for (int i=0; i<wObstacles.size(); i++){//Overlaps the water obstacles, then they move with the water obstacle
					if (frog.getR().contains(wObstacles.get(i).getR())){
						landed = i
						frog.setx(wObstacles.get(i).gitx());
						checker.add(1);//We add a checker to show that it has landed
					}
					else{//Does not land
						//System.out.println("NO");
						checker.add(0);
					}
				}
				for(int i=checker.size()-1;i>=0;i--){//Runs through the water checker list
					if(checker.get(i)==1){
						count = 1;
						//Frog moves along with the water obstacle
						checker.remove(i);
						//System.out.println(count);
					}
					else{
						checker.remove(i);
					}
				}
				if(count == 0){//No confinement, then we reset the frog
					frog.reset();
					System.out.println("NO");
				}
				else{
					System.out.println("YES");
					count = 0;
				}	
			}
			
			for(int i=0;i<winRects.size();i++){//Checks if the frog is on the win rectangles and the frog lands there
				if(frog.getR().intersects(winRects.get(i))){
					won.add(i+1);
					score+=100;
					winRects.remove(i);
					frog.reset();//Another frog is now initiated
				}
			}
			//System.out.println(checker.size());
		}
	}
	public int state(){//The state of the game
		return end;
	}
	public void paintComponent(Graphics g){
		g.drawImage(back,0,0,this);//Draws the background image at the top corner
		/*g.setColor(Color.BLUE);
        g.fillRect(frog.gitx()+3,frog.gity()+3,frog.getw(),frog.geth());
        g.setColor(Color.RED);
        g.fillRect(log.gitx(),log.gity(),log.getw(),log.geth());
        g.setColor(Color.RED);
        g.fillRect(log1.gitx(),log1.gity(),log1.getw(),log1.geth());
        g.setColor(Color.YELLOW);
        g.fillRect(turtle.gitx(),turtle.gity(),turtle.geth(),turtle.getw()+10);*/
        
        //Draws the car, truck, logs, and turtle objects based on their location
		g.drawImage(cars,car.gitx(),car.gity(),this);
		g.drawImage(cars,car2.gitx(),car2.gity(),this);
		g.drawImage(cars,car3.gitx(),car3.gity(),this);
		g.drawImage(cars,car5.gitx(),car5.gity(),this);
		g.drawImage(trucks,truck.gitx(),truck.gity(),this);
		g.drawImage(trucks,truck2.gitx(),truck2.gity(),this);
		g.drawImage(trucks,truck4.gitx(),truck4.gity(),this);
		g.drawImage(logs,log.gitx(),log.gity(),this);
		g.drawImage(logs,log1.gitx(),log1.gity(),this);
		g.drawImage(logs,log2.gitx(),log2.gity(),this);
		g.drawImage(logs,log3.gitx(),log3.gity(),this);
		g.drawImage(logs,log4.gitx(),log4.gity(),this);
		g.drawImage(turtles,turtle.gitx(),turtle.gity(),this);
		g.drawImage(turtles,turtle1.gitx(),turtle1.gity(),this);
		g.drawImage(turtles,turtle2.gitx(),turtle2.gity(),this);
		g.drawImage(turtles,turtle3.gitx(),turtle3.gity(),this);
		//g.drawImage(turtles,turtle4.gitx(),turtle4.gity(),this);
		g.drawImage(turtles,turtle5.gitx(),turtle5.gity(),this);
		
		//g.setColor(Color.RED);
        //g.fillRect(rObstacles.get(0).gitx(),rObstacles.get(0).gity(),rObstacles.get(0).geth(),rObstacles.get(0).getw());
        //g.drawImage(frogPics.get(2),frog.gitx(),frog.gity(),this);
        /*g.setColor(Color.YELLOW);
        g.fillRect(665,5,70,40);
        g.setColor(Color.YELLOW);
        g.fillRect(470,5,70,40);
        g.setColor(Color.YELLOW);
        g.fillRect(295,5,65,40);
        g.setColor(Color.YELLOW);
        g.fillRect(105,5,70,40);*/
        
        //Sets the sprites for the cycle of images to do each action
        if(cycle == 8){//Stationary movement
			g.drawImage(frogPics.get(0),frog.gitx(),frog.gity(),this);
		}
		else{//Action performed
			g.drawImage(frogPics.get(cycle+rotate/10),frog.gitx(),frog.gity(),this);
		}
        rotate += 1;//Rotating variable for the sprites
        if(rotate == 20){
        	rotate = 0;
        }
        for(int a=0;a<won.size();a++){//Gets to the end of the game
        //Draws the frog at the corresponding lilypad that it lands on
        	if(won.get(a) == 1){
        	g.drawImage(frogs,680,10,this);
	        }
	        if(won.get(a) == 2){
	        	g.drawImage(frogs,485,10,this);
	        }
	        if(won.get(a) == 3){
	        	g.drawImage(frogs,310,10,this);
	        }
	        if(won.get(a) == 4){
	        	g.drawImage(frogs,120,10,this);
	        }
        }
        //Colour and type of font of number of lives
        g.setColor(Color.RED);
		g.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
		g.drawString("Lives: "+frog.life(),10,30);//Draws the number of lives the frog has
		g.drawString("Score: "+score,760,30);//Draws the number of lives the frog has
   
        //System.out.println(won.get());
	}
}
