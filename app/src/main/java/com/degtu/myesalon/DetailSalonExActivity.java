package com.degtu.myesalon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.degtu.myesalon.databinding.ActivityDetailSalonExBinding;

public class DetailSalonExActivity extends AppCompatActivity {

    ActivityDetailSalonExBinding binding;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailSalonExBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int selectedId = binding.radiogroup1.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                if (selectedId == 1){
                    Toast.makeText(DetailSalonExActivity.this, String.valueOf(radioButton.getText().toString()), Toast.LENGTH_SHORT).show();
                }
                if (selectedId == 2){
                    Toast.makeText(DetailSalonExActivity.this, String.valueOf(radioButton.getText().toString()), Toast.LENGTH_SHORT).show();
                }
                if (selectedId == 3){
                    Toast.makeText(DetailSalonExActivity.this, String.valueOf(radioButton.getText().toString()), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}