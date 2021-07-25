package groupProject;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridLayout;

public class Tile extends JLayeredPane {

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
	
	private ImageIcon background;
	
	public Tile() {
		setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel topLeftPanel = new JPanel();		
		JPanel topRightPanel = new JPanel();
		JPanel bottomLeftPanel = new JPanel();
		JPanel bottomRightPanel = new JPanel();
		
		add(topLeftPanel);
		add(topRightPanel);
		add(bottomLeftPanel);
		add(bottomRightPanel);

	}

	public boolean isHasPlayer1() {
		return hasPlayer1;
	}

	public void setHasPlayer1(boolean hasPlayer1) {
		this.hasPlayer1 = hasPlayer1;
	}

	public boolean isHasPlayer2() {
		return hasPlayer2;
	}

	public void setHasPlayer2(boolean hasPlayer2) {
		this.hasPlayer2 = hasPlayer2;
	}

	public boolean isHasZombie1() {
		return hasZombie1;
	}

	public void setHasZombie1(boolean hasZombie1) {
		this.hasZombie1 = hasZombie1;
	}

	public boolean isHasZombie2() {
		return hasZombie2;
	}

	public void setHasZombie2(boolean hasZombie2) {
		this.hasZombie2 = hasZombie2;
	}

	public boolean isHasWestDoor() {
		return hasWestDoor;
	}

	public void setHasWestDoor(boolean hasWestDoor) {
		this.hasWestDoor = hasWestDoor;
	}

	public boolean isHasEastDoor() {
		return hasEastDoor;
	}

	public void setHasEastDoor(boolean hasEastDoor) {
		this.hasEastDoor = hasEastDoor;
	}

	public boolean isHasSouthDoor() {
		return hasSouthDoor;
	}

	public void setHasSouthDoor(boolean hasSouthDoor) {
		this.hasSouthDoor = hasSouthDoor;
	}

	public boolean isHasNorthDoor() {
		return hasNorthDoor;
	}

	public void setHasNorthDoor(boolean hasNorthDoor) {
		this.hasNorthDoor = hasNorthDoor;
	}

	public boolean isHasWestWall() {
		return hasWestWall;
	}

	public void setHasWestWall(boolean hasWestWall) {
		this.hasWestWall = hasWestWall;
	}

	public boolean isHasEastWall() {
		return hasEastWall;
	}

	public void setHasEastWall(boolean hasEastWall) {
		this.hasEastWall = hasEastWall;
	}

	public boolean isHasSouthWall() {
		return hasSouthWall;
	}

	public void setHasSouthWall(boolean hasSouthWall) {
		this.hasSouthWall = hasSouthWall;
	}

	public boolean isHasNorthWall() {
		return hasNorthWall;
	}

	public void setHasNorthWall(boolean hasNorthWall) {
		this.hasNorthWall = hasNorthWall;
	}
}
