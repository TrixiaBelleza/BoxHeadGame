class FiringWeapon extends Weapon {

	public FiringWeapon(String name, float damage, int fireRate, int fireRangeLen, int fireRangeWid) {
		super(name, damage, fireRate, fireRangeLen, fireRangeWid);
	}	
	
	public void fire(int dir, int xPos, int yPos, Player player, WeaponArray weapons){
				//This variable represents the range of the fire
		int range;

		if(dir == Player.RIGHT) {
			if(xPos + this.fireRangeLen > 700) range = 700; //if the range exceeds the boundary, set the range equal to boundary
			else range = xPos + this.fireRangeLen; 

			for(int i = xPos; i < range; i ++) { //for each x position, according to the length of range
				for(int j=0; j<GamePanel.zombieList.size(); j++){ //for each zombie in the list
					if(GamePanel.zombieList.get(j).getXPos()==i && GamePanel.zombieList.get(j).getYPos()==yPos){ //if there is a zombie
						GamePanel.zombieList.get(j).setHealth(this.damage); //damage its health
						
						/* Gets the player's current weapon */
						try{
                            if(player.getWeapon() == weapons.getWeapon(WeaponArray.PISTOL)){ 
                                Thread.sleep(300);
                            }
                            else if(player.getWeapon() == weapons.getWeapon(WeaponArray.UZI)){
                                Thread.sleep(100);
                            }
							else if(player.getWeapon() == weapons.getWeapon(WeaponArray.SHOTGUN)){
                                Thread.sleep(400);
                            }
						}catch(Exception e){}
						break;
					}
				}
			}
		}

		else if(dir == Player.LEFT) {
			if(xPos - this.fireRangeLen < 0) range = 0; //if the range exceeds the boundary, set the range equal to boundary
			else range = xPos - this.fireRangeLen;

			for(int i = xPos; i > range; i--) { //for each x position, according to the length of range
				for(int j=0; j<GamePanel.zombieList.size(); j++){  //for each zombie in the list
					if(GamePanel.zombieList.get(j).getXPos()==i && GamePanel.zombieList.get(j).getYPos()==yPos){ //if there's a zombie
						GamePanel.zombieList.get(j).setHealth(this.damage); //damage its health
						/* Gets the player's current weapon */
						try{
                            if(player.getWeapon() == weapons.getWeapon(WeaponArray.PISTOL)){
                                Thread.sleep(300);
                            }
                            else if(player.getWeapon() == weapons.getWeapon(WeaponArray.UZI)){
                                Thread.sleep(100);
                            }
							else if(player.getWeapon() == weapons.getWeapon(WeaponArray.SHOTGUN)){
                                Thread.sleep(400);
                            }

						}catch(Exception e){}
						break;
					}
				}
			}
		}

		else if(dir == Player.UP) {

			if(yPos - this.fireRangeLen < 0) range = 0; //if the range exceeds the boundary, set the range equal to boundary
			else range = yPos - this.fireRangeLen;

			for(int i = yPos; i > range; i--) { //for each y position, according to the length of range
				for(int j=0; j<GamePanel.zombieList.size();j++){  //for each zombie in the list
					if(GamePanel.zombieList.get(j).getYPos()==i && GamePanel.zombieList.get(j).getXPos()==xPos){ //for each zombie in the list
						GamePanel.zombieList.get(j).setHealth(this.damage);
						/* Gets the player's current weapon */
						try{
                            if(player.getWeapon() == weapons.getWeapon(WeaponArray.PISTOL)){
                                Thread.sleep(300);
                            }
                            else if(player.getWeapon() == weapons.getWeapon(WeaponArray.UZI)){
                                Thread.sleep(100);
                            }
							else if(player.getWeapon() == weapons.getWeapon(WeaponArray.SHOTGUN)){
                                Thread.sleep(400);
                            }
						}catch(Exception e){}
						break;
					}
				}
			}
		}

		else if(dir == Player.DOWN) {
			if(yPos +  this.fireRangeLen > 700) range = 700; //if the range exceeds the boundary, set the range equal to boundary
			else range = yPos + this.fireRangeLen;

			for(int i = yPos; i < range; i++) { //for each y position, according to the length of range
				for(int j=0; j<GamePanel.zombieList.size(); j++){ //for each zombie in the list
					if(GamePanel.zombieList.get(j).getYPos()==i && GamePanel.zombieList.get(j).getXPos()==xPos){ //if there's a zombie sa range
						GamePanel.zombieList.get(j).setHealth(this.damage); //damage its health
						/* Gets the player's current weapon */
						try{
                            if(player.getWeapon() == weapons.getWeapon(WeaponArray.PISTOL)){
                                Thread.sleep(300);
                            }
                            else if(player.getWeapon() == weapons.getWeapon(WeaponArray.UZI)){
                                Thread.sleep(100);
                            }
							else if(player.getWeapon() == weapons.getWeapon(WeaponArray.SHOTGUN)){
                                Thread.sleep(400);
                            }
						}catch(Exception e){}
						break;
					}
				}
			}
		}
	}
}