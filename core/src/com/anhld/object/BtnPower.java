package com.anhld.object;

import vn.daragon.ballninja.Stores;

import com.anhld.screens.GameScreen;
import com.anhld.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

public class BtnPower extends AbstractGameObject implements InputProcessor,Disposable{

	private AtlasRegion powerRed;
	private AtlasRegion powerGreen;
	private AtlasRegion powerBlue;
	private Vector2 powerPosition;
	private int power = 1;
	private static BtnPower instance = null;
	
	private BtnPower() {
		this.powerRed = Stores.getInstance().getObjectStores().get(Constants.BTN_POWER_RED);
		this.powerGreen = Stores.getInstance().getObjectStores().get(Constants.BTN_POWER_GREEN);
		this.powerBlue = Stores.getInstance().getObjectStores().get(Constants.BTN_POWER_BLUE);
		this.powerPosition = new Vector2(SCENE_WIDTH / 2 - 2 *  powerRed.getRegionWidth(), -SCENE_HEIGHT / 2 );
	}
	
	public synchronized static BtnPower getInstance() {
		if (instance == null) {
			instance = new BtnPower();
		}
		return instance;
	}
	
	@Override
	public void render(SpriteBatch batch) {

		if(power == 1)
			batch.draw(powerRed, powerPosition.x, powerPosition.y);
		else if (power == 2) {
			batch.draw(powerGreen, powerPosition.x, powerPosition.y);
		}else {
			batch.draw(powerBlue, powerPosition.x, powerPosition.y);
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 tmp = new Vector3(screenX, screenY, 0.0f);
		GameScreen.getInstance().getCamera().unproject(tmp);
		if(tmp.x > SCENE_WIDTH /2){
			bounds.set(powerPosition.x, powerPosition.y,
					powerRed.getRegionWidth(), powerRed.getRegionHeight());
			if (bounds.contains(tmp.x, tmp.y)) {
				Gdx.app.debug("power down", "power down");
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
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
