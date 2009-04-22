package spectrum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Actor 
{
	public static ArrayList<Actor> actors;
	private Sprite spriteSheet;
	public boolean collidable;
	public boolean colliding;

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
	
	static
	{
		actors = new ArrayList<Actor>();
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
	
	public Sprite setSprite(Sprite sprite)
	{
		return this.spriteSheet = sprite;
	}
	
	public Rectangle getRectangle()
	{
		return new Rectangle((int)this.posX, (int)this.posY, this.spriteSheet.getWidth(), this.spriteSheet.getHeight());
	}
	
	public void update()
	{
		spriteSheet.update();
	}
	
	public void draw(Graphics g, ImageObserver observer)
	{
		spriteSheet.draw(g, observer, (int)posX, (int)posY);
	}
	
	public static Rectangle intersects(Rectangle a, Rectangle b)
	{
		int x1 = Math.max((int)a.getMinX(), (int)b.getMinX());
		int y1 = Math.max((int)a.getMinY(), (int)b.getMinY());
		int x2 = Math.min((int)a.getMaxX(), (int)b.getMaxX());
		int y2 = Math.min((int)a.getMaxY(), (int)b.getMaxY());
		
		int width = x2 - x1;
		int height = y2 - y1;
		
		if(width > 0 && height > 0)
			return new Rectangle(x1, y1, width, height);
		else
			return null;
	}
	
	public static boolean checkCollision(Actor a, Actor b)
	{
		final int ALPHA_THRESHOLD = 48;
		
		Rectangle collisionRect = intersects(a.getRectangle(), b.getRectangle());
		if(collisionRect == null)
			return false;
		
		Rectangle rA = new Rectangle((int)(collisionRect.x - a.posX), (int)(collisionRect.y - a.posY), collisionRect.width, collisionRect.height);
		Rectangle rB = new Rectangle((int)(collisionRect.x - b.posX), (int)(collisionRect.y - b.posY), collisionRect.width, collisionRect.height);
		
		int[] alphaA = a.getSprite().getAlpha(rA);
		int[] alphaB = b.getSprite().getAlpha(rB);
		
		for (int i = 0; i < alphaB.length; i++) {
			if(alphaA[i] > ALPHA_THRESHOLD && alphaB[i] > ALPHA_THRESHOLD)
				return true;
		}
		
		return false;
	}
}