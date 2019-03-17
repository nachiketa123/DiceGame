package com.example.nachu.dicegame2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Player1 extends Fragment
{
    Vibrator v;
    Communicator c;
    View view;
    Button roll,hold,reset;
    RelativeLayout rel;
    ImageView iv;
    TextView tv;
    private int player1Score=0;
    private int p1TurnScore=0;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c=(Communicator)context;
        //Log.d("mytag","onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("mytag","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        //Log.d("mytag","onCreateView");
        view= inflater.inflate(R.layout.activity_game,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        roll=(Button)view.findViewById(R.id.roll);
        iv=(ImageView)view.findViewById(R.id.iv);
        tv=(TextView) view.findViewById(R.id.tv);
        hold=(Button)view.findViewById(R.id.hold);
        reset=(Button)view.findViewById(R.id.reset);
        rel=(RelativeLayout) view.findViewById(R.id.rel);
        rel.setBackgroundColor(Color.parseColor("#F6FFA3"));
        //Log.d("mytag","onActivityCreate");
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

                i = rollIt();
                if (i != 1)
                {
                    p1TurnScore += i;
                }
                else
                {
                    p1TurnScore = 0;
                    c.change();
                }
                if(!c.checkWin()){
                    tv.setText("Your score :"+(player1Score+p1TurnScore));
                }
                else
                    if(player1Score+p1TurnScore>=50)
                    {
                        tv.setText("You Win");
                        c.updateTV();
                    }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hold.setEnabled(false);
                player1Score=0;
                p1TurnScore=0;
                c.setReset();
                tv.setText("");
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player1Score+=p1TurnScore;
                p1TurnScore=0;
                if(!c.checkWin()){
                    tv.setText("Your score :"+player1Score);
                }
                c.change();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //Log.d("mytag","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.d("mytag","onResume");
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
                Toast.makeText(getActivity(),"Loading error",Toast.LENGTH_SHORT).show();
                break;
        }
        return i;
    }
    public int getScore()
    {
        return (player1Score+p1TurnScore);
    }
    public void changeMe()
    {
        if(!roll.isEnabled()) {
            rel.setBackgroundColor(Color.parseColor("#F6FFA3"));
            roll.setEnabled(true);
            hold.setEnabled(true);
            //Log.d("mytag","hey");
        }
        else
        {
            //Log.d("mytag","else");
            rel.setBackgroundColor(Color.parseColor("#FFFFFF"));
            roll.setEnabled(false);
            hold.setEnabled(false);
        }
    }
    public String getTV()
    {
        return tv.getText().toString();
    }
    public void setTV()
    {
        tv.setText("You Loose");
    }
}