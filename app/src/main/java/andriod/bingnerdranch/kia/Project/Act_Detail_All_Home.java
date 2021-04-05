package andriod.bingnerdranch.kia.Project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import andriod.bingnerdranch.kia.Model.Post_Data_Orders_Mdl;
import andriod.bingnerdranch.kia.Model.Post_Data_Whish_List_Mdl;
import andriod.bingnerdranch.kia.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Act_Detail_All_Home extends AppCompatActivity {


    public static String ID_Home = "id2";

    private APIInterface request;

    int id2;

    Bundle bundle;

    boolean flag = false;

    TextView tv_name_prd_detail_all_home, tv_aprice_detail_all_home, tv_bprice_detail_all_home, tv_description_detail_all_home, tv_off_detail_all_home;
    ImageView iv_prd_detail_all_home, iv_whish_detail_all_home;
    ConstraintLayout cl_detail_all_home;

    String before_price = "";
    String after_price= "";
    String off = "";
    String name_whish_list = "";
    String name_orders = "";
    String description = "";
    String link_img = "";

    @OnClick(R.id.cl_buy_detail_all_home)
    public void cl_buy_detail_all_home(){

        Intent i = new Intent(Act_Detail_All_Home.this,Act_Buy_Product.class);
        startActivity(i);
        PostData_orders();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detail_all_home);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        request = APIClient.getApiClient("http://192.168.43.167/kia/").create(APIInterface.class);

        iv_whish_detail_all_home = findViewById(R.id.iv_whish_detail_all_home);
        cl_detail_all_home = findViewById(R.id.cl_buy_detail_all_home);

        tv_name_prd_detail_all_home = findViewById(R.id.tv_name_prd_detail_all_home);
        tv_aprice_detail_all_home = findViewById(R.id.tv_aprice_detail_all_home);
        tv_bprice_detail_all_home = findViewById(R.id.tv_bprice_detail_all_home);
        tv_description_detail_all_home = findViewById(R.id.tv_description_detail_all_home);
        tv_off_detail_all_home = findViewById(R.id.tv_off_detail_all_home);
        iv_prd_detail_all_home = findViewById(R.id.iv_prd_detail_all_home);


        id2 = Integer.parseInt(getIntent().getStringExtra(ID_Home));

        bundle = getIntent().getExtras();

        before_price = bundle.getString("before_price");
        tv_bprice_detail_all_home.setText(before_price);

        after_price = bundle.getString("after_price");
        tv_aprice_detail_all_home.setText(after_price);

        off = bundle.getString("off");
        tv_off_detail_all_home.setText(off);

        name_whish_list = bundle.getString("name");
        tv_name_prd_detail_all_home.setText(name_whish_list);


        name_orders = bundle.getString("name");
        tv_name_prd_detail_all_home.setText(name_orders);

        description = bundle.getString("description");
        tv_description_detail_all_home.setText(description);


        link_img = bundle.getString("link_img");
        Picasso.get().load(bundle.getString("link_img")).into(iv_prd_detail_all_home);


        iv_whish_detail_all_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag) {
                    iv_whish_detail_all_home.setImageResource(R.drawable.ic_whish_black);
                    flag = false;
                } else {
                    iv_whish_detail_all_home.setImageResource(R.drawable.ic_whish_green);
                    flag = true;
                    PostData_favourite();
                }

            }
        });
    }

    private void PostData_favourite() {


        Call<Post_Data_Whish_List_Mdl> call = request.Post_Data_Wish_List(before_price, after_price, link_img, off, name_whish_list, description);

        call.enqueue(new Callback<Post_Data_Whish_List_Mdl>() {

            @Override
            public void onResponse(Call<Post_Data_Whish_List_Mdl> call, Response<Post_Data_Whish_List_Mdl> response) {

                if (response.body().getResponse().equals("DATA_REGISTERED")) {

                    Toast.makeText(Act_Detail_All_Home.this, "مورد انتخابی قبلا افزوده شده است.", Toast.LENGTH_SHORT).show();


                } else if (response.body().getResponse().equals("SUCCESS")) {

                    //  Toast.makeText(Act_Sign_Up.this, "SUCCESS", Toast.LENGTH_SHORT).show();


                } else if (response.body().getResponse().equals("WRONG")) {

                    //  Toast.makeText(Act_Detail_Home.this, "Something Wrong", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Post_Data_Whish_List_Mdl> call, Throwable t) {

            }

        });
    }
    private void PostData_orders() {


        Call<Post_Data_Orders_Mdl> call = request.Post_Data_orders( before_price , after_price , link_img , off , name_orders , description );

        call.enqueue(new Callback<Post_Data_Orders_Mdl>() {

            @Override
            public void onResponse(Call<Post_Data_Orders_Mdl> call, Response<Post_Data_Orders_Mdl> response) {

                if (response.body().getResponse().equals("DATA_REGISTERED")) {

                    Toast.makeText(Act_Detail_All_Home.this, "مورد انتخابی قبلا افزوده شده است.", Toast.LENGTH_SHORT).show();



                } else if (response.body().getResponse().equals("SUCCESS")) {

                    //  Toast.makeText(Act_Sign_Up.this, "SUCCESS", Toast.LENGTH_SHORT).show();


                } else if (response.body().getResponse().equals("WRONG")) {

                    //  Toast.makeText(Act_Detail_Home.this, "Something Wrong", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Post_Data_Orders_Mdl> call, Throwable t) {

            }

        });
    }
}
