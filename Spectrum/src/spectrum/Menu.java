package spectrum;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Menu 
{
	private  ArrayList<MenuItem> menuItems;
	private int current;
	private int space = 50;
	Game game;
	private Font menuFont = new Font("Calibri", Font.PLAIN, 18);
	private Font menuFont_focused = new Font("Calibri", Font.BOLD, 22);
	
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
			Game.gs = GameState.PLAYING;
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
			if(current == i){
				graphics.setFont(menuFont_focused);
				graphics.setColor(Color.darkGray);
			}
			else{
				graphics.setFont(menuFont);
				graphics.setColor(Color.white);
			}
			graphics.drawString(menuItems.get(i).name, Game.SCREEN_WIDTH / 2 - 20, Game.SCREEN_HEIGHT / 4 + space * i);
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