package com.anhld.object;

import vn.daragon.ballninja.Stores;

import com.anhld.util.Constants;
import com.anhld.util.MathUtil;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class BackGround extends AbstractGameObject{

	private static BackGround instance = null;
	private AtlasRegion hill1;
	private AtlasRegion hill2;
	private AtlasRegion pointyMountains;
	private AtlasRegion clouds;
	private AtlasRegion grass;
	private AtlasRegion tree1;
	
	private int numberOfHill = 1;
	private int numberOfGrass = 0;
	
	public synchronized static BackGround getInstance() {
		if (instance == null) {
			instance = new BackGround();
		}
		return instance;
	}
	
	private BackGround() {
		super();
		
		this.hill1 = Stores.getInstance().getObjectStores().get(Constants.HILL1);
		this.hill2 = Stores.getInstance().getObjectStores().get(Constants.HILL2);
		this.pointyMountains = Stores.getInstance().getObjectStores().get(Constants.POINTY_MOUNTAINS);
		this.clouds = Stores.getInstance().getObjectStores().get(Constants.CLOUDS1);
		this.grass = Stores.getInstance().getObjectStores().get(Constants.GRASS);
		this.tree1 = Stores.getInstance().getObjectStores().get(Constants.TREE1);
		
		for (int i = 1; i < 10; i++) {
			if(i * hill1.getRegionWidth() > SCENE_WIDTH){
				numberOfHill = i;
				break;
			}
		}
		for (int i = 0; i < 500; i++) {
			if(i * grass.getRegionWidth() > SCENE_WIDTH){
				numberOfGrass = i;
				break;
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		
		renderHill(batch);
	}
	public static void main(String[] args) {
		System.out.println(Math.floor(2048 / 1001));
	}
	private void renderHill(SpriteBatch batch){
		/*TiledDrawable title1 = new TiledDrawable(hill1);
		title1.draw(batch, (-SCENE_WIDTH / 2 - 3) , -SCENE_HEIGHT / 6, SCENE_WIDTH,hill1.getRegionHeight());*/
		//Gdx.app.debug("hill ", String.valueOf(hill1.getRegionWidth()));
		Color tempColor = batch.getColor();
		batch.setColor(MathUtil.convertR(226),MathUtil.convertG(237) , MathUtil.convertB(255), 1);
		float hillPositionY = -SCENE_HEIGHT / 2 + grass.getRegionHeight() - 10;
		for (int i = 0; i < numberOfHill; i++) {
			if(i == 0)
				batch.draw(clouds, (-SCENE_WIDTH / 2) , hillPositionY + 90);
			else {
				batch.draw(clouds, (-SCENE_WIDTH / 2) + (i * clouds.getRegionWidth()) -3 , hillPositionY + 90);
			}
		}
		batch.setColor(MathUtil.convertR(213),MathUtil.convertG(237) , MathUtil.convertB(247), 1);
		for (int i = 0; i < numberOfHill; i++) {
			if(i == 0)
				batch.draw(pointyMountains, (-SCENE_WIDTH / 2) - 30, hillPositionY + 50);
			else {
				batch.draw(pointyMountains, ((-SCENE_WIDTH / 2) - 30) + (pointyMountains.getRegionWidth() * i) -3, hillPositionY + 50);
			}
		}
		
		batch.setColor(0.72f, 0.82f, 0.65f, 1);
		
		for (int i = 0; i < numberOfHill; i++) {
			if(i == 0){
				//Gdx.app.debug("hill ", "draw");
				batch.draw(hill1, (-SCENE_WIDTH / 2) , hillPositionY);
			}
			else {
				batch.draw(hill1, (-SCENE_WIDTH / 2) + (hill1.getRegionWidth() * i) -3, hillPositionY);
			}
		}
		//System.out.println(batch.getColor().r + " " + batch.getColor().g + " " + batch.getColor().b + " " + batch.getColor().a);
		batch.setColor(1, 1, 1, 1);
		
		for (int i = 0; i < numberOfGrass; i++) {
			if(i == 0)
				batch.draw(grass, (-SCENE_WIDTH / 2) , -SCENE_HEIGHT / 2 );
			else {
				batch.draw(grass, (-SCENE_WIDTH / 2) + (i * grass.getRegionWidth()), -SCENE_HEIGHT / 2);
			}
		}
		
		batch.draw(tree1, SCENE_WIDTH / 2 - 2 * tree1.getRegionWidth() - 50 , hillPositionY + 10);
		batch.draw(tree1, (-SCENE_WIDTH / 2) + tree1.getRegionWidth(), hillPositionY + 10);
		batch.draw(tree1, -SCENE_WIDTH / 2 + tree1.getRegionWidth() + 10, hillPositionY + 10);
		batch.setColor(tempColor);
	}
	
	public void dispose(){
		if(instance != null)
			instance = null;
	}
	
	public float getGrassHeight(){
		return grass.getRegionHeight();
	}
}
