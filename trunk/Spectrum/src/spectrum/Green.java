package spectrum;

public class Green extends Gem 
{
	public Green(Sprite sprite, Player player, int x, int y) 
	{
		super(sprite, player);
		this.setPosX(x);
		this.setPosY(y);
	}

	public void applyProperties()
	{
		player.setGravity(6f);
	}

}
