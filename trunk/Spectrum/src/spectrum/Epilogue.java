package spectrum;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Timer;


public class Epilogue {
	
	String madeBy;
	String [] names;
	Font nameFont;
	Font endFont;
	Timer timer;
	int y;
	int stringLength;
	String theEnd;
	
	
	
	public Epilogue(){
		names = new String [] {"Aleksander Torstensen", "Jon Roar Solset", "Jostein Roalkvam", "Christian Håland", "Steffen Stenersen"};
		madeBy = "Made By: ";
		theEnd = "The End";

		nameFont = new Font("Helvetica", Font.PLAIN, 26);
		endFont = new Font("Helvetica", Font.BOLD, 40);
		y = -2000;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getY(){
		return y;
	}
		
	//Draws the credits to screen
	public void drawEpilogue(Graphics g){
		
		FontMetrics fm = g.getFontMetrics(nameFont);
		FontMetrics fm2 = g.getFontMetrics(endFont);
		
		for (int i = 0; i < names.length; i++) {
			stringLength = fm.stringWidth(names[i]);
			g.setFont(nameFont);
			g.drawString(names[i], Game.SCREEN_WIDTH / 2 - stringLength / 2, y + i * 300);
			
			stringLength = fm.stringWidth(madeBy);
			if (y < -1300) {
				g.drawString(madeBy, Game.SCREEN_WIDTH / 2 - stringLength / 2, 1900 + y);
			}
			
			stringLength = fm2.stringWidth(theEnd);
			
			g.setFont(endFont);
			if (y > 1100) {
			g.drawString(theEnd, Game.SCREEN_WIDTH / 2 - stringLength / 2, Game.SCREEN_HEIGHT / 2 );
			}
			
			if (y > 1900) {
				Game.gs = GameState.MENU;
			}
			
			
		}
	}
}
