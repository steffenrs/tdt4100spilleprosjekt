package spectrum;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Actor 
{
	public static ArrayList<Actor> actors;
	Sprite spriteSheet;
	
	static 
	{
		actors = new ArrayList<Actor>();
	}
	
	public Actor(Sprite sprite)
	{
		actors.add(this);
		this.spriteSheet = sprite;
	}
	
	public void Update()
	{
		spriteSheet.Update();
	}
	
	public void Draw(Graphics g, ImageObserver observer, int x, int y)
	{
		spriteSheet.Draw(g, observer, x, y);
	}
}