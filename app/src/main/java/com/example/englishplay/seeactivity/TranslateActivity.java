package com.example.englishplay.seeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.englishplay.R;

public class TranslateActivity extends AppCompatActivity {

    private ImageView comment_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        comment_image = (ImageView) findViewById(R.id.commentimage);

        comment_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TranslateActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
