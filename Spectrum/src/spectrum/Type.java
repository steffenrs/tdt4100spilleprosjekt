package spectrum;

public enum Type 
{
	BLOCK(true, "block"),
	PLAYER(true, "player"),
	GEM(false, "gem"),
	GOAL(false, "goal"),
	BACKGROUND(false, "background");
	
	final boolean collidable;
	final String name;
	
	public boolean getCollidable()
	{
		return this.collidable;
	}
	
	private Type(boolean collidable, String name)
	{
		this.collidable = collidable;
		this.name = name; 
	}
	
	public static Type typeOf(String in)
	{
		Type[] types = Type.values();
		for (Type type : types) 
		{
			if(type.name.equals(in.toLowerCase()))
				return type;
		}
		return null;
	}
}