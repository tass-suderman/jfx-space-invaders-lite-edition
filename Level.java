import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Level
{
	private int difficulty;
	
	
	public Level()
	{
		this.difficulty=1;
	}
	
	protected void levelUp()
	{
		Main.ufoList.clear();
		
		levelAnnounce();
		addUFOs();
		this.difficulty++;

		Main.obLauncher.reloadTime-= Main.obLauncher.reloadTime > 30 ? 2 : 0;
		UFOTypeA.turnDelay-= UFOTypeA.turnDelay>20 ? 7 : 0;
		Main.turnDelay-= Main.turnDelay>10 ? 1 : 0;
	}
	
	private void levelAnnounce()
	{
		Alert obAlert = new Alert(AlertType.INFORMATION);
		obAlert.setTitle("Here Comes a wave of them!");
		obAlert.setContentText("Watch out! More of them are coming!");
		obAlert.setHeaderText("You've reached level " + difficulty);
		obAlert.showAndWait();
	}
	

	private void addUFOs()
	{	
		int nTotalUFO = 4*this.difficulty;
		int nGuard = (int) (Math.random()*nTotalUFO/2) + 4;
		int nHeight = 0;
		for (int i = 1; i<= nGuard; i++)
		{
			Main.ufoList.add(new UFOTypeC(((i%5)*70)+55, nHeight, UFOTypeC.UFOIMAGES));
			if(i%5==0) 
			{
				nHeight-=100;
			}
			
		}
		for (int i=1; i<=nTotalUFO-nGuard+2; i++)
		{
			nHeight-= 75;
			Main.ufoList.add(new UFOTypeA((Math.random()*Main.CANVASWIDTH-50)+25,nHeight, UFOTypeA.UFOIMAGES));
		}
		if(this.difficulty>=3) 
		{
			for (int i=0; i<4*(difficulty-2); i++)
			{
				nHeight -=75;
				Main.ufoList.add(new UFOTypeB((Math.random()*Main.CANVASWIDTH-50)+75, nHeight,UFOTypeB.UFOIMAGES));
			}
		}
	}

}
