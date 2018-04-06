package com.example.degrood21.mexican_train_dominoes;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private TextView p1ScoreTV, p2ScoreTV, p3ScoreTV, p4ScoreTV, roundTV;
    private ImageView turnMarker1, turnMarker2, turnMarker3, turnMarker4,roundDom, p1First, p1Second,
            p1Third, p1Fourth, p1Fifth, p1Sixth, p2First, p2Second, p2Third, p2Fourth, p2Fifth,
            p2Sixth, p3First, p3Second, p3Third, p3Fourth, p3Fifth, p3Sixth, p4First, p4Second,
            p4Third, p4Fourth, p4Fifth, p4Sixth, publicFirst, publicSecond, publicThird,
            publicFourth, publicFifth, publicSixth, handOne, handTwo, handThree, handFour, handFive,
            handSix, handSeven, handEight, handNine, handTen, handEleven, handTwelve, handThirteen,
            handFourteen, handFifteen, handSixteen, handSeventeen, handEighteen, handNineteen,
            handTwenty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quitButton = (Button)findViewById(R.id.quitButton);
        restartButton = (Button)findViewById(R.id.restartButton);
        helpButton = (Button)findViewById(R.id.button);
        drawButton = (Button)findViewById(R.id.drawButton);

        p1ScoreTV = (TextView)findViewById(R.id.player1Score);
        p2ScoreTV = (TextView)findViewById(R.id.player2Score);
        p3ScoreTV = (TextView)findViewById(R.id.player3Score);
        p4ScoreTV = (TextView)findViewById(R.id.player4Score);
        roundTV = (TextView)findViewById(R.id.roundView);

        roundDom = (ImageView)findViewById(R.id.roundDominoIV);

        turnMarker1 = (ImageView)findViewById(R.id.turnMarker);
        turnMarker2 = (ImageView)findViewById(R.id.turnMarker2);
        turnMarker3 = (ImageView)findViewById(R.id.turnMarker3);
        turnMarker4 = (ImageView)findViewById(R.id.turnMarker4);

        p1First = (ImageView)findViewById(R.id.p1IVone);
        p1Second = (ImageView)findViewById(R.id.p1IVtwo);
        p1Third = (ImageView)findViewById(R.id.p1IVthree);
        p1Fourth = (ImageView)findViewById(R.id.p1IVfour);
        p1Fifth = (ImageView)findViewById(R.id.p1IVfive);
        p1Sixth = (ImageView)findViewById(R.id.p1IVsix);

        p2First = (ImageView)findViewById(R.id.p2IVone);
        p2Second = (ImageView)findViewById(R.id.p2IVtwo);
        p2Third = (ImageView)findViewById(R.id.p2IVthree);
        p2Fourth = (ImageView)findViewById(R.id.p2IVfour);
        p2Fifth = (ImageView)findViewById(R.id.p2IVfive);
        p2Sixth = (ImageView)findViewById(R.id.p2IVsix);

        p3First = (ImageView)findViewById(R.id.p3IVone);
        p3Second = (ImageView)findViewById(R.id.p3IVtwo);
        p3Third = (ImageView)findViewById(R.id.p3IVthree);
        p3Fourth = (ImageView)findViewById(R.id.p3IVfour);
        p3Fifth = (ImageView)findViewById(R.id.p3IVfive);
        p3Sixth = (ImageView)findViewById(R.id.p3IVsix);

        p4First = (ImageView)findViewById(R.id.p4IVone);
        p4Second = (ImageView)findViewById(R.id.p4IVtwo);
        p4Third = (ImageView)findViewById(R.id.p4IVthree);
        p4Fourth = (ImageView)findViewById(R.id.p4IVfour);
        p4Fifth = (ImageView)findViewById(R.id.p4IVfive);
        p4Sixth = (ImageView)findViewById(R.id.p4IVsix);

        publicFirst = (ImageView)findViewById(R.id.pTrainIVone);
        publicSecond = (ImageView)findViewById(R.id.pTrainIVtwo);
        publicThird = (ImageView)findViewById(R.id.pTrainIVthree);
        publicFourth = (ImageView)findViewById(R.id.pTrainIVfour);
        publicFifth = (ImageView)findViewById(R.id.pTrainIVfive);
        publicSixth = (ImageView)findViewById(R.id.pTrainIVsix);

        handOne = (ImageView)findViewById(R.id.handIVone);
        handTwo = (ImageView)findViewById(R.id.handIVtwo);
        handThree = (ImageView)findViewById(R.id.handIVthree);
        handFour = (ImageView)findViewById(R.id.handIVfour);
        handFive = (ImageView)findViewById(R.id.handIVfive);
        handSix = (ImageView)findViewById(R.id.handIVsix);
        handSeven = (ImageView)findViewById(R.id.handIVseven);
        handEight = (ImageView)findViewById(R.id.handIVeight);
        handNine = (ImageView)findViewById(R.id.handIVnine);
        handTen = (ImageView)findViewById(R.id.handIVten);
        handEleven = (ImageView)findViewById(R.id.handIVeleven);
        handTwelve = (ImageView)findViewById(R.id.handIVtwelve);
        handThirteen = (ImageView)findViewById(R.id.handIVthirteen);
        handFourteen = (ImageView)findViewById(R.id.handIVfourteen);
        handFifteen = (ImageView)findViewById(R.id.handIVfifteen);
        handSixteen = (ImageView)findViewById(R.id.handIVsixteen);
        handSeventeen = (ImageView)findViewById(R.id.handIVseventeen);
        handEighteen = (ImageView)findViewById(R.id.handIVeighteen);
        handNineteen = (ImageView)findViewById(R.id.handIVnineteen);
        handTwenty = (ImageView)findViewById(R.id.handIVtwenty);

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


        //listens to see which domino is selected

        p1Sixth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


            }
        });

        p3Sixth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        p4Sixth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        publicSixth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        //which domino in hand is selected
        handOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handFour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handFive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handSix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handSeven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handEight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handNine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handTen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handEleven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handTwelve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handThirteen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handFourteen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handFifteen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handSixteen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handSeventeen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handEighteen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handNineteen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        handTwenty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });



    }

    @Override
    public void onClick(View v) {
    }
}
