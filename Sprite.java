import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class Sprite
{
	
	protected final static Image LAUNCHER = new Image("file:images/Launch.png");
	

	public static final ArrayList<Image> UFOIMAGES= new ArrayList<>(Arrays.asList(new Image("file:images/ufo_1.png"),new Image("file:images/ufo_2.png"),new Image("file:images/ufo_3.png"),new Image("file:images/ufo_4.png"),new Image("file:images/ufo_5.png"),new Image("file:images/ufo_0.png")));

	
	public static final ArrayList<Image> EXPLOSIONIMAGES = new ArrayList<>(Arrays.asList(new Image("file:images/bang1.png"),new Image("file:images/bang2.png"),new Image("file:images/bang3.png"),new Image("file:images/bang4.png"),new Image("file:images/bang5.png"),new Image("file:images/bang6.png"),new Image("file:images/bang7.png"),new Image("file:images/bang8.png"),new Image("file:images/bang9.png"),new Image("file:images/bang10.png"),new Image("file:images/bang11.png"),new Image("file:images/bang12.png")));
  
 
	
	public static final Image IMGMISSILE = new Image("file:images/shell.png");
	public static final Image IMGMISSILE2 = new Image("file:images/shell2.png");
	
	
	
	private Image image;
	private Coors obCoor;

	private double width;
	private double height;

	public Sprite(Image image, double nXPos, double nYPos)
	{
		setImage(image);
		obCoor = new Coors(nXPos, nYPos);

	}

	public Image getImage()
	{
		return this.image;
	}

	public void setImage(Image image)
	{
		this.image = image;
		width = image.getWidth();
		height = image.getHeight();

	}

	public double getWidth()
	{
		return this.width;
	}

	public double getHeight()
	{
		return this.height;
	}

	public void setPosition(double x, double y)
	{
		this.obCoor.setX(x);
		this.obCoor.setY(y);

	}

	public Coors getCoors()
	{
		return this.obCoor;

	}

	public void moveLeft(GraphicsContext gc)
	{

	}

	public void clear(GraphicsContext gc)
	{
		gc.clearRect(obCoor.getX(), obCoor.getY(), image.getWidth(), image.getHeight());

	}

	public void render(GraphicsContext gc)
	{
		gc.drawImage(image, obCoor.getX(), obCoor.getY());

	}

	public Rectangle2D getBoundary()
	{
		return new Rectangle2D(obCoor.getX(), obCoor.getY(), width, height);
	}

	public boolean intersects(Sprite spr)
	{
		return spr.getBoundary().intersects(this.getBoundary());
	}
}
