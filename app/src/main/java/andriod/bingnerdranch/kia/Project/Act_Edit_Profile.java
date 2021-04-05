package andriod.bingnerdranch.kia.Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import andriod.bingnerdranch.kia.Model.Info_Edit_Profile_Mdl;
import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Act_Edit_Profile extends AppCompatActivity {

    APIInterface request;

    @BindView(R.id.iv_edit_profile)
    ImageView iv_edit_profile;
    @BindView(R.id.edt_name_edit_profile)
    EditText edt_name_edit_profile;
    @BindView(R.id.email_edit_profile)
    EditText email_edit_profile;
    @BindView(R.id.number_edit_profile)
    EditText number_edit_profile;
    @BindView(R.id.code_edit_profile)
    EditText code_edit_profile;
    @BindView(R.id.spinner_city_edt_profile)
    Spinner spinner_city_edt_profile;
    @BindView(R.id.spinner_gender_edit_profile)
    Spinner spinner_gender_edit_profile;
    @BindView(R.id.btn_edit_profile)
    Button btn_edit_profile;

    private boolean detail_edit_profile = false ;

    @OnClick(R.id.btn_edit_profile)
    public void btn_edit_profile() {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("showFrg", "profile");
        intent.putExtra("name_profile", edt_name_edit_profile.getText().toString());
        intent.putExtra("gender_profile", spinner_gender_edit_profile.getSelectedItem().toString());
        intent.putExtra("city_profile", spinner_city_edt_profile.getSelectedItem().toString());
        intent.putExtra("email_profile", email_edit_profile.getText().toString());
        intent.putExtra("code_profile", code_edit_profile.getText().toString());
        intent.putExtra("number_profile", number_edit_profile.getText().toString());
        startActivity(intent);
        post_Edit_Profile_Info();
    }

    String[] city_sign_up = new String[]{"مشهد", "تهران", "کرچ", "شیراز", "اصفهان", "گرگان", "بندرعباس", "کرمان"};
    String[] gender_sign_up = new String[]{"مرد", "زن"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_edit_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        request = APIClient.getApiClient("http://192.168.1.3/kia/").create(APIInterface.class);

        Bundle extras = getIntent().getExtras();

        try {
            detail_edit_profile = extras.getBoolean("detail_edit_profile");
        }catch (Exception e){

        }

        if(detail_edit_profile) {
            String tv_name_frg_profile = extras.getString("tv_name_frg_profile");
            String tv_gender_frg_profile = extras.getString("tv_gender_frg_profile");
            String tv_city_frg_profile = extras.getString("tv_city_frg_profile");
            String tv_email_frg_profile = extras.getString("tv_email_frg_profile");
            String tv_code_frg_profile = extras.getString("tv_code_frg_profile");
            String tv_number_frg_profile = extras.getString("tv_number_frg_profile");

            edt_name_edit_profile.setText(tv_name_frg_profile);
  //          spinner_gender_edit_profile.setSelection(((ArrayAdapter<String>)spinner_gender_edit_profile.getAdapter()).getPosition(tv_gender_frg_profile));
 //          spinner_city_edt_profile.setSelection(((ArrayAdapter<String>)spinner_city_edt_profile.getAdapter()).getPosition(tv_city_frg_profile));
            email_edit_profile.setText(tv_email_frg_profile);
            code_edit_profile.setText(tv_code_frg_profile);
            number_edit_profile.setText(tv_number_frg_profile);

        }else{

            // old data must be shown

        }
        ArrayAdapter<String> adapter_city = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, city_sign_up);
        adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_city_edt_profile.setAdapter(adapter_city);


        ArrayAdapter<String> adapter_gender = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, gender_sign_up);
        adapter_gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender_edit_profile.setAdapter(adapter_gender);

    }

    private void post_Edit_Profile_Info() {

        String name_profile = edt_name_edit_profile.getText().toString();
        String gender_profile = spinner_gender_edit_profile.getSelectedItem().toString();
        String city_profile = spinner_city_edt_profile.getSelectedItem().toString();
        String email_profile = email_edit_profile.getText().toString();
        String code_profile = code_edit_profile.getText().toString();
        String number_profile = number_edit_profile.getText().toString();

        Call<Info_Edit_Profile_Mdl> call = request.GetInfoEdit_Profile(name_profile, gender_profile, city_profile, email_profile, code_profile, number_profile);


        call.enqueue(new Callback<Info_Edit_Profile_Mdl>() {
            @Override
            public void onResponse(Call<Info_Edit_Profile_Mdl> call, Response<Info_Edit_Profile_Mdl> response) {

                if (response.body().getResponse().equals("USER_REGISTER")) {

                    Toast.makeText(Act_Edit_Profile.this, "ایمیل یا شماره وارد شده قبلا استفاده شده است.", Toast.LENGTH_SHORT).show();


                } else if (response.body().getResponse().equals("SUCCESS")) {

                    // Toast.makeText(Act_Edit_Profile.this, "SUCCESS", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Act_Edit_Profile.this, MainActivity.class));
                    finish();


                } else if (response.body().getResponse().equals("WRONG")) {

                    //    Toast.makeText(Act_Edit_Profile.this, "Something Wrong", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Info_Edit_Profile_Mdl> call, Throwable t) {
                Toast.makeText(Act_Edit_Profile.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });

    }
}
