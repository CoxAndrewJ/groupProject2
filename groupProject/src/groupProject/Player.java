package groupProject;

import javax.swing.ImageIcon;

public class Player {

	private int health;
	private int location;
	private Weapon weapon;
	private ImageIcon image;
	

	public Player() {
		
	}
	
	public void move() {
		
	}
	
	public void fight() {
		
	}
	
	public void loot() {
		
	}
	
	public void openDoor() {
		
	}
	
	
	
	//Getters and setters
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}
}
