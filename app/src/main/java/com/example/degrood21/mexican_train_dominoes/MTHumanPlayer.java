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

/**
 * Created by crawforl20 on 4/4/2018.
 */

public class MTHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    protected DominoGameState state;

    private Activity myActivity;

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

        /**
         * This works in SlapJack but not working here
         *
         */
        this.state = (DominoGameState) info;

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

        handOne = (ImageView) myActivity.findViewById(R.id.handIVone);

        handTwo = (ImageView) myActivity.findViewById(R.id.handIVtwo);

        handThree = (ImageView) myActivity.findViewById(R.id.handIVthree);

        handFour = (ImageView) myActivity.findViewById(R.id.handIVfour);

        handFive = (ImageView) myActivity.findViewById(R.id.handIVfive);

        handSix = (ImageView) myActivity.findViewById(R.id.handIVsix);

        handSeven = (ImageView) myActivity.findViewById(R.id.handIVseven);

        handEight = (ImageView) myActivity.findViewById(R.id.handIVeight);

        handNine = (ImageView) myActivity.findViewById(R.id.handIVnine);

        handTen = (ImageView) myActivity.findViewById(R.id.handIVten);

        handEleven = (ImageView) myActivity.findViewById(R.id.handIVeleven);

        handTwelve = (ImageView) myActivity.findViewById(R.id.handIVtwelve);

        handThirteen = (ImageView) myActivity.findViewById(R.id.handIVthirteen);

        handFourteen = (ImageView) myActivity.findViewById(R.id.handIVfourteen);

        handFifteen = (ImageView) myActivity.findViewById(R.id.handIVfifteen);
        handSixteen = (ImageView) myActivity.findViewById(R.id.handIVsixteen);
        handSeventeen = (ImageView) myActivity.findViewById(R.id.handIVseventeen);
        handEighteen = (ImageView) myActivity.findViewById(R.id.handIVeighteen);
        handNineteen = (ImageView) myActivity.findViewById(R.id.handIVnineteen);
        handTwenty = (ImageView) myActivity.findViewById(R.id.handIVtwenty);

        /**
         * Tests of how to draw the dominoes into the ImageViews
         *
         */
        handOne.setImageResource(state.Player1Hand.get(0).pictureID);
        handOne.getLayoutParams().width = 200;
        handTwo.setImageResource(R.drawable.d1_2);
        handTwo.setRotation(180);
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

