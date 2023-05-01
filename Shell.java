public class Shell extends Sprite
{

	private int speed;
	

	public Shell(PlayerShip obLauncher)
	{
		super(IMGMISSILE, 
				obLauncher.getCoors().getX() + obLauncher.getImage().getWidth() / 2 - IMGMISSILE.getWidth() / 2,
				obLauncher.getCoors().getY() - 10);
		Main.lstShells.add(this);

		this.speed=6;
		this.render(Main.gc);

	}	

	public Shell(PlayerShip obLauncher, int nSpeed)
	{
		super(IMGMISSILE2, 
				obLauncher.getCoors().getX() + obLauncher.getImage().getWidth() / 2 - IMGMISSILE.getWidth() / 2,
				obLauncher.getCoors().getY() - 10);
		Main.lstShells.add(this);

		this.speed=nSpeed;
		this.render(Main.gc);

	}

	
	public void clearShell()
	{
		this.clear(Main.gc);
		Main.lstShells.remove(this);

	}

	public int getSpeed()
	{
		return this.speed;
	}


}
