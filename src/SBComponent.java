
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 * This class is the one that creates the whole game and its features. 
 * @author Juan J. Nieves
 * @author Jonathan A. Nieves
 *
 */
public class SBComponent extends JFrame implements ActionListener, KeyListener
{

	/**
	 * This constructor, build the entire game and all its features.
	 * 
	 * 
	 * It holds the JmenuBar, the grid layout of the player, the discovery box and the button.
	 * @param gridSizeW the grid width size
	 * @param gridSizeH the grid height size
	 * @param playerName  the string of usernames of all the players
	 * @param ships the number of ships in the game
	 * @param markedMode  To know if wanted to play in Marked Mode
	 * @param players  the number of players playing
	 * @param rofongo To know if wanted to play with Rofongo
	 * @param level the level of the game
	 * 
	 * (Preconditions: gridSizeW = 10)
	 * (Preconditions: gridSizeH = 10 || gridSizeH == 20)
	 * (Preconditions: ships > 4 && ships < 9)
	 * (Preconditions: markedMode = true or false)
	 * (Preconditions: players > 0 && players < 3)
	 * (preconditions: rofongo = true or false)
	 * (Preconditions: level > 0 || level < 3)
	 */
	public SBComponent( int gridSizeW, int gridSizeH , String[] playerName,int ships, boolean markedMode, int players,boolean rofongo, int level) 
	{
		super();
		this.gridSizeW = gridSizeW;
		this.gridSizeH = gridSizeH;
		this.playerName = playerName;
		this.players = players;
		this.rofongo = rofongo;
		this.ships = ships;
		this.marked = markedMode;
		this.level = level;
		
		assert players > 0 && players < 3;
		assert gridSizeW == 10;
		assert gridSizeH == 10 || gridSizeH == 20;
		assert ships > 4 && ships < 9;
		assert level > 0 || level < 3;
		
		
		if(gridSizeH == 10)
		{
			buttonW = 43;
			buttonH = 40;
		}
		else if( gridSizeH == 20)
		{
			buttonW = 43;
			buttonH = 25;
			System.out.println("Entro");
		}
		if(players == 1)
		{
			buttonW = 50;
			buttonH = 50;
		}
		
		////////////////////////////////////////////
		
		Ships truth = new Ships(this.gridSizeW, this.gridSizeH,level,ships);
		truth.addBoat(ships);
		board = new boolean[this.gridSizeH][this.gridSizeW];
		board = truth.getGame();
		
		
		////////////////////////////////////////////

		bottomPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		topPanel = new JPanel();
		gridPanel = new JPanel();
		gridPanel2 = new JPanel();
		gridPanel3 = new JPanel();
		gridPanel4 = new JPanel();
		allPanels = new JPanel();
		playerPanel = new JPanel();
		allPanels.setLayout(new BorderLayout());



//-----------------------------------------------------------------------------------------------------------------
		//Create the discovery text field and button.

		discoveryBox = new JTextField(30);

		click = new JButton("Discover");
		click.addActionListener(this);

		bottomPanel.setLayout(new FlowLayout());
		//bottomPanel.add(discoveryBox);
		//bottomPanel.add(click);
		
		
//-----------------------------------------------------------------------------------------------------------------
		//Left panel for the numbers of the coordinates.
		coorArrayN = new JButton[this.gridSizeH +1];
		leftPanel.setLayout(new GridLayout(this.gridSizeH +1,1));


		for(int j=0; j<coorArrayN.length; j++)
		{

			coorArrayN[j] = new JButton();
			coorArrayN[j].setPreferredSize(new Dimension(buttonW,buttonH));
			coorArrayN[j].setBackground(sand);
			coorArrayN[j].setForeground(Color.BLACK);
			coorArrayN[j].removeActionListener(this);
			
			if( j> 0)
			{
				coorArrayN[j].setText(""+j);
				coorArrayN[j].setFont(new Font(coorArrayN[j].getText(), Font.PLAIN, 8));
				
			}

			leftPanel.setBorder(border2);
			leftPanel.add(coorArrayN[j]);
		}



//-----------------------------------------------------------------------------------------------------------------	
		//Center Layout for the grid of the ocean.
				
		if(players > 0 && players <3)
		{
			if(rofongo)
			{
				playerPanel.setLayout(new GridLayout(1,this.players +1));
			}
			else
			{
				playerPanel.setLayout(new GridLayout(1,this.players));
			}
		}
		else 
		{
			if(players == 3 && rofongo == false)
			{
				playerPanel.setLayout(new GridLayout(1,this.players));
			}
			else if( players == 3 && rofongo == true)
			{
				playerPanel.setLayout(new GridLayout(2,2));
			}
			else if( players == 4 && rofongo == false)
			{
				playerPanel.setLayout(new GridLayout(2,2));
			}
		}
		
		gridGen5 = new GridGenerator(gridSizeW,gridSizeH,buttonW,buttonH);
		grid = gridGen5.getButtonArrayfromPanel();
		
		gridPanel = gridGen5.generateGridPanel(this);
		gridPanel.setBorder(border);
		playerPanel.add(gridPanel);

		
		if(players> 1)
		{
			Ships truth2 = new Ships(this.gridSizeW, this.gridSizeH,level,ships);
			gridGen = new GridGenerator(gridSizeW,gridSizeH,buttonW,buttonH);
			gridPanel2 = gridGen.generateGridPanel(this);
			grid2 = gridGen.getButtonArrayfromPanel();
			
			gridPanel2.setBorder(border);
			playerPanel.add(gridPanel2);
			
			truth2.addBoat(ships);
			board2 = new boolean[gridSizeW][gridSizeH];
			board2 = truth2.getGame();
		}
		if ( players >2)
		{
			Ships truth3 = new Ships(this.gridSizeW, this.gridSizeH,level,ships);
			gridGen2 = new GridGenerator(gridSizeW,gridSizeH,buttonW,buttonH);
			grid3 = gridGen.getButtonArrayfromPanel();
			
			gridPanel3 = gridGen2.generateGridPanel(this);
			gridPanel3.setBorder(border);
			playerPanel.add(gridPanel3);
			
			truth3.addBoat(ships);
			board3 = new boolean[gridSizeW][gridSizeH];
			board3 = truth3.getGame();
		}
		
		
		
		

//------------------------------------------------------------------------------------------------------------------

		if(rofongo == true)
		{
			rofongoPanel();
			
			Ships truth4 = new Ships(this.gridSizeW, this.gridSizeH,level,ships);
			truth4.addBoat(ships);
			board3 = new boolean[gridSizeW][gridSizeH];
			board3 = truth4.getGame();
		}
		
		allPanels.add(topPanel, BorderLayout.NORTH);
		//allPanels.add(rightPanel, BorderLayout.EAST);
		allPanels.add(leftPanel, BorderLayout.WEST);
		allPanels.add(bottomPanel, BorderLayout.SOUTH);
		allPanels.add(playerPanel, BorderLayout.CENTER);
		add(allPanels);
		
		menuBar();//Add the menu bar to the frame.
		pack();	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Shuttle Beep");
		setVisible(true);

		
	}

	
	/**
	 * This creates if needed the entire panel for Rofongo to play
	 */
	public void rofongoPanel()
	{	
		rofongoPanel = new JPanel();
		gridGen3 = new GridGenerator(gridSizeW,gridSizeH,buttonW,buttonH);
		grid3 = gridGen3.getButtonArrayfromPanel();
		rofongoPanel = gridGen3.generateGridPanel(this);
		
		rofongoPanel.setBorder(border);
		playerPanel.add(rofongoPanel);
			
	}
	
	/**
	 * This method will create and add all the features hold in the JMenuBar
	 */
	public void menuBar()
	{
		menuBar = new JMenuBar();
		boolean levelA = false;
		boolean levelB = false;
		
		if(level == 1)
		{
			levelA = true;
			levelB = false;
		}
		if(level == 2)
		{
			levelB = true;
			levelA = true;
		}
		
		//Creates the menu and item add it to the bar
		game = new JMenu("Game");
		game.setMnemonic(KeyEvent.VK_A);
		menuBar.add(game);
		
		
		//Creates the first content that will be inside the menu bar
		newGame = new JMenuItem("New Game");
		game.add(newGame);
		game.addSeparator();
		game.addSeparator();
		newGame.addActionListener(this);
		
		
		//Creates the second content that will be inside the menu bar 
		level1 = new JCheckBoxMenuItem("Level 1",levelA);
		game.add(level1);
		level1.addActionListener(this);
		
		//Creates the third content that will be inside the menu bar
		level2 = new JCheckBoxMenuItem("Level 2",levelB);
		game.add(level2);
		game.addSeparator();
		game.addSeparator();
		level2.addActionListener(this);
		
		//Creates the marked mode checkbox into the game menu
		markedMode = new JMenuItem("Marked Mode");
		game.add(markedMode);
		markedMode.addActionListener(this);
		
		
		//Creates the fourth content that will be inside the menu bar
		newGridSize = new JMenuItem("Custom Grid Size..");
		game.add(newGridSize);
		newGridSize.addActionListener(this);
		
		//Creates the fifth content that will be inside the menu bar
		shuttleNum = new JMenuItem("Number of Shuttles...");
		game.add(shuttleNum);
		shuttleNum.addActionListener(this);
		
		//Creates the sixth content that will be inside the menu bar
		randShips = new JCheckBoxMenuItem("Place Boats Randomly",true);
		game.add(randShips);
		game.addSeparator();
		game.addSeparator();
		randShips.addActionListener(this);
		
		//Creates the seventh content that will be inside the menu bar
		highScore = new JMenuItem("High Scores");
		game.add(highScore);
		highScore.addActionListener(this);
		
		//Creates the last content that will be inside the menu bar
		exit = new JMenuItem("Exit");
		game.add(exit);
		exit.addActionListener(this);
		
		//Creates the menu for the name of us and item add it to the bar
		creators = new JMenu("Creators");
		creators.setMnemonic(KeyEvent.VK_B);
		menuBar.add(creators);
	
		juan = new JMenuItem("Juan J. Nieves Miranda");
		creators.add(juan);
		johnny = new JMenuItem("Jonathan A. Butler Nieves");
		creators.add(johnny);
		
		
		setJMenuBar(menuBar);
	} 
	
	/**
	 * This method will turn the letter from the discovery box to a number
	 * This is usefull to know what button is pressing and tho know his coordinates
	 * @param letter the letter string to be converted
	 * @return number this is the number once is converted
	 * 
	 * (Precondition: letter has to be from A to J)
	 * (Postcondition: number >= 0 && number < 10 )
	 */
	public int convertLetters(String letter)
	{
		int number=0;
		if(letter.equalsIgnoreCase("A"))
		{
			number = 0;
		}
		else if(letter.equalsIgnoreCase("B"))
		{
			number = 1;
		}
		else if(letter.equalsIgnoreCase("C"))
		{
			number = 2;
		}
		else if(letter.equalsIgnoreCase("D"))
		{
			number = 3;
		}
		else if(letter.equalsIgnoreCase("E"))
		{
			number = 4;
		}
		else if(letter.equalsIgnoreCase("F"))
		{
			number = 5;
		}
		else if(letter.equalsIgnoreCase("G"))
		{
			number = 6;
		}
		else if(letter.equalsIgnoreCase("H"))
		{
			number = 7;
		}
		else if(letter.equalsIgnoreCase("I"))
		{
			number = 8;
		}
		else if(letter.equalsIgnoreCase("J"))
		{
			number = 9;
		}
		
		return number;
	}
	
	/**
	 * This method check if  the player have take out all the sunked shuttles
	 * @param hits the hits of player
	 * @param ships the ships in the board game
	 * @return true or false
	 */
	public boolean win(int hits, int ships)
	{
		int total = 0;
		for(int h = 0; h < ships; h++)
		{
			total = total + h + 2;
		}
		if( hits == total)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This method will write in the text the best high score
	 * @param username
	 * @param hits
	 * @param miss
	 */
	public void HighScores(String username, int hits, int miss)
	{
		this.username = username;
		highScores = (hits/miss) *1000;
		try{
			// Create file 
			FileWriter fstream = new FileWriter("scores.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write(username + "\t" + highScore);
			
			//Close the output stream
			out.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	/**
	 * Return the name of the high scorer
	 * @return
	 */
	public String getHighScoreName()
	{
		return username;
	}
	
	/**
	 * Return the value of the high scorer
	 * @return
	 */
	public double getHighScoreValue()
	{
		return highScores;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		Object source = e.getSource();
		
				for( int p= gridSizeW; p< grid.length; p++)
				{
					
					
					if( source == grid[p])
					{
						if(players != 1)
						{
							gridPanel.setBorder(player);
						}
						
						String name = grid[p].getName().toString();
						int column = Integer.parseInt(name.substring(1,2));
						int row = Integer.parseInt(name.substring(0,1));

						if(board[row][column] == true )
						{
							grid[p].setBorder(hitBorder);
							grid[p].setIcon(new ImageIcon(getClass().getResource("joder.jpg")));
							grid[p].removeActionListener(this);
							hits++;

							if(win(hits, ships))
							{
								disable();
								JOptionPane.showMessageDialog(null, playerName[0]+" has won the game!","Shuttle Beep" ,JOptionPane.PLAIN_MESSAGE , new ImageIcon(getClass().getResource("titanic.jpg")));
								HighScores(playerName[0],hits, miss);
//								System.out.println(hits);
								System.exit(0);
							}

						}
						else if(board[row][column] == false && marked == false)
						{
							miss++;
							gridPanel.setBorder(border);
						}
						else if(board[row][column] == false && marked == true)
						{
							grid[p].setIcon(new ImageIcon(getClass().getResource("clear.jpg")));
							grid[p].removeActionListener(this);
							gridPanel.setBorder(border);
							miss++;
						}

					}
				}
			
			
			if(players> 1)
			{
				for( int p= gridSizeW; p< grid2.length; p++)
				{
					
					
					if( source == grid2[p])
					{
						gridPanel2.setBorder(player);
						
						String name = grid2[p].getName().toString();
						int column = Integer.parseInt(name.substring(1,2));
						int row = Integer.parseInt(name.substring(0,1));

						if(board2[row][column])
						{
							grid2[p].setBorder(hitBorder);
							grid2[p].setIcon(new ImageIcon(getClass().getResource("joder.jpg")));
							grid2[p].removeActionListener(this);
							hits2++;

							if(win(hits2, ships))
							{
								disable();
								JOptionPane.showMessageDialog(null, "Plawer 2 has won the game!","Shuttle Beep" ,JOptionPane.PLAIN_MESSAGE , new ImageIcon(getClass().getResource("titanic.jpg")));
								HighScores(playerName[1],hits2, miss2);
								System.exit(0);
							}

						}
						else if(board2[row][column] == false && marked == false)
						{
							miss2++;
							gridPanel2.setBorder(border);
						}
						else if(board2[row][column] == false && marked == true)
						{
							grid2[p].setIcon(new ImageIcon(getClass().getResource("clear.jpg")));
							grid2[p].removeActionListener(this);
							gridPanel2.setBorder(border);
							miss2++;
						}

						miss2++;
					}
				}
			}

			if(players> 2 && rofongo == false)
			{
				for( int p= gridSizeW; p< grid3.length; p++)
				{
					
					
					if( source == grid3[p])
					{
						gridPanel3.setBorder(player);
						
						String name = grid3[p].getName().toString();
						int column = Integer.parseInt(name.substring(1,2));
						int row = Integer.parseInt(name.substring(0,1));

						if(board3[row][column])
						{
							grid3[p].setBorder(hitBorder);
							grid3[p].setIcon(new ImageIcon(getClass().getResource("joder.jpg")));
							grid3[p].removeActionListener(this);
							hits3++;

							if(win(hits3, ships))
							{
								disable();
								JOptionPane.showMessageDialog(null,"Player 3 has won the game!","Shuttle Beep" ,JOptionPane.PLAIN_MESSAGE , new ImageIcon(getClass().getResource("titanic.jpg")));
								HighScores(playerName[2],hits3, miss3);
								System.exit(0);
							}
						}
						else if(board3[row][column] == false && marked == false)
						{
							miss3++;
							gridPanel3.setBorder(border);
						}
						else if(board3[row][column] == false && marked == true)
						{
							grid3[p].setIcon(new ImageIcon(getClass().getResource("clear.jpg")));
							grid3[p].removeActionListener(this);
							gridPanel3.setBorder(border);
							miss3++;
						}

						miss3++;
					}
				}
			}
			if(rofongo == true)
			{
				for( int p= gridSizeW; p< grid3.length; p++)
				{
					rofongoPanel.setBorder(player);
					if( source == grid3[p])
					{
						rofongoPanel.setBorder(player);
						
						RofongoTurn AI = new RofongoTurn(board3,gridSizeW,gridSizeH);

						if(AI.getTry())
						{
							grid3[p].setBorder(hitBorder);
							grid3[p].setIcon(new ImageIcon(getClass().getResource("joder.jpg")));
							grid3[p].removeActionListener(this);
							
							hits3++;

							if(win(hits3, ships))
							{
								disable();
								JOptionPane.showMessageDialog(null,"Rofongo has won the game!","Shuttle Beep" ,JOptionPane.PLAIN_MESSAGE , new ImageIcon(getClass().getResource("titanic.jpg")));
								HighScores("Rofongo",hits3, miss3);
								System.exit(0);
							}
						}

						miss3++;
						gridPanel2.setBorder(border);
					}
				}
			}




			//----------------------------------------------------------------------------------------------------

			if( source == click)
			{
				coordinates = discoveryBox.getText();//Read from the discovery box
				int column = convertLetters(coordinates.substring(0,1));
				int row = Integer.parseInt(coordinates.substring(1,2)) -1;


				if(board[row][column])
				{
					grid[row*10 + column + 10].setBackground(Color.RED);
					
				}
				else if(board2[row][column])
				{
					grid2[row*10 + column + 10].setBackground(Color.RED);
				}
				else if(board3[row][column])
				{
					grid3[row*10 + column + 10].setBackground(Color.RED);
				}
			}

			///////-------------------------------------------------------------------------------------------------------------------------------

			else if( source == newGame)
			{
				ShuttleBeepMain main = new ShuttleBeepMain();
				main.mainMenu();
				dispose();
			}
			else if( source == level1)
			{
				if(level1.isSelected() == false && level == 2)
				{
					dispose();
					SBComponent component = new SBComponent(gridSizeW, gridSizeH,playerName, ships, marked, players, rofongo, 1);
				}

			}
			else if( source == level2)
			{
				if(level2.isSelected() == false && level == 1 && level1.isSelected() == true)
				{
					dispose();
					SBComponent component = new SBComponent(gridSizeW, gridSizeH,playerName, ships, marked, players, rofongo, 2);
				}

			}
			else if( source == markedMode)
			{
				if( level == 1)
				{
					dispose();
					SBComponent component = new SBComponent(gridSizeW, gridSizeH,playerName, ships, true, players, rofongo, 1);
				}
				else if( level == 2)
				{
					dispose();
					SBComponent component = new SBComponent(gridSizeW, gridSizeH,playerName, ships, true, players, rofongo, 2);
				}
			}
			else if( source == newGridSize)
			{
				boolean finish = true;
				while(finish)
				{
					String newSize = JOptionPane.showInputDialog("Enter new size (10x10 or 10x20):");
					if(newSize == null)
					{

					}
					if( newSize.equals("10x20"))
					{
						dispose();
						SBComponent component = new SBComponent(10, 20, playerName,ships, marked, players, rofongo, level);
						finish = false;
					}
					else if (newSize.equals("10x10") )
					{
						dispose();
						SBComponent component = new SBComponent(10, 10,playerName, ships, marked, players, rofongo, level);
						finish = false;
					}
				}
			} 
			else if( source == shuttleNum)
			{
				boolean finish = true;
				while(finish)
				{
					String shuttleSize = JOptionPane.showInputDialog("Enter number of shuttles (5-8):");
					if(shuttleSize == null)
					{

					}
					if( shuttleSize.equals("5") || shuttleSize.equals("6") || shuttleSize.equals("7") || shuttleSize.equals("8") )
					{
						dispose();
						int shuttles = Integer.parseInt(shuttleSize);
						SBComponent component = new SBComponent(gridSizeW, gridSizeH,playerName, shuttles, marked, players, rofongo, level);
						finish = false;
					}

				}
			} 
			else if( source == highScore)
			{
				JOptionPane.showMessageDialog(null, "The Best Highscore: "+ getHighScoreName()+ " "+ getHighScoreValue());
			}
			else if( source == exit)
			{
				System.exit(0);
			}

	}
	
	/**
	 * This is method to exit the game with Alt + F4
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
	
		if(e.getKeyCode() == (KeyEvent.VK_ALT + KeyEvent.VK_F4))
		{
			System.exit(0);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	//JMenuBar Instance Fields
	private JMenuBar menuBar;
	private JMenu game;
	private JMenu creators;
	private JMenuItem newGame;
	private JMenuItem newGridSize;
	private JMenuItem shuttleNum;
	private JMenuItem highScore;
	private JMenuItem exit;
	private JMenuItem juan;
	private JMenuItem johnny;
	private JCheckBoxMenuItem level1;
	private JCheckBoxMenuItem level2;
	private JCheckBoxMenuItem randShips;
	private JMenuItem markedMode;
	
	

	//JPanels for the entire game
	private JPanel bottomPanel;
	private JPanel leftPanel;
	private JPanel topPanel;
	private JPanel rightPanel;
	private JPanel gridPanel;
	private JPanel gridPanel2;
	private JPanel gridPanel3;
	private JPanel gridPanel4;
	private JPanel allPanels;
	private JPanel playerPanel;
	private JPanel rofongoPanel;
	

	//Instance Fields
	private int buttonW ;
	private int buttonH ;
	private double highScores;
	private String username;
	private int hits = 0, hits2 = 0, hits3 = 0;
	private int miss = 0, miss2 = 0, miss3 = 0;
	private boolean done = true;
	private int gridSizeW;
	private int gridSizeH;
	private String[] playerName;
	private int players;
	private String coordinates;
	private boolean rofongo;
	private GridGenerator gridGen, gridGen2, gridGen3, gridGen4, gridGen5;
	private boolean marked;
	private int ships;
	private boolean[][] board, board2,board3,board4;
	private int level, playerTurns = 0;
	private JTextField discoveryBox;
	private JButton click;
	private String Letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private JButton[] coorArrayN, grid, grid2, grid3;
	Color sand = new Color(238,221,130);
	Border border = new LineBorder(Color.BLACK,3);
	Border border2 = new LineBorder(Color.GRAY,3);
	Border hitBorder = new LineBorder(Color.RED,2);
	Border player = new LineBorder(Color.YELLOW,3);

	private static final long serialVersionUID = 1L;

}
