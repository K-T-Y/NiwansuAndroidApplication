package com.example.niwansu_android_application.screens.patient.activities;

import static com.example.niwansu_android_application.core.Constants.KEY_EMAIL;
import static com.example.niwansu_android_application.core.Constants.KEY_FIRST_NAME;
import static com.example.niwansu_android_application.core.Constants.KEY_LAST_NAME;
import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.Constants;
import com.example.niwansu_android_application.core.LoginResponseModel;
import com.example.niwansu_android_application.core.NetworkClient;
import com.example.niwansu_android_application.core.NetworkService;
import com.example.niwansu_android_application.core.ResponseModel;

import java.util.Date;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDoctorActivity extends AppCompatActivity {
    CircleImageView doctorImage;
    ImageView back;
    TextView docDescription,txtAvailability;

    SharedPreferences sharedPreferences;
    CalendarView calendarView;

    public String sd = "";
    public  String time = "Not Assigned";
    public String appointmentStatus = "Pending";
    AppCompatButton btnBookAppointment;
    int imagevalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doctor);
        doctorImage = findViewById(R.id.doctorImage);
        docDescription = findViewById(R.id.doctorDescription);
        back = findViewById(R.id.back);
        calendarView = findViewById(R.id.calender1);
        btnBookAppointment = findViewById(R.id.btnBookAppointment);
        txtAvailability =findViewById(R.id.txtAvailability);

        //set calender to hide past dates
        calendarView.setMinDate((new Date().getTime()));
        btnBookAppointment.setVisibility(View.GONE);

        Intent i = getIntent();
        String DoctorName = i.getStringExtra("DoctorName");

        //Set Doctor Name and description
        if (DoctorName.equals("Dr.Vihara Pathirage"))
        {
            docDescription.setText(DoctorName + " is a member of a limited group of board certified Endocrinologist.");

        } else if (DoctorName.equals("Dr.Mahesh Pathirana")) {
            docDescription.setText(DoctorName + " is a member of a limited group of board certified Cardiologist.");

        }
        else if (DoctorName.equals("Dr.Anjela Colonne")) {
            docDescription.setText(DoctorName + " is a member of a limited group of board certified Nephrologist.");

        }
        else if (DoctorName.equals("Dr.Sachini Gallage")) {
            docDescription.setText(DoctorName + " is a member of a limited group of board certified Ophthalmologist.");

        }
        else if (DoctorName.equals("Dr.Himasha Perera")) {
            docDescription.setText(DoctorName + " is a member of a limited group of board certified Neurologist.");

        }else if (DoctorName.equals("Dr.Lalani Gamage")) {
            docDescription.setText(DoctorName + " is a member of a limited group of board certified Dermatologist.");

        }



        //Getting Loged-in user using shared preferences
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);

        //   String name = sharedPreferences.getString(KEY_NAME, null);
        String PatientEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String fname = sharedPreferences.getString(KEY_FIRST_NAME ,null);
        String lName = sharedPreferences.getString(KEY_LAST_NAME ,null);
        String patientName = fname +" "+lName;

        //GetImage of the selected doctor from previous Activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imagevalue = bundle.getInt("DocImage");
        }
        doctorImage.setImageResource(imagevalue
        );


        //when date change
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                sd = String.format("%04d/%02d/%02d", year, month + 1, dayOfMonth);
                // DateselectHidden.setText(sd);
                btnBookAppointment.setVisibility(View.VISIBLE);

                doctorcheck(DoctorName, sd);
            }
        });

        //Book appointment
        btnBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(BookDoctorActivity.this, patientName, Toast.LENGTH_SHORT).show();
              UploadDoctorSchedule(DoctorName, PatientEmail,patientName);
            }
        });


        //back button selected
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                finish();
            }
        });

    }

    public void UploadDoctorSchedule(String docName, String PatientEmail,String pName) {

        HashMap<String, String> params = new HashMap<>();
        params.put("doctorname", docName);
        params.put("patientemail", PatientEmail);
        params.put("patientname",pName);
        params.put("bookeddate", sd);
        params.put("time", time);
        params.put("doctorapprovalstatus", appointmentStatus);
        doctorschedule(params);


    }

    private void doctorschedule(HashMap<String, String> params) {
        final ProgressDialog progressDialog = new ProgressDialog(BookDoctorActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Booking...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<ResponseModel> doc = networkService.bookDoctor(params);
        doc.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);


                        Intent i = new Intent(BookDoctorActivity.this, MainActivity.class);
                        finish();
                        startActivity(i);


                    } else {
                        Toast.makeText(BookDoctorActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {


                progressDialog.dismiss();
            }
        });
    }


    private void doctorcheck(String doctorname, String bookeddate) {

        final ProgressDialog progressDialog = new ProgressDialog(BookDoctorActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Getting doctor schedule ready...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        System.out.println(doctorname +" "+ bookeddate);
        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<LoginResponseModel> doctorcheck = networkService.doctorcheck(doctorname, bookeddate);
        doctorcheck.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                LoginResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Constants.KEY_ISE_LOGGED_IN, true);
                        editor.putString(Constants.KEY_DOCTOR_NAME, responseBody.getUserDetailObject().getUserDetails().get(0).getDoctorname());
                        editor.putString(Constants.KEY_BOOKED_DATE, responseBody.getUserDetailObject().getUserDetails().get(0).getBookeddate());
                        editor.putString(Constants.KEY_APPROVAL_STATUS, responseBody.getUserDetailObject().getUserDetails().get(0).getDoctorapprovalstatus());


                        editor.apply();


                        Toast.makeText(BookDoctorActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                        txtAvailability.setText("Not Available");
                        btnBookAppointment.setVisibility(View.GONE);
                    } else if (responseBody.getSuccess().equals("0")) {
//
                        txtAvailability.setText("Available");
                        btnBookAppointment.setVisibility(View.VISIBLE);
                        Toast.makeText(BookDoctorActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }


}