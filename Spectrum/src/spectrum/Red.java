package spectrum;

public class Red extends Gem 
{
	public Red(Sprite sprite, int x, int y, boolean active) 
	{
		super(sprite);
		this.setPosX(x);
		this.setPosY(y);
		this.setActive(active);
		
	}
	
	public void applyProperties()
	{
		Game.getPlayer().setIsSmall(true);
	}
	
	public void removeProperties()
	{
		Game.getPlayer().setIsSmall(false);
	}
}