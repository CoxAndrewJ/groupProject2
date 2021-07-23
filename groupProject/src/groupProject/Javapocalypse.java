package groupProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Javapocalypse implements ActionListener{

	public static Javapocalypse instance = new Javapocalypse();
	public static TitleFrame frame;
	public static GameBoard gameBoard;
	public static boolean[]characters;

	/**
	 * Launch the application.
	 * Contains the main method, and creates the frame
	 */
	public static void main(String[] args) {
		
		frame = new TitleFrame();

	
	}

	//the following2 methods are used to dispose of the frame and instantiate the gameboard
	public static Javapocalypse getInstance() {
		return instance;
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		gameBoard = new GameBoard();
		characters = frame.getCharacters();//this tells us which characters are which
	}
	
	
}