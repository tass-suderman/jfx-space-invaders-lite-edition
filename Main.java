import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
public class Main extends Application
{
	protected static final double CANVASWIDTH=400, CANVASHEIGHT=600;

	protected static Canvas obCanvas = new Canvas(CANVASWIDTH, CANVASHEIGHT);
	protected static GraphicsContext gc = obCanvas.getGraphicsContext2D();
	
	protected static ArrayList<Shell> lstShells = new ArrayList<>();
	protected static ArrayList<UFOTypeA> ufoList = new ArrayList<>();
	
	protected static final Level OBLEVEL = new Level();
	
	public static int turnDelay = 85;
	private static Line obLine;
	private static Thread obGThread;
	
	public static PlayerShip obLauncher = new PlayerShip();
	private static Stage obStage = new Stage();
	
	@Override
	public void start(Stage gameStage) throws Exception
	{
		obStage=gameStage;
    Platform.runLater(()->startGame());
    obStage.setTitle("Space Invaders Demo");
		obStage.show();
		

	}
	
	

	private static void startGame()
	{
		Pane obPane = new Pane(obCanvas);

		OBLEVEL.levelUp();
		startTask();


		obPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#000000"), new CornerRadii(0), new Insets(0))));
		obLine = new Line(0, CANVASHEIGHT-50, CANVASWIDTH, CANVASHEIGHT-50);
		obLine.setStroke(Color.RED);
		obPane.getChildren().add(obLine);
		
		obStage.setScene(new Scene(obPane));
		obStage.show();

		obPane.requestFocus();

		obPane.setOnKeyPressed(e ->
		{
			obLauncher.controller(e);

		});
		
	}
	
	
	private static void track(Shell obShell, UFOTypeA obUFO)
	{
		if (obShell.intersects(obUFO) && obUFO.isDropping)
		{
			Platform.runLater(()->obUFO.ufoDeath(obShell));
		}
	}

	public static void playerKill()
	{
		obStage.close();
	
	}


	synchronized private static void startTask()
	{
		obGThread = new Thread(() -> runBarrage());
		obGThread.setDaemon(true);
		obGThread.start();
		

	}

	private static void runBarrage()
	{
		while (true)
		{
			for (Shell obShell : lstShells)
			{
				if (obShell.getCoors().getY() < 0)
				{
					Platform.runLater(() -> obShell.clearShell());
					continue;
				}
				else 
				{
					Platform.runLater(() -> {
						obShell.clear(gc);
						obShell.getCoors().decY(obShell.getSpeed());
						obShell.render(gc);
					});
				}
				for (UFOTypeA obUFO : ufoList)
				{
					Platform.runLater(() -> track(obShell, obUFO));
					
				}
			}
			try
			{
				obLauncher.reloading();
				Thread.sleep(turnDelay);
				
			} 
			catch (InterruptedException exp)
			{
				exp.printStackTrace();
			}
		}

	}


	public static void main(String[] args)
	{
		Application.launch(args);

	}
	
	
}
