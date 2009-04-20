package spectrum;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Actor 
{
	public static ArrayList<Actor> actors;
	private Sprite spriteSheet;
	private Type type;
	private boolean collidable;
	

	private float posX;
	private float posY;
	
	public Actor(Sprite sprite, Type type)
	{
		actors.add(this);
		this.spriteSheet = sprite;
		this.type = type;
	}
	
	public Actor(Sprite sprite, int x, int y, Type type)
	{
		this(sprite, type);
		this.posX = x;
		this.posY = y;
	}
	
	public Type getType(){
		return this.type;
	}

	
	public void setposX(float x)
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