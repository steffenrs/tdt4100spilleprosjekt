package spectrum;

public class Red extends Gem 
{
	public Red(Sprite sprite, int x, int y, boolean visible, boolean active) 
	{
		super(sprite, x, y, visible, active, "red");
	}
	//Red gem handles player size
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
	
	public void removeProperties()
	{	
		Game.getPlayer().setActiveSprite("normal");
		if( Game.getPlayer().getGravity() > 0)
		{
			Game.getPlayer().setPosY((float)(Game.getPlayer().getPosY() - Game.getPlayer().getRectangle().getHeight()));
		}
	}
}