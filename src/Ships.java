import java.util.Random;




/**
 * This class is in charge of creating a boolean bi-dimentional array that will be use for every panel of every player
 * Also this si the class that will assignd the boats to their places, will control the level 1 and level 2.
 * @author juanito
 *
 */
public class Ships 
{


	/**
	 * This constructor receive the level and the number of ships and creates the boolean[][] array and filli it with the ships
	 * @param width  the grid width size
	 * @param height the grid height size
	 * @param nivel  the level of the game
	 * @param botes  the number of ships
	 * 
	 * (Precondition: width = 10)
	 * (Precondition: heigth = 10 || height = 20)
	 * (Precondition: nivel =1 || nivel =2 )
	 * (Precondition: botes > 4 && botes < 9)
	 */
	public Ships(int width, int height, int nivel, int botes)
	{
		filler = new boolean[width][height];
		gameW=width;
		gameH=height;
		level = nivel;
		ships = botes;
		setBoats(ships);
	}

	/**
	 * This method will set the number of ships indicated
	 * From 5 to 8 shuttles only
	 * @param boats the number of shuttles
	 * 
	 * (Precondition: boats > 4 && boats < 9)
	 */
	public void setBoats(int boats)
	{
		for (int i = 0; i < boats ; i++)
		{
			addBoat(i+2);
		}
	}

	/**
	 * Method will add the boat to the boolean[][] array with an specific length
	 * @param length the number of cell of the boat
	 * 
	 * (Precondition: length > 2 && length < 10)
	 */
	public void addBoat(int length)
	{
		Random dice = new Random();
		int randomNumber =dice.nextInt(level+1)+1;
		
		int xCoord = dice.nextInt(gameW);
		int yCoord = dice.nextInt(gameH);
		
		boolean [] boat = new boolean [length];
		
		for(int i = 0; i<length;i++)
		{
			boat[i]=true;
		}
		
		
		if(randomNumber==1)
		{
			do{
				while((yCoord + length)>=gameH){
					yCoord = dice.nextInt(gameH);
				}

				porno = true;
				boolean checker[]=new boolean[length];

				for(int i =0;i<length;i++)
				{
					checker[i]=filler[xCoord][yCoord+i];
				}
				for(int ii=0;ii<length;ii++)
				{
					if(checker[ii]==boat[ii])
					{
						porno= false;
					}				
				}
				if(porno)
				{
					for(int i =0;i<length;i++)
					{
						filler[xCoord][yCoord+i]= boat[i];
					}
				}
			}while(porno ==true);
		}
		if(randomNumber==2)
		{
			do{
				while((xCoord + length)>=gameW)
				{
					xCoord = dice.nextInt(gameW);
				}

				porno = true;
				boolean checker[]=new boolean[length];

				for(int i =0;i<length;i++)
				{
					checker[i]=filler[xCoord+1][yCoord];
				}
				for(int ii=0;ii<length;ii++)
				{
					if(checker[ii]==boat[ii])
					{
						porno= false;
					}				
				}
				for(int i =0;i<length;i++)
				{
					filler[xCoord+i][yCoord]= boat[i];
				}
			}while(porno==true);
		}
		if(randomNumber==3)
		{
			while((xCoord + length)>= gameW||(yCoord + length)> gameH)
			{
				xCoord = dice.nextInt(gameW);
				yCoord = dice.nextInt(gameH);
			}
			
			for(int i =0;i<length;i++)
			{
				filler[xCoord+i][yCoord+i]= boat[i];
			}
		}
	}


	/**
	 * This method return the bi-dimentional array with the ships inside.
	 * @return filler the bi-dimentional array with the shuttles
	 * 
	 */
	public boolean[][] getGame()
	{
		return filler;
	}

	private boolean[][] filler;
	private int level;
	private int ships;
	private boolean porno;

	private int gameW;
	private int gameH;



}
