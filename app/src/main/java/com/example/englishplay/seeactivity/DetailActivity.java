package com.example.englishplay.seeactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.englishplay.R;

/*
显示单词的具体细节
 */
public class DetailActivity extends AppCompatActivity{

    private TextView detail_tv1,detail_tv2,detail_tv3,detail_tv4,detail_tv5,detail_tv6,detail_tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    public void init(){
        detail_tv1 = (TextView) findViewById(R.id.input);
        detail_tv2 = (TextView) findViewById(R.id.translation);
        detail_tv3 = (TextView) findViewById(R.id.webmeans);
        detail_tv4 = (TextView) findViewById(R.id.means);
        detail_tv5 = (TextView) findViewById(R.id.spell);
        detail_tv6 = (TextView) findViewById(R.id.ukSpell);
        detail_tv7 = (TextView) findViewById(R.id.usSpell);
    }
}
