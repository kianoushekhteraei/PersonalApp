package andriod.bingnerdranch.kia.Sign_Up;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import andriod.bingnerdranch.kia.Fragment.Frg_Profile;
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
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Act_Sign_Up extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 10;
    APIInterface request;
    @BindView(R.id.edt_email_sign_up)
    EditText edt_email_sign_up;
    @BindView(R.id.edt_code_sign_up)
    EditText edt_code_sign_up;
    @BindView(R.id.edt_name_sign_up)
    EditText edt_name_sign_up;
    @BindView(R.id.iv_sign_up)
    CircleImageView iv_sign_up;
    @BindView(R.id.spinner_city_sign_up)
    Spinner spinner_city_sign_up;
    @BindView(R.id.spinner_gender_sign_up)
    Spinner spinner_gender_sign_up;
    @BindView(R.id.btn_sign_up)
    Button btn_sign_up;


    public SharedPreferences sharedPreferences ;

    public static String filename = "" ;

    @OnClick(R.id.iv_sign_up)
    public void setIv_sign_up(){
        operngallery();
    }

    @OnClick(R.id.btn_sign_up)
    public void btn_sign_up() {
        if (!TextUtils.isEmpty(edt_name_sign_up.getText().toString()) && !TextUtils.isEmpty(edt_email_sign_up.getText().toString())) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
//            intent.putExtra("name_sign_up", edt_name_sign_up.getText().toString());
//            intent.putExtra("gender_sign_up", spinner_gender_sign_up.getSelectedItem().toString());
//            intent.putExtra("city_sign_up", spinner_city_sign_up.getSelectedItem().toString());
//            intent.putExtra("email_sign_up", edt_email_sign_up.getText().toString());
//            intent.putExtra("code_sign_up", edt_code_sign_up.getText().toString());

            sharedPreferences = getSharedPreferences(filename,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String name_sign_up = edt_name_sign_up.getText().toString();
            String gender_sign_up = spinner_gender_sign_up.getSelectedItem().toString();
            String city_sign_up = spinner_city_sign_up.getSelectedItem().toString();
            String email_sign_up = edt_email_sign_up.getText().toString();
            String code_sign_up = edt_code_sign_up.getText().toString();
            editor.putString("name_sign_up",name_sign_up);
            editor.putString("gender_sign_up",gender_sign_up);
            editor.putString("city_sign_up",city_sign_up);
            editor.putString("email_sign_up",email_sign_up);
            editor.putString("code_sign_up",code_sign_up);
            editor.commit();
            startActivity(intent);
            post_users_info();


        }else {
            edt_name_sign_up.setError("This Field Cant Be Null");
            edt_email_sign_up.setError("This Field Cant Be Null");
        }
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
        request = APIClient.getApiClient("http://192.168.1.4/kia/").create(APIInterface.class);
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


        Call<Info_Users_Mdl> call = request.GetInfoAccount(name_sign_up, gender_sign_up, city_sign_up, code_sign_up, email_sign_up );


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

    public void operngallery(){

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, RESULT_LOAD_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            iv_sign_up.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

}

