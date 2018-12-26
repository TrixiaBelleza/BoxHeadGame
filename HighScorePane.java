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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Toolkit;
import java.util.Iterator;
import javax.swing.JScrollPane;

public class HighScorePane extends JPanel {
	private Image img, background;
	String holder;

	private MainFrame panelHolder;

	public HighScorePane(MainFrame panelHolder){
		this.panelHolder = panelHolder;
		this.initUI(panelHolder);
	}
	
	private void initUI(MainFrame panelHolder){
		
	this.setLayout(new BorderLayout());
		
		//Textarea of high scores//
		JTextArea textarea = new JTextArea(20, 50);
		textarea.setFont(new Font("Courier", Font.BOLD, 15));
		textarea.setForeground(Color.BLACK);
		textarea.setOpaque(false);
		textarea.setEditable(false);
		//Add scroll to text area
		JScrollPane scroll = new JScrollPane(textarea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//Set size of scroll
		scroll.setSize( 100, 100 );
		//JPanel containg text area
		JPanel scoreList = new JPanel();
		scoreList.setPreferredSize(new Dimension(5,5));
		scoreList.setBorder(BorderFactory.createEmptyBorder(100,50,100,50));
		scoreList.setOpaque(false);
        
		textarea.append("\t USERNAME       SCORE \t \t" + "\n\n" );
		if(!GamePanel.listOfScores.isEmpty()) {
			Iterator<String> iter = GamePanel.listOfScores.keySet().iterator();

   		    while(iter.hasNext()){
        		String p = iter.next();
        		Integer l = GamePanel.listOfScores.get(p);
        		textarea.append("\t " +p + " . . . . . . . . . " + l +"\t \t" +"\n");
        	}
		}
		
		//Add text area to jpanel
		scoreList.add(scroll);
		this.add(scoreList, BorderLayout.CENTER);

		JPanel panelOfButtons = new JPanel();
		JButton backButton = addButton("Back",panelOfButtons);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				//when start button is pressed, start game
				panelHolder.showMenuPane();
			}
		});
		panelOfButtons.setOpaque(false);
		this.add(panelOfButtons, BorderLayout.SOUTH);
	
		this.setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		try{
			//Adds background image to the pane
			background= ImageIO.read(new File("boxHeadBG.jpg")); 
		} catch(Exception e){}
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(this.background,0,0, 685, 660, null);
		Toolkit.getDefaultToolkit().sync();
		
		try{
			//Adds the image "hs2" to the pane
			img = ImageIO.read(new File("hs2.png"));
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		g.drawImage(img, 200,1, 300, 100,null);
	}
	
	/****
	Adds button to container 
	****/
	private static JButton addButton(String text, Container container){
		JButton button = new JButton(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(button);
		return button;
	}
}