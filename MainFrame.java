import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;


/***************************************************
The class MainFrame is a JFrame that has a KeyListener.
The MainFrame holds the significant panels(menuPanel, gamePanel, instructionsPane, highscorePane).
***************************************************/
class MainFrame extends JFrame implements KeyListener {
	//ATTRIBUTES//
	//The MenuPanel that will display the menu
	MenuPanel menuPanel; 
	//The GamePanel where the game begins
	GamePanel gamePanel;
	//The InstructionsPane that will display instructions
	InstructionsPane instructionsPane;
	//The HighScorePane that will display high scores
	HighScorePane highscorePane;
	//The MainFrame's layout is cardLayout
	CardLayout cardLayout;
	//This JPanel will hold all the above panels
	JPanel panelHolder;
	
	public MainFrame() {
		//Set stuffs
		this.setPreferredSize(new Dimension(690, 690));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);

		//Instantiate card layout
		this.cardLayout = new CardLayout();
		
		//This panel will implement card layout to hold other panels
		this.panelHolder = new JPanel(cardLayout);
		//Instantiate menuPanel
		this.menuPanel = new MenuPanel(this, this.gamePanel);
		//Instantiate instructionsPane
		this.instructionsPane = new InstructionsPane(this);
		
		//Adding the panels to the panel holder
		this.panelHolder.add(this.menuPanel, "Menu");
		this.panelHolder.add(this.instructionsPane, "Instructions");

		//At the start show the menuPanel
		this.showMenuPane();

		this.setContentPane(panelHolder);
		this.setFocusable(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	//KeyListener functions
	/*This will set values to the attributes of the player
	that will be continously monitored in the player's thread for the player to do specific 
	actions*/
	public void keyPressed(KeyEvent ke){
	 	if(ke.getKeyCode()==KeyEvent.VK_W) this.gamePanel.getPlayer().setMove(Player.UP);
	 	if(ke.getKeyCode()==KeyEvent.VK_S) this.gamePanel.getPlayer().setMove(Player.DOWN);
	 	if(ke.getKeyCode()==KeyEvent.VK_A) this.gamePanel.getPlayer().setMove(Player.LEFT);
	 	if(ke.getKeyCode()==KeyEvent.VK_D) this.gamePanel.getPlayer().setMove(Player.RIGHT);
	 	if(ke.getKeyCode()==KeyEvent.VK_SPACE) this.gamePanel.getPlayer().setAttack(true);
	 	if(ke.getKeyCode()==KeyEvent.VK_1) this.gamePanel.getPlayer().setRequestWeapon(WeaponArray.PISTOL);
	 	if(ke.getKeyCode()==KeyEvent.VK_2) this.gamePanel.getPlayer().setRequestWeapon(WeaponArray.UZI);
	 	if(ke.getKeyCode()==KeyEvent.VK_3) this.gamePanel.getPlayer().setRequestWeapon(WeaponArray.SHOTGUN);
	 	if(ke.getKeyCode()==KeyEvent.VK_4) this.gamePanel.getPlayer().setRequestWeapon(WeaponArray.FAKEWALLS);
	 	if(ke.getKeyCode()==KeyEvent.VK_5) this.gamePanel.getPlayer().setRequestWeapon(WeaponArray.CHARGEPACK);
	}
	public void keyTyped(KeyEvent ke){}
	public void keyReleased(KeyEvent ke){}

	/***************************************************
	This function creates a new gamePanel and adds it to the MainFrame
	and shows it
	***************************************************/
	public void showGame(int lvl){
		this.gamePanel = new GamePanel(this, lvl);
		this.panelHolder.add(this.gamePanel, "StartGame");
		this.cardLayout.show(this.panelHolder, "StartGame");
	}

	/***************************************************
	This function shows the MenuPane
	***************************************************/
	public void showMenuPane(){
		this.cardLayout.show(this.panelHolder, "Menu");
	}

	/***************************************************
	This function shows the instructionsPane
	***************************************************/
	public void showInstructions(){
		this.cardLayout.show(this.panelHolder, "Instructions");
	}

	/***************************************************
	This function creates a new HighScorePane(for it to be updated) and adds it to the MainFrame
	and shows it
	***************************************************/
	public void showHighScore(){
		this.highscorePane = new HighScorePane(this);
		this.panelHolder.add(this.highscorePane,"High Scores");
		this.cardLayout.show(this.panelHolder,"High Scores");
	}

	/***************************************************
	This function creates a JButton with the String text and puts in the Container container
	This function is called by several classes to create a button
	***************************************************/
	public static JButton addButton(String text, Container container){
		JButton button = new JButton(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setSize(new Dimension(20,20));
		button.setForeground(Color.RED);
		button.setFont(new Font("Tahoma", Font.BOLD, 16));
		container.add(button);
		return button;
	}
}


