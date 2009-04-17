package spectrum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame
{
	private ImageObserver observer;
	private Graphics graphics;
	private Input input;
	
	private Actor test;
	private Level testLevel;
	private Player player;
	
	//Game Loop
	static final double UPDATE_INTERVAL = 16.67;
	
	long start = System.currentTimeMillis();
	long current = 0;
	
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
		
		createTestActor();
		
		input = new Input(player);
		this.addKeyListener(input);
		graphics = this.getGraphics();
	}
	
	public void run()
	{
		this.update();
	}
	
	//temp code
	/**
	 * 
	 */
	public void createTestActor()
	{
		String path = System.getProperty("user.dir") + "\\Content\\";
		ImageIcon ic = new ImageIcon(path + "explode_01.png");
		Sprite sprite = new Sprite(ic.getImage(), 8, 4, this.observer);
		
		
		testLevel = new Level(this.observer);
		testLevel.Load(System.getProperty("user.dir") + "\\Content\\test.layer");
		test = new Actor(sprite, 32, 32, Type.PLAYER);
		
		 
		ImageIcon playerImage = new ImageIcon(path + "player_awsome.png");
		Sprite playerSprite = new Sprite(playerImage.getImage(), 1, 1, this.observer);
		player = new Player(playerSprite, 700, 700);
		
		
	}
	
	public void update()
	{
		while(true)
		{
			current = System.currentTimeMillis() - start;
			if(current > UPDATE_INTERVAL)
			{
				test.update();
				player.update();
				this.draw();
				start = System.currentTimeMillis();
			}
		}
	}
	
	public void draw()
	{
		//super.paintComponents(this.graphics);
		Actor actor;	
		Actor.actors.size();
		Iterator<Actor> it = Actor.actors.iterator();
		while(it.hasNext())
		{
			actor = it.next();
			actor.draw(this.graphics, this);
		}
		drawDebug();
	}
	
	public void drawDebug()
	{
		graphics.setColor(Color.WHITE);
		graphics.drawString("Debug:", 10, 80);
		graphics.drawString("Player pos: " + Float.toString(player.getPosX()) + "," + Float.toString(player.getPosY()), 10, 100);	
	}
}
