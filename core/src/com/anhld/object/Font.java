package com.anhld.object;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Font {

	private BitmapFont defaultSmall;
	private BitmapFont defaultNormal;
	private BitmapFont defaultBig;
	public Font(BitmapFont defaultSmall, BitmapFont defaultNormal,
			BitmapFont defaultBig) {
		super();
		this.defaultSmall = defaultSmall;
		this.defaultNormal = defaultNormal;
		this.defaultBig = defaultBig;
	}
	public BitmapFont getDefaultSmall() {
		return defaultSmall;
	}
	public BitmapFont getDefaultNormal() {
		return defaultNormal;
	}
	public BitmapFont getDefaultBig() {
		return defaultBig;
	}
	
}
