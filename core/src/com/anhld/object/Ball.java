package com.anhld.object;

import vn.daragon.ballninja.Stores;

import com.anhld.util.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;

public class Ball extends AbstractGameObject{

	private AtlasRegion ball;
	public Ball() {
		super();
		this.ball = Stores.getInstance().getObjectStores().get(Constants.BALL);
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(ball != null){
			batch.draw(ball, position.x, position.y);
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);
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
		position.y = (float) ((-2 * Constants.SCENE_HEIGHT * Math.pow(position.x, 2)) / (Math.pow(Constants.SCENE_WIDTH,2)));
		//System.out.println(position.y);
	}

	@Override
	public void updateVelocity(Vector2 velocity, Vector2 terminalVelocity) {
		// TODO Auto-generated method stub
		super.updateVelocity(velocity, terminalVelocity);
	}

	
}
