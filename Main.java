import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Main extends JFrame implements ActionListener{//Our main class. Checks for actions and keys
	private Image menuPic;//Our menu picture
    public Main(){
    	super("Plants Vs Zombies");//Our caption and using the super class
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
}

class GamePanel extends JPanel implements MouseListener{
	private Image map, mapPic;
	public GamePanel(){
		map= new ImageIcon("Background1.jpg").getImage();
		mapPic = map.getScaledInstance(800,600,Image.SCALE_SMOOTH);
		setSize(800,600);
		addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mousePressed(MouseEvent e){
		Graphics g = getGraphics();
    	int mx=(e.getX());
    	int my=(e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}
