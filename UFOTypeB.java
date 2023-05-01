
import java.util.ArrayList;

import javafx.scene.image.Image;

public class UFOTypeB extends UFOTypeA
{

	public UFOTypeB(double nXPos, double nYPos, ArrayList<Image> lstSkin)
	{
		super(nXPos, nYPos, lstSkin);
		this.setImage(Sprite.UFOIMAGES.get(0));
		this.render(Main.gc);
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
		if(this.getCoors().getX() <= 0)
		{
			this.getCoors().incX(4);
		}
		if(this.getCoors().getX() > Main.CANVASWIDTH)
		{
			this.getCoors().decX(4);
		}
		else
		{
			this.getCoors().incX((int)Math.round(Math.random()*4-2));
		}
		
	}
	
	
}
