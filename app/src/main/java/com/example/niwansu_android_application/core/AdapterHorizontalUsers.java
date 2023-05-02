package com.example.niwansu_android_application.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.niwansu_android_application.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterHorizontalUsers extends RecyclerView.Adapter<AdapterHorizontalUsers.viewHolder> {

        ArrayList<HorizontalUserModel> list;
        Context context;

public AdapterHorizontalUsers(ArrayList<HorizontalUserModel> list, Context context) {
        this.list = list;
        this.context = context;
        }

@NonNull
@Override
public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drawer_item_user, parent, false);
        return new viewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull viewHolder holder, int position) {
       HorizontalUserModel model = list.get(position);
       holder.name.setText(model.getUsername());
       System.out.println("Amo AMO"+model.getUsername());
       if(model.getImage()!=null)
       {
           String base64 = model.getImage();
           byte[] imgdate = Base64.decode(base64, Base64.DEFAULT);
           Bitmap decodedByte = BitmapFactory.decodeByteArray(imgdate, 0, imgdate.length);
           holder.profile.setImageBitmap(decodedByte);

       }

        }

@Override
public int getItemCount() {
        return list.size();
        }

public class viewHolder extends RecyclerView.ViewHolder {
    CircleImageView profile;
    TextView name;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.txt_name);
        profile = itemView.findViewById(R.id.img_profile);

    }
}
}
