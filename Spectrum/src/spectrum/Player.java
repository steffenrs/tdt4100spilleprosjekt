package spectrum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Player extends Actor
{
	//Physics
	private float gravity = 20f;
	private float jumpPower = -10f;
	private float pJumpPower = 0;
	private float jumpTime = 0f;
	private boolean isOnGround = true;
	private boolean jump = false;
	private float moveSpeed = 3f;
	private float maxSpeed = 20f;
	
	public void setJumpPower(float value)
	{
		this.jumpPower = value;
	}
	
	public float getJumpPower()
	{
		return this.jumpPower;
	}
	
	public void setGravity(float value)
	{
		this.gravity = value;
	}
	
	public float getGravity()
	{
		return this.gravity;
	}
	
	public void setIsOnGround(boolean value)
	{
		if(this.isOnGround == value)
			return;
		
		if(value)
		{
			jumpTime = 0f;
			jump = false;
		}
			
		this.isOnGround = value;
	}
	
	public boolean getIsOnGround(){
		return isOnGround;
	}
	
	public Player(Sprite sprite, float x, float y)
	{
		super(sprite);
		this.setPosX(x);
		this.setPosY(y);
	}
	
	/*
	 * player jumps if it's not in the ground
	 */
	public void doJump()
	{
		if(isOnGround)
		{
			this.jump = true;
			pJumpPower = jumpPower;
			this.setIsOnGround(false);
		}
	}
	
	/*
	 * moves player alongs the x-axis
	 */
	public void doMove(int direction)
	{
		this.setPosX(this.getPosX() + direction*moveSpeed);
	}
	
	public void update()
	{
		super.update();
		
		applyPhysics();
	}
	
	/*
	 * Handles jumping an actor / wall collision
	 */
	private void applyPhysics()
	{
		if(!isOnGround)
			jumpTime += Game.UPDATE_INTERVAL / 1000;

		//Gravity + jumppower
		if(jump)			
			this.setPosY(this.getPosY() + pJumpPower + gravity * jumpTime);
		//Only gravity
		else
			this.setPosY(this.getPosY() + gravity * jumpTime);

		setIsOnGround(false);
		checkActorCollision();
		checkWallCollision();
	}

	/*
	 * Checks if the actor collides with other collidable actors
	 */
	private void checkActorCollision() 
	{
		for (Actor actor : actors) 
		{
			if (this == actor) {
				continue;
			}

			if (actor.getCollidable()) {
				if(Actor.checkCollision(this, actor))
				{
					Rectangle intersection = Actor.intersects(this.getRectangle(), actor.getRectangle());
					
					//Top / bottom
					 if(intersection.width > intersection.height)
					 {
						 if (Game.getPlayer().getGravity() > 0) {
							//beneath actor
							 if(this.getPosY() > actor.getPosY())
							 {
								 this.setPosY(this.getPosY() + intersection.height);
								 pJumpPower = 0;
								 jumpTime = 0;
							 }
							 //above actor
							 else
							 {
								 this.setPosY(this.getPosY() - intersection.height);
								 setIsOnGround(true);
							 }
						}
						else
						{
							
							if(this.getPosY() > actor.getPosY())
							 {
								this.setPosY(this.getPosY() + intersection.height );
								setIsOnGround(true);
								
							 }
							 //above actor
							 else
							 {
								 this.setPosY(this.getPosY() - intersection.height);
								 
								 pJumpPower = 0;
								 jumpTime = 0;
							 }
						}
					 }
					 //Sideways
					 else
					 {
						 //left
						 if(this.getPosX() < actor.getPosX())
						 {
							 this.setPosX(this.getPosX() - intersection.width);
						 }
						 //right
						 else
						 {
							 this.setPosX(this.getPosX() + intersection.width);
						 }
					 }
				}
			}
		}
	}

	/*
	 * Checks if the player collides with the wall
	 */
	private void checkWallCollision() 
	{
		//check bottom
		if(this.getPosY() > 800 - this.getActiveSprite().getHeight())
		{
			this.setPosY(800 - this.getActiveSprite().getHeight());
			setIsOnGround(true);
		}

		//check right wall
		if (this.getPosX() >= 800 - this.getActiveSprite().getWidth() ) {
			this.setPosX(800 - this.getActiveSprite().getWidth());	
		}
		
		//check left wall
		if (this.getPosX() <= 0) {
			this.setPosX(0);
		}
		
		// check roof
		if (this.getPosY() <= 0 + 20) {
			this.setPosY(0 + 20);
			
			if(Game.getPlayer().getGravity() < 0)
				setIsOnGround(true);
			else
			{
				jump = false;
				jumpTime = 0;
			}
		}
	}
	
	public static Player getPlayer()
	{
		for (Actor actor : actors) 
		{
			if(actor instanceof Player)
				return (Player)actor;
		}
		return null;
	}

	public void draw(Graphics g, ImageObserver observer)
	{
		this.getActiveSprite().draw(g, observer, (int)this.getPosX(), (int) this.getPosY());
	}
}