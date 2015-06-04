package com.anhld.object;

import vn.daragon.ballninja.Stores;

import com.anhld.util.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Ninja extends AbstractGameObject implements Disposable{

	private AtlasRegion ninja;
	private int regionWidth;
	private static Ninja instance = null;
	
	public synchronized static Ninja getInstance(){
		if(instance == null){
			instance = new Ninja();
		}
		return instance;
	}
	
	private Ninja() {
		this.ninja = Stores.getInstance().getObjectStores().get(Constants.BLOCK);
		float ninjaPositionY = -SCENE_HEIGHT / 2 + Stores.getInstance().getObjectStores().get(Constants.GRASS).getRegionHeight();
		position.x = 0;
		position.y = ninjaPositionY;
		regionWidth = ninja.getRegionWidth();
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
		if(ninja != null){
			if(position.x >= SCENE_WIDTH /2 - ninja.getRegionWidth() || position.x <= -SCENE_WIDTH /2)
				updateVelocity(new Vector2(0, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
		}
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

	public int getRegionWidth() {
		return regionWidth;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		if(instance != null)
			instance = null;
	}

	public Vector2 getCurrentPosition() {
		
		return new Vector2(position.x + ninja.getRegionWidth() / 2, position.y);
	}
	
	public Rectangle getRect(){
		return new Rectangle(position.x, position.y, ninja.getRegionWidth(), ninja.getRegionHeight());
	}
}
