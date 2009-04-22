package spectrum;

import java.util.ArrayList;

public class Gem extends Actor
{
//	Player player;
	private boolean active = false;
	public static ArrayList<Gem> gems;
	private final int limit = 2;
	
	public Gem(Sprite sprite) 
	{
		super(sprite);
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
		//Jon: Fiks slik at vi kan deaktivere effekten til Gem'en dersom den er aktiv
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
	
	public static void activateGem()
	{
		for (Gem gem : gems) 
		{
			if(Actor.checkCollision(Game.getPlayer(), gem))
				gem.activate();
		}
	}
	
	public void applyProperties()
	{
		
	}
	
	public void removeProperties()
	{
		
	}
}