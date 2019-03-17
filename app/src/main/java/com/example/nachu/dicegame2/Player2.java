package com.example.nachu.dicegame2;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Player2 extends Fragment
{
    RelativeLayout rel;
    Communicator c;
    View view;
    Button roll,hold,reset;
    ImageView iv;
    TextView tv;
    private int player2Score=0;
    private int p2TurnScore=0;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c=(Communicator)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_game,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rel=(RelativeLayout)view.findViewById(R.id.rel);
        roll=(Button)view.findViewById(R.id.roll);
        iv=(ImageView)view.findViewById(R.id.iv);
        tv=(TextView) view.findViewById(R.id.tv);
        hold=(Button)view.findViewById(R.id.hold);
        reset=(Button)view.findViewById(R.id.reset);
        reset.setVisibility(View.GONE);
        hold.setEnabled(false);
        roll.setEnabled(false);
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
                    p2TurnScore += i;
                }
                else
                {
                    p2TurnScore = 0;
                    c.change();
                }
                if(!c.checkWin()){
                    tv.setText("Your score :"+(player2Score+p2TurnScore));
                }
                else
                if(player2Score+p2TurnScore>=50)
                {
                    tv.setText("You Win");
                    c.updateTV();
                }

            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2Score+=p2TurnScore;
                p2TurnScore=0;
                if(!c.checkWin()){
                    tv.setText("Your score :"+player2Score);
                }
                c.change();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
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
        return (player2Score+p2TurnScore);
    }
    public void setToZero(){
        player2Score=0;
        p2TurnScore=0;
        tv.setText("");
    }

    public void changeMe()
    {
        if(!roll.isEnabled()) {
            rel.setBackgroundColor(Color.parseColor("#F6FFA3"));
            roll.setEnabled(true);
            hold.setEnabled(true);
        }
        else
        {
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