package andriod.bingnerdranch.kia.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Color;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import andriod.bingnerdranch.kia.Adapter.Rv_Orders_Adp;
import andriod.bingnerdranch.kia.Model.Delete_Item_Tabs_Mdl;
import andriod.bingnerdranch.kia.Model.Tab_Orders_Mdl;
import andriod.bingnerdranch.kia.Project.APIClient;
import andriod.bingnerdranch.kia.Project.APIInterface;
import andriod.bingnerdranch.kia.Project.SwipeHelper;
import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frg_Tab_Orders extends Fragment {

    private APIInterface request;

    Rv_Orders_Adp orders_adp;
    ArrayList<Tab_Orders_Mdl> stringArrayList = new ArrayList<>();
    ConstraintLayout cl_sample_tabs;

    public static Integer ID_delete_item_orders;

    @BindView(R.id.rv_orders)
    RecyclerView rv_orders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_tab_orders, container, false);
        ButterKnife.bind(this, view);
        request = APIClient.getApiClient("http://192.168.1.4/kia/").create(APIInterface.class);
        rv_orders.setHasFixedSize(true);
        rv_orders.setLayoutManager(new LinearLayoutManager(getContext(), rv_orders.VERTICAL, false));
        enableSwipeHelperAndUndo();


        request.getData_orders()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Tab_Orders_Mdl>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<Tab_Orders_Mdl> tab_orders_mdl) {
                        rv_orders.setHasFixedSize(true);
                        rv_orders.setLayoutManager(new LinearLayoutManager(getContext(),rv_orders.VERTICAL,true));
                        orders_adp = new Rv_Orders_Adp(getContext(),tab_orders_mdl,cl_sample_tabs);
                        rv_orders.setAdapter(orders_adp);
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


                final int position = viewHolder.getAdapterPosition();
                final Tab_Orders_Mdl item = orders_adp.getData().get(position);

                orders_adp.removeItem(position);
                Delete_Data_orders();


                Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "گزینه انتخاب شده حذف شد", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        orders_adp.restoreItem(item, position);
                        rv_orders.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(rv_orders);
    }

    private void Delete_Data_orders() {



        Call<Delete_Item_Tabs_Mdl> call = request.Delete_item_Orders( ID_delete_item_orders );

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
