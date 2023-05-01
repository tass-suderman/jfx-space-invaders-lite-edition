import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class UFOTypeA extends Sprite
{


	public static Thread gravityThread;
	
	public boolean isDropping;
	
	private ArrayList<Image> ufoSkin;
	
	
	public static final double MURDER_THRESHHOLD = Main.obCanvas.getHeight()-75.0;
	
	public static int turnDelay = 90; 
	
	
	public UFOTypeA(double nXPos, double nYPos, ArrayList<Image> lstSkin)
	{
		super(lstSkin.get(0), nXPos, nYPos);
		this.ufoSkin = lstSkin;
		isDropping=true;
		this.render(Main.gc);
		this.beginDescend();
	}



	public void beginDescend()
	{
		gravityThread = new Thread(() -> this.runTask());
		gravityThread.setDaemon(true);
		gravityThread.start();
	}
		

	protected void runTask()
	{
		try
		{
			int nPos = 0;
			while (true && this.isDropping)
			{
				this.setImage(this.ufoSkin.get(nPos % this.ufoSkin.size()));
				nPos++;
				Platform.runLater(() -> this.gravity());
				Platform.runLater(()-> this.checkBottom());
				Thread.sleep(turnDelay);
			}

		}

		catch (InterruptedException exp)
		{
			exp.printStackTrace();
		}
	}
	

	private void checkBottom()
	{
		if  (this.getCoors().getY() > MURDER_THRESHHOLD) 
		{
			Platform.runLater(()->Main.playerKill());
		}
	}

	protected void gravity()
	{
		this.clear(Main.gc);
		this.getCoors().incY(1);
		this.render(Main.gc);
	}
	
	

	public void ufoDeath(Shell obShell)
	{
		Platform.runLater(()->{

			obShell.clear(Main.gc);
			this.clear(Main.gc);
			this.isDropping = false;
		
		});
		Thread obThread = new Thread(() -> this.coolExplosion());
		obThread.setDaemon(true);
		obThread.start();
		Main.lstShells.remove(obShell);
		Main.ufoList.remove(this);
		if(Main.ufoList.size()<1)
		{
			Main.OBLEVEL.levelUp();
		}
	}
	


	private void coolExplosion()
	{
		for (int i = 1; i <= 9; i++)
		{
			this.setImage(new Image("file:images/bang" + i + ".png"));
			Platform.runLater(() -> this.gravity());
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException exp)
			{

			}
		}

		Platform.runLater(() ->
		{
			this.clear(Main.gc);
		});

	}

}
