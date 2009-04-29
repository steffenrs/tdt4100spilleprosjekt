package spectrum;

public class Key 
{
	
	int keyCode;
	boolean keyState;
	boolean lastKeyState;
	
	public Key(int keyCode)
	{
		this.keyCode = keyCode;
	}
	
	public int getKeyCode()
	{
		return this.keyCode;
	}
	
	public void setKeyState(boolean value)
	{
		this.keyState = value;
	}
	
	public void setLastKeyState(boolean value)
	{
		this.lastKeyState = value;
	}
	
	boolean isKeyPress()
	{
		return (lastKeyState && !keyState);
	}
	
	boolean isKeyDown()
	{
		return keyState;
	}
}
