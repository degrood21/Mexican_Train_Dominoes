package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.LocalGame;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * Created by crawforl20 on 4/4/2018.
 */

public class MTLocalGame extends LocalGame {

    DominoGameState state;

    public MTLocalGame(){

        state = new DominoGameState();

    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

        DominoGameState gs = new DominoGameState(state);

    }

    @Override
    protected boolean canMove(int playerIdx) {
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
