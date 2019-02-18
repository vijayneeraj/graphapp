package mobileapp.leadgraph.com.leadgraph;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mobileapp.leadgraph.com.leadgraph.utils.ActivitySplitAnimationUtil;

/**
 * @author neeraj on 7/12/18.
 */
public class WalkThroughScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_one);
        ActivitySplitAnimationUtil.startActivity(WalkThroughScreen.this, new Intent(WalkThroughScreen.this, SplashActivity.class));

    }
}
