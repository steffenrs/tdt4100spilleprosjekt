package spectrum;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JFrame;

import com.sun.corba.se.impl.orbutil.graph.Graph;

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
			if (y > 1200) {
			g.drawString(theEnd, Game.SCREEN_WIDTH / 2 - stringLength / 2, Game.SCREEN_HEIGHT / 2 );
			}
			
			if (y > 2000) {
				Game.gs = GameState.MENU;
			}
			
			
		}
	}
}
