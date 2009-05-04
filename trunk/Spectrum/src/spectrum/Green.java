package spectrum;

public class Green extends Gem 
{
	public Green(Sprite sprite, int x, int y, boolean visible,  boolean collidable) 
	{
		super(sprite, x , y, visible, collidable);
		
	}

	public void applyProperties()
	{
		if (Game.getPlayer().getGravity() < 0) {
			Game.getPlayer().setGravity(-13f);
		}
		else{
			Game.getPlayer().setGravity(13f);
		}
		
	}

	public void removeProperties()
	{
		if (Game.getPlayer().getGravity() < 0) {
			Game.getPlayer().setGravity(-20f);
		}
		else{
			Game.getPlayer().setGravity(20f);
		}
		
	}
	
	
}
