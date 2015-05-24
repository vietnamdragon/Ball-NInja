package vn.daragon.ballninja;

import com.anhld.object.Font;
import com.anhld.screens.DirectedGame;
import com.anhld.screens.MenuScreen;
import com.anhld.util.Constants;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameBase extends DirectedGame {
	
	private static AssetManager assetManager;
	private static Texture splashScreen;
	private static SpriteBatch batch;
	private Boolean loadToStore = false;
	private BitmapFont defaultSmall;
	private BitmapFont defaultNormal;
	private BitmapFont defaultBig;
	static ActionResolver actionResolver;
	private static GameBase instance = null;
	public synchronized static GameBase getInstance(){
		if(instance == null){
			instance = new GameBase();
		}
		return instance;
	}
	private GameBase(){
		
	}
	public void setActionResolver(ActionResolver actionResolver){
		this.actionResolver = actionResolver;
	}
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		assetManager.load(Constants.TEXTURE_SPLASH_SCREEN, Texture.class);
		assetManager.finishLoading(); // Blocks until all resources are loaded into memory
		splashScreen = assetManager.get(Constants.TEXTURE_SPLASH_SCREEN);
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		// load sounds
		assetManager.load("sounds/soundyes.wav", Sound.class);
		assetManager.load("sounds/soundno.wav", Sound.class);
		assetManager.load("sounds/bagsound.wav", Sound.class);
		assetManager.load("sounds/soundfinish.wav", Sound.class);
		defaultSmall = new BitmapFont(Gdx.files.internal(Constants.FONTS_LOCATION), false);
		defaultNormal = new BitmapFont(Gdx.files.internal(Constants.FONTS_LOCATION), false);
		defaultBig = new BitmapFont(Gdx.files.internal(Constants.FONTS_LOCATION), false);
		// set font sizes
		//defaultSmall.setScale(0.75f);
		//defaultNormal.setScale(1.0f);
		//defaultBig.setScale(2.0f);
		// enable linear texture filtering for smooth fonts
		defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(255.0f, 255.0f, 255.0f, 255.0f);
		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(splashScreen, 0 , 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		
		if(assetManager.update() && !loadToStore){ //done load data
			Gdx.app.debug("test", "load data");
			TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

			// enable texture filtering for pixel smoothing
			for (Texture t : atlas.getTextures()) {
				t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			}
			Stores.getInstance().Update(atlas);
			Stores.getInstance().Update(assetManager.get("sounds/soundyes.wav", Sound.class), Constants.SOUND_YES);
			Stores.getInstance().Update(assetManager.get("sounds/soundno.wav", Sound.class), Constants.SOUND_NO);
			Stores.getInstance().Update(assetManager.get("sounds/bagsound.wav", Sound.class), Constants.BAGSOUND);
			Stores.getInstance().Update(assetManager.get("sounds/soundfinish.wav", Sound.class), Constants.SOUNDFINISH);
			Stores.getInstance().Update(new Font(defaultSmall, defaultNormal, defaultBig));
			loadToStore = true;
			//setScreen(new GameScreen());
			//MenuScreen.getInstance().setDirectedGame(this);
			setScreen(MenuScreen.getInstance());
		}
		super.render();
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		assetManager.dispose();
		instance = null;
		Gdx.app.debug("asset dispose", "asset dispose");
		splashScreen.dispose();
		assetManager.dispose();
		super.dispose();
	}
	
	public static void ShowMessage(String msg){
		actionResolver.showToast(msg);
	}
}
