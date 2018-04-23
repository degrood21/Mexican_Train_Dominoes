package com.example.degrood21.mexican_train_dominoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

/**
 * HelpMenu displays the rules of the game to the player. The player can return to the game by
 * tapping the return button.
 *
 * @authors Devin Smith, Callum Morham, Dylan Degrood, Logan Crawford
 */

public class HelpMenu extends AppCompatActivity implements Serializable {
    private static long serialVersionUID = 1293786127936L;

    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help_menu);
        returnButton = (Button)findViewById(R.id.exitButton);//casts it to a button


       returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//ends current activity and returns to game
            }
        });
    }

}
