package com.example.englishplay.seeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.englishplay.R;

public class SeeActivity extends AppCompatActivity {

    private TextView most_text;
    private TextView next_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);
        init();
    }

    public void init(){
        most_text = (TextView) findViewById(R.id.most_like_tv);
        next_view = (TextView) findViewById(R.id.next_like_tv);

        most_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeeActivity.this,TranslateActivity.class);
                startActivity(intent);
            }
        });

        next_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeeActivity.this,TranslateActivity.class);
                startActivity(intent);
            }
        });
    }
}
