package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GameComputerPlayer;
import com.example.degrood21.mexican_train_dominoes.game.infoMsg.GameInfo;

/**
 * This is the computer player for the Mexican Train Domino game, at it's most basic level,
 * it is capable of checking its hand for any playable domino. At a higher intellect level,
 * it is capable of constructing a string of dominoes from its hand that can be played turn after turn.
 *
 * @author Dylan Degrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class MTComputerPlayer extends GameComputerPlayer {

    //Difficulty of computer player
    private boolean comDifficulty = false;
    //state of game
    private DominoGameState mtState;

    /**
     * Constructor for the MTComputerPlayer
     *
     * @param name The player's name
     */
    public MTComputerPlayer(String name) {
        this(name, false);
    }

    /**
     * Copy Constructor for the MTComputerPlayer class
     *
     * @param name
     */
    public MTComputerPlayer(String name, boolean difficulty) {
        super(name);
        //set difficulty
        comDifficulty = difficulty;

    }

    /**
     * receiveInfo receives the info from the game state and has computer player play accordingly
     * <p>
     * Computer Player (Basic) just plays on next available train to be played
     * on using the first playable domino in hand
     *
     * @param info not always an instance of DominoGameState
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        //If we don't have a game-state, ignore
        if (!(info instanceof DominoGameState)) {
            return;
        }
        //update our variable
        mtState = (DominoGameState) info;
        //int playerNum = mtState.playerTurn;
        int playerNum = this.playerNum;

        if (!comDifficulty) {
            //simple implementation of computer player
            //check if this player's Hand still contains dominoes and if this player is player one
            if (mtState.playerTurn == playerNum) {//if its your turn
                if (mtState.hand.get(playerNum).size() > 0) {//if you have a domino in hand
                    for (int i = 0; i < mtState.hand.get(playerNum).size(); i++) {//loops through hand
                        if (mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 0)//looks to see if you can play on any train
                                || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 1)
                                || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 2)
                                || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 3)
                                || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 4)) {

                            if (mtState.doublePlay) {//checks if doubleplay is active
                                if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), mtState.doublePlayTrain)) {
                                    mtState.doublePlay = false;//sets doubleplay to false
                                    mtState.playerTurn++;//increment turn
                                } else {
                                    mtState.playerPublic.set(playerNum, true);
                                }
                            } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), playerNum)) {//places on own train
                                mtState.playerPublic.set(playerNum, false);
                                mtState.playerTurn++;
                            } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 0)) {//places on train 0
                                mtState.playerTurn++;
                            } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 1)) {//places on train 1
                                mtState.playerTurn++;
                            } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 2)) {//places on train 2
                                mtState.playerTurn++;
                            } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 3)) {//places on train 3
                                mtState.playerTurn++;
                            } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 4)) {//places on public train
                                mtState.playerTurn++;
                            }

                            if (mtState.playerTurn > 3) {//checks turn value for next player
                                mtState.playerTurn = 0;
                            }
                            break;
                        }
                    }
                    if (mtState.playerTurn == playerNum) {//if its still your turn then you couldnt play
                        if (mtState.drawAction(playerNum)) {//draws
                            if (mtState.doublePlay) {
                                if (mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), mtState.doublePlayTrain)) {
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), mtState.doublePlayTrain);
                                    mtState.playerTurn++;
                                    if (mtState.playerTurn > 3) {//check this
                                        mtState.playerTurn = 0;
                                    }
                                }
                            }
                            //if you can now play any domino on any public train play it
                            else if (mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 0)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 1)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 2)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 3)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 4)) {
                                //trys to play on all trains with your ned domino
                                if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), playerNum)) {
                                    mtState.playerPublic.set(playerNum, false);
                                    //sets your train to false since you played on your own train
                                } else {
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 0);
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 1);
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 2);
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 3);
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 4);
                                    //sets your train to false since you played
                                    mtState.playerPublic.set(playerNum, true);
                                    mtState.playerTurn++;
                                    if (mtState.playerTurn > 3) {
                                        mtState.playerTurn = 0;
                                    }
                                }
                            }
                        } else {//if you can't draw at all
                            mtState.playerPublic.set(playerNum, true);
                            mtState.playerTurn++;
                            if (mtState.playerTurn > 3) {
                                mtState.playerTurn = 0;
                            }
                        }
                    }

                }
            }
            sleep(1500);//delay for one and a half seconds(1500); then play
            sendInfo(mtState);//redraws the state
        } else {
            //for(int i = 0; i < mtState.hand.get(playerNum).size(); i++){
            //    if(mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 0)) {
            //    }
            //}
            //intelligent implementation of computer player, for now does nothing.
            //call checkplayable, if none, draw.
            //if(doubleplay == truue) satisfy double if possible
            //else draw
            //calls smartAI in DominoGameState
            //first call check playable on each domino, do in smartAI


            //BETA computer below here
            //see above comments, code is very similar
            //Plays on own train if public to make it private and thus harder for the opponents to use pieces
            if (mtState.playerTurn == playerNum) {
                if (mtState.hand.get(playerNum).size() > 0) {
                    //if this for loop happens the other doesnt
                    for (int i = 0; i < mtState.hand.get(playerNum).size(); i++) {
                        if (mtState.playerPublic.get(playerNum)) {//if your train is public
                            if (mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), playerNum)) {
                                //if you can play on your own train
                                if (mtState.doublePlay) {
                                    if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), mtState.doublePlayTrain)) {
                                        mtState.doublePlay = false;
                                        mtState.playerTurn++;
                                    } else {
                                        mtState.playerPublic.set(playerNum, true);
                                    }
                                } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), playerNum)) {
                                    mtState.playerPublic.set(playerNum, false);
                                    mtState.playerTurn++;//plays on own train
                                }
                            }
                        }
                    }

                    if (mtState.playerTurn == playerNum) {//if its still your turn check the rest of the hand
                        for (int i = 0; i < mtState.hand.get(playerNum).size(); i++) {

                            if (mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 0)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 1)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 2)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 3)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 4)) {

                                if (mtState.doublePlay) {
                                    if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), mtState.doublePlayTrain)) {
                                        mtState.doublePlay = false;
                                        mtState.playerTurn++;
                                    } else {
                                        mtState.playerPublic.set(playerNum, true);
                                    }
                                } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), playerNum)) {
                                    mtState.playerPublic.set(playerNum, false);
                                    mtState.playerTurn++;
                                } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 0)) {
                                    mtState.playerTurn++;
                                } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 1)) {
                                    mtState.playerTurn++;
                                } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 2)) {
                                    mtState.playerTurn++;
                                } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 3)) {
                                    mtState.playerTurn++;
                                } else if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 4)) {
                                    mtState.playerTurn++;
                                }

                                if (mtState.playerTurn > 3) {
                                    mtState.playerTurn = 0;
                                }
                                break;
                            }
                        }
                    }
                    if (mtState.playerTurn == playerNum) {
                        if (mtState.drawAction(playerNum)) {//draws
                            if (mtState.doublePlay) {
                                if (mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), mtState.doublePlayTrain)) {
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), mtState.doublePlayTrain);
                                    mtState.playerTurn++;
                                    if (mtState.playerTurn > 3) {//check this
                                        mtState.playerTurn = 0;
                                    }
                                }
                            }
                            //if you can now play any domino on any public train play it
                            else if (mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 0)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 1)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 2)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 3)
                                    || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 4)) {
                                //trys to play on all trains with your ned domino
                                if (mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), playerNum)) {
                                    mtState.playerPublic.set(playerNum, false);
                                    //sets your train to false since you played on your own train
                                } else {
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 0);
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 1);
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 2);
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 3);
                                    mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), 4);
                                    //sets your train to false since you played
                                    mtState.playerPublic.set(playerNum, true);
                                    mtState.playerTurn++;
                                    if (mtState.playerTurn > 3) {
                                        mtState.playerTurn = 0;
                                    }
                                }
                            }
                        } else {//if you can't draw at all
                            mtState.playerPublic.set(playerNum, true);
                            mtState.playerTurn++;
                            if (mtState.playerTurn > 3) {
                                mtState.playerTurn = 0;
                            }
                        }
                    }

                }
            }
            sleep(1500);//delay for one and a half seconds(1500); then play
            sendInfo(mtState);
        }
    }
}//end of MTComputerPlayer class
