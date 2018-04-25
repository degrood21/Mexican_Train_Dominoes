package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * Smart computer play action, initiates an action that will allow the smart computer to play
 * it will be better than the regular computer as it will prioritize playing on its own train
 * if it is public to restrict others from being allowed to play on it.
 *
 * @author Dylan Degrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class MTSmartPlayAction extends GameAction {
    /**
     * constructor for MTSmartPlayAction
     *
     * @param player the player who created the action
     */
    public MTSmartPlayAction(GamePlayer player) {
        super(player);
    }
}
