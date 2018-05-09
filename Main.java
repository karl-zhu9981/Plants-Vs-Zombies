import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Main extends JFrame implements MouseListener, ActionListener{//Our main class. Checks for actions and keys
	private Image menuPic;//Our menu picture
	JPanel cards;   	//a panel that uses CardLayout
    CardLayout cLayout = new CardLayout();
	Timer myTimer;
	GamePanel game;
    public Main(){
    	super("Plants Vs Zombies");//Our caption and using the super class
    	game = new GamePanel();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(1000,700);//Size of our screen
		myTimer = new Timer(10,this);
		myTimer.start();

		menuPic = new ImageIcon("menuPic.jpg").getImage();//Background image
		menuPic = menuPic.getScaledInstance(1000,700,Image.SCALE_SMOOTH);
		JLabel backLabel = new JLabel(new ImageIcon(menuPic));
		JLayeredPane mPage=new JLayeredPane(); 	// LayeredPane allows my to control what shows on top
		mPage.setLayout(null);

		backLabel.setSize(1000,700);
		backLabel.setLocation(0,0);
		mPage.add(backLabel,1);					// The numbers I use when adding to the LayeredPane
												// are just relative to one another. Higher numbers on top.
		cards = new JPanel(cLayout);
		cards.add(mPage, "menu");
		cards.add(game, "game");
		add(cards);

    	addMouseListener(this);
    	//add(game);
    	setResizable(true);
    	setVisible(true);
    }
    public static void main(String[] args) {
		new Main();//Call the game to run
    }
    public void actionPerformed(ActionEvent evt){
    	Object source =evt.getSource();
		if(source==myTimer){
			//Runs the main methods to operate our game
   			game.repaint();
    	}
    	/*if(game.state()==1){
    		cLayout.show(cards,"menu");
		    myTimer.start();
		    requestFocus();//Gets the focus of the program to our game
    	}*/
    }
    @Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mousePressed(MouseEvent e){
		 cLayout.show(cards,"game");
		 myTimer.start();
		 requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}
}

class GamePanel extends JPanel implements MouseListener{
	private Image map, mapPic, load, loadPic, selOne, selPic1, selTwo, selPic2;
	private int mx, my;
	private Rectangle plantRect;
	//private String page = "Game";
	public GamePanel(){
		map= new ImageIcon("Background1.jpg").getImage();
		mapPic = map.getScaledInstance(1000,700,Image.SCALE_SMOOTH);
		selOne= new ImageIcon("selecting1.jpg").getImage();
		selPic1 = selOne.getScaledInstance(80,200,Image.SCALE_SMOOTH);
		selTwo= new ImageIcon("selecting2.jpg").getImage();
		selPic2 = selTwo.getScaledInstance(80,130,Image.SCALE_SMOOTH);
		/*sunRect= new Rectangle();
		peaRect= new Rectangle();
		iceRect= new Rectangle();
		venusRect= new Rectangle();
		bombRect= new Rectangle();
		nutRect= new Rectangle();
		cherryRect= new Rectangle();*/
		//load= new ImageIcon("Background1.jpg").getImage();
		//loadPic = load.getScaledInstance(1200,750,Image.SCALE_SMOOTH);
		//setSize(800,600);
		addMouseListener(this);
	}
	public static void delay(long n){
		try{
			Thread.sleep(n);
		}
		catch(InterruptedException ex ){

		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mousePressed(MouseEvent e){
		Graphics g = getGraphics();
    	mx=(e.getX());
    	my=(e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}

	public void releaseSuns(){

	}
	//public void selectPlant(){
		//for (p:plants){
		//	if p.intersects(mx, my, 1, 1){
				//plantRect = p;
		//	}
	//	}
	//}
	public void paintComponent(Graphics g){
		/*if (page=="Loading"){
			g.drawImage(loadPic,0,0,this);
			Thread.delay(1500);
			page="Game";
		}*/
		//delay(1500);
		g.drawImage(mapPic,0,0,this);
		g.drawImage(selPic1,20, 50, this);
		g.drawImage(selPic2,20, 250, this);
	}
}
