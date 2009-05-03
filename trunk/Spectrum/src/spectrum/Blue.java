package spectrum;

public class Blue extends Gem {

	public Blue(Sprite sprite, int x, int y, boolean visible, boolean active) 
	{
		super(sprite, x, y, visible, active);
	
	}
	
	public void applyProperties()
	{
		Game.getPlayer().setGravity((Game.getPlayer().getGravity()) * -1);
		Game.getPlayer().setIsOnGround(false);
		
		if(gems.get(0)instanceof Red || gems.get(1) instanceof Red)
		{
			Game.getPlayer().getActiveSprite().changeFrameY(2);
		}
		else
		{
			Game.getPlayer().getActiveSprite().changeFrameY(3);
		}
	}
	
	public void removeProperties()
	{
		Game.getPlayer().getActiveSprite().changeFrameY(0);
	}
	
}
