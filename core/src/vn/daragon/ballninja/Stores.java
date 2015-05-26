package vn.daragon.ballninja;

import java.util.HashMap;

import com.anhld.object.Font;
import com.anhld.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

public class Stores implements Disposable{

	private static Stores instance = null;
	
	private String[] objects = Constants.OBJECTS;
	
	private HashMap<String, AtlasRegion> objectStores = new HashMap<String, TextureAtlas.AtlasRegion>();
	
	private HashMap<String, Sound> soundStores = new HashMap<String, Sound>();
	
	private Font font;
	
	private Stores(){
		
	}
	
	public synchronized static Stores getInstance(){
		if(instance == null){
			instance = new Stores();
		}
		return instance;
	}
	
	public void Update(TextureAtlas atlas){
		for (String name : objects) {
			objectStores.put(name, atlas.findRegion(name));
		}
	}
	
	public void Update(Sound sound,String name){
		soundStores.put(name, sound);
	}
	
	public void Update(Font font){
		this.font = font;
	}
	
	public HashMap<String, AtlasRegion> getObjectStores() {
		return objectStores;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		/*instance.defaultBig.dispose();
		instance.defaultNormal.dispose();
		instance.defaultNormal.dispose();
		instance.defaultSmall.dispose();
		instance.distanceFont.dispose();
		instance.fontShader.dispose();*/
		Gdx.app.debug("clear store", "clear store");
		objectStores.clear();
		if(instance != null)
			instance = null;
	}

	public Sound getSound(String name){
		if(soundStores.containsKey(name))
			return soundStores.get(name);
		return null;
	}

	public Font getFont() {
		return font;
	}
}
