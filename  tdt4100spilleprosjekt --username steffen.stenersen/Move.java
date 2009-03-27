package oving9;

public class Move 
{
	public final Direction direction;
	public final boolean wasPush;
	
	public Move(Direction dir, boolean wp)
	{
		this.direction = dir;
		this.wasPush = wp;
	}
}
