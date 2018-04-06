package com.example.degrood21.mexican_train_dominoes;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Main Activity
 * Creates Permanent Set of Dominoes
 * by creating individual dominoes with Drawable ID, Right Value, Left Value
 * and adds them to setOfDominoes ArrayList
 *
 * @authors Dylan DeGrood, Callum Morham, Logan Crawford, Devin Smith
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button quitButton, restartButton, helpButton, drawButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quitButton = (Button)findViewById(R.id.quitButton);
        restartButton = (Button)findViewById(R.id.restartButton);
        helpButton = (Button)findViewById(R.id.button);
        drawButton = (Button)findViewById(R.id.drawButton);
        quitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

            }
        });

        restartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        helpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        drawButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });


    }

    @Override
    public void onClick(View view) {

    }
}
