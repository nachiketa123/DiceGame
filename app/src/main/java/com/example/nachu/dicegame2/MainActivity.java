package com.example.nachu.dicegame2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
RadioGroup rg;
ImageButton share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg=(RadioGroup)findViewById(R.id.rg);
        share=(ImageButton)findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webPage=new Intent(Intent.ACTION_SEND);
                webPage.setType("text/plain");
                webPage.putExtra(Intent.EXTRA_TEXT,"https://drive.google.com/open?id=1yVOt5Iekqpkkvc5o9HuWoLlHeqJRx0rb");
                startActivity(Intent.createChooser(webPage,"Share via"));
            }
        });
    }
    public void startGame(View v)
    {
        int x=rg.getCheckedRadioButtonId();
        Intent i=null;
        if(x==R.id.rb1)
        {
            i=new Intent(this,GameActivity.class);
        }
        if(x==R.id.rb2)
        {
            i=new Intent(this,GameActivity2.class);
        }
        startActivity(i);
    }
}
