package com.degtu.myesalon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.degtu.myesalon.databinding.ActivityCustomerSignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CustomerSignUpActivity extends AppCompatActivity {

    ActivityCustomerSignUpBinding binding;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    String id;
    ProgressDialog dialog;
    RadioButton radioButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        context = CustomerSignUpActivity.this;

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Signing Up...");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


        binding.signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deviceId = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
                int selectedId = binding.radiogroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                if (binding.email.getText().toString().isEmpty()){
                    binding.email.setError("Please enter email");
                }else if (binding.password.getText().toString().isEmpty()){
                    binding.password.setError("Please enter password");
                }else if (selectedId==-1){
                    Toast.makeText(CustomerSignUpActivity.this, "Please select any of the role", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (selectedId==0){
                        dialog.show();
                        dialog.setProgress(100);
                        String email = binding.email.getText().toString();
                        String password = binding.password.getText().toString();
                        Map<String,Object> customer = new HashMap<>();
                        customer.put("email",email);
                        customer.put("password",password);

                        auth.createUserWithEmailAndPassword(email,password);

                        firebaseFirestore.collection("customers").document(deviceId.toString()).set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                dialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(),CustomerSignInActivity.class);
                                intent.putExtra("email",email);
                                intent.putExtra("password",password);
                                intent.putExtra("uid",id);
                                intent.putExtra("role", String.valueOf(radioButton.getText().toString()));
                                intent.putExtra("deviceId",deviceId.toString());
                                startActivity(intent);
                                finishAfterTransition();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CustomerSignUpActivity.this, "Failed!;", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        dialog.show();
                        dialog.setProgress(100);
                        String email = binding.email.getText().toString();
                        String password = binding.password.getText().toString();
                        Map<String,Object> customer = new HashMap<>();
                        customer.put("email",email);
                        customer.put("password",password);

                        auth.createUserWithEmailAndPassword(email,password);

                        firebaseFirestore.collection("barbers").document(deviceId.toString()).set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                dialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(),CustomerSignInActivity.class);
                                intent.putExtra("email",email);
                                intent.putExtra("password",password);
                                intent.putExtra("uid",id);
                                intent.putExtra("role", String.valueOf(radioButton.getText().toString()));
                                intent.putExtra("deviceId",deviceId.toString());
                                startActivity(intent);
                                finishAfterTransition();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CustomerSignUpActivity.this, "Failed!;", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            }
        });

    }
}