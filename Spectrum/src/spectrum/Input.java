package spectrum;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Input extends KeyAdapter
{
	private Player player;
	
	//Keys
	private boolean upKeyDown;
	private boolean lastUpKeyDown;
	private boolean downKeyDown;
	private boolean lastDownKeyDown;
	private boolean leftKeyDown;
	private boolean rightKeyDown;
	private boolean pKeyDown;
	private boolean lastPKeyDown;
	private boolean spaceKeyDown;
	private boolean escKeyDown;
	private boolean enterKeyDown;
	
	public boolean isEnterKeyDown(){
		return enterKeyDown;
	}
	
	public boolean isEscKeyDown(){
		return escKeyDown;
	}
	
	public boolean isUpKeyDown() {
		return upKeyDown;
	}
	
	public boolean isLastUpKeyDown(){
		return lastUpKeyDown;
	}

	public boolean isDownKeyDown() {
		return downKeyDown;
	}
	
	public boolean isLastDownKeyDown() {
		return lastDownKeyDown;
	}

	public boolean isLeftKeyDown() {
		return leftKeyDown;
	}

	public boolean isRightKeyDown() {
		return rightKeyDown;
	}

	public boolean isPKeyDown() {
		return pKeyDown;
	}
	
	public boolean isLastPKeyDown() {
		return lastPKeyDown;
	}

	public boolean isSpaceKeyDown() {
		return spaceKeyDown;
	}

	public Input(Player player)
	{
		this.player = player;
	}

	public void keyPressed(KeyEvent ke) 
	{
		switch (ke.getKeyCode()) 
		{
		case KeyEvent.VK_ENTER:
			enterKeyDown = true;
			break;
		
		case KeyEvent.VK_ESCAPE:
			escKeyDown = true;
			break;
		
		case KeyEvent.VK_UP :
			upKeyDown = true;
			break;
			
		case KeyEvent.VK_DOWN:
			downKeyDown = true;
			break;
		
		case KeyEvent.VK_LEFT:
			leftKeyDown = true;
			break;
			
		case KeyEvent.VK_RIGHT:
			rightKeyDown = true;
			break;
			
		case KeyEvent.VK_P:
			pKeyDown = true;
			break;
			
		case KeyEvent.VK_SPACE:
			spaceKeyDown = true;
			break;
			
		default:
			break;
		}
	}
	
	public void keyReleased(KeyEvent ke)
	{
		switch (ke.getKeyCode()) 
		{
		case KeyEvent.VK_ENTER:
			enterKeyDown = false;
			break;
		
		case KeyEvent.VK_ESCAPE:
			escKeyDown = false;
			break;
		
		case KeyEvent.VK_UP :
			upKeyDown = false;
			break;
			
		case KeyEvent.VK_LEFT :
			leftKeyDown = false;
			break;
		case KeyEvent.VK_DOWN :
			downKeyDown = false;
			break;
		case KeyEvent.VK_RIGHT :
			rightKeyDown = false;
			break;
		case KeyEvent.VK_SPACE :
			spaceKeyDown = false;
			break;
		case KeyEvent.VK_P :
			pKeyDown = false;
			break;
		default:
			break;
		}
	}
	
	public void setLastKeys()
	{
		this.lastDownKeyDown = downKeyDown;
		this.lastPKeyDown = pKeyDown;
		this.lastUpKeyDown = upKeyDown;
	}
}