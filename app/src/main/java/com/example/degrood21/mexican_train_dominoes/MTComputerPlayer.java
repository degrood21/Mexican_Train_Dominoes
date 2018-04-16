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
            if(mtState.playerTurn == playerNum) {
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
                            else if(mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), playerNum)) {
                                mtState.playerPublic.set(playerNum, false);
                                mtState.playerTurn++;
                            }
                            else if(mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 0)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 1)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 2)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 3)){mtState.playerTurn++;}
                            else if(mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(i), 4)){mtState.playerTurn++;}

                            if (mtState.playerTurn >= 3) {
                                mtState.playerTurn = 0;
                            }
                            break;
                        }
                    }
                    if (mtState.playerTurn == playerNum) {
                        if(mtState.drawAction(playerNum)){
                            if(mtState.playableTrains(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), playerNum)) {
                                mtState.placeDomino(playerNum, mtState.hand.get(playerNum).get(mtState.hand.get(playerNum).size() - 1), playerNum);
                                mtState.playerPublic.set(playerNum, false);
                                mtState.playerTurn++;
                            } else {
                                mtState.playerTurn++;
                            }
                        }
                        else {
                            mtState.playerPublic.set(playerNum, true);
                            mtState.playerTurn++;
                        }
                    }
                }
            }
            sendInfo(mtState);
        }
        else {
            //intelligent implementation of computer player, for now does nothing.

        }
    }
}//end of MTComputerPlayer class
