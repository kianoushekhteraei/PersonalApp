package andriod.bingnerdranch.kia.Project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.List;

import andriod.bingnerdranch.kia.Adapter.Rv_All_Home_Adp;
import andriod.bingnerdranch.kia.Adapter.Rv_Home_Adp;
import andriod.bingnerdranch.kia.Model.Rv_Home_Mdl;
import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;

public class Act_Rv_New_Prd extends AppCompatActivity {

    APIInterface request;
    @BindView(R.id.rv_all_new_Prd)
    RecyclerView rv_all_new_Prd;
    Context context;

    Rv_All_Home_Adp rv_all_home_adp;
    List<Rv_Home_Mdl> rv_home_mdl = new ArrayList<>();

    Observable<List<Rv_Home_Mdl>> mObservable ;
    Observer<List<Rv_Home_Mdl>> mObserver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rv_new_prd);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        request = APIClient.getApiClient("http://192.168.43.167/kia/").create(APIInterface.class);
      //  getData_new_prd();

//        mObservable = Observable.just(rv_home_mdl);
//        mObserver = new Observer<List<Rv_Home_Mdl>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(List<Rv_Home_Mdl> rv_home_mdls) {
//                rv_all_new_Prd.setHasFixedSize(true);
//                rv_all_new_Prd.setLayoutManager(new LinearLayoutManager(context, rv_all_new_Prd.VERTICAL,false));
//                rv_all_home_adp = new Rv_All_Home_Adp(rv_home_mdl, context);
//                rv_all_new_Prd.setAdapter(rv_all_home_adp);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        mObservable.subscribe(mObserver);
//        request.getData_new_prd()
//                .subscribeOn(Schedulers.io()) // the observable is emitted on io thread
//                .observeOn(AndroidSchedulers.mainThread()) // Methods needed to handle request in background thread
//                .subscribe(new Observer<List<Rv_Home_Mdl>>() {
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<Rv_Home_Mdl> rv_home_mdls) {
//                        rv_all_new_Prd.setHasFixedSize(true);
//                        rv_all_new_Prd.setLayoutManager(new LinearLayoutManager(getApplicationContext(), rv_all_new_Prd.HORIZONTAL, true));
//                        rv_all_home_adp = new Rv_All_Home_Adp(rv_home_mdl,getApplicationContext());
//                        rv_all_new_Prd.setAdapter(rv_all_home_adp);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//   }


//    private void getData_new_prd() {
//
//        request.getData_new_prd().enqueue(new Callback<List<Rv_Home_Mdl>>() {
//            @Override
//            public void onResponse(Call<List<Rv_Home_Mdl>> call, Response<List<Rv_Home_Mdl>> response) {
//
//                rv_home_mdl = response.body();
//                rv_all_home_adp = new Rv_All_Home_Adp(rv_home_mdl, context);
//                rv_all_new_Prd.setAdapter(rv_all_home_adp);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Rv_Home_Mdl>> call, Throwable t) {
//
//                Toast.makeText(Act_Rv_New_Prd.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.e("Error", t.getMessage() + "");
//            }
//        });
//
    }
}
