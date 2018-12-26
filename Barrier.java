class Barrier extends Weapon{
	int health;
	boolean dead;
	private final static int MAX_HEALTH = 150;

	public Barrier(String name, float damage, int fireRate, int fireRangeLen, int fireRangeWid, int health) {
		super(name, damage, fireRate, fireRangeLen, fireRangeWid);
		this.health = this.MAX_HEALTH;
	}

	void fire(int dir, int xPos, int yPos, Player player, WeaponArray weapons) {
		System.out.println("PLACED" + this.name + "IN MAP");
		if(dir == Player.RIGHT) this.printCoor(xPos + 1, yPos);
		else if(dir == Player.LEFT) this.printCoor(xPos - 1, yPos);
		else if(dir == Player.UP) this.printCoor(xPos, yPos - 1);
		else if(dir == Player.DOWN) this.printCoor(xPos, yPos + 1);
	}
	/************************************
	To place barrier in map, just get from weapons list
	and put in map
	************************************/

	/************************************
	This function will be called by either player otr zombie when
	it hits the barrier
	************************************/
	void decHealth(int damage) {
		this.health -= damage;
		if(health <= 0) {} //map should delete it in array and in gui 
	}

	/************************************
	This function will be called after 
	this barrier is destroyedand removed in map,
	for it to be used again by player
	************************************/
	void revive() {	
		this.health = this.MAX_HEALTH;
	}
}