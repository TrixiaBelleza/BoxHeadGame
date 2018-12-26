/*******************
The class Weapon is a class that will be extended by all weapons
*******************/
abstract class Weapon {
	//ATTRIBUTES
	String name;
	float damage;
	int fireRate;
	int fireRangeWid;
	int fireRangeLen;

	//CONSRTUCTOR
	public Weapon(String name, float damage, int fireRate, int fireRangeLen, int fireRangeWid) {
		this.name = name;
		this.damage = damage;
		this.fireRate = fireRate;
		this.fireRangeLen = fireRangeLen;
		this.fireRangeWid = fireRangeWid;	
	}

	//prints the x and y coor
	void printCoor(int x, int y) {
		// System.out.println("(" + x + ", " + y + ")");
	}

	//Returns name of weapon
	String getName() {
		return this.name;
	}

	//This function respectively fires to its range and direction
	void fire(int dir, int xPos, int yPos, Player player, WeaponArray weapons) {}
}	