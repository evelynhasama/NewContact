package com.example.newcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityInfo extends AppCompatActivity {

    EditText etName;
    EditText etNumber;
    EditText etWebsite;
    EditText etAddress;
    ImageView imHappy;
    ImageView imSad;
    ImageView imStraight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etWebsite = findViewById(R.id.etWebsite);
        etAddress = findViewById(R.id.etAddress);

        imHappy = findViewById(R.id.ivHappy);
        imStraight = findViewById(R.id.ivStraight);
        imSad = findViewById(R.id.ivSad);

        imHappy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedEmoji(1);
            }
        });
        imStraight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedEmoji(2);
            }
        });
        imSad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedEmoji(3);
            }
        });
    }

    public void clickedEmoji(Integer face){
        if (etName.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty() ||
                etWebsite.getText().toString().isEmpty() || etAddress.getText().toString().isEmpty()) {
            Toast.makeText(this, "please enter all fields.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("name", etName.getText().toString().trim());
            intent.putExtra("number", etNumber.getText().toString().trim());
            intent.putExtra("website", etWebsite.getText().toString().trim());
            intent.putExtra("address", etAddress.getText().toString().trim());
            intent.putExtra("face", face);
            setResult(RESULT_OK, intent);
            ActivityInfo.this.finish();
        }
    }

}