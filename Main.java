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
    	setSize(1000,600);//Size of our screen
		myTimer = new Timer(10,this);
		myTimer.start();

		menuPic = new ImageIcon("menuPic.jpg").getImage();//Background image
		menuPic = menuPic.getScaledInstance(1000,600,Image.SCALE_SMOOTH);
		JLabel backLabel = new JLabel(new ImageIcon(menuPic));
		JLayeredPane mPage=new JLayeredPane(); 	// LayeredPane allows my to control what shows on top
		mPage.setLayout(null);

		backLabel.setSize(1000,600);
		backLabel.setLocation(0,0);
		mPage.add(backLabel,1);					// The numbers I use when adding to the LayeredPane
												// are just relative to one another. Higher numbers on top.
		cards = new JPanel(cLayout);
		cards.add(mPage, "menu");
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
   			game.repaint();
    	}
    	/*if(game.state()==1){
    		cLayout.show(cards,"menu");
		    myTimer.start();
		    requestFocus();//Gets the focus of the program to our game
    	}*/
    }
    @Override
	public void mouseClicked(MouseEvent e) {
		// int x = e.getX();
		//System.out.println(x);
		game.pick(e.getX(),e.getY());
		game.place(e.getX(),e.getY());
		
		
	}
	

	@Override
	public void mouseEntered(MouseEvent e){
		/*if(e.getX()>500){
			System.out.println(1);
		}*/
		game.glow(e.getX(),e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e){
		 cLayout.show(cards,"game");
		 myTimer.start();
		 requestFocus();
		 //game.press(e.getX(),e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}
}

class GamePanel extends JPanel{
	private Image map, plant;
	private Rectangle spot = new Rectangle(20,80,65,110);
	private Plant peas;
	private int a = 0;
	private int glower = -1;
	private int placer = -1;
	private ArrayList<Plant> defense = new ArrayList<Plant>();
	private ArrayList<Rectangle> spots = new ArrayList<Rectangle>();
	private ArrayList<Integer> field = new ArrayList<Integer>();
	private ArrayList<Image> pea = new ArrayList<Image>();
	
	public GamePanel(){
		map = new ImageIcon("Background1.jpg").getImage();
		map = map.getScaledInstance(1000,600,Image.SCALE_SMOOTH);
		for(int i=0;i<3;i++){
			plant = new ImageIcon("peashooting"+i+".png").getImage();
			plant = plant.getScaledInstance(50,60,Image.SCALE_SMOOTH);
			pea.add(plant);
		}
		spots.add(new Rectangle(235,80,65,110));
		spots.add(new Rectangle(305,80,65,110));
		spots.add(new Rectangle(378,80,65,110));
		spots.add(new Rectangle(452,80,65,110));
		spots.add(new Rectangle(527,80,65,110));
		spots.add(new Rectangle(600,80,65,110));
		spots.add(new Rectangle(672,80,65,110));
		spots.add(new Rectangle(742,80,65,110));
		spots.add(new Rectangle(815,80,65,110));
		for(int i=0;i<9;i++){
			Rectangle rect = new Rectangle((int)(spots.get(i).getX()),(int)(spots.get(i).getY()),65,110);
			for(int j=0;j<3;j++){
				spots.add(new Rectangle((int)(rect.getX()),(int)(rect.getY()+(115*(j+1))),65,110));
			}
		}
		for(int i=0;i<36;i++){
			field.add(0);
		}
	}
	public void pick(int x, int y){
		if(spot.contains(x,y)==true){
			placer = 1;
		}
	}
	public void place(int x, int y){
		if(placer != -1){
			for(int i=0;i<spots.size();i++){
				if(spots.get(i).contains(x,y)){
					if(field.get(i) == 0){
						field.set(i,1);
						defense.add(new Plant((int)(spots.get(i).getX()),(int)(spots.get(i).getY()+33),placer));
						placer = -1;
					}
				}
			}
		}
		//
	}
	public void glow(int x, int y){
		if(placer != -1){
			for(int i=0;i<spots.size();i++){
				if(spots.get(i).contains(x,y)){
					glower = i;
				}
			}		
		}
	}
	public void paintComponent(Graphics g){
		g.drawImage(map,0,0,this);
		g.setColor(Color.RED);
        g.fillRect(20,80,65,110);
        if(glower != -1){
        	g.setColor(Color.YELLOW);
        	g.fillRect((int)(spots.get(glower).getX()),(int)(spots.get(glower).getY()),65,110);
        }
        /*for(int i=0;i<spots.size();i++){
        	g.setColor(Color.YELLOW);
        	g.fillRect((int)(spots.get(i).getX()),(int)(spots.get(i).getY()),65,110);
        }*/
        for(int i=0;i<defense.size();i++){
        	g.drawImage(pea.get(defense.get(i).getS()/25),(int)(defense.get(i).getX()),(int)(defense.get(i).getY()),this);
        }
        System.out.println(defense.size());

     }
		
}
