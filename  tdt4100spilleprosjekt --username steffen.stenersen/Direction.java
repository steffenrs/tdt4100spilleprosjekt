package oving9;

public enum Direction 
{
	UP(0, -1),
	LEFT(-1, 0),
	DOWN(0, 1),
	RIGHT(1, 0);
	
	public final int dx;
	public final int dy;
	
	private Direction(int dx, int dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public static Direction directionFor(int x, int y)
	{
		if(x == 0 && y == -1)
			return UP;
		else if(x == -1 && y == 0)
			return LEFT;
		else if(x == 0 && y == 1)
			return DOWN;
		else if(x == 1 && y == 0)
			return RIGHT;
		else
			return null;
	}
	
	public static Direction directionFor(char c)
	{
		c = Character.toLowerCase(c);
		if(c == 'l')
			return LEFT;
		else if(c == 'r')
			return RIGHT;
		else if(c == 'u')
			return UP;
		else if(c == 'd')
			return DOWN;
		else
			return null;
	}	
	
	public char charFor()
	{
		if(this == UP)
			return 'U';
		else if(this == DOWN)
			return 'D';
		else if(this == LEFT)
			return 'L';
		else if(this == RIGHT)
			return 'R';
		else
			return ' ';
	}
	
	public Direction opposite()
	{
		if(this == UP)
			return DOWN;
		else if(this == DOWN)
			return UP;
		else if(this == RIGHT)
			return LEFT;
		else if(this == LEFT)
			return RIGHT;
		else
			return null;
	}
}
