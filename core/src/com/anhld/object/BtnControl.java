package com.anhld.object;

import vn.daragon.ballninja.Stores;

import com.anhld.screens.GameScreen;
import com.anhld.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

public class BtnControl extends AbstractGameObject implements InputProcessor,Disposable{

	private AtlasRegion btnLeft;
	private AtlasRegion btnRight;
	private Vector2 leftPosition;
	private Vector2 rightPosition;
	private int power = 1;
	private AtlasRegion powerRed;
	private AtlasRegion powerGreen;
	private AtlasRegion powerBlue;
	private Vector2 powerPosition;
	private static BtnControl instance = null;
	private final String leftTouch = "LEFT_TOUCH";
	private final String rightTouch = "RIGHT_TOUCH";
	private final String powerTouch = "POWER_TOUCH";
	private Sprite sLeft;
	private Sprite sRight;
	private Sprite sPower;
	private BtnControl() {
		this.btnLeft = Stores.getInstance().getObjectStores().get(Constants.BTN_LEFT);
		this.btnRight = Stores.getInstance().getObjectStores().get(Constants.BTN_RIGHT);
		this.leftPosition = new Vector2(-SCENE_WIDTH / 2 , -SCENE_HEIGHT / 2 -15);
		this.rightPosition = new Vector2(-SCENE_WIDTH / 2 + (2 * btnLeft.getRegionWidth() / 2), -SCENE_HEIGHT / 2 -15);
		this.powerRed = Stores.getInstance().getObjectStores().get(Constants.BTN_POWER_RED);
		this.powerGreen = Stores.getInstance().getObjectStores().get(Constants.BTN_POWER_GREEN);
		this.powerBlue = Stores.getInstance().getObjectStores().get(Constants.BTN_POWER_BLUE);
		this.powerPosition = new Vector2(SCENE_WIDTH / 2 - 2 *  powerRed.getRegionWidth(), -SCENE_HEIGHT / 2 );
		sLeft = new Sprite(btnLeft);
		sRight = new Sprite(btnRight);
		sPower = new Sprite(powerRed);
		sLeft.setPosition(leftPosition.x, leftPosition.y);
		sRight.setPosition(rightPosition.x, rightPosition.y);
		sPower.setPosition(powerPosition.x, powerPosition.y);
	}
	
	public synchronized static BtnControl getInstance() {
		if (instance == null) {
			instance = new BtnControl();
		}
		return instance;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		//batch.draw(btnLeft, leftPosition.x, leftPosition.y);
		//batch.draw(btnRight, rightPosition.x , rightPosition.y);
		sLeft.draw(batch);
		sRight.draw(batch);
		sPower.draw(batch);
		/*if(power == 1)
			batch.draw(powerRed, powerPosition.x, powerPosition.y);
		else if (power == 2) {
			batch.draw(powerGreen, powerPosition.x, powerPosition.y);
		}else {
			batch.draw(powerBlue, powerPosition.x, powerPosition.y);
		}*/
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 tmp = new Vector3(screenX, screenY, 0.0f);
		GameScreen.getInstance().getCamera().unproject(tmp);
		bounds.set(leftPosition.x, leftPosition.y,
				btnLeft.getRegionWidth(), btnLeft.getRegionHeight());
		if (bounds.contains(tmp.x, tmp.y)) {
			Gdx.app.debug("left click", "left click");
			GameScreen.getInstance().UpdateVolecityNinja(-120, 0);
			sLeft.setScale(1.2f);
		}
		bounds.set(rightPosition.x, rightPosition.y,
				btnRight.getRegionWidth(), btnRight.getRegionHeight());
		if (bounds.contains(tmp.x, tmp.y)) {
			Gdx.app.debug("right click", "right click");
			GameScreen.getInstance().UpdateVolecityNinja(120, 0);
			sRight.setScale(1.2f);
		}
		bounds.set(powerPosition.x, powerPosition.y,
				powerRed.getRegionWidth(), powerRed.getRegionHeight());
		if (bounds.contains(tmp.x, tmp.y)) {
			Gdx.app.debug("power down", "power down");
			sPower.setScale(1.2f);
			GameScreen.getInstance().shootLase();
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		Vector3 tmp = new Vector3(screenX, screenY, 0.0f);
		GameScreen.getInstance().getCamera().unproject(tmp);
		bounds.set(leftPosition.x, leftPosition.y,
				btnLeft.getRegionWidth(), btnLeft.getRegionHeight());
		if (bounds.contains(tmp.x, tmp.y)) {
			GameScreen.getInstance().UpdateVolecityNinja(0, 0);
			
		}
		bounds.set(rightPosition.x, rightPosition.y,
				btnRight.getRegionWidth(), btnRight.getRegionHeight());
		if (bounds.contains(tmp.x, tmp.y)) {
			GameScreen.getInstance().UpdateVolecityNinja(0, 0);
		}
		sLeft.setScale(1);
		sRight.setScale(1);
		sPower.setScale(1);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispose() {
		if(instance != null)
			instance = null;
	}
	
	
}
