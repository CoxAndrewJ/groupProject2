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
		// this creates teh menu bar so that we can have a save option
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("File");
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
		// PERHAPS I WILL LEAVE THE GAMEBOARD AS ITS OWN CLASS, IT MAY BE EASIER TO WORK
		// THAT WAY

		///////////////////// VERY IMPORTANT
		JPanel gameBoard = new JPanel();
		gameBoard.setPreferredSize(new Dimension(50, 50));
		contentPane.add(gameBoard, BorderLayout.CENTER);
		gameBoard.setLayout(new GridLayout(5, 5));

		// Creates 100 buttons for the game board
		buttonsOnBoard(gameBoard);

		// Create a new panel to be on the East side, and hold two more panels--
		// resultsPanel & gameInteractionPanel
		JPanel sidePanel = createSidePanel();
		contentPane.add(sidePanel, BorderLayout.EAST);

		// mainFrame.setContentPane(contentPane); I think this is not needed.
		
	
	
	}
	/**
	 * event is in order to save from the menu bar. 
	 * I am not sure why it is not recognizing those two variables but will figure it out later
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save) {
			playerSaveFile(player1, player2);
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
}
