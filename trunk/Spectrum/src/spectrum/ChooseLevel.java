package spectrum;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class ChooseLevel {
	
	private int current;
	private Font menuFont = new Font("Calibri", Font.PLAIN, 20);
	private Font menuFont_focused = new Font("Calibri", Font.BOLD, 26);
	private int space = 50;
	private String [] levels;
	private LevelSystem levelSystem;
	
	public ChooseLevel(String [] levels, LevelSystem levelsystem){
		this.levels = levels;
		this.current = 0;
		this.levelSystem = levelsystem;
	}
	
	//Moves marker up an down in the menulist
	public void moveUp()
	{
		current--;
		if(current < 0)
			current = levels.length -1;
			
	}
	
	public void moveDown()
	{
		current++;
		if(current >= levels.length)
			current = 0;
	}
	
	//Launches when enter is pressed in the menu
	public void takeAction()
	{
		levelSystem.changeLevel(current);
		current = 0;
		
	}
	
	//Draws the chooseLevel menu to screen
	public void drawString(Graphics graphics)
	{
		for (int i = 0; i < levels.length; i++) 
		{
			if(current == i){
				graphics.setFont(menuFont_focused);
				graphics.setColor(Color.darkGray);
			}
			else{
				graphics.setFont(menuFont);
				graphics.setColor(Color.white);
			}
			graphics.drawString(levels[i].substring(0, levels[i].indexOf('.')), 200, Game.SCREEN_HEIGHT / 4 + space * i);
		}
	}
}
