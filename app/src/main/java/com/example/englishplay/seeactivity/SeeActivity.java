package com.example.englishplay.seeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.example.englishplay.R;

import java.io.File;

/*
what to see activity下方两个按钮的点击事件
 */
public class SeeActivity extends AppCompatActivity {

    private Button take_photo;
    private Button choose_picture;
    private String imageUri;
    private ImageView picture;
    private ImageView most,next;
    public static final int REQUEST_CODE_ACCURATE_BASIC = 1;
    File photoFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);

        most = findViewById(R.id.most_like);
        next = findViewById(R.id.next_like);

        most.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeeActivity.this,TranslateActivity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeeActivity.this,TranslateActivity.class);
                startActivity(intent);
            }
        });


        picture = (ImageView) findViewById(R.id.picture);

        take_photo = findViewById(R.id.take_photo);
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoFile = new File(getExternalCacheDir(),"image.jpg");

                Intent intent = new Intent(SeeActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,photoFile.getPath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent,REQUEST_CODE_ACCURATE_BASIC);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this,photoFile.getPath() +"    "+ photoFile.length(),Toast.LENGTH_LONG).show();
    }
}
