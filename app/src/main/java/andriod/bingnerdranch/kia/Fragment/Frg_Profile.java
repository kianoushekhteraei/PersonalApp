package andriod.bingnerdranch.kia.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import andriod.bingnerdranch.kia.Project.APIClient;
import andriod.bingnerdranch.kia.Project.APIInterface;
import andriod.bingnerdranch.kia.Project.Act_Edit_Profile;
import andriod.bingnerdranch.kia.Project.Act_Exit_Profile;
import andriod.bingnerdranch.kia.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Frg_Profile extends Fragment {

    APIInterface request;

    TextView tv_name_frg_profile, tv_city_frg_profile, tv_gender_frg_profile, tv_number_frg_profile, tv_email_frg_profile, tv_code_frg_profile;

    private boolean isFromEdit = false;

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
            intent.putExtra("detail_edit_profile",true);
            intent.putExtra("tv_name_frg_profile", tv_name_frg_profile.getText().toString());
            intent.putExtra("tv_gender_frg_profile", tv_gender_frg_profile.getText().toString());
            intent.putExtra("tv_city_frg_profile", tv_city_frg_profile.getText().toString());
            intent.putExtra("tv_email_frg_profile", tv_email_frg_profile.getText().toString());
            intent.putExtra("tv_code_frg_profile", tv_code_frg_profile.getText().toString());
            intent.putExtra("tv_number_frg_profile", tv_number_frg_profile.getText().toString());
        }
        startActivity(intent);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_profile, container, false);
        ButterKnife.bind(this, view);
        request = APIClient.getApiClient("http://192.168.43.167/kia/").create(APIInterface.class);

        try {
            isFromEdit = getArguments().getBoolean("isFromEdit");
        } catch (Exception e) {

        }
        if (isFromEdit) {
            //isFromEdit is true and we must do changes
            tv_name_frg_profile = view.findViewById(R.id.tv_name_frg_profile);
            tv_city_frg_profile = view.findViewById(R.id.tv_city_frg_profile);
            tv_gender_frg_profile = view.findViewById(R.id.tv_gender_frg_profile);
            tv_number_frg_profile = view.findViewById(R.id.tv_number_frg_profile);
            tv_email_frg_profile = view.findViewById(R.id.tv_email_frg_profile);
            tv_code_frg_profile = view.findViewById(R.id.tv_code_frg_profile);
            String name_profile = getArguments().getString("name_profile");
            String gender_profile = getArguments().getString("gender_profile");
            String city_profile = getArguments().getString("city_profile");
            String email_profile = getArguments().getString("email_profile");
            String code_profile = getArguments().getString("code_profile");
            String number_profile = getArguments().getString("number_profile");
            tv_name_frg_profile.setText(name_profile);
            tv_gender_frg_profile.setText(gender_profile);
            tv_city_frg_profile.setText(city_profile);
            tv_email_frg_profile.setText(email_profile);
            tv_code_frg_profile.setText(code_profile);
            tv_number_frg_profile.setText(number_profile);

            //  Log.e("NameProfile", name_profile);

        } else {
            //user from main not edit
            //show old values

        }

            return view;

    }
}
