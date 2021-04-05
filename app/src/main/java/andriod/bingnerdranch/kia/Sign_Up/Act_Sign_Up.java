package andriod.bingnerdranch.kia.Sign_Up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import andriod.bingnerdranch.kia.Model.Info_Users_Mdl;
import andriod.bingnerdranch.kia.Model.Verify_Number_Mdl;
import andriod.bingnerdranch.kia.Project.APIClient;
import andriod.bingnerdranch.kia.Project.APIInterface;
import andriod.bingnerdranch.kia.Project.MainActivity;
import andriod.bingnerdranch.kia.R;
import andriod.bingnerdranch.kia.Verification.Act_Verification;
import andriod.bingnerdranch.kia.Verification.Act_Verify_Code;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Act_Sign_Up extends AppCompatActivity {

    APIInterface request;
    @BindView(R.id.edt_email_sign_up)
    EditText edt_email_sign_up;
    @BindView(R.id.edt_code_sign_up)
    EditText edt_code_sign_up;
    @BindView(R.id.edt_name_sign_up)
    EditText edt_name_sign_up;
    @BindView(R.id.iv_sign_up)
    ImageView iv_sign_up;
    @BindView(R.id.spinner_city_sign_up)
    Spinner spinner_city_sign_up;
    @BindView(R.id.spinner_gender_sign_up)
    Spinner spinner_gender_sign_up;
    @BindView(R.id.btn_sign_up)
    Button btn_sign_up;

    @OnClick(R.id.btn_sign_up)
    public void btn_sign_up() {

        Intent i = new Intent(Act_Sign_Up.this, MainActivity.class);
        startActivity(i);
        post_users_info();
    }

    String[] city_sign_up = new String[]{"مشهد", "تهران", "کرچ", "شیراز", "اصفهان", "گرگان", "بندرعباس", "کرمان"};
    String[] gender_sign_up = new String[]{"مرد", "زن"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        request = APIClient.getApiClient("http://192.168.1.3/kia/").create(APIInterface.class);
        ArrayAdapter<String> adapter_city = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, city_sign_up);
        adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_city_sign_up.setAdapter(adapter_city);


        ArrayAdapter<String> adapter_gender = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, gender_sign_up);
        adapter_gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender_sign_up.setAdapter(adapter_gender);
    }

    private void post_users_info() {

        String name_sign_up = edt_name_sign_up.getText().toString();
        String gender_sign_up = spinner_gender_sign_up.getSelectedItem().toString();
        String city_sign_up = spinner_city_sign_up.getSelectedItem().toString();
        String email_sign_up = edt_email_sign_up.getText().toString();
        String code_sign_up = edt_code_sign_up.getText().toString();

        Call<Info_Users_Mdl> call = request.GetInfoAccount(name_sign_up, gender_sign_up, city_sign_up, email_sign_up, code_sign_up);


        call.enqueue(new Callback<Info_Users_Mdl>() {
            @Override
            public void onResponse(Call<Info_Users_Mdl> call, Response<Info_Users_Mdl> response) {

                if (response.body().getResponse().equals("USER_REGISTER")) {

                    Toast.makeText(Act_Sign_Up.this, "ایمیل وارد شده قبلا استفاده شده است.", Toast.LENGTH_SHORT).show();


                } else if (response.body().getResponse().equals("SUCCESS")) {

                    //  Toast.makeText(Act_Sign_Up.this, "SUCCESS", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Act_Sign_Up.this, MainActivity.class));
                    finish();


                } else if (response.body().getResponse().equals("WRONG")) {

                    Toast.makeText(Act_Sign_Up.this, "Something Wrong", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Info_Users_Mdl> call, Throwable t) {
                Toast.makeText(Act_Sign_Up.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

}

