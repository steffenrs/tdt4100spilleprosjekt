package spectrum;

public class Red extends Gem 
{
	public Red(Sprite sprite, int x, int y, boolean active) 
	{
		super(sprite, x, y, active);
	}
	
	public void applyProperties()
	{
		Game.getPlayer().setActiveSprite("small");
	}
	
	public void removeProperties()
	{	
		Game.getPlayer().setActiveSprite("normal");
		Game.getPlayer().setPosY((float)(getPosY() - Game.getPlayer().getRectangle().getHeight()));
	}
}