package spectrum;

import java.awt.image.ImageObserver;
import java.net.URL;


public class LevelSystem {
	
	ImageObserver io;
	private Level currentLevel;
	private String[] levels;
	private int levelIndex = 0;
	private Game game;
	
	public LevelSystem(ImageObserver io, String[] levels, Game game){
		this.io = io;
		this.currentLevel = new Level(io, game);
		this.levels = levels;
		this.game = game;
	}
	
	public String[] getLevels()
	{
		return levels;
	}
	
	public int getLevelIndex(){
		return this.levelIndex;
	}
	
	public void changeLevel(String path)
	{
		URL url = getClass().getResource("content/" + path);
		currentLevel.clear();
		currentLevel.Load(url);
		game.createPlayer();
		Game.gs = GameState.PLAYING;
	}
	
	public void changeLevel(int index){
		
		if (index > levels.length - 1){
			Game.gs = GameState.COMPLETE;
			return;
		}
		else{
			levelIndex = index;
			changeLevel(levels[index]);
			Menu.menuItems.get(0).name = "Restart Level";
			game.setStarted(true);
		}
	}
	
	
}
