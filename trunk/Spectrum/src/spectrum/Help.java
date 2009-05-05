package spectrum;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;




public class Help {
	
	
	private Font headerFont;
	private FontMetrics fm;
	private Font helpFont;
	private Font subHeader;
	private int stringLength;
	private String [] keys;
	private String [] gems;
	private String header;
	private String subHeader1;
	private String subHeader2;
	private String [] gemNumber;
	

	
	public Help(){
		
		header = "Help";
		
		gems = new String[]{"- The green gem makes the gravity lower and you'll therefore", 
				"   be able to jump higher", 
				"- The red gem makes you small, this is convienent in shallow places",
				"- The blue gem turns the world upside down and sends you crashing to the roof",
				"- The purple gem gives you Spectravision.",
				"  With Spectravision you can see gems that are normally invisible to you",		
		};
		
		keys = new String[] {"- Left/Right  =  Move",
							"- Up  =  Jump",
							"- Space  =  Activate Gem",
							"- Escape  =  Menu / Exit menu",
							"- P  =  Pause"
		};
		
		gemNumber = new String []{"- You can only have 2 active gems at the same time.",
				"  When you activate the 3rd gem, the 1st gem you activated will become inactive"};
		
		helpFont = new Font("Calibri", Font.PLAIN, 17);	
		headerFont = new Font("Calibri", Font.BOLD, 32);
		subHeader = new Font("Calibri", Font.BOLD, 24);
		subHeader1 = "Keys";
		subHeader2 = "Gems";
		
	}
	
	
	public void drawHelp(Graphics g){
		g.setColor(Color.BLACK);
		
		g.setFont(headerFont);
		fm = g.getFontMetrics(headerFont);
		stringLength = fm.stringWidth(header);
		g.drawString(header, Game.SCREEN_WIDTH / 2 - stringLength / 2, 70);
		
		
		fm = g.getFontMetrics(subHeader);
		stringLength = fm.stringWidth(subHeader1);
		g.setFont(subHeader);
		g.drawString(subHeader1, Game.SCREEN_WIDTH / 2 - stringLength / 2 , 150);
		
		g.setFont(helpFont);
		for (int i = 0; i < keys.length; i++) {
			g.drawString(keys[i], 330, 200 + i * 40);
		}
		
		fm = g.getFontMetrics(subHeader);
		stringLength = fm.stringWidth(subHeader2);
		g.setFont(subHeader);
		g.drawString(subHeader2, Game.SCREEN_WIDTH / 2 - stringLength / 2 , 400);
		
		//Draws gemdescriptions
		g.setFont(helpFont);
		g.drawString(gems[0], 200, 450);
		g.drawString(gems[1], 200, 470);
		g.drawString(gems[2], 200, 520);
		g.drawString(gems[3], 200, 570);
		g.drawString(gems[4], 200, 620);
		g.drawString(gems[5], 200, 640);
		
		for (int i = 0; i < gemNumber.length; i++) {
			g.drawString(gemNumber[i], 200, 700 + i * 20);
		}
		
		
		
	}
}
