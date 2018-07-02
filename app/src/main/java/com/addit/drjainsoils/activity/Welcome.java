package com.addit.drjainsoils.activity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.addit.drjainsoils.R;
import com.addit.drjainsoils.utils.XAppCompatActivity;


public class Welcome extends XAppCompatActivity {
    private Thread splashTread;


    private int mBorderColor = Color.parseColor("#44f16d7a");
    private int mBorderWidth = 0;
    public Welcome() {
        super(R.layout.activity_welcome, 0);
    }

    @Override
    protected boolean getXmlControls() {



      /*  final WaveView waveView = (WaveView) findViewById(R.id.wave);
        waveView.setBorder(mBorderWidth, mBorderColor);
        waveView.setShapeType(WaveView.ShapeType.SQUARE);
        mBorderColor = Color.parseColor("#44f16d7a");
        waveView.setBorder(mBorderWidth, mBorderColor);
        mWaveHelper = new WaveHelper(waveView);
*/

        StartSplash();
        return true;
    }

    @Override
    protected void initXmlControls() throws Exception {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashTread = null;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    private void StartSplash() {
        splashTread = new Thread() {
            @Override
            public void run() {
                try {

/*                    ImageView iv=(ImageView)findViewById(R.id.imageview) ;
                    Animation fadeInAnimation = AnimationUtils.loadAnimation(Welcome.this, R.anim.splash_alpha);
                    iv.startAnimation(fadeInAnimation);*/

                    sleep(1000);
                    //boolean hasNewNotification = Welcome.this.getIntent().getBooleanExtra(GcmMessageListenerService.KEY_NEW_NOTIFICATION, true);
                  /* if(hasNewNotification)
                       showToast("true");*/
                    //String msg = Welcome.this.getIntent().getStringExtra(GcmMessageListenerService.MESSAGE);
                    Intent i = new Intent(Welcome.this, MainActivity.class);
                    startActivity(i);

                } catch (Exception ignored) {
                    //ignored
                } finally {
                    Welcome.this.finish();
                }

            }
        };
        splashTread.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //   mWaveHelper.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // mWaveHelper.start();
    }


}




