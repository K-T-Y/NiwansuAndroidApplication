package com.example.niwansu_android_application.core;

import static android.content.Context.MODE_PRIVATE;
import static com.example.niwansu_android_application.core.Constants.KEY_EMAIL;
import static com.example.niwansu_android_application.core.Constants.KEY_FIRST_NAME;
import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.screens.patient.activities.ConversationActivity;
import com.zegocloud.zimkit.services.ZIMKit;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import im.zego.zim.enums.ZIMErrorCode;

public class AdapterGetPatientList extends RecyclerView.Adapter <AdapterGetPatientList.MyViewHolder>{

    private List<Users> doctorlist;
    private Context context;

    SharedPreferences sharedPreferences;



    String uName = null;


    public AdapterGetPatientList(List<Users> doctorlist, Context context) {
        this.doctorlist = doctorlist;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list, parent, false);

        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.doctorName.setText(""+doctorlist.get(position).getName()+" "+doctorlist.get(position).getLastname());
        holder.Occupation.setText(""+doctorlist.get(position).getOccupation());
        holder.EmailPeer.setText(""+doctorlist.get(position).getEmail());
        holder.mobile.setText(""+doctorlist.get(position).getMobile());
        String PeerEmail = doctorlist.get(position).getEmail();
        String PeerUserName = doctorlist.get(position).getName();


        String base64 =  doctorlist.get(position).getProfilepicture();
        if (base64!=null )
        {
            byte[] imgdate = Base64.decode(base64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(imgdate, 0, imgdate.length);
            holder.image.setImageBitmap(decodedByte);
        }
        else
        {
            holder.image.setImageBitmap(null);
        }





    }

    @Override
    public int getItemCount() {
        return doctorlist.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView doctorName,Occupation,EmailPeer,mobile;
        CircleImageView image;

        RelativeLayout rL1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.DocName);
            Occupation = itemView.findViewById(R.id.txtOccupation);
            image = itemView.findViewById(R.id.DocImage);
            mobile = itemView.findViewById(R.id.txtMobile);
            rL1 = itemView.findViewById(R.id.rL1);
            EmailPeer = itemView.findViewById(R.id.txtEmail);
        }


    }





}
