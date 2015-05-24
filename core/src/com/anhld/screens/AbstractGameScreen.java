/*******************************************************************************
 * Copyright 2013 Andreas Oehlke
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package com.anhld.screens;

import vn.daragon.ballninja.Stores;

import com.badlogic.gdx.Screen;

public abstract class AbstractGameScreen implements Screen {

	protected DirectedGame game;
	
	//public abstract InputProcessor getInputProcessor ();

	/*public AbstractGameScreen (DirectedGame game) {
		this.game = game;
	}*/
	public void setDirectedGame(DirectedGame game){
		this.game = game;
	}
	public abstract void render (float deltaTime);

	public abstract void resize (int width, int height);

	public abstract void show ();

	public abstract void hide ();

	public abstract void pause ();

	public void resume () {
		//Assets.instance.init(new AssetManager());
	}

	public void dispose () {
		//Assets.instance.dispose();
		Stores.getInstance().dispose();
	}

}
