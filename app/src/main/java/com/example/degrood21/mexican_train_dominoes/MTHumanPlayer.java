package com.example.degrood21.mexican_train_dominoes;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.degrood21.mexican_train_dominoes.game.GameHumanPlayer;
import com.example.degrood21.mexican_train_dominoes.game.GameMainActivity;
import com.example.degrood21.mexican_train_dominoes.game.infoMsg.GameInfo;

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

    private int selectedDomino;

    private ArrayList<ImageView> HandIVs = new ArrayList<ImageView>();
    private ArrayList<ImageView> PublicTrainIVs = new ArrayList<ImageView>();
    private ArrayList<ImageView> Player1TrainIVs = new ArrayList<ImageView>();
    private ArrayList<ImageView> Player2TrainIVs = new ArrayList<ImageView>();
    private ArrayList<ImageView> Player3TrainIVs = new ArrayList<ImageView>();
    private ArrayList<ImageView> Player4TrainIVs = new ArrayList<ImageView>();


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


        /**
         * Tests of how to draw the dominoes into the ImageViews
         *
         */
        for (int i = 0; i < 20; i++) {

            HandIVs.get(i).setImageResource(R.color.green_playboard);
            HandIVs.get(i).getLayoutParams().width = 200;

        }
        for (int i = 0; i < state.Player1Hand.size(); i++) {

            HandIVs.get(i).setImageResource(state.Player1Hand.get(i).pictureID);
            HandIVs.get(i).getLayoutParams().width = 200;

        }

        for (int i = Player1TrainIVs.size() - 2; i >= 0; i--) {

            Player1TrainIVs.get(i).setImageResource(R.color.green_playboard);
            Player1TrainIVs.get(i).getLayoutParams().width = 200;

        }
        if (state.Player1Train.size() != 0) {
            int j = state.Player1Train.size() - 1;
            for (int i = Player1TrainIVs.size() - 2; i >= 0; i--) {

                if (j >= 0) {
                    if (state.Player1Train.get(j).rightSide == -1) {
                        Player1TrainIVs.get(i).setImageResource(state.Player1Train.get(j).pictureID);
                        Player1TrainIVs.get(i).getLayoutParams().width = 200;
                        Player1TrainIVs.get(i).setRotation(180);
                        j--;
                    }else{
                        Player1TrainIVs.get(i).setImageResource(state.Player1Train.get(j).pictureID);
                        Player1TrainIVs.get(i).getLayoutParams().width = 200;
                        j--;
                    }
                }

            }
        }

        for (int i = Player2TrainIVs.size() - 2; i >= 0; i--) {

            Player2TrainIVs.get(i).setImageResource(R.color.green_playboard);
            Player2TrainIVs.get(i).getLayoutParams().width = 200;

        }
        if (state.Player2Train.size() != 0) {
            int j = state.Player2Train.size() - 1;
            for (int i = Player2TrainIVs.size() - 2; i >= 0; i--) {

                if (j >= 0) {
                    if (state.Player2Train.get(j).rightSide == -1) {
                        Player2TrainIVs.get(i).setImageResource(state.Player2Train.get(j).pictureID);
                        Player2TrainIVs.get(i).getLayoutParams().width = 200;
                        Player2TrainIVs.get(i).setRotation(180);
                        j--;
                    } else{
                        Player2TrainIVs.get(i).setImageResource(state.Player2Train.get(j).pictureID);
                        Player2TrainIVs.get(i).getLayoutParams().width = 200;
                        j--;
                    }
                }

            }
        }

        for (int i = Player3TrainIVs.size() - 2; i >= 0; i--) {

            Player3TrainIVs.get(i).setImageResource(R.color.green_playboard);
            Player3TrainIVs.get(i).getLayoutParams().width = 200;

        }
        if (state.Player3Train.size() != 0) {
            int j = state.Player3Train.size() - 1;
            for (int i = Player3TrainIVs.size() - 2; i >= 0; i--) {

                if (j >= 0) {
                    if (state.Player3Train.get(j).rightSide == -1) {
                        Player3TrainIVs.get(i).setImageResource(state.Player3Train.get(j).pictureID);
                        Player3TrainIVs.get(i).getLayoutParams().width = 200;
                        Player3TrainIVs.get(i).setRotation(180);
                        j--;
                    } else {
                        Player3TrainIVs.get(i).setImageResource(state.Player3Train.get(j).pictureID);
                        Player3TrainIVs.get(i).getLayoutParams().width = 200;
                        j--;
                    }
                }

            }
        }

        for (int i = Player4TrainIVs.size() - 2; i >= 0; i--) {

            Player4TrainIVs.get(i).setImageResource(R.color.green_playboard);
            Player4TrainIVs.get(i).getLayoutParams().width = 200;

        }
        if (state.Player4Train.size() != 0) {
            int j = state.Player4Train.size() - 1;
            for (int i = Player4TrainIVs.size() - 2; i >= 0; i--) {

                if (j >= 0) {
                    if (state.Player4Train.get(j).rightSide == -1) {
                        Player4TrainIVs.get(i).setImageResource(state.Player4Train.get(j).pictureID);
                        Player4TrainIVs.get(i).getLayoutParams().width = 200;
                        Player4TrainIVs.get(i).setRotation(180);
                        j--;
                    } else {
                        Player4TrainIVs.get(i).setImageResource(state.Player4Train.get(j).pictureID);
                        Player4TrainIVs.get(i).getLayoutParams().width = 200;
                        j--;
                    }
                }

            }
        }


        for (
                int i = PublicTrainIVs.size() - 2;
                i >= 0; i--)

        {

            PublicTrainIVs.get(i).setImageResource(R.color.green_playboard);
            PublicTrainIVs.get(i).getLayoutParams().width = 200;

        }
        if (state.PublicTrain.size() != 0)

        {
            int j = state.PublicTrain.size() - 1;
            for (int i = PublicTrainIVs.size() - 2; i >= 0; i--) {

                if (j >= 0) {
                    if (state.PublicTrain.get(j).rightSide == -1) {
                        PublicTrainIVs.get(i).setImageResource(state.PublicTrain.get(j).pictureID);
                        PublicTrainIVs.get(i).getLayoutParams().width = 200;
                        PublicTrainIVs.get(i).setRotation(180);
                        j--;
                    } else{
                        PublicTrainIVs.get(i).setImageResource(state.PublicTrain.get(j).pictureID);
                        PublicTrainIVs.get(i).getLayoutParams().width = 200;
                        j--;
                    }
                }

            }
        }

        if (state.player1Public)

        {
            turnMarker1.setImageResource(R.drawable.trainmarker);
            turnMarker1.getLayoutParams().width = 40;
        } else

        {
            turnMarker1.setImageResource(R.color.green_playboard);
            turnMarker1.getLayoutParams().width = 40;
        }
        if (state.player2Public)

        {
            turnMarker2.setImageResource(R.drawable.trainmarker);
            turnMarker2.getLayoutParams().width = 40;
        } else

        {
            turnMarker2.setImageResource(R.color.green_playboard);
            turnMarker2.getLayoutParams().width = 40;
        }
        if (state.player3Public)

        {
            turnMarker3.setImageResource(R.drawable.trainmarker);
            turnMarker3.getLayoutParams().width = 40;
        } else

        {
            turnMarker3.setImageResource(R.color.green_playboard);
            turnMarker3.getLayoutParams().width = 40;
        }
        if (state.player4Public)

        {
            turnMarker4.setImageResource(R.drawable.trainmarker);
            turnMarker4.getLayoutParams().width = 40;
        } else

        {
            turnMarker4.setImageResource(R.color.green_playboard);
            turnMarker4.getLayoutParams().width = 40;
        }

        roundDom.setImageResource(state.PublicTrain.get(0).pictureID);
        roundDom.getLayoutParams().width = 150;

        roundTV.setText("Round: Double " + state.PublicTrain.get(0).rightSide);

        sendInfo(state);

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

        Player1TrainIVs.add(p1First = (ImageView) myActivity.findViewById(R.id.p1IVone));
        Player1TrainIVs.add(p1Second = (ImageView) myActivity.findViewById(R.id.p1IVtwo));
        Player1TrainIVs.add(p1Third = (ImageView) myActivity.findViewById(R.id.p1IVthree));
        Player1TrainIVs.add(p1Fourth = (ImageView) myActivity.findViewById(R.id.p1IVfour));
        Player1TrainIVs.add(p1Fifth = (ImageView) myActivity.findViewById(R.id.p1IVfive));
        Player1TrainIVs.add(p1Sixth = (ImageView) myActivity.findViewById(R.id.p1IVsix));

        Player2TrainIVs.add(p2First = (ImageView) myActivity.findViewById(R.id.p2IVone));
        Player2TrainIVs.add(p2Second = (ImageView) myActivity.findViewById(R.id.p2IVtwo));
        Player2TrainIVs.add(p2Third = (ImageView) myActivity.findViewById(R.id.p2IVthree));
        Player2TrainIVs.add(p2Fourth = (ImageView) myActivity.findViewById(R.id.p2IVfour));
        Player2TrainIVs.add(p2Fifth = (ImageView) myActivity.findViewById(R.id.p2IVfive));
        Player2TrainIVs.add(p2Sixth = (ImageView) myActivity.findViewById(R.id.p2IVsix));

        Player3TrainIVs.add(p3First = (ImageView) myActivity.findViewById(R.id.p3IVone));
        Player3TrainIVs.add(p3Second = (ImageView) myActivity.findViewById(R.id.p3IVtwo));
        Player3TrainIVs.add(p3Third = (ImageView) myActivity.findViewById(R.id.p3IVthree));
        Player3TrainIVs.add(p3Fourth = (ImageView) myActivity.findViewById(R.id.p3IVfour));
        Player3TrainIVs.add(p3Fifth = (ImageView) myActivity.findViewById(R.id.p3IVfive));
        Player3TrainIVs.add(p3Sixth = (ImageView) myActivity.findViewById(R.id.p3IVsix));

        Player4TrainIVs.add(p4First = (ImageView) myActivity.findViewById(R.id.p4IVone));
        Player4TrainIVs.add(p4Second = (ImageView) myActivity.findViewById(R.id.p4IVtwo));
        Player4TrainIVs.add(p4Third = (ImageView) myActivity.findViewById(R.id.p4IVthree));
        Player4TrainIVs.add(p4Fourth = (ImageView) myActivity.findViewById(R.id.p4IVfour));
        Player4TrainIVs.add(p4Fifth = (ImageView) myActivity.findViewById(R.id.p4IVfive));
        Player4TrainIVs.add(p4Sixth = (ImageView) myActivity.findViewById(R.id.p4IVsix));

        PublicTrainIVs.add(publicFirst = (ImageView) myActivity.findViewById(R.id.pTrainIVone));
        PublicTrainIVs.add(publicSecond = (ImageView) myActivity.findViewById(R.id.pTrainIVtwo));
        PublicTrainIVs.add(publicThird = (ImageView) myActivity.findViewById(R.id.pTrainIVthree));
        PublicTrainIVs.add(publicFourth = (ImageView) myActivity.findViewById(R.id.pTrainIVfour));
        PublicTrainIVs.add(publicFifth = (ImageView) myActivity.findViewById(R.id.pTrainIVfive));
        PublicTrainIVs.add(publicSixth = (ImageView) myActivity.findViewById(R.id.pTrainIVsix));

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

                state.drawAction(0);
                if (state.playableTrains(0, state.Player1Hand.get(state.Player1Hand.size() - 1), 0)) {
                    state.placeDomino(0, state.Player1Hand.get(state.Player1Hand.size() - 1), 0);
                    state.player1Public = false;
                    state.playerTurn++;
                } else {
                    state.playerTurn++;
                }

                sendInfo(state);

            }
        });

        p1Sixth.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                if (state.playableTrains(state.playerTurn, state.Player1Hand.get(selectedDomino), 0)) {

                    if (state.placeDomino(state.playerTurn, state.Player1Hand.get(selectedDomino), 0)) {

                        state.player1Public = false;
                        if (state.playerTurn >= 3) {
                            state.playerTurn = 0;
                        } else {
                            state.playerTurn++;
                        }

                    }
                    sendInfo(state);
                }


            }
        });

        p2Sixth.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                if (state.playerTurn == 0) {
                    if (state.playableTrains(state.playerTurn, state.Player1Hand.get(selectedDomino), 1)) {

                        if (state.placeDomino(state.playerTurn, state.Player1Hand.get(selectedDomino), 1)) {

                            if (state.playerTurn >= 3) {
                                state.playerTurn = 0;
                            } else {
                                state.playerTurn++;
                            }

                        }
                    }
                    sendInfo(state);

                }


            }
        });

        p3Sixth.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                if (state.playerTurn == 0) {
                    if (state.playableTrains(state.playerTurn, state.Player1Hand.get(selectedDomino), 2)) {

                        if (state.placeDomino(state.playerTurn, state.Player1Hand.get(selectedDomino), 2)) {

                            if (state.playerTurn >= 3) {
                                state.playerTurn = 0;
                            } else {
                                state.playerTurn++;
                            }

                        }
                    }
                    sendInfo(state);

                }

            }
        });

        p4Sixth.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                if (state.playerTurn == 0) {
                    if (state.playableTrains(state.playerTurn, state.Player1Hand.get(selectedDomino), 3)) {

                        if (state.placeDomino(state.playerTurn, state.Player1Hand.get(selectedDomino), 3)) {

                            if (state.playerTurn >= 3) {
                                state.playerTurn = 0;
                            } else {
                                state.playerTurn++;
                            }

                        }
                    }
                    sendInfo(state);

                }

            }
        });

        publicSixth.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                if (state.playerTurn == 0) {
                    if (state.playableTrains(state.playerTurn, state.Player1Hand.get(selectedDomino), 4)) {

                        if (state.placeDomino(state.playerTurn, state.Player1Hand.get(selectedDomino), 4)) {

                            if (state.playerTurn >= 3) {
                                state.playerTurn = 0;
                            } else {
                                state.playerTurn++;
                            }

                        }
                    }
                    sendInfo(state);

                }

            }
        });

        //which domino in hand is selected

        for (int i = 0; i < HandIVs.size(); i++) {
            HandIVs.get(i).setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {

                    for (int i = 0; i < HandIVs.size(); i++) {

                        HandIVs.get(i).setBackgroundResource(R.color.green_playboard);

                    }

                    Player1TrainIVs.get(5).setImageResource(R.color.green_playboard);
                    Player2TrainIVs.get(5).setImageResource(R.color.green_playboard);
                    Player3TrainIVs.get(5).setImageResource(R.color.green_playboard);
                    Player4TrainIVs.get(5).setImageResource(R.color.green_playboard);
                    PublicTrainIVs.get(5).setImageResource(R.color.green_playboard);


                    for (int i = 0; i < HandIVs.size(); i++) {

                        if (v == HandIVs.get(i)) {

                            selectedDomino = i;

                            HandIVs.get(i).setBackgroundResource(R.color.colorPrimary);


                            if (state.playableTrains(state.playerTurn, state.Player1Hand.get(i), 0)) {
                                Player1TrainIVs.get(5).setImageResource(R.drawable.purple_delete_button);
                                Player1TrainIVs.get(5).getLayoutParams().height = 50;
                                //Player1TrainIVs.get(4).getLayoutParams().height = 200;
                                //Player1TrainIVs.get(4).getLayoutParams().width = 200;
                            }
                            if (state.playableTrains(state.playerTurn, state.Player1Hand.get(i), 1)) {
                                Player2TrainIVs.get(5).setImageResource(R.drawable.purple_delete_button);
                                Player2TrainIVs.get(5).getLayoutParams().height = 50;
                                //Player2TrainIVs.get(4).getLayoutParams().height = 200;
                                //Player2TrainIVs.get(4).getLayoutParams().width= 200;
                            }
                            if (state.playableTrains(state.playerTurn, state.Player1Hand.get(i), 2)) {
                                Player3TrainIVs.get(5).setImageResource(R.drawable.purple_delete_button);
                                Player3TrainIVs.get(5).getLayoutParams().height = 50;
                            }
                            if (state.playableTrains(state.playerTurn, state.Player1Hand.get(i), 3)) {
                                Player4TrainIVs.get(5).setImageResource(R.drawable.purple_delete_button);
                                Player4TrainIVs.get(5).getLayoutParams().height = 50;
                            }
                            if (state.playableTrains(state.playerTurn, state.Player1Hand.get(i), 4)) {
                                PublicTrainIVs.get(5).setImageResource(R.drawable.purple_delete_button);
                                PublicTrainIVs.get(5).getLayoutParams().height = 50;
                                //PublicTrainIVs.get(4).getLayoutParams().height= 200;
                                //PublicTrainIVs.get(4).getLayoutParams().width= 200;

                            }

                        }

                    }

                }
            });

        }

    }

    @Override
    public void onClick(View view) {

    }
}

