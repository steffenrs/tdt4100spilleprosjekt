package spectrum;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;

public class Sprite 
{
	private Image image;
	private Rectangle rectangle;
	
	private boolean animate;
	private int frameX = 0;
	private int frameY = 0;
	private int tilesX;
	private int tilesY;
	private int frameInterval;
	private int frameTimeRemaining;
	
	private boolean active;
	private String description;
	
	private PixelGrabber pg;
	ColorModel cm;
	int[] pixels;
	
	/**
	 * @author Steffen R. Stenersen
	 * @return the width of each sprite in the spriteSheet
	 */
	public int getWidth() 
	{
		return (int)(rectangle.width / tilesX);
	}

	/**
	 * @author Steffen R. Stenersen
	 * @return the height of each sprite in the spriteSheet
	 */
	public int getHeight()
	{
		return (int)(rectangle.height / tilesY);
	}
	
	public boolean getActive()
	{
		return this.active;
	}
	
	public void setActive(boolean value)
	{
		this.active = value;
	}
	
	public void setDescription(String value)
	{
		this.description = value;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setAnimate(boolean value)
	{
		this.animate = value;
	}
	
	/**
	 * @author Steffen R. Stenersen
	 * Constructor
	 * @param image SpriteSheet
	 * @param tilesX the number of frames in the sheet
	 * @param frameInterval
	 * @param imageObserver
	 */
	public Sprite(Image image, int tilesX, int tilesY , int frameInterval, boolean animate, String description, ImageObserver imageObserver)
	{
		this.rectangle = new Rectangle(0, 0, image.getWidth(imageObserver), image.getHeight(imageObserver));
		this.description = description;
		this.image = image;
		this.tilesX = tilesX;
		this.tilesY = tilesY;
		this.frameInterval = frameInterval;
		this.frameTimeRemaining = frameInterval;
		this.animate = animate;
	}
	
	public void changeFrameX()
	{
		this.frameX++;
		this.frameX %= this.tilesX;
	}
	
	public void changeFrameY(int value)
	{
		if(value > this.tilesY)
			return;
		
		this.frameY = value;
	}
	
	/**
	 * @author Steffen R. Stenersen
	 * @param rect is the rectangle defining the area you want to get the alpha values from
	 * @return
	 */
	public int[] getAlpha(Rectangle rect)
	{
		pg = new PixelGrabber(image, rect.x, rect.y, rect.width, rect.height, false);
		
		try
		{
			pg.grabPixels();
		}
		catch(Exception e) { ; };
		
		ColorModel cm = pg.getColorModel();
		pixels = (int[])pg.getPixels();
		int[] pixels2;
		int[] alpha;
		
		pixels2 = (int[])pixels;
		alpha = new int[pixels2.length];

		for(int i= 0; i < pixels2.length; i++)
		{
			alpha[i] = cm.getAlpha(pixels2[i]);
		}		
		return alpha;
	}

	/**
	 * @author Steffen R. Stenersen
	 */
	public void update()
	{
		if(!animate)
			return;
		
		this.frameTimeRemaining--;
		if(this.frameTimeRemaining <= 0)
		{
			changeFrameX();
			
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
		g.drawImage(image, 
					x, 
					y,
					x + getWidth(),
					y + getHeight(),
					frameX * getWidth(),
					frameY * getHeight(),
					(frameX + 1) * getWidth(),
					(frameY + 1) * getHeight(),
					observer);
	}
}