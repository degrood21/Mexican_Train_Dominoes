package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * round over action, checks if the round ends through different ways
 * - A player gets to 0 dominoes in their hand
 * - nobody is able to play on any trains
 *
 *
 * @authors Dylan DeGrood, Callum Morham, Logan Crawford, Devin Smith
 */

public class roundOverAction extends GameAction {
    private static final long serialVersionUID = -4182018123456439L;

    DominoGameState state;//sets the state

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public roundOverAction(GamePlayer player, DominoGameState initState) {
        super(player);
        state = initState;
    }
    public boolean checkRoundOver() {//Check's if the round is over, returns true if it is. False otherwise.

        if (state.PileofDominoes.size() == 0//if the pile is 0
                && state.hand.get(0).size() != 0//and player 1's hand is not 0
                && state.hand.get(1).size() != 0//and player 2's hand is not 0
                && state.hand.get(2).size() != 0//and player 3's hand is not 0
                && state.hand.get(3).size() != 0) {//and player 4's hand is not 0..
            //then if none of the players can play, then the round is over because no one can play and no one can draw any more dominoes.
            if ((!state.checkPlayable(0, 0) && !state.checkPlayable(1, 0)
                    && !state.checkPlayable(2, 0) && !state.checkPlayable(3, 0))) {

                //used to compare the scores and detect whose is the highest then return them as the winner
                int less1, less2;
                if (state.player1Score < state.player2Score) {
                    less1 = state.player1Score;//player 1 is less than player 2
                } else {
                    less1 = state.player2Score;//player 2 is less than player 1
                }

                if (state.player3Score < state.player4Score) {
                    less2 = state.player3Score;//player 3 is less than player 4
                } else {
                    less2 = state.player4Score;//player 4 is less than player 3
                }

                if (less1 < less2) {//compares the higher of the first two rounds
                    if (less1 == state.player1Score) {//sets the winner
                        return true;
                    } else {
                        return true;
                    }
                } else {
                    if (less2 == state.player3Score) {//sets the winner
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
