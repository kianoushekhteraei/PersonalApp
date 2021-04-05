package andriod.bingnerdranch.kia.Adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import andriod.bingnerdranch.kia.Model.Slider_Img_Mdl;
import andriod.bingnerdranch.kia.R;

public class Slider_Home_Adp extends PagerAdapter {

    private List<Slider_Img_Mdl> imageModelList;
    private LayoutInflater inflater;
    private Context context;


    public Slider_Home_Adp(Context context, List<Slider_Img_Mdl> imageModelList) {
        this.context = context;
        this.imageModelList = imageModelList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageModelList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slider_sample, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.iv_slider);


       Picasso.get().load(imageModelList.get(position).getLink_img()).into(imageView);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
