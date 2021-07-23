package groupProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * 
 * 
 * @author Andrew, Cody, Hayden
 *
 */
@SuppressWarnings("serial")
public class TitleFrame extends JFrame
{

	// Layered Pane is used for character selection frame, characters is a boolean
	// array returning which characters are chosen
	private JLayeredPane layeredPane;
	private final boolean[] characters = {false, false, false, false, false};
		

	// Additional fields used for functionality
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Used with setbounds, we take width and
																				// height of screen, and divide by 6,
																				// set those as starting points for the
																				// bounds
	private byte charactersSelected = 0;
	private JPanel readyPnl; // Set readyPnl as a field so that we may access it from the character menu

	/*-----------------------------------------------------------------------------------
	 * This code block is all the main menu that first pops up,
	 * that being, the JFrame that will allow you to select two characters
	 * Jump down to the comment stating that the following code is all for the game board
	 -----------------------------------------------------------------------------------*/

	/**
	 * Create the menu frame of this game This will include a layered pane, and we
	 * run methods to create all the components. We then add each component to a
	 * layered pane, which acts as the content pane of the character selection frame
	 * 
	 * @author Andrew
	 */
	public TitleFrame()
	{

		// Create a frame
		JFrame frame = new JFrame("Javapocalypse");

		// Create a layeredPane
		layeredPane = new JLayeredPane();
		layeredPane.setSize(new Dimension(1260, 900));

		JPanel backgroundPnl = new JPanel();

		// Creating the background of menu
		createMenuBackground(backgroundPnl);

		// Creating a JLabel to hold the title text
		JLabel titleLbl = createMenuJavapocalypse();

		// Create the character selections
		JPanel characterPnl = createCharacterPnl(frame);

		// Create a Ready label that appears once 2 characters are selected
		JPanel readyPnl = createReadyBtn(frame);

		// Add all the components to the layeredPane
		layeredPane.add(backgroundPnl, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(titleLbl, Integer.valueOf(2));
		layeredPane.add(characterPnl, Integer.valueOf(1));
		layeredPane.add(readyPnl, Integer.valueOf(2));

		// Set the properties of the frame and add the layered pane
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(dim.width / 6, dim.height / 8 - 50, 1260, 900);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().add(layeredPane);

	}

	/**
	 * Used to create the ready button that appears when 2 characters are selected
	 * Once the ready button is selected, we .dispose of the frame and jump down to
	 * MainFrame() to run the game board This is likely where we can send the
	 * character selection down as well.
	 *
	 * @param frame
	 * @return
	 */
	private JPanel createReadyBtn(JFrame frame)
	{
		readyPnl = new JPanel();
		readyPnl.setBounds(this.getX() + 570, this.getY() + 600, 130, 95);
		readyPnl.setVisible(false);
		readyPnl.setLayout(new GridLayout(0, 1, 0, 0));

		// Create a ready button, and add it to the readyPnl
		JButton readyBtn = new JButton();

		ImageIcon readyImg = new ImageIcon(TitleFrame.class.getResource("/resources/ready.png"));
		readyBtn.setIcon(readyImg);
		readyBtn.setBounds(0, 0, 50, 50);
		readyPnl.add(readyBtn);

		// Add an action listener to the screen
		readyBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispose();
				@SuppressWarnings("unused")
				GameBoard gamePanel = new GameBoard();
			}
		});

		return readyPnl;
	}

	/**
	 * 
	 * We still need to add images for each character We also need a way to pass the
	 * characters that are selected to the gameplay frame
	 * 
	 * TODO
	 * 
	 * @param frame
	 * @return
	 */
	private JPanel createCharacterPnl(JFrame frame)
	{
		JPanel centerPnl = new JPanel();
		centerPnl.setBackground(new Color(139, 0, 0));
		centerPnl.setBounds(frame.getX() + 315, frame.getY() + 200, 630, 300);
		centerPnl.setLayout(new BorderLayout(0, 0));

		JLabel lblPrompt = new JLabel("Choose 2 characters!");
		lblPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrompt.setFont(new Font("Ink Free", Font.BOLD, 39));
		lblPrompt.setForeground(new Color(50, 205, 50));

		// Create a new panel that we can stick in the south, to hold 4 characters
		JPanel characterPnl = new JPanel();
		characterPnl.setBackground(new Color(139, 0, 0));

		//Create the characters and their checkboxes with images
		JCheckBox char1chx = new JCheckBox("Character 1");
		char1chx.setIcon(new ImageIcon(TitleFrame.class.getResource("/resources/old-woman-pistol.png")));
		JCheckBox char2chx = new JCheckBox("Character 2");
		char2chx.setIcon(new ImageIcon(TitleFrame.class.getResource("/resources/angry-guy.png")));
		JCheckBox char3chx = new JCheckBox("Character 3");
		char3chx.setIcon(new ImageIcon(TitleFrame.class.getResource("/resources/portly-mobster-wearing-suit.png")));
		JCheckBox char4chx = new JCheckBox("Character 4");
		char4chx.setIcon(new ImageIcon(TitleFrame.class.getResource("/resources/pirate-kid.png")));
		characterPnl.setLayout(new GridLayout(0, 4, 0, 0));

		//Add all the checkboxes to the large component of characterPnl
		characterPnl.add(char1chx);
		characterPnl.add(char2chx);
		characterPnl.add(char3chx);
		characterPnl.add(char4chx);

		// This is to make sure that we don't select more than 2 characters
			//The boolean parameters passed are used to set on or off whether a character is chosen
			//This is then used to determine which characters are used in game
		checkBoxCheck(char1chx, 1);
		checkBoxCheck(char2chx, 2);
		checkBoxCheck(char3chx, 3);
		checkBoxCheck(char4chx, 4);

		// Add both the prompt and the character panel to the center panel
		centerPnl.add(lblPrompt, BorderLayout.NORTH);
		centerPnl.add(characterPnl, BorderLayout.CENTER);
		return centerPnl;
	}

	/**
	 * Checks if we have only 2 characters checked
	 * 
	 * @param charChx
	 */
	private void checkBoxCheck(JCheckBox charChx, int charNum)
	{
		charChx.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if (!charChx.isSelected())
				{
					charactersSelected--;
					characters[charNum] = false;
				} else
				{
					charactersSelected++;
					characters[charNum] = true;
				}
				if (charactersSelected == 3)
				{
					charChx.setSelected(false);
				}
				if (charactersSelected == 2)
				{
					readyPnl.setVisible(true);
				}
				if (charactersSelected == 1)
				{
					readyPnl.setVisible(false);
				}
			}
		});
	}

	/**
	 * This method is simple-- It creates the word "JAVAPOCALYPSE" that runs along
	 * the top of the frame
	 * 
	 * @return
	 */
	private JLabel createMenuJavapocalypse()
	{
		JLabel titleLbl = new JLabel();
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon titleIcon = new ImageIcon(TitleFrame.class.getResource("/resources/menuJavapocalypse.png"));
		titleLbl.setIcon(titleIcon);
		titleLbl.setBounds(0, 0, titleIcon.getIconWidth(), titleIcon.getIconHeight());
		return titleLbl;
	}

	/**
	 * Sets the background to that red zombie looking business.
	 * 
	 * @param jPanel
	 */
	private void createMenuBackground(JPanel jPanel)
	{
		ImageIcon menuBackground = new ImageIcon(TitleFrame.class.getResource("/resources/menuBackground.png"));
		JLabel jLabel = new JLabel();
		jLabel.setIcon(menuBackground);

		// using this to set the bounds of the jPanel to hold image, and add the jLabel
		// that holds the image
		jPanel.setBounds(0, 0, menuBackground.getIconWidth(), menuBackground.getIconHeight());
		jPanel.add(jLabel);
	}

	
	public boolean[] getCharacters() {
		return characters;
	}
}
