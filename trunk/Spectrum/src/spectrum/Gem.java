package spectrum;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Gem extends Actor
{
//	Player player;
	private boolean active = false;
	public static ArrayList<Gem> gems = new ArrayList<Gem>();
	private final int limit = 2;
	private long buttonPressed;
	private boolean visible;
	
	public Gem(Sprite sprite) 
	{
		super(sprite);
	}
	
	public void setActive(boolean active)
	{
		this.active = active;		
	}

	public Gem(Sprite sprite, int x, int y, boolean visible, boolean collidable)
	{
		super(sprite, x, y, collidable);
		this.visible = visible;
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
			this.getActiveSprite().changeFrameX();
			if(this.active){
				this.removeProperties();
				this.active = false;
				buttonPressed = System.currentTimeMillis();
				return;
			}
		else
		{
			buttonPressed = System.currentTimeMillis();
			
			if(gems.size() > 1)
			{
				gems.get(0).removeProperties();
				gems.get(0).active = false;
				gems.get(0).getActiveSprite().changeFrameX();
				gems.remove(0);
			}
			gems.add(this);
			this.active = true;
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
				((Gem)(gem)).activate();
				return;
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
}