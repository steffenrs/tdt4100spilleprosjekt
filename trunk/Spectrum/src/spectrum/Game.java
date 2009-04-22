package spectrum;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.text.AttributedCharacterIterator;

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
	Gem gem;
	
	private static Player player;
	private Goal goal;
	
	//Game Loop
	Thread thread;
	
	//Game Constants
	static final double UPDATE_INTERVAL = 16.67;
	static final int SCREEN_WIDTH = 800;
	static final int SCREEN_HEIGHT = 800;
	
	public static Player getPlayer()
	{
		return player;
	}
	
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
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
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
	
	public void createTestActor()
	{		
		testLevel = new Level(this.observer);
		testLevel.Load(getClass().getResource("content/Test.layer"));
		this.goal = Goal.getGoal();
		
		ImageIcon playerImage = new ImageIcon(getClass().getResource("content//player_jostein.png"));
		ImageIcon playerSmall = new ImageIcon(getClass().getResource("content//small.png"));
		Sprite playerSprite = new Sprite(playerImage.getImage(), 1, 1, this.observer);
		Sprite playerSmallSprite = new Sprite(playerSmall.getImage(), 1, 1, this.observer);
		player = new Player(playerSprite, playerSmallSprite, 700, 700);
		player.collidable = true;
		
		ImageIcon green = new ImageIcon(getClass().getResource("content//green.png"));
		Sprite greenSprite = new Sprite(green.getImage(), 1, 1, this.observer);
		gem = new Green(greenSprite, 600, 700);
		
		ImageIcon red = new ImageIcon(getClass().getResource("content//red.png"));
		Sprite redSprite = new Sprite(red.getImage(), 1, 1, this.observer);
		gem = new Red(redSprite, 600, 770);
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
		
//		if(!input.isPKeyDown() && input.isLastPKeyDown())
//			if(gs == GameState.PLAYING)
//				gs = GameState.PAUSED;
//			else if(gs == GameState.PAUSED)
//				gs = GameState.PLAYING;
		
		if(input.isPKeyDown())
			Actor.checkCollision(player, gem);
		
		if(input.isSpaceKeyDown())
			Gem.activateGem();
	}

	private void updateActors()
	{
		for (Actor actor : Actor.actors) 
		{
			actor.update();
		}
		
		checkWin();
	}
	
	private void checkWin()
	{
		if(goal == null)
			return;
		
		if(player.getRectangle().intersects(goal.getRectangle()))
			gs = GameState.WON;
	}
	
	public void paint(Graphics g)
	{
		if(offscreen == null || gs == null)
			return;
		
		offscreen.setColor(Color.BLACK);
		
		for (Actor actor : Actor.actors) 
		{
//			if(actor.colliding)
//			{
//				offscreen.setColor(Color.red);
//				offscreen.fillRect(actor.getRectangle().x, actor.getRectangle().y, actor.getRectangle().width, actor.getRectangle().height);
//				offscreen.drawString(Float.toString(actor.getPosY()), 10, 140);
//			}
//			else
				actor.draw(offscreen, this);
				offscreen.setColor(Color.red);
				if(actor.getCollidable())
					offscreen.drawRect((int)actor.getPosX(), (int)actor.getPosY(), 1, 1);
		}

		switch(gs)
		{
			case WON:
				offscreen.setColor(Color.WHITE);
				offscreen.drawString("NICE", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
				break;
			case MENU:
				break;
			case PAUSED:
				break;
			case PLAYING:
				break;
		}
		
		drawDebug();
		g.drawImage(offscreenImage, 0, 0, this);
	}
	
	public void drawDebug()
	{
		if(Actor.checkCollision(player, gem))
			offscreen.setColor(Color.red);
		else
			offscreen.setColor(Color.black);
		
		Rectangle r = Actor.intersects(player.getRectangle(), gem.getRectangle());
		
		if(r != null)
		{
			offscreen.fillRect(r.x, r.y, r.width, r.height);
		}
		
		offscreen.setColor(Color.WHITE);
		offscreen.drawString("Debug:", 10, 80);
		offscreen.drawString("Player pos: " + Float.toString(player.getPosX()) + "," + Float.toString(player.getPosY()), 10, 100);	
		offscreen.drawString("Player grav; " + Float.toString(player.getGravity()), 10, 120);
	}
}
