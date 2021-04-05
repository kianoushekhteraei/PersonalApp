package andriod.bingnerdranch.kia.Project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import andriod.bingnerdranch.kia.R;
import andriod.bingnerdranch.kia.Splash.Act_Splash;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Act_Exit_Profile extends AppCompatActivity {

    @BindView(R.id.cl_all_exit)
    ConstraintLayout cl_all_exit;
    @BindView(R.id.btn_not_exit)
    Button btn_not_exit;
    @BindView(R.id.btn_exit)
    Button btn_exit;

    @OnClick(R.id.btn_exit)
    public void exit() {

        Intent i = new Intent(Act_Exit_Profile.this, Act_Splash.class);
        startActivity(i);

    }

    @OnClick(R.id.btn_not_exit)
    public void not_exit() {
        Intent i = new Intent(Act_Exit_Profile.this, MainActivity.class);
        startActivity(i);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_exit_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

    }
}

