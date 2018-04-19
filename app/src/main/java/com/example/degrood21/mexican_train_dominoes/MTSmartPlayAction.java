package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * Created by dylandegrood on 4/19/18.
 */

public class MTSmartPlayAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MTSmartPlayAction(GamePlayer player) {
        super(player);
    }
}
