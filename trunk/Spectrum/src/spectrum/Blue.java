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
		Game.getPlayer().setGravity(-20f);
		Game.getPlayer().setActiveSprite("rotate");
	}
	
	

}
