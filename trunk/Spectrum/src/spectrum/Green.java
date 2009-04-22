package spectrum;

public class Green extends Gem 
{
	public Green(Sprite sprite, int x, int y) 
	{
		super(sprite);
		this.setPosX(x);
		this.setPosY(y);
	}

	public void applyProperties()
	{
		Game.getPlayer().setGravity(6f);
	}

}
