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
	private Level testLevel;
	
	public static void main(String[] args) 
	{
		Game game = new Game();
		game.run();
	}
	
	public Game()
	{
		this.setTitle("Spectrum");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setVisible(true);
		this.setResizable(false);
		
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
		Sprite sprite = new Sprite(ic.getImage(), 8, 4, this.observer);
		
		
		testLevel = new Level(this.observer);
		testLevel.Load(System.getProperty("user.dir") + "\\Content\\test.layer");
		test = new Actor(sprite, 32, 32);
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
		//super.paintComponents(this.graphics);
		Actor actor;	
		Actor.actors.size();
		Iterator<Actor> it = Actor.actors.iterator();
		while(it.hasNext())
		{
			actor = it.next();
			actor.Draw(this.graphics, this);
		}
	}
}
