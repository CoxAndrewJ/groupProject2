package groupProject;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class Tile extends JPanel {

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
	
	private ImageIcon player1;
	private ImageIcon player2;
	private ImageIcon zombie;
	
	JPanel leftPanel;
	JPanel rightPanel;
	JPanel bottomPanel;
	JPanel topPanel;
	
	public Tile() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel leftPanel = new JPanel();		
		JPanel rightPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel topPanel = new JPanel();
		
		JPanel centralPanel = new JPanel();
		
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);
		add(topPanel, BorderLayout.NORTH);
		add(centralPanel, BorderLayout.CENTER);
		
		JLabel player1Panel = new JLabel();
		
		centralPanel.add(player1Panel);
		
		JPanel player2Panel = new JPanel();
		centralPanel.add(player2Panel);
		
		JPanel zombie1Panel = new JPanel();
		centralPanel.add(zombie1Panel);
		
		JPanel zombie2Panel = new JPanel();
		centralPanel.add(zombie2Panel);
		
	}

	
	
	
	
	public void setPlayer1(ImageIcon player1) {
		this.player1 = player1;
	}
	
	public void setPlayer2(ImageIcon player2) {
		this.player2 = player2;
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
