package andriod.bingnerdranch.kia.Project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import andriod.bingnerdranch.kia.Fragment.Frg_Home;
import andriod.bingnerdranch.kia.Fragment.Frg_Profile;
import andriod.bingnerdranch.kia.Fragment.Frg_Store;
import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cl_home)
    ConstraintLayout cl_home;
    @BindView(R.id.cl_profile)
    ConstraintLayout cl_profile;
    @BindView(R.id.cl_store)
    ConstraintLayout cl_store;
    @BindView(R.id.iv_profile_btm)
    ImageView iv_profile_btm;
    @BindView(R.id.iv_store_btm)
    ImageView iv_store_btm;
    @BindView(R.id.iv_home_btm)
    ImageView iv_home_btm;
    @BindView(R.id.fl_all)
    FrameLayout fl_all;
    @BindView(R.id.swipe_ref)
    SwipeRefreshLayout swipe_ref;

    private Fragment fragment;
    APIInterface request;


    private String showFrg = "home";

    @OnClick(R.id.cl_profile)
    public void Frg_Profile() {

        Bundle extras = getIntent().getExtras();
        try {
            showFrg = extras.getString("showFrg");
            Log.e("showFrg", showFrg);
        } catch (Exception e) {

        }

        Intent intent = getIntent();

        if (showFrg.equals("home")) {
            iv_profile_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_green));
            iv_home_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_white));
            iv_store_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_store_white));

            fragment = new Frg_Profile();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fl_all, fragment);
            fragmentTransaction.commit();

//            String name_sign_up = getIntent().getStringExtra("name_sign_up");
//            String gender_sign_up = getIntent().getStringExtra("gender_sign_up");
//            String city_sign_up = getIntent().getStringExtra("city_sign_up");
//            String email_sign_up = getIntent().getStringExtra("email_sign_up");
//            String code_sign_up = getIntent().getStringExtra("code_sign_up");
//
//            Bundle bundle = new Bundle();
//            bundle.putBoolean("isFromSign", true);
//            bundle.putString("name_sign_up", name_sign_up);
//            bundle.putString("gender_sign_up", gender_sign_up);
//            bundle.putString("city_sign_up", city_sign_up);
//            bundle.putString("email_sign_up", email_sign_up);
//            bundle.putString("code_sign_up", code_sign_up);
//            Frg_Profile frg_profile = new Frg_Profile();
//            frg_profile.setArguments(bundle);
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.fl_all, frg_profile);
//            transaction.commit();

        }

        if (showFrg.equals("profile")) {

            iv_profile_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_green));
            iv_home_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_white));
            iv_store_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_store_white));

            String name_profile = extras.getString("name_profile");
            String gender_profile = extras.getString("gender_profile");
            String city_profile = extras.getString("city_profile");
            String email_profile = extras.getString("email_profile");
            String code_profile = extras.getString("code_profile");
            String number_profile = extras.getString("number_profile");

            Bitmap bitmap = (Bitmap) intent.getParcelableExtra("image_profile");

            Log.e("name_profile", name_profile);
            Log.e("gender_profile", gender_profile);
            Log.e("city_profile", city_profile);
            Log.e("email_profile", email_profile);
            Log.e("code_profile", code_profile);
            Log.e("number_profile", number_profile);

            Bundle bundle = new Bundle();
            bundle.putBoolean("isFromEdit", true);
            bundle.putString("name_profile", name_profile);
            bundle.putString("gender_profile", gender_profile);
            bundle.putString("city_profile", city_profile);
            bundle.putString("email_profile", email_profile);
            bundle.putString("code_profile", code_profile);
            bundle.putString("number_profile", number_profile);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            bundle.putByteArray("image_profile", byteArray);
            Frg_Profile frg_profile = new Frg_Profile();
            frg_profile.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_all, frg_profile);
            transaction.commit();

        }

    }

    @OnClick(R.id.cl_home)
    public void Frg_Home() {
        iv_profile_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_white));
        iv_home_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_green));
        iv_store_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_store_white));
        fragment = new Frg_Home();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_all, fragment);
        fragmentTransaction.commit();
    }

    @OnClick(R.id.cl_store)
    public void Frg_Store() {
        iv_profile_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_white));
        iv_home_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_white));
        iv_store_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_store_green));
        fragment = new Frg_Store();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_all, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        request = APIClient.getApiClient("http://192.168.1.4/kia/").create(APIInterface.class);
        fragment = new Frg_Home();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_all, fragment);
        fragmentTransaction.commit();

        swipe_ref.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request = APIClient.getApiClient("http://192.168.1.4/kia/").create(APIInterface.class);
                swipe_ref.setRefreshing(true);
            }
        });

    }


//    @Override
//    public void sendData(String data) {
//        data.equals(name_sign_up);
//    }
}


