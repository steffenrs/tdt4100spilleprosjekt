package spectrum;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame
{
	private ImageObserver observer;
	private Graphics graphics;
	private int counter = 0;
	
	private Actor test;
	
	public Game()
	{
		this.setTitle("WOOO");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setVisible(true);
		
		graphics = this.getGraphics();
	}
	
	public void run()
	{
		//temp
		createTestActor();
		
		//Game Loop
		while(true)
		{
			this.Update();
		}
	}
	
	//temp code
	public void createTestActor()
	{
		String path = System.getProperty("user.dir") + "\\Content\\explode01.png";
		ImageIcon ic = new ImageIcon(path);
		Sprite sprite = new Sprite(ic.getImage(), 8, 8, this.observer);
		test = new Actor(sprite);
	}
	
	public void Update()
	{
		int limit = 2000000;
		
		counter++;
		if(counter > limit)
		{
			test.Update();
			this.Draw();
			counter = 0;	
		}
	}
	
	public void Draw()
	{
		super.paintComponents(this.graphics);
		
		Actor actor;	
		Iterator<Actor> it = Actor.actors.iterator();
		while(it.hasNext())
		{
			actor = it.next();
			actor.Draw(this.graphics, this, 32, 32);
		}
	}
}
