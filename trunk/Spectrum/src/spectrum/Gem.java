package spectrum;

import java.util.ArrayList;

public class Gem extends Actor
{
	Player player;
	private boolean active = false;
	public static ArrayList<Gem> gems;
	private final int limit = 2;
	
	public Gem(Sprite sprite, Player player) 
	{
		super(sprite);
		this.player = player;
	}
	
	static
	{
		gems = new ArrayList<Gem>();	
	}
	
	public void update()
	{
		super.update();
	}
	
	public void activate()
	{
		if(active)
			return;
		
		this.active = true;
		
		if(gems.size() > limit)
		{
			gems.get(0).removeProperties();
			gems.get(0).active = false;
			gems.remove(0);
		}
		
		this.applyProperties();
	}
	
	public boolean checkDistance()
	{
		return(this.getSprite().getRectangle().intersects(this.player.getSprite().getRectangle()));
	}
	
	public void applyProperties()
	{
		
	}
	
	public void removeProperties()
	{
		
	}
}