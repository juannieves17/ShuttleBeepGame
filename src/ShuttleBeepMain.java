
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * This is a game called Shuttle Beep and consist almost like Battleship.But you are trying to find your
 *	sunken shuttles in the ocean instead of trying to sink your opponent’s boats.
 * @author Juan J. Nieves 
 * @author Jonathan A. Nieves
 */
public class ShuttleBeepMain
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{

		mainMenu();
	}

	/**
	 * This method will create the main menu for the game.
	 * Mostly the frame and the panels added to the frame are done in here.
	 */
	public static void mainMenu()
	{
		frame = new JFrame();
		frame.setSize(174,341);
		//frame.setResizable(false);
		frame.setTitle("Shuttle Beep");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400,200);

		panel = new JPanel();

		buttons();

		panel.add(playerS);
		panel.add(numS);
		panel.add(click);
		panel.add(gridSize10);
		panel.add(gridSize20);
		panel.add(rofongoText);
		panel.add(rofongoY);
		panel.add(rofongoN);
		panel.add(shipsText);
		panel.add(ships5);
		panel.add(ships6);
		panel.add(ships7);
		panel.add(ships8);
		panel.add(markedMode);
		

		frame.add(panel);
		frame.setVisible(true);
		

	}

	/**
	 * This method will add the buttons to the main menu frame.
	 * Also add all the component in the menu.
	 */
	public static void buttons()
	{
		playerS = new JLabel();
		playerS.setText("Shuttle Beep Menu");
		numS = new JLabel();
		players = "1";
		
		click = new JButton("Enter");
		aL = new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == click)
				{
					if(players.equals("1")  )
					{
						
						int playerNum = Integer.parseInt(players);
						
						
						usernames = new String[playerNum];
						
						for(int p=0; p < playerNum ; p++)
						{
							usernames[p] = JOptionPane.showInputDialog("Enter player"+(p+1)+" username:");
							if(usernames[p] == null)
							{
								frame.dispose();
								mainMenu();
							}
						}
						
						boolean rofongo = false;
						boolean marked = false;
						
						if(group2.getSelection() == rofongoY.getModel())
						{
							rofongo = true;
						}
						
						if(playerNum == 3 && group2.getSelection() == rofongoY.getModel())
						{
							rofongo = false;
						}
						
						if(group.getSelection() == gridSize20.getModel())
						{
							gridSize2+=10;
						}
						
						if(group3.getSelection() == ships5.getModel())
						{
							shipsNum = 5;
						}
						if(group3.getSelection() == ships6.getModel())
						{
							shipsNum = 6;
						}
						 if(group3.getSelection() == ships7.getModel())
						{
							shipsNum = 7;
						}
						if(group3.getSelection() == ships8.getModel())
						{
							shipsNum = 8;
						}
						if(markedMode.isSelected())
						{
							marked = true;
						}
						
							SBComponent component = new SBComponent(gridSize, gridSize2,usernames, shipsNum, marked, 1, rofongo, 1);
							frame.dispose();
		
					}
					else
					{
						frame.dispose();
						mainMenu();
						
					}
					
				}
			}
		};
		click.addActionListener(aL);

		//Grid size buttons
		gridSize10 = new JRadioButton("10x10 Grid Size",true);

		gridSize20 = new JRadioButton("10x20 Grid Size");

		rofongoText = new JLabel("Play with Rofongo?");
		
		rofongoY = new JRadioButton("Yes");

		rofongoN = new JRadioButton("No",true);
		
		shipsText = new JLabel("How many ships?");
		
		ships5 = new JRadioButton("5",true);
		ships6 = new JRadioButton("6");
		ships7 = new JRadioButton("7");
		ships8 = new JRadioButton("8");
		
		markedMode = new JRadioButton("Marked Mode",true);
		
		

		//Adding first group of radio buttons
		group = new ButtonGroup();
		group.add(gridSize10);
		group.add(gridSize20);

		//Adding the second group of radio buttons
		group2 = new ButtonGroup();
		group2.add(rofongoY);
		group2.add(rofongoN);
		
		//Adding the third group of radio buttons
		group3 = new ButtonGroup();
		group3.add(ships5);
		group3.add(ships6);
		group3.add(ships7);
		group3.add(ships8);
	}

	private static String[] usernames;

	
	//Instance Fields
	private static String players;
	private static int shipsNum =0;
	private static JButton click;
	private static ActionListener aL;
	private static JPanel panel;
	private static JFrame frame;
	private static JLabel playerS;
	private static JLabel rofongoText;
	private static JLabel shipsText;
	private static JLabel numS;

	//Menu buttons
	private static ButtonGroup group;
	private static ButtonGroup group2;
	private static ButtonGroup group3;
	private static JRadioButton rofongoY;
	private static JRadioButton rofongoN;
	private static JRadioButton gridSize10;
	private static JRadioButton gridSize20;
	private static JRadioButton ships5;
	private static JRadioButton ships6;
	private static JRadioButton ships7;
	private static JRadioButton ships8;
	private static JRadioButton markedMode;

	private static int gridSize = 10;
	private static int gridSize2 = 10;
}
