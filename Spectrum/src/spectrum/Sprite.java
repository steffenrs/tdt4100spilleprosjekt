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
	
	private int frame = 0;
	private int tilesX;
	private int frameInterval;
	private int frameTimeRemaining;
	
	private boolean active;
	private String description;
	
	private PixelGrabber pg;
	ColorModel cm;
	int[] pixels;
	
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
	
	public int[] getAlpha(Rectangle rect)
	{
		pg = new PixelGrabber(image, rect.x, rect.y, rect.width, rect.height, false);
		
		try
		{
			pg.grabPixels();
		}
		catch(Exception e) { ; };
		
		ColorModel cm = pg.getColorModel();
		boolean isByte;
		isByte = pg.getPixels() instanceof byte[] ? true : false;
		
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
	 * Constructor
	 * @param image SpriteSheet
	 * @param tilesX the number of frames in the sheet
	 * @param frameInterval
	 * @param imageObserver
	 */
	public Sprite(Image image, int tilesX, int frameInterval, String description, ImageObserver imageObserver)
	{
		this.rectangle = new Rectangle(0, 0, image.getWidth(imageObserver), image.getHeight(imageObserver));
		this.description = description;
		this.image = image;
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
		g.drawImage(image, 
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