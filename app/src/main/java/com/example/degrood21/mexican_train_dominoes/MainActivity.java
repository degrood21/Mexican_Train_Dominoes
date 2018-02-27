package com.example.degrood21.mexican_train_dominoes;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Drawable> setOfDominoes = new ArrayList<Drawable>();

        final EditText editTextView = (EditText) findViewById(R.id.editText);

        Button runTest = (Button) findViewById(R.id.buttonTest);
        runTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextView.setText("");
                DominoGameState firstInstance = new DominoGameState(setOfDominoes);
                DominoGameState secondInstance = new DominoGameState(firstInstance);

                //
                //
                // List of firstInstance calling all action methods from GameState
                //
                //

                if(firstInstance.testAction()){

                    editTextView.append("testAction Method works fine\n");

                }
                if(firstInstance.testAction()){

                    editTextView.append("Understanding how to append EditText\n");

                }

            }
        });
    }
}
