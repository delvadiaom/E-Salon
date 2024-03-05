package com.degtu.myesalon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.bumptech.glide.Glide;
import com.degtu.myesalon.databinding.ActivityDetailSalonBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DetailSalonActivity extends AppCompatActivity {

    ActivityDetailSalonBinding binding;
    FirebaseFirestore firebaseFirestore;
    public String salonname;
    FirebaseAuth auth;
    FirebaseDatabase database;
    public String ip;
    public String IP;
    public String Time;
    Context context;
    public String[] timeslots = {"09:00 am","10:00 am","11:00 am","12:00 pm","01:00 pm","02:00 pm","03:00 pm","04:00 pm","05:00 pm","06:00 pm","07:00 pm","08:00 pm"};
    public String[] services = {"Haircut","Scalp Detox","Shave","Clean Up"};
    public String[] hours = {"9","10","11","12","1","2","3","4","5","6","7","8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailSalonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        LocalTime localTime = LocalTime.now();
        int rightNowHour = Integer.parseInt(localTime.format(DateTimeFormatter.ofPattern("hh")));
        String ampm = localTime.format(DateTimeFormatter.ofPattern("a"));

        if (rightNowHour==10 && ampm.equals("am")){
            binding.card1.setVisibility(View.GONE);
        }
        else if (rightNowHour==11 && ampm.equals("am")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
        }
        else if (rightNowHour==12 && ampm.equals("pm")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
            binding.card3.setVisibility(View.GONE);
        }
        else if (rightNowHour==1 && ampm.equals("pm")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
            binding.card3.setVisibility(View.GONE);
            binding.card4.setVisibility(View.GONE);
        }
        else if (rightNowHour==2 && ampm.equals("pm")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
            binding.card3.setVisibility(View.GONE);
            binding.card4.setVisibility(View.GONE);
            binding.card5.setVisibility(View.GONE);
        }
        else if (rightNowHour==3 && ampm.equals("pm")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
            binding.card3.setVisibility(View.GONE);
            binding.card4.setVisibility(View.GONE);
            binding.card5.setVisibility(View.GONE);
            binding.card6.setVisibility(View.GONE);
        }
        else if (rightNowHour==4 && ampm.equals("pm")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
            binding.card3.setVisibility(View.GONE);
            binding.card4.setVisibility(View.GONE);
            binding.card5.setVisibility(View.GONE);
            binding.card6.setVisibility(View.GONE);
            binding.card7.setVisibility(View.GONE);
        }
        else if (rightNowHour==5 && ampm.equals("pm")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
            binding.card3.setVisibility(View.GONE);
            binding.card4.setVisibility(View.GONE);
            binding.card5.setVisibility(View.GONE);
            binding.card6.setVisibility(View.GONE);
            binding.card7.setVisibility(View.GONE);
            binding.card8.setVisibility(View.GONE);
        }
        else if (rightNowHour==6 && ampm.equals("pm")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
            binding.card3.setVisibility(View.GONE);
            binding.card4.setVisibility(View.GONE);
            binding.card5.setVisibility(View.GONE);
            binding.card6.setVisibility(View.GONE);
            binding.card7.setVisibility(View.GONE);
            binding.card8.setVisibility(View.GONE);
            binding.card9.setVisibility(View.GONE);
        }
        else if (rightNowHour==7 && ampm.equals("pm")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
            binding.card3.setVisibility(View.GONE);
            binding.card4.setVisibility(View.GONE);
            binding.card5.setVisibility(View.GONE);
            binding.card6.setVisibility(View.GONE);
            binding.card7.setVisibility(View.GONE);
            binding.card8.setVisibility(View.GONE);
            binding.card9.setVisibility(View.GONE);
            binding.card10.setVisibility(View.GONE);
        }
        else if (rightNowHour==8 && ampm.equals("pm")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
            binding.card3.setVisibility(View.GONE);
            binding.card4.setVisibility(View.GONE);
            binding.card5.setVisibility(View.GONE);
            binding.card6.setVisibility(View.GONE);
            binding.card7.setVisibility(View.GONE);
            binding.card8.setVisibility(View.GONE);
            binding.card9.setVisibility(View.GONE);
            binding.card10.setVisibility(View.GONE);
            binding.card11.setVisibility(View.GONE);
        }else if (rightNowHour>=9 && ampm.equals("pm")){
            binding.card1.setVisibility(View.GONE);
            binding.card2.setVisibility(View.GONE);
            binding.card3.setVisibility(View.GONE);
            binding.card4.setVisibility(View.GONE);
            binding.card5.setVisibility(View.GONE);
            binding.card6.setVisibility(View.GONE);
            binding.card7.setVisibility(View.GONE);
            binding.card8.setVisibility(View.GONE);
            binding.card9.setVisibility(View.GONE);
            binding.card10.setVisibility(View.GONE);
            binding.card11.setVisibility(View.GONE);
            binding.card12.setVisibility(View.GONE);
            binding.selectyourtime.setText("Boooking is not open for now");
            binding.selectyourtime.setGravity(View.TEXT_ALIGNMENT_CENTER);
            binding.selectyourservice.setVisibility(View.GONE);
            binding.servicegridlayout.setVisibility(View.GONE);
            binding.callsalon.setVisibility(View.GONE);
        }
        else {
            binding.card1.setVisibility(View.VISIBLE);
            binding.card2.setVisibility(View.VISIBLE);
            binding.card3.setVisibility(View.VISIBLE);
            binding.card4.setVisibility(View.VISIBLE);
            binding.card5.setVisibility(View.VISIBLE);
            binding.card6.setVisibility(View.VISIBLE);
            binding.card7.setVisibility(View.VISIBLE);
            binding.card8.setVisibility(View.VISIBLE);
            binding.card9.setVisibility(View.VISIBLE);
            binding.card10.setVisibility(View.VISIBLE);
            binding.card11.setVisibility(View.VISIBLE);
            binding.card12.setVisibility(View.VISIBLE);
            binding.selectyourtime.setText("Select your time");
            binding.selectyourtime.setGravity(View.TEXT_ALIGNMENT_CENTER);
            binding.selectyourservice.setVisibility(View.VISIBLE);
            binding.servicegridlayout.setVisibility(View.VISIBLE);
            binding.callsalon.setVisibility(View.VISIBLE);
        }



        binding.bookbtn.setVisibility(View.GONE);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();

        context = DetailSalonActivity.this;

        salonname = getIntent().getStringExtra("salonname");
        String salonaddress = getIntent().getStringExtra("salonaddress");
        String city = getIntent().getStringExtra("city");
        String pincode = getIntent().getStringExtra("pincode");
        String state = getIntent().getStringExtra("state");
        String salonimg = getIntent().getStringExtra("salonimg");
        String service = getIntent().getStringExtra("service");

        ip = getIntent().getStringExtra("ip");
        binding.salonname.setText(salonname);
        binding.salonaddress.setText(salonaddress + " , " + city + ", " + state + " .");
        binding.specialservice.setText(service);
        Glide.with(context).load(salonimg).placeholder(R.drawable.profilepic3).into(binding.salonimg);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        for (int m = 0; m < 12; m++) {
            String o = timeslots[m];
            Map<String, Object> map = new HashMap<>();
            map.put(o, salonname);
            DocumentReference documentReference = firebaseFirestore.collection("booking").document(String.valueOf(map));
            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (value.exists()) {
                        String salonNamee = value.getString("salonname");
                        if (salonname.equals(salonNamee)) {
                            LocalTime localTime = LocalTime.now();
                            String ampmrightnow = localTime.format(DateTimeFormatter.ofPattern("a"));
                            String inttimee = value.getString("inttimee");
                            String ampm = value.getString("ampm");
                            int INTTIMEFROMDATABASE = Integer.parseInt(inttimee);
                            int rightNowHour = Integer.parseInt(localTime.format(DateTimeFormatter.ofPattern("hh")));
                            if (INTTIMEFROMDATABASE == 12 && ampmrightnow.equals(ampm)) {
                                if (rightNowHour==1 && ampmrightnow.equals("pm")) {
                                    firebaseFirestore.collection("booking").document(String.valueOf(map)).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "Booking Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } else if (rightNowHour == 12 && INTTIMEFROMDATABASE < 12 && ampm.equals("pm")){
                                Toast.makeText(DetailSalonActivity.this, "No Problem", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if (rightNowHour > INTTIMEFROMDATABASE && ampmrightnow.equals(ampm)) {
                                    firebaseFirestore.collection("booking").document(String.valueOf(map)).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "Booking Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            });
        }

        for (int i = 0; i < 12; i++) {
            String t = timeslots[i];
            Map<Object, Object> map = new HashMap<>();
            map.put(t, salonname);
            DocumentReference documentReference = firebaseFirestore.collection("booking").document(String.valueOf(map));
            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (value.exists()) {
                        String SalonName = value.getString("salonname");
                        if (salonname.equals(SalonName)) {
                            String TIME = value.getString("timeslot");
                            switch (TIME) {
                                case "09:00 am":
                                    binding.card1.setClickable(false);
                                    binding.card1.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text1.setTextColor(R.color.white);
                                    break;
                                case "10:00 am":
                                    binding.card2.setClickable(false);
                                    binding.card2.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text2.setTextColor(R.color.white);
                                    break;
                                case "11:00 am":
                                    binding.card3.setClickable(false);
                                    binding.card3.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text3.setTextColor(R.color.white);
                                    break;
                                case "12:00 pm":
                                    binding.card4.setClickable(false);
                                    binding.card4.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text4.setTextColor(R.color.white);
                                    break;
                                case "01:00 pm":
                                    binding.card5.setClickable(false);
                                    binding.card5.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text5.setTextColor(R.color.white);
                                    break;
                                case "02:00 pm":
                                    binding.card6.setClickable(false);
                                    binding.card6.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text6.setTextColor(R.color.white);
                                    break;
                                case "03:00 pm":
                                    binding.card7.setClickable(false);
                                    binding.card7.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text7.setTextColor(R.color.white);
                                    break;
                                case "04:00 pm":
                                    binding.card8.setClickable(false);
                                    binding.card8.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text8.setTextColor(R.color.white);
                                    break;
                                case "05:00 pm":
                                    binding.card9.setClickable(false);
                                    binding.card9.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text9.setTextColor(R.color.white);
                                    break;
                                case "06:00 pm":
                                    binding.card10.setClickable(false);
                                    binding.card10.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text10.setTextColor(R.color.white);
                                    break;
                                case "07:00 pm":
                                    binding.card11.setClickable(false);
                                    binding.card11.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text11.setTextColor(R.color.white);
                                    break;
                                case "08:00 pm":
                                    binding.card12.setClickable(false);
                                    binding.card12.setBackgroundResource(R.drawable.rectangle_fill);
                                    binding.text12.setTextColor(R.color.white);
                                    break;
                            }
                        }
                    } else {
                        binding.lin1.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin2.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin3.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin4.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin5.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin6.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin7.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin8.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin9.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin10.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin11.setBackgroundResource(R.drawable.rectangle_outline);
                        binding.lin12.setBackgroundResource(R.drawable.rectangle_outline);
                    }
                }
            });
        }

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL myIp = new URL("https://checkip.amazonaws.com//");

            URLConnection connection = myIp.openConnection();
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            IP = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < binding.mainGridLayout.getChildCount(); i++) {
            final int finalI = i;
            CardView cardView = (CardView) binding.mainGridLayout.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardView.setBackgroundResource(R.drawable.rectangle_fill);
                    binding.bookbtn.setVisibility(View.VISIBLE);
                    binding.bookbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (finalI == 0) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[0];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(",Scalp Detox");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(",Shave");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append(",Clean Up");
                                }
                                String timeslot = binding.text1.getText().toString();
                                String ampm = "am";
                                binding.card1.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text1.getText().toString(), salonname);
                                map.put("time", binding.text1.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 1) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[1];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text2.getText().toString();
                                String ampm = "am";
                                binding.card2.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text2.getText().toString(), salonname);
                                map.put("time", binding.text2.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 2) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[2];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text3.getText().toString();
                                String ampm = "am";
                                binding.card3.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text3.getText().toString(), salonname);
                                map.put("time", binding.text3.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 3) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[3];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text4.getText().toString();
                                String ampm = "pm";
                                binding.card4.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                map.put("time", binding.text4.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text4.getText().toString(), salonname);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 4) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[4];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text5.getText().toString();
                                String ampm = "pm";
                                binding.card5.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                map.put("time", binding.text5.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text5.getText().toString(), salonname);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 5) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[5];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text6.getText().toString();
                                String ampm = "pm";
                                binding.card6.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                map.put("time", binding.text6.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text6.getText().toString(), salonname);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 6) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[6];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text7.getText().toString();
                                String ampm = "pm";
                                binding.card7.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                map.put("time", binding.text7.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text7.getText().toString(), salonname);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 7) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[7];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text8.getText().toString();
                                String ampm = "pm";
                                binding.card8.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                map.put("time", binding.text8.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),ip,salonaddress);
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text8.getText().toString(), salonname);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 8) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[8];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text9.getText().toString();
                                String ampm = "pm";
                                binding.card9.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                map.put("time", binding.text9.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text9.getText().toString(), salonname);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 9) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[9];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text10.getText().toString();
                                String ampm = "pm";
                                binding.card10.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                map.put("time", binding.text10.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text10.getText().toString(), salonname);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 10) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[10];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text11.getText().toString();
                                String ampm = "pm";
                                binding.card11.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                map.put("time", binding.text11.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text11.getText().toString(), salonname);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (finalI == 11) {
                                StringBuilder result = new StringBuilder();
                                String inttimee = hours[11];
                                if (binding.haircut.isChecked()) {
                                    result.append("Haircut ");
                                }
                                if (binding.scalpdetox.isChecked()) {
                                    result.append(", Scalp Detox ");
                                }
                                if (binding.shave.isChecked()) {
                                    result.append(", Shave ");
                                }
                                if (binding.cleanup.isChecked()) {
                                    result.append("& Clean Up");
                                }
                                String timeslot = binding.text12.getText().toString();
                                String ampm = "pm";
                                binding.card12.setCardBackgroundColor(R.color.purple_200);
                                Map<String, Object> map = new HashMap<>();
                                map.put("time", binding.text12.getText().toString());
                                map.put("inttime", inttimee);
                                map.put("ampm",ampm);
                                map.put("salonname", salonname);
                                map.put("service", result.toString());
                                map.put("ip", IP);
                                Booking booking = new Booking(timeslot,inttimee,ampm,salonname,result.toString(),IP,salonaddress);
                                Map<Object, Object> map1 = new HashMap<>();
                                map1.put(binding.text12.getText().toString(), salonname);
                                firebaseFirestore.collection("booking").document(String.valueOf(map1)).set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(),BookingDoneActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(DetailSalonActivity.this, "Booking Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                }
            });
        }
    }
}