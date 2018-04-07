package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.LocalGame;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * Created by crawforl20 on 4/4/2018.
 */

public class MTLocalGame extends LocalGame {

    DominoGameState state = new DominoGameState();

    public MTLocalGame(){

        state = new DominoGameState();

    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

        DominoGameState Current = new DominoGameState(state);
        p.sendInfo(state);

    }

    @Override
    protected boolean canMove(int playerIdx) {

        if(playerIdx == state.playerTurn){

            return true;

        }

        return false;

    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {




        return false;
    }
}
