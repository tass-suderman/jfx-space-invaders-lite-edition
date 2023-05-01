import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;

public class PlayerShip extends Sprite
{
	
	
	public int reloadTime = 50;
	private int recovery = 0; 
	private int recovery2 = 0;
	
	private int speed; 
	
	private int score = 0;
	
	
	public PlayerShip()
	{
		super(LAUNCHER, 150, 540);
		this.render(Main.gc);
		this.speed=3;
	}

	public void controller(KeyEvent e)
	{
		switch (e.getCode())
		{
			case LEFT:
				this.flyLeft();
				break;
			case RIGHT:
				this.flyRight();
				break;
			case UP:
				if (recovery > 0)
				{
					break;
				}
				this.launchMissile();
				break;			
			case DOWN:
				if (recovery2 > 0)
				{
					break;
				}
				this.launchWeapon2();
				break;
			case ESCAPE:

					Platform.runLater(()->Main.playerKill());	
			   break;
			default:
				break;
		}
	}

	private void flyLeft()
	{
		this.clear(Main.gc);
		this.getCoors().decX(this.getCoors().getX()<-25 ? 0 : this.speed);
		this.render(Main.gc);
	}


	private void flyRight()
	{
		this.clear(Main.gc);
		this.getCoors().incX(this.getCoors().getX()>Main.CANVASWIDTH-40 ? 0 : this.speed);
		this.render(Main.gc);
	}
	

	private void launchMissile()
	{
		Platform.runLater(() -> { 
			new Shell(this);
			recovery = reloadTime;
		}); 
		
	}
	
	private void launchWeapon2()
	{
		Platform.runLater(() -> { 
			new Shell(this, 18);
			recovery2 = reloadTime*3;
		}); 
	}
	

	public void reloading()
	{

		if (recovery <= 0 && recovery2 <= 0)
		{
			return;
		}
		recovery -= recovery > 0 ? 1 : 0;
		recovery2 -= recovery2 > 0 ? 1: 0;
		
	}
	
	}