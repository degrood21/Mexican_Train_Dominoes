package com.example.degrood21.mexican_train_dominoes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dylandegrood on 4/24/18.
 */

public class DominoGameStateTest {

    /**
     * After creating an instance of the Game State, which deals everyone 15 dominoes,
     * we test to make sure each hand has 15 dominoes
     */
    @Test
    public void dealActionTest() throws Exception {

        DominoGameState testState = new DominoGameState(4, 12);

        assertTrue(testState.hand.get(0).size() == 15); //dealt 15
        assertTrue(testState.hand.get(1).size() == 15); //dealt 15
        assertTrue(testState.hand.get(2).size() == 15); //dealt 15
        assertTrue(testState.hand.get(3).size() == 15); //dealt 15

    }

    /**
     * After creating and instance of the Game State, we find a domino in the
     * players hand that is able to match the round domino since it is
     * technically the first play of the game
     */
    @Test
    public void playableTrainsTest() throws Exception {

        DominoGameState testState = new DominoGameState(4, 12);

        int playableDominoIndex = -1; // index of domino that matches round (12)

        for (int i = 0; i < testState.hand.get(0).size(); i++) {
            if (testState.hand.get(0).get(i).rightSide == 12
                    || testState.hand.get(0).get(i).leftSide == 12) {

                playableDominoIndex = i;

            }
        }


        //System.out.println(selectedDomino.leftSide + " " + selectedDomino.rightSide);

        if (playableDominoIndex == -1) {
            // no playable domino in their hand
        } else {
            Domino selectedDomino = testState.hand.get(0).get(playableDominoIndex);
            boolean testPlayableTrain = testState.playableTrains(0, selectedDomino, 0);
            assertEquals(true, testPlayableTrain); // Make sure call to method returned true
        }

    }

    /**
     * After creating an instance of the Game State, we find all
     * 6 sided dominoes and remove them from all hands and the Pile,
     * while also trying to find the double 6 and place it in player 1's hand
     */
    @Test
    public void doubleEndOfTrainTest() throws Exception {

        DominoGameState testState = new DominoGameState(4, 12);

        // Testing to see if double 6 is played and there are no more 6's
        // anywhere that the game will recognize and set doublePlay
        // to false continuing the game as normal

        testState.doublePlayDomino = 6;

        for (int i = 0; i < testState.hand.get(0).size(); i++) {

            if (testState.hand.get(0).get(i).rightSide == 6 || testState.hand.get(0).get(i).leftSide == 6) {

                if (testState.hand.get(0).get(i).rightSide == 6 && testState.hand.get(0).get(i).leftSide == 6) {
                    testState.doublePlay = true;
                    testState.hand.get(0).remove(i);
                } else {
                    testState.hand.get(0).remove(i);
                }
                i = 0; //if a 6 is removed then search again from beginning until no more 6's
            }

        }

        for (int i = 0; i < testState.hand.get(1).size(); i++) {

            if (testState.hand.get(1).get(i).rightSide == 6 || testState.hand.get(1).get(i).leftSide == 6) {

                if (testState.hand.get(1).get(i).rightSide == 6 && testState.hand.get(1).get(i).leftSide == 6) {
                    testState.doublePlay = true;
                    testState.hand.get(1).remove(i);
                } else {
                    testState.hand.get(1).remove(i);
                }
                i = 0; //if a 6 is removed then search again from beginning until no more 6's
            }

        }

        for (int i = 0; i < testState.hand.get(2).size(); i++) {

            if (testState.hand.get(2).get(i).rightSide == 6 || testState.hand.get(2).get(i).leftSide == 6) {

                if (testState.hand.get(2).get(i).rightSide == 6 && testState.hand.get(2).get(i).leftSide == 6) {
                    testState.doublePlay = true;
                    testState.hand.get(2).remove(i);
                } else {
                    testState.hand.get(2).remove(i);
                }
                i = 0; //if a 6 is removed then search again from beginning until no more 6's
            }

        }

        for (int i = 0; i < testState.hand.get(3).size(); i++) {

            if (testState.hand.get(3).get(i).rightSide == 6 || testState.hand.get(3).get(i).leftSide == 6) {

                if (testState.hand.get(3).get(i).rightSide == 6 && testState.hand.get(3).get(i).leftSide == 6) {
                    testState.doublePlay = true;
                    testState.hand.get(3).remove(i);
                } else {
                    testState.hand.get(3).remove(i);
                }
                i = 0; //if a 6 is removed then search again from beginning until no more 6's
            }

        }

        for (int i = 0; i < testState.PileofDominoes.size(); i++) {

            if (testState.PileofDominoes.get(i).rightSide == 6 || testState.PileofDominoes.get(i).leftSide == 6) {

                if (testState.PileofDominoes.get(i).rightSide == 6 && testState.PileofDominoes.get(i).leftSide == 6) {
                    testState.doublePlay = true;
                    testState.PileofDominoes.remove(i);
                } else {
                    testState.PileofDominoes.remove(i);
                }
                i = 0; //if a 6 is removed then search again from beginning until no more 6's

            }

        }

        // doubleEndOfTrain, if a certain double is played (double 6) and no more
        // dominoes exist that could match this double, then doublePlay turns false
        // and game returns to normal play

        testState.doubleEndOfTrain(0); // doublePlay starts off as true until this method is ran

        assertEquals(false, testState.doublePlay); // doublePlay should be false at this point

    }

    /**
     * Once a Game State instance is created, we make sure
     * player 2 will have a playable domino for their first turn so a domino
     * with a 12 pip side in order to test if checkPlayable returns true
     * if they are able to play it on atleast one train
     */
    @Test
    public void checkPlayableTest() throws Exception {

        DominoGameState testState = new DominoGameState(4, 12);

        /**
         * This for loop assures that player 2 has a domino that can be played
         * in the beginning of the round (matching 12 pip)
         *
         */
        for (int i = 0; i < testState.PileofDominoes.size(); i++) {

            if (testState.PileofDominoes.get(i).leftSide == 12 || testState.PileofDominoes.get(i).rightSide == 12) {

                testState.hand.get(1).add(testState.PileofDominoes.get(i));
                testState.PileofDominoes.remove(i);
                break;

            }

        }

        // needs to return true since we make sure Player 1 has a playable 12 domino
        assertEquals(true, testState.checkPlayable(1, 0));


    }

    /**
     * After creating an instance of the Game State, we test
     * to make sure that the drawAction will draw from the Pile
     * and add it to corresponding Player id
     */
    @Test
    public void drawActionTest() throws Exception {

        DominoGameState testState = new DominoGameState(4, 12);

        // Tests to make sure a domino was drawn and put into player one's hand
        assertEquals(true, testState.drawAction(0));

        // Extra check to make sure Player one has one more domino
        assertEquals(16, testState.hand.get(0).size());

    }

    /**
     * After creating an instance of the Game State, we
     * grab a domino that can match the round since it is the first play of the game
     * and test that placeDomino method will place the domino and return true
     */
    @Test
    public void placeDominoTest() throws Exception {

        DominoGameState testState = new DominoGameState(4, 12);

        int playableDominoIndex = -1;

        for (int i = 0; i < testState.hand.get(0).size(); i++) {

            if (testState.hand.get(0).get(i).rightSide == 12 || testState.hand.get(0).get(i).leftSide == 12) {

                playableDominoIndex = i;
                break;

            }

        }

        if (playableDominoIndex == -1) {
            //no playable domino in their hand
        } else {
            assertEquals(true, testState.placeDomino(0, testState.hand.get(0).get(playableDominoIndex), 0));
            assertEquals(14, testState.hand.get(0).size());
        }


    }

    /**
     * After creating an instance of Game State, we grab the double 6
     * from any hand, or the Pile and add it to player ones hand. Also, we find all six's
     * and add them to player one's train so player one can play the double.
     * <p>
     * We then play the double and make sure it returns true while also changing doublePlay to true
     */
    @Test
    public void doublePlayTest() throws Exception {

        DominoGameState testState = new DominoGameState(4, 12);

        for (int i = 0; i < testState.hand.get(0).size(); i++) {

            if (testState.hand.get(0).get(i).rightSide == 6 || testState.hand.get(0).get(i).leftSide == 6) {

                testState.Player1Train.add(testState.hand.get(0).get(i));

            }

        }

        for (int i = 0; i < testState.hand.get(1).size(); i++) {

            if (testState.hand.get(1).get(i).rightSide == 6 || testState.hand.get(1).get(i).leftSide == 6) {

                if (testState.hand.get(1).get(i).rightSide == 6 && testState.hand.get(1).get(i).leftSide == 6) {
                    testState.hand.get(0).add(testState.hand.get(1).get(i));
                    //break;
                } else {
                    testState.Player1Train.add(testState.hand.get(1).get(i));
                }
            }

        }

        for (int i = 0; i < testState.hand.get(2).size(); i++) {

            if (testState.hand.get(2).get(i).rightSide == 6 || testState.hand.get(2).get(i).leftSide == 6) {

                if (testState.hand.get(2).get(i).rightSide == 6 && testState.hand.get(2).get(i).leftSide == 6) {
                    testState.hand.get(0).add(testState.hand.get(2).get(i));
                    //break;
                } else {
                    testState.Player1Train.add(testState.hand.get(2).get(i));
                }
            }

        }

        for (int i = 0; i < testState.hand.get(3).size(); i++) {

            if (testState.hand.get(3).get(i).rightSide == 6 || testState.hand.get(3).get(i).leftSide == 6) {

                if (testState.hand.get(3).get(i).rightSide == 6 && testState.hand.get(3).get(i).leftSide == 6) {
                    testState.hand.get(0).add(testState.hand.get(3).get(i));
                    //break;
                } else {
                    testState.Player1Train.add(testState.hand.get(3).get(i));
                }
            }

        }

        for (int i = 0; i < testState.PileofDominoes.size(); i++) {

            if (testState.PileofDominoes.get(i).rightSide == 6 || testState.PileofDominoes.get(i).leftSide == 6) {

                if (testState.PileofDominoes.get(i).rightSide == 6 && testState.PileofDominoes.get(i).leftSide == 6) {
                    testState.hand.get(0).add(testState.PileofDominoes.get(i));
                    //break;
                } else {
                    testState.Player1Train.add(testState.PileofDominoes.get(i));
                }

            }

        }

        Domino testDomino = testState.PileofDominoes.get(testState.randomDomino());
        int indexOfDouble = -1;

        for (int i = 0; i < testState.hand.get(0).size(); i++) {

            if (testState.hand.get(0).get(i).leftSide == 6 && testState.hand.get(0).get(i).rightSide == 6) {

                indexOfDouble = i;
                break;

            }

        }

        if (indexOfDouble == -1) {
            //there was no playable domino in their hand
        } else {
            assertEquals(true, testState.doublePlay(0, testState.hand.get(0).get(indexOfDouble), 0));
            /**
             * makes sure that as a result of doublePlay, doublePlay is then true (active)
             */
            assertEquals(true, testState.doublePlay);
        }


    }

}
