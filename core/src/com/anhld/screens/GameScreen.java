package com.anhld.screens;

import vn.daragon.ballninja.GameBase;

import com.anhld.customviewport.MultipleVirtualViewportBuilder;
import com.anhld.customviewport.OrthographicCameraWithVirtualViewport;
import com.anhld.customviewport.VirtualViewport;
import com.anhld.object.BackGround;
import com.anhld.object.Ball;
import com.anhld.object.BtnControl;
import com.anhld.object.BtnPower;
import com.anhld.object.Ninja;
import com.anhld.util.CameraHelper;
import com.anhld.util.Constants;
import com.anhld.util.GamePreferences;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

//do not implement back key
public class GameScreen extends AbstractGameScreen implements InputProcessor {

	private boolean paused;
	private VirtualViewport virtualViewport = null;
	private MultipleVirtualViewportBuilder multipleVirtualViewportBuilder;
	private  OrthographicCameraWithVirtualViewport camera;
	private SpriteBatch batch;
	private CameraHelper cameraHelper;
	private InputMultiplexer multiplexer = new InputMultiplexer();
	private static GameScreen instance = null;
	
	Ball ball = new Ball();
	Ninja ninja = new Ninja();
	BackGround bg = BackGround.getInstance();
	BtnControl lr = BtnControl.getInstance();
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
		multiplexer.addProcessor(lr);

		ball.updateVelocity(new Vector2(50, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
		//ball.updateAcceleration(new Vector2(0, -9.8f));
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
			ball.update(deltaTime);
			ninja.update(deltaTime);
			//System.out.println(ball.velocity.y);
		}
		// Sets the clear screen color to: Cornflower Blue
		Gdx.gl.glClearColor(126 / 255.0f, 192 / 255.0f, 238 / 255.0f,1);
		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Render for this screen
		if (batch != null) {
			cameraHelper.applyTo(camera);
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			// your code here
			if(bg != null)
				bg.render(batch);
			lr.render(batch);
			ninja.render(batch);
			ball.render(batch);
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
		//bg = BackGround.getInstance();
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
		if(instance != null)
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
		//Gdx.app.debug("touch: ", String.valueOf(touchPos.x + " " + touchPos.y));
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
	
	public void UpdateVolecityNinja(int velocityX,int velocityY){
		ninja.updateVelocity(new Vector2(velocityX, velocityY), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
	}
}
