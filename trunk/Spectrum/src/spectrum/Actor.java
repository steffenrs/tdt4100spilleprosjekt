package spectrum;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Actor 
{
	public static ArrayList<Actor> actors;
	private Sprite spriteSheet;
	private boolean collidable;
	

	private float posX;
	private float posY;
	
	public Actor(Sprite sprite)
	{
		actors.add(this);
		this.spriteSheet = sprite;
		
	}
	
	public Actor(Sprite sprite, int x, int y, boolean collidable)
	{
		this(sprite);
		this.posX = x;
		this.posY = y;
		this.collidable = collidable;
	}
	
	public boolean getCollidable(){
		return collidable;
	}

	
	public void setPosX(float x)
	{
		posX = x;	
	}
	
	public float getPosX()
	{
		return posX;
	}
	
	public void setPosY(float y)
	{
		posY = y;
	}
	
	public float getPosY()
	{
		return posY;
	}
	
	public Sprite getSprite()
	{
		return this.spriteSheet;
	}
	
	static
	{
		actors = new ArrayList<Actor>();
	}
	
	
	
	public void update()
	{
		spriteSheet.update();
	}
	
	public void draw(Graphics g, ImageObserver observer)
	{
		spriteSheet.draw(g, observer, (int)posX, (int)posY);
	}
}