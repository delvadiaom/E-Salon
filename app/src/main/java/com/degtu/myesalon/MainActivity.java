package com.degtu.myesalon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.degtu.myesalon.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    FirebaseStorage storage;
    String IP;
    Uri selectedImage;
    FirebaseDatabase database;
    Context context;
    RadioButton radioButtonforsalontype;
    RadioButton getRadioButtonforservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        getSupportActionBar().hide();

        context = MainActivity.this;

        binding.salonimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,75);
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    URL myIp = new URL("https://checkip.amazonaws.com/");

                    URLConnection connection = myIp.openConnection();
                    connection.setConnectTimeout(1000);
                    connection.setReadTimeout(1000);

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    IP = bufferedReader.readLine();
                }catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (selectedImage!=null){
                    StorageReference reference = storage.getReference().child("Salon Images").child(binding.salonname.getText().toString());
                    reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String salonname = binding.salonname.getText().toString();
                                        String salonaddress = binding.salonaddress.getText().toString();
                                        String city = binding.city.getText().toString();
                                        String pincode = binding.pincode.getText().toString();
                                        String state = binding.state.getText().toString();
                                        String salonimage = uri.toString();
                                        int selectIdForGender = binding.radiogroup2.getCheckedRadioButtonId();
                                        radioButtonforsalontype = findViewById(selectIdForGender);
                                        int selectedIdForService = binding.radiogroup.getCheckedRadioButtonId();
                                        getRadioButtonforservice = findViewById(selectedIdForService);
                                        String gender = String.valueOf(radioButtonforsalontype.getText().toString());
                                        String service = String.valueOf(getRadioButtonforservice.getText().toString());
                                        String deviceId = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);

                                            Salon salon = new Salon(salonname,salonaddress,city,pincode,state,deviceId.toString(),salonimage,gender,service);

                                            firebaseFirestore.collection("salon").document(deviceId.toString()).set(salon).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(MainActivity.this, "Salon Added", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    }
                                });
                            }
                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this, "No salon image selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (data.getData()!=null){
                binding.salonimg.setImageURI(data.getData());
                selectedImage = data.getData();
            }
        }
    }
}