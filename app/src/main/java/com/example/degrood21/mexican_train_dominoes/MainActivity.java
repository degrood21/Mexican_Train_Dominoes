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

        final ArrayList<Integer> setOfDominoes = new ArrayList<Integer>();

        setOfDominoes.add(R.drawable.c0_0);
        setOfDominoes.add(R.drawable.c1_1);
        setOfDominoes.add(R.drawable.c2_2);
        setOfDominoes.add(R.drawable.c3_3);
        setOfDominoes.add(R.drawable.c4_4);
        setOfDominoes.add(R.drawable.c5_5);
        setOfDominoes.add(R.drawable.c6_6);
        setOfDominoes.add(R.drawable.c7_7);
        setOfDominoes.add(R.drawable.c8_8);
        setOfDominoes.add(R.drawable.c9_9);
        setOfDominoes.add(R.drawable.c10_10);
        setOfDominoes.add(R.drawable.c11_11);
        setOfDominoes.add(R.drawable.c12_12);

        setOfDominoes.add(R.drawable.d0_1);
        setOfDominoes.add(R.drawable.d0_2);
        setOfDominoes.add(R.drawable.d0_3);
        setOfDominoes.add(R.drawable.d0_4);
        setOfDominoes.add(R.drawable.d0_5);
        setOfDominoes.add(R.drawable.d0_6);
        setOfDominoes.add(R.drawable.d0_7);
        setOfDominoes.add(R.drawable.d0_8);
        setOfDominoes.add(R.drawable.d0_9);
        setOfDominoes.add(R.drawable.d0_10);
        setOfDominoes.add(R.drawable.d0_11);
        setOfDominoes.add(R.drawable.d0_12);

        setOfDominoes.add(R.drawable.d1_2);
        setOfDominoes.add(R.drawable.d1_3);
        setOfDominoes.add(R.drawable.d1_4);
        setOfDominoes.add(R.drawable.d1_5);
        setOfDominoes.add(R.drawable.d1_6);
        setOfDominoes.add(R.drawable.d1_7);
        setOfDominoes.add(R.drawable.d1_8);
        setOfDominoes.add(R.drawable.d1_9);
        setOfDominoes.add(R.drawable.d1_10);
        setOfDominoes.add(R.drawable.d1_11);
        setOfDominoes.add(R.drawable.d1_12);

        setOfDominoes.add(R.drawable.d2_3);
        setOfDominoes.add(R.drawable.d2_4);
        setOfDominoes.add(R.drawable.d2_5);
        setOfDominoes.add(R.drawable.d2_6);
        setOfDominoes.add(R.drawable.d2_7);
        setOfDominoes.add(R.drawable.d2_8);
        setOfDominoes.add(R.drawable.d2_9);
        setOfDominoes.add(R.drawable.d2_10);
        setOfDominoes.add(R.drawable.d2_11);
        setOfDominoes.add(R.drawable.d2_12);

        setOfDominoes.add(R.drawable.d3_4);
        setOfDominoes.add(R.drawable.d3_5);
        setOfDominoes.add(R.drawable.d3_6);
        setOfDominoes.add(R.drawable.d3_7);
        setOfDominoes.add(R.drawable.d3_8);
        setOfDominoes.add(R.drawable.d3_9);
        setOfDominoes.add(R.drawable.d3_10);
        setOfDominoes.add(R.drawable.d3_11);
        setOfDominoes.add(R.drawable.d3_12);

        setOfDominoes.add(R.drawable.d4_5);
        setOfDominoes.add(R.drawable.d4_6);
        setOfDominoes.add(R.drawable.d4_7);
        setOfDominoes.add(R.drawable.d4_8);
        setOfDominoes.add(R.drawable.d4_9);
        setOfDominoes.add(R.drawable.d4_10);
        setOfDominoes.add(R.drawable.d4_11);
        setOfDominoes.add(R.drawable.d4_12);

        setOfDominoes.add(R.drawable.d5_6);
        setOfDominoes.add(R.drawable.d5_7);
        setOfDominoes.add(R.drawable.d5_8);
        setOfDominoes.add(R.drawable.d5_9);
        setOfDominoes.add(R.drawable.d5_10);
        setOfDominoes.add(R.drawable.d5_11);
        setOfDominoes.add(R.drawable.d5_12);

        setOfDominoes.add(R.drawable.d6_7);
        setOfDominoes.add(R.drawable.d6_8);
        setOfDominoes.add(R.drawable.d6_9);
        setOfDominoes.add(R.drawable.d6_10);
        setOfDominoes.add(R.drawable.d6_11);
        setOfDominoes.add(R.drawable.d6_12);

        setOfDominoes.add(R.drawable.d7_8);
        setOfDominoes.add(R.drawable.d7_9);
        setOfDominoes.add(R.drawable.d7_10);
        setOfDominoes.add(R.drawable.d7_11);
        setOfDominoes.add(R.drawable.d7_12);

        setOfDominoes.add(R.drawable.d8_9);
        setOfDominoes.add(R.drawable.d8_10);
        setOfDominoes.add(R.drawable.d8_11);
        setOfDominoes.add(R.drawable.d8_12);

        setOfDominoes.add(R.drawable.d9_10);
        setOfDominoes.add(R.drawable.d9_11);
        setOfDominoes.add(R.drawable.d9_12);

        setOfDominoes.add(R.drawable.d10_11);
        setOfDominoes.add(R.drawable.d10_12);

        setOfDominoes.add(R.drawable.d11_12);

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

                if (firstInstance.testAction()) {

                    editTextView.append("testAction Method works fine\n");
                    editTextView.append("" + firstInstance.PileofDominoes + "\n");

                }
                if (firstInstance.testAction()) {

                    editTextView.append("Understanding how to append EditText\n");

                }

            }
        });
    }
}
