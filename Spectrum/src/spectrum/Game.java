package spectrum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable
{
	private ImageObserver observer;
	private Graphics graphics;
	private Image offscreenImage;
	private Graphics offscreen;
	
	private Input input;
	public static GameState gs;
	
	//Temp code
	private Actor test;
	private Level testLevel;
	private Player player;
	
	//Game Loop
	Thread thread;
	static final double UPDATE_INTERVAL = 16.67;
	
	public static void main(String[] args) 
	{
		Game game = new Game();
	}
	
	public Game()
	{		
		//Thread
		thread = new Thread(this);
		thread.start();
	}
	
	public void run()
	{
		//Window settings
		this.setTitle("Spectrum");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setResizable(false);
	
		this.setVisible(true);
		
		//Double Buffering
		offscreenImage = createImage(800, 800);
		offscreen = offscreenImage.getGraphics();
		createTestActor();

		gs = GameState.PLAYING;
		input = new Input(player);
		this.addKeyListener(input);
		graphics = this.getGraphics();
		
		this.update();
	}
	
	//temp code
	public void createTestActor()
	{
		String path = System.getProperty("user.dir") + "\\Content\\";
		ImageIcon ic = new ImageIcon(path + "explode_01.png");
		Sprite sprite = new Sprite(ic.getImage(), 8, 4, this.observer);
		
		testLevel = new Level(this.observer);
		testLevel.Load(path + "test.layer");
		test = new Actor(sprite, 32, 32, false);
		
		 
		ImageIcon playerImage = new ImageIcon(path + "player_02.png");
		ImageIcon playerSmall = new ImageIcon(path + "small.png");
		Sprite playerSprite = new Sprite(playerImage.getImage(), 1, 1, this.observer);
		Sprite playerSmallSprite = new Sprite(playerSmall.getImage(), 1, 1, this.observer);
		player = new Player(playerSprite, playerSmallSprite, 700, 700);
		
		ImageIcon green = new ImageIcon(path + "green.png");
		Sprite greenSprite = new Sprite(green.getImage(), 1, 1, this.observer);
		Gem gem = new Green(greenSprite, player, 600, 700);
		
		ImageIcon red = new ImageIcon(path + "red.png");
		Sprite redSprite = new Sprite(red.getImage(), 1, 1, this.observer);
		gem = new Red(redSprite, player, 500, 700);
		
	}
	
	public void update()
	{
		while(true)
		{
			switch(gs)
			{
			case WON:
				break;
				
			case MENU:
				break;
			case PAUSED:
				break;
			case PLAYING:
				handleInput();
				this.updateActors();
				this.paint(graphics);
				
				try
				{
					Thread.sleep(1000 / 60);
				}
				catch(InterruptedException e) {; }
					break;
			}
		}
	}
	
	private void handleInput() 
	{
		if(input.isLeftKeyDown())
			player.doMove(-1);
		if(input.isRightKeyDown())
			player.doMove(1);
		
		if(input.isUpKeyDown())
			player.doJump();
		if(!input.isPKeyDown() && input.isLastPKeyDown())
			if(gs == GameState.PLAYING)
				gs = GameState.PAUSED;
			else if(gs == GameState.PAUSED)
				gs = GameState.PLAYING;
		
		if(input.isSpaceKeyDown())
			Gem.activateGem();
	}

	public void updateActors()
	{
		for (Actor actor : Actor.actors) 
		{
			actor.update();
		}
	}
	
	public void paint(Graphics g)
	{
		if(offscreen == null)
			return;
		
		offscreen.setColor(Color.BLACK);
		
		for (Actor actor : Actor.actors) 
		{
			actor.draw(offscreen, this);
		}
		
		g.drawImage(offscreenImage, 0, 0, this);

		drawDebug();
	}
	
	public void drawDebug()
	{
		graphics.setColor(Color.WHITE);
		graphics.drawString("Debug:", 10, 80);
		graphics.drawString("Player pos: " + Float.toString(player.getPosX()) + "," + Float.toString(player.getPosY()), 10, 100);	
		graphics.drawString("Player grav; " + Float.toString(player.getGravity()), 10, 120);
	}
}
