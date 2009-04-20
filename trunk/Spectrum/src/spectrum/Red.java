package spectrum;

public class Red extends Gem {

	public Red(Sprite sprite, Player player, int x, int y) 
	{
		super(sprite, player);
		this.setPosX(x);
		this.setPosY(y);
	}
	
	public void applyProperties()
	{
		this.setSprite(player.getSmall());
	}

}
