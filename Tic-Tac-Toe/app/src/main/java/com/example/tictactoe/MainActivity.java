package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.reset, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (gamenotend) {
        gamereset();}
        else {
            Toast.makeText(this, "COMPLETE THE GAME TO RESET", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    boolean gameactive = true;
    boolean gamenotend = false;
    // Player representation
    // 0 - X
    // 1 - O
    int taptime = 0;
    int activeplayer =0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meaning
    // 0-X
    // 1-O
    // 2-NULL

    int[][] winpos = {{0,1,2}   ,{0,3,6},
                      {3,4,5}   ,{1,4,7},
                      {6,7,8}   ,{2,5,8},
                                 {0,4,8},{2,4,6}} ;

    public void playerTap(View view) {

        if (gameactive == true) {
            ImageView img = (ImageView) view;
            int tapedimage = Integer.parseInt(img.getTag().toString());
            ImageView img1 = findViewById(R.id.imageView);
            if (gamestate[tapedimage] == 2) {
                gamestate[tapedimage] = activeplayer;
                img.setTranslationY(-1000f);
                if (activeplayer == 0) {
                    img.setImageResource(R.drawable.x);
                    activeplayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("O 's - TURN");


                } else {
                    img.setImageResource(R.drawable.o);
                    activeplayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText("X 's - TURN");
                }
                taptime++;
                img.animate().translationYBy(1000f).setDuration(300);
            }
            boolean ans = true;
            // Check If Any Player Win
            for (int[] winpos : winpos) {
                if (gamestate[winpos[0]] == gamestate[winpos[1]] &&
                        gamestate[winpos[1]] == gamestate[winpos[2]] && gamestate[winpos[0]] != 2) {
                    if (gamestate[winpos[0]] == 0) {
                        TextView status = findViewById(R.id.status);
                        status.setText("!!! X WON !!!");
                    } else {
                        TextView status = findViewById(R.id.status);
                        status.setText("!!! O WON !!!");
                    }
                    ans=false;
                    gameactive = false;
                    gamenotend=true;
                }
            }
            if(ans && taptime==9){
                TextView status = findViewById(R.id.status);
                status.setText("! DRAW !");
                gameactive = false;
                gamenotend=true;
            }
        }

    }


    public void gamereset() {
        gamenotend = false;
        taptime=0;
        activeplayer=0;
        gameactive = true;
        for (int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }

        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X 's - TURN");

    }
}