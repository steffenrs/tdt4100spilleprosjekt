package spectrum;

import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.ArrayList;

public class LevelSystem {
	
	private ImageObserver io;
	private Level currentLevel;
	private String[] levels;
	private int levelIndex = 0;
	
	
	
	public LevelSystem(ImageObserver io, String[] levels){
		this.io = io;
		this.currentLevel = new Level(io);
		this.levels = levels;
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
		
		
	}
	
	public void changeLevel(int index){
		
		if (index > levels.length) {
			
		}
		else{
			levelIndex = index;
			changeLevel(levels[index]);
		}
	}
	

}
