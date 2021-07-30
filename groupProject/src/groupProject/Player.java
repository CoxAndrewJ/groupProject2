package groupProject;

import javax.swing.ImageIcon;

public class Player
{

	private int health = 3;
	private int location;
	private String name;
	private ImageIcon image;
	public int actions;
	public final int deadActions = 0;

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

	public void setDeadActions()
	{
		actions = deadActions;
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

	public int subtractAction()
	{
		actions--;
		return actions;
	}

	public int resetActions()
	{
		actions = 3;
		return actions;
	}

}
