package spectrum;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Purple extends Gem 
{
	
	static ArrayList<Gem> invisible = new ArrayList<Gem>();

	public Purple(Sprite sprite, int x, int y, boolean visible,  boolean collidable) 
	{
		super(sprite, x , y, visible, collidable, "purple");	
	}
	
	public void applyProperties()
	{	
		for(int i = 0; i < Actor.actors.size(); i++)
		{
			if(Actor.actors.get(i) instanceof Gem)
			{
				if(!(((Gem)(Actor.actors.get(i))).getState()))
				{
					invisible.add((Gem)(Actor.actors.get(i)));
					((Gem)(Actor.actors.get(i))).setState(true);
				}
			}
		}
	}
	
	public void removeProperties(Gem gem)
	{
		for(int i = 0; i < invisible.size(); i++)
		{
			((Gem)(invisible.get(i))).setState(false);
		}
		invisible.clear();
	}
	
}
