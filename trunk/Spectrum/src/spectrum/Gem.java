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
	
	
	/**
	 * Activates / deactivates the current gem
	 */
	private void activate()
	{
		if ((System.currentTimeMillis() - buttonPressed) > 1000) 
		{
			Highscore.addPenalty();
			this.getActiveSprite().changeFrameX();
			
			//if gem is active, deactivate it
			if(this.active){
				this.removeProperties();
				this.active = false;
				buttonPressed = System.currentTimeMillis();
				
				//deactivate all other gems of same color
				for (Gem gem : getGems(this.color)) 
				{
					if(gem == this)
						continue;
					
					gem.active = false;
					gem.getActiveSprite().changeFrameX();
				}
				
				for (Gem gem : gems) 
				{
					if(this.color == gem.color)
					{
						gems.remove(gem);
						break;
					}
				}
				return;
			}
			//if gem is not active, activate it
			else
			{
				buttonPressed = System.currentTimeMillis();
				
				//if the number of active gems is bigger than allowed, remove the first active gem
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
				
				//activate the current gem
				gems.add(this);
				this.active = true;
				
				//activate all other gems of same color
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
	
	/**
	 * Activates/deactivates a gem if the player stands on it
	 * @author Jon Roar Solset
	 */
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
	
	/*
	 * Draws the gem if its visible
	 * (non-Javadoc)
	 * @see spectrum.Actor#draw(java.awt.Graphics, java.awt.image.ImageObserver)
	 */
	public void draw(Graphics g, ImageObserver observer)
	{
		if(!this.visible)
			return;
		
		super.draw(g, observer);
	}
	
	/*
	 * Applies the property of the current gem
	 */
	public void applyProperties()
	{
		
	}
	
	/*
	 * Removes the property of the current gem
	 */
	public void removeProperties()
	{
		
	}
	
	/**
	 * @param color Color to search for
	 * @return Returns a list of all gems of the given color
	 */
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