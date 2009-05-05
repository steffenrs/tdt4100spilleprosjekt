package spectrum;

public class Red extends Gem 
{
	public Red(Sprite sprite, int x, int y, boolean visible, boolean active) 
	{
		super(sprite, x, y, visible, active, "red");
	}
	
	public void applyProperties()
	{
		if (Game.getPlayer().getGravity() < 0) {
			Game.getPlayer().setActiveSprite("small");
			Player.getPlayer().getActiveSprite().changeFrameY(2);
			
		}
		else{
			Game.getPlayer().setActiveSprite("small");
		}
	}
	
	public void removeProperties(Gem gem)
	{	
		Game.getPlayer().setActiveSprite("normal");
		if( Game.getPlayer().getGravity() > 0 && !(gem instanceof Blue))
		{
			Game.getPlayer().setPosY((float)(getPosY() - Game.getPlayer().getRectangle().getHeight()));
		}
		else if(Game.getPlayer().getGravity() > 0 && (gem instanceof Blue))
		{
			
		}
	}
}