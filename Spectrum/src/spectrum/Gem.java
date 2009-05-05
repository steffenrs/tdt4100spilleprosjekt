package spectrum;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Gem extends Actor
{
//	Player player;
	private boolean active = false;
	public static ArrayList<Gem> gems = new ArrayList<Gem>();
	private final int limit = 1;
	private long buttonPressed;
	private boolean visible;
	private String color;
	
	public Gem(Sprite sprite) 
	{
		super(sprite);
	}
	
	public void setActive(boolean active)
	{
		this.active = active;		
	}

	public Gem(Sprite sprite, int x, int y, boolean visible, boolean collidable, String color)
	{
		super(sprite, x, y, collidable);
		this.visible = visible;
		this.color = color;
	}
	
	public void setState(boolean visible)
	{
		this.visible = visible;
	}
	
	public boolean getState()
	{
		return this.visible;
	}
	
	public void update()
	{
		super.update();
	}
	
	private void activate()
	{
		if ((System.currentTimeMillis() - buttonPressed) > 1000) {
			Highscore.addPenalty();
			this.getActiveSprite().changeFrameX();
			if(this.active){
				this.removeProperties();
				this.active = false;
				buttonPressed = System.currentTimeMillis();
				
				
				for (Gem gem : getGems(this.color)) 
				{
					if(gem == this)
						continue;
					
					gem.active = false;
					gem.getActiveSprite().changeFrameX();
				}
				
				for (Gem gem : gems) {
					if(this.color == gem.color)
					{
						gems.remove(gem);
						break;
					}
				}
				return;
			}
		else
		{
			buttonPressed = System.currentTimeMillis();
			
			if(gems.size() > limit)
			{
				gems.get(0).removeProperties();
				gems.get(0).active = false;
				
				for (Gem gem : getGems(gems.get(0).color)) 
				{
					if(gem == gems.get(0))
						continue;
					
					gem.active = false;
					gem.getActiveSprite().changeFrameX();
				}
				
				gems.get(0).getActiveSprite().changeFrameX();
				gems.remove(0);
			}
			gems.add(this);
			this.active = true;
			
			for (Gem gem : getGems(this.color)) 
			{
				if(gem == this)
					continue;
				
				gem.active = true;
				gem.getActiveSprite().changeFrameX();
			}
			
			this.applyProperties();
				
		}
	}
}
	
	public static void activateGem()
	{
		
		for (Actor gem : Actor.actors) 
		{
			if(!(gem instanceof Gem))
				continue;

			if(Actor.checkCollision(Game.getPlayer(), gem))
			{
				if(((Gem)(gem)).visible)
				{
				((Gem)(gem)).activate();
				return;
				}
			}
		}
	}
	
	public void draw(Graphics g, ImageObserver observer)
	{
		if(!this.visible)
			return;
		
		super.draw(g, observer);
	}
	
	public void applyProperties()
	{
		
	}
	
	public void removeProperties()
	{
		
	}
	
	public static ArrayList<Gem> getGems(String color)
	{
		ArrayList<Gem> gems = new ArrayList<Gem>();
		
		for (Actor actor : Actor.actors) {
			if(actor instanceof Gem)
			{
				Gem g = ((Gem)(actor));
				if(g.color.equals(color))
					gems.add(g);
			}
		}
		
		return gems;
	}
}