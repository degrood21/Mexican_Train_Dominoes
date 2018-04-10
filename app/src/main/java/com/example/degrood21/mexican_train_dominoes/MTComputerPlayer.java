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
    private boolean comDifficulty;
    //state of game
    private DominoGameState mtState;

    /**
     * Constructor for the MTComputerPlayer
     *
     * @param name
     *      The player's name
     */
    public MTComputerPlayer(String name) {
        this(name, false);

    }

    /**
     * Copy Constructor for the MTComputerPlayer class
     *
     * @param name
     */
    public MTComputerPlayer(String name, boolean difficulty){
        super(name);
        //set difficulty
        comDifficulty = difficulty;

    }
    /**
     *
     *
     * @param info
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        //If we don't have a game-state, ignore
        if(!(info instanceof DominoGameState)){
            return;
        }
        //update our variable
        mtState = (DominoGameState)info;
        int playerNum = this.playerNum;

        if(mtState.playerTurn == playerNum){
            //If it's my turn to play a domino,
            //delay for one and a half seconds; then play
            sleep(1500);

            if(!comDifficulty) {
                //simple implementation of computer player
                //check if this player's Hand still contains dominoes
                if(playerNum == 0){
                    if(mtState.Player1Hand.size() > 0){

                        /*
                        int i;
                        for(i=0; i < mtState.Player1Hand.size(); i++) {
                            if()
                            mtState.checkPlayable(playerNum, playerNum);
                            mtState.placeDomino(playerNum, mtState.Player1Hand.get(i), playerNum);
                        }
                        */

                    }
                    else{
                        //This player should theoretically have won the round.
                    }
                }
                else if(playerNum == 1){
                    if(mtState.Player2Hand.size() > 0){
                        //if dominoes
                    }
                }
                else if(playerNum == 2){
                    if(mtState.Player3Hand.size() > 0){
                        //if dominoes
                    }
                }
                else if(playerNum == 3){
                    if(mtState.Player4Hand.size() > 0){
                        //if dominoes
                    }
                }
            }
            else{
                //intelligent implementation of computer player, for now does nothing.

            }
        }
    }
}
