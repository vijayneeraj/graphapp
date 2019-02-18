package mobileapp.leadgraph.com.leadgraph;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import android.widget.RelativeLayout;

import mobileapp.leadgraph.com.leadgraph.utils.ActivitySplitAnimationUtil;
import mobileapp.leadgraph.com.leadgraph.utils.PermissionHelperNew;
import mobileapp.leadgraph.com.leadgraph.utils.SPUtils;

/**
 * @author neeraj on 5/12/18.
 */
public class SplashActivity extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        PermissionHelperNew.requestingPermissionFromSetting = false;
                if (!PermissionHelperNew.checkAllPermissionGranted(SplashActivity.this)) {
                    PermissionHelperNew.needPermissions(SplashActivity.this);
                } else {
                    StartAnimations();
                }
    }

    @Override
    protected void onStop() {
        // If we're currently running the entrance animation - cancel it
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionHelperNew.onRequestPermissionsResult(this,
                requestCode, permissions, grantResults)) {
            if (PermissionHelperNew.checkAllPermissionGranted(this)) {
                StartAnimations();
            } else {
                PermissionHelperNew.needPermissions(this);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PermissionHelperNew.requestingPermissionFromSetting) {
            PermissionHelperNew.requestingPermissionFromSetting = false;
            if (PermissionHelperNew.checkAllPermissionGranted(this)) {
                StartAnimations();
            }
        } else {
            if (PermissionHelperNew.checkAllPermissionGranted(this)) {
                StartAnimations();
            }
        }
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l = (RelativeLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.clearAnimation();
        iv.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                //start here login screen
                if (SPUtils.getLogin(SplashActivity.this)){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
