package andriod.bingnerdranch.kia.Project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import andriod.bingnerdranch.kia.Adapter.Rv_All_Home_Adp;
import andriod.bingnerdranch.kia.Model.Rv_Home_Mdl;
import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Act_Rv_Most_Off extends AppCompatActivity {

    APIInterface request;
    @BindView(R.id.rv_all_most_off)
    RecyclerView rv_all_most_off;
    Context context;

    Rv_All_Home_Adp rv_all_home_adp;
    List<Rv_Home_Mdl> rv_home_mdl = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rv_most_off);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        rv_all_most_off.setHasFixedSize(true);
        rv_all_most_off.setLayoutManager(new LinearLayoutManager(context, rv_all_most_off.VERTICAL,false));
        request = APIClient.getApiClient("http://192.168.43.167/kia/").create(APIInterface.class);
 //       getData_most_off();
    }


//    private void getData_most_off() {
//
//        request.getData_most_off().enqueue(new Callback<List<Rv_Home_Mdl>>() {
//            @Override
//            public void onResponse(Call<List<Rv_Home_Mdl>> call, Response<List<Rv_Home_Mdl>> response) {
//
//                rv_home_mdl = response.body();
//                rv_all_home_adp = new Rv_All_Home_Adp(rv_home_mdl, context);
//                rv_all_most_off.setAdapter(rv_all_home_adp);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Rv_Home_Mdl>> call, Throwable t) {
//
//                Toast.makeText(Act_Rv_Most_Off.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.e("Error", t.getMessage() + "");
//            }
//        });
//
//    }
}
