package com.example.degrood21.mexican_train_dominoes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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

    protected DominoGameState state; //current state of the game

    private Activity myActivity; //reference to the main activity for calling ImageViews etc.

    private int selectedDomino; // helper variable for currently selected domino

    private ArrayList<ImageView> HandIVs = new ArrayList<ImageView>(); //All ImageViews in Players Hand (max 20)
    private ArrayList<ImageView> PublicTrainIVs = new ArrayList<ImageView>(); //All ImageViews in Public Train (max 6)
    private ArrayList<ImageView> Player1TrainIVs = new ArrayList<ImageView>(); //All ImageViews in Player 1 Train (max 6)
    private ArrayList<ImageView> Player2TrainIVs = new ArrayList<ImageView>(); //All ImageViews in Player 2 Train (max 6)
    private ArrayList<ImageView> Player3TrainIVs = new ArrayList<ImageView>(); //All ImageViews in Player 3 Train (max 6)
    private ArrayList<ImageView> Player4TrainIVs = new ArrayList<ImageView>(); //All ImageViews in Player 4 Train (max 6)

    private Button quitButton, restartButton, helpButton, drawButton;
    private TextView player1TrainTV, player2TrainTV, player3TrainTV, player4TrainTV, p1ScoreTV, p2ScoreTV, p3ScoreTV, p4ScoreTV, roundTV, pileOfDominoCounter;
    private ImageView turnMarker1, turnMarker2, turnMarker3, turnMarker4, roundDom, p1First, p1Second,
            p1Third, p1Fourth, p1Fifth, p2Sixth, p2First, p2Second, p2Third, p2Fourth, p2Fifth,
            p1Sixth, p3First, p3Second, p3Third, p3Fourth, p3Fifth, p3Sixth, p4First, p4Second,
            p4Third, p4Fourth, p4Fifth, p4Sixth, publicFirst, publicSecond, publicThird,
            publicFourth, publicFifth, publicSixth, handOne, handTwo, handThree, handFour, handFive,
            handSix, handSeven, handEight, handNine, handTen, handEleven, handTwelve, handThirteen,
            handFourteen, handFifteen, handSixteen, handSeventeen, handEighteen, handNineteen,
            handTwenty;

    private boolean actionCaused = false;

    // cstor for Human Player
    public MTHumanPlayer(String name) {
        super(name);
    }

    /**
     * getTopView returns the top view of surface
     * <p>
     * (We do not use a surface or animation surface so
     * no need to send a top view)
     *
     * @return View of top view
     */
    @Override
    public View getTopView() {
        return null;
    }

    /**
     * receiveInfo receives the game state and updates GUI accordingly with changes
     *
     * @param info might not always be instanceof DominoGameState
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if (!(info instanceof DominoGameState)) {
            return;
        }

        this.state = (DominoGameState) info; //gets reference to state

        drawButton.setEnabled(true);

        //setup doublePlay message
        TextView myText = (TextView) myActivity.findViewById(R.id.doublePlayTV);
        myText.setText("DOUBLE PLAY!\n");
        if (state.doublePlayTrain < 4) {
            myText.append("Play on: Train " + (state.doublePlayTrain + 1));
        } else {
            myText.append("Play on: Public Train");
        }
        myText.setTextColor(Color.BLACK);
        myText.setBackgroundColor(Color.argb(255, 255, 0, 0));
        /**
         * External Citation
         * Date: 9 April 2018
         * Problem: Causing a text to display only when we are in DoublePlay
         * Resource: https://stackoverflow.com/questions/23468302/how-to-change-text-visibility-on-android-application
         * Solution: SetVisibility of mtText to Visible that says DoublePLay is in play
         * and the train it is occurring onto. This notifies the user that Doubleplay
         * is in motion, making the user aware.
         */
        if (state.doublePlay) {
            myText.setVisibility(View.VISIBLE);//if double play is true make it visible
        } else {
            myText.setVisibility(View.INVISIBLE);//if not double play its false
        }

        game.sendAction(new roundOverAction(this, state));

        //sets the TextView to display the scores of each player after each round
        p1ScoreTV.setText("" + allPlayerNames[0] + ": " + state.player1Score + " pips");
        p2ScoreTV.setText("" + allPlayerNames[1] + ": " + state.player2Score + " pips");
        p3ScoreTV.setText("" + allPlayerNames[2] + ": " + state.player3Score + " pips");
        p4ScoreTV.setText("" + allPlayerNames[3] + ": " + state.player4Score + " pips");


        /**
         * All for loops and if statements until next comment
         * are used to draw and update all ImageViews in hand
         * and all players trains and also the public train
         *
         */
        for (int i = 0; i < 20; i++) {

            HandIVs.get(i).setImageResource(R.color.green_playboard);
            HandIVs.get(i).getLayoutParams().width = 200;

        }
        for (int i = 0; i < state.hand.get(playerNum).size(); i++) {

            HandIVs.get(i).setImageResource(state.hand.get(playerNum).get(i).pictureID);
            HandIVs.get(i).getLayoutParams().width = 200;

        }

        for (int i = Player1TrainIVs.size() - 2; i >= 0; i--) {

            Player1TrainIVs.get(i).setImageResource(R.color.green_playboard);
            Player1TrainIVs.get(i).getLayoutParams().width = 200;

        }
        if (state.Player1Train.size() != 0) {
            int j = state.Player1Train.size() - 1;//j=4
            for (int i = Player1TrainIVs.size() - 2; i >= 0; i--) {//i=3

                if (j >= 0) {
                    if (state.Player1Train.get(j).rightSide == -1) {// -1 indicates already matched side
                        Player1TrainIVs.get(i).setImageResource(state.Player1Train.get(j).pictureID);
                        Player1TrainIVs.get(i).getLayoutParams().width = 200;
                        Player1TrainIVs.get(i).setRotation(180); // rotates based on -1 indicator
                        j--;
                    } else {
                        Player1TrainIVs.get(i).setImageResource(state.Player1Train.get(j).pictureID);
                        Player1TrainIVs.get(i).getLayoutParams().width = 200;
                        Player1TrainIVs.get(i).setRotation(0); // locks rotation if rightside is not -1
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
            int j = state.Player2Train.size() - 1;//if size = 8 = 7
            for (int i = Player2TrainIVs.size() - 2; i >= 0; i--) {

                if (j >= 0) {
                    if (state.Player2Train.get(j).rightSide == -1) {
                        Player2TrainIVs.get(i).setImageResource(state.Player2Train.get(j).pictureID);
                        Player2TrainIVs.get(i).getLayoutParams().width = 200;
                        Player2TrainIVs.get(i).setRotation(180);
                        j--;
                    } else {
                        Player2TrainIVs.get(i).setImageResource(state.Player2Train.get(j).pictureID);
                        Player2TrainIVs.get(i).getLayoutParams().width = 200;
                        Player2TrainIVs.get(i).setRotation(0);
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
                        Player3TrainIVs.get(i).setRotation(0);
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
                        Player4TrainIVs.get(i).setRotation(0);
                        j--;
                    }
                }

            }
        }


        for (int i = PublicTrainIVs.size() - 2; i >= 0; i--) {

            PublicTrainIVs.get(i).setImageResource(R.color.green_playboard);
            PublicTrainIVs.get(i).getLayoutParams().width = 200;

        }
        if (state.PublicTrain.size() != 0) {
            int j = state.PublicTrain.size() - 1;
            for (int i = PublicTrainIVs.size() - 2; i >= 0; i--) {

                if (j >= 0) {
                    if (state.PublicTrain.get(j).rightSide == -1) {
                        PublicTrainIVs.get(i).setImageResource(state.PublicTrain.get(j).pictureID);
                        PublicTrainIVs.get(i).getLayoutParams().width = 200;
                        PublicTrainIVs.get(i).setRotation(180);
                        j--;
                    } else {
                        PublicTrainIVs.get(i).setImageResource(state.PublicTrain.get(j).pictureID);
                        PublicTrainIVs.get(i).getLayoutParams().width = 200;
                        PublicTrainIVs.get(i).setRotation(0);
                        j--;
                    }
                }

            }
        }

        //highlights the player whose turn it is
        if (state.playerTurn == 0) {
            player1TrainTV.setBackgroundResource(R.color.colorAccent);
            player2TrainTV.setBackgroundResource(R.color.green_playboard);
            player3TrainTV.setBackgroundResource(R.color.green_playboard);
            player4TrainTV.setBackgroundResource(R.color.green_playboard);
        } else if (state.playerTurn == 1) {
            player1TrainTV.setBackgroundResource(R.color.green_playboard);
            player2TrainTV.setBackgroundResource(R.color.colorAccent);
            player3TrainTV.setBackgroundResource(R.color.green_playboard);
            player4TrainTV.setBackgroundResource(R.color.green_playboard);
        } else if (state.playerTurn == 2) {
            player1TrainTV.setBackgroundResource(R.color.green_playboard);
            player2TrainTV.setBackgroundResource(R.color.green_playboard);
            player3TrainTV.setBackgroundResource(R.color.colorAccent);
            player4TrainTV.setBackgroundResource(R.color.green_playboard);
        } else if (state.playerTurn == 3) {
            player1TrainTV.setBackgroundResource(R.color.green_playboard);
            player2TrainTV.setBackgroundResource(R.color.green_playboard);
            player3TrainTV.setBackgroundResource(R.color.green_playboard);
            player4TrainTV.setBackgroundResource(R.color.colorAccent);
        }

        /**
         * All if statements till next comment sets a trainMarker in front of train
         * depending on if that train is public at the moment
         *
         * using turnMarkers to display whether or not a player train is public
         */
        if (state.playerPublic.get(0)) {
            turnMarker1.setImageResource(R.drawable.trainmarker);
            turnMarker1.getLayoutParams().width = 40;
        } else

        {
            turnMarker1.setImageResource(R.color.green_playboard);
            turnMarker1.getLayoutParams().width = 40;
        }
        if (state.playerPublic.get(1))

        {
            turnMarker2.setImageResource(R.drawable.trainmarker);
            turnMarker2.getLayoutParams().width = 40;
        } else

        {
            turnMarker2.setImageResource(R.color.green_playboard);
            turnMarker2.getLayoutParams().width = 40;
        }
        if (state.playerPublic.get(2))

        {
            turnMarker3.setImageResource(R.drawable.trainmarker);
            turnMarker3.getLayoutParams().width = 40;
        } else

        {
            turnMarker3.setImageResource(R.color.green_playboard);
            turnMarker3.getLayoutParams().width = 40;
        }
        if (state.playerPublic.get(3))

        {
            turnMarker4.setImageResource(R.drawable.trainmarker);
            turnMarker4.getLayoutParams().width = 40;
        } else

        {
            turnMarker4.setImageResource(R.color.green_playboard);
            turnMarker4.getLayoutParams().width = 40;
        }

        //Sets the round image according to what round it is
        if (!(state.PublicTrain.size() == 0)) {
            roundDom.setImageResource(state.PublicTrain.get(0).pictureID);
            roundDom.getLayoutParams().width = 150;
        }

        //Displays to player in words what round it is
        roundTV.setText("Round: Double " + state.PublicTrain.get(0).rightSide);
        pileOfDominoCounter.setText("" + state.PileofDominoes.size());

        //Displays the names for players in front of their train
        player1TrainTV.setText(allPlayerNames[0]);
        player2TrainTV.setText(allPlayerNames[1]);
        player3TrainTV.setText(allPlayerNames[2]);
        player4TrainTV.setText(allPlayerNames[3]);

    }

    /**
     * setAsGui -- ultimately connects the GUI to the GameState to update GUI to see
     * all changes
     *
     * @param activity MainActivity to reference Buttons, ImageViews, etc.
     */
    @Override
    public void setAsGui(GameMainActivity activity) {

        myActivity = activity;

        activity.setContentView(R.layout.activity_main);

        // Creating the button and ImageViews used to draw the dominoes onto the screen

        quitButton = (Button) myActivity.findViewById(R.id.quitButton);
        restartButton = (Button) myActivity.findViewById(R.id.restartButton);
        helpButton = (Button) myActivity.findViewById(R.id.button);
        drawButton = (Button) myActivity.findViewById(R.id.drawButton);
        /**
         * External Citation
         * Date: 23 April, 2018
         * Problem: During Network play, pressing the draw button before host initialized crashed the game.
         * Source: Professor Nuxoll
         * Solution: In setAsGUI() we set the drawButton to disabled. And when
         * receiveInfo gets called for the first time, we enable it.
         */
        drawButton.setEnabled(false);

        //score TextViews
        p1ScoreTV = (TextView) myActivity.findViewById(R.id.player1Score);
        p2ScoreTV = (TextView) myActivity.findViewById(R.id.player2Score);
        p3ScoreTV = (TextView) myActivity.findViewById(R.id.player3Score);
        p4ScoreTV = (TextView) myActivity.findViewById(R.id.player4Score);
        roundTV = (TextView) myActivity.findViewById(R.id.roundView);
        pileOfDominoCounter = (TextView) myActivity.findViewById(R.id.domPileNumTV);
        roundDom = (ImageView) myActivity.findViewById(R.id.roundDominoIV);

        //will display train image when specific train is public
        turnMarker1 = (ImageView) myActivity.findViewById(R.id.turnMarker);
        turnMarker2 = (ImageView) myActivity.findViewById(R.id.turnMarker2);
        turnMarker3 = (ImageView) myActivity.findViewById(R.id.turnMarker3);
        turnMarker4 = (ImageView) myActivity.findViewById(R.id.turnMarker4);

        //names of players
        player1TrainTV = (TextView) myActivity.findViewById(R.id.player1Train);
        player2TrainTV = (TextView) myActivity.findViewById(R.id.player2Train);
        player3TrainTV = (TextView) myActivity.findViewById(R.id.player3Train);
        player4TrainTV = (TextView) myActivity.findViewById(R.id.player4train);

        //ImageViews of all of the trains
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

        //ImageViews for the player's hand
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


        // OnClickListeners to listen to Buttons and last ImageView of each train in order to place a domino

        //quits game
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myActivity.finish();//quits program
            }
        });

        //restarts game when button is pushed
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 External Citation:
                 Date: 13 April 2018
                 Problem: I wasn't sure how to restart.
                 Resource: https://stackoverflow.com/questions/16116664/making-a-button-restart-the-activity
                 Solution: I was inspired the code from one of the answers
                 */
                Intent intent = myActivity.getIntent();
                myActivity.finish();
                myActivity.startActivity(intent);

            }
        });

        //displays menu containing game rules when button is pressed
        helpButton.setOnClickListener(new View.OnClickListener() {
            //displays rules when question mark button is pressed
            @Override
            public void onClick(View v) {
                /**
                 External Citation:
                 Date: 15 April 2018
                 Problem: I wasn't sure how to start a new activity
                 Resource: https://www.youtube.com/watch?v=n21mXO1ASJM&t=62s
                 Solution: I used the code from this video.
                 */
                myActivity.startActivity(new Intent(myActivity, HelpMenu.class));
            }
        });

        //draws a domino if the game allows when pressed
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // When clicked, draws a domino using Player 1 ID (Human Player)
                if (playerNum == state.playerTurn) {
                    sendDrawAction();
                }

            }
        });

        /**
         * All Sixth ImageView in each train is listening for a click in order to place domino within that train
         *
         */
        p1Sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerNum == state.playerTurn) {

                    sendPlaceAction(0);
                }

            }
        });

        p2Sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (state.playerTurn == playerNum) {

                    sendPlaceAction(1);
                }


            }
        });

        p3Sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (state.playerTurn == playerNum) {

                    sendPlaceAction(2);
                }

            }
        });

        p4Sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (state.playerTurn == playerNum) {

                    sendPlaceAction(3);
                }

            }
        });

        publicSixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (state.playerTurn == playerNum) {

                    sendPlaceAction(4);
                }

            }
        });

        //Goes through each Hand ImageView to see which one is selected

        for (int i = 0; i < HandIVs.size(); i++) {
            HandIVs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (playerNum == state.playerTurn) {
                        for (int i = 0; i < HandIVs.size(); i++) {

                            HandIVs.get(i).setBackgroundResource(R.color.green_playboard);

                        }

                        Player1TrainIVs.get(5).setImageResource(R.color.green_playboard);
                        Player2TrainIVs.get(5).setImageResource(R.color.green_playboard);
                        Player3TrainIVs.get(5).setImageResource(R.color.green_playboard);
                        Player4TrainIVs.get(5).setImageResource(R.color.green_playboard);
                        PublicTrainIVs.get(5).setImageResource(R.color.green_playboard);


                        // Depending on what domino is currently selected in Hand
                        // Will display on last ImageView of each train, whether that domino can be played there
                        //
                        // This is based on player turn, if trains are public, and if domino last played matched selected
                        for (int i = 0; i < HandIVs.size(); i++) {

                            if (v == HandIVs.get(i)) {

                                selectedDomino = i;

                                HandIVs.get(i).setBackgroundResource(R.color.colorPrimary);

                                if (state.doublePlayTrain == 0 && state.doublePlay || !state.doublePlay) {
                                    if (state.playableTrains(state.playerTurn, state.hand.get(playerNum).get(i), 0)) {
                                        Player1TrainIVs.get(5).setImageResource(R.drawable.purple_delete_button);
                                        Player1TrainIVs.get(5).getLayoutParams().height = 50;
                                    }
                                }
                                if (state.doublePlayTrain == 1 && state.doublePlay || !state.doublePlay) {
                                    if (state.playableTrains(state.playerTurn, state.hand.get(playerNum).get(i), 1)) {
                                        Player2TrainIVs.get(5).setImageResource(R.drawable.purple_delete_button);
                                        Player2TrainIVs.get(5).getLayoutParams().height = 50;
                                    }
                                }
                                if (state.doublePlayTrain == 2 && state.doublePlay || !state.doublePlay) {
                                    if (state.playableTrains(state.playerTurn, state.hand.get(playerNum).get(i), 2)) {
                                        Player3TrainIVs.get(5).setImageResource(R.drawable.purple_delete_button);
                                        Player3TrainIVs.get(5).getLayoutParams().height = 50;
                                    }
                                }
                                if (state.doublePlayTrain == 3 && state.doublePlay || !state.doublePlay) {
                                    if (state.playableTrains(state.playerTurn, state.hand.get(playerNum).get(i), 3)) {
                                        Player4TrainIVs.get(5).setImageResource(R.drawable.purple_delete_button);
                                        Player4TrainIVs.get(5).getLayoutParams().height = 50;
                                    }
                                }
                                if (state.doublePlayTrain == 4 && state.doublePlay || !state.doublePlay) {
                                    if (state.playableTrains(state.playerTurn, state.hand.get(playerNum).get(i), 4)) {
                                        PublicTrainIVs.get(5).setImageResource(R.drawable.purple_delete_button);
                                        PublicTrainIVs.get(5).getLayoutParams().height = 50;
                                    }
                                }

                            }

                        }
                    }

                }
            });

        }
        if (state != null) {
            receiveInfo(state);
        }
    }

    /**
     * Helps to determine whether to draw when in Double Play
     * <p>
     * Meaning that the Human player after playing a double, must play a matching domino or must draw if they cannot
     */
    public void doubleHelper() {

        if (playerNum == state.playerTurn) {
            if (state.placeDomino(playerNum, state.hand.get(playerNum).get(selectedDomino), state.doublePlayTrain)) {
                state.doublePlay = false;
                state.playerTurn++;
            } else {
                state.drawAction(playerNum);
                if (state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), state.doublePlayTrain)) {
                    state.placeDomino(playerNum


                            , state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), state.doublePlayTrain);
                    state.playerPublic.set(playerNum, false);
                    state.playerTurn++;
                } else {
                    state.playerTurn++;
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
    }

    public void sendPlaceAction(int trainNum) {
        game.sendAction(new MTPlaceAction(this, trainNum, selectedDomino));
        resetPlaceHelpers();
    }

    public void sendDrawAction() {
        game.sendAction(new MTDrawAction(this));
        resetPlaceHelpers();
    }

    /**
     * resetPlaceHelpers recolors the space with the purple x and background of selected ImageView
     * to the color of the board after a domino is placed
     */
    public void resetPlaceHelpers() {

        selectedDomino = 0;
        //After every place action reset all purple x views
        Player1TrainIVs.get(5).setImageResource(R.color.green_playboard);
        Player1TrainIVs.get(5).getLayoutParams().height = 50;
        Player2TrainIVs.get(5).setImageResource(R.color.green_playboard);
        Player2TrainIVs.get(5).getLayoutParams().height = 50;
        Player3TrainIVs.get(5).setImageResource(R.color.green_playboard);
        Player3TrainIVs.get(5).getLayoutParams().height = 50;
        Player4TrainIVs.get(5).setImageResource(R.color.green_playboard);
        Player4TrainIVs.get(5).getLayoutParams().height = 50;
        PublicTrainIVs.get(5).setImageResource(R.color.green_playboard);
        PublicTrainIVs.get(5).getLayoutParams().height = 50;

        for (int i = 0; i < HandIVs.size(); i++) {
            HandIVs.get(i).setBackgroundResource(R.color.green_playboard);
        }

    }

}

