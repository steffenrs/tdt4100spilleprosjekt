package spectrum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Menu 
{
	private  ArrayList<MenuItem> menuItems;
	private int current;
	private int space = 50;
	Game game;
	
	public Menu(Game game)
	{
		menuItems = new ArrayList<MenuItem>();
		current = 0;
		this.game = game;
	}
	
	public void add(MenuItem item)
	{
		menuItems.add(item);
	}
	
	public void moveUp()
	{
		current--;
		if(current < 0)
			current = menuItems.size() - 1;
	}
	
	public void moveDown()
	{
		current++;
		if(current >= menuItems.size())
			current = 0;
	}
	
	public void takeAction()
	{
		if(current == 0)
			game.gs = GameState.PLAYING;
		else if(current == 1)
		{
			
		}
		else if(current == 2)
		{
			System.exit(0);
		}
		
	}
	
	public void drawString(Graphics graphics)
	{
		for (int i = 0; i < menuItems.size(); i++) 
		{
			if(current == i)
				graphics.setColor(Color.red);
			else
				graphics.setColor(Color.white);
			
			graphics.drawString(menuItems.get(i).name, 300, space + space * i);
		}
	}
	
	public void draw(Graphics graphics, ImageObserver io)
	{
		for (int i = 0; i < menuItems.size(); i++) 
		{
			menuItems.get(i).sprite.draw(graphics, io, 300, space + space * i);
		}
	}
}