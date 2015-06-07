package com.anhld.screens;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import vn.daragon.ballninja.GameBase;
import vn.daragon.ballninja.Stores;

import com.anhld.customviewport.MultipleVirtualViewportBuilder;
import com.anhld.customviewport.OrthographicCameraWithVirtualViewport;
import com.anhld.customviewport.VirtualViewport;
import com.anhld.object.BackGround;
import com.anhld.object.Ball;
import com.anhld.object.BallManager;
import com.anhld.object.BtnControl;
import com.anhld.object.Laser;
import com.anhld.object.Ninja;
import com.anhld.util.CameraHelper;
import com.anhld.util.Constants;
import com.anhld.util.GamePreferences;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.CountdownEventAction;

//do not implement back key
public class GameScreen extends AbstractGameScreen implements InputProcessor {
	String tag = GameScreen.class.getName();
	private boolean paused;
	private VirtualViewport virtualViewport = null;
	private MultipleVirtualViewportBuilder multipleVirtualViewportBuilder;
	private OrthographicCameraWithVirtualViewport camera;
	private SpriteBatch batch;
	private CameraHelper cameraHelper;
	private InputMultiplexer multiplexer = new InputMultiplexer();
	private static GameScreen instance = null;
	private int ballSize = 10;
	private Set<String> ballsActive = new CopyOnWriteArraySet<String>();
	Ninja ninja = Ninja.getInstance();
	BackGround bg = BackGround.getInstance();
	BtnControl control = BtnControl.getInstance();
	Laser laser = new Laser();
	
	private void init() {
		batch = new SpriteBatch();
		multipleVirtualViewportBuilder = new MultipleVirtualViewportBuilder(
				Constants.MIN_SCENE_WIDTH, Constants.MIN_SCENE_HEIGHT,
				Constants.MAX_SCENE_WIDTH, Constants.MAX_SCENE_HEIGHT);
		virtualViewport = multipleVirtualViewportBuilder.getVirtualViewport(
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera = new OrthographicCameraWithVirtualViewport(virtualViewport);
		camera.position.set(0, 0f, 0f);
		cameraHelper = new CameraHelper();
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(control);
	}

	private GameScreen() {
		init();
	}

	public synchronized static GameScreen getInstance() {
		if (instance == null) {
			instance = new GameScreen();
		}
		return instance;
	}
	
	@Override
	public void render(float deltaTime) {
		// TODO Auto-generated method stub
		if (!paused) {
			// update logic for this screen
			Gdx.input.setInputProcessor(multiplexer);
			if(ballsActive.size() > 0){
				for (String ball : ballsActive) {
					BallManager.getInstance().getBallByKey(ball).update(deltaTime);;
				}
			}else {
				nextLevel();
			}
			ninja.update(deltaTime);
			laser.update(deltaTime);
			// System.out.println(ball.velocity.y);
		}
		// Sets the clear screen color to: Cornflower Blue
		Gdx.gl.glClearColor(126 / 255.0f, 192 / 255.0f, 238 / 255.0f, 1);
		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Render for this screen
		if (batch != null) {
			cameraHelper.applyTo(camera);
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();

			// your code here
			if (bg != null)
				bg.render(batch);
			control.render(batch);
			ninja.render(batch);
			if(ballsActive.size() > 0){
				for (String ball : ballsActive) {
					BallManager.getInstance().getBallByKey(ball).render(batch);
				}
			}
			laser.render(batch);
			// your code here
			batch.end();

		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		virtualViewport = multipleVirtualViewportBuilder.getVirtualViewport(
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setVirtualViewport(virtualViewport);
		camera.updateViewport();
		camera.position.set(0f, 0f, 0f);
	}

	@Override
	public void show() {
		GamePreferences.instance.load();
		Gdx.app.debug("Menu", " show");
		// bg = BackGround.getInstance();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		// worldRenderer.dispose();
		// dispose here
		Gdx.app.debug("Menu", " hide");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		paused = true;
		Gdx.app.debug("Menu", " pause");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
		paused = false;
		Gdx.app.debug("Menu", " resume");
		bg = BackGround.getInstance();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		batch.dispose();
		ninja = null;
		laser = null;
		if (instance != null)
			instance = null;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			Gdx.app.debug("test back", "test back");
			GameBase.getInstance().setScreen(MenuScreen.getInstance());
		}
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
		// TODO Auto-generated method stub
		// paused = true;
		// GameBase.getInstance().setScreen(MenuScreen.getInstance());

		Vector3 touchPos = new Vector3(screenX, screenY, 0);
		camera.unproject(touchPos);
		// Gdx.app.debug("touch: ", String.valueOf(touchPos.x + " " +
		// touchPos.y));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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

	public OrthographicCameraWithVirtualViewport getCamera() {
		return camera;
	}

	public VirtualViewport getVirtualViewport() {
		return virtualViewport;
	}

	public void UpdateVolecityNinja(int velocityX, int velocityY) {
		if (ninja.position.x < ninja.SCENE_WIDTH / 2 - ninja.getRegionWidth()
				&& velocityX >= 0)
			ninja.updateVelocity(new Vector2(velocityX, velocityY),
					new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
		if (ninja.position.x > -ninja.SCENE_WIDTH / 2 && velocityX <= 0)
			ninja.updateVelocity(new Vector2(velocityX, velocityY),
					new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
	}
	
	public void shootLase(){
		if(laser.isFinish()){
			laser.position.x = ninja.position.x + 8;
			laser.position.y = ninja.position.y;
			laser.setFinish(false);
		}
	}
	
	public Vector2 getPositionLaser(){
		if(!laser.isFinish())
			return new Vector2(laser.position.x, laser.position.y);
		else {
			return new Vector2(Float.MAX_VALUE, Float.MAX_VALUE);
		}
	}
	
	private void nextLevel(){
		ballSize += 10;
		ballsActive.add(BallManager.getInstance().getBall(ballSize).getKey());
	}
	
	public void removeBallActive(String key){
		if(ballsActive.contains(key))
			ballsActive.remove(key);
	}
	
	public void addBallActive(String key){
		ballsActive.add(key);
	}
}
