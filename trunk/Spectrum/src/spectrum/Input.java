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
			
		default:
			break;
		}
	}
	
//	public void keyReleased(KeyEvent arg0) 
//	{
//
//	}
//
//	public void keyTyped(KeyEvent arg0) 
//	{
//
//	}
}
