package com.example.niwansu_android_application.screens.doctor.fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.AdapterHorizontalUsers;
import com.example.niwansu_android_application.core.Constants;
import com.example.niwansu_android_application.core.HorizontalUserModel;
import com.example.niwansu_android_application.core.NetworkClient;
import com.example.niwansu_android_application.core.NetworkService;
import com.example.niwansu_android_application.core.Users;
import com.example.niwansu_android_application.screens.doctor.activities.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

View view;
TextView txt_name;
CircleImageView pro_pic;
RecyclerView storyRV;
    ArrayList<HorizontalUserModel> storyList;
    SharedPreferences sharedPreferences;

    ProfileFragment profileFragment = new ProfileFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home2, container, false);
        txt_name=view.findViewById( R.id.txt_name);
        pro_pic=view.findViewById(R.id.pro_pic);
        storyRV=view.findViewById(R.id.storyRV);
        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = "Dr."+(sharedPreferences.getString(Constants.KEY_FIRST_NAME,null))+" "+(sharedPreferences.getString(Constants.KEY_LAST_NAME,null));
        txt_name.setText(name);
        get_all_stories();
        //get profile Image
        if(sharedPreferences.getString(Constants.KEY_PROFILE_PIC, null)!=null)
        {
            String base64 = sharedPreferences.getString(Constants.KEY_PROFILE_PIC, null);
            byte[] imgdate = Base64.decode(base64, Base64.DEFAULT);
//                 String text = new String(imgdate, StandardCharsets.UTF_8);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(imgdate, 0, imgdate.length);
            pro_pic.setImageBitmap(decodedByte);
        }

        pro_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                return;
            }
        });


        return view;
    }
    private void get_all_stories() {
        storyList = new ArrayList<>();
        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<List<Users>> call = networkService.getUsers();

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                List<Users> data = response.body();
                for (int i = 0; i < data.size(); i++) {
                    String encodedImage = data.get(i).getProfilepicture();
                    String name = data.get(i).getName()+" "+data.get(i).getLastname();


                    storyList.add(new HorizontalUserModel(encodedImage,name));
                    AdapterHorizontalUsers adapter = new AdapterHorizontalUsers(storyList, getContext());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
                    storyRV.setLayoutManager(layoutManager);

                    storyRV.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });

    }
}