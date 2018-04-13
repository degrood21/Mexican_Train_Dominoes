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
     *
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

        //If it's my turn to play a domino,
        //delay for one and a half seconds(1500); then play
        sleep(500);

        if (!comDifficulty) {
            //simple implementation of computer player
            //check if this player's Hand still contains dominoes and if this player is player one
            /*if(mtState.playerTurn == playerNum) {
                if(mtState.hand.get(playerNum).size() > 0) {
                    for(int i = 0; i < mtState.hand.get(playerNum).size(); i++) {
                        if(mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 0)
                                || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 1)
                                || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 2)
                                || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 3)
                                || mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(i), 4)) {
                            if(mtState.doublePlay) {
                                if(mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), mtState.doublePlayTrain)) {
                                    mtState.doublePlay = false;
                                    mtState.playerTurn++;
                                }
                                else {
                                    mtState.playerPublic.set(playerNum, true);
                                }
                            }
                            else if(mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), ))
                        }
                    }
                }
            }*/

            if (playerNum == 1) {
                if (mtState.Player2Hand.size() > 0 && playerNum == 1) {
                    for (int i = 0; i < mtState.Player2Hand.size(); i++) {
                        if (mtState.playableTrains(1,mtState.Player2Hand.get(i), 4)
                                || mtState.playableTrains(1,mtState.Player2Hand.get(i), 1)
                                || mtState.playableTrains(1,mtState.Player2Hand.get(i), 0)
                                || mtState.playableTrains(1,mtState.Player2Hand.get(i), 2)
                                || mtState.playableTrains(1,mtState.Player2Hand.get(i), 3)) {

                            if(mtState.doublePlay){
                                if(mtState.placeDomino(1, mtState.Player2Hand.get(i), mtState.doublePlayTrain)){
                                    mtState.doublePlay = false;
                                    mtState.playerTurn++;
                                }
                                else{
                                    mtState.player2Public = true;
                                }
                            }
                            else if(mtState.placeDomino(1, mtState.Player2Hand.get(i), 1)){
                                mtState.player2Public = false;
                                mtState.playerTurn++;
                            }
                            else if(mtState.placeDomino(1, mtState.Player2Hand.get(i), 2)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(1, mtState.Player2Hand.get(i), 3)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(1, mtState.Player2Hand.get(i), 4)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(1, mtState.Player2Hand.get(i), 0)){mtState.playerTurn++;}

                            if (mtState.playerTurn >= 3) {
                                mtState.playerTurn = 0;
                            }
                            break;
                        }
                    }

                    if (mtState.playerTurn == 1) {

                            if (mtState.drawAction(1)) {
                                if (mtState.playableTrains(1, mtState.Player2Hand.get(mtState.Player2Hand.size() - 1), 1)) {
                                    mtState.placeDomino(1, mtState.Player2Hand.get(mtState.Player2Hand.size() - 1), 1);
                                    mtState.player2Public = false;
                                    mtState.playerTurn++;
                                } else {
                                    mtState.playerTurn++;
                                }
                            }

                        else{
                            mtState.player2Public = true;
                            mtState.playerTurn++;
                        }

                    }

                }
            } else if (playerNum == 2) {
                if (mtState.Player3Hand.size() > 0 && playerNum == 2) {

                    for (int i = 0; i < mtState.Player3Hand.size(); i++) {
                        if (mtState.playableTrains(2,mtState.Player3Hand.get(i), 4)
                                || mtState.playableTrains(2,mtState.Player3Hand.get(i), 2)
                                || mtState.playableTrains(2,mtState.Player3Hand.get(i), 0)
                                || mtState.playableTrains(2,mtState.Player3Hand.get(i), 3)
                                || mtState.playableTrains(2,mtState.Player3Hand.get(i), 1)) {

                            if(mtState.doublePlay){
                                if(mtState.placeDomino(2, mtState.Player3Hand.get(i), mtState.doublePlayTrain)){
                                    mtState.doublePlay = false;
                                    mtState.playerTurn++;
                                }
                                else{
                                    mtState.player3Public = true;
                                }
                            }
                            else if(mtState.placeDomino(2, mtState.Player3Hand.get(i), 2)){
                                mtState.player3Public = false;
                                mtState.playerTurn++;
                            }
                            else if(mtState.placeDomino(2, mtState.Player3Hand.get(i), 4)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(2, mtState.Player3Hand.get(i), 3)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(2, mtState.Player3Hand.get(i), 1)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(2, mtState.Player3Hand.get(i), 0)){mtState.playerTurn++;}

                            break;
                        }
                    }

                    if (mtState.playerTurn == 2) {

                        if(mtState.drawAction(2)) {
                            if (mtState.playableTrains(2, mtState.Player3Hand.get(mtState.Player3Hand.size() - 1), 2)) {
                                mtState.placeDomino(2, mtState.Player3Hand.get(mtState.Player3Hand.size() - 1), 2);
                                mtState.player3Public = false;
                                mtState.playerTurn++;
                            } else {
                                mtState.playerTurn++;
                            }
                        }
                        else{
                            mtState.player3Public = true;
                            mtState.playerTurn++;
                        }

                    }

                }
            } else if (playerNum == 3) {
                if (mtState.Player4Hand.size() > 0 && playerNum == 3) {
                    for (int i = 0; i < mtState.Player4Hand.size(); i++) {
                        if (mtState.playableTrains(3,mtState.Player4Hand.get(i), 4)
                                || mtState.playableTrains(3,mtState.Player4Hand.get(i), 3)
                                || mtState.playableTrains(3,mtState.Player4Hand.get(i), 0)
                                || mtState.playableTrains(3,mtState.Player4Hand.get(i), 2)
                                || mtState.playableTrains(3,mtState.Player4Hand.get(i), 1)) {

                            if(mtState.doublePlay){
                                if(mtState.placeDomino(3, mtState.Player4Hand.get(i), mtState.doublePlayTrain)){
                                    mtState.doublePlay = false;
                                    mtState.playerTurn++;
                                }
                                else{
                                    mtState.player4Public = true;
                                }
                            }
                            else if(mtState.placeDomino(3, mtState.Player4Hand.get(i), 3)){
                                mtState.player4Public = false;
                                mtState.playerTurn++;
                            }
                            else if(mtState.placeDomino(3, mtState.Player4Hand.get(i), 4)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(3, mtState.Player4Hand.get(i), 1)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(3, mtState.Player4Hand.get(i), 2)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(3, mtState.Player4Hand.get(i), 0)){mtState.playerTurn++;}

                            if (mtState.playerTurn >= 3) {
                                mtState.playerTurn = 0;
                            }

                            break;
                        }
                    }
                    if (mtState.playerTurn == 3) {

                        if(mtState.drawAction(3)) {
                            if (mtState.playableTrains(3, mtState.Player4Hand.get(mtState.Player4Hand.size() - 1), 3)) {
                                mtState.placeDomino(3, mtState.Player4Hand.get(mtState.Player4Hand.size() - 1), 3);
                                mtState.player4Public = false;
                                mtState.playerTurn = 0;
                            } else {
                                mtState.playerTurn = 0;
                            }
                        }
                        else{
                            mtState.player4Public = true;
                            mtState.playerTurn = 0;
                        }

                    }
                }
            }

            sendInfo(mtState);

        } else {
            //intelligent implementation of computer player, for now does nothing.

        }
        //}
    }
}
