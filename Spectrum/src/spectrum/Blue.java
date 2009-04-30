package spectrum;

public class Blue extends Gem {

	public Blue(Sprite sprite, int x, int y, boolean active) 
	{
		super(sprite);
		this.setPosX(x);
		this.setPosY(y);
		this.setActive(active);
	}
	
	public void applyProperties()
	{
		Game.getPlayer().setGravity((Game.getPlayer().getGravity()) * -1);
		Game.getPlayer().setIsOnGround(false);
		
		if(gems.get(0)instanceof Red && gems.get(1) instanceof Red)
		{
			Game.getPlayer().setActiveSprite("smallRotate");
		}
		else
		{
			Game.getPlayer().setActiveSprite("rotate");
		}
	}
	
	public void removeProperties()
	{
		Game.getPlayer().setActiveSprite("normal");
	}
	
}
