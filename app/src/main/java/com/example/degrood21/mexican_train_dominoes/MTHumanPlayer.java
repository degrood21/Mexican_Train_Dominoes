package com.example.degrood21.mexican_train_dominoes;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.degrood21.mexican_train_dominoes.game.GameHumanPlayer;
import com.example.degrood21.mexican_train_dominoes.game.GameMainActivity;
import com.example.degrood21.mexican_train_dominoes.game.infoMsg.GameInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Human Player class that allows the user to interact with a GUI to play Mexican Train Dominoes.
 * Designed for landscape orientation, player plays the game by touching the screen.
 *
 * @author Dylan Degrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class MTHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    protected DominoGameState state;

    private Activity myActivity;

    private ArrayList<ImageView> HandIVs = new ArrayList<ImageView>();

    private Button quitButton, restartButton, helpButton, drawButton;
    private TextView p1ScoreTV, p2ScoreTV, p3ScoreTV, p4ScoreTV, roundTV;
    private ImageView turnMarker1, turnMarker2, turnMarker3, turnMarker4, roundDom, p1First, p1Second,
            p1Third, p1Fourth, p1Fifth, p1Sixth, p2First, p2Second, p2Third, p2Fourth, p2Fifth,
            p2Sixth, p3First, p3Second, p3Third, p3Fourth, p3Fifth, p3Sixth, p4First, p4Second,
            p4Third, p4Fourth, p4Fifth, p4Sixth, publicFirst, publicSecond, publicThird,
            publicFourth, publicFifth, publicSixth, handOne, handTwo, handThree, handFour, handFive,
            handSix, handSeven, handEight, handNine, handTen, handEleven, handTwelve, handThirteen,
            handFourteen, handFifteen, handSixteen, handSeventeen, handEighteen, handNineteen,
            handTwenty;

    public MTHumanPlayer(String name) {

        super(name);

    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

        if (!(info instanceof DominoGameState)) {

            return;

        }

        this.state = (DominoGameState) info;
        //this.state = new DominoGameState();

        /**
         * Tests of how to draw the dominoes into the ImageViews
         *
         */
        for (int i = 0; i < state.Player1Hand.size(); i++) {

            HandIVs.get(i).setImageResource(state.Player1Hand.get(i).pictureID);
            HandIVs.get(i).getLayoutParams().width = 200;

        }

        for(int i = state.Player1Hand.size(); i < 20; i++){

            HandIVs.get(i).setImageResource(R.color.green_playboard);
            HandIVs.get(i).getLayoutParams().width = 200;

        }

        /*handOne.getLayoutParams().width = 200;
        handTwo.setImageResource(R.drawable.d1_2);
        handTwo.setRotation(0);
        handTwo.getLayoutParams().width = 200;
        handThree.setImageResource(R.drawable.c2_2);
        handThree.getLayoutParams().width = 200;
        handFour.setImageResource(R.drawable.d1_2);
        handFour.getLayoutParams().width = 200;
        handFive.setImageResource(R.drawable.c2_2);
        handFive.getLayoutParams().width = 200;
        handSix.setImageResource(R.drawable.d1_2);
        handSix.getLayoutParams().width = 200;
        handSeven.setImageResource(R.drawable.c2_2);
        handSeven.getLayoutParams().width = 200;
        handEight.setImageResource(R.drawable.d1_2);
        handEight.getLayoutParams().width = 200;
        handNine.setImageResource(R.drawable.d0_5);
        handNine.getLayoutParams().width = 200;
        handTen.setImageResource(R.drawable.d1_2);
        handTen.getLayoutParams().width = 200;
        handEleven.setImageResource(R.drawable.c2_2);
        handEleven.getLayoutParams().width = 200;
        handTwelve.setImageResource(R.drawable.d1_2);
        handTwelve.getLayoutParams().width = 200;
        handThirteen.setImageResource(R.drawable.d1_2);
        handThirteen.getLayoutParams().width = 200;
        handFourteen.setImageResource(R.drawable.d1_2);
        handFourteen.getLayoutParams().width = 200;
        handFifteen.setImageResource(R.drawable.d1_2);
        handFifteen.getLayoutParams().width = 200;
        handSixteen.setImageResource(R.color.green_playboard);
        handSixteen.getLayoutParams().width = 200;
        handSeventeen.setImageResource(R.drawable.d1_2);
        handSeventeen.getLayoutParams().width = 200;
        handEighteen.setImageResource(R.drawable.d1_2);
        handEighteen.getLayoutParams().width = 200;
        handNineteen.setImageResource(R.drawable.d1_2);
        handNineteen.getLayoutParams().width = 200;
        handTwenty.setImageResource(R.color.green_playboard);
        handTwenty.getLayoutParams().width = 200;
*/

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

        myActivity = activity;

        activity.setContentView(R.layout.activity_main);

        /**
         * Creating the button and ImageViews used to draw the dominoes onto the screen
         *
         */
        quitButton = (Button) myActivity.findViewById(R.id.quitButton);

        restartButton = (Button) myActivity.findViewById(R.id.restartButton);

        helpButton = (Button) myActivity.findViewById(R.id.button);

        drawButton = (Button) myActivity.findViewById(R.id.drawButton);


        p1ScoreTV = (TextView) myActivity.findViewById(R.id.player1Score);
        p2ScoreTV = (TextView) myActivity.findViewById(R.id.player2Score);
        p3ScoreTV = (TextView) myActivity.findViewById(R.id.player3Score);
        p4ScoreTV = (TextView) myActivity.findViewById(R.id.player4Score);
        roundTV = (TextView) myActivity.findViewById(R.id.roundView);
        roundDom = (ImageView) myActivity.findViewById(R.id.roundDominoIV);
        turnMarker1 = (ImageView) myActivity.findViewById(R.id.turnMarker);
        turnMarker2 = (ImageView) myActivity.findViewById(R.id.turnMarker2);
        turnMarker3 = (ImageView) myActivity.findViewById(R.id.turnMarker3);
        turnMarker4 = (ImageView) myActivity.findViewById(R.id.turnMarker4);

        p1First = (ImageView) myActivity.findViewById(R.id.p1IVone);
        p1Second = (ImageView) myActivity.findViewById(R.id.p1IVtwo);
        p1Third = (ImageView) myActivity.findViewById(R.id.p1IVthree);
        p1Fourth = (ImageView) myActivity.findViewById(R.id.p1IVfour);
        p1Fifth = (ImageView) myActivity.findViewById(R.id.p1IVfive);
        p1Sixth = (ImageView) myActivity.findViewById(R.id.p1IVsix);
        p2First = (ImageView) myActivity.findViewById(R.id.p2IVone);
        p2Second = (ImageView) myActivity.findViewById(R.id.p2IVtwo);
        p2Third = (ImageView) myActivity.findViewById(R.id.p2IVthree);
        p2Fourth = (ImageView) myActivity.findViewById(R.id.p2IVfour);
        p2Fifth = (ImageView) myActivity.findViewById(R.id.p2IVfive);
        p2Sixth = (ImageView) myActivity.findViewById(R.id.p2IVsix);
        p3First = (ImageView) myActivity.findViewById(R.id.p3IVone);
        p3Second = (ImageView) myActivity.findViewById(R.id.p3IVtwo);
        p3Third = (ImageView) myActivity.findViewById(R.id.p3IVthree);
        p3Fourth = (ImageView) myActivity.findViewById(R.id.p3IVfour);
        p3Fifth = (ImageView) myActivity.findViewById(R.id.p3IVfive);
        p3Sixth = (ImageView) myActivity.findViewById(R.id.p3IVsix);
        p4First = (ImageView) myActivity.findViewById(R.id.p4IVone);
        p4Second = (ImageView) myActivity.findViewById(R.id.p4IVtwo);
        p4Third = (ImageView) myActivity.findViewById(R.id.p4IVthree);
        p4Fourth = (ImageView) myActivity.findViewById(R.id.p4IVfour);
        p4Fifth = (ImageView) myActivity.findViewById(R.id.p4IVfive);
        p4Sixth = (ImageView) myActivity.findViewById(R.id.p4IVsix);
        publicFirst = (ImageView) myActivity.findViewById(R.id.pTrainIVone);
        publicSecond = (ImageView) myActivity.findViewById(R.id.pTrainIVtwo);
        publicThird = (ImageView) myActivity.findViewById(R.id.pTrainIVthree);
        publicFourth = (ImageView) myActivity.findViewById(R.id.pTrainIVfour);
        publicFifth = (ImageView) myActivity.findViewById(R.id.pTrainIVfive);
        publicSixth = (ImageView) myActivity.findViewById(R.id.pTrainIVsix);

        HandIVs.add(handOne = (ImageView) myActivity.findViewById(R.id.handIVone));
        HandIVs.add(handTwo = (ImageView) myActivity.findViewById(R.id.handIVtwo));
        HandIVs.add(handThree = (ImageView) myActivity.findViewById(R.id.handIVthree));
        HandIVs.add(handFour = (ImageView) myActivity.findViewById(R.id.handIVfour));
        HandIVs.add(handFive = (ImageView) myActivity.findViewById(R.id.handIVfive));
        HandIVs.add(handSix = (ImageView) myActivity.findViewById(R.id.handIVsix));
        HandIVs.add(handSeven = (ImageView) myActivity.findViewById(R.id.handIVseven));
        HandIVs.add(handEight = (ImageView) myActivity.findViewById(R.id.handIVeight));
        HandIVs.add(handNine = (ImageView) myActivity.findViewById(R.id.handIVnine));
        HandIVs.add(handTen = (ImageView) myActivity.findViewById(R.id.handIVten));
        HandIVs.add(handEleven = (ImageView) myActivity.findViewById(R.id.handIVeleven));
        HandIVs.add(handTwelve = (ImageView) myActivity.findViewById(R.id.handIVtwelve));
        HandIVs.add(handThirteen = (ImageView) myActivity.findViewById(R.id.handIVthirteen));
        HandIVs.add(handFourteen = (ImageView) myActivity.findViewById(R.id.handIVfourteen));
        HandIVs.add(handFifteen = (ImageView) myActivity.findViewById(R.id.handIVfifteen));
        HandIVs.add(handSixteen = (ImageView) myActivity.findViewById(R.id.handIVsixteen));
        HandIVs.add(handSeventeen = (ImageView) myActivity.findViewById(R.id.handIVseventeen));
        HandIVs.add(handEighteen = (ImageView) myActivity.findViewById(R.id.handIVeighteen));
        HandIVs.add(handNineteen = (ImageView) myActivity.findViewById(R.id.handIVnineteen));
        HandIVs.add(handTwenty = (ImageView) myActivity.findViewById(R.id.handIVtwenty));


        quitButton.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v) {

            }
        });

        restartButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });

        helpButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });

        drawButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        p1Sixth.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


            }
        });

        p3Sixth.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });

        p4Sixth.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });

        publicSixth.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });

        //which domino in hand is selected
        handOne.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handTwo.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handThree.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handFour.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handFive.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handSix.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handSeven.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handEight.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handNine.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handTen.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handEleven.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handTwelve.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handThirteen.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handFourteen.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handFifteen.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handSixteen.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handSeventeen.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handEighteen.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handNineteen.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });
        handTwenty.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}

