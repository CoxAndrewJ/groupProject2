package groupProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Javapocalypse implements ActionListener
{

	// The first 4 fields are used to create both the gameBoard as well as the
	// titleFrame
	private static Javapocalypse instance = new Javapocalypse();
	private static TitleFrame frame;
	public static GameBoard gameBoard;
	private static boolean[] characters;

	public static int objectivesObtained;
	public static int objectivesRemaining;
	public static int turn = 0;
	public static Player player1;
	public static Player player2;

	static Random randZombieSpawn = new Random();
	static Random randDirection = new Random();

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

	////////////////////////////////////////////////////////////////////
	// Code below is all for the GameBoard interaction
	////////////////////////////////////////////////////////////////////

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
		objectivesRemaining = 3;

		// Start the players off on space 1 and 10
		player1.setLocation(1);
		player2.setLocation(10);
		player1.resetActions();
		player2.resetActions();

		// Create a new gameBoard using the above code as parameters(in a way)
		gameBoard = new GameBoard();

		updateBoardLocations();

	}

	/**
	 * Updates the action count, player image, action label, and whatever else we
	 * need to update
	 */
	public static void updateStuffBtnPress(Player player)
	{
		GameBoard.currentPlayerImage.setIcon(player.getImage());
		player.subtractAction();
		GameBoard.actionsLbl.setText("Actions: " + player.getActions());
		Javapocalypse.updateBoardLocations();
		GameBoard.healthLbl.setText("Health: " + player.getHealth());

		if (player.getLocation() == 5 || player.getLocation() == 21 || player.getLocation() == 24)
		{
			objectivesObtained++;
			objectivesRemaining--;
			GameBoard.objectiveLbl.setText("Objectives: " + objectivesRemaining);
			if (player.getLocation() == 24)
			{
				gameBoard.tile24.centralPanel.setBackground(Color.DARK_GRAY);
			}
			if (player.getLocation() == 21)
			{
				gameBoard.tile21.centralPanel.setBackground(Color.DARK_GRAY);
			}
			if (player.getLocation() == 5)
			{
				gameBoard.tile5.centralPanel.setBackground(Color.DARK_GRAY);
			}

			if (objectivesRemaining < 1)
			{
				JOptionPane.showMessageDialog(frame, "ALL OBJECTIVES COLLECTED\n     YOU WIN!");
				System.exit(0);
			}
		}
	}

	/**
	 * @return
	 * 
	 */
	private static void turnCycle()
	{
		// Cycles between player1's turn, player2's turn, and zombie phase as well as if
		// the game has been won or lost.
		player1.resetActions();
		player2.resetActions();

		if (player1.getHealth() < 1)
		{
			for (Tile tile : gameBoard.tiles)
			{
				tile.setHasPlayer1(false);
			}
			player1.setDeadActions();

		}
		if (player2.getHealth() < 1)
		{
			for (Tile tile : gameBoard.tiles)
			{
				tile.setHasPlayer2(false);
			}
			player2.setDeadActions();
			player1.resetActions();

		}
		if (player1.getHealth() < 1 && player2.getHealth() < 1)
		{
			JOptionPane.showMessageDialog(frame, "YOU LOSE\n GAME OVER");
			System.exit(0);
		}

		return;
	}

	/**
	 * Spawns the indicated number of zombies
	 * 
	 * @param tile the tile to spawn the zombies in
	 */
	public static void zombieSpawn(Tile tile1, Tile tile2, Tile tile3, Tile tile4)
	{
		zombieAttack();
		zombieMove();

		List<Tile> spawns = new ArrayList<>();
		Collections.addAll(spawns, tile1, tile2, tile3, tile4);

		for (Tile tile : spawns)
		{
			randZombieSpawn = new Random();
			int zNum = randZombieSpawn.nextInt(7);
			if (zNum > 4)
			{
				tile.setHasZombie1(true);
				tile.setHasZombie2(true);
				Javapocalypse.updateBoardLocations();

			} else
			{
				tile.setHasZombie1(true);
				Javapocalypse.updateBoardLocations();
			}
			if (player1.getHealth() > 0)
			{
				GameBoard.currentPlayerImage.setIcon(player1.getImage());
				GameBoard.actionsLbl.setText("Actions: " + player1.getActions());
				Javapocalypse.updateBoardLocations();
				GameBoard.healthLbl.setText("Health: " + player1.getHealth());
			} else
			{
				GameBoard.currentPlayerImage.setIcon(player2.getImage());
				GameBoard.actionsLbl.setText("Actions: " + player2.getActions());
				Javapocalypse.updateBoardLocations();
				GameBoard.healthLbl.setText("Health: " + player2.getHealth());
			}
		}
		turnCycle();

		return;
	}

	public static void zombieAttack()
	{
		for (Tile tile : gameBoard.tiles)
		{

			if (tile.hasPlayer1())
			{
				if (tile.hasZombie1())
				{
					player1.setHealth(player1.getHealth() - 1);
					updateStuffBtnPress(player1);

					if (tile.hasZombie2())
					{
						player1.setHealth(player1.getHealth() - 1);
						updateStuffBtnPress(player1);
					}

				}
			}

			if (tile.hasPlayer2())
			{
				if (tile.hasZombie1())
				{
					player2.setHealth(player2.getHealth() - 1);
					updateStuffBtnPress(player2);

					if (tile.hasZombie2())
					{
						player2.setHealth(player2.getHealth() - 1);
						updateStuffBtnPress(player2);
					}

				}
			}
		}

	}

	public static void zombieMove()
	{
		int randomMove = randDirection.nextInt(4);

		switch (randomMove)
		{
		case 0: // Move Zombie North
			for (Tile tile : gameBoard.tiles) {
				if (tile.hasZombie1() && !tile.hasNorthWall())
				{
					if (!gameBoard.tiles.get(tile.getTileLocation() -5).hasZombie1())
					{
						tile.setHasZombie1(false);
						gameBoard.tiles.get(tile.getTileLocation() - 5).setHasZombie1(true);
						
					}
				}

				if (tile.hasZombie2() && !tile.hasNorthWall())
				{
					if (!gameBoard.tiles.get(tile.getTileLocation() - 5).hasZombie2())
					{
						tile.setHasZombie2(false);
						gameBoard.tiles.get(tile.getTileLocation() - 5).setHasZombie2(true);
					}
				}
			}
			break;
		case 1:
			for (Tile tile : gameBoard.tiles)
			{
				if (tile.hasZombie1() && !tile.hasSouthWall())
				{
					if (!gameBoard.tiles.get(tile.getTileLocation() + 5).hasZombie1())
					{
						tile.setHasZombie1(false);
						gameBoard.tiles.get(tile.getTileLocation() + 5).setHasZombie1(true);
					}
				}
				if (tile.hasZombie2() && !tile.hasSouthWall())
				{
					if (!gameBoard.tiles.get(tile.getTileLocation() + 5).hasZombie2())
					{
						tile.setHasZombie2(false);
						gameBoard.tiles.get(tile.getTileLocation() + 5).setHasZombie2(true);
					}
				}
			}
			break;
		case 2:
			for (Tile tile : gameBoard.tiles)
			{
				if (tile.hasZombie1() && !tile.hasEastWall())
				{
					if (!gameBoard.tiles.get(tile.getTileLocation() - 1).hasZombie1())
					{
						tile.setHasZombie1(false);
						gameBoard.tiles.get(tile.getTileLocation() - 1).setHasZombie1(true);
					}
				}
				if (tile.hasZombie2() && !tile.hasEastWall())
				{
					if (!gameBoard.tiles.get(tile.getTileLocation() - 1).hasZombie2())
					{
						tile.setHasZombie2(false);
						gameBoard.tiles.get(tile.getTileLocation() - 1).setHasZombie2(true);
					}
				}
			}
			break;
		case 3:
			for (Tile tile : gameBoard.tiles)
			{
				if (tile.hasZombie1() && !tile.hasWestWall())
				{
					if (!gameBoard.tiles.get(tile.getTileLocation() + 1).hasZombie1())
					{
						tile.setHasZombie1(false);
						gameBoard.tiles.get(tile.getTileLocation() + 1).setHasZombie1(true);
						
					}
				}
				if (tile.hasZombie2() && !tile.hasWestWall())
				{
					if (!gameBoard.tiles.get(tile.getTileLocation() + 1).hasZombie2())
					{
						tile.setHasZombie2(false);
						gameBoard.tiles.get(tile.getTileLocation() + 1).setHasZombie2(true);
					}
				}
			}
		}

	}

	/**
	 * Updates the locations of the players on the board.
	 */
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

			// Update the Zombie positions
			if (tile.hasZombie1())
			{

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

/**
 * Moves the player up one tile on the game board if allowed.
 * 
 * @author Andrew and Cody
 *
 */
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
		// Move
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[0] == true)
		{

			if (Javapocalypse.player1.getActions() > 0
					&& !Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasNorthWall())
			{
				Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() - 5);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
				if (Javapocalypse.player2.getHealth() < 0)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);
				}

			} else if (Javapocalypse.player2.getActions() > 0
					&& !Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation()).hasNorthWall())
			{
				Javapocalypse.player2.setLocation(Javapocalypse.player2.getLocation() - 5);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			}
		}

		// Open
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[1] == true)
		{
			if (Javapocalypse.player1.getActions() > 0
					&& Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasNorthDoor())
			{
				Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() - 5);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
			} else if (Javapocalypse.player2.getActions() > 0
					&& Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation()).hasNorthDoor())
			{
				Javapocalypse.player2.setLocation(Javapocalypse.player2.getLocation() - 5);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			}

		}

		// Attack
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[2] == true)
		{
			Tile target1 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation() - 5);
			Tile target2 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation() - 5);
			Tile sameTile1 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation());
			Tile sameTile2 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation());

			if (Javapocalypse.player1.getActions() > 0)
			{
				if (sameTile1.hasZombie1() || sameTile1.hasZombie2())
				{
					target1 = sameTile1;
				}
				if (target1.hasZombie2())
				{
					target1.setHasZombie2(false);
				} else
				{
					target1.setHasZombie1(false);
				}

				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
			} else if (Javapocalypse.player2.getActions() > 0)
			{
				if (sameTile2.hasZombie1() || sameTile2.hasZombie2())
				{
					target2 = sameTile2;
				}
				if (target2.hasZombie2())
				{
					target2.setHasZombie2(false);
				} else
				{
					target2.setHasZombie1(false);
				}
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			} else
			{
				Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
						Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

			}

		}
	}
}

/**
 * Moves the player down one tile on the game board if allowed.
 * 
 * @author Andrew and Cody
 *
 */
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
		// Move
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[0] == true)
		{
			if (Javapocalypse.player1.getActions() > 0
					&& !Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasSouthWall())
			{
				Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() + 5);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}

				if (Javapocalypse.player2.getHealth() < 0)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}

			} else if (Javapocalypse.player2.getActions() > 0
					&& !Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation()).hasSouthWall())
			{
				Javapocalypse.player2.setLocation(Javapocalypse.player2.getLocation() + 5);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			}
		}

		// Open
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[1] == true)
		{
			if (Javapocalypse.player1.getActions() > 0
					&& Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasSouthDoor())
			{
				Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() + 5);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
			} else if (Javapocalypse.player2.getActions() > 0
					&& Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation()).hasSouthDoor())
			{
				Javapocalypse.player2.setLocation(Javapocalypse.player2.getLocation() + 5);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			} else
			{
				Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
						Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

			}
		}

		// Attack
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[2] == true)
		{
			Tile target = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation() + 5);
			Tile target2 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation() + 5);
			Tile sameTile1 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation());
			Tile sameTile2 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation());

			if (Javapocalypse.player1.getActions() > 0)
			{
				if (sameTile1.hasZombie1() || sameTile1.hasZombie2())
				{
					target = sameTile1;
				}
				if (target.hasZombie2())
				{
					target.setHasZombie2(false);
				} else
				{
					target.setHasZombie1(false);
				}

				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
			} else if (Javapocalypse.player2.getActions() > 0)
			{
				if (sameTile2.hasZombie1() || sameTile2.hasZombie2())
				{
					target2 = sameTile2;
				}
				if (target2.hasZombie2())
				{
					target2.setHasZombie2(false);
				} else
				{
					target2.setHasZombie1(false);
				}
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			} else
			{
				Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
						Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

			}

		}
	}
}

/**
 * Moves the player left one tile on the game board if allowed.
 * 
 * @author Andrew and Cody
 *
 */
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
		// Move
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[0] == true)
		{
			if (Javapocalypse.player1.getActions() > 0
					&& !Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasWestWall())
			{
				Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() - 1);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
				if (Javapocalypse.player2.getHealth() < 0)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}

			} else if (Javapocalypse.player2.getActions() > 0
					&& !Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation()).hasWestWall())
			{
				Javapocalypse.player2.setLocation(Javapocalypse.player2.getLocation() - 1);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			}
		}

		// Open
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[1] == true)
		{
			if (Javapocalypse.player1.getActions() > 0
					&& Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasWestDoor())
			{
				Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() - 1);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
			} else if (Javapocalypse.player2.getActions() > 0
					&& Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation()).hasWestDoor())
			{
				Javapocalypse.player2.setLocation(Javapocalypse.player2.getLocation() - 1);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			} else
			{
				Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
						Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

			}

		}

		// Attack
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[2] == true)
		{
			Tile target = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation() - 1);
			Tile target2 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation() - 1);
			Tile sameTile1 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation());
			Tile sameTile2 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation());

			if (Javapocalypse.player1.getActions() > 0)
			{
				if (sameTile1.hasZombie1() || sameTile1.hasZombie2())
				{
					target = sameTile1;
				}
				if (target.hasZombie2())
				{
					target.setHasZombie2(false);
				} else
				{
					target.setHasZombie1(false);
				}

				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
			} else if (Javapocalypse.player2.getActions() > 0)
			{
				if (sameTile2.hasZombie1() || sameTile2.hasZombie2())
				{
					target2 = sameTile2;
				}
				if (target2.hasZombie2())
				{
					target2.setHasZombie2(false);
				} else
				{
					target2.setHasZombie1(false);
				}
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			} else
			{
				Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
						Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

			}

		}
	}
}

/**
 * Moves the player right one tile on the game board if allowed.
 * 
 * @author Andrew and Cody
 *
 */
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
		// Move
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[0] == true)
		{

			if (Javapocalypse.player1.getActions() > 0
					&& !Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasEastWall())
			{
				Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() + 1);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
				if (Javapocalypse.player2.getHealth() < 0)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}

			} else if (Javapocalypse.player2.getActions() > 0
					&& !Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation()).hasEastWall())
			{
				Javapocalypse.player2.setLocation(Javapocalypse.player2.getLocation() + 1);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			}
		}

		// Open
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[1] == true)
		{
			if (Javapocalypse.player1.getActions() > 0
					&& Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation()).hasEastDoor())
			{
				Javapocalypse.player1.setLocation(Javapocalypse.player1.getLocation() + 1);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
			} else if (Javapocalypse.player2.getActions() > 0
					&& Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation()).hasEastDoor())
			{
				Javapocalypse.player2.setLocation(Javapocalypse.player2.getLocation() + 1);
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			} else
			{
				Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
						Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

			}

		}

		// Attack
		if (Javapocalypse.gameBoard.getNumbersPressedArr()[2] == true)
		{
			Tile target = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation() + 1);
			Tile target2 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation() + 1);
			Tile sameTile1 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player1.getLocation());
			Tile sameTile2 = Javapocalypse.gameBoard.tiles.get(Javapocalypse.player2.getLocation());

			if (Javapocalypse.player1.getActions() > 0)
			{
				if (sameTile1.hasZombie1() || sameTile1.hasZombie2())
				{
					target = sameTile1;
				}
				if (target.hasZombie2())
				{
					target.setHasZombie2(false);
				} else
				{
					target.setHasZombie1(false);
				}

				Javapocalypse.updateStuffBtnPress(Javapocalypse.player1);

				if (Javapocalypse.player1.getActions() < 1)
				{
					GameBoard.currentPlayerImage.setIcon(Javapocalypse.player2.getImage());
					GameBoard.actionsLbl.setText("Actions: " + Javapocalypse.player2.getActions());
					GameBoard.healthLbl.setText("Health: " + Javapocalypse.player2.getHealth());
				}
			} else if (Javapocalypse.player2.getActions() > 0)
			{
				if (sameTile2.hasZombie1() || sameTile2.hasZombie2())
				{
					target2 = sameTile2;
				}
				if (target2.hasZombie2())
				{
					target2.setHasZombie2(false);
				} else
				{
					target2.setHasZombie1(false);
				}
				Javapocalypse.updateStuffBtnPress(Javapocalypse.player2);

				if (Javapocalypse.player2.getActions() < 1)
				{
					Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
							Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

				}
			} else
			{
				Javapocalypse.zombieSpawn(Javapocalypse.gameBoard.tile2, Javapocalypse.gameBoard.tile11,
						Javapocalypse.gameBoard.tile22, Javapocalypse.gameBoard.tile25);

			}

		}
	}
}
