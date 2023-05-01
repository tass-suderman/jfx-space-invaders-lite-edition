import java.util.ArrayList;

import javafx.scene.image.Image;

public class UFOTypeC extends UFOTypeA
{
	
	private int direction;
	private int timeToChange;

	public UFOTypeC(double nXPos, double nYPos, ArrayList<Image> lstSkin)
	{
		super(nXPos, nYPos, lstSkin);
		this.setImage(UFOIMAGES.get(0));
		this.render(Main.gc);
		this.timeToChange=20;
		this.direction = -1;
	}

	
	@Override
	protected void gravity()
	{
		this.clear(Main.gc);
		this.getCoors().incY(2);
		this.sideToSide();
		this.render(Main.gc);
	}

	
	private void sideToSide()
	{
		if(this.timeToChange<=0)
		{
			direction *= -1;
			this.timeToChange=20;
		}

		this.timeToChange--;
		this.getCoors().incX(direction);
	}
	
}
