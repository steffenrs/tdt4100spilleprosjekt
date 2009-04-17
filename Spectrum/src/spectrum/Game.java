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
			this.Update();
		
	}
	
	//temp code
	/**
	 * 
	 */
	public void createTestActor()
	{
		String path = System.getProperty("user.dir") + "\\Content\\";
		ImageIcon ic = new ImageIcon(path + "explode01.png");
		Sprite sprite = new Sprite(ic.getImage(), 8, 4, this.observer);
		
		
		testLevel = new Level(this.observer);
		testLevel.Load(System.getProperty("user.dir") + "\\Content\\test.layer");
		test = new Actor(sprite, 32, 32);
		
		 
		ImageIcon playerImage = new ImageIcon(path + "player.png");
		Sprite playerSprite = new Sprite(playerImage.getImage(), 1, 1, this.observer);
		Player player = new Player(playerSprite, 700, 700);
		
		
	}
	
	
	
	float updateInterval = 1000/60;
	long start = System.currentTimeMillis();
	long current = 0;
	
	public void Update()
	{
		while(true)
		{
			current = System.currentTimeMillis() - start;
			if(current > updateInterval)
			{
				test.Update();
				this.Draw();
				start = System.currentTimeMillis();
			}
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
