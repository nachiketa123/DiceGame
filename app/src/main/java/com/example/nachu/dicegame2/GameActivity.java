package com.example.nachu.dicegame2;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Button roll,hold,reset;
    ImageView iv;
    TextView tv;
    Vibrator vib;
    ConstraintLayout ll_2;
    private int playerScore=0;
    private int pTurnScore=0;
    private int cpuScore=0;
    private int cpuTurnScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        vib= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        ll_2=(ConstraintLayout)findViewById(R.id.ll_2);
        RelativeLayout.LayoutParams lp= (RelativeLayout.LayoutParams) ll_2.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        ll_2.setLayoutParams(lp);
        roll=(Button)findViewById(R.id.roll);
        iv=(ImageView)findViewById(R.id.iv);
        tv=(TextView) findViewById(R.id.tv);
        hold=(Button)findViewById(R.id.hold);
        reset=(Button)findViewById(R.id.reset);
        //onclick for roll
        hold.setEnabled(false);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hold.isEnabled())
                {
                    //Log.d("mytag","hey");
                    hold.setEnabled(true);
                }

                        int i;
                        i= rollIt();
                        if (i != 1)
                        {
                            pTurnScore += i;
                        }
                        else
                        {
                            pTurnScore = 0;
                            vib.vibrate(300);
                            computerTurn();
                        }
                        if(!checkWin()){
                            tv.setText("Your score :"+(playerScore+pTurnScore)+" \t\t\t\t CPU score :"+cpuScore);
                        }

            }
        });
        //onclick for hold
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerScore+=pTurnScore;
                pTurnScore=0;
                computerTurn();
                if(!checkWin()){
                    tv.setText("Your score :"+playerScore+" \t\t\t\t CPU score :"+cpuScore);
                }

            }
        });

        //onclick for reset
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll.setEnabled(true);
                hold.setEnabled(false);
                playerScore=0;
                pTurnScore=0;
                cpuScore=0;
                cpuTurnScore=0;
                tv.setText("");
            }
        });


    }


    public int rollIt()
    {
        Random random=new Random();
        int i=0;
        while(i==0)
        {
            i=random.nextInt(7);
        }
        switch (i)
        {
            case 1:
                iv.setImageResource(R.drawable.dice1);
                break;
            case 2:
                iv.setImageResource(R.drawable.dice2);
                break;
            case 3:
                iv.setImageResource(R.drawable.dice3);
                break;
            case 4:
                iv.setImageResource(R.drawable.dice4);
                break;
            case 5:
                iv.setImageResource(R.drawable.dice5);
                break;
            case 6:
                iv.setImageResource(R.drawable.dice6);
                break;
            default:
                Toast.makeText(this,"Loading error",Toast.LENGTH_SHORT).show();
                break;
        }
        return i;
    }

    public void computerTurn() {
        roll.setEnabled(false);
        hold.setEnabled(false);

        Animation anim1 = AnimationUtils.loadAnimation(GameActivity.this, R.anim.shake);
        Animation.AnimationListener animationListener=new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        anim1.setAnimationListener(animationListener);
        iv.startAnimation(anim1);
        int i;
        do {
            i = rollIt();
            // Log.d("mytag","i="+String.valueOf(i));
            if (i != 1) {
                cpuTurnScore += i;
            } else {
                cpuTurnScore = 0;
                break;
            }
            if(!checkWin()){
                tv.setText("Your score :"+playerScore+" \t\t\t\t CPU score :"+(cpuScore + cpuTurnScore));
            }
        } while (cpuTurnScore < 15);
        cpuScore+=cpuTurnScore;
        cpuTurnScore=0;
        if(!checkWin()){
            tv.setText("Your score :"+playerScore+" \t\t\t\t CPU score :"+cpuScore);
        }
        roll.setEnabled(true);
        hold.setEnabled(true);
    }
    public boolean checkWin()
    {
        if(cpuScore+cpuTurnScore>=100)
        {
            vib.vibrate(500);
            tv.setText("CPU WINS");
            roll.setEnabled(false);
            hold.setEnabled(false);
            return true;
        }
        else
        if(playerScore+pTurnScore>=100)
        {
            vib.vibrate(500);
            tv.setText("YOU WIN");
            roll.setEnabled(false);
            hold.setEnabled(false);
            return  true;
        }
        return false;
    }
}
