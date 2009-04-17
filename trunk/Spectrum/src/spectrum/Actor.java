package spectrum;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Actor 
{
	public static ArrayList<Actor> actors;
	private Sprite spriteSheet;
	private Type type;
	
	private int xPos;
	private int yPos;
	
	public void setXPos(int x)
	{
		xPos = x;	
	}
	
	public int getXPos()
	{
		return xPos;
	}
	
	public void setYPos(int y)
	{
		yPos = y;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	static
	{
		actors = new ArrayList<Actor>();
	}
	
	public Actor(Sprite sprite, Type type)
	{
		actors.add(this);
		this.spriteSheet = sprite;
		this.type = type;
	}
	
	public Actor(Sprite sprite, int x, int y, Type type)
	{
		this(sprite, type);
		this.xPos = x;
		this.yPos = y;
	}
	
	public void Update()
	{
		spriteSheet.Update();
	}
	
	public void Draw(Graphics g, ImageObserver observer)
	{
		spriteSheet.Draw(g, observer, xPos, yPos);
	}
	
	public void Draw(Graphics g, ImageObserver observer, int x, int y)
	{
		spriteSheet.Draw(g, observer, x, y);
		
	}
}