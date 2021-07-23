package groupProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class Javapocalypse implements ActionListener{

	private static Javapocalypse instance = new Javapocalypse();
	private static TitleFrame frame;
	private static GameBoard gameBoard;
	private static boolean[]characters;

	
	//the following2 methods are used to dispose of the frame and instantiate the gameboard
	public static Javapocalypse getInstance() {
		return instance;
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		gameBoardMain();
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
		Player player1 = createPlayer();
		Player player2 = createPlayer();
		
		//This is temp code to show a player made
		System.out.println(player1.getHealth());
		System.out.println(player1.getName());
		System.out.println(player1.getLocation());
		System.out.println(player1.getWeapon());
		System.out.println(player1.getImage().toString());
		
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
			player = new Player("Pirate Kid", new ImageIcon(Javapocalypse.class.getResource("/resources/portlypirate-kid.png")));
			return player;
		}
	}
	
}