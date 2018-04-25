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

        if (!comDifficulty) {//Simple AI
            //simple implementation of computer player
            //check if this player's Hand still contains dominoes and if this player is player one
            if (mtState.playerTurn == playerNum) {//if its your turn
                sleep(100);//delay for one second(1000), then play
                game.sendAction(new MTComputerPlayAction(this));
                sleep(100);
            }
        } else {
            //Smart AI
            if (mtState.playerTurn == playerNum) {//if its your turn
                sleep(100);//delay for one second(1000), then play
                game.sendAction(new MTSmartPlayAction(this));
                sleep(100);
            }

        }
    }
}//end of MTComputerPlayer class
