package groupProject;

import javax.swing.ImageIcon;

public class Player
{

	private int health = 5;
	private int location;
	private Weapon weapon = Weapon.UNARMED;
	private String name;
	private ImageIcon image;
	public int actions;

	public Player(String name, ImageIcon image)
	{
		this.name = name;
		this.image = image;
	}

	public void move()
	{

	}

	public void attack()
	{

	}

	public void loot()
	{

	}

	public void openDoor()
	{

	}

	// Getters and setters
	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getLocation()
	{
		return location;
	}

	public void setLocation(int location)
	{
		this.location = location;
	}

	public Weapon getWeapon()
	{
		return weapon;
	}

	public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}

	public String getName()
	{
		return name;
	}

	public ImageIcon getImage()
	{
		return image;
	}

	public void setImage(ImageIcon image)
	{
		this.image = image;
	}

	public int getActions()
	{
		return actions;
	}

	public int resetActions()
	{
		actions = 3;
		return actions;
	}

}
