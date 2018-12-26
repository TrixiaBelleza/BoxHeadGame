/************************************
The class Explosive is a Weapon. 
It covers the range from all four directions.
It also has to be deployed before being fired
A chargepack is an Explosive
************************************/
class Explosive extends Weapon {
	boolean deployed;	
	int xPos;
	int yPos;

	public Explosive(String name, float damage, int fireRate, int fireRangeLen, int fireRangeWid) {
		super(name, damage, fireRate, fireRangeLen, fireRangeWid);
		this.deployed = false;
		this.xPos = 0;
		this.yPos = 0;
	}

	/************************************
	This function will be called by the player when
	hits "fire" button with a current weapon of Explosive
	************************************/
	@Override
	void fire(int dir, int xPos, int yPos, Player player, WeaponArray weapons) {
		this.useExplosive(dir, xPos, yPos);
	}

	/************************************
	This Function calls necessary functions.
	If explosive is already deployed, then it will fire,
	else, if not deplyoed, then deploy
	************************************/
	void useExplosive(int dir, int xPos, int yPos) {
		if(this.deployed) this.explode();
		else this.deploy(dir, xPos, yPos);
	}

	/************************************
	This function sets the coordinates of this explosive where it will explode
	************************************/
	void deploy(int dir, int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		System.out.println("DEPLOYED AT: " + this.xPos + this.yPos);
		this.deployed = true;
	}


	/************************************
	This function will check its range and damage hitted objects
	************************************/
	void explode() {
		
		int upperRange, lowerRange, rightRange, leftRange;
		//UPPER RANGE
		if(yPos - fireRangeLen <= 0) upperRange = 0;
		else upperRange = yPos - fireRangeLen;
		//Starting from position, check upper blocks for objects
		for(int y = yPos; y > upperRange; y --) {
			for(int j=0; j<GamePanel.zombieList.size(); j++){
				if(GamePanel.zombieList.get(j).getHealth() > 0) {
					if(GamePanel.zombieList.get(j).getXPos()==this.xPos && GamePanel.zombieList.get(j).getYPos()==y) {
						GamePanel.zombieList.get(j).setHealth(this.damage);
						break;
					}
				}
			}
		}

		//LOWER RANGE
		if(yPos + fireRangeLen >= 700) lowerRange = 700;
		else lowerRange = yPos + fireRangeLen;
		//Starting from position, check lower blocks for objects
		for(int y = yPos; y < lowerRange; y ++){
			for(int j=0; j<GamePanel.zombieList.size(); j++){
				if(GamePanel.zombieList.get(j).getHealth() > 0) {
					if(GamePanel.zombieList.get(j).getXPos()==this.xPos && GamePanel.zombieList.get(j).getYPos()==y) {
						GamePanel.zombieList.get(j).setHealth(this.damage); 
						break;
					}
				}
			}
		}

		//RIGHT RANGE
		if(xPos + fireRangeWid >= 700) rightRange = 700;
		else rightRange = xPos + fireRangeWid;
		//Starting from position, check right blocks for objects
		for(int x = xPos; x < rightRange; x ++) {
			for(int j=0; j<GamePanel.zombieList.size(); j++){
				if(GamePanel.zombieList.get(j).getHealth() > 0) {
					if(GamePanel.zombieList.get(j).getXPos()==x && GamePanel.zombieList.get(j).getYPos()==this.yPos) {
						GamePanel.zombieList.get(j).setHealth(this.damage);
						break;
					}
				}
			}
		}
		//LEFT RANGE
		if(xPos - fireRangeWid <= 0) leftRange = 0;
		else leftRange = xPos - fireRangeWid;
		//Starting from position, check left blocks for objects
		for(int x = xPos; x > leftRange; x --) {
			for(int j=0; j<GamePanel.zombieList.size(); j++){
				if(GamePanel.zombieList.get(j).getHealth() > 0) {
					if(GamePanel.zombieList.get(j).getXPos()==x && GamePanel.zombieList.get(j).getYPos()==yPos) {
						GamePanel.zombieList.get(j).setHealth(this.damage);
						break;
					}
				}
			}
		}
		this.deployed = false;
	}
}