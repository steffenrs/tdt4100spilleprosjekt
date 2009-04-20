package spectrum;

import java.util.ArrayList;

public class Gem extends Actor
{
	Player player;
	private boolean active = false;
	public static ArrayList<Gem> activeGems;
	private final int limit = 2;
	
	public Gem(Sprite sprite, Player player) 
	{
		super(sprite);
		this.player = player;
		activeGems = new ArrayList<Gem>();	
	}
	
	public void update()
	{
		super.update();
	}
	
	public void activate()
	{
		if(active)
			return;
		
		activeGems.add(this);
		this.active = true;
		
		if(activeGems.size() > limit)
		{
			activeGems.get(0).removeProperties();
			activeGems.get(0).active = false;
			activeGems.remove(0);
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