package andriod.bingnerdranch.kia.Verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import andriod.bingnerdranch.kia.R;
import andriod.bingnerdranch.kia.Sign_Up.Act_Sign_Up;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Act_Verify_Code extends AppCompatActivity {

    @BindView(R.id.btn_verify_code)
    Button btn_verify_code;

    @OnClick(R.id.btn_verify_code)
    public void btn_verify_code(){

        Intent i = new Intent(Act_Verify_Code.this, Act_Sign_Up.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_verify_code);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
