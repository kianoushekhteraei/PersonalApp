package andriod.bingnerdranch.kia.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import andriod.bingnerdranch.kia.Adapter.Rv_Home_Adp;
import andriod.bingnerdranch.kia.Adapter.Slider_Home_Adp;
import andriod.bingnerdranch.kia.Model.Rv_Home_Mdl;

import andriod.bingnerdranch.kia.Model.Slider_Img_Mdl;
import andriod.bingnerdranch.kia.Project.APIClient;
import andriod.bingnerdranch.kia.Project.APIInterface;
import andriod.bingnerdranch.kia.Project.Act_Rv_Most_Off;
import andriod.bingnerdranch.kia.Project.Act_Rv_Most_View;
import andriod.bingnerdranch.kia.Project.Act_Rv_New_Prd;
import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frg_Home extends Fragment {

    private APIInterface request;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    Slider_Home_Adp slider_home_adp;
    List<Slider_Img_Mdl> slider_img_mdl = new ArrayList<>();
    Rv_Home_Adp rv_home_adp;

    @BindView(R.id.vp_slider)
    ViewPager vp_slider;
    @BindView(R.id.vp_indicator)
    CirclePageIndicator vp_indicator;
    @BindView(R.id.rv_new_prd)
    RecyclerView rv_new_prd;
    @BindView(R.id.rv_most_off)
    RecyclerView rv_most_off;
    @BindView(R.id.rv_most_view)
    RecyclerView rv_most_view;

    @BindView(R.id.tv_more_most_off)
    TextView tv_more_most_off;
    @BindView(R.id.tv_more_most_view)
    TextView tv_more_most_view;
    @BindView(R.id.tv_more_new_prd)
    TextView tv_more_new_prd;

    @OnClick(R.id.tv_more_new_prd)
    public void tv_more_new_prd() {

        Intent i = new Intent(getContext(), Act_Rv_New_Prd.class);
        startActivity(i);
    }

    @OnClick(R.id.tv_more_most_view)
    public void tv_more_most_view() {

        Intent i = new Intent(getContext(), Act_Rv_Most_View.class);
        startActivity(i);
    }

    @OnClick(R.id.tv_more_most_off)
    public void tv_more_most_off() {

        Intent i = new Intent(getContext(), Act_Rv_Most_Off.class);
        startActivity(i);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_home, container, false);
        request = APIClient.getApiClient("http://192.168.1.4/kia/").create(APIInterface.class);
        ButterKnife.bind(this, view);
        rv_new_prd.setHasFixedSize(true);
        rv_new_prd.setLayoutManager(new LinearLayoutManager(getContext(), rv_new_prd.HORIZONTAL, true));
        rv_most_view.setHasFixedSize(true);
        rv_most_view.setLayoutManager(new LinearLayoutManager(getContext(), rv_most_view.HORIZONTAL, true));
        init();
        get_Data_slider();


        request.getData_most_off()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Rv_Home_Mdl>>() {
                    @Override
                    public void onSubscribe(Disposable d) {


                    }

                    @Override
                    public void onNext(List<Rv_Home_Mdl> rv_home_mdl) {
                        rv_most_off.setHasFixedSize(true);
                        rv_most_off.setLayoutManager(new LinearLayoutManager(getContext(),rv_most_off.HORIZONTAL,true));
                        rv_home_adp = new Rv_Home_Adp(rv_home_mdl,getContext());
                        rv_most_off.setAdapter(rv_home_adp);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        request.getData_most_view()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Rv_Home_Mdl>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.d("onSubscribe","Success");

                    }

                    @Override
                    public void onNext(List<Rv_Home_Mdl> rv_home_mdl) {
                        rv_most_view.setHasFixedSize(true);
                        rv_most_view.setLayoutManager(new LinearLayoutManager(getContext(),rv_most_view.HORIZONTAL,true));
                        rv_home_adp = new Rv_Home_Adp(rv_home_mdl,getContext());
                        rv_most_view.setAdapter(rv_home_adp);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        request.getData_new_prd()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Rv_Home_Mdl>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Rv_Home_Mdl> rv_home_mdl) {
                        rv_new_prd.setHasFixedSize(true);
                        rv_new_prd.setLayoutManager(new LinearLayoutManager(getContext(),rv_new_prd.HORIZONTAL,true));
                        rv_home_adp = new Rv_Home_Adp(rv_home_mdl,getContext());
                        rv_new_prd.setAdapter(rv_home_adp);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return view;
    }

    private void init() {

        vp_slider.setAdapter(new Slider_Home_Adp(getContext(), slider_img_mdl));

        vp_indicator.setViewPager(vp_slider);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        vp_indicator.setRadius(3 * density);

        NUM_PAGES = slider_img_mdl.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                vp_slider.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        vp_indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    private void get_Data_slider() {

        request.get_Data_slider().enqueue(new Callback<List<Slider_Img_Mdl>>() {


            @Override
            public void onResponse(Call<List<Slider_Img_Mdl>> call, Response<List<Slider_Img_Mdl>> response) {

                slider_img_mdl = response.body();
                slider_home_adp = new Slider_Home_Adp(getContext(), slider_img_mdl);
                vp_slider.setAdapter(slider_home_adp);
            }

            @Override
            public void onFailure(Call<List<Slider_Img_Mdl>> call, Throwable t) {

//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage() + "");
            }
        });
    }

}
