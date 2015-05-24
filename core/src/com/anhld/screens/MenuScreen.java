package com.anhld.screens;

import vn.daragon.ballninja.GameBase;
import vn.daragon.ballninja.Stores;

import com.anhld.customviewport.MultipleVirtualViewportBuilder;
import com.anhld.customviewport.OrthographicCameraWithVirtualViewport;
import com.anhld.customviewport.VirtualViewport;
import com.anhld.object.BackGround;
import com.anhld.util.CameraHelper;
import com.anhld.util.Constants;
import com.anhld.util.GamePreferences;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;


public class MenuScreen extends AbstractGameScreen implements InputProcessor{
	
	private boolean paused;
	private VirtualViewport virtualViewport = null;
	private MultipleVirtualViewportBuilder multipleVirtualViewportBuilder;
	//private static final String TAG = WorldRenderer.class.getName();
	public static OrthographicCameraWithVirtualViewport camera;
	private SpriteBatch batch;
	private CameraHelper cameraHelper;
	private InputMultiplexer multiplexer = new InputMultiplexer();
	private static MenuScreen instance = null;
	private void init() {
		batch = new SpriteBatch();
		multipleVirtualViewportBuilder = new MultipleVirtualViewportBuilder(Constants.MIN_SCENE_WIDTH, Constants.MIN_SCENE_HEIGHT, Constants.MAX_SCENE_WIDTH, Constants.MAX_SCENE_HEIGHT);
		virtualViewport = multipleVirtualViewportBuilder.getVirtualViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera = new OrthographicCameraWithVirtualViewport(virtualViewport);
		camera.position.set(0f, 0f, 0f);
		cameraHelper = new CameraHelper();
		multiplexer.addProcessor(this);
		
	}
	private MenuScreen(){
		init();
	}
	
	public synchronized static MenuScreen getInstance(){
		if(instance == null){
			instance = new MenuScreen();
		}
		return instance;
	}

	@Override
	public void render(float deltaTime) {
		// TODO Auto-generated method stub
		if (!paused) {
			//update logic for this screen
			Gdx.input.setInputProcessor(multiplexer);
		}
		// Sets the clear screen color to: Cornflower Blue
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Render for this screen
		if(batch != null){
			cameraHelper.applyTo(camera);
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			//your code here
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		virtualViewport = multipleVirtualViewportBuilder.getVirtualViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setVirtualViewport(virtualViewport);
		camera.updateViewport();
		camera.position.set(0f, 0f, 0f);
	}

	@Override
	public void show() {
		GamePreferences.instance.load();
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		//worldRenderer.dispose();
		//dispose here
		Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		paused = true;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
		paused = false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		batch.dispose();
		instance = null;
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			GameBase.ShowMessage("Back again to exit!");
			Gdx.app.debug("menu back", "menu back");
			//dispose all singleton
			Stores.getInstance().dispose();
			GameBase.getInstance().dispose();;
			BackGround.getInstance().dispose();
			GameScreen.getInstance().dispose();
			Gdx.app.exit();
			Task wait5Second = new Task() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			};
			Timer.schedule(wait5Second, 10);
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
		//paused = true;
		GameBase.getInstance().setScreen(GameScreen.getInstance());
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

}
