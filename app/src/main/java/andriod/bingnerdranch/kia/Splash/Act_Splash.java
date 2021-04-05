package andriod.bingnerdranch.kia.Splash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.wang.avi.AVLoadingIndicatorView;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import andriod.bingnerdranch.kia.Project.Act_Connect;
import andriod.bingnerdranch.kia.R;
import andriod.bingnerdranch.kia.Verification.Act_Verification;
import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("DEPRECATION")
public class Act_Splash extends AppCompatActivity {

    ImageView iv_logo_splash;
    Animation slideAnimation;

    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);

        iv_logo_splash = findViewById(R.id.iv_logo_splash);
        slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide);
        iv_logo_splash.startAnimation(slideAnimation);
    /**
     * Duration of wait
     **/
    final int SPLASH_DISPLAY_LENGTH = 3000;

    /**
     * Called when the activity is first created.
     */

    ButterKnife.bind(this);
    startAnim();
    /* New Handler to start the Menu-Activity
     * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {

            //       if (isOnline()) {
            Intent i = new Intent(Act_Splash.this, Act_Verification.class);
            startActivity(i);

//                } else {
//
//                    Intent i = new Intent(Act_Splash.this, Act_Connect.class);
//                    startActivity(i);
//
//                }
        }
    }, SPLASH_DISPLAY_LENGTH);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                         WindowManager.LayoutParams.FLAG_FULLSCREEN);

}

    void startAnim() {
        avi_indicator.show();
        // or avi.smoothToShow();
    }

    void stopAnim() {
        avi_indicator.hide();
        // or avi.smoothToHide();
    }

    public boolean isOnline() {
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
//            int exitValue = ipProcess.waitFor();
//            return (exitValue == 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return false;

    }

}

