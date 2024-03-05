package com.degtu.myesalon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.degtu.myesalon.databinding.ActivityBarberHomeBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarberHomeActivity extends AppCompatActivity {

    ActivityBarberHomeBinding binding;
    String IP;
    FirebaseFirestore firebaseFirestore;
    BookingAdapter bookingAdapter;
    ArrayList<Booking> bookingArrayList;
    public String[] timeslots = {"09:00 am","10:00 am","11:00 am","12:00 pm","01:00 pm","02:00 pm","03:00 pm","04:00 pm","05:00 pm","06:00 pm","07:00 pm","08:00 pm"};
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBarberHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        firebaseFirestore = FirebaseFirestore.getInstance();
        bookingArrayList = new ArrayList<>();
        bookingAdapter = new BookingAdapter(this,bookingArrayList);
        binding.yourbookingsrecyclerview.setHasFixedSize(true);
        binding.yourbookingsrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.yourbookingsrecyclerview.setAdapter(bookingAdapter);

        binding.profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarberHomeActivity.this,BarberProfileActivity.class);
                startActivity(intent);
            }
        });

        context = BarberHomeActivity.this;

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

        String deviceId = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);

        DocumentReference documentReference = firebaseFirestore.collection("salon").document(deviceId.toString());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()){
                    binding.notaddedsalontxt.setVisibility(View.GONE);
                    String salonname = value.getString("salonname");
                    String salonaddress = value.getString("salonaddress");
                    String salonimg = value.getString("salonimg");
                    String service = value.getString("service");
                    binding.salonname.setText(salonname);
                    binding.salonaddress.setText(salonaddress);
                    binding.specialservicetxt.setText(service);
                    Glide.with(getApplicationContext()).load(salonimg).placeholder(R.drawable.profilepic3).into(binding.salonimg);
                    firebaseFirestore.collection("booking").whereEqualTo("salonname",salonname).whereEqualTo("salonaddress",salonaddress).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (error!=null){
                                return;
                            }else {
                                assert value != null;
                                for (DocumentChange documentChange : value.getDocumentChanges()){
                                    bookingArrayList.add(documentChange.getDocument().toObject(Booking.class));
                                    bookingAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
                }else {
                    binding.yoursalonrelative.setVisibility(View.GONE);
                    binding.yoursalontxt.setVisibility(View.GONE);
                    binding.notaddedsalontxt.setVisibility(View.VISIBLE);
                    binding.yourbookingstxt.setVisibility(View.GONE);
                }
            }
        });
    }
}