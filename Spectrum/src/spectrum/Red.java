package spectrum;

public class Red extends Gem 
{
	public Red(Sprite sprite, int x, int y) 
	{
		super(sprite);
		this.setPosX(x);
		this.setPosY(y);
		
	}
	
	public void applyProperties()
	{
		Game.getPlayer().setSprite(Game.getPlayer().getSmall());
	}
}