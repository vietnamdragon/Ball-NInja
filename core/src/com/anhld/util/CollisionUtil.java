package com.anhld.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class CollisionUtil {
	public static boolean isCollisionDetected(Rectangle rect1, Rectangle rect2){
	    
		if (rect1.x < rect2.x + rect2.width &&
				   rect1.x + rect1.width > rect2.x &&
				   rect1.y < rect2.y + rect2.height &&
				   rect1.height + rect1.y > rect2.y) {
				    // collision detected!
			return true;
		}
		return false;
	}
}
