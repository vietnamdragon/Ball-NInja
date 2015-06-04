package com.anhld.object;

import vn.daragon.ballninja.Stores;

import com.anhld.screens.GameScreen;
import com.anhld.util.CollisionUtil;
import com.anhld.util.Constants;
import com.anhld.util.MathUtil;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball extends AbstractGameObject{

	private AtlasRegion ball;
	private Sprite sBall;
	private boolean isBound = false; //cham vao bien hay chua ?
	private Vector2 nextTarget;
	private int STEP = 150;
	//he so phuong trinh bac 2
	float a,b,c;
	
	public Ball() {
		super();
		this.ball = Stores.getInstance().getObjectStores().get(Constants.BALL);
		if(MathUtils.random(0, 2) == 1)
			position.x = -SCENE_WIDTH /2;
		else {
			position.x = SCENE_WIDTH /2;
		}
		position.y = MathUtils.random(-SCENE_HEIGHT / 4, SCENE_HEIGHT / 4);
		sBall = new Sprite(ball);
		//ball.setRegionWidth(50);
		//ball.setRegionHeight(50);;
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(ball != null){
			sBall.setPosition(position.x, position.y);
			//batch.draw(ball, position.x, position.y);
			sBall.draw(batch);
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);
		sBall.rotate(5);
		if(CollisionUtil.isCollisionDetected(new Rectangle(position.x, position.y, ball.getRegionWidth(), ball.getRegionHeight()), Ninja.getInstance().getRect()))
			GameScreen.getInstance().pause();
		/*if(position.y <= 0)
			updateVelocity(new Vector2(0, 50), new Vector2(x, y));*/
		/*if(nextTarget != null)
			System.out.println("khoang cach: " + MathUtil.getDistance(position.x, position.y, nextTarget.x, nextTarget.y));*/
		
		/*if(MathUtil.getDistance(position.x, position.y, nextTarget.x, nextTarget.y) <= 1){
			attack();
		}*/
	}

	@Override
	protected void updateMotionX(float deltaTime) {
		// TODO Auto-generated method stub
		super.updateMotionX(deltaTime);
		if(position.x <= -SCENE_WIDTH / 2)
			position.x = -SCENE_WIDTH / 2;
		else if (position.x >= SCENE_WIDTH / 2 - ball.getRegionWidth()) {
			position.x = SCENE_WIDTH / 2 - ball.getRegionWidth();
		}
		if(position.x <= -SCENE_WIDTH / 2 || position.x >= SCENE_WIDTH / 2 - ball.getRegionWidth()){
			isBound = true;
			updateVelocity(new Vector2(0, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
			attack();
		}
	}

	@Override
	protected void updateMotionY(float deltaTime) {
		// TODO Auto-generated method stub
		super.updateMotionY(deltaTime);
		//position.y = (float) ((-2 * SCENE_HEIGHT * Math.pow(position.x, 2)) / (Math.pow(SCENE_WIDTH,2)));
		if(position.y < -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight())
			position.y = -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight();
		//neu chua cham vao bien thi update Y
		if(!isBound){
			position.y = a * position.x * position.x + b * position.x + c;
			if(position.y <= -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight()) //cham vao day
			{
				updateVelocity(new Vector2(0, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
				attack();
			}
		}
	}

	@Override
	public void updateVelocity(Vector2 velocity, Vector2 terminalVelocity) {
		// TODO Auto-generated method stub
		super.updateVelocity(velocity, terminalVelocity);
	}

	private void attack(){
		//can refactor lai doan nay
		if(isBound) //neu dang o bien thi tinh target dau tien va tinh quy dao chuyen dong
		{
			Vector2 ninjaPosition = Ninja.getInstance().getCurrentPosition();
			float distanceToTarget = MathUtil.getDistance(ninjaPosition.x, 0f, position.x, position.y);
			int numberOfStep = MathUtils.floor(distanceToTarget / Math.abs(STEP));
			float nextTargetX;
			if(position.x < 0){ //left
				STEP = MathUtils.random(130, 160);
				nextTargetX = -SCENE_WIDTH / 2 + (distanceToTarget - numberOfStep * Math.abs(STEP));
				nextTarget = new Vector2(nextTargetX, -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight());
				if(MathUtil.getDistance(position.x, position.y, nextTarget.x, nextTarget.y) < 130)
					nextTarget = new Vector2(nextTarget.x + STEP, -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight());
				//tinh quy dao chuyen dong
				tinhQuyDao(new Vector2(position.x, position.y), nextTarget);
				isBound = false; //cho phep chuyen dong theo quy dao moi
				
			}else {
				STEP =  -MathUtils.random(130, 160);;
				nextTargetX = SCENE_WIDTH / 2 - (distanceToTarget - numberOfStep * Math.abs(STEP));
				nextTarget = new Vector2(nextTargetX, -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight());
				if(MathUtil.getDistance(position.x, position.y, nextTarget.x, nextTarget.y) < 130)
					nextTarget = new Vector2(nextTarget.x + STEP, -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight());
				//tinh quy dao chuyen dong
				tinhQuyDao(new Vector2(position.x, position.y), nextTarget);
				isBound = false; //cho phep chuyen dong theo quy dao moi
				
			}
			//tinh next target
		}else{ //neu den target
			//tinh next tar get va tinh lai quy dao chuyen dong
			Vector2 currentTarget = new Vector2(nextTarget.x, nextTarget.y);
			nextTarget = new Vector2(nextTarget.x + STEP, nextTarget.y);
			System.out.println("next target = " + nextTarget.x + " " + nextTarget.y);
			tinhQuyDao(new Vector2(currentTarget.x, currentTarget.y), nextTarget);
			isBound = false; //cho phep chuyen dong theo quy dao moi
		}
		if(STEP > 0){
			updateVelocity(new Vector2(100, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
		}else {
			updateVelocity(new Vector2(-100, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
		}
	}
	
	private void tinhQuyDao(Vector2 A ,Vector2 B){
		
		//tinh toa do cua dinh
		float dinhX = (A.x + B.x) / 2;
		float dinhY = MathUtils.random(0, 50);
		Vector2 C = new Vector2(dinhX, dinhY);
		float[][] matrixD = new float[4][4];
		//nhap matrix D
		matrixD[1][1] = A.x * A.x;
		matrixD[1][2] = A.x;
		matrixD[1][3] = 1;
		matrixD[2][1] = B.x * B.x;
		matrixD[2][2] = B.x;
		matrixD[2][3] = 1;
		matrixD[3][1] = C.x * C.x;
		matrixD[3][2] = C.x;
		matrixD[3][3] = 1;
		float D = det(matrixD);
		if(D != 0){
			float[][] matrixDX = new float[4][4];
			
			matrixDX[1][1] = A.y;
			matrixDX[1][2] = A.x;
			matrixDX[1][3] = 1;
			matrixDX[2][1] = B.y;
			matrixDX[2][2] = B.x;
			matrixDX[2][3] = 1;
			matrixDX[3][1] = C.y;
			matrixDX[3][2] = C.x;
			matrixDX[3][3] = 1;
			
			float DX = det(matrixDX);
			float[][] matrixDY = new float[4][4];
			
			matrixDY[1][1] = A.x * A.x;
			matrixDY[1][2] = A.y;
			matrixDY[1][3] = 1;
			matrixDY[2][1] = B.x * B.x;
			matrixDY[2][2] = B.y;
			matrixDY[2][3] = 1;
			matrixDY[3][1] = C.x * C.x;
			matrixDY[3][2] = C.y;
			matrixDY[3][3] = 1;
			
			float DY = det(matrixDY);
			float[][] matrixDZ = new float[4][4];
			
			matrixDZ[1][1] = A.x * A.x;
			matrixDZ[1][2] = A.x;
			matrixDZ[1][3] = A.y;
			matrixDZ[2][1] = B.x * B.x;
			matrixDZ[2][2] = B.x;
			matrixDZ[2][3] = B.y;
			matrixDZ[3][1] = C.x * C.x;
			matrixDZ[3][2] = C.x;
			matrixDZ[3][3] = C.y;
			
			float DZ = det(matrixDZ);
			a = DX / D;
			b = DY / D;
			c = DZ / D;
			System.out.println("a " + a);
			System.out.println("b " + b);
			System.out.println("c " + c);
		}
		else {
			System.out.println("phuong trinh vo nghiem");
		}
	}
	
	//fuck math
	private float det(float[][] a)
	{
		return a[1][1] * a[2][2] * a[3][3] + a[1][2] * a[2][3] * a[3][1] + a[1][3] * a[2][1] * a[3][2] - a[1][3] * a[2][2] * a[3][1] - a[1][2] * a[2][1] * a[3][3] - a[1][1] * a[2][3] * a[3][2];
	}
}
