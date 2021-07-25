package groupProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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

	public List<Integer> buildingSquares = new ArrayList<>();

	/**
	 * TODO Create the main frame of the item We will add 2 panels: the gameBoard
	 * panel on west and the sidePanel on east We then add two more panels to the
	 * east panel
	 * 
	 * @author Andrew
	 */

	public GameBoard()
	{

		// Setting up basic properties of frame
		JFrame mainFrame = new JFrame();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 1500, 800);
		
		// this creates the menu bar so that we can have a save option
		JMenuBar mb = new JMenuBar();
		file = new JMenu("File");
		mb.add(file);
		JMenuItem save = new JMenuItem("Save");
		file.add(save);
		save.addActionListener(this);
		JTextArea ta=new JTextArea();    
		 ta.setBounds(5,5,360,320);    
		mainFrame.add(mb);mainFrame.add(ta);    
		mainFrame.setJMenuBar(mb);  
		mainFrame.setLayout(null);    
		mainFrame.setVisible(true);   
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Create the game Panel
		JPanel gameBoard = new JPanel();
		gameBoard.setPreferredSize(new Dimension(50, 50));
		contentPane.add(gameBoard, BorderLayout.CENTER);
		gameBoard.setLayout(new GridLayout(5, 5));
		gameBoard.add(buttonsBoard());
		
		// Creates 25 buttons for the game board
	//	buttonBoard(gameBoard);

		// Create a new panel to be on the East side, and hold two more panels--
		// resultsPanel & gameInteractionPanel
		JPanel sidePanel = createSidePanel();
		contentPane.add(sidePanel, BorderLayout.EAST);

	}
	
	/**
	 * event is in order to save from the menu bar. 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == file) {
			playerSaveFile(Javapocalypse.getPlayer1(), Javapocalypse.getPlayer2());
		}
	}

	
	private void buttonBoard(JPanel gameBoard)
	{
		List<Integer> data = new ArrayList<>();
		for (int i = 0; i < 25; i++)
		{
			data.add(i);
		}
		for (int index = 0; index < data.size(); index++)
		{

	
			
			JLayeredPane tile = new JLayeredPane();
			tile.setBorder(new EmptyBorder(0, 0, 0, 0));
			// tile.setLayout(new GridLayout(2, 2, 10, 10));
			tile.setOpaque(true);

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(2, 2, 10, 10));

			JLabel lbl1 = new JLabel("1");
			JLabel lbl2 = new JLabel("2");
			JLabel lbl3 = new JLabel("4");
			JLabel lbl4 = new JLabel("3");
			panel.add(lbl1);
			panel.add(lbl2);
			panel.add(lbl3);
			panel.add(lbl4);
			JButton button = new JButton();
			button.setSize(100, 100);
			button.setBounds(0, 50, 50, 50);
			button.setContentAreaFilled(false);

			tile.add(panel, JLayeredPane.DEFAULT_LAYER);
			tile.add(button, JLayeredPane.DRAG_LAYER);
			gameBoard.add(tile);

			if (buildingSquares.contains(index))
			{
				tile.setBackground(new Color(150, 90, 20));

			} else
			{
				tile.setBackground(new Color(50, 185, 30));
			}

		}
	}
	
	/**
	 * Creates the buttons that will act as the game board.
	 * 
	 * @param gameBoard
	 */
	private void buttonsOnBoard(JPanel gameBoard)
	{
		List<Integer> data = new ArrayList<>();
		for (int i = 0; i < 25; i++)
		{
			data.add(i);
		}
		for (int index = 0; index < data.size(); index++)
		{
			JButton button = new JButton("" + index);
			button.setBounds(0, 50, 50, 50);
			gameBoard.add(button);

		}
	}

	/**
	 * -------------------------------------------------- All the following code is
	 * for the side Panel. --------------------------------------------------- The
	 * side panel is on the east side and contains two more panels-- resultsPanel
	 * gameInteractionPanel
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

		// Add the gameInteractionPanel to the sidePanel
		// will go on the lower side
		JPanel gameInteractionPanel = createGameInteractionPanel();
		sidePanel.add(gameInteractionPanel);

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
		gameInteractionPanel.add(moveBtn);

		JButton lootBtn = new JButton("Loot");
		lootBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lootBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		gameInteractionPanel.add(lootBtn);

		JButton attackBtn = new JButton("Attack");
		attackBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		gameInteractionPanel.add(attackBtn);

		JButton openBtn = new JButton("Open");
		openBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		gameInteractionPanel.add(openBtn);
		return gameInteractionPanel;
	}


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
	//method to write the players data to a textfile. 
	private void playerSaveFile(Player player1, Player player2) {
		String fileData = "src.groupProject/TextFile/SaveData.txt";
		
		try(PrintWriter writer = new PrintWriter(fileData)){
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
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * This is used to create all the buttons on the gameBoard
	 * The reason this method is so huge is because we need to set every tile to its own unique properties.
	 * 
	 * @return
	 */
	private JPanel buttonsBoard() {
		JPanel buttonsBoard = new JPanel();
		
		Tile tile1 = new Tile();
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
			buttonsBoard.add(tile1);
			
		Tile tile2 = new Tile();
			tile2.setTileLocation(2);
				tile2.northPanel.setBackground(Color.BLACK);
				tile2.setHasNorthWall(true);			
				tile2.westPanel.setBackground(Color.BLACK);
				tile2.setHasWestWall(true);
				tile2.eastPanel.setBackground(Color.BLACK);
				tile2.setHasEastWall(true);
				tile2.southPanel.setBackground(Color.DARK_GRAY);
				tile2.setHasSouthWall(false);
				tile2.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile2);
			
		Tile tile3 = new Tile();
			tile3.setTileLocation(3);
				tile3.northPanel.setBackground(Color.BLACK);
				tile3.setHasNorthWall(true);			
				tile3.westPanel.setBackground(Color.BLACK);
				tile3.setHasWestWall(true);
				tile3.eastPanel.setBackground(Color.BLACK);
				tile3.setHasEastWall(true);
				tile3.southPanel.setBackground(Color.DARK_GRAY);
				tile3.setHasSouthWall(false);
				tile3.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile3);

		Tile tile4 = new Tile();
			tile4.setTileLocation(4);
				tile4.northPanel.setBackground(Color.BLACK);
				tile4.setHasNorthWall(true);			
				tile4.westPanel.setBackground(Color.BLACK);
				tile4.setHasWestWall(true);
				tile4.eastPanel.setBackground(Color.BLACK);
				tile4.setHasEastWall(true);
				tile4.southPanel.setBackground(Color.DARK_GRAY);
				tile4.setHasSouthWall(false);
				tile4.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile4);

		Tile tile5 = new Tile();
			tile5.setTileLocation(5);
				tile5.northPanel.setBackground(Color.BLACK);
				tile5.setHasNorthWall(true);			
				tile5.westPanel.setBackground(Color.BLACK);
				tile5.setHasWestWall(true);
				tile5.eastPanel.setBackground(Color.BLACK);
				tile5.setHasEastWall(true);
				tile5.southPanel.setBackground(Color.DARK_GRAY);
				tile5.setHasSouthWall(false);
				tile5.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile5);
			
		Tile tile6 = new Tile();
			tile6.setTileLocation(6);
				tile6.northPanel.setBackground(Color.BLACK);
				tile6.setHasNorthWall(true);			
				tile6.westPanel.setBackground(Color.BLACK);
				tile6.setHasWestWall(true);
				tile6.eastPanel.setBackground(Color.BLACK);
				tile6.setHasEastWall(true);
				tile6.southPanel.setBackground(Color.DARK_GRAY);
				tile6.setHasSouthWall(false);
				tile6.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile6);
			
		Tile tile7 = new Tile();
			tile7.setTileLocation(7);
				tile7.northPanel.setBackground(Color.BLACK);
				tile7.setHasNorthWall(true);			
				tile7.westPanel.setBackground(Color.BLACK);
				tile7.setHasWestWall(true);
				tile7.eastPanel.setBackground(Color.BLACK);
				tile7.setHasEastWall(true);
				tile7.southPanel.setBackground(Color.DARK_GRAY);
				tile7.setHasSouthWall(false);
				tile7.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile7);
			
		Tile tile8 = new Tile();
			tile8.setTileLocation(8);
				tile8.northPanel.setBackground(Color.BLACK);
				tile8.setHasNorthWall(true);			
				tile8.westPanel.setBackground(Color.BLACK);
				tile8.setHasWestWall(true);
				tile8.eastPanel.setBackground(Color.BLACK);
				tile8.setHasEastWall(true);
				tile8.southPanel.setBackground(Color.DARK_GRAY);
				tile8.setHasSouthWall(false);
				tile8.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile8);
			
		Tile tile9 = new Tile();
			tile9.setTileLocation(9);
				tile9.northPanel.setBackground(Color.BLACK);
				tile9.setHasNorthWall(true);			
				tile9.westPanel.setBackground(Color.BLACK);
				tile9.setHasWestWall(true);
				tile9.eastPanel.setBackground(Color.BLACK);
				tile9.setHasEastWall(true);
				tile9.southPanel.setBackground(Color.DARK_GRAY);
				tile9.setHasSouthWall(false);
				tile9.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile9);
			
		Tile tile10 = new Tile();
			tile10.setTileLocation(10);
				tile10.northPanel.setBackground(Color.BLACK);
				tile10.setHasNorthWall(true);			
				tile10.westPanel.setBackground(Color.BLACK);
				tile10.setHasWestWall(true);
				tile10.eastPanel.setBackground(Color.BLACK);
				tile10.setHasEastWall(true);
				tile10.southPanel.setBackground(Color.DARK_GRAY);
				tile10.setHasSouthWall(false);
				tile10.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile10);
			
		Tile tile11 = new Tile();
			tile11.setTileLocation(11);
				tile11.northPanel.setBackground(Color.BLACK);
				tile11.setHasNorthWall(true);			
				tile11.westPanel.setBackground(Color.BLACK);
				tile11.setHasWestWall(true);
				tile11.eastPanel.setBackground(Color.BLACK);
				tile11.setHasEastWall(true);
				tile11.southPanel.setBackground(Color.DARK_GRAY);
				tile11.setHasSouthWall(false);
				tile11.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile11);
			
		Tile tile12 = new Tile();
			tile12.setTileLocation(12);
				tile12.northPanel.setBackground(Color.BLACK);
				tile12.setHasNorthWall(true);			
				tile12.westPanel.setBackground(Color.BLACK);
				tile12.setHasWestWall(true);
				tile12.eastPanel.setBackground(Color.BLACK);
				tile12.setHasEastWall(true);
				tile12.southPanel.setBackground(Color.DARK_GRAY);
				tile12.setHasSouthWall(false);
				tile12.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile12);
			
		Tile tile13 = new Tile();
			tile13.setTileLocation(13);
				tile13.northPanel.setBackground(Color.BLACK);
				tile13.setHasNorthWall(true);			
				tile13.westPanel.setBackground(Color.BLACK);
				tile13.setHasWestWall(true);
				tile13.eastPanel.setBackground(Color.BLACK);
				tile13.setHasEastWall(true);
				tile13.southPanel.setBackground(Color.DARK_GRAY);
				tile13.setHasSouthWall(false);
				tile13.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile13);
			
		Tile tile14 = new Tile();
			tile14.setTileLocation(14);
				tile14.northPanel.setBackground(Color.BLACK);
				tile14.setHasNorthWall(true);			
				tile14.westPanel.setBackground(Color.BLACK);
				tile14.setHasWestWall(true);
				tile14.eastPanel.setBackground(Color.BLACK);
				tile14.setHasEastWall(true);
				tile14.southPanel.setBackground(Color.DARK_GRAY);
				tile14.setHasSouthWall(false);
				tile14.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile14);
			
		Tile tile15 = new Tile();
			tile15.setTileLocation(15);
				tile15.northPanel.setBackground(Color.BLACK);
				tile15.setHasNorthWall(true);			
				tile15.westPanel.setBackground(Color.BLACK);
				tile15.setHasWestWall(true);
				tile15.eastPanel.setBackground(Color.BLACK);
				tile15.setHasEastWall(true);
				tile15.southPanel.setBackground(Color.DARK_GRAY);
				tile15.setHasSouthWall(false);
				tile15.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile15);
			
		Tile tile16 = new Tile();
			tile16.setTileLocation(16);
				tile16.northPanel.setBackground(Color.BLACK);
				tile16.setHasNorthWall(true);			
				tile16.westPanel.setBackground(Color.BLACK);
				tile16.setHasWestWall(true);
				tile16.eastPanel.setBackground(Color.BLACK);
				tile16.setHasEastWall(true);
				tile16.southPanel.setBackground(Color.DARK_GRAY);
				tile16.setHasSouthWall(false);
				tile16.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile16);
			
		Tile tile17 = new Tile();
			tile2.setTileLocation(17);
				tile17.northPanel.setBackground(Color.BLACK);
				tile17.setHasNorthWall(true);			
				tile17.westPanel.setBackground(Color.BLACK);
				tile17.setHasWestWall(true);
				tile17.eastPanel.setBackground(Color.BLACK);
				tile17.setHasEastWall(true);
				tile17.southPanel.setBackground(Color.DARK_GRAY);
				tile17.setHasSouthWall(false);
				tile17.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile17);
			
		Tile tile18 = new Tile();
			tile18.setTileLocation(18);
				tile18.northPanel.setBackground(Color.BLACK);
				tile18.setHasNorthWall(true);			
				tile18.westPanel.setBackground(Color.BLACK);
				tile18.setHasWestWall(true);
				tile18.eastPanel.setBackground(Color.BLACK);
				tile18.setHasEastWall(true);
				tile18.southPanel.setBackground(Color.DARK_GRAY);
				tile18.setHasSouthWall(false);
				tile18.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile18);
			
		Tile tile19 = new Tile();
			tile2.setTileLocation(19);
				tile2.northPanel.setBackground(Color.BLACK);
				tile2.setHasNorthWall(true);			
				tile2.westPanel.setBackground(Color.BLACK);
				tile2.setHasWestWall(true);
				tile2.eastPanel.setBackground(Color.BLACK);
				tile2.setHasEastWall(true);
				tile2.southPanel.setBackground(Color.DARK_GRAY);
				tile2.setHasSouthWall(false);
				tile2.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile2);
			
		Tile tile20 = new Tile();
			tile2.setTileLocation(20);
				tile2.northPanel.setBackground(Color.BLACK);
				tile2.setHasNorthWall(true);			
				tile2.westPanel.setBackground(Color.BLACK);
				tile2.setHasWestWall(true);
				tile2.eastPanel.setBackground(Color.BLACK);
				tile2.setHasEastWall(true);
				tile2.southPanel.setBackground(Color.DARK_GRAY);
				tile2.setHasSouthWall(false);
				tile2.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile2);
			
		Tile tile21 = new Tile();
			tile2.setTileLocation(21);
				tile2.northPanel.setBackground(Color.BLACK);
				tile2.setHasNorthWall(true);			
				tile2.westPanel.setBackground(Color.BLACK);
				tile2.setHasWestWall(true);
				tile2.eastPanel.setBackground(Color.BLACK);
				tile2.setHasEastWall(true);
				tile2.southPanel.setBackground(Color.DARK_GRAY);
				tile2.setHasSouthWall(false);
				tile2.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile2);
			
		Tile tile22 = new Tile();
			tile2.setTileLocation(22);
				tile2.northPanel.setBackground(Color.BLACK);
				tile2.setHasNorthWall(true);			
				tile2.westPanel.setBackground(Color.BLACK);
				tile2.setHasWestWall(true);
				tile2.eastPanel.setBackground(Color.BLACK);
				tile2.setHasEastWall(true);
				tile2.southPanel.setBackground(Color.DARK_GRAY);
				tile2.setHasSouthWall(false);
				tile2.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile2);
			
		Tile tile23 = new Tile();
			tile2.setTileLocation(23);
				tile2.northPanel.setBackground(Color.BLACK);
				tile2.setHasNorthWall(true);			
				tile2.westPanel.setBackground(Color.BLACK);
				tile2.setHasWestWall(true);
				tile2.eastPanel.setBackground(Color.BLACK);
				tile2.setHasEastWall(true);
				tile2.southPanel.setBackground(Color.DARK_GRAY);
				tile2.setHasSouthWall(false);
				tile2.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile2);
			
		Tile tile24 = new Tile();
			tile2.setTileLocation(24);
				tile2.northPanel.setBackground(Color.BLACK);
				tile2.setHasNorthWall(true);			
				tile2.westPanel.setBackground(Color.BLACK);
				tile2.setHasWestWall(true);
				tile2.eastPanel.setBackground(Color.BLACK);
				tile2.setHasEastWall(true);
				tile2.southPanel.setBackground(Color.DARK_GRAY);
				tile2.setHasSouthWall(false);
				tile2.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile2);
			
		Tile tile25 = new Tile();
			tile2.setTileLocation(25);
				tile2.northPanel.setBackground(Color.BLACK);
				tile2.setHasNorthWall(true);			
				tile2.westPanel.setBackground(Color.BLACK);
				tile2.setHasWestWall(true);
				tile2.eastPanel.setBackground(Color.BLACK);
				tile2.setHasEastWall(true);
				tile2.southPanel.setBackground(Color.DARK_GRAY);
				tile2.setHasSouthWall(false);
				tile2.centralPanel.setBackground(Color.DARK_GRAY);
			buttonsBoard.add(tile2);
			
			
			
		return buttonsBoard;
	}
}
