import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Color;

public class Sprite extends JPanel{
    private spriteSheet sheet;  //dito nahahati yung image by frame
    private BufferedImage img; 
    private int frames, state, xPos, yPos;
    private String filename; 
    private BufferedImage img2;

    public Sprite(String filename, int frames, int xPos, int yPos){
        this.loadSheet(filename, frames); //kukunin yung image with frames
        this.state = 0;
        this.xPos = xPos;
        this.yPos = yPos;
     }

    public BufferedImage getImg(){
        return this.img;
    }

    public void setFrames(int frames){
        this.frames = frames;
    }

    public void setName(String name){
        this.filename = name;
    }
    public int getXPos(){
        return this.xPos;
    }

    public int getYPos(){
        return this.yPos;
    }
    public void setXPos(int distance, int state){  //movement ng sprite
        if(state == 1){
            this.xPos += distance;
        }
        else{
            this.xPos -= distance;
        }

    }

    public void setYPos(int distance, int state){ //movement ng sprite
        if(state == 1){
            this.yPos += distance;
        }
        else{
            this.yPos -= distance;
        }
    }


    public void loadSheet(String filename, int frames){
		//Loads the image
        this.frames = frames;
			try{
				img2 = ImageIO.read(new File(filename));
			} catch (IOException e){
		}

		this.sheet = new spriteSheet(img2, this.frames);
    }
    public void loadImageLoop(){ 	/*Iloload yung image sa next frame*/
        if(this.state >= frames){
            this.state = 0; 
        }
        this.img = (this.sheet).getState(this.state++);
    }
	
	/******
	Commonly used pag namamatay yung zombie 
	******/
    public void loadImageSingle(String filename, int frames){     /*repaints each frame */
		this.loadSheet(filename, frames);
        int i = 0;
        while(i < frames){
            this.img = (this.sheet).getState(i);
            this.repaint();
            try{
                Thread.sleep(300);
            }catch(Exception e){}
            i++;
        }
    }
    public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(this.getImg(),this.getXPos(),this.getYPos(),null);
		Toolkit.getDefaultToolkit().sync();
	}
}