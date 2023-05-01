package com.example.niwansu_android_application.screens.patient.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.NetworkClient;
import com.example.niwansu_android_application.core.NetworkService;
import com.example.niwansu_android_application.core.ResponseModel;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterDetailsActivity extends AppCompatActivity {

    TextInputEditText etMobile, etFirstName, etSurename,  etCity,etPasswordOne,etReenterPassword,etEmail,etOccupations;
    Button buttonRegister;
    TextView linklogin, abcd;
    ImageView imgBack, upload_image;
    protected TextView txtPatient;
    private Bitmap bitmap;

    AutoCompleteTextView etSex,etDistrict,etProvince;

    private String picture,date;
    //private CircleImageView mPicture;
    ImageView done;

    ArrayAdapter<String> adapterItems,adapterProvince,adapterDistrict;
    String [] item = {"Male", "Female","Other"};
    String [] provinceitem = {"Central Province","Eastern Province","Northern Province","Southern Province","Western Province","North Western Province","North Central Province","Uva Province","Sabaragamuwa Province"};

    String [] districtitem = {"Ampara","Anuradhapura","Badulla","Batticaloa","Colombo","Galle","Gampaha","Hambantota","Jaffna","Kalutara","Kandy","Kegalle","Kilinochchi","Kurunegala","Mannar","Matale","Matara","Monaragala","Mullaitivu","Nuwara Eliya","Polonnaruwa","Puttalam","Ratnapura","Trincomalee","Vavuniya"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);

        done = findViewById(R.id.done);
        etMobile = findViewById(R.id.etMobile);
        etFirstName = findViewById(R.id.etFirstName);
        etSurename = findViewById(R.id.etSurename);
        etProvince = findViewById(R.id.etProvince);
        etDistrict = findViewById(R.id.etDistrict);
        etCity = findViewById(R.id.etCity);
        etPasswordOne = findViewById(R.id.etPasswordOne);
        etReenterPassword = findViewById(R.id.etReenterPassword);
        etEmail = findViewById(R.id.etEmail);
        etSex = findViewById(R.id.etSex);
        etOccupations = findViewById(R.id.etOccupation);
        upload_image = findViewById(R.id.upload_image);

        //get current date
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        //set string to set the user category as patient
        String category = "patient";


        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,item);
        adapterProvince = new ArrayAdapter<String>(this,R.layout.list_items,provinceitem);
        adapterDistrict = new ArrayAdapter<String>(this,R.layout.list_items,districtitem);

        etSex.setAdapter(adapterItems);
        etSex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
                //  Toast.makeText(MainActivity.this, "Item", Toast.LENGTH_SHORT).show();
               // btnContinue.setVisibility(View.VISIBLE);



            }
        });

        etProvince.setAdapter(adapterProvince);
        etProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String provinceitem =adapterView.getItemAtPosition(i).toString();
                Toast.makeText(EnterDetailsActivity.this, provinceitem, Toast.LENGTH_SHORT).show();
                // btnContinue.setVisibility(View.VISIBLE);



            }
        });
        etDistrict.setAdapter(adapterDistrict);
        etDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String provinceitem =adapterView.getItemAtPosition(i).toString();
                //  Toast.makeText(MainActivity.this, "Item", Toast.LENGTH_SHORT).show();
                // btnContinue.setVisibility(View.VISIBLE);



            }
        });



        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                upload_image.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();

                            }


                        }

                    }
                });


//Upload profile image - get from gallery

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();


//                Intent intent1 = new Intent(Intent.ACTION_PICK);
//                intent1.setType("image/*");
//                intent1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                activityResultLauncher.launch(intent1);

            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if (etMobile.getText().toString().length() !=10 ) {
                    Toast.makeText(EnterDetailsActivity.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                }
                else if (etFirstName.getText().toString().equals("")) {
                    Toast.makeText(EnterDetailsActivity.this, "Enter first name", Toast.LENGTH_SHORT).show();
                } else if (etSurename.getText().toString().equals("")) {
                    Toast.makeText(EnterDetailsActivity.this, "Enter Last name", Toast.LENGTH_SHORT).show();
                } else if (etEmail.getText().toString().equals("")) {
                    Toast.makeText(EnterDetailsActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(etEmail.getText().toString())) {
                    Toast.makeText(EnterDetailsActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if (etProvince.getText().toString().equals("")) {
                    Toast.makeText(EnterDetailsActivity.this, "Enter Province", Toast.LENGTH_SHORT).show();
                } else if (etDistrict.getText().toString().equals("")) {
                    Toast.makeText(EnterDetailsActivity.this, "Enter District", Toast.LENGTH_SHORT).show();
                } else if (etCity.getText().toString().equals("") ) {
                    Toast.makeText(EnterDetailsActivity.this, "Enter City", Toast.LENGTH_SHORT).show();
                }
                 else if (etSex.getText().toString().equals("")) {
                     Toast.makeText(EnterDetailsActivity.this, "Select Sex", Toast.LENGTH_SHORT).show();
                 } else if (etOccupations.getText().toString().equals("") ) {
                     Toast.makeText(EnterDetailsActivity.this, "Select Occupation", Toast.LENGTH_SHORT).show();
                 }
                else if (!etPasswordOne.getText().toString().equals(etReenterPassword.getText().toString())) {
                    Toast.makeText(EnterDetailsActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (etPasswordOne.getText().toString().length() < 8 && !isValidPassword(etPasswordOne.getText().toString())) {
                    Toast.makeText(EnterDetailsActivity.this, "Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character", Toast.LENGTH_SHORT).show();
                }


                 else {
                     String picture = null;
                     if (bitmap == null) {
                         picture = "";
                     } else {
                         picture = getStringImage(bitmap);
                         // picture = getRoundedCroppedBitmap(bitmap);

                     }
                     System.out.println(picture);
                     UpdateDataSet(date, category, picture);
                 }


            }
        });

    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                upload_image.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public void UpdateDataSet(String date,String category, String picture) {

        Toast.makeText(this, category, Toast.LENGTH_SHORT).show();
        HashMap<String, String> params = new HashMap<>();
        params.put("name", etFirstName.getText().toString());
        params.put("lastname", etSurename.getText().toString());
        params.put("sex", etSex.getText().toString());
        params.put("occupation", etOccupations.getText().toString());
        params.put("email", etEmail.getText().toString());
        params.put("password", etPasswordOne.getText().toString());
        params.put("passwordtwo", etReenterPassword.getText().toString());
        params.put("mobile", etMobile.getText().toString());
        params.put("province", etProvince.getText().toString());
        params.put("district", etDistrict.getText().toString());
        params.put("city", etCity.getText().toString());
        params.put("date",String.valueOf(date));
        params.put("usercategory",String.valueOf(category));
        params.put("profilepicture", picture);

        register(params);
    }
    private void register(HashMap<String, String> params) {

        final ProgressDialog progressDialog = new ProgressDialog(EnterDetailsActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<ResponseModel> registerCall = networkService.register(params);
        registerCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(EnterDetailsActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                       Toast.makeText(EnterDetailsActivity.this, "okayyyyyy", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EnterDetailsActivity.this, LoginActivity.class);
                        intent.putExtra("email",etEmail.getText().toString());
                        intent.putExtra("password",etPasswordOne.getText().toString());

                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(EnterDetailsActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
          Toast.makeText(EnterDetailsActivity.this, "okay", Toast.LENGTH_SHORT).show();
            }
        });
    }
}