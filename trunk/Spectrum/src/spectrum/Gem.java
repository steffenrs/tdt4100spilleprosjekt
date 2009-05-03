package spectrum;

import java.util.ArrayList;

public class Gem extends Actor
{
//	Player player;
	private boolean active = false;
	public static ArrayList<Gem> gems;
	private final int limit = 2;
	private long buttonPressed;
	
	public Gem(Sprite sprite) 
	{
		super(sprite);
		gems.add(this);
	}
	
	public void setActive(boolean active){
		this.active = active;
		
	}

	public Gem(Sprite sprite, int x, int y, boolean collidable)
	{
		super(sprite, x, y, collidable);
		gems.add(this);
	}
	
	static
	{
		gems = new ArrayList<Gem>();	
	}
	
	public void update()
	{
		super.update();
	}
	
	private void activate()
	{
		if (System.currentTimeMillis() - buttonPressed > 1000) {
			this.getActiveSprite().changeFrameX();
			if(active){
				this.removeProperties();
				active = false;
				buttonPressed = System.currentTimeMillis();
				return;
			}
			
			buttonPressed = System.currentTimeMillis();
			this.active = true;
			
			if(gems.size() > limit)
			{
				gems.get(0).removeProperties();
				gems.get(0).active = false;
				gems.remove(0);
			}
			
			this.applyProperties();
	}
}
	
	public static void activateGem()
	{
		
		for (Gem gem : gems) 
		{
			if(Actor.checkCollision(Game.getPlayer(), gem))
			{
				gem.activate();
				return;
			}
		}
	}
	
	public void applyProperties()
	{
		
	}
	
	public void removeProperties()
	{
		
	}
}