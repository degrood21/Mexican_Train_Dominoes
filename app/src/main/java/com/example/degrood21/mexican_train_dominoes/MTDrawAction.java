package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * DrawAction takes a random index from the drawPile, which is a random domino, and gives it to
 * the designated player. This is because, dominoes in the drawPile are face-down, so the player
 * should not have any clue as to which domino they are drawing. Hence the randomization.
 *
 * @author Dylan Degrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class MTDrawAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MTDrawAction(GamePlayer player) {
        super(player);
    }
}
