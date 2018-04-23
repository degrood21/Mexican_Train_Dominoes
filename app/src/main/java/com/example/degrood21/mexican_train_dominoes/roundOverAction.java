package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * Created by dylandegrood on 4/18/18.
 */

public class roundOverAction extends GameAction {
    private static final long serialVersionUID = -4182018123456439L;

    DominoGameState state;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public roundOverAction(GamePlayer player, DominoGameState initState) {
        super(player);
        state = initState;

    }

    public boolean checkRoundOver() {

        if (state.PileofDominoes.size() == 0) {
            if ((!state.checkPlayable(0, 0) && !state.checkPlayable(1, 0)
                    && !state.checkPlayable(2, 0) && !state.checkPlayable(3, 0))) {

                //countScores();

                int less1, less2;
                if (state.player1Score < state.player2Score) {
                    less1 = state.player1Score;
                } else {
                    less1 = state.player2Score;
                }

                if (state.player3Score < state.player4Score) {
                    less2 = state.player3Score;
                } else {
                    less2 = state.player4Score;
                }

                if (less1 < less2) {
                    if (less1 == state.player1Score) {
                        return true;
                    } else {
                        return true;
                    }
                } else {
                    if (less2 == state.player3Score) {
                        return true;
                    } else {
                        return true;
                    }
                }
            }

        } else if (state.round > 0) {
            //Check if any of the players hands have reached 0, meaning they have ran out of dominoes
            //in their hand and won the round.
            if (state.hand.get(0).size() == 0) {
                return true;
            } else if (state.hand.get(1).size() == 0) {
                return true;
            } else if (state.hand.get(2).size() == 0) {
                return true;
            } else if (state.hand.get(3).size() == 0) {
                return true;
            } else {
                //All players still have dominoes in their hands, the game goes on.
                return false;
            }
        }

        return false;

    }
}
