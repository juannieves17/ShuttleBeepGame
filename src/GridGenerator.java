import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;




/**
 * This class will construct the panel for each player need. Inside every panel it has the
 * JButtons, with his picture, action listener, font, grid layout and more.
 * @author Juan J. Nieves 
 * @author Jonathan A. Nieves
 */
public class GridGenerator
{
	
	/**
	 * This will construct the grid of JButtons
	 * @param sizeW  the grid width size
	 * @param sizeH  the grid height size
	 * @param buttonW  the button width size
	 * @param buttonH  the button height size
	 * (Precondition: sizeW > 0)
	 * (Precondition: sizeH > 0)
	 * (Precondition: buttonW > 0)
	 * (Precondition: buttonH > 0)
	 */
	public GridGenerator(int sizeW,int sizeH, int buttonW, int buttonH)
	{
		this.gridSize = sizeW;
		this.gridSize2 = sizeH;
		this.buttonW = buttonW;
		this.buttonH = buttonH;
		grid2 = new JButton[gridSize*gridSize2 + gridSize];
		gridPane = new JPanel();
	}
	
	/**
	 * This will construct the fully JPanel with the grid layout and all the action listener
	 * ready to put in a frame.
	 * @param aL the action listener for all the buttons
	 * @return gridPane the Jpanel with everything in it.
	 * 
	 */
	public JPanel generateGridPanel(ActionListener aL)
	{
		this.aL = aL;
		gridPane.setLayout(new GridLayout(gridSize2 +1 ,gridSize));
		
		int p=0;
		int k=0;
		for(int i= 0; i < grid2.length; i++)
		{
			grid2[i] = new JButton();
			
			if( i < gridSize)
			{
				grid2[i].setBackground(sand);
				grid2[i].setText(Letters.substring(i,i+1));
				grid2[i].removeActionListener(this.aL);
				grid2[i].setFont(new Font(grid2[i].getText(), Font.PLAIN, 10));
			}
			else
			{
				
				if(k < gridSize)
				{
					grid2[i].setText(p+""+k);
					grid2[i].setName(p+""+k);
					k++;
				}
				if(k == gridSize)
				{
					k=0;
					p++;
				}
				grid2[i].addActionListener(this.aL);
				grid2[i].setIcon(new ImageIcon(getClass().getResource("ocean.jpg")));
			}
			grid2[i].setPreferredSize(new Dimension(buttonW,buttonH));
			gridPane.add(grid2[i]);
		}
		return gridPane;
	}
	public JButton[] getButtonArrayfromPanel()
	{
		return grid2;
	}
	
	//Intance Variables
	private String Letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private JButton[]  grid2;
	private int gridSize;
	private int gridSize2;
	private int buttonW;
	private int buttonH;
	private JPanel gridPane;
	Color sand = new Color(238,221,130);
	private  ActionListener aL;

}
