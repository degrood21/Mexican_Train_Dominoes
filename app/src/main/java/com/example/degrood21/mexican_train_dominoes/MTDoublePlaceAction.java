package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * DoublePlaceAction occurs when a player plays a double domino(both sides contain equal number of pips)
 * Then the player is allowed to place another domino that as one end match that of the double.
 *
 * @author Dylan Degrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class MTDoublePlaceAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MTDoublePlaceAction(GamePlayer player) {
        super(player);
    }
}
