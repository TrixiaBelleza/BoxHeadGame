import java.util.Random;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
/*LEGEND
dir = direction
*/
public class Player extends Sprite implements Runnable{
	public GamePanel gamepanel;
	String username;
	//Image
	private Image img1,img2,img3,img4;
	//Direction of player
	private int dir;
	private int prevDir;
	//Current health of player
	private int currHealth;
	//Number of kills attained by player
	private int kills;
	//Input movement
	private int move;
	private int level;
	//Timer
	private int timer;
	
	private int scoreLVL1;
	private int scoreLVL2;
	
	//ATTACKING ATTRIBUTEES
	private WeaponArray weapons;
	private Weapon currentWeapon;
	private int requestWeapon;
	private boolean attack;
	//Constants
	private static int MAX_DISTANCE = 620;
	private static int MIN_DISTANCE = 0;
	private static int MAX_LIFE = 100;
	public static int RIGHT = 1;
	public static int LEFT = 2;
	public static int UP = 3;
	public static int DOWN = 4;
	public static int NONE = 5;


	public Player(String name, int frames, int xPos, int yPos, GamePanel gamepanel) {
		super(name, frames, xPos, yPos);
		this.gamepanel=gamepanel;
		
		//If nasa level 2 na
		if(gamepanel.level == GamePanel.LVL2){
			this.scoreLVL2 = 18000 + GamePanel.tempScore;
			this.kills = 5;
			this.level = GamePanel.LVL2;
		}else { //If nasa level 1
			this.scoreLVL1 = 12000; //two minutes maximum
			this.kills = 0;
			this.level = GamePanel.LVL1;
		}

		//Initialize current health to maximum life
		this.currHealth = this.MAX_LIFE;
		//Initial direction of player is to the right
		this.dir = Player.RIGHT;
		//Intialize weapons
		this.weapons = new WeaponArray();
		//Get first weapon
		this.currentWeapon = weapons.getWeapon(WeaponArray.PISTOL);
		//Initialize requested weapon as pistol
		this.requestWeapon = WeaponArray.PISTOL;
		//Initialize to not attack
		this.attack = false;
		this.setOpaque(false);
		this.setSize(750,780);

	}
	//MOVEMENT FUNCTIONS//
	//Lets player move right
	public void moveRight(int distance) {
		if(this.getXPos() + distance < 700) this.setXPos(distance, 1); //increment x pos
		this.move = Player.NONE;
		}
	//Lets player move left
	public void moveLeft(int distance){
		if(this.getXPos() - distance > 0) this.setXPos(distance , 0); //decrement x pos
		this.move = Player.NONE;
	}
	//Lets player move up
	public void moveUp(int distance){
		if(this.getYPos() - distance > 0) this.setYPos(distance, 0); //decrement y pos
		this.move = Player.NONE;
	}
	//Lets player move down
	public void moveDown(int distance){
		if(this.getYPos() + distance < 700) this.setYPos(distance, 1); //increment y pos
		this.move = Player.NONE;
	}


	public void printCoor() {
		//System.out.println("(" + this.getXPos() + ", " + this.getYPos() + ")");
	}
	
	/********************************
	This method will do the player's movements 
	
	********************************/
	//This will do players movements
	public void playerDemo() {
		
		if(this.move == Player.RIGHT) {

			if(this.getXPos() + 50 == 650){} //if nasa boundary
			else{
                moveRight(10);
			}
			setDir(Player.RIGHT); //go right
			/*Pag nag change weapon*/
			if(this.currentWeapon == weapons.getWeapon(WeaponArray.PISTOL)){
                this.loadSheet("BobRight.png", 8);
			}
			else if(this.currentWeapon == weapons.getWeapon(WeaponArray.UZI)){
                this.loadSheet("BobRightUzi.png", 8);
			}
			else if(this.currentWeapon == weapons.getWeapon(WeaponArray.SHOTGUN)){
            this.loadSheet("BobRightShotgun.png", 8);
			}
			else{
				this.loadSheet("BobRight.png", 8);
			}
			
			this.loadImageLoop(); //load image
			repaint();
		}
		else if(this.move == Player.LEFT) {
			setDir(Player.LEFT); //go left
			if(this.getXPos() - 10 == 20){} //if nasa boundary
			else{
                moveLeft(10);
			}
			if(this.currentWeapon == weapons.getWeapon(WeaponArray.PISTOL)){
                this.loadSheet("BobLeft.png", 8);
			}
			else if(this.currentWeapon == weapons.getWeapon(WeaponArray.UZI)){
                this.loadSheet("BobLeftUzi.png", 8);
			}
			else if(this.currentWeapon == weapons.getWeapon(WeaponArray.SHOTGUN)){
                this.loadSheet("BobLeftShotgun.png", 8);
			}
			else{
				this.loadSheet("BobLeft.png", 8);
			}
			this.loadImageLoop();
			repaint();
		}
		else if(this.move == Player.UP) {
			setDir(Player.UP); //go up
			if(this.getYPos() - 10 == 50){}
			else{
                moveUp(10);
			}
			if(this.currentWeapon == weapons.getWeapon(WeaponArray.PISTOL)){
                this.loadSheet("BobUp.png", 8);
			}
			else if(this.currentWeapon == weapons.getWeapon(WeaponArray.UZI)){
                this.loadSheet("BobUpUzi.png", 8);
			}
			else if(this.currentWeapon == weapons.getWeapon(WeaponArray.SHOTGUN)){
            this.loadSheet("BobUpShotgun.png", 8);
			}
			else{
				this.loadSheet("BobUp.png", 8);
			}
			this.loadImageLoop();
			repaint();
			this.printCoor();
		}
		else if(this.move == Player.DOWN) {
			setDir(Player.DOWN); //go down
			if(this.getYPos() + 50 == 580){}
			else{
                moveDown(10);
			}
			if(this.currentWeapon == weapons.getWeapon(WeaponArray.PISTOL)){
                this.loadSheet("Bob.png", 8);
			}
			else if(this.currentWeapon == weapons.getWeapon(WeaponArray.UZI)){
                this.loadSheet("BobDownUzi.png", 8);
			}
			else if(this.currentWeapon == weapons.getWeapon(WeaponArray.SHOTGUN)){
            this.loadSheet("BobDownShotgun.png", 8);
			}
			else{
				this.loadSheet("Bob.png", 8);
			}
			this.loadImageLoop();
			repaint();
			this.printCoor();
		}
	}
	public void setRequestWeapon(int weapon) { /*Pag change ng weapon*/
		this.requestWeapon = weapon;
	}
	public void attack() {
		this.currentWeapon.fire(this.dir, this.getXPos(), this.getYPos(),this,weapons);
		this.attack = false;
		gamepanel.repaint(); //to remove the red background after the attack()
	}
	public void playerAttackDemo() {
		gamepanel.viewScore();
		gamepanel.viewLife();
		gamepanel.viewKills();
		if(this.attack) {
			this.attack();
			gamepanel.repaint(); //for red background in gamepanel (indicate damage)
			this.repaint(); //for bullet
		}
		/* Change weapon:*/
		if(this.requestWeapon != Player.NONE){ //If nagrequest ng change for weapon
			if(weapons.getWeapon(this.requestWeapon)!=null){ //accessible ang weapon
				gamepanel.viewWeapon();
				gamepanel.viewAccessible();
				this.currentWeapon= weapons.getWeapon(this.requestWeapon); //assign current weapon sa nirequest na weapon
			}
			else{
				//meaning di pa accessible
				gamepanel.viewNotAccessible();
				this.requestWeapon = Player.NONE; 
			}
		}
		this.weapons.update(this.kills); //determines kung ano na pwede iaccess by checking the kills
	}
	public void run(){
		int maxKills; //ilan dapat mapatay ni player per level
		if(this.level == GamePanel.LVL2) maxKills = this.level + 5; //if nasa level 2, max kills ay 15, including kills sa level 1
		else maxKills=this.level; 
		this.loadSheet("BobDown0.png", 1); //iloload static na bob, para kahit di pa nags-start yung game, may character na doon.
        this.loadImageLoop();
        repaint();
		while(this.currHealth > 0 && this.kills < maxKills && this.getScore() > 0){
			try{
				this.playerAttackDemo();
				Thread.sleep(100);

			}catch (Exception e) {}
			try{
				this.playerDemo();
				gamepanel.viewScore();
				if(this.level == GamePanel.LVL1) this.scoreLVL1 -= 1;
				else this.scoreLVL2 -= 1;
				Thread.sleep(1);
			}catch(Exception e) {}
		}
		if(this.currHealth <= 0) GamePanel.cardLayout.show(GamePanel.myPanel, "over");
		else if(this.kills >= maxKills){
			GamePanel.cardLayout.show(GamePanel.myPanel,"won");
		}
		if(this.level == GamePanel.LVL1) GamePanel.tempScore = this.scoreLVL1;
	}
	
	//Overrides paint component for personalization
	//@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		try{
			img1 = ImageIO.read(new File("bulletRIGHT.png"));
			img2 = ImageIO.read(new File("bulletLEFT.png"));
			img3 = ImageIO.read(new File("bulletUP.png"));
			img4 = ImageIO.read(new File("bulletDOWN.png"));
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		Graphics2D g2d = (Graphics2D)g;
		if(this.attack==true){
			switch(this.dir){
				case 1: g.drawImage(img1, this.getXPos()+50, this.getYPos()+10, 50, 50, null); break; //go right
				case 2:	g.drawImage(img2, this.getXPos()-50,this.getYPos()+10, 50, 50, null); break; //go left
				case 3: g.drawImage(img3, this.getXPos()+10,this.getYPos()-50, 50, 50, null); break; //up
				case 4: g.drawImage(img4, this.getXPos()+10, this.getYPos()+50, 50, 50, null); break; //down
			}
		}
	}
	/***********************
	GETTERS
	***********************/
	public int getKills(){
		return this.kills;
	}
	public int getScore(){
		if(this.level == GamePanel.LVL1) return this.scoreLVL1;
		return this.scoreLVL2;
	}
	public String getCurrentWeapon(){
		return this.currentWeapon.getName();
	}
	public int getHealth() {
		return this.currHealth;
	}
	public String getName(){
		return this.username;
	}
	public Weapon getWeapon(){
        return this.currentWeapon;
	}
	public boolean getAttack(){
		return this.attack;
	}
	/*******************
	End of getters
	*******************/
	
	/**********************
	SETTERS
	**********************/
	//Sets the direction of player
	public void setDir(int dir){
		this.dir=dir; //set direction
	}
	public void setAttack(boolean bool) {
		this.attack = bool;
	}
	public synchronized void setKills(){
		this.kills += 1;
		if(this.level == GamePanel.LVL1) this.scoreLVL1 += 500;
		else this.scoreLVL2 += 800;
	}
	//This function is called by the the key listener
	public void setMove(int move) {
		this.move = move;
	}
	public void setName(String name){
		this.username= name;
		System.out.println("username: "+ this.username);
	}
	//Decreases health by damage
	public void decHealth(int damage){
		this.currHealth-=damage;
	}
	//Resets health
	public void resetHealth(){
		this.currHealth=this.MAX_LIFE;
	}

	/*************
	End of Setters
	*************/
	
}

