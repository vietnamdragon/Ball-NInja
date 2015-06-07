package com.anhld.object;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.utils.Disposable;


public class BallManager implements Disposable{

	private static BallManager instance = null;
	private Map<String, Ball> mBall10 = new HashMap<String, Ball>();
	private Map<String, Ball> mBall20 = new HashMap<String, Ball>();
	private Map<String, Ball> mBall30 = new HashMap<String, Ball>();
	private Map<String, Ball> mBall40 = new HashMap<String, Ball>();
	private Map<String, Ball> mBall50 = new HashMap<String, Ball>();
	private Map<String, Ball> mBall60 = new HashMap<String, Ball>();
	private Map<String, Ball> mBall70 = new HashMap<String, Ball>();
	private BallManager(){
		for (int i = 0; i < 10; i++) {
			
			Ball ball10 = new Ball(10);
			mBall10.put(ball10.getKey(), ball10);

			
			Ball ball20 = new Ball(20);
			mBall20.put(ball20.getKey(), ball20);
			
			Ball ball30 = new Ball(30);
			mBall30.put(ball30.getKey(), ball30);
			
			Ball ball40 = new Ball(40);
			mBall40.put(ball40.getKey(), ball40);
			
			Ball ball50 = new Ball(50);
			mBall50.put(ball50.getKey(), ball50);
			
			Ball ball60 = new Ball(60);
			mBall60.put(ball60.getKey(), ball60);
			
			Ball ball70 = new Ball(70);
			mBall70.put(ball70.getKey(), ball70);
		}
	}
	
	public synchronized static BallManager getInstance(){
		if(instance == null){
			instance = new BallManager();
		}
		return instance;
	}
	
	public Ball getBall(int size){
		if(size == 10){
			Set<String> keys = mBall10.keySet();
			for (String key : keys) {
				if(!mBall10.get(key).isActive()){
					mBall10.get(key).setActive(true);
					return mBall10.get(key);
				}
			}
			
			Ball ball10 = new Ball(10);
			ball10.setActive(true);
			mBall10.put(ball10.getKey(), ball10);
			keys.add(ball10.getKey());
			return ball10;
		}else if (size == 20) {
			Set<String> keys = mBall20.keySet();
			for (String key : keys) {
				if(!mBall20.get(key).isActive()){
					mBall20.get(key).setActive(true);
					return mBall20.get(key);
				}
			}
			
			Ball ball20 = new Ball(20);
			ball20.setActive(true);
			mBall20.put(ball20.getKey(), ball20);
			keys.add(ball20.getKey());
			return ball20;
		}else if (size == 30) {
			Set<String> keys = mBall30.keySet();
			for (String key : keys) {
				if(!mBall30.get(key).isActive()){
					mBall30.get(key).setActive(true);
					return mBall30.get(key);
				}
			}
			
			Ball ball30 = new Ball(30);
			ball30.setActive(true);
			mBall30.put(ball30.getKey(), ball30);
			keys.add(ball30.getKey());
			return ball30;
		}else if (size == 40) {
			Set<String> keys = mBall40.keySet();
			for (String key : keys) {
				if(!mBall40.get(key).isActive()){
					mBall40.get(key).setActive(true);
					return mBall40.get(key);
				}
			}
			
			Ball ball40 = new Ball(40);
			ball40.setActive(true);
			mBall40.put(ball40.getKey(), ball40);
			keys.add(ball40.getKey());
			return ball40;
		}else if (size == 50) {
			Set<String> keys = mBall50.keySet();
			for (String key : keys) {
				if(!mBall50.get(key).isActive()){
					mBall50.get(key).setActive(true);
					return mBall50.get(key);
				}
			}
			
			Ball ball50 = new Ball(50);
			ball50.setActive(true);
			mBall50.put(ball50.getKey(), ball50);
			keys.add(ball50.getKey());
			return ball50;
		}else if (size == 60) {
			Set<String> keys = mBall60.keySet();
			for (String key : keys) {
				if(!mBall60.get(key).isActive()){
					mBall60.get(key).setActive(true);
					return mBall60.get(key);
				}
			}
			
			Ball ball60 = new Ball(60);
			ball60.setActive(true);
			mBall60.put(ball60.getKey(), ball60);
			keys.add(ball60.getKey());
			return ball60;
		}else if (size == 70) {
			Set<String> keys = mBall70.keySet();
			for (String key : keys) {
				if(!mBall70.get(key).isActive()){
					mBall70.get(key).setActive(true);
					return mBall70.get(key);
				}
			}
			
			Ball ball70 = new Ball(70);
			ball70.setActive(true);
			mBall70.put(ball70.getKey(), ball70);
			keys.add(ball70.getKey());
			return ball70;
		}
		
		return null;
	}
	
	public Ball getBallByKey(String key){
		if(mBall10.keySet().contains(key))
			return mBall10.get(key);
		
		if(mBall20.keySet().contains(key))
			return mBall20.get(key);
		
		if(mBall30.keySet().contains(key))
			return mBall30.get(key);
		
		if(mBall40.keySet().contains(key))
			return mBall40.get(key);
		
		if(mBall40.keySet().contains(key))
			return mBall40.get(key);
		if(mBall50.keySet().contains(key))
			return mBall50.get(key);
		if(mBall60.keySet().contains(key))
			return mBall60.get(key);
		if(mBall70.keySet().contains(key))
			return mBall70.get(key);
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		if(instance != null)
			instance = null;
		mBall10.clear();
		mBall20.clear();
		mBall30.clear();
		mBall40.clear();
		mBall50.clear();
		mBall60.clear();
		mBall70.clear();
	}
}
