package com.degtu.myesalon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.degtu.myesalon.databinding.ActivityCustomerSignInBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CustomerSignInActivity extends AppCompatActivity {

    ActivityCustomerSignInBinding binding;
    FirebaseFirestore firebaseFirestore;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerSignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        firebaseFirestore = FirebaseFirestore.getInstance();
        context = CustomerSignInActivity.this;

        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String role = getIntent().getStringExtra("role");
        String deviceId = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);

        binding.signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.equals(binding.email.getText().toString()) && password.equals(binding.password.getText().toString())){
                    if (role.equals("Customer")){
                        String username = binding.username.getText().toString();
                        Map<String,Object> map = new HashMap<>();
                        map.put("email",email);
                        map.put("password",password);
                        map.put("username",username);
                        firebaseFirestore.collection("customers").document(deviceId.toString()).set(map);
                        Intent intent = new Intent(getApplicationContext(),ShowSalonActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                        finishAfterTransition();
                    }else {
                        String username = binding.username.getText().toString();
                        Map<String,Object> map = new HashMap<>();
                        map.put("email",email);
                        map.put("password",password);
                        map.put("username",username);
                        firebaseFirestore.collection("barbers").document(deviceId.toString()).set(map);
                        Intent intent = new Intent(getApplicationContext(),BarberHomeActivity.class);
                        startActivity(intent);
                        finishAfterTransition();
                    }
                }else if (binding.username.getText().toString().isEmpty()){
                    binding.username.setError("Please enter username");
                }else {
                    Toast.makeText(CustomerSignInActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}