package andriod.bingnerdranch.kia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import andriod.bingnerdranch.kia.Fragment.Frg_Tab_Favourite;
import andriod.bingnerdranch.kia.Model.Tab_Favourite_Mdl;

import andriod.bingnerdranch.kia.Project.Act_Detail_Tabs;

import andriod.bingnerdranch.kia.R;

public class Rv_Favourite_Adp extends RecyclerView.Adapter<Rv_Favourite_Adp.FavouriteViewHolder> {

    Context context;
    private ArrayList<Tab_Favourite_Mdl> data;
    ConstraintLayout cl_sample_tabs;


    public Rv_Favourite_Adp(Context context, ArrayList<Tab_Favourite_Mdl> data,ConstraintLayout cl_sample_tabs) {

        this.context = context;
        this.data = data;
        this.cl_sample_tabs = cl_sample_tabs;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_rv_tabs, parent, false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder_favourite, int position) {

        holder_favourite.tv_sample_name_prd.setText(data.get(position).getName_whish_list());
        holder_favourite.tv_bprice_tabs.setText(data.get(position).getBefore_price());
        holder_favourite.tv_aprice_tabs.setText(data.get(position).getAfter_price());
        holder_favourite.tv_off_tabs.setText(data.get(position).getOff());


        Picasso.get().load(data.get(position).getLink_img()).into(holder_favourite.iv_sample_tabs);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);

    }

    public void restoreItem(Tab_Favourite_Mdl item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<Tab_Favourite_Mdl> getData() {
        return data;
    }


    public class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView tv_sample_name_prd , tv_bprice_tabs , tv_aprice_tabs , tv_off_tabs;
        ImageView iv_sample_tabs  ;


        public FavouriteViewHolder(@NonNull View itemView) {

            super(itemView);

            tv_sample_name_prd = itemView.findViewById(R.id.tv_sample_name_prd);
            tv_bprice_tabs = itemView.findViewById(R.id.tv_bprice_tabs);
            tv_aprice_tabs = itemView.findViewById(R.id.tv_aprice_tabs);
            tv_off_tabs = itemView.findViewById(R.id.tv_off_tabs);

            iv_sample_tabs = itemView.findViewById(R.id.iv_sample_tabs);


            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, Act_Detail_Tabs.class);
            intent.putExtra(Act_Detail_Tabs.ID, data.get(getAdapterPosition()).getId());
            intent.putExtra(Frg_Tab_Favourite.ID_delete_item_wish_list, data.get(getAdapterPosition()).getId());
            intent.putExtra("name", data.get(getAdapterPosition()).getName_whish_list());
            intent.putExtra("before_price", data.get(getAdapterPosition()).getBefore_price());
            intent.putExtra("link_img", data.get(getAdapterPosition()).getLink_img());
            intent.putExtra("after_price", data.get(getAdapterPosition()).getAfter_price());
            intent.putExtra("off", data.get(getAdapterPosition()).getOff());
            intent.putExtra("description", data.get(getAdapterPosition()).getDescription());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

}
