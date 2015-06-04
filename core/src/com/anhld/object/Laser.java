package com.anhld.object;

import vn.daragon.ballninja.Stores;

import com.anhld.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Laser extends AbstractGameObject {

	float time;
	int totalLoop = 0;
	float alpha = 1f;
	boolean finish = false;
	AtlasRegion bgStart = Stores.getInstance().getObjectStores()
			.get(Constants.LASER_START2);
	AtlasRegion bgMid = Stores.getInstance().getObjectStores()
			.get(Constants.LASER);
	AtlasRegion bgEnd = Stores.getInstance().getObjectStores()
			.get(Constants.LASER_END6);
	AtlasRegion startOver = Stores.getInstance().getObjectStores()
			.get(Constants.LASER_STAR2OVER);
	AtlasRegion midOver = Stores.getInstance().getObjectStores()
			.get(Constants.LASER_OVERLAYSTATIC);
	AtlasRegion endOver = Stores.getInstance().getObjectStores()
			.get(Constants.LASER_END4OVERLAY);

	AtlasRegion animation = Stores.getInstance().getObjectStores()
			.get(Constants.LASER_OVERLAY01);
	int count = 0;
	public Laser() {
		super();
		this.time = 0.5f;
		totalLoop = (int) (time / Gdx.graphics.getDeltaTime());
		finish = true;
	}

	@Override
	public void render(SpriteBatch batch) {
		if(!finish){
			// for laser test
			float laserPositionY = -SCENE_HEIGHT
					/ 2
					+ Stores.getInstance().getObjectStores().get(Constants.GRASS)
							.getRegionHeight() + Stores.getInstance().getObjectStores().get(Constants.BLOCK).getRegionHeight() -5;
			int blendSrc = batch.getBlendSrcFunc();
			int blendDst = batch.getBlendDstFunc();
			batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
			Color startColor = batch.getColor();
			
			// bgMid.setRegionWidth(ball.SCENE_HEIGHT);
			// midOver.setRegionWidth(ball.SCENE_HEIGHT);
			Color white = Color.GREEN;
			count++;
			if (count == totalLoop)
			{
				alpha = 0;
				finish = true;
				position.x = Float.MAX_VALUE;
				position.y = Float.MAX_VALUE;
				count = 0;
			}
			else {
				alpha -= 1f / totalLoop;
			}
			white.a = alpha;
			// Color red = Color.GREEN;
			// red.a = alpha;
			int numOfMid = (SCENE_HEIGHT - Stores.getInstance().getObjectStores().get(Constants.GRASS).getRegionHeight() - 50) / bgStart.getRegionHeight();
			batch.setColor(white);
			batch.draw(bgStart, position.x, laserPositionY);
			for (int i = 1; i <= numOfMid; i++) {
				batch.draw(bgMid, position.x, laserPositionY + i * bgStart.getRegionHeight());
			}
			
			batch.draw(
					bgEnd,
					position.x,
					laserPositionY + bgStart.getRegionHeight()
							+ bgMid.getRegionHeight() * numOfMid);
			batch.setColor(white);
			batch.draw(startOver, position.x, laserPositionY);
			for (int i = 1; i < numOfMid; i++) {
				batch.draw(midOver, position.x, laserPositionY + bgStart.getRegionHeight() * i);
			}
			
			batch.draw(endOver, position.x, laserPositionY + bgStart.getRegionHeight()
					+ bgMid.getRegionHeight() * numOfMid);
			// animation
			batch.setColor(white);
			batch.draw(animation, position.x, laserPositionY + bgStart.getRegionHeight() / 2);
			for (int i = 1; i < numOfMid; i++) {
				batch.draw(animation, position.x, laserPositionY + bgMid.getRegionHeight() * i);
			}
			
			//batch.draw(animation, 0, laserPositionY + bgEnd.getRegionHeight() / 2);
			// reset
			batch.setColor(startColor);
			batch.setBlendFunction(blendSrc, blendDst);
			// for laser test
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);
	}

	public boolean isFinish() {
		return finish;
	}

	public void setTime(short time) {
		this.time = time;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

}
