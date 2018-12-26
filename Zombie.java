import java.util.Random;
import java.lang.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Zombie extends Sprite implements Runnable{
	private int health;
	private int dmgPoints;
	private Image img;
	private int id;
	Player player;
	private static int populationA; //number of total instantiated zombies
	Random rand = new Random();

	public Zombie(int id, Player player, String name, int frames, int xPos, int yPos){
		super(name, frames, xPos, yPos);
		Zombie.populationA+=1;
		this.setOpaque(false);
		this.health = 15;
		this.dmgPoints = 20;
		this.id = id;
		this.player = player;

        this.setOpaque(false);
		this.setSize(750,780);
	} 
	public boolean zombieCollision(){
		/**********************************************************
		If may something sa harap niya, either magleleft or magriright siya
		***********************************************************/
        boolean test = false;
        return test;
	}
	public void setHealth(float damage){
		this.health -= damage;
	}

	public void attackPlayer(Player player){
		/*************************
		The player's health decreases
		*************************/
		this.player.decHealth(this.dmgPoints);
	}

	 public void paintComponent(Graphics g) {
		super.paintComponent(g); // important to maintain the default behavior of the method
		Graphics2D g2d = (Graphics2D)g;
		g.drawImage(img, this.getXPos(),this.getYPos(),50,50,null); //50 x 50 yung size
	}

	public void moveVertical(int dir){
		if(dir == 0){
			if(this.getYPos() == 50){} //boundary
			else{
                this.setYPos( 10, 0); //move down
                this.repaint();
			}
		}
		else{
			if(this.getYPos() + 50 == 580){} //boundary
			else{
                this.setYPos(10, 1); //move up
                this.repaint();
			}
		}
	}
	public void moveHorizontal(int dir){
		if(dir == 0){
			if(this.getXPos() == 30){} //boundary
			else{
                this.setXPos(10, 0); //move 
                this.repaint();
			}
		}
		else{
			if(this.getXPos() + 50 == 650){} //boundary
			else{
                this.setXPos(10, 1); //move
                this.repaint();
			}
		}
	}

	public void zombieMove(Player player){
		int randomizer = rand.nextInt(2);
		int x = player.getXPos();
		int y = player.getYPos();
		if(x == this.getXPos()){ //if same yung vertical position ng player sa zombie
			if(java.lang.Math.abs(y - this.getYPos()) == 10){ //
				this.attackPlayer(player);
				if(y > this.getYPos()){ //if nasa baba si player ng zombie
                    player.moveDown(30);
				}
				else if(y<this.getYPos()){ //if nasa taas si player ng zombie
					player.moveUp(30);
				}
			}
			else{
				if(y > this.getYPos()){ //if  nasa baba si player
					if(this.zombieCollision() == true){
                        this.moveHorizontal(randomizer);
					}
					else{
                        this.loadSheet("ZombieDown.png", 8); //mag move towards player, downwards
                        this.loadImageLoop();
                        this.moveVertical(1);
					}
				}
				else{ //if nasa taas si player
					if(this.zombieCollision() == true){
                        this.moveHorizontal(randomizer);
                    }
					else{
                        this.loadSheet("ZombieUp.png", 8); //mag move towards player, upwards
                        this.loadImageLoop();
                        this.moveVertical(0);
					}
				}
			}
		}
		else if(y == this.getYPos()){ //if nasa same y position
			if(java.lang.Math.abs(x - this.getXPos()) == 10){
				this.attackPlayer(player); 
				if(x > this.getXPos()){ //if nasa right si player ng zombie
					player.moveRight(30);
				}
				else if(x<this.getXPos()){
					player.moveLeft(30);
				}
			}
			else{
				if(x > this.getXPos()){ //if nasa right si player ng zombie
					if(this.zombieCollision() == true){
                        this.moveVertical(randomizer);
					}
					else{
                        this.loadSheet("ZombieRight.png", 8);
                        this.loadImageLoop();
                        this.moveHorizontal(1);
					}
				}
				else{ //if nasa left si player ng zombie
					if(this.zombieCollision() == true){
                        this.moveVertical(randomizer);
					}
					else{
                        this.loadSheet("ZombieLeft.png", 8);
                        this.loadImageLoop();
                        this.moveHorizontal(0);
					}
				}
			}
		}

		else{ //if hindi same yung both x and y pos
			if(java.lang.Math.abs(x - this.getXPos()) == java.lang.Math.abs(y - this.getYPos())){ //gets the distance of the player to zombie
				//checks if may harang, tapos if meron move right or left. Randomizer magdedecide ng movement
				if(randomizer == 0){ 
					if(x > this.getXPos()){
						if(this.zombieCollision() == true){
                            this.moveVertical(randomizer);
                        }
                        else{
							/*Zombie moves to the right*/
                            this.loadSheet("ZombieRight.png", 8);
                            this.loadImageLoop();
                            this.moveHorizontal(1);
                        }
					}
					else{
						if(this.zombieCollision() == true){
                            this.moveVertical(randomizer);
                        }
                        else{
							/*Zombie moves to the left*/
                            this.loadSheet("ZombieLeft.png", 8);
                            this.loadImageLoop();
                            this.moveHorizontal(0);
                        }
					}
				}
				else{
					if(y > this.getYPos()){ //if nasa baba si player
                        if(this.zombieCollision() == true){
                            this.moveHorizontal(randomizer);
                        }
                        else{

                            this.loadSheet("ZombieDown.png", 8); //move down si zombie
                            this.loadImageLoop();
                            this.moveVertical(1);
                        }
                    }
                    else{ //if nasa taas si player
                        if(this.zombieCollision() == true){
                            this.moveHorizontal(randomizer);
                        }
                        else{
                            this.loadSheet("ZombieUp.png", 8); //move up si zombie
                            this.loadImageLoop();
                            this.moveVertical(0);
                        }
                    }
				}
			}

			else{ //if walang harang
				if(java.lang.Math.abs(x - this.getXPos()) > java.lang.Math.abs(y - this.getYPos())){ //gets the distance of the player to zombie
					if(y > this.getYPos()){ //if nasa baba si player
                        if(this.zombieCollision() == true){
                            this.moveHorizontal(randomizer);
                        }
                        else{
                            this.loadSheet("ZombieDown.png", 8);
                            this.loadImageLoop();
                            this.moveVertical(1);
                        }
                    }
                    else{
                        if(this.zombieCollision() == true){
                            this.moveHorizontal(randomizer);
                        }
                        else{ //if nasa taas si player
                            this.loadSheet("ZombieUp.png", 8);
                            this.loadImageLoop();
                            this.moveVertical(0);
                        }
                    }
				}
				else if(java.lang.Math.abs(x - this.getXPos()) < java.lang.Math.abs(y - this.getYPos())){ //gets the distance of the player to zombie
					/*** If may harang  ***/
					if(x > this.getXPos()){ 
						if(this.zombieCollision() == true){
                            this.moveVertical(randomizer);
                        }
                        else{
                            this.loadSheet("ZombieRight.png", 8);
                            this.loadImageLoop();
                            this.moveHorizontal(1);
                        }
					}
					else{
						if(this.zombieCollision() == true){
                            this.moveVertical(randomizer);
                        }
                        else{
                            this.loadSheet("ZombieLeft.png", 8);
                            this.loadImageLoop();
                            this.moveHorizontal(0);
                        }
					}
				}
			}
		}
	}
	static int getPopulation(){
		return Zombie.populationA;
	}
	public int getHealth(){
		return this.health;
	}
	public void run(){
		this.loadSheet("ZombieDown0.png", 1); // I-load yung static motion muna, initially
        this.loadImageLoop();
        this.repaint();
		do{
            this.zombieMove(this.player); 
            this.repaint();
            try{
                Thread.sleep(500);
            }catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }while(this.health > 0); //Habang di pa deadz ang zombie
        this.loadImageSingle("ZombieDeath.png", 15); 
		player.setKills(); //set player's kills. Update it everytime may namamatay na zombie
        this.setVisible(false); //once namatay, setVisible to false para magdisappear sa platform
    }
}
