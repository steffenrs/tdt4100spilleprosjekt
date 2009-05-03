package spectrum;

import java.awt.Color;
import java.awt.Font;
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
	Font menuFont = new Font("Arial", Font.PLAIN, 32);
	Font debugFont = new Font("Arial", Font.PLAIN, 12);
	
	private Input input;
	public static GameState gs;
	
	//Temp code
	private LevelSystem levelSystem;
	
	Image background;
	private static Player player;
	private Goal goal;
	private Menu menu;
	
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
		
		levelSystem = new LevelSystem(this, new String[]{
				"Test.layer", "Test.layer"
		});
		levelSystem.changeLevel(0);
		createPlayer();
		createMenu();

		gs = GameState.MENU;
		input = new Input();
		this.addKeyListener(input);
		graphics = this.getGraphics();
		this.update();
	}
	
	public void createPlayer()
	{		
		background = new ImageIcon(getClass().getResource("content//background_01.png")).getImage();
		
		player = Player.getPlayer();
		ImageIcon playerSmall = new ImageIcon(getClass().getResource("content//player_animated_small.png"));
		Sprite playerSmallSprite = new Sprite(playerSmall.getImage(), 2 , 4, 10, true, "small", this.observer);
		player.addSprite(playerSmallSprite);
		player.collidable = true;
	}
	
	public void createMenu()
	{
		menu = new Menu(this);
		menu.add(new MenuItem("Start"));
		menu.add(new MenuItem("Choose Level"));
		menu.add(new MenuItem("Help"));
		menu.add(new MenuItem("Exit"));
	}
	
	public void update()
	{	
		while(true)
		{
			handleInput();
			switch(gs)
			{
			case WON:
				break;
				
			case MENU:
				this.paint(graphics);
				break;
			case PAUSED:
				break;
			case PLAYING:
				Actor.updateActors();
				checkWin();
				this.paint(graphics);
				break;
				
			case SELECT_LEVEL:
				this.paint(graphics);
				break;
			}
			
			try
			{
				Thread.sleep(1000 / 60);
			}
			catch(InterruptedException e) {; }
				
		}
	}
	
	private void handleInput() 
	{
		switch(gs)
		{
			case PLAYING:	
				player.getActiveSprite().setAnimate(false);
				if(input.getKey("Left").isKeyDown())
				{
					player.getActiveSprite().setAnimate(true);
					player.doMove(-1);
					player.getActiveSprite().changeFrameY(0);
				}
				if(input.getKey("Right").isKeyDown())
				{	player.getActiveSprite().setAnimate(true);
					player.doMove(1);
					player.getActiveSprite().changeFrameY(1);
				}
				
				if(input.getKey("Up").isKeyDown())
					player.doJump();
				
				if(input.getKey("P").isKeyPress())
					gs = GameState.PAUSED;
				
				if(input.getKey("Space").isKeyDown())
					Gem.activateGem();
				
				if(input.getKey("Escape").isKeyDown())
					gs = GameState.MENU;
			break;
			
			case PAUSED:
				if(input.getKey("P").isKeyPress())
					gs = GameState.PLAYING;
				break;
			case MENU:
				if(input.getKey("Down").isKeyPress())
					menu.moveDown();
				if(input.getKey("Up").isKeyPress())
					menu.moveUp();
				if(input.getKey("Enter").isKeyDown())
					menu.takeAction();
				break;
				
			case WON:
				if(input.getKey("Enter").isKeyDown())
				{
					this.repaint();
					gs = GameState.PLAYING;
				}
				break;
				
			}
		input.setLastKeys();
	}
	
	
	private void checkWin()
	{
		if(Goal.getGoal() == null)
			return;
		
		if(player.isInside(Goal.getGoal()))
		{	
			levelSystem.changeLevel(levelSystem.getLevelIndex() + 1);
			createPlayer();
			gs = GameState.WON;
		}
	}
	
	public void paint(Graphics g)
	{	
		if(offscreen == null || gs == null)
			return;
	
		offscreen.drawImage(background, 0, 0, this);
		
		switch(gs)
		{
			case WON:
				offscreen.setColor(Color.black);
				offscreen.setFont(menuFont);
				offscreen.drawString("NICE, Level up!", SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2);
				break;
			case MENU:
				menu.drawString(offscreen);
				break;
			case PAUSED:
				break;
			case PLAYING:				
				offscreen.setColor(Color.BLACK);
				
				for (Actor actor : Actor.actors) 
				{
					actor.draw(offscreen, this);
				}
				break;
				
				
			case SELECT_LEVEL:
				offscreen.setFont(menuFont);
				String[] levels = levelSystem.getLevels();
				
				for (int i = 0; i < levels.length; i++) {
					offscreen.drawString(levels[i].toString(), 100, 100 + i * 50);
				}
				break;
		}
		
		drawDebug();
		g.drawImage(offscreenImage, 0, 0, this);
	}
	
	public void drawDebug()
	{
		offscreen.setColor(Color.black);
		offscreen.setFont(debugFont);
		
		offscreen.setColor(Color.black);
		offscreen.drawString("Debug:", 10, 80);
		offscreen.drawString("Player pos: " + Float.toString(player.getPosX()) + "," + Float.toString(player.getPosY()), 10, 100);	
		offscreen.drawString("Player grav; " + Float.toString(player.getGravity()), 10, 120);
		offscreen.drawString("Player rest; " + Float.toString(player.restTime), 10, 140);
	}
}
