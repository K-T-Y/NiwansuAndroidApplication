package com.example.niwansu_android_application.core;


import com.example.niwansu_android_application.models.changeStatusModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkService {
    @FormUrlEncoded

    @POST("register.php")
    Call<ResponseModel> register(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponseModel> login1(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("checkdoctoravailability.php")
    Call<LoginResponseModel> doctorcheck(@Field("doctorname") String doctorname, @Field("bookeddate") String bookeddate);

    @FormUrlEncoded
    @POST("BookDoctor.php")
    Call<ResponseModel> bookDoctor(@FieldMap HashMap<String, String> params);





    @GET("fetchappointmentdetails.php")
    Call<List<AppointmentsList>> getAppointments(

            @Query("patientemail") String patientemail

    );
    @GET("fetchappointmentdetailsondate.php")
    Call<List<AppointmentsList>> getAppointmentsondate(

            @Query("patientemail") String patientemail,
            @Query("bookeddate") String date

    );

    @GET("getDoctorsList.php")
    Call<List<Users>> getDoctorList(

            @Query("usercategory") String usercategory

    );

    @GET("getPatientsList.php")
    Call<List<Users>> getPatientsList(

            @Query("usercategory") String usercategory

    );
    @GET("fetchappointmentdetailsondateDoc.php")
    Call<List<AppointmentsList>> getAppointmentsondateDoc(

            @Query("name") String name,
            @Query("bookeddate") String date

    );

    @GET("fetchappointmentdetailsDoc.php")
    Call<List<AppointmentsList>> getAppointmentsDoctor(

            @Query("name") String name

    );


    @FormUrlEncoded
    @POST("updateStatus.php")
    Call<changeStatusModel> changeStatus(@Field("req_id") int req_id, @Field("status") String status,@Field("time") String time);

//    @FormUrlEncoded
//    @POST("profile.php")
//    Call<SetupProfileResponseModel> profile(@FieldMap HashMap<String, String> params);
//
//    @FormUrlEncoded
//    @POST("TestHistory.php")
//    Call<QuestionnaireResponseModel> upload(@FieldMap HashMap<String, String> params);
//
//
//    @FormUrlEncoded
//    @POST("DoctorSchedule.php")
//    Call<DocResponseModel> doctorschedule(@FieldMap HashMap<String, String> params);
//
//
//
//
//    @GET("getdoctorscheduledates.php")
//    Call<List<Users>> getUsers(
//
//            @Query("key") String key,
//            @Query("doctorid") String doctorid
//
//    );
//    @GET("getdoctorscheduleddatesevening.php")
//    Call<List<Users>> getUsers2(
//
//            @Query("key") String key,
//            @Query("doctorid") String doctorid
//
//    );
//
//    @GET("FetchNotification.php")
//    Call<List<Users>> getNotification(
//
//            @Query("patientid") String patientid
//
//    );
//    @GET("getTestResults.php")
//    Call<List<TestResults>> getTestResults(
//
//            @Query("userid") String patientid
//
//    );
////    @FormUrlEncoded
////    @POST("appointmentaccept.php")
////    Call<AppointmentResponseModel> doctorappointmentaccept(@FieldMap HashMap<String, String> params);
//
//
//    @FormUrlEncoded
//    @POST("appointmentaccept.php")
//    Call<AppointmentResponseModel> doctorappointmentaccept(
//            @Field("key") String key,
//            @Field("id") int id,
//            @Field("doctorapprovalstatus") String appAccept,
//              @Field("timeslotassigned") String timeslot);
//
//    @FormUrlEncoded
//    @POST("appointmentaccept.php")
//    Call<AppointmentResponseModel> doctorappointmentdeclined(
//            @Field("key") String key,
//            @Field("id") int id,
//            @Field("doctorapprovalstatus") String appAccept);
//


}
