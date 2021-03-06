package andriod.bingnerdranch.kia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import andriod.bingnerdranch.kia.Model.Rv_Home_Mdl;
import andriod.bingnerdranch.kia.Project.Act_Detail_All_Home;
import andriod.bingnerdranch.kia.R;

public class Rv_All_Home_Adp extends RecyclerView.Adapter<Rv_All_Home_Adp.Rv_All_HomeViewHolder> {

    private Context context;
    List<Rv_Home_Mdl> Rv_home_mdl;

    public Rv_All_Home_Adp(List<Rv_Home_Mdl> Rv_home_mdl, Context context) {

        this.Rv_home_mdl = Rv_home_mdl;
        this.context = context ;
    }

    @NonNull
    @Override
    public Rv_All_HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_rv_all_home, parent, false);
        context = parent.getContext();
        return new Rv_All_HomeViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull Rv_All_HomeViewHolder rv_all_homeViewHolder, final int position) {

        rv_all_homeViewHolder.tv_name_prd_all_home.setText(Rv_home_mdl.get(position).getName());
        rv_all_homeViewHolder.tv_bprice_all_home.setText(Rv_home_mdl.get(position).getBefore_price());
        rv_all_homeViewHolder.tv_aprice_all_home.setText(Rv_home_mdl.get(position).getAfter_price());
        rv_all_homeViewHolder.tv_off_all_home.setText(Rv_home_mdl.get(position).getOff());

        Picasso.get().load(Rv_home_mdl.get(position).getLink_img()).into(rv_all_homeViewHolder.iv_prd_all_home);

    }

    @Override
    public int getItemCount() {return Rv_home_mdl.size();}

    public class Rv_All_HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView  tv_name_prd_all_home,  tv_bprice_all_home , tv_aprice_all_home, tv_off_all_home ;
        ImageView iv_prd_all_home ;


        public Rv_All_HomeViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_name_prd_all_home = itemView.findViewById(R.id.tv_name_prd_all_home);
            tv_bprice_all_home = itemView.findViewById(R.id.tv_bprice_all_home);
            tv_aprice_all_home = itemView.findViewById(R.id.tv_aprice_all_home);
            tv_off_all_home = itemView.findViewById(R.id.tv_off_all_home);

            iv_prd_all_home = itemView.findViewById(R.id.iv_sample_all_home);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(context, Act_Detail_All_Home.class);
            intent.putExtra(Act_Detail_All_Home.ID_Home, Rv_home_mdl.get(getAdapterPosition()).getId());
            intent.putExtra("name", Rv_home_mdl.get(getAdapterPosition()).getName());
            intent.putExtra("before_price", Rv_home_mdl.get(getAdapterPosition()).getBefore_price());
            intent.putExtra("link_img", Rv_home_mdl.get(getAdapterPosition()).getLink_img());
            intent.putExtra("after_price", Rv_home_mdl.get(getAdapterPosition()).getAfter_price());
            intent.putExtra("off", Rv_home_mdl.get(getAdapterPosition()).getOff());
            intent.putExtra("description", Rv_home_mdl.get(getAdapterPosition()).getDescription());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }
}
