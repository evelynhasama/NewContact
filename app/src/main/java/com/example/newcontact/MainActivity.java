package com.example.newcontact;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import static com.example.newcontact.R.drawable.ic_baseline_sentiment_satisfied_alt_24;

public class MainActivity extends AppCompatActivity {

    Button btnCreate;
    LinearLayout llContactInfo;
    ImageView ivFace;
    ImageView ivPhone;
    ImageView ivWeb;
    ImageView ivPin;

    final int ACTIVITYINFO = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);
        llContactInfo = findViewById(R.id.llContactInfi);
        ivFace = findViewById(R.id.ivFace);
        ivPhone = findViewById(R.id.ivPhone);
        ivWeb = findViewById(R.id.ivWeb);
        ivPin = findViewById(R.id.ivPin);

        llContactInfo.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.newcontact.ActivityInfo.class);
                startActivityForResult(intent, ACTIVITYINFO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITYINFO) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String number = data.getStringExtra("number");
                String website = data.getStringExtra("website");
                String address = data.getStringExtra("address");
                Integer face = data.getIntExtra("face", 0);

                if (face == 1){
                    ivFace.setImageResource(ic_baseline_sentiment_satisfied_alt_24);
                } else if (face == 2){
                    ivFace.setImageResource(R.drawable.ic_baseline_sentiment_dissatisfied_24);
                } else {
                    ivFace.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
                }

                ivFace.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Contact name: " + name, Toast.LENGTH_SHORT).show();
                    }
                });

                ivPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
                        startActivity(intent);
                    }
                });

                ivPin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+address));
                        startActivity(intent);
                    }
                });

                ivWeb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www."+website));
                        startActivity(intent);
                    }
                });


                llContactInfo.setVisibility(View.VISIBLE);
            }
            if (resultCode == RESULT_CANCELED) {
                //
            }
        }
    }

}