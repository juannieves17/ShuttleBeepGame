



import java.util.Random;

/**
 * This class play the robot Rofongo.
 * @author Juan J. Nieves
 * @author Jonathan A. Nieves
 *
 */
public class RofongoTurn 
{
	private boolean hitMiss=false;
	private int xCoord;
	private int yCoord;
	private boolean[][] rofongosGame;

	/**
	 * El main simula lo que el component va enviar
	 * @param width width of the array
	 * @param height heigth of the array
	 * @param Game the array of Rofongo
	 */
	public RofongoTurn(boolean[][] game, int width, int height)
	{
		Random random = new Random();
		rofongosGame=game;
		if(hitMiss==false)
		{
			xCoord = random.nextInt(width);
			yCoord = random.nextInt(height);
			hitMiss = game[xCoord][yCoord];
		}
		else if (hitMiss ==true){
			hitMiss=nextTry();
		}
	}


	/**
	 * This method will return if rofongo miss or if hit the boats.
	 * @return
	 */
	public boolean getTry()
	{
		return hitMiss;
	}

	/**
	 * This method will continue looking for ships by Rofongo
	 * @return
	 */
	public boolean nextTry()
	{
		Random random = new Random();
		int select = random.nextInt(3);
		if(select==0)
		{
			xCoord++;
			hitMiss = rofongosGame[xCoord][yCoord];
		}
		else if(select==1)
		{
			yCoord++;
			hitMiss = rofongosGame[xCoord][yCoord];
		}
		else if(select==2)
		{
			xCoord++;
			yCoord++;
			hitMiss = rofongosGame[xCoord][yCoord];
		}
		return hitMiss;
	}


}