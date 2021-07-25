package groupProject;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class Tile extends JPanel {

	private int location;//This is used to set the location of the particular tile
	
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
	
	private ImageIcon player1icon;
	private ImageIcon player2icon;
	private ImageIcon zombieIcon = new ImageIcon(Tile.class.getResource("/resources/zombie1.png"));
	
	JPanel westPanel;
	JPanel eastPanel;
	JPanel southPanel;
	JPanel northPanel;
	JPanel centralPanel;
	
	public Tile() {
		setLayout(new BorderLayout(0, 0));
		setSize(50, 50);
		setBackground(Color.WHITE);
		
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

	private JPanel createCentralPanel() {
		JPanel centralPanel = new JPanel();
		centralPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel player1Label = new JLabel();
		player1Label.setIcon(player1icon);
		centralPanel.add(player1Label);
		
		JLabel player2Label = new JLabel();
		player2Label.setIcon(player2icon);
		centralPanel.add(player2Label);
		
		JLabel zombie1Label = new JLabel();
		zombie1Label.setIcon(zombieIcon);
		centralPanel.add(zombie1Label);
		
		JLabel zombie2Label = new JLabel();
		zombie2Label.setIcon(zombieIcon);
		centralPanel.add(zombie2Label);
		
		return centralPanel;
	}

	
	
	
	/////Used to set images of the 2 players/////
	public void setPlayer1(ImageIcon player1icon) {
		this.player1icon = player1icon;
	}
	
	public void setPlayer2(ImageIcon player2icon) {
		this.player2icon = player2icon;
	}
	/////***********************************/////
	
	
	public int getTileLocation() {
		return location;
	}
	
	public void setTileLocation(int location) {
		this.location = location;
	}
	
	public boolean hasPlayer1() {
		return hasPlayer1;
	}

	public void setHasPlayer1(boolean hasPlayer1) {
		this.hasPlayer1 = hasPlayer1;
	}

	public boolean hasPlayer2() {
		return hasPlayer2;
	}

	public void setHasPlayer2(boolean hasPlayer2) {
		this.hasPlayer2 = hasPlayer2;
	}

	public boolean hasZombie1() {
		return hasZombie1;
	}

	public void setHasZombie1(boolean hasZombie1) {
		this.hasZombie1 = hasZombie1;
	}

	public boolean hasZombie2() {
		return hasZombie2;
	}

	public void setHasZombie2(boolean hasZombie2) {
		this.hasZombie2 = hasZombie2;
	}

	public boolean hasWestDoor() {
		return hasWestDoor;
	}

	public void setHasWestDoor(boolean hasWestDoor) {
		this.hasWestDoor = hasWestDoor;
	}

	public boolean hasEastDoor() {
		return hasEastDoor;
	}

	public void setHasEastDoor(boolean hasEastDoor) {
		this.hasEastDoor = hasEastDoor;
	}

	public boolean hasSouthDoor() {
		return hasSouthDoor;
	}

	public void setHasSouthDoor(boolean hasSouthDoor) {
		this.hasSouthDoor = hasSouthDoor;
	}

	public boolean hasNorthDoor() {
		return hasNorthDoor;
	}

	public void setHasNorthDoor(boolean hasNorthDoor) {
		this.hasNorthDoor = hasNorthDoor;
	}

	public boolean hasWestWall() {
		return hasWestWall;
	}

	public void setHasWestWall(boolean hasWestWall) {
		this.hasWestWall = hasWestWall;
	}

	public boolean hasEastWall() {
		return hasEastWall;
	}

	public void setHasEastWall(boolean hasEastWall) {
		this.hasEastWall = hasEastWall;
	}

	public boolean hasSouthWall() {
		return hasSouthWall;
	}

	public void setHasSouthWall(boolean hasSouthWall) {
		this.hasSouthWall = hasSouthWall;
	}

	public boolean hasNorthWall() {
		return hasNorthWall;
	}

	public void setHasNorthWall(boolean hasNorthWall) {
		this.hasNorthWall = hasNorthWall;
	}
	
	public boolean hasObjective() {
		return hasObjective;
	}
	
	public void setHasObjective(boolean hasObjective) {
		this.hasObjective = hasObjective;
	}
}
