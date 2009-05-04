package spectrum;

public class Blue extends Gem {

	public Blue(Sprite sprite, int x, int y, boolean visible, boolean active) 
	{
		super(sprite, x, y, visible, active, "blue");
	
	}
	
	public void applyProperties()
	{
		Game.getPlayer().setGravity((Game.getPlayer().getGravity()) * -1);
		Game.getPlayer().setJumpPower((Game.getPlayer().getJumpPower()) * -1);
		Game.getPlayer().setIsOnGround(false);
		Player.getPlayer().getActiveSprite().changeFrameY(2);
	}
	
	public void removeProperties()
	{
		Game.getPlayer().setGravity((Game.getPlayer().getGravity()) * -1);
		Game.getPlayer().setJumpPower((Game.getPlayer().getJumpPower()) * -1);
		Game.getPlayer().getActiveSprite().changeFrameY(0);
	}
	
}
