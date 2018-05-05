package com.example.englishplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.example.englishplay.menuactivity.SlidingMenu;
import com.example.englishplay.readactivity.ReadActivity;
import com.example.englishplay.seeactivity.SeeActivity;


public class MainActivity extends FragmentActivity {
    private SlidingMenu slidingMenu;
    private TextView see_text = null;
    private TextView read_text = null;

//    private final static String LOG_TAG = "MainActivity";
//
//    private ImageView picture;
//    private static final int TAKE_PHOTO = 1;
//    private static final int CHOOSE_PHOTO = 2;
//    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        slidingMenu = (SlidingMenu) findViewById(R.id.slidingmenu);

        init();

//        Window window = getWindow();//得到窗口
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//没有标题
//
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
//
//        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//设置高亮
    }

    public void init(){
        see_text = (TextView) findViewById(R.id.see_tv);
        read_text = (TextView) findViewById(R.id.read_tv);

        see_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SeeActivity.class);
                startActivity(intent);
            }
        });

        //Log.i(LOG_TAG, "Main Activity Created.");

        read_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReadActivity.class);
                startActivity(intent);
            }
        });
    }

    public void toggle(View view) {
        slidingMenu.toggle();
    }

}
