package spectrum;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Sprite 
{
	private Image spriteSheet;
	private Rectangle rectangle;
	
	private int frame = 0;
	private int tilesX;
	private int frameInterval;
	private int frameTimeRemaining;
	
	/**
	 * @author Steffen R. Stenersen
	 * @return the width of each sprite
	 */
	public int getWidth() 
	{
		return (int)(rectangle.width / tilesX);
	}

	/**
	 * @author Steffen R. Stenersen
	 * @return the height of each sprite
	 */
	public int getHeight() 
	{
		return rectangle.height;
	}

	/**
	 * @author Steffen R. Stenersen
	 * Constructor
	 * @param image SpriteSheet
	 * @param tilesX the number of frames in the sheet
	 * @param frameInterval
	 * @param imageObserver
	 */
	public Sprite(Image image, int tilesX, int frameInterval, ImageObserver imageObserver)
	{
		this.rectangle = new Rectangle(0, 0, image.getWidth(imageObserver), image.getHeight(imageObserver));
		this.spriteSheet = image;
		this.tilesX = tilesX;
		this.frameInterval = frameInterval;
		this.frameTimeRemaining = frameInterval;
	}
	
	/**
	 * @author Steffen R. Stenersen
	 */
	public void update()
	{
		this.frameTimeRemaining--;
		if(this.frameTimeRemaining <= 0)
		{
			this.frame++;
			if(this.frame >= this.tilesX)
				this.frame = 0;
			
			this.frameTimeRemaining = this.frameInterval;
		}
	}
	
	/**
	 * @author Steffen R. Stenersen
	 * @param g Graphics
	 * @param observer the GUI
	 * @param x position x that the frame will be drawn at
	 * @param y position y that the frame will be drawn at
	 */
	public void draw(Graphics g, ImageObserver observer, int x, int y)
	{
		g.drawImage(spriteSheet, 
					x, 
					y,
					x + getWidth(),
					y + getHeight(),
					frame * getWidth(),
					0,
					(frame + 1) * getWidth(),
					getHeight(),
					observer);
	}
}