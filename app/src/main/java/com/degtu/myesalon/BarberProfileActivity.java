package com.degtu.myesalon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.degtu.myesalon.databinding.ActivityBarberProfileBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class BarberProfileActivity extends AppCompatActivity {

    ActivityBarberProfileBinding binding;
    FirebaseFirestore firebaseFirestore;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBarberProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        context = BarberProfileActivity.this;
        firebaseFirestore = FirebaseFirestore.getInstance();
        String deviceId = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);

        firebaseFirestore.collection("barbers").document(deviceId.toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()){
                    String username = value.getString("username");
                    String email = value.getString("email");
                    binding.username.setText(username);
                    binding.email.setText(email);
                }
            }
        });

        binding.addsalonrelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarberProfileActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}