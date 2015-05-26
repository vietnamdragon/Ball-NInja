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


package com.anhld.object;

import com.anhld.customviewport.MultipleVirtualViewportBuilder;
import com.anhld.customviewport.VirtualViewport;
import com.anhld.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractGameObject{

	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Vector2 scale;
	public float rotation;
	public Vector2 velocity;
	public Vector2 terminalVelocity;//van toc toi da
	public Vector2 friction;
	public Vector2 acceleration;
	public Rectangle bounds;
	public int SCENE_WIDTH;
	public int SCENE_HEIGHT;
	public AbstractGameObject () {
		position = new Vector2();
		dimension = new Vector2(1, 1);
		origin = new Vector2();
		scale = new Vector2(1, 1);
		rotation = 0;
		velocity = new Vector2();
		terminalVelocity = new Vector2(1, 1);
		friction = new Vector2();
		acceleration = new Vector2();
		bounds = new Rectangle();
		
		//VITUAL SCREEN 
		MultipleVirtualViewportBuilder multipleVirtualViewportBuilder = new MultipleVirtualViewportBuilder(
				Constants.MIN_SCENE_WIDTH, Constants.MIN_SCENE_HEIGHT,
				Constants.MAX_SCENE_WIDTH, Constants.MAX_SCENE_HEIGHT);
		VirtualViewport virtualViewport = multipleVirtualViewportBuilder.getVirtualViewport(
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		SCENE_WIDTH = (int) virtualViewport.getVirtualWidth();
		SCENE_HEIGHT = (int) virtualViewport.getVirtualHeight();
	}

	public void update (float deltaTime) {
		updateMotionX(deltaTime);
		updateMotionY(deltaTime);

		// Move to new position
		position.x += velocity.x * deltaTime;
		position.y += velocity.y * deltaTime;
	}

	protected void updateMotionX (float deltaTime) {
		if (velocity.x != 0) {
			// Apply friction
			if (velocity.x > 0) {
				velocity.x = Math.max(velocity.x - friction.x * deltaTime, 0);
			} else {
				velocity.x = Math.min(velocity.x + friction.x * deltaTime, 0);
			}
		}
		// Apply acceleration
		velocity.x += acceleration.x * deltaTime;
		// Make sure the object's velocity does not exceed the
		// positive or negative terminal velocity
		velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x, terminalVelocity.x);
	}

	protected void updateMotionY (float deltaTime) {
		if (velocity.y != 0) {
			// Apply friction
			if (velocity.y > 0) {
				velocity.y = Math.max(velocity.y - friction.y * deltaTime, 0);
			} else {
				velocity.y = Math.min(velocity.y + friction.y * deltaTime, 0);
			}
		}
		// Apply acceleration
		velocity.y += acceleration.y * deltaTime;
		// Make sure the object's velocity does not exceed the
		// positive or negative terminal velocity
		velocity.y = MathUtils.clamp(velocity.y, -terminalVelocity.y, terminalVelocity.y);
	}
	public void updateVelocity(Vector2 velocity,Vector2 terminalVelocity){
		this.velocity = velocity;
		this.terminalVelocity = terminalVelocity;
	}
	
	public void updateAcceleration(Vector2 a){
		this.acceleration = a;
	}
	
	public abstract void render (SpriteBatch batch);

}
