package groupProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class Javapocalypse implements ActionListener
{

	// The first 4 fields are used to create both the gameBoard as well as the
	// titleFrame
	private static Javapocalypse instance = new Javapocalypse();
	private static TitleFrame frame;
	public static GameBoard gameBoard;
	private static boolean[] characters;
	public static int objectivesObtained;
	public static Player player1;
	public static Player player2;

	Dice die = new Dice();

	// the following 3 methods are used to dispose of the frame and instantiate the
	// gameboard
	public static Javapocalypse getInstance()
	{
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		initializeGameBoard();
	}

	public static Player createPlayer()
	{
		Player player;
		if (characters[1] == true)
		{
			characters[1] = false;
			player = new Player("Old Woman",
					new ImageIcon(Javapocalypse.class.getResource("/resources/old-woman-pistol.png")));
			return player;
		}
		if (characters[2] == true)
		{
			characters[2] = false;
			player = new Player("Angry Guy",
					new ImageIcon(Javapocalypse.class.getResource("/resources/angry-guy.png")));
			return player;
		}
		if (characters[3] == true)
		{
			characters[3] = false;
			player = new Player("Portly Mobster",
					new ImageIcon(Javapocalypse.class.getResource("/resources/portly-mobster-wearing-suit.png")));
			return player;
		} else
		{
			player = new Player("Pirate Kid",
					new ImageIcon(Javapocalypse.class.getResource("/resources/pirate-kid.png")));
			return player;
		}
	}

	/**
	 * Launch the application. Contains the main method, and creates the frame
	 */
	public static void main(String[] args)
	{
		frame = new TitleFrame();
	}

	/**
	 * All code in the initializeGameBoard method will run only once we have hit the
	 * ready button. Otherwise, code would run instantaneously
	 */
	public static void initializeGameBoard()
	{
		// Next 5 lines will create the character images which are added to gameBoard
		characters = frame.getCharacters();// this tells us which characters are which
		player1 = createPlayer();
		player2 = createPlayer();
		Tile.setPlayer1Icon(player1.getImage());
		Tile.setPlayer2Icon(player2.getImage());

		// Start the players off on space 25
		player1.setLocation(25);
		player2.setLocation(25);

		// Create a new gameBoard using the above code as parameters(in a way)
		gameBoard = new GameBoard();

		player1.setLocation(25);
		player2.setLocation(25);

		startGame();
	}

	public static void startGame()
	{
		objectivesObtained = 0;
		updateBoardLocations();
	}

	public static void endGame()
	{
		if (objectivesObtained == 3)
		{
			// Victory
		}
		if (player1.getHealth() == 0 && player2.getHealth() == 0)
		{
			// GameOver
		}

	}

	public static void updateBoardLocations()
	{

		for (Tile tile : gameBoard.tiles)
		{
			if (tile.getTileLocation() == player1.getLocation())
			{
				tile.setHasPlayer1(true);
			}
			if (tile.getTileLocation() == player2.getLocation())
			{
				tile.setHasPlayer2(true);
			}
			if (tile.getTileLocation() != player1.getLocation())
			{
				tile.setHasPlayer1(false);
			}
			if (tile.getTileLocation() != player2.getLocation())
			{
				tile.setHasPlayer2(false);
			}
		}
	}

	public static Player getPlayer1()
	{
		return player1;
	}

	public static Player getPlayer2()
	{
		return player2;
	}

}

class UpBtnListener implements ActionListener
{

	public static UpBtnListener upBtnListener = new UpBtnListener();

	public static UpBtnListener getListener()
	{
		return upBtnListener;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (!Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasNorthWall())
		{

			Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() - 5);
			for (Tile tile : Javapocalypse.gameBoard.tiles)
			{
				if (tile.getTileLocation() == Javapocalypse.player1.getLocation())
				{
					tile.setHasPlayer1(true);
				}
				if (tile.getTileLocation() == Javapocalypse.player2.getLocation())
				{
					tile.setHasPlayer2(true);
				}
				if (tile.getTileLocation() != Javapocalypse.player1.getLocation())
				{
					tile.setHasPlayer1(false);
				}
				if (tile.getTileLocation() != Javapocalypse.player2.getLocation())
				{
					tile.setHasPlayer2(false);
				}
			}
		}
	}
}

class DownBtnListener implements ActionListener
{

	public static DownBtnListener downBtnListener = new DownBtnListener();

	public static DownBtnListener getListener()
	{
		return downBtnListener;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (!Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasSouthWall())
		{

			Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() + 5);
			for (Tile tile : Javapocalypse.gameBoard.tiles)
			{
				if (tile.getTileLocation() == Javapocalypse.player1.getLocation())
				{
					tile.setHasPlayer1(true);
				}
				if (tile.getTileLocation() == Javapocalypse.player2.getLocation())
				{
					tile.setHasPlayer2(true);
				}
				if (tile.getTileLocation() != Javapocalypse.player1.getLocation())
				{
					tile.setHasPlayer1(false);
				}
				if (tile.getTileLocation() != Javapocalypse.player2.getLocation())
				{
					tile.setHasPlayer2(false);
				}
			}
		}
	}
}

class LeftBtnListener implements ActionListener
{

	public static LeftBtnListener leftBtnListener = new LeftBtnListener();

	public static LeftBtnListener getListener()
	{
		return leftBtnListener;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (!Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasWestWall())
		{

			Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() - 1);
			for (Tile tile : Javapocalypse.gameBoard.tiles)
			{
				if (tile.getTileLocation() == Javapocalypse.player1.getLocation())
				{
					tile.setHasPlayer1(true);
				}
				if (tile.getTileLocation() == Javapocalypse.player2.getLocation())
				{
					tile.setHasPlayer2(true);
				}
				if (tile.getTileLocation() != Javapocalypse.player1.getLocation())
				{
					tile.setHasPlayer1(false);
				}
				if (tile.getTileLocation() != Javapocalypse.player2.getLocation())
				{
					tile.setHasPlayer2(false);
				}
			}
		}
	}
}

class RightBtnListener implements ActionListener
{

	public static RightBtnListener rightBtnListener = new RightBtnListener();

	public static RightBtnListener getListener()
	{
		return rightBtnListener;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (!Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasEastWall())
		{

			Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() + 1);
			for (Tile tile : Javapocalypse.gameBoard.tiles)
			{
				if (tile.getTileLocation() == Javapocalypse.player1.getLocation())
				{
					tile.setHasPlayer1(true);
				}
				if (tile.getTileLocation() == Javapocalypse.player2.getLocation())
				{
					tile.setHasPlayer2(true);
				}
				if (tile.getTileLocation() != Javapocalypse.player1.getLocation())
				{
					tile.setHasPlayer1(false);
				}
				if (tile.getTileLocation() != Javapocalypse.player2.getLocation())
				{
					tile.setHasPlayer2(false);
				}
			}
		}
	}
}
