package com.example.englishplay.readactivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.englishplay.R;


public class ReadActivity extends AppCompatActivity {

    Button capButton;
    Button readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_read);
        init();

    }

    private void init() {
        capButton = findViewById(R.id.CAP_Button);
        readButton = findViewById(R.id.READ_Button);

        capButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCamera = new Intent(ReadActivity.this, CameraActivity1.class);
                startActivity(toCamera);
            }
        });
    }
//        readButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent toCamera = new Intent(ReadActivity.this, CameraActivity1.class);
//                startActivity(toCamera);
//            }
//        });
}
