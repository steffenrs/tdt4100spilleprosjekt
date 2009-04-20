package spectrum;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter
{
	private Player player;
	
	public Input(Player player)
	{
		this.player = player;
	}

	public void keyPressed(KeyEvent ke) 
	{
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_UP : 
			player.doJump();
			break;
		
		case KeyEvent.VK_LEFT:
			player.doMove(-1);
			break;
			
		case KeyEvent.VK_RIGHT:
			player.doMove(1);
			break;
			
		case KeyEvent.VK_P:
			if(Game.gs == GameState.PLAYING)
				Game.gs = GameState.PAUSED;
			else if(Game.gs == GameState.PAUSED)
				Game.gs = GameState.PLAYING;
			break;
			
		default:
			break;
		}
	}
}
