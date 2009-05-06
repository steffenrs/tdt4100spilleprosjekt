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
	Font menuFont = new Font("Calibri", Font.PLAIN, 32);
	Font debugFont = new Font("Arial", Font.PLAIN, 12);
	Font scoreFont = new Font("Times New Roman", Font.BOLD, 18);
	
	private Input input;
	public static GameState gs;
	private LevelSystem levelSystem;
	private Image background;
	private Image menuBackground;
	private static Player player;
	private Goal goal;
	private Menu menu;
	private ChooseLevel chooseLevel;
	private Epilogue epilogue;
	private Help help;
	
	
	boolean started = false;
	
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
	
	public void setBackground(Image background)
	{
		this.background = background;
	}
	
	public void setStarted(boolean value)
	{
		this.started = value;
	}
	
	public boolean getStarted()
	{
		return this.started;
	}
	
	public LevelSystem getLevelSystem()
	{
		return this.levelSystem;
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
		this.setLocationRelativeTo(null);
		input = new Input();
		this.addKeyListener(input);
		
		this.setVisible(true);
		
		//Double Buffering
		offscreenImage = createImage(800, 800);
		offscreen = offscreenImage.getGraphics();
		graphics = this.getGraphics();
		
		menuBackground = new ImageIcon(getClass().getResource("content/background_00.png")).getImage();
		
		//game setting
		levelSystem = new LevelSystem(this, new String[]{
				"level1.layer", "level2.layer", "level3.layer", "level4.layer",  "level5.layer"
				, "level6.layer", "level7.layer", "level8.layer", "level9.layer", "level10.layer"
		}, this);
		
		String[] levels = levelSystem.getLevels();
		chooseLevel = new ChooseLevel(levels, levelSystem);
		epilogue = new Epilogue();
		help = new Help();
		createMenu();
		gs = GameState.MENU;
		this.update();
	}
	
	/*
	 * Sets up the player settings
	 */
	public void createPlayer()
	{		
		player = Player.getPlayer();
		ImageIcon playerSmall = new ImageIcon(getClass().getResource("content/player_animated_small.png"));
		Sprite playerSmallSprite = new Sprite(playerSmall.getImage(), 2 , 4, 10, true, "small", this.observer);
		playerSmallSprite.setAnimate(true);
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
	
	/*
	 * Handles the updating of the game based on which gamestate it's in
	 */
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
				this.paint(graphics);
				break;
			case PLAYING:
				Actor.updateActors();
				checkWin();
				Highscore.updateHighscore();
				this.paint(graphics);
				break;
				
			case SELECT_LEVEL:
				this.paint(graphics);
				break;
				
			case COMPLETE:
				this.setStarted(false);
				this.paint(graphics);
				break;
			case HELP:
				this.paint(graphics);
				break;
			}
			
			try
			{
				Thread.sleep(1000 / 65);
			}
			catch(InterruptedException e) {; }
		}
	}
	
	private void handleInput() 
	{
		switch(gs)
		{
			case PLAYING:
				
				Sprite s = player.getActiveSprite();
				player.getActiveSprite().setAnimate(false);
				if(input.getKey("Left").isKeyDown())
				{
					
					player.getActiveSprite().setAnimate(true);
					player.doMove(-1);
					if(player.getGravity() < 0)
						player.getActiveSprite().changeFrameY(2);
					else
						player.getActiveSprite().changeFrameY(0);
				}	
				if(input.getKey("Right").isKeyDown())
				{	player.getActiveSprite().setAnimate(true);
					player.doMove(1);
					if(player.getGravity() < 0)
						player.getActiveSprite().changeFrameY(3);
					else
						player.getActiveSprite().changeFrameY(1);
				}
				
				if(input.getKey("Up").isKeyDown())
					player.doJump();
				
				if(input.getKey("P").isKeyPress())
					gs = GameState.PAUSED;
				
				if(input.getKey("Space").isKeyDown())
					
					if(player.getIsOnGround())
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
				if(input.getKey("Enter").isKeyPress())
					menu.takeAction();
				break;
			
			case SELECT_LEVEL:
				if(input.getKey("Down").isKeyPress())
					chooseLevel.moveDown();
				if(input.getKey("Up").isKeyPress())
					chooseLevel.moveUp();
				if(input.getKey("Enter").isKeyDown())	
				{
					Highscore.resetScore();
					chooseLevel.takeAction();
				}
				if (input.getKey("Escape").isKeyDown()) 
					Game.gs = GameState.MENU;
				
				break;
				
			case HELP:
				if(input.getKey("Escape").isKeyPress()){
					Game.gs = GameState.MENU;	
				}
				break;
				
			case WON:
				if(input.getKey("Enter").isKeyDown())
				{
					levelSystem.changeLevel(levelSystem.getLevelIndex() + 1);
					this.repaint();		
				}
				break;
			case COMPLETE:
				if (input.getKey("Escape").isKeyDown()) {
					Game.gs = GameState.MENU;
				}
				
			}
		input.setLastKeys();
	}
	
	/*
	 * Checks if the player have reached the goal
	 */
	private void checkWin()
	{
		if(Goal.getGoal() == null)
			return;
		
		if(player.isInside(Goal.getGoal()))
		{	
			
			Highscore.updateTotalScore();
			gs = GameState.WON;
		}
	}	
	
	/*
	 * Resets the current level
	 */
	public void resetLevel()
	{
		Highscore.resetHighscore();
		levelSystem.changeLevel(levelSystem.getLevelIndex());
	}
	
	/*
	 * Handles the painting of the game based on the gamestate
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
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
				offscreen.drawImage(menuBackground, 0, 0, this);
				menu.drawString(offscreen);
				break;
			case PAUSED:
				offscreen.setFont(menuFont);
				offscreen.drawString("PAUSED!", SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2);
				break;
			case PLAYING:
		
				offscreen.setColor(Color.BLACK);
				
				for (Actor actor : Actor.actors) 
				{
					actor.draw(offscreen, this);
					drawScore();
				}
				break;
				
			case SELECT_LEVEL:
				offscreen.drawImage(menuBackground, 0, 0, this);
				offscreen.setFont(menuFont);
				chooseLevel.drawString(offscreen);	
				break;
				
			case COMPLETE:
				offscreen.drawImage(menuBackground, 0, 0, this);
				epilogue.drawEpilogue(offscreen);
				epilogue.setY(epilogue.getY() + 2);
				break;
				
			case HELP:
				offscreen.drawImage(menuBackground, 0, 0, this);
				help.drawHelp(offscreen);
				break;
		}
		
		
		g.drawImage(offscreenImage, 0, 0, this);
	}
	
	/*
	 * draws the score to the screen
	 */
	public void drawScore()
	{
		offscreen.setFont(scoreFont);
		offscreen.drawString("Total score: " + (int)(Highscore.getTotalscore()), 10, 60);
		offscreen.drawString("Your score: " + (int)(Highscore.getHighscore()), 10, 80);
	}
}
