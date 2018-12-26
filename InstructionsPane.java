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

public class InstructionsPane extends JPanel {
	Image img;
	private MainFrame panelHolder; //this joins the instructions pane together with the maingame and highscore panes

	public InstructionsPane(MainFrame panelHolder){
		this.panelHolder = panelHolder;
		this.initUI(panelHolder);
	}
	
	private void initUI(MainFrame panelHolder){
		
		this.setPreferredSize(new Dimension(700,700));
		
		/*Creates title pane, ito yung nasa taas na part sa UI */
		JPanel title_pane = new JPanel();
		title_pane.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		title_pane.setLayout(new BorderLayout());
		title_pane.setBackground(Color.BLACK);
		title_pane.setPreferredSize(new Dimension(700,250));
		
		
		//----------------- FOR TITLE (LABEL)
		JLabel title = new JLabel("INSTRUCTIONS");
		title.setFont(new Font("Relief BD", Font.BOLD, 100));
		title.setForeground(Color.WHITE);
		title.setOpaque(false);
		//------------------ 'FOR TITLE' UP TO HERE

		//------------------FOR INSTRUCTIONS 
		JTextArea textarea = new JTextArea(300,300);
		textarea.setEditable(false);
		textarea.setBackground(Color.BLACK);
		textarea.setFont(new Font("Courier", Font.BOLD, 15));
		textarea.setForeground(Color.WHITE);

		textarea.append("CONTROLS\n");
		textarea.append("W = UP \t \t \t 1 - PISTOL\n");
		textarea.append("S = DOWN\t \t 2- UZI\n");
		textarea.append("A = LEFT \t \t 3- SHOTGUN\n");
		textarea.append("D = RIGHT\t \t 4 - FAKE WALL\n");
		textarea.append("\t \t \t 5- CHARGE PACK\n");

		textarea.append("Instructions\n");
		textarea.append("Basically, this is a zombie game starring you.\n");
		textarea.append("Your aim is to kill all the zombies in each round\n");
		textarea.append("and unlock all the weapons in your weapon tier.\n\n");
		textarea.append("This game is based on Boxhead (series) by Sean Cooper.\n");

		//-------ADD ELEMENTS
		title_pane.add(title, BorderLayout.CENTER);
		title_pane.add(textarea);

		this.add(title_pane);
		/*
		creates buttons
		*/
		JPanel panelOfButtons = new JPanel();
		JButton backButton = addButton("Back",panelOfButtons);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				//when start button is pressed, start game
				panelHolder.showMenuPane();
			}
		});
		this.add(panelOfButtons);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		try{
			img = ImageIO.read(new File("mvt.png"));
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		Graphics2D g2d = (Graphics2D)g;
		g.drawImage(img, 200, 320, null);
	}

	/*******
	Adds button to container
	****/
	private static JButton addButton(String text, Container container){
		JButton button = new JButton(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(button);
		return button;
	}
}