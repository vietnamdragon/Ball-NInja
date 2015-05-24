package vn.daragon.ballninja.android;

import vn.daragon.ballninja.GameBase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TableRow.LayoutParams;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication {
	
	private static final String AD_UNIT_ID = "ca-app-pub-1204239872276927/1634027491";
	protected AdView adView;
	protected View gameView;
	ActionResolverAndroid actionResolverAndroid;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		actionResolverAndroid = new ActionResolverAndroid(this);
		/*AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GameBase(actionResolverAndroid), config);*/
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		// Do the stuff that initialize() would do for you
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		RelativeLayout layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);
		AdView admobView = createAdView();
		layout.addView(admobView);
		View gameView = createGameView(actionResolverAndroid,config);
		layout.addView(gameView);
		setContentView(layout);
		startAdvertising(admobView);
        // Hook it all up
        setContentView(layout);
	}
	
	private AdView createAdView() {
		adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(AD_UNIT_ID);
		adView.setId(12345); // this is an arbitrary id, allows for relative
								// positioning in createGameView()
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		adView.setLayoutParams(params);
		adView.setBackgroundColor(Color.BLACK);
		return adView;
	}

	private View createGameView(ActionResolverAndroid actionResolverAndroid,AndroidApplicationConfiguration cfg) {
		GameBase.getInstance().setActionResolver(actionResolverAndroid);;
		gameView = initializeForView(GameBase.getInstance(), cfg);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.ABOVE, adView.getId());
		gameView.setLayoutParams(params);
		return gameView;
	}

	private void startAdvertising(AdView adView) {
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (adView != null) adView.pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (adView != null) adView.resume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (adView != null) adView.destroy();
		super.onDestroy();
	}
}
