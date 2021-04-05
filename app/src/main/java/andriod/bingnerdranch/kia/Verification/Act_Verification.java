package andriod.bingnerdranch.kia.Verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import andriod.bingnerdranch.kia.Model.Info_Users_Mdl;
import andriod.bingnerdranch.kia.Model.Verify_Number_Mdl;
import andriod.bingnerdranch.kia.Project.APIClient;
import andriod.bingnerdranch.kia.Project.APIInterface;
import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Act_Verification extends AppCompatActivity {

    APIInterface request;

    @BindView(R.id.btn_verification)
    Button btn_verification;
    @BindView(R.id.edt_verification_Number)
    EditText edt_verification_Number;
    @OnClick(R.id.btn_verification)

    public void btn_verification() {

        Intent i = new Intent(Act_Verification.this, Act_Verify_Code.class);
        startActivity(i);
        post_users_number();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_verification);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        request = APIClient.getApiClient("http://192.168.1.3/kia/").create(APIInterface.class);
    }

    private void post_users_number() {

        String phone_number = edt_verification_Number.getText().toString();

        Call<Verify_Number_Mdl> call = request.GetPhoneNumber(phone_number);


        call.enqueue(new Callback<Verify_Number_Mdl>() {
            @Override
            public void onResponse(Call<Verify_Number_Mdl> call, Response<Verify_Number_Mdl> response) {

                if (response.body().getResponse().equals("USER_REGISTER")) {

                    Toast.makeText(Act_Verification.this, "این شماره تلفن قبلا استفاده شده است.", Toast.LENGTH_SHORT).show();


                } else if (response.body().getResponse().equals("SUCCESS")) {

                    startActivity(new Intent(Act_Verification.this, Act_Verify_Code.class));
                    finish();


                } else if (response.body().getResponse().equals("WRONG")) {

                   // Toast.makeText(Act_Verification.this, "Something Wrong", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Verify_Number_Mdl> call, Throwable t) {
                Toast.makeText(Act_Verification.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }
}
