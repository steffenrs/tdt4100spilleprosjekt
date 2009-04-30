package spectrum;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Input extends KeyAdapter
{	
	Key[] keys = new Key[] 
	{
		new Key(KeyEvent.VK_LEFT),
		new Key(KeyEvent.VK_ESCAPE),
		new Key(KeyEvent.VK_UP),
		new Key(KeyEvent.VK_DOWN),
		new Key(KeyEvent.VK_RIGHT),
		new Key(KeyEvent.VK_P),
		new Key(KeyEvent.VK_ENTER),
		new Key(KeyEvent.VK_SPACE)
	};
	
	public Key getKey(String value)
	{
		String keyValue;
		
		for (int i = 0; i < keys.length; i++) 
		{
			keyValue = KeyEvent.getKeyText(keys[i].getKeyCode());
			if(value.equals(keyValue))
				return keys[i];
		}
		
		return null;
	}

	public void keyPressed(KeyEvent ke) 
	{
		for (int i = 0; i < keys.length; i++) 
		{
			if(ke.getKeyCode() == keys[i].getKeyCode())
				keys[i].setKeyState(true);
		}
	}
	
	public void keyReleased(KeyEvent ke)
	{
		for (int i = 0; i < keys.length; i++) 
		{
			if(ke.getKeyCode() == keys[i].getKeyCode())
				keys[i].setKeyState(false);
		}
	}
	
	public void setLastKeys()
	{
		for (int i = 0; i < keys.length; i++) 
		{
			keys[i].setLastKeyState(keys[i].isKeyDown());
		}
	}
}