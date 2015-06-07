package com.anhld.object;

import java.util.UUID;

import vn.daragon.ballninja.Stores;

import com.anhld.screens.GameScreen;
import com.anhld.util.CollisionUtil;
import com.anhld.util.Constants;
import com.anhld.util.MathUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball extends AbstractGameObject{

	private final String tag = Ball.class.getName();
	private AtlasRegion ball;
	private Sprite sBall;
	private boolean isBound = false; //cham vao bien hay chua ?
	private Vector2 nextTarget;
	private int STEP = 150;
	private boolean isActive = false;
	private int size;
	private String key;
	//he so phuong trinh bac 2
	float a,b,c;
	public Ball() {
		super();
	}
	public Ball(int size) {
		super();
		key = UUID.randomUUID().toString();
		init(size);
	}
	public void init(int size) { //khoi tao ball o 2 bien
		this.size = size;
		this.ball = Stores.getInstance().getObjectStores().get(Constants.BALL_BLUE + size);
		if(MathUtils.random(0, 2) == 1)
			position.x = -SCENE_WIDTH /2;
		else {
			position.x = SCENE_WIDTH /2;
		}
		position.y = MathUtils.random(-SCENE_HEIGHT / 4, SCENE_HEIGHT / 4);
		sBall = new Sprite(ball);
	}
	public Ball(int size, float positionX, float positionY, boolean isToleft) {
		super();
		key = UUID.randomUUID().toString();
		init(size, positionX, positionY, isToleft);
	}
	
	public void init(int size, float positionX, float positionY, boolean isToleft){ //khoi tao ball o vi tri bat ky
		this.size = size;
		this.ball = Stores.getInstance().getObjectStores().get(Constants.BALL_BLUE + size);
		position.x = positionX;
		position.y = positionY;
		sBall = new Sprite(ball);
		if(isToleft){ //go to left
			STEP =  -MathUtils.random(130, 160);
		}else {
			STEP =  MathUtils.random(130, 160);
		}
		Vector2 currentTarget = new Vector2(positionX - STEP / 2, -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight());
		nextTarget = new Vector2(positionX + STEP / 2, -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight());
		tinhQuyDao(new Vector2(currentTarget.x, currentTarget.y), nextTarget, new Vector2(positionX, positionY), true);
		if(STEP > 0){
			updateVelocity(new Vector2(100, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
		}else {
			updateVelocity(new Vector2(-100, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(ball != null && isActive){
			sBall.setPosition(position.x, position.y);
			sBall.draw(batch);
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);
		if(isActive){
			if(CollisionUtil.isCollisionDetected(new Rectangle(position.x, position.y, ball.getRegionWidth(), ball.getRegionHeight()), Ninja.getInstance().getRect()))
				GameScreen.getInstance().pause();
			//cham vao laser
			if(Math.abs(GameScreen.getInstance().getPositionLaser().x - position.x) <= ball.getRegionWidth() && position.x <= GameScreen.getInstance().getPositionLaser().x)
			{
				isActive = false;
				if(size > 10){ //chia lam 2 phan
					Ball ball1 = BallManager.getInstance().getBall(size - 10);
					ball1.init(size - 10, position.x - 50, position.y, true);
					Ball ball2 = BallManager.getInstance().getBall(size - 10);
					ball2.init(size - 10, position.x + 50, position.y, false);
					GameScreen.getInstance().addBallActive(ball1.getKey());
					GameScreen.getInstance().addBallActive(ball2.getKey());
					GameScreen.getInstance().removeBallActive(key);
				}
				GameScreen.getInstance().removeBallActive(key);
			}
		}else {
			position.x = Float.MAX_VALUE;
			position.y = Float.MAX_VALUE;
		}
	}

	@Override
	protected void updateMotionX(float deltaTime) {
		// TODO Auto-generated method stub
		super.updateMotionX(deltaTime);
		if(isActive){
						
			if(position.x <= -SCENE_WIDTH / 2){
				
				position.x = -SCENE_WIDTH / 2;
				isBound = true;
				updateVelocity(new Vector2(0, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
				attack();
			}
			if(position.x >= SCENE_WIDTH / 2 - ball.getRegionWidth()){
				
				position.x = SCENE_WIDTH / 2 - ball.getRegionWidth();
				isBound = true;
				updateVelocity(new Vector2(0, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
				attack();
			}
		}
	}

	@Override
	protected void updateMotionY(float deltaTime) {
		// TODO Auto-generated method stub
		super.updateMotionY(deltaTime);
		if(isActive){
			//position.y = (float) ((-2 * SCENE_HEIGHT * Math.pow(position.x, 2)) / (Math.pow(SCENE_WIDTH,2)));
			if(position.y <= -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight()){
				position.y = -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight();
			}
				
			//neu chua cham vao bien thi update Y
			if(!isBound){
				position.y = a * position.x * position.x + b * position.x + c;
				if(position.y <= -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight()) //cham vao day
				{
					position.y = -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight();
					updateVelocity(new Vector2(0, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
					attack();
				}
			}
		}
	}

	@Override
	public void updateVelocity(Vector2 velocity, Vector2 terminalVelocity) {
		// TODO Auto-generated method stub
		super.updateVelocity(velocity, terminalVelocity);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getSize() {
		return size;
	}

	public String getKey() {
		return key;
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
				STEP = MathUtils.random(130, 140);
				nextTargetX = -SCENE_WIDTH / 2 + (distanceToTarget - numberOfStep * Math.abs(STEP));
				nextTarget = new Vector2(nextTargetX, -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight());
				if(Math.abs(Math.abs(nextTargetX) - Math.abs(position.x)) < 2 * Math.abs(STEP) / 3)
					nextTarget = new Vector2(nextTarget.x + STEP, -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight());
				//tinh quy dao chuyen dong
				tinhQuyDao(new Vector2(position.x, position.y), nextTarget, new Vector2(), false);
				isBound = false; //cho phep chuyen dong theo quy dao moi
				
			}else {
				STEP =  -MathUtils.random(130, 140);;
				nextTargetX = SCENE_WIDTH / 2 - (distanceToTarget - numberOfStep * Math.abs(STEP));
				nextTarget = new Vector2(nextTargetX, -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight());
				if(Math.abs(Math.abs(nextTargetX) - Math.abs(position.x)) < 2 * Math.abs(STEP) / 3)
					nextTarget = new Vector2(nextTarget.x + STEP, -SCENE_HEIGHT / 2 + BackGround.getInstance().getGrassHeight());
				//tinh quy dao chuyen dong
				tinhQuyDao(new Vector2(position.x, position.y), nextTarget, new Vector2(), false);
				isBound = false; //cho phep chuyen dong theo quy dao moi
				
			}
			if(STEP > 0){
				updateVelocity(new Vector2(130, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
			}else {
				updateVelocity(new Vector2(-130, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
			}
			//tinh next target
		}else{ //neu den target
			//tinh next tar get va tinh lai quy dao chuyen dong
			Vector2 currentTarget = new Vector2(nextTarget.x, nextTarget.y);
			nextTarget = new Vector2(nextTarget.x + STEP, nextTarget.y);
			
			System.out.println("next target = " + nextTarget.x + " " + nextTarget.y);
			tinhQuyDao(new Vector2(currentTarget.x, currentTarget.y), nextTarget, new Vector2(), false);
			isBound = false; //cho phep chuyen dong theo quy dao moi
			if(STEP > 0){
				updateVelocity(new Vector2(100, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
			}else {
				updateVelocity(new Vector2(-100, 0), new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
			}
		}
		
	}
	
	private void tinhQuyDao(Vector2 A ,Vector2 B, Vector2 Dinh, boolean hasDinh){
		
		Gdx.app.debug(tag, " do rong parabol " + String.valueOf(MathUtil.getDistance(A.x, A.y, B.x, B.y)));
		//tinh toa do cua dinh
		float dinhX = (A.x + B.x) / 2;
		float dinhY = MathUtils.random(0, 50);
		Vector2 C;
		if(!hasDinh)
			C = new Vector2(dinhX, dinhY);
		else {
			C = Dinh;
		}
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
			Gdx.app.debug(tag, "phuong trinh vo nghiem");
			Gdx.app.debug(tag, A.toString());
			Gdx.app.debug(tag, B.toString());
		}
	}
	
	//fuck math
	private float det(float[][] a)
	{
		return a[1][1] * a[2][2] * a[3][3] + a[1][2] * a[2][3] * a[3][1] + a[1][3] * a[2][1] * a[3][2] - a[1][3] * a[2][2] * a[3][1] - a[1][2] * a[2][1] * a[3][3] - a[1][1] * a[2][3] * a[3][2];
	}
}
