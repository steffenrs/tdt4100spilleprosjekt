package spectrum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Player extends Actor
{
	//Physics
	private float gravity = 20f;
	private float jumpPower = -10f;
	private float minRestTime = 100;
	private float pJumpPower = 0;
	private float jumpTime = 0f;
	public float restTime = 0f;
	private boolean isOnGround = true;
	private boolean jump = false;
	private float moveSpeed = 3f;
	
	private Sprite playerSmall;
	private boolean isSmall;
	
	public void setIsSmall(boolean value){
		this.isSmall = value;
	}
	
	
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
		if(this.isOnGround == value)
			return;
		
		if(value)
		{
			jumpTime = 0f;
			jump = false;
			restTime = 0;
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
		if(isOnGround) //&& restTime > minRestTime)
		{
			this.jump = true;
			pJumpPower = jumpPower;
			this.setIsOnGround(false);
		}
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
		else
			restTime += Game.UPDATE_INTERVAL;

		//Gravity + jumppower
		if(jump)			
			this.setPosY(this.getPosY() + pJumpPower + gravity * jumpTime);
		//Only gravity
		else
			this.setPosY(this.getPosY() + gravity * jumpTime);

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
				
				if(Actor.intersects(this.getRectangle(), actor.getRectangle()) != null)
				{
					Rectangle r = Actor.intersects(this.getRectangle(), actor.getRectangle());
					
					 if(r.width > r.height)
					 {
						 if(this.getPosY() > actor.getPosY())
						 {
							 this.setPosY(this.getPosY() + r.height);
							 pJumpPower = 0;
							 jumpTime = 0;
						 }
						 else
						 {
							 this.setPosY(this.getPosY() - r.height);
							 setIsOnGround(true);
						 }
						 
					 }
					 else
					 {
						 if(this.getPosX() < actor.getPosX())
						 {
							 this.setPosX(this.getPosX() - r.width);

						 }
							 
						 else
						 {
							 this.setPosX(this.getPosX() + r.width);

						 }
					 }
                    return;
				}
			}
			
		}
		
		if(this.getPosY() > 800 - this.getSprite().getHeight())
		{
			this.setPosY(800 - this.getSprite().getHeight());
			
			setIsOnGround(true);
		}
		else
			setIsOnGround(false);
		
		
		
		
	}

	private void checkWallCollision() 
	{
		//check bottom
		if(this.getPosY() > 800 - this.getSprite().getHeight())
		{
			this.setPosY(800 - this.getSprite().getHeight());
			
				//setIsOnGround(true);
		}

		//check right wall
		if (this.getPosX() >= 800 - this.getSprite().getWidth() ) {
			this.setPosX(800 - this.getSprite().getWidth());	
		}
		
		//check left wall
		if (this.getPosX() <= 0) {
			this.setPosX(0);
		}
		
		// check roof
		if (this.getPosY() <= 0 + 20) {
			this.setPosY(0 + 20);
			jump = false;
			jumpTime = 0;
		}
	}

	public void draw(Graphics g, ImageObserver observer)
	{
		if(this.isSmall)
			playerSmall.draw(g, observer, (int)this.getPosX(), (int)this.getPosY());
		else
			this.getSprite().draw(g, observer, (int)this.getPosX(), (int) this.getPosY());
	}
}