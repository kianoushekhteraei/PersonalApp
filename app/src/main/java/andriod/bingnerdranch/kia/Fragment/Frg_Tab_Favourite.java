package andriod.bingnerdranch.kia.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import andriod.bingnerdranch.kia.Adapter.Rv_Favourite_Adp;
import andriod.bingnerdranch.kia.Model.Delete_Item_Tabs_Mdl;
import andriod.bingnerdranch.kia.Model.Tab_Favourite_Mdl;
import andriod.bingnerdranch.kia.Project.APIClient;
import andriod.bingnerdranch.kia.Project.APIInterface;
import andriod.bingnerdranch.kia.Project.Act_Detail_Tabs;
import andriod.bingnerdranch.kia.Project.RecyclerItemClickListener;
import andriod.bingnerdranch.kia.Project.SwipeHelper;
import andriod.bingnerdranch.kia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class Frg_Tab_Favourite extends Fragment {

    private APIInterface request;

    Rv_Favourite_Adp favourite_adp;
    ArrayList<Tab_Favourite_Mdl> stringArrayList = new ArrayList<>();
    ConstraintLayout cl_sample_tabs;

    public static String ID_delete_item_wish_list =  "" ;


    @BindView(R.id.rv_favourite)
    RecyclerView rv_favourite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_tab_favourite, container, false);
        ButterKnife.bind(this, view);
        request = APIClient.getApiClient("http://192.168.1.4/kia/").create(APIInterface.class);
        enableSwipeHelperAndUndo();


//        if(getArguments() != null){
//
//            ID_delete_item_wish_list = getArguments().getString("id_delete");
//
//        }

//        rv_favourite.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), rv_favourite, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(),ID_delete_item_wish_list,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));

        request.getData_favourite()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Tab_Favourite_Mdl>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<Tab_Favourite_Mdl> tab_favourite_mdl) {
                        rv_favourite.setHasFixedSize(true);
                        rv_favourite.setLayoutManager(new LinearLayoutManager(getContext(),rv_favourite.VERTICAL,false));
                        favourite_adp = new Rv_Favourite_Adp(getContext(),tab_favourite_mdl,cl_sample_tabs);
                        rv_favourite.setAdapter(favourite_adp);

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

    public void enableSwipeHelperAndUndo() {
        SwipeHelper swipeToDeleteCallback = new SwipeHelper(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int id_delete = viewHolder.getAdapterPosition();
                final Tab_Favourite_Mdl item = favourite_adp.getData().get(id_delete);

                favourite_adp.removeItem(id_delete);
                Delete_Data_favourite();


                Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "گزینه انتخاب شده حذف شد", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        favourite_adp.restoreItem(item, id_delete);
                        rv_favourite.scrollToPosition(id_delete);

                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(rv_favourite);
    }

    private void Delete_Data_favourite() {


        Call<Delete_Item_Tabs_Mdl> call = request.Delete_item_whish_list( ID_delete_item_wish_list );

        call.enqueue(new Callback<Delete_Item_Tabs_Mdl>() {

            @Override
            public void onResponse(Call<Delete_Item_Tabs_Mdl> call, Response<Delete_Item_Tabs_Mdl> response) {

                if (response.body().getResponse().equals("SUCCESS")) {


                    Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();


                } else if (response.body().getResponse().equals("WRONG")) {

                    Toast.makeText(getContext(), "WRONG", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Delete_Item_Tabs_Mdl> call, Throwable t) {

            }

        });
    }
}
