package groupProject;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tile extends JPanel
{

	private int location;// This is used to set the location of the particular tile

	private boolean hasPlayer1 = false;
	private boolean hasPlayer2 = false;
	private boolean hasZombie1 = false;
	private boolean hasZombie2 = false;

	private boolean hasWestDoor = false;
	private boolean hasEastDoor = false;
	private boolean hasSouthDoor = false;
	private boolean hasNorthDoor = false;

	private boolean hasWestWall = false;
	private boolean hasEastWall = false;
	private boolean hasSouthWall = false;
	private boolean hasNorthWall = false;

	private boolean hasObjective = false;

	private static ImageIcon player1icon;
	private static ImageIcon player2icon;
	private ImageIcon zombieIcon = new ImageIcon(Tile.class.getResource("/resources/zombie1.png"));

	JPanel westPanel;
	JPanel eastPanel;
	JPanel southPanel;
	JPanel northPanel;
	JPanel centralPanel;

	JLabel player1Label;
	JLabel player2Label;
	JLabel zombie1Label;
	JLabel zombie2Label;

	public Tile()
	{
		setLayout(new BorderLayout(0, 0));
		setBounds(0, 0, 50, 50);

		westPanel = new JPanel();
		eastPanel = new JPanel();
		southPanel = new JPanel();
		northPanel = new JPanel();

		centralPanel = createCentralPanel();

		add(westPanel, BorderLayout.WEST);
		add(eastPanel, BorderLayout.EAST);
		add(southPanel, BorderLayout.SOUTH);
		add(northPanel, BorderLayout.NORTH);
		add(centralPanel, BorderLayout.CENTER);

	}

	private JPanel createCentralPanel()
	{
		JPanel centralPanel = new JPanel();
		centralPanel.setLayout(new GridLayout(2, 2, 0, 0));

		player1Label = new JLabel();
		player1Label.setIcon(player1icon);
		player1Label.setVisible(false);
		centralPanel.add(player1Label);

		player2Label = new JLabel();
		player2Label.setIcon(player2icon);
		player2Label.setVisible(false);
		centralPanel.add(player2Label);

		zombie1Label = new JLabel();
		zombie1Label.setIcon(zombieIcon);
		zombie1Label.setVisible(false);
		centralPanel.add(zombie1Label);

		zombie2Label = new JLabel();
		zombie2Label.setIcon(zombieIcon);
		zombie2Label.setVisible(false);
		centralPanel.add(zombie2Label);

		return centralPanel;
	}

	///// Used to set images of the 2 players/////
	public static void setPlayer1Icon(ImageIcon icon)
	{
		player1icon = icon;
	}

	public static void setPlayer2Icon(ImageIcon icon)
	{
		player2icon = icon;
	}
	///// ***********************************/////

	public int getTileLocation()
	{
		return location;
	}

	public void setTileLocation(int location)
	{
		this.location = location;
	}

	public boolean hasPlayer1()
	{
		return hasPlayer1;
	}

	public void setHasPlayer1(boolean hasPlayer1)
	{
		this.hasPlayer1 = hasPlayer1;
		if (hasPlayer1 == true)
		{
			player1Label.setVisible(true);
		}
		if (hasPlayer1 == false)
		{
			player1Label.setVisible(false);
		}
	}

	public boolean hasPlayer2()
	{
		return hasPlayer2;
	}

	public void setHasPlayer2(boolean hasPlayer2)
	{
		if (hasPlayer2 == true)
		{
			player2Label.setVisible(true);
		}
		if (hasPlayer2 == false)
		{
			player2Label.setVisible(false);
		}
		this.hasPlayer2 = hasPlayer2;
	}

	public boolean hasZombie1()
	{
		return hasZombie1;
	}

	public void setHasZombie1(boolean hasZombie1)
	{
		if (hasZombie1 == true) {
			zombie1Label.setVisible(true);
		}
		if (hasZombie1 == false) {
			zombie1Label.setVisible(false);
		}
		this.hasZombie1 = hasZombie1;
	}

	public boolean hasZombie2()
	{
		return hasZombie2;
	}

	public void setHasZombie2(boolean hasZombie2)
	{
		if (hasZombie2 == true) {
			zombie2Label.setVisible(true);
		}
		if (hasZombie2 == false) {
			zombie2Label.setVisible(false);
		}
		this.hasZombie2 = hasZombie2;
	}

	public boolean hasWestDoor()
	{
		return hasWestDoor;
	}

	public void setHasWestDoor(boolean hasWestDoor)
	{
		this.hasWestDoor = hasWestDoor;
	}

	public boolean hasEastDoor()
	{
		return hasEastDoor;
	}

	public void setHasEastDoor(boolean hasEastDoor)
	{
		this.hasEastDoor = hasEastDoor;
	}

	public boolean hasSouthDoor()
	{
		return hasSouthDoor;
	}

	public void setHasSouthDoor(boolean hasSouthDoor)
	{
		this.hasSouthDoor = hasSouthDoor;
	}

	public boolean hasNorthDoor()
	{
		return hasNorthDoor;
	}

	public void setHasNorthDoor(boolean hasNorthDoor)
	{
		this.hasNorthDoor = hasNorthDoor;
	}

	public boolean hasWestWall()
	{
		return hasWestWall;
	}

	public void setHasWestWall(boolean hasWestWall)
	{
		this.hasWestWall = hasWestWall;
	}

	public boolean hasEastWall()
	{
		return hasEastWall;
	}

	public void setHasEastWall(boolean hasEastWall)
	{
		this.hasEastWall = hasEastWall;
	}

	public boolean hasSouthWall()
	{
		return hasSouthWall;
	}

	public void setHasSouthWall(boolean hasSouthWall)
	{
		this.hasSouthWall = hasSouthWall;
	}

	public boolean hasNorthWall()
	{
		return hasNorthWall;
	}

	public void setHasNorthWall(boolean hasNorthWall)
	{
		this.hasNorthWall = hasNorthWall;
	}

	public boolean hasObjective()
	{
		return hasObjective;
	}

	public void setHasObjective(boolean hasObjective)
	{
		this.hasObjective = hasObjective;
	}
	@Override
	public String toString() {
		return "tile location " + location;
	}
}
