package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * Draw action, initiates an action that allows everyone to draw
 *
 * @authors Dylan DeGrood, Callum Morham, Logan Crawford, Devin Smith
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
