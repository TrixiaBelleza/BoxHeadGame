import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
class GamePanel extends JPanel {
	//stores the listOfScores according to their respective users
	public static HashMap<String, Integer> listOfScores = new HashMap<String, Integer>(); 		
	Player player;																													
	public static Zombie zombie;
	//5 zombies
	public final static int LVL1 = 5;		
	//10 zombies			
	public final static int LVL2 = 10;																					
 	int level;
	
	String holder;
	//stores list of zombies
 	public static ArrayList <Zombie> zombieList;																	
 	//This panel will implement card layout to show other panels
	public static CardLayout cardLayout;
 	//This is a jpanel that displays game over
 	private JPanel gameOver;
	//This is a jpanel that displays "you won"
	private JPanel youWon;
 	//This is a panel that will hold the player and the zombies
 	private JPanel mainGame;
 	//This class attribute will hold the instantiated GamePanel
 	public static GamePanel myPanel;
	private Image image,image2;
	private MainFrame panelHolder;
	public static int tempScore;
	//For score,life, kills, weapon labels sa gamePanel
	private JLabel scoreLabel;
	private JLabel lifeLabel;
	private JLabel killsLabel;
	private JLabel weaponLabel;
	private JLabel notAccesibleLabel;
	
 	//Will hold the panels:
 	//GameOver - displays the word game over
 	//mainGame - contains the player and the zombies
	public GamePanel(MainFrame panelHolder, int lvl){
		try{
			image = Toolkit.getDefaultToolkit().getImage("finalbg.png"); //for the background image of the maingame
			image2 = Toolkit.getDefaultToolkit().getImage("rect.png");
		} catch(Exception e){}
		this.level = lvl; 
		this.setPreferredSize(new Dimension(700, 700));
		this.panelHolder=panelHolder;
		//Instantiate card layout for this panel to hold other panels
		this.cardLayout = new CardLayout();
		//Set layout to card layout
		this.setLayout(this.cardLayout);

		//Create panels 
		this.initGamePanel();
		this.initGameOverPanel();
		this.initYouWonPanel();

		//ADD PANELS TO PANEL HOLDER
		this.add(this.mainGame, "Main");
		this.add(this.gameOver, "over");
		this.add(this.youWon,"won");
		this.cardLayout.show(this, "Main");
		
		//Add Labels to game
		this.scoreLabel = new JLabel("Score: 0");
		scoreLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		mainGame.add(scoreLabel);
		this.lifeLabel = new JLabel("Life: 0");
		lifeLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		mainGame.add(lifeLabel);
		this.killsLabel = new JLabel("Kills: 0");
		killsLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		mainGame.add(killsLabel);
		this.notAccesibleLabel = new JLabel();
		notAccesibleLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		mainGame.add(notAccesibleLabel);
		this.viewScore();
		this.viewLife();
		this.viewKills();

		//WEAPONS LABEL
		this.weaponLabel = new JLabel("Current Weapon: PISTON");
		weaponLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		mainGame.add(weaponLabel);
		this.viewWeapon();

		//Set the instantiated GamePanel as a class attribute for the zombies and player to access
		this.myPanel = this;
		this.requestFocusInWindow();
	}

	public void viewScore(){
		scoreLabel.setBounds(45,5,100,200);
		scoreLabel.setText("Score: " + player.getScore());
	}

	public void viewLife(){
		lifeLabel.setBounds(45,10,200,300);
		lifeLabel.setText("Life: " + player.getHealth());
	}

	public void viewKills(){
		killsLabel.setBounds(45,15,300,400);
		killsLabel.setText("Kills: " + player.getKills());
	}

	public void viewWeapon(){
		weaponLabel.setBounds(45,20,1000,1100);
		weaponLabel.setText("Current Weapon: " + player.getCurrentWeapon());
	}
	public void viewNotAccessible(){
		notAccesibleLabel.setBounds(45,25,900,1000);
		notAccesibleLabel.setText("Weapon is not yet accessible!");
		
	}
	public void viewAccessible(){
		notAccesibleLabel.setBounds(45,25,900,1000);
		notAccesibleLabel.setText(" ");
	}
	
	//To paint the game's background
	public void paintComponent(Graphics g) { //java.awt.Graphics
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		if(this.player.getAttack() == true) g2d.drawImage(this.image2,0,0, 700, 680, null);
		g2d.drawImage(this.image,0,0, 690, 680, null);
		Toolkit.getDefaultToolkit().sync();
	}

	public Player getPlayer(){
		return this.player;
	}

	//CREATION OF THE MAIN GAME PANEL
	private void initGamePanel() {
		//THE MAIN GAME PANEL
		//create the main game panel
		this.mainGame = new JPanel();
		this.mainGame.setBackground(Color.BLACK);
		this.mainGame.setLayout(null);
		
		//Instantiate player and zombies and the threads
		Player player =  new Player("Bob.png", 7, 350, 350, this);
		this.player = player;

		Thread playerThread = new Thread(player);
		playerThread.start();
		
		//Add to the mainGame panel the player and the zombies
		this.mainGame.add(player);
		
		this.zombieList = new ArrayList<Zombie>();
		Random rand= new Random();
		for(int i=0;i<level;i++){

		 	int xRandom=rand.nextInt(570) + 30;
            int yRandom=rand.nextInt(500) + 40;
		 	while(xRandom%10 !=0){ //habang di siya divisible by 10
                xRandom=rand.nextInt(570) + 30;
            }
            while(yRandom%10 !=0){ //habang di siya divisible by 10
                yRandom=rand.nextInt(500) + 40;
            }
		 	Zombie zombie = new Zombie(i, this.player, "Barrel.png", 8, xRandom, yRandom);
			zombieList.add(zombie); //add each zombie to arrayList
			Thread zombieThread = new Thread(zombie);
		 	this.mainGame.add(zombie);
		 	zombieThread.start();
		}
		this.mainGame.setOpaque(false);
		this.mainGame.requestFocusInWindow();
	}

	//CREATION OF THE GAME OVER PANEL
/**********************************************************
	This function will initialize the gameOver panel.
	This will display the text "GAME OVER" and 
	displays a JTextArea where the user can input its username
	for it to be recorded. 
	This will also have a button for the user to return to the MenuPanel.
	(User is allowed to not enter any username, thus, not recording his/her score)
	**********************************************************/
	private void initGameOverPanel() {
		//Sets the gameOver panel
		this.gameOver = new JPanel();
		// this.gameOver.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
		// this.gameOver.setLayout();
		this.gameOver.setBackground(Color.BLACK);
		
		//Creates panel that will contain the a JLabel and a text field
		JPanel titlePane = new JPanel();
		titlePane.setBorder(BorderFactory.createEmptyBorder(300, 100, 10, 100));
		titlePane.setLayout(new BorderLayout());
		titlePane.setPreferredSize(new Dimension(700,550));
		titlePane.setOpaque(false);

		//Create the label for the titlePane panel
		JLabel title = new JLabel("GAME OVER");
		title.setFont(new Font("Relief BD", Font.BOLD, 75));
		title.setForeground(Color.WHITE);
		title.setOpaque(false);
		
		JLabel inform = new JLabel("Please enter name below and HIT ENTER: ");
		inform.setFont(new Font("Orator STD", Font.BOLD, 20));
		inform.setForeground(Color.WHITE);
	
		//Create JTextField for inputting username of user
		JTextField name = new JTextField();		
		//Adds listener to JTextField that will get the inputted username
		//Adds the username and the score to the hashMap of listOfScores
		name.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		//When made an action, will get the text in the textfield
	    	   String holder = name.getText();
	    	   listOfScores.put(holder, player.getScore());
	    	   tempScore = 0;
	    	}
		});
		
		//Add JLabel and text field to the titlePane
		titlePane.add(inform,BorderLayout.CENTER);
		titlePane.add(name, BorderLayout.SOUTH);
		titlePane.add(title,BorderLayout.NORTH);

		//Create Panel Button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		JButton backButton = MainFrame.addButton("Back", buttonPanel);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//when back button is clicked, goes to main menu
				panelHolder.showMenuPane();
			}
		});
		//Add the titlePane and the buttonPanel
		this.gameOver.add(titlePane);
		this.gameOver.add(buttonPanel);	
	}

	private void initYouWonPanel() {
		//THE GAME OVER PANEL
		//This panel will display the game over
		this.youWon= new JPanel();
		this.youWon.setPreferredSize(new Dimension(10,10));
		this.youWon.setBackground(Color.BLACK);

		JPanel titlePane = new JPanel(); //game over title
		titlePane.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		titlePane.setLayout(new BorderLayout());
		titlePane.setBackground(Color.BLACK);
		titlePane.setPreferredSize(new Dimension(700,550));

		//-------For TITLE (LABEL)
		JLabel title;
		title = new JLabel("YOU WON");

		title.setFont(new Font("Relief BD", Font.BOLD, 100));
		title.setForeground(Color.WHITE);
		title.setOpaque(false);

		//---------For title up to here

		//------------Add elements
		titlePane.add(title,BorderLayout.CENTER);
		this.youWon.add(titlePane);
		
		if(this.level == LVL2){ //if it ends in level 2, then prompt user to go back to the main menu but first enter name for score saving
			titlePane.setBorder(BorderFactory.createEmptyBorder(300, 100, 10, 100)); //adjust the textfield 
			
			JLabel inform = new JLabel("Please enter name below \nand HIT ENTER: ");
			inform.setFont(new Font("Orator STD", Font.BOLD, 20));
			inform.setForeground(Color.WHITE);
			//Enter username
			JTextField name = new JTextField();
			name.setPreferredSize(new Dimension(50,20));		
			name.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    	   holder = name.getText();
	    	   player.setName(holder);
			   //Add user to listOfScores hashmap
	    	   listOfScores.put(player.getName(),player.getScore()); 
	    	}
		});
		titlePane.add(title,BorderLayout.NORTH);
		titlePane.add(inform, BorderLayout.CENTER);
		titlePane.add(name,BorderLayout.SOUTH);
		
		JPanel buttonPanel = new JPanel();
		JButton backButton = addButton("Back", buttonPanel);
		buttonPanel.setBackground(Color.BLACK);

		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//when back button is clicked, goes to main menu
				panelHolder.showMenuPane();
			}
		});
		
		this.youWon.add(buttonPanel);
		}else{
			JPanel buttonPanel = new JPanel();
			JButton backButton = addButton("Back", buttonPanel);
			JButton nextGame = addButton("Next Level", buttonPanel);
			buttonPanel.setBackground(Color.BLACK);

			backButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				//when back button is clicked, goes to main menu
				panelHolder.showMenuPane();
			}
		});
			nextGame.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				//when back button is clicked, goes to main menu
				panelHolder.showGame(GamePanel.LVL2);
			}
		});
		
		this.youWon.add(buttonPanel);
		}
		
	}

	/********************************************
	This function adds a button to a container
	********************************************/
	private static JButton addButton(String text, Container container){
		JButton button = new JButton(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setSize(new Dimension(20,20));
		button.setForeground(Color.RED);
		button.setFont(new Font("Tahoma", Font.BOLD, 16));
		container.add(button);
		return button;
	}

}
