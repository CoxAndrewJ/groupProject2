package groupProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class Javapocalypse implements ActionListener{

	//The first 4 fields are used to create both the gameBoard as well as the titleFrame
	private static Javapocalypse instance = new Javapocalypse();
	private static TitleFrame frame;
	private static GameBoard gameBoard;
	private static boolean[]characters;
	
	private static Player player1;
	private static Player player2;

	
	//the following 3 methods are used to dispose of the frame and instantiate the gameboard
	public static Javapocalypse getInstance() {
		return instance;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==frame) {
			gameBoardMain();
		}
	}
	
	public static Player createPlayer(){
		Player player;
		if(characters[1]==true) {
			characters[1] =false;
			player = new Player("Old Woman", new ImageIcon(Javapocalypse.class.getResource("/resources/old-woman-pistol.png")));
			return player;
		}
		if(characters[2]==true) {
			characters[2] =false;
			player = new Player("Angry Guy", new ImageIcon(Javapocalypse.class.getResource("/resources/angry-guy.png")));
			return player;
		}
		if(characters[3]==true) {
			characters[3] =false;
			player = new Player("Portly Mobster", new ImageIcon(Javapocalypse.class.getResource("/resources/portly-mobster-wearing-suit.png")));
			return player;
		}
		else {
			player = new Player("Pirate Kid", new ImageIcon(Javapocalypse.class.getResource("/resources/pirate-kid.png")));
			return player;
		}
	}
	
	/**
	 * Launch the application.
	 * Contains the main method, and creates the frame
	 */
	public static void main(String[] args) {	
		frame = new TitleFrame();
	}
	
	
	/**
	 * All code in the GameBoardMain method will run only once we have hit the ready button.
	 * Otherwise, code would run instantaneously
	 */
	public static void gameBoardMain() {
		gameBoard = new GameBoard();
		characters = frame.getCharacters();//this tells us which characters are which
		
		player1 = createPlayer();
		player2 = createPlayer();
		
		Dice die = new Dice();
		
		player1.setLocation(25);
		player2.setLocation(25);
		
		
	}
	
	public static Player getPlayer1() {
		return player1;
	}

	public static Player getPlayer2() {
		return player2;
	}
}