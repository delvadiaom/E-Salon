package com.degtu.myesalon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.degtu.myesalon.databinding.ActivityViewAllManSalonBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewAllManSalonActivity extends AppCompatActivity {

    ActivityViewAllManSalonBinding binding;
    DetailManSalonAdapter detailManSalonAdapter;
    ArrayList<Salon> salonArrayList;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllManSalonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        firebaseFirestore = FirebaseFirestore.getInstance();

        salonArrayList = new ArrayList<>();
        detailManSalonAdapter = new DetailManSalonAdapter(getApplicationContext(),salonArrayList);
        binding.mansalons.setHasFixedSize(true);
        binding.mansalons.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.mansalons.setAdapter(detailManSalonAdapter);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
                        detailManSalonAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }
}