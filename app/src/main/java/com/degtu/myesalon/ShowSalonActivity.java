package com.degtu.myesalon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.degtu.myesalon.databinding.ActivityShowSalonBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowSalonActivity extends AppCompatActivity {

    ActivityShowSalonBinding binding;
    FirebaseFirestore firebaseFirestore;
    SalonAdapter salonAdapter;
    ArrayList<Salon> salonArrayList;
    UniSexSalonAdapter uniSexSalonAdapter;
    ArrayList<Salon> unisexArrayList;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowSalonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        String username = getIntent().getStringExtra("username");

        firebaseFirestore = FirebaseFirestore.getInstance();
        salonArrayList = new ArrayList<>();
        unisexArrayList = new ArrayList<>();
        salonAdapter = new SalonAdapter(getApplicationContext(),salonArrayList);
        uniSexSalonAdapter = new UniSexSalonAdapter(getApplicationContext(),unisexArrayList);
        binding.mansalons.setHasFixedSize(true);
        binding.mansalons.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        binding.mansalons.setAdapter(salonAdapter);
        binding.unisexsalons.setHasFixedSize(true);
        binding.unisexsalons.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        binding.unisexsalons.setAdapter(uniSexSalonAdapter);


        binding.viewalltxtmansalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ViewAllManSalonActivity.class);
                startActivity(intent);
            }
        });

        binding.viewalltxtunisexsalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ViewAllUnisexSalonActivity.class);
                startActivity(intent);
            }
        });

        for (int i=0; i<binding.mainGridLayout.getChildCount();i++){
            final int finalI = i;
            CardView cardView = (CardView) binding.mainGridLayout.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI==0){
                        Intent intent = new Intent(ShowSalonActivity.this,ViewAllHaircutSalonActivity.class);
                        startActivity(intent);
                    }
                    if (finalI==1){
                        Intent intent = new Intent(ShowSalonActivity.this,ViewAllScalpDetoxActivity.class);
                        startActivity(intent);
                    }
                    if (finalI==2){
                        Intent intent = new Intent(ShowSalonActivity.this,ViewAllShaveActivity.class);
                        startActivity(intent);
                    }
                    if (finalI==3){
                        Intent intent = new Intent(ShowSalonActivity.this,ViewAllCleanUpActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }

        firebaseFirestore.collection("salon").whereEqualTo("gender","Male").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    return;
                }else {
                        assert value != null;
                        for (DocumentChange documentChange : value.getDocumentChanges()){
                            salonArrayList.add(documentChange.getDocument().toObject(Salon.class));
                            salonAdapter.notifyDataSetChanged();
                        }
                }
            }
        });

        firebaseFirestore.collection("salon").whereEqualTo("gender","Unisex").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    return;
                }else {
                    assert value != null;
                    for (DocumentChange documentChange : value.getDocumentChanges()){
                        unisexArrayList.add(documentChange.getDocument().toObject(Salon.class));
                        uniSexSalonAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}