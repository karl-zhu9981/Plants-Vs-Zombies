import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Frogger2 extends JFrame implements ActionListener,KeyListener{
	JPanel cards;   	//a panel that uses CardLayout
    CardLayout cLayout = new CardLayout();
	Timer myTimer;
	GamePanel game;
	private Image menuPic;
    public Frogger2(){
    	super("Frogger");
    	game = new GamePanel();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(800,600);
		myTimer = new Timer(10,this);
		myTimer.start();

		menuPic = new ImageIcon("menuPic.jpg").getImage();
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
		new Frogger2();


    }
    public void actionPerformed(ActionEvent evt){
    	Object source =evt.getSource();
		/*if(source==playBtn){
		    cLayout.show(cards,"game");
		    myTimer.start();
		    requestFocus();
		}*/
		if(source==myTimer){
			game.move();
    		game.traffic();
    		game.gameState();
   			game.repaint();
    	}
    	if(game.state()==1){
    		cLayout.show(cards,"menu");
		    myTimer.start();
		    requestFocus();
    	}
    }
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
    	game.setKey(e.getKeyCode(),true);
    	if (e.getKeyCode()==KeyEvent.VK_ENTER){
            cLayout.show(cards,"game");
		    myTimer.start();
		    requestFocus();
        }
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
	private ArrayList<Image> frogPics = new ArrayList<Image>();
	private ArrayList<Rectangle> winRects = new ArrayList<Rectangle>();
	Rectangle base1 = new Rectangle(665,5,70,40);
	Rectangle base2 = new Rectangle(470,5,70,40);
	Rectangle base3 = new Rectangle(295,5,65,40);
	Rectangle base4 = new Rectangle(105,5,70,40);
	private WaterObstacle log;
	private Frog frog;
	private int numLives=3;
	private boolean [] keys;
	private Image back, frogs, cars, trucks, turtles, logs, fPics;
	private int end;
	private int cycle = 0;
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
		wObstacles.add(turtle);
		wObstacles.add(turtle1);
		wObstacles.add(turtle2);
		wObstacles.add(turtle3);
		wObstacles.add(turtle4);
		wObstacles.add(turtle5);
		winRects.add(base1);
		winRects.add(base2);
		winRects.add(base3);
		winRects.add(base4);
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
		for(int i=0;i<8;i++){
			fPics = new ImageIcon((i+1)+".png").getImage();
			fPics = fPics.getScaledInstance(frog.getw(),frog.geth(),Image.SCALE_SMOOTH);
			frogPics.add(new ImageIcon(fPics).getImage());
		}
		System.out.println(frogPics.size());
		setSize(800,600);
	}
	public void setKey(int k, boolean v){
		keys[k] = v;
	}
	public void traffic(){
		car.drive();
		/*car1.drive();
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
		log.moveObstacle();*/
	}
	public void move(){
		if(keys[KeyEvent.VK_RIGHT] ){
			cycle = 8;
			frog.jump(3);
		}
		else if(keys[KeyEvent.VK_LEFT] ){
			cycle = 6;
			frog.jump(1);
		}
		else if(keys[KeyEvent.VK_UP] ){
			cycle = 2;
			frog.jump(2);
		}
		else if(keys[KeyEvent.VK_DOWN] ){
			cycle = 4;
			frog.jump(4);
		}
		else{
			cycle = 0;
		}
	}
	public void gameState(){
		if(frog.life()==0){
			System.out.println("LOSE");
			end = 1;
		}
		else if(frog.getR().intersects(base1)){
			System.out.println("WIN");
		}

		else{
			for (int i=0; i<rObstacles.size(); i++){
				if (rObstacles.get(i).roadCollision(frog.getR())){
					frog.reset();
					System.out.println("Lives: "+frog.life(1));

				}
			}
			if (frog.getY()<=200){
				for (int i=0; i<wObstacles.size(); i++){
					if (!wObstacles.get(i).waterCollision(frog.getR())){
						numLives-=1;
						frog.reset();
						System.out.println("Lives: "+frog.life(1));

					}
				}
			}

		}
	}
	public int state(){
		return end;
	}
	public void paintComponent(Graphics g){
		g.drawImage(back,0,0,this);
		/*for(int i=cycle-2;i<cycle;i++){
			g.drawImage(frogPics.get(i),frog.getX(),frog.getY(),this);
		}
		g.drawImage(cars,car.getX(),car.getY(),this);
		g.drawImage(cars,car1.getX(),car1.getY(),this);
		g.drawImage(cars,car2.getX(),car2.getY(),this);
		g.drawImage(cars,car3.getX(),car3.getY(),this);
		g.drawImage(cars,car4.getX(),car4.getY(),this);
		g.drawImage(cars,car5.getX(),car5.getY(),this);
		g.drawImage(trucks,truck.getX(),truck.getY(),this);
		g.drawImage(trucks,truck1.getX(),truck1.getY(),this);
		g.drawImage(trucks,truck2.getX(),truck2.getY(),this);
		g.drawImage(trucks,truck3.getX(),truck3.getY(),this);
		g.drawImage(trucks,truck4.getX(),truck4.getY(),this);
		g.drawImage(trucks,truck5.getX(),truck5.getY(),this);
		g.drawImage(logs,log.getX(),log.getY(),this);
		g.drawImage(turtles,turtle.getX(),turtle.getY(),this);
		g.drawImage(turtles,turtle1.getX(),turtle1.getY(),this);
		g.drawImage(turtles,turtle2.getX(),turtle2.getY(),this);
		g.drawImage(turtles,turtle3.getX(),turtle3.getY(),this);
		g.drawImage(turtles,turtle4.getX(),turtle4.getY(),this);
		g.drawImage(turtles,turtle5.getX(),turtle5.getY(),this);*/

		g.setColor(Color.RED);
        g.fillRect(rObstacles.get(0).getX(),rObstacles.get(0).getY(),rObstacles.get(0).geth(),rObstacles.get(0).getw());
        g.setColor(Color.RED);
        g.fillRect(rObstacles.get(1).getX(),rObstacles.get(1).getY(),rObstacles.get(1).geth(),rObstacles.get(1).getw());
        g.setColor(Color.RED);
        g.fillRect(rObstacles.get(2).getX(),rObstacles.get(2).getY(),rObstacles.get(2).geth(),rObstacles.get(2).getw());
        g.setColor(Color.RED);
        g.fillRect(rObstacles.get(3).getX(),rObstacles.get(3).getY(),rObstacles.get(3).geth(),rObstacles.get(3).getw());
        g.setColor(Color.WHITE);
        g.fillRect(wObstacles.get(0).getX(),wObstacles.get(0).getY(),wObstacles.get(0).geth(),wObstacles.get(0).getw());
        g.setColor(Color.WHITE);
        g.fillRect(wObstacles.get(1).getX(),wObstacles.get(1).getY(),wObstacles.get(1).geth(),wObstacles.get(1).getw());
        g.setColor(Color.WHITE);
        g.fillRect(wObstacles.get(2).getX(),wObstacles.get(2).getY(),wObstacles.get(2).geth(),wObstacles.get(2).getw());
        g.setColor(Color.WHITE);
        g.fillRect(wObstacles.get(3).getX(),wObstacles.get(3).getY(),wObstacles.get(3).geth(),wObstacles.get(3).getw());
        g.setColor(Color.WHITE);
        g.fillRect(wObstacles.get(4).getX(),wObstacles.get(4).getY(),wObstacles.get(4).geth(),wObstacles.get(4).getw());
        g.setColor(Color.BLUE);
        g.fillRect(frog.getX(),frog.getY(),frog.getw(),frog.geth());
        g.setColor(Color.YELLOW);
        g.fillRect(665,5,70,40);
        g.setColor(Color.YELLOW);
        g.fillRect(470,5,70,40);
        g.setColor(Color.YELLOW);
        g.fillRect(295,5,65,40);
        g.setColor(Color.YELLOW);
        g.fillRect(105,5,70,40);


		/*g.drawImage(logs,log1.getX(),log1.getY(),this);
		g.drawImage(logs,log2.getX(),log2.getY(),this);
		g.drawImage(logs,log3.getX(),log3.getY(),this);
		g.drawImage(logs,log4.getX(),log4.getY(),this);
		g.drawImage(logs,log5.getX(),log5.getY(),this);*/

	}
}
