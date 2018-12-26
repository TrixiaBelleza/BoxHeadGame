import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.event.*;
import javax.swing.JFrame;
import java.io.*;
import java.util.Iterator;
import java.util.HashMap;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/***************************************************
The class MenuPanel is a JPanel.
	The MenuPanel contains the buttons to access other panels.
	The MenuPanel also displays the title of the Game and the exit button
that will save the record of score
***************************************************/
public class MenuPanel extends JPanel {
	//ATTRIBUTES
	//The MainFrame panelHolder
	private MainFrame panelHolder;
	//The GamePanel
	private GamePanel gamepanel;
	private Image background;
	
	//CONSTRUCTOR
	public MenuPanel(MainFrame panelHolder, GamePanel gampanel){
		this.panelHolder = panelHolder;
		//Initialize MenuPanel
		this.initUI(panelHolder);
		this.gamepanel = gamepanel;
		//At the initialization of the MenuPanel, it will load the scores of the previous player in the file
		GamePanel.listOfScores = this.loadScore();
	}

	/***************************************************
	This function initializes the MainFrame
	***************************************************/
	private void initUI(MainFrame panelHolder){
		this.setBackground(Color.BLACK);
		
		//Thid JPanel will contain the icon for boxHead
		JPanel title_pane = new JPanel();
		//Personalization of title_pane
		title_pane.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		title_pane.setLayout(new BorderLayout());
		title_pane.setPreferredSize(new Dimension(700,550));
		title_pane.setOpaque(false);
		
		//Creates a JLabel that is an icon
		JLabel title = new JLabel();
		title.setIcon(new ImageIcon("boxhead.png"));
		title.setForeground(Color.WHITE);
		title.setOpaque(false);

		//Add the JLabel icon in the JPanel title_pane
		title_pane.add(title, BorderLayout.CENTER);

		//This is a JPanel that will hold buttons
		JPanel panelOfButtons = new JPanel();
		panelOfButtons.setPreferredSize(new Dimension(700,50));
		panelOfButtons.setOpaque(false);
		//These are instatiated buttons that will be added in the panelOfButtons//
		JButton startButton = MainFrame.addButton("Start",panelOfButtons);
		JButton instButton = MainFrame.addButton("Instructions", panelOfButtons);
		JButton highScoreButton = MainFrame.addButton("High Score", panelOfButtons);
		JButton exitButton = MainFrame.addButton("Exit",panelOfButtons);
		//ActionListeners for the instatiated buttons//
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				//when startButton is pressed, will show GamePanel
				panelHolder.showGame(GamePanel.LVL1);
			}
		});
		instButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//when insButton is pressed, will show InstructionsPane
				panelHolder.showInstructions();
			}
		});
		highScoreButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//when highScoreButton is pressed, will show HighScorePane
				panelHolder.showHighScore();
			}
		});
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//When exitButton is pressed, will call saveScore() (see below description)
				//And exits the program
				saveScore();
				System.exit(0);
			}
		});

		//Add the JPanel with the icon JLabel and the JPanel containing the buttons
		this.add(title_pane);
		this.add(panelOfButtons);
	}
	
	/***************************************************
	This function saves the recorded scores of players in a file
	***************************************************/
	private void saveScore(){
		try{
			//Instatiates a FileWriter that will write in the highscores.txt file
			FileWriter output = new FileWriter("highscores.txt");
			//This iterator will iterate through the HashMap that has the scores in the GamePanel
			Iterator<String> iter = GamePanel.listOfScores.keySet().iterator();
   		    while(iter.hasNext()){
        		String p = iter.next();
        		Integer l = GamePanel.listOfScores.get(p);
        		//For every iteration, write in the file
        		output.write(p + " " + l + "\n");
        	}
			output.close();
		} catch(Exception e) {}
	}

	/***************************************************
	This function creates a new HashMap<String, Integer> 
	from the loaded keys and values.
	It will then return the HashMap<String, Integer>
	***************************************************/
	public HashMap <String,Integer> loadScore() {
		//Instanatiate a Hashmap
		HashMap <String,Integer> listOfScores= new HashMap<String, Integer>();
		try{
			//Instantiate a reader
			BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"));
			//this String line will hold the lines read in the filw
			String line;

			while((line=reader.readLine()) != null) {
				/*For every line,
					Create a String[] with size 2
					split the line and store the splitted strings in the String[]
					put the those 2 Strings to the HashMap*/
				String[] string = new String[2];
				string = line.split(" ");
				listOfScores.put(string[0], Integer.parseInt(string[1]));
			}
            reader.close();

		} catch (Exception e){}
		return listOfScores;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		try{
			background=ImageIO.read(new File("BoxHeadMenu.jpg"));
		}catch(Exception e){}
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(this.background,0,0,685,660,null);
		Toolkit.getDefaultToolkit().sync();
	}
}

