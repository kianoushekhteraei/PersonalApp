
package andriod.bingnerdranch.kia.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import andriod.bingnerdranch.kia.Project.APIClient;
import andriod.bingnerdranch.kia.Project.APIInterface;
import andriod.bingnerdranch.kia.Project.Act_Edit_Profile;
import andriod.bingnerdranch.kia.Project.Act_Exit_Profile;
import andriod.bingnerdranch.kia.Project.MainActivity;
import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Frg_Profile extends Fragment {

    APIInterface request;
//  PassingData passingData ;
    //pushing into github

    SharedPreferences sharedPreferences;

    TextView tv_name_frg_profile, tv_city_frg_profile, tv_gender_frg_profile, tv_number_frg_profile, tv_email_frg_profile, tv_code_frg_profile;
    ImageView iv_frg_profile;

    private boolean isFromEdit = false ;
    private boolean isFromSign = true ;

    @BindView(R.id.tv_logout)
    TextView tv_logout;
    @BindView(R.id.tv_edit_profile)
    TextView tv_edit_profile;

    @OnClick(R.id.tv_logout)
    public void tv_logout() {

        Intent i = new Intent(getContext(), Act_Exit_Profile.class);
        startActivity(i);
    }

    @OnClick(R.id.tv_edit_profile)
    public void tv_edit_profile() {

        Intent intent = new Intent(getActivity(), Act_Edit_Profile.class);
        if (isFromEdit) {
            intent.putExtra("detail_edit_profile", true);
            intent.putExtra("tv_name_frg_profile", tv_name_frg_profile.getText().toString());
            intent.putExtra("tv_gender_frg_profile", tv_gender_frg_profile.getText().toString());
            intent.putExtra("tv_city_frg_profile", tv_city_frg_profile.getText().toString());
            intent.putExtra("tv_email_frg_profile", tv_email_frg_profile.getText().toString());
            intent.putExtra("tv_code_frg_profile", tv_code_frg_profile.getText().toString());
            intent.putExtra("tv_number_frg_profile", tv_number_frg_profile.getText().toString());
            iv_frg_profile.buildDrawingCache();
            Bitmap bitmap = iv_frg_profile.getDrawingCache();
            intent.putExtra("iv_frg_profile", bitmap);

        }
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_profile, container, false);
        ButterKnife.bind(this, view);
        request = APIClient.getApiClient("http://192.168.1.4/kia/").create(APIInterface.class);

        try {
            isFromEdit = getArguments().getBoolean("isFromEdit");
            isFromSign = getArguments().getBoolean("isFromSign");
        } catch (Exception e) {

        }

        //user from main not edit
        //show old values
        if (isFromSign) {
            tv_name_frg_profile = view.findViewById(R.id.tv_name_frg_profile);
            tv_city_frg_profile = view.findViewById(R.id.tv_city_frg_profile);
            tv_gender_frg_profile = view.findViewById(R.id.tv_gender_frg_profile);
            tv_number_frg_profile = view.findViewById(R.id.tv_number_frg_profile);
            tv_email_frg_profile = view.findViewById(R.id.tv_email_frg_profile);
            tv_code_frg_profile = view.findViewById(R.id.tv_code_frg_profile);
            iv_frg_profile = view.findViewById(R.id.iv_frg_profile);

//            String name_profile = getArguments().getString("name_sign_up");
//            String gender_profile = getArguments().getString("gender_sign_up");
//            String city_profile = getArguments().getString("city_sign_up");
//            String email_profile = getArguments().getString("email_sign_up");
//            String code_profile = getArguments().getString("code_sign_up");

            String name_profile = sharedPreferences.getString("name_sign_up", "a");
            String gender_profile = sharedPreferences.getString("gender_sign_up", "b");
            String city_profile = sharedPreferences.getString("city_sign_up", "c");
            String email_profile = sharedPreferences.getString("email_sign_up", "d");
            String code_profile = sharedPreferences.getString("code_sign_up", "e");

            tv_name_frg_profile.setText(name_profile);
            tv_gender_frg_profile.setText(gender_profile);
            tv_city_frg_profile.setText(city_profile);
            tv_email_frg_profile.setText(email_profile);
            tv_code_frg_profile.setText(code_profile);
        }
        if (isFromEdit) {
            //isFromEdit is true and we must do changes
            tv_name_frg_profile = view.findViewById(R.id.tv_name_frg_profile);
            tv_city_frg_profile = view.findViewById(R.id.tv_city_frg_profile);
            tv_gender_frg_profile = view.findViewById(R.id.tv_gender_frg_profile);
            tv_number_frg_profile = view.findViewById(R.id.tv_number_frg_profile);
            tv_email_frg_profile = view.findViewById(R.id.tv_email_frg_profile);
            tv_code_frg_profile = view.findViewById(R.id.tv_code_frg_profile);
            iv_frg_profile = view.findViewById(R.id.iv_frg_profile);

            String name_profile = getArguments().getString("name_profile");
            String gender_profile = getArguments().getString("gender_profile");
            String city_profile = getArguments().getString("city_profile");
            String email_profile = getArguments().getString("email_profile");
            String code_profile = getArguments().getString("code_profile");
            String number_profile = getArguments().getString("number_profile");

            byte[] byteArray = getArguments().getByteArray("image_profile");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            tv_name_frg_profile.setText(name_profile);
            tv_gender_frg_profile.setText(gender_profile);
            tv_city_frg_profile.setText(city_profile);
            tv_email_frg_profile.setText(email_profile);
            tv_code_frg_profile.setText(code_profile);
            tv_number_frg_profile.setText(number_profile);
            iv_frg_profile.setImageBitmap(bmp);
        }

        return view;
    }

//    public interface PassingData{
//
//        public void sendData(String data);
//    }
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        passingData = (PassingData) context ;
//    }
//
//    public void passData(String data) {
//        passingData.sendData(data);
//    }

}
