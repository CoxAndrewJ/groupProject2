package groupProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GameBoard implements ActionListener
{

	private JPanel contentPane;
	private JMenu file;

	private JPanel gameInteractionPanel;
	private JPanel directionPanel;

	private boolean[] buttonNumberPressed =
	{ false, false, false, false };

	public List<Integer> buildingSquares = new ArrayList<>();

	/**
	 * Create the main frame of the item We will add 2 panels: the gameBoard panel
	 * on west and the sidePanel on east We then add two more panels to the east
	 * panel
	 * 
	 * @author Cody, Hayden
	 */

	public GameBoard()
	{

		// Setting up basic properties of frame
		JFrame mainFrame = new JFrame();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// this creates the menu bar so that we can have a save option
		JMenuBar mb = new JMenuBar();
		file = new JMenu("File");
		mb.add(file);
		JMenuItem save = new JMenuItem("Save");
		file.add(save);
		save.addActionListener(this);
		JTextArea ta = new JTextArea();
		ta.setBounds(5, 5, 360, 320);
		mainFrame.getContentPane().add(ta);
		mainFrame.setJMenuBar(mb);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Create the game Panel
		JPanel gameBoard = buttonsBoard();
		contentPane.add(gameBoard, BorderLayout.CENTER);

		// Create a new panel to be on the East side, and hold two more panels--
		// resultsPanel & gameInteractionPanel
		JPanel sidePanel = createSidePanel();
		contentPane.add(sidePanel, BorderLayout.EAST);

	}

	/**
	 * event is in order to save from the menu bar.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == file)
		{
			playerSaveFile(Javapocalypse.getPlayer1(), Javapocalypse.getPlayer2());
		}
	}

	/**
	 * All the following code is for the side Panel. The side panel is on the east
	 * side and contains two more panels - resultsPanel gameInteractionPanel
	 * 
	 * @author Andrew
	 * @return
	 */
	private JPanel createSidePanel()
	{
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

		// Add the resultsPanel to the sidePanel
		// Will go on the upper side
		JPanel resultsPanel = createResultsPanel();
		sidePanel.add(resultsPanel);

		gameInteractionPanel = createGameInteractionPanel();
		sidePanel.add(gameInteractionPanel);

		directionPanel = createDirectionPanel();
		directionPanel.setVisible(false);
		sidePanel.add(directionPanel);

		return sidePanel;

	}

	/**
	 * Create the gameInteractionPanel
	 * 
	 * This will have 4 buttons, Move Loot Attack Open
	 * 
	 */
	private JPanel createGameInteractionPanel()
	{
		JPanel gameInteractionPanel = new JPanel();
		gameInteractionPanel.setBackground(Color.GRAY);
		gameInteractionPanel.setLayout(new GridLayout(0, 2, 10, 10));

		JButton moveBtn = new JButton("Move");
		moveBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		moveBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				buttonNumberPressed[0] = true;
				gameInteractionPanel.setVisible(false);
				directionPanel.setVisible(true);
			}
		});
		gameInteractionPanel.add(moveBtn);

		JButton lootBtn = new JButton("Loot");
		lootBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lootBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		lootBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				buttonNumberPressed[1] = true;
				gameInteractionPanel.setVisible(false);
				directionPanel.setVisible(true);
			}
		});
		gameInteractionPanel.add(lootBtn);

		JButton attackBtn = new JButton("Attack");
		attackBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		attackBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				buttonNumberPressed[2] = true;
				gameInteractionPanel.setVisible(false);
				directionPanel.setVisible(true);
			}
		});
		gameInteractionPanel.add(attackBtn);

		JButton openBtn = new JButton("Open");
		openBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		openBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				buttonNumberPressed[2] = true;
				gameInteractionPanel.setVisible(false);
				directionPanel.setVisible(true);
			}
		});
		gameInteractionPanel.add(openBtn);
		return gameInteractionPanel;
	}

	/**
	 * This panel replaces the interaction panel with a panel containing arrow keys
	 * to allow the player to indicate what direction they will move/open/attack
	 * 
	 * @return
	 */
	private JPanel createDirectionPanel()
	{
		JPanel directionPanel = new JPanel();
		directionPanel.setBackground(Color.GRAY);
		directionPanel.setLayout(new GridLayout(0, 3, 10, 10));

		JButton backBtn = new JButton("Back");
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		backBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				buttonNumberPressed[0] = false;
				buttonNumberPressed[1] = false;
				buttonNumberPressed[2] = false;
				buttonNumberPressed[3] = false;
				directionPanel.setVisible(false);
				gameInteractionPanel.setVisible(true);
			}
		});

		JButton upBtn = new JButton("^");
		upBtn.addActionListener(UpBtnListener.getListener());
		upBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));

		JLabel blankLbl = new JLabel();

		JButton leftBtn = new JButton("<");
		leftBtn.addActionListener(LeftBtnListener.getListener());
		leftBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));

		JButton downBtn = new JButton("v");
		downBtn.addActionListener(DownBtnListener.getListener());
		downBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));

		JButton rightBtn = new JButton(">");
		rightBtn.addActionListener(RightBtnListener.getListener());
		rightBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));

		directionPanel.add(backBtn);
		directionPanel.add(upBtn);
		directionPanel.add(blankLbl);

		directionPanel.add(leftBtn);
		directionPanel.add(downBtn);
		directionPanel.add(rightBtn);

		return directionPanel;
	}

	/**
	 * Contains information about the game such as player health, attack roll, what
	 * player's turn it is, current objectives
	 * 
	 * @return
	 */
	private JPanel createResultsPanel()
	{
		JPanel resultsPanel = new JPanel();
		resultsPanel.setBackground(Color.LIGHT_GRAY);
		resultsPanel.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel healthLbl = new JLabel("Health: " /* + healthAmount */);
		healthLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		healthLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resultsPanel.add(healthLbl);

		JLabel diceRollLbl = new JLabel("Dice Roll: " /* + dice.getRoll */);
		diceRollLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		diceRollLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resultsPanel.add(diceRollLbl);

		JLabel actionsLbl = new JLabel("Actions: " /* + numberOfActions */);
		actionsLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		actionsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resultsPanel.add(actionsLbl);

		JLabel objectiveLbl = new JLabel("Objective: " /* + objectiveNumber */);
		objectiveLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		objectiveLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resultsPanel.add(objectiveLbl);

		// This label will be an image
		JLabel statusIconLbl = new JLabel("statusIconLbl");
		// statusIconLbl.setIcon(smileyFace,SadFace);
		statusIconLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		statusIconLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resultsPanel.add(statusIconLbl);

		// Potentially used to display a dice image
		/*
		 * JLabel lblNewLabel_1 = new JLabel("New label"); add(lblNewLabel_1);
		 */
		return resultsPanel;
	}

	/**
	 * method to write the players data to a textfile.
	 * 
	 * @param player1
	 * @param player2
	 */
	private void playerSaveFile(Player player1, Player player2)
	{
		String fileData = "src.groupProject/TextFile/SaveData.txt";

		try (PrintWriter writer = new PrintWriter(fileData))
		{
			writer.println(player1.getName());
			writer.println(player1.getHealth());
			writer.println(player1.getWeapon());
			writer.println(player1.getImage());
			writer.println(player1.getLocation());

			writer.println(player2.getName());
			writer.println(player2.getHealth());
			writer.println(player2.getWeapon());
			writer.println(player2.getImage());
			writer.println(player2.getLocation());

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

//BUNCHA TILES WAHOOOOOO
	Tile tile0 = new Tile();
	Tile tile1;
	Tile tile2;
	Tile tile3;
	Tile tile4;
	Tile tile5;
	Tile tile6;
	Tile tile7;
	Tile tile8;
	Tile tile9;
	Tile tile10;
	Tile tile11;
	Tile tile12;
	Tile tile13;
	Tile tile14;
	Tile tile15;
	Tile tile16;
	Tile tile17;
	Tile tile18;
	Tile tile19;
	Tile tile20;
	Tile tile21;
	Tile tile22;
	Tile tile23;
	Tile tile24;
	Tile tile25;

	ArrayList<Tile> tiles = new ArrayList<>();

	/**
	 * This is used to create all the buttons on the gameBoard The reason this
	 * method is so huge is because we need to set every tile to its own unique
	 * properties.
	 * 
	 * @return
	 */
	private JPanel buttonsBoard()
	{
		JPanel buttonsBoard = new JPanel();
		buttonsBoard.setLayout(new GridLayout(5, 5, 0, 0));

		tile1 = new Tile();
		tile1.setTileLocation(1);
		tile1.northPanel.setBackground(Color.BLACK);
		tile1.setHasNorthWall(true);
		tile1.westPanel.setBackground(Color.BLACK);
		tile1.setHasWestWall(true);
		tile1.eastPanel.setBackground(Color.BLACK);
		tile1.setHasEastWall(true);
		tile1.southPanel.setBackground(Color.DARK_GRAY);
		tile1.setHasSouthWall(false);
		tile1.centralPanel.setBackground(Color.DARK_GRAY);

		tile1.southPanel.setVisible(false);
		buttonsBoard.add(tile1);

		tile2 = new Tile();
		tile2.setTileLocation(2);
		tile2.northPanel.setBackground(Color.RED);
		tile2.setHasNorthWall(true);
		tile2.westPanel.setBackground(Color.LIGHT_GRAY);
		tile2.setHasWestWall(true);
		tile2.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile2.setHasEastWall(true);
		tile2.southPanel.setBackground(Color.LIGHT_GRAY);
		tile2.setHasSouthWall(false);
		tile2.centralPanel.setBackground(Color.LIGHT_GRAY);
		buttonsBoard.add(tile2);

		tile3 = new Tile();
		tile3.setTileLocation(3);
		tile3.northPanel.setBackground(Color.BLACK);
		tile3.setHasNorthWall(true);
		tile3.westPanel.setBackground(Color.BLACK);
		tile3.setHasWestWall(true);
		tile3.eastPanel.setBackground(Color.DARK_GRAY);
		tile3.setHasEastWall(false);
		tile3.southPanel.setBackground(Color.BLACK);
		tile3.setHasSouthWall(true);
		tile3.centralPanel.setBackground(Color.DARK_GRAY);
		buttonsBoard.add(tile3);

		tile4 = new Tile();
		tile4.setTileLocation(4);
		tile4.northPanel.setBackground(Color.BLACK);
		tile4.setHasNorthWall(true);
		tile4.westPanel.setBackground(Color.DARK_GRAY);
		tile4.setHasWestWall(false);
		tile4.eastPanel.setBackground(Color.DARK_GRAY);
		tile4.setHasEastWall(false);
		tile4.southPanel.setBackground(new Color(100, 50, 0));
		tile4.setHasSouthWall(true);
		tile4.centralPanel.setBackground(Color.DARK_GRAY);

		tile4.setHasSouthDoor(true);
		buttonsBoard.add(tile4);

		tile5 = new Tile();
		tile5.setTileLocation(5);
		tile5.northPanel.setBackground(Color.BLACK);
		tile5.setHasNorthWall(true);
		tile5.westPanel.setBackground(Color.DARK_GRAY);
		tile5.setHasWestWall(false);
		tile5.eastPanel.setBackground(Color.BLACK);
		tile5.setHasEastWall(true);
		tile5.southPanel.setBackground(Color.BLACK);
		tile5.setHasSouthWall(true);
		tile5.centralPanel.setBackground(Color.BLUE);

		tile5.setHasObjective(true);
		buttonsBoard.add(tile5);

		tile6 = new Tile();
		tile6.setTileLocation(6);
		tile6.northPanel.setBackground(Color.DARK_GRAY);
		tile6.setHasNorthWall(false);
		tile6.westPanel.setBackground(Color.BLACK);
		tile6.setHasWestWall(true);
		tile6.eastPanel.setBackground(new Color(100, 50, 0));
		tile6.setHasEastWall(true);
		tile6.southPanel.setBackground(Color.BLACK);
		tile6.setHasSouthWall(true);
		tile6.centralPanel.setBackground(Color.DARK_GRAY);

		tile6.northPanel.setVisible(false);
		buttonsBoard.add(tile6);

		tile7 = new Tile();
		tile7.setTileLocation(7);
		tile7.northPanel.setBackground(Color.LIGHT_GRAY);
		tile7.setHasNorthWall(false);
		tile7.westPanel.setBackground(Color.LIGHT_GRAY);
		tile7.setHasWestWall(true);
		tile7.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile7.setHasEastWall(false);
		tile7.southPanel.setBackground(Color.LIGHT_GRAY);
		tile7.setHasSouthWall(false);
		tile7.centralPanel.setBackground(Color.LIGHT_GRAY);

		tile7.setHasWestDoor(true);
		buttonsBoard.add(tile7);

		tile8 = new Tile();
		tile8.setTileLocation(8);
		tile8.northPanel.setBackground(Color.LIGHT_GRAY);
		tile8.setHasNorthWall(true);
		tile8.westPanel.setBackground(Color.LIGHT_GRAY);
		tile8.setHasWestWall(false);
		tile8.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile8.setHasEastWall(false);
		tile8.southPanel.setBackground(Color.LIGHT_GRAY);
		tile8.setHasSouthWall(true);
		tile8.centralPanel.setBackground(Color.LIGHT_GRAY);

		tile8.setHasSouthDoor(true);
		buttonsBoard.add(tile8);

		tile9 = new Tile();
		tile9.setTileLocation(9);
		tile9.northPanel.setBackground(Color.LIGHT_GRAY);
		tile9.setHasNorthWall(true);
		tile9.westPanel.setBackground(Color.LIGHT_GRAY);
		tile9.setHasWestWall(false);
		tile9.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile9.setHasEastWall(false);
		tile9.southPanel.setBackground(Color.LIGHT_GRAY);
		tile9.setHasSouthWall(true);
		tile9.centralPanel.setBackground(Color.LIGHT_GRAY);

		tile9.setHasNorthDoor(true);
		buttonsBoard.add(tile9);

		tile10 = new Tile();
		tile10.setTileLocation(10);
		tile10.northPanel.setBackground(Color.LIGHT_GRAY);
		tile10.setHasNorthWall(true);
		tile10.westPanel.setBackground(Color.LIGHT_GRAY);
		tile10.setHasWestWall(false);
		tile10.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile10.setHasEastWall(true);
		tile10.southPanel.setBackground(Color.LIGHT_GRAY);
		tile10.setHasSouthWall(false);
		tile10.centralPanel.setBackground(Color.LIGHT_GRAY);
		buttonsBoard.add(tile10);

		tile11 = new Tile();
		tile11.setTileLocation(11);
		tile11.northPanel.setBackground(Color.LIGHT_GRAY);
		tile11.setHasNorthWall(true);
		tile11.westPanel.setBackground(Color.RED);
		tile11.setHasWestWall(true);
		tile11.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile11.setHasEastWall(false);
		tile11.southPanel.setBackground(Color.LIGHT_GRAY);
		tile11.setHasSouthWall(true);
		tile11.centralPanel.setBackground(Color.LIGHT_GRAY);
		buttonsBoard.add(tile11);

		tile12 = new Tile();
		tile12.setTileLocation(12);
		tile12.northPanel.setBackground(Color.LIGHT_GRAY);
		tile12.setHasNorthWall(false);
		tile12.westPanel.setBackground(Color.LIGHT_GRAY);
		tile12.setHasWestWall(false);
		tile12.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile12.setHasEastWall(true);
		tile12.southPanel.setBackground(Color.LIGHT_GRAY);
		tile12.setHasSouthWall(false);
		tile12.centralPanel.setBackground(Color.LIGHT_GRAY);
		buttonsBoard.add(tile12);

		tile13 = new Tile();
		tile13.setTileLocation(13);
		tile13.northPanel.setBackground(new Color(100, 50, 0));
		tile13.setHasNorthWall(true);
		tile13.westPanel.setBackground(Color.BLACK);
		tile13.setHasWestWall(true);
		tile13.eastPanel.setBackground(Color.DARK_GRAY);
		tile13.setHasEastWall(false);
		tile13.southPanel.setBackground(Color.DARK_GRAY);
		tile13.setHasSouthWall(false);
		tile13.centralPanel.setBackground(Color.DARK_GRAY);

		tile13.southPanel.setVisible(false);
		buttonsBoard.add(tile13);

		tile14 = new Tile();
		tile14.setTileLocation(14);
		tile14.northPanel.setBackground(Color.BLACK);
		tile14.setHasNorthWall(true);
		tile14.westPanel.setBackground(Color.DARK_GRAY);
		tile14.setHasWestWall(false);
		tile14.eastPanel.setBackground(Color.BLACK);
		tile14.setHasEastWall(true);
		tile14.southPanel.setBackground(Color.DARK_GRAY);
		tile14.setHasSouthWall(false);
		tile14.centralPanel.setBackground(Color.DARK_GRAY);

		tile14.southPanel.setVisible(false);
		buttonsBoard.add(tile14);

		tile15 = new Tile();
		tile15.setTileLocation(15);
		tile15.northPanel.setBackground(Color.LIGHT_GRAY);
		tile15.setHasNorthWall(false);
		tile15.westPanel.setBackground(Color.LIGHT_GRAY);
		tile15.setHasWestWall(true);
		tile15.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile15.setHasEastWall(true);
		tile15.southPanel.setBackground(Color.LIGHT_GRAY);
		tile15.setHasSouthWall(false);
		tile15.centralPanel.setBackground(Color.LIGHT_GRAY);
		buttonsBoard.add(tile15);

		tile16 = new Tile();
		tile16.setTileLocation(16);
		tile16.northPanel.setBackground(Color.BLACK);
		tile16.setHasNorthWall(true);
		tile16.westPanel.setBackground(Color.BLACK);
		tile16.setHasWestWall(true);
		tile16.eastPanel.setBackground(new Color(100, 50, 0));
		tile16.setHasEastWall(true);
		tile16.southPanel.setBackground(Color.DARK_GRAY);
		tile16.setHasSouthWall(false);
		tile16.centralPanel.setBackground(Color.DARK_GRAY);

		tile16.setHasEastDoor(true);
		tile16.southPanel.setVisible(false);
		buttonsBoard.add(tile16);

		tile17 = new Tile();
		tile17.setTileLocation(17);
		tile17.northPanel.setBackground(Color.LIGHT_GRAY);
		tile17.setHasNorthWall(false);
		tile17.westPanel.setBackground(Color.LIGHT_GRAY);
		tile17.setHasWestWall(true);
		tile17.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile17.setHasEastWall(true);
		tile17.southPanel.setBackground(Color.LIGHT_GRAY);
		tile17.setHasSouthWall(false);
		tile17.centralPanel.setBackground(Color.LIGHT_GRAY);

		tile17.setHasWestDoor(true);
		buttonsBoard.add(tile17);

		tile18 = new Tile();
		tile18.setTileLocation(18);
		tile18.northPanel.setBackground(Color.DARK_GRAY);
		tile18.setHasNorthWall(false);
		tile18.westPanel.setBackground(Color.BLACK);
		tile18.setHasWestWall(true);
		tile18.eastPanel.setBackground(Color.DARK_GRAY);
		tile18.setHasEastWall(false);
		tile18.southPanel.setBackground(Color.DARK_GRAY);
		tile18.setHasSouthWall(false);
		tile18.centralPanel.setBackground(Color.DARK_GRAY);

		tile18.northPanel.setVisible(false);
		tile18.southPanel.setVisible(false);
		buttonsBoard.add(tile18);

		tile19 = new Tile();
		tile19.setTileLocation(19);
		tile19.northPanel.setBackground(Color.DARK_GRAY);
		tile19.setHasNorthWall(false);
		tile19.westPanel.setBackground(Color.DARK_GRAY);
		tile19.setHasWestWall(false);
		tile19.eastPanel.setBackground(Color.BLACK);
		tile19.setHasEastWall(true);
		tile19.southPanel.setBackground(Color.DARK_GRAY);
		tile19.setHasSouthWall(false);
		tile19.centralPanel.setBackground(Color.DARK_GRAY);

		tile19.northPanel.setVisible(false);
		tile19.southPanel.setVisible(false);
		buttonsBoard.add(tile19);

		tile20 = new Tile();
		tile20.setTileLocation(20);
		tile20.northPanel.setBackground(Color.LIGHT_GRAY);
		tile20.setHasNorthWall(false);
		tile20.westPanel.setBackground(Color.LIGHT_GRAY);
		tile20.setHasWestWall(true);
		tile20.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile20.setHasEastWall(true);
		tile20.southPanel.setBackground(Color.LIGHT_GRAY);
		tile20.setHasSouthWall(false);
		tile20.centralPanel.setBackground(Color.LIGHT_GRAY);
		buttonsBoard.add(tile20);

		tile21 = new Tile();
		tile21.setTileLocation(21);
		tile21.northPanel.setBackground(Color.DARK_GRAY);
		tile21.setHasNorthWall(false);
		tile21.westPanel.setBackground(Color.BLACK);
		tile21.setHasWestWall(true);
		tile21.eastPanel.setBackground(Color.BLACK);
		tile21.setHasEastWall(true);
		tile21.southPanel.setBackground(Color.BLACK);
		tile21.setHasSouthWall(true);
		tile21.centralPanel.setBackground(Color.BLUE);

		tile21.setHasObjective(true);
		tile21.northPanel.setVisible(false);
		buttonsBoard.add(tile21);

		tile22 = new Tile();
		tile22.setTileLocation(22);
		tile22.northPanel.setBackground(Color.LIGHT_GRAY);
		tile22.setHasNorthWall(false);
		tile22.westPanel.setBackground(Color.LIGHT_GRAY);
		tile22.setHasWestWall(true);
		tile22.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile22.setHasEastWall(true);
		tile22.southPanel.setBackground(Color.RED);
		tile22.setHasSouthWall(true);
		tile22.centralPanel.setBackground(Color.LIGHT_GRAY);
		buttonsBoard.add(tile22);

		tile23 = new Tile();
		tile23.setTileLocation(23);
		tile23.northPanel.setBackground(Color.DARK_GRAY);
		tile23.setHasNorthWall(false);
		tile23.westPanel.setBackground(Color.BLACK);
		tile23.setHasWestWall(true);
		tile23.eastPanel.setBackground(Color.DARK_GRAY);
		tile23.setHasEastWall(false);
		tile23.southPanel.setBackground(Color.BLACK);
		tile23.setHasSouthWall(true);
		tile23.centralPanel.setBackground(Color.DARK_GRAY);

		tile23.northPanel.setVisible(false);
		buttonsBoard.add(tile23);

		tile24 = new Tile();
		tile24.setTileLocation(24);
		tile24.northPanel.setBackground(Color.DARK_GRAY);
		tile24.setHasNorthWall(false);
		tile24.westPanel.setBackground(Color.DARK_GRAY);
		tile24.setHasWestWall(false);
		tile24.eastPanel.setBackground(Color.BLACK);
		tile24.setHasEastWall(true);
		tile24.southPanel.setBackground(Color.BLACK);
		tile24.setHasSouthWall(true);
		tile24.centralPanel.setBackground(Color.BLUE);

		tile24.setHasObjective(true);
		tile24.northPanel.setVisible(false);
		buttonsBoard.add(tile24);

		tile25 = new Tile();
		tile25.setTileLocation(25);
		tile25.northPanel.setBackground(Color.LIGHT_GRAY);
		tile25.setHasNorthWall(false);
		tile25.westPanel.setBackground(Color.LIGHT_GRAY);
		tile25.setHasWestWall(true);
		tile25.eastPanel.setBackground(Color.LIGHT_GRAY);
		tile25.setHasEastWall(true);
		tile25.southPanel.setBackground(Color.LIGHT_GRAY);
		tile25.setHasSouthWall(true);
		tile25.centralPanel.setBackground(Color.LIGHT_GRAY);
		buttonsBoard.add(tile25);

		tiles.addAll(Arrays.asList(tile0, tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11,
				tile12, tile13, tile14, tile15, tile16, tile17, tile18, tile19, tile20, tile21, tile22, tile23, tile24,
				tile25));

		return buttonsBoard;
	}
}
