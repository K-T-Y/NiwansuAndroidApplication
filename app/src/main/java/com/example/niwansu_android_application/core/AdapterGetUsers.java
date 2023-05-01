package com.example.niwansu_android_application.core;

import static android.content.Context.MODE_PRIVATE;
import static com.example.niwansu_android_application.core.Constants.KEY_EMAIL;
import static com.example.niwansu_android_application.core.Constants.KEY_FIRST_NAME;
import static com.example.niwansu_android_application.core.Constants.KEY_LAST_NAME;
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

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.screens.patient.activities.ConversationActivity;
import com.zegocloud.zimkit.services.ZIMKit;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import im.zego.zim.enums.ZIMErrorCode;

public class AdapterGetUsers extends RecyclerView.Adapter <AdapterGetUsers.MyViewHolder>{

    private List<Users> doctorlist;
    private Context context;

    SharedPreferences sharedPreferences;


    String uId = null;
    String uName = null;


    public AdapterGetUsers(List<Users> doctorlist, Context context) {
        this.doctorlist = doctorlist;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_chat, parent, false);

        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.doctorName.setText(""+doctorlist.get(position).getName()+" "+doctorlist.get(position).getLastname());

        holder.Occupation.setText(""+doctorlist.get(position).getOccupation());


        String fName = doctorlist.get(position).getName();
        String lName = doctorlist.get(position).getLastname();


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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             buttonClick(fName,lName);
            }
        });

        if ((position %2)==0 )
        {
            holder.rL1.setBackgroundColor(Color.parseColor("#8BCC8F"));
        } else  {
            holder.rL1.setBackgroundColor(Color.parseColor("#D1F0CF"));
        }

    }

    @Override
    public int getItemCount() {
        return doctorlist.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView doctorName,Occupation;
        CircleImageView image;

        RelativeLayout rL1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.DocName);
            Occupation = itemView.findViewById(R.id.txtOccupation);
            image = itemView.findViewById(R.id.DocImage);
            rL1 = itemView.findViewById(R.id.rL1);
        }


    }

    public void buttonClick(String fname,String lname) {
        sharedPreferences =this.context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String userId = KEY_EMAIL;
        String userName = KEY_FIRST_NAME ;
        String userAvatar =   "https://storage.zego.im/IMKit/avatar/avatar-1.png";; // The image you set as the user avatar must be network image. e.g., https://storage.zego.im/IMKit/avatar/avatar-0.png
        connectUser(userId, userName,userAvatar,fname,lname);
    }
    public void connectUser(String userId, String userName,String userAvatar,String fname,String lname) {
        // Logs in.
        ZIMKit.connectUser(userId,userName,userAvatar, errorInfo -> {
            if (errorInfo.code == ZIMErrorCode.SUCCESS) {
                // Operation after successful login. You will be redirected to other modules only after successful login. In this sample code, you will be redirected to the conversation module.
                toConversationActivity(fname,lname);
            } else {

            }
        });
    }

    // Integrate the conversation list into your Activity as a Fragment
    private void toConversationActivity(String fname, String lname) {
        // Redirect to the conversation list (Activity) you created.
        Intent intent = new Intent(context, ConversationActivity.class);
        intent.putExtra("userID",fname);
        intent.putExtra("userName",lname);
        context.startActivity(intent);

    }




}
