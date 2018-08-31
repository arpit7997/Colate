package com.feedr.arpit.colateassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.feedr.arpit.colateassignment.Utils.Constants;

public class DetailScreen extends AppCompatActivity {

    TextView text_detail_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);

        text_detail_title = (TextView) findViewById(R.id.text_detail_title);

        if(getIntent().getExtras()!=null){
            text_detail_title.setText(getIntent().getStringExtra(Constants.title));
        }
    }
}
