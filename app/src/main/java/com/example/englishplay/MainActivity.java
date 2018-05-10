package com.example.englishplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.example.englishplay.learnactivity.LearnActivity;
import com.example.englishplay.menuactivity.SlidingMenu;
import com.example.englishplay.seeactivity.SeeActivity;
import com.example.englishplay.view.CircleButtonView;

import java.io.File;


public class MainActivity extends FragmentActivity {
    private SlidingMenu slidingMenu;
    private TextView see_text = null;
    private TextView read_text = null;

    File photoFile = null;

    public static final int REQUEST_CODE_ACCURATE_BASIC = 1;

    private CircleButtonView see_btn;
    private CircleButtonView learn_btn;
    private CircleButtonView read_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        slidingMenu = (SlidingMenu) findViewById(R.id.slidingmenu);

        init();
    }

    public void init(){

        see_btn = (CircleButtonView) findViewById(R.id.cbv_see);
        learn_btn = (CircleButtonView) findViewById(R.id.cbv_learn);
        read_btn = (CircleButtonView) findViewById(R.id.cbv_read);

        see_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SeeActivity.class);
                startActivity(intent);
            }
        });

        learn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LearnActivity.class);
                startActivity(intent);
            }
        });

        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoFile = new File(getExternalCacheDir(),"image.jpg");

                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,photoFile.getPath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent,REQUEST_CODE_ACCURATE_BASIC);
            }
        });

    }

    public void toggle(View view) {
        slidingMenu.toggle();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this, photoFile.getPath() +"    "+ photoFile.length(), Toast.LENGTH_LONG).show();
        Log.d("test", "onActivityResult: " + photoFile.getPath() + "\n" + photoFile.length());

    }
}
