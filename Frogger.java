import java.util.ArrayList;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Frogger extends JFrame implements ActionListener,KeyListener{
	JPanel cards;   	//a panel that uses CardLayout
    CardLayout cLayout = new CardLayout();
	JButton playBtn = new JButton("Play");
	Timer myTimer;
	GamePanel game;
	Image menuPic;
    public Frogger(){
    	super("Frogger");
    	game = new GamePanel();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(800,600);
    	playBtn.addActionListener(this);
		myTimer = new Timer(10,this);
		myTimer.start();

		ImageIcon back = new ImageIcon("menuPic.png");
		JLabel backLabel = new JLabel(back);
		JLayeredPane mPage=new JLayeredPane(); 	// LayeredPane allows my to control what shows on top
		mPage.setLayout(null);

		backLabel.setSize(800,600);
		backLabel.setLocation(0,0);
		mPage.add(backLabel,1);					// The numbers I use when adding to the LayeredPane
												// are just relative to one another. Higher numbers on top.
		playBtn.setSize(100,30);
		playBtn.setLocation(350,315);
		mPage.add(playBtn,2);

		cards = new JPanel(cLayout);
		cards.add(mPage, "menu");
		cards.add(game, "game");
		add(cards);

    	addKeyListener(this);
    	setResizable(false);
    	setVisible(true);
    }
    public static void main(String[] args) {
		new Frogger();


    }
    public void actionPerformed(ActionEvent evt){
    	Object source =evt.getSource();
		if(source==playBtn){
		    cLayout.show(cards,"game");
		    myTimer.start();
		    requestFocus();
		}
		else if(source==myTimer){
			game.move();
    		game.traffic();
    		game.gameState();
   			game.repaint();
    	}
    }
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
    	game.setKey(e.getKeyCode(),true);
    }
     public void keyReleased(KeyEvent e) {
    	game.setKey(e.getKeyCode(),false);
    }

}

class GamePanel extends JPanel{
	private RoadObstacle car, car1, car2, car3, car4, car5;
	private RoadObstacle truck, truck1, truck2, truck3, truck4, truck5;
	private WaterObstacle turtle, turtle1, turtle2, turtle3, turtle4, turtle5;
	private ArrayList<RoadObstacle> rObstacles = new ArrayList<RoadObstacle> ();
	private ArrayList<WaterObstacle> wObstacles = new ArrayList<WaterObstacle> ();
	private WaterObstacle log;
	private Frog frog;
	private boolean [] keys;
	private Image back, frogs, cars, trucks, turtles, logs;
	public GamePanel(){
		car = new RoadObstacle(1,1);
		car1 = new RoadObstacle(1,2);
		car2 = new RoadObstacle(1,3);
		car3 = new RoadObstacle(3,1);
		car4 = new RoadObstacle(3,2);
		car5 = new RoadObstacle(3,3);
		truck = new RoadObstacle(2,1);
		truck1 = new RoadObstacle(2,2);
		truck2 = new RoadObstacle(2,3);
		truck3 = new RoadObstacle(4,1);
		truck4 = new RoadObstacle(4,2);
		truck5 = new RoadObstacle(4,3);
		rObstacles.add(car);
		rObstacles.add(car1);
		rObstacles.add(car2);
		rObstacles.add(car3);
		rObstacles.add(car4);
		rObstacles.add(car5);
		rObstacles.add(truck);
		rObstacles.add(truck1);
		rObstacles.add(truck2);
		rObstacles.add(truck3);
		rObstacles.add(truck4);
		rObstacles.add(truck5);
		turtle = new WaterObstacle(1,1);
		turtle1 = new WaterObstacle(1,2);
		turtle2 = new WaterObstacle(1,3);
		turtle3 = new WaterObstacle(3,1);
		turtle4 = new WaterObstacle(3,2);
		turtle5 = new WaterObstacle(3,3);
		log = new WaterObstacle(1,1);
		frog = new Frog();
		keys = new boolean[KeyEvent.KEY_LAST+1];
		back= new ImageIcon("background.jpg").getImage();
		back = back.getScaledInstance(800,600,Image.SCALE_SMOOTH);
		frogs = new ImageIcon(frog.pik()).getImage();
		frogs = frogs.getScaledInstance(35,35,Image.SCALE_SMOOTH);
		cars = new ImageIcon(car.pik()).getImage();
		cars = cars.getScaledInstance(car.geth(),car.getw(),Image.SCALE_SMOOTH);
		trucks = new ImageIcon(truck.pik()).getImage();
		trucks = trucks.getScaledInstance(truck.geth(),truck.getw(),Image.SCALE_SMOOTH);
		turtles = new ImageIcon(turtle.pik()).getImage();
		turtles = turtles.getScaledInstance(turtle.getw(),turtle.geth(),Image.SCALE_SMOOTH);
		logs = new ImageIcon(log.pik()).getImage();
		logs = logs.getScaledInstance(log.getw(),log.geth(),Image.SCALE_SMOOTH);
		setSize(800,600);
	}
	public void setKey(int k, boolean v){
		keys[k] = v;
	}
	public void traffic(){
		car.drive();
		car1.drive();
		car2.drive();
		car3.drive();
		car4.drive();
		car5.drive();
		truck.drive();
		truck1.drive();
		truck2.drive();
		truck3.drive();
		truck4.drive();
		truck5.drive();
		turtle.moveObstacle();
		turtle1.moveObstacle();
		turtle2.moveObstacle();
		turtle3.moveObstacle();
		turtle4.moveObstacle();
		turtle5.moveObstacle();
		//log.moveObstacle();
	}
	public void move(){
		if(keys[KeyEvent.VK_RIGHT] ){
			frog.jump(3);
		}
		else if(keys[KeyEvent.VK_LEFT] ){
			frog.jump(1);
		}
		else if(keys[KeyEvent.VK_UP] ){
			frog.jump(2);
		}
		else if(keys[KeyEvent.VK_DOWN] ){
			frog.jump(4);
		}
	}
	public void gameState(){
		for (int i=0; i<rObstacles.size(); i++){
			if (rObstacles.get(i).roadCollision(frog.getR())){
				System.out.println("Lose");
			}
		}

	}
	public void paintComponent(Graphics g){
		g.drawImage(back,0,0,this);
		g.drawImage(frogs,frog.gitx(),frog.gity(),this);
		g.drawImage(cars,car.gitx(),car.gity(),this);
		g.drawImage(cars,car1.gitx(),car1.gity(),this);
		g.drawImage(cars,car2.gitx(),car2.gity(),this);
		g.drawImage(cars,car3.gitx(),car3.gity(),this);
		g.drawImage(cars,car4.gitx(),car4.gity(),this);
		g.drawImage(cars,car5.gitx(),car5.gity(),this);
		g.drawImage(trucks,truck.gitx(),truck.gity(),this);
		g.drawImage(trucks,truck1.gitx(),truck1.gity(),this);
		g.drawImage(trucks,truck2.gitx(),truck2.gity(),this);
		g.drawImage(trucks,truck3.gitx(),truck3.gity(),this);
		g.drawImage(trucks,truck4.gitx(),truck4.gity(),this);
		g.drawImage(trucks,truck5.gitx(),truck5.gity(),this);
		g.drawImage(logs,log.gitx(),log.gity(),this);
		g.drawImage(turtles,turtle.gitx(),turtle.gity(),this);
		g.drawImage(turtles,turtle1.gitx(),turtle1.gity(),this);
		g.drawImage(turtles,turtle2.gitx(),turtle2.gity(),this);
		g.drawImage(turtles,turtle3.gitx(),turtle3.gity(),this);
		g.drawImage(turtles,turtle4.gitx(),turtle4.gity(),this);
		g.drawImage(turtles,turtle5.gitx(),turtle5.gity(),this);

		/*g.drawImage(logs,log1.gitx(),log1.gity(),this);
		g.drawImage(logs,log2.gitx(),log2.gity(),this);
		g.drawImage(logs,log3.gitx(),log3.gity(),this);
		g.drawImage(logs,log4.gitx(),log4.gity(),this);
		g.drawImage(logs,log5.gitx(),log5.gity(),this);*/

	}
}

