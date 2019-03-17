package com.example.nachu.dicegame2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity2 extends AppCompatActivity implements Communicator{

    Player1 player1;
    Player2 player2;
    Vibrator v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        player1=new Player1();
        player2=new Player2();
        v=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.player1frame,player1,"player1");
        transaction.add(R.id.player2frame,player2,"player2");
        transaction.commit();
        //Log.d("mytag","hi");
    }

    @Override
    public boolean checkWin() {
        int p1score=player1.getScore();
        int p2score=player2.getScore();
        if(p1score>=100)
            return true;
        if(p2score>=100)
            return true;
        return false;
    }
    public void setReset()
    {
        player2.setToZero();
        player1.changeMe();
        player2.changeMe();
    }

    @Override
    public void change() {
        v.vibrate(300);
        player1.changeMe();
        player2.changeMe();
    }

    @Override
    public void updateTV() {
        v.vibrate(500);
        if(player1.getTV().equals("You Win"))
        {
            player2.setTV();
        }
        if(player2.getTV().equals("You Win"))
        {
            player1.setTV();
        }
    }
}
