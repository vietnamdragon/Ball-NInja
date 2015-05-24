package com.anhld.util;

import com.badlogic.gdx.Gdx;

public class Constants {

	public static final float MIN_SCENE_WIDTH = 480.0f;
	public static final float MIN_SCENE_HEIGHT = 320.0f;
	public static final float MAX_SCENE_WIDTH = 720.0f;
	public static final float MAX_SCENE_HEIGHT = 1280.0f;

	// Location of description file for texture atlas
	public static final String TEXTURE_ATLAS_OBJECTS = "images/BallNinja.pack";

	public static final String TEXTURE_SPLASH_SCREEN = "images/splashScreen.png";

	public static final String FONTS_LOCATION = "fonts/arial-15.fnt";
	
	//game object
	public static final String NINJA = "ninja";
	public static final String BALL = "ball";
	
	public static final String CLOUDS1 = "clouds1";
	public static final String CLOUDS2 = "clouds2";
	
	public static final String HILL1 = "hills1";
	public static final String HILL2 = "hills2";
	
	public static final String MOUNTAIN1 = "mountain1";
	public static final String MOUNTAIN2 = "mountain2";
	public static final String MOUNTAIN3 = "mountain3";
	public static final String POINTY_MOUNTAINS = "pointy_mountains";
	
	public static final String TREE1 = "bush";

	public static final String GRASS = "grass";
	
	public static final String[] OBJECTS = new String[] {NINJA,BALL,CLOUDS1,CLOUDS2,HILL1,HILL2,MOUNTAIN1,MOUNTAIN2,MOUNTAIN3,POINTY_MOUNTAINS,TREE1,GRASS};
	// Game preferences file
	public static final String PREFERENCES = "gamebase.prefs";

	// sound
	public static String SOUND_NO = "soundno";
	public static String SOUND_YES = "soundyes";
	public static String BAGSOUND = "bagsound";
	public static String SOUNDFINISH = "soundfinish";
	public static final int SCENE_WIDTH = Gdx.graphics.getWidth();
	public static final int SCENE_HEIGHT = Gdx.graphics.getHeight();
}