package com.anhld.object;

import vn.daragon.ballninja.Stores;

import com.anhld.util.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;

public class Ball extends AbstractGameObject{

	private AtlasRegion ball;
	private Sprite sBall;
	public Ball() {
		super();
		this.ball = Stores.getInstance().getObjectStores().get(Constants.BALL);
		position.x = -SCENE_WIDTH /2;
		position.y = -SCENE_HEIGHT /2;
		sBall = new Sprite(ball);
		//ball.setRegionWidth(50);
		//ball.setRegionHeight(50);;
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(ball != null){
			sBall.setPosition(position.x, position.y);
			//batch.draw(ball, position.x, position.y);
			sBall.draw(batch);
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);
		sBall.rotate(5);
		/*if(position.y <= 0)
			updateVelocity(new Vector2(0, 50), new Vector2(x, y));*/
	}

	@Override
	protected void updateMotionX(float deltaTime) {
		// TODO Auto-generated method stub
		super.updateMotionX(deltaTime);
		//System.out.println(position.x);
	}

	@Override
	protected void updateMotionY(float deltaTime) {
		// TODO Auto-generated method stub
		super.updateMotionY(deltaTime);
		position.y = (float) ((-2 * SCENE_HEIGHT * Math.pow(position.x, 2)) / (Math.pow(SCENE_WIDTH,2)));
		//System.out.println(position.y);
	}

	@Override
	public void updateVelocity(Vector2 velocity, Vector2 terminalVelocity) {
		// TODO Auto-generated method stub
		super.updateVelocity(velocity, terminalVelocity);
	}

	
}
