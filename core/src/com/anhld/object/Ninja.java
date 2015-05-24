package com.anhld.object;

import vn.daragon.ballninja.Stores;

import com.anhld.util.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;

public class Ninja extends AbstractGameObject{

	private AtlasRegion ninja;
	public Ninja() {
		super();
		this.ninja = Stores.getInstance().getObjectStores().get(Constants.NINJA);
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(ninja != null){
			batch.draw(ninja, position.x, position.y);
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);
	}

	@Override
	protected void updateMotionX(float deltaTime) {
		// TODO Auto-generated method stub
		super.updateMotionX(deltaTime);
	}

	@Override
	protected void updateMotionY(float deltaTime) {
		// TODO Auto-generated method stub
		super.updateMotionY(deltaTime);
	}

	@Override
	public void updateVelocity(Vector2 velocity, Vector2 terminalVelocity) {
		// TODO Auto-generated method stub
		super.updateVelocity(velocity, terminalVelocity);
	}

	
}
