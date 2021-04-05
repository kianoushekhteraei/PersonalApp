package andriod.bingnerdranch.kia.Project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import andriod.bingnerdranch.kia.Fragment.Frg_Home;
import andriod.bingnerdranch.kia.Fragment.Frg_Profile;
import andriod.bingnerdranch.kia.Fragment.Frg_Store;
import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
            Log.e("showFrg",showFrg);
        }catch (Exception e){

        }
        if(showFrg.equals("home")){
        iv_profile_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_green));
        iv_home_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_white));
        iv_store_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_store_white));
        fragment = new Frg_Profile();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_all, fragment);
        fragmentTransaction.commit();
        }else if (showFrg.equals("profile")) {
            iv_profile_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_green));
            iv_home_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_white));
            iv_store_btm.setImageDrawable(getResources().getDrawable(R.drawable.ic_store_white));
            String name_profile = extras.getString("name_profile");
            String gender_profile = extras.getString("gender_profile");
            String city_profile = extras.getString("city_profile");
            String email_profile = extras.getString("email_profile");
            String code_profile = extras.getString("code_profile");
            String number_profile = extras.getString("number_profile");
            Bundle bundle = new Bundle();
            bundle.putBoolean("isFromEdit",true);
            bundle.putString("name_profile", name_profile);
            bundle.putString("gender_profile", gender_profile);
            bundle.putString("city_profile", city_profile);
            bundle.putString("email_profile", email_profile);
            bundle.putString("code_profile", code_profile);
            bundle.putString("number_profile", number_profile);
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        request = APIClient.getApiClient("http://192.168.43.167/kia/").create(APIInterface.class);

        swipe_ref.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request = APIClient.getApiClient("http://192.168.43.167/kia/").create(APIInterface.class);
                swipe_ref.setRefreshing(false);
            }
        });
        Bundle extras = getIntent().getExtras();

        try {
            showFrg = extras.getString("showFrg");
            Log.e("showFrg",showFrg);
        }catch (Exception e){

        }

            if (showFrg.equals("home")){
                fragment = new Frg_Home();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_all, fragment);
                fragmentTransaction.commit();
       }else if (showFrg.equals("profile")){
                String name_profile = extras.getString("name_profile");
                String gender_profile = extras.getString("gender_profile");
                String city_profile = extras.getString("city_profile");
                String email_profile = extras.getString("email_profile");
                String code_profile = extras.getString("code_profile");
                String number_profile = extras.getString("number_profile");

                Bundle bundle = new Bundle();
                bundle.putBoolean("isFromEdit",true);
                bundle.putString("name_profile", name_profile);
                bundle.putString("gender_profile", gender_profile);
                bundle.putString("city_profile", city_profile);
                bundle.putString("email_profile", email_profile);
                bundle.putString("code_profile", code_profile);
                bundle.putString("number_profile", number_profile);
                Frg_Profile frg_profile = new Frg_Profile();
                frg_profile.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_all, frg_profile);
                transaction.commit();
           }
    }
}

