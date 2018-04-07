package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * CheckPlayableAction is used at the start of each turn, to check whether the player is permitted
 * to play, is it their turn?, and which train is designated as their own.
 *
 * @author Dylan Degrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class MTCheckPlayableAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MTCheckPlayableAction(GamePlayer player) {
        super(player);
    }
}
