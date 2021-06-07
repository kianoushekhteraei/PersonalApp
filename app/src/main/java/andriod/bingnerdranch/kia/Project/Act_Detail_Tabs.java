package andriod.bingnerdranch.kia.Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;


import andriod.bingnerdranch.kia.R;

public class Act_Detail_Tabs extends AppCompatActivity {

    public static String ID = "id";


    private APIInterface request;

    int id;

    boolean flag = false;


    TextView tv_name_prd_detail, tv_aprice_detail, tv_bprice_detail, tv_description_detail, tv_off_detail;
    ImageView iv_prd_detail_tabs, iv_whish_detail;

    String before_price = "";
    String after_price = "";
    String off = "";
    String name_whish_list = "";
    String name_orders = "";
    String description = "";
    String link_img = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detail_tabs);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        request = APIClient.getApiClient("http://192.168.43.167/kia/").create(APIInterface.class);

        iv_whish_detail = findViewById(R.id.iv_whish_detail);
        tv_name_prd_detail = findViewById(R.id.tv_name_prd_detail);
        tv_aprice_detail = findViewById(R.id.tv_aprice_detail);
        tv_bprice_detail = findViewById(R.id.tv_bprice_detail);
        tv_description_detail = findViewById(R.id.tv_description_detail);
        tv_off_detail = findViewById(R.id.tv_off_detail);
        iv_prd_detail_tabs = findViewById(R.id.iv_prd_detail_tabs);


        id = Integer.parseInt(getIntent().getStringExtra(ID));
//        Toast.makeText(getApplicationContext(), ""+ id, Toast.LENGTH_SHORT).show();

        Bundle bundle = getIntent().getExtras();

        before_price = bundle.getString("before_price");
        tv_bprice_detail.setText(before_price);

        after_price = bundle.getString("after_price");
        tv_aprice_detail.setText(after_price);

        off = bundle.getString("off");
        tv_off_detail.setText(off);

        name_whish_list = bundle.getString("name");
        tv_name_prd_detail.setText(name_whish_list);


        name_whish_list = bundle.getString("name");
        tv_name_prd_detail.setText(name_whish_list);

        name_whish_list = bundle.getString("name");
        tv_name_prd_detail.setText(name_whish_list);


        description = bundle.getString("description");
        tv_description_detail.setText(description);


        link_img = bundle.getString("link_img");
        Picasso.get().load(bundle.getString("link_img")).into(iv_prd_detail_tabs);



        iv_whish_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag) {
                    iv_whish_detail.setImageResource(R.drawable.ic_whish_green);
                    flag = false;

                } else {
                    iv_whish_detail.setImageResource(R.drawable.ic_whish_white);
                    flag = true;
                }
                return;
            }

        });
    }

}
