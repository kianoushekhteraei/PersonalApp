package andriod.bingnerdranch.kia.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Frg_Store extends Fragment {

    Fragment fragment;

    @BindView(R.id.cl_toolbar_tabs)
    ConstraintLayout cl_toolbar_tabs;
    @BindView(R.id.cl_orders)
    ConstraintLayout cl_orders;
    @BindView(R.id.cl_favourite)
    ConstraintLayout cl_favourite;
    @BindView(R.id.tv_title_orders)
    TextView tv_title_orders;
    @BindView(R.id.tv_title_favourite)
    TextView tv_title_favourite;


    @OnClick(R.id.cl_orders)
    public void cl_orders() {

        fragment = new Frg_Tab_Orders();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_all_store, fragment);
        fragmentTransaction.commit();
        tv_title_orders.setTextColor(getResources().getColor(R.color.green_4ac58e));
        tv_title_favourite.setTextColor(getResources().getColor(R.color.white));


    }

    @OnClick(R.id.cl_favourite)
    public void cl_favourite() {


        fragment = new Frg_Tab_Favourite();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_all_store, fragment);
        fragmentTransaction.commit();
        tv_title_orders.setTextColor(getResources().getColor(R.color.white));
        tv_title_favourite.setTextColor(getResources().getColor(R.color.green_4ac58e));


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_store, container, false);
        ButterKnife.bind(this, view);
        fragment = new Frg_Tab_Orders();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_all_store, fragment);
        fragmentTransaction.commit();
        return view;

    }
}
