package spectrum;

public class Player extends Actor
{
	//Physics
	private float gravity = 10f;
	private float jumpPower = -10f;
	private float jumpTime = 0f;
	private boolean isOnGround = true;
	private boolean jump = false;
	private Sprite playerSmall;
	
	private float moveSpeed = 3f;
	
	public void setGravity(float value)
	{
		this.gravity = value;
	}
	
	public float getGravity()
	{
		return this.gravity;
	}
	
	private void setIsOnGround(boolean value)
	{
		if(value)
		{
			jumpTime = 0f;
			jump = false;
		}
			
		this.isOnGround = value;
	}
	
	public Player(Sprite sprite, Sprite playerSmall, float x, float y)
	{
		super(sprite);
		this.setPosX(x);
		this.setPosY(y);
		this.playerSmall = playerSmall;
	}
	
	public Sprite getSmall()
	{
		return this.playerSmall;
	}
	
	public void doJump()
	{
		if(isOnGround)
			this.jump = true;
	}
	
	public void doMove(int direction)
	{
		this.setPosX(this.getPosX() + direction*moveSpeed);
	}
	
	public void update()
	{
		super.update();
		
		applyPhysics();
	}
	
	private void applyPhysics()
	{
		if(!isOnGround)
			jumpTime += Game.UPDATE_INTERVAL / 1000;

		//Gravity + jumppower
		if(jump)			
			this.setPosY(this.getPosY() + jumpPower + gravity * jumpTime);
		//Only gravity
		else
			this.setPosY(this.getPosY() + gravity * jumpTime);
		
		if(!isOnGround)
			jumpTime += Game.UPDATE_INTERVAL / 1000;

		checkActorCollision();
		checkWallCollision();
	}

	private void checkActorCollision() 
	{
		for (Actor actor : actors) 
		{
			if (this == actor) {
				continue;
			}

			if (actor.getCollidable()) {
				if (this.getRectangle().intersects(actor.getRectangle())) 
				{
					//ER EN FEIL HER. PRØV Å COMMENTE UT COLLISON right->left && left->right
					//Collision below
					if(this.getPosY() < actor.getPosY()){
						this.setPosY(actor.getPosY() - this.getSprite().getHeight());
						setIsOnGround(true);
					}
					
					//Collision right->left 
					else if(this.getPosX() > actor.getPosX())
						this.setPosX(actor.getPosX() + actor.getSprite().getWidth());
					
					//Collision left->right
					else if(this.getPosX() < actor.getPosX())
						this.setPosX(actor.getPosX() - this.getSprite().getWidth());
					
					//Collision above ********FUNKER IKKE!*******
					else if(this.getPosY() > actor.getPosY()){
						this.setPosY(actor.getPosY() + this.getSprite().getHeight());
					}
					return;
				}
			}
		}
		
		setIsOnGround(false);
	}

	private void checkWallCollision() 
	{
		//check bottom
		if(this.getPosY() > 800 - this.getSprite().getHeight())
		{
			this.setPosY(800 - this.getSprite().getHeight());
			setIsOnGround(true);
		}

		//check right wall
		if (this.getPosX() >= 800 - this.getSprite().getWidth() ) {
			this.setPosX(800 - this.getSprite().getWidth());	
		}
		
		if (this.getPosX() <= 0) {
			this.setPosX(0);
		}
	}

	
}