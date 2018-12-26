class WeaponArray {
	Weapon[] weapons;
	int accessible; // this var will tell player until which index
	public final static int PISTOL = 0;
	public final static int UZI = 1;
	public final static int SHOTGUN = 2;
	public final static int FAKEWALLS = 3;
	public final static int CHARGEPACK = 4;

	public WeaponArray() {
		this.weapons = new Weapon[5];
		this.accessible = 0;

		//INSTANTIATIONS
		FiringWeapon pistol = new FiringWeapon("Pistol", 5f, 1, 50, 0);
		FiringWeapon uzi = new FiringWeapon("Uzi", 5f, 2, 150, 0);
		FiringWeapon shotgun = new FiringWeapon("Shotgun", 15f, 3, 60, 0);
		Barrier fakeWalls = new Barrier("Fake Walls", 0f, 0, 0, 0, 150);
		Explosive chargePack = new Explosive("Charge Pack", 38f, 3, 60, 50);

		this.weapons[WeaponArray.PISTOL] = pistol;
		this.weapons[WeaponArray.UZI] = uzi;
		this.weapons[WeaponArray.SHOTGUN] = shotgun;
		this.weapons[WeaponArray.FAKEWALLS] = fakeWalls;
		this.weapons[WeaponArray.CHARGEPACK] = chargePack;

		this.accessible = WeaponArray.PISTOL;
	}
	
	//This function will be clled by player when changing weapons
	///this asks the player to pass the index of weapon that will be accessed
	Weapon getWeapon(int weapon) {
		if(weapon <= accessible) {
			//System.out.println("NOW USING " + this.weapons[weapon].getName());
			return this.weapons[weapon];
		}
		else{
			//System.out.println("Cannot use "+ this.weapons[weapon].getName() + ". Lacks number of kills.");
		}
		return null;
	}

	//This function will be called by the player during its thread
	//to check and update if there will be new weapons
	void update(int kills) {
		if(kills == 2 ) this.accessible += 1;
		else if(kills == 3) this.accessible += 1;
		else if(kills == 4) this.accessible += 1;
		else if(kills == 5) this.accessible += 1;
	}
}