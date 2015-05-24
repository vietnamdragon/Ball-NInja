package vn.daragon.ballninja.desktop;

import vn.daragon.ballninja.GameBase;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 480;
		config.width = 800;
		new LwjglApplication(GameBase.getInstance(), config);
	}
}
