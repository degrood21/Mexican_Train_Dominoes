package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * SelectAction selects a domino for the user to do some other action with.
 *
 * @author Dylan Degrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class MTSelectAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MTSelectAction(GamePlayer player) {
        super(player);
    }
}