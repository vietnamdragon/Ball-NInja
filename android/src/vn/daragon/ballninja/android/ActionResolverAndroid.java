package vn.daragon.ballninja.android;

import vn.daragon.ballninja.ActionResolver;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ActionResolverAndroid implements ActionResolver{

	Handler handler;
    Context context;

    public ActionResolverAndroid(Context context) {
        handler = new Handler();
        this.context = context;
    }

    public void showToast(final CharSequence text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
