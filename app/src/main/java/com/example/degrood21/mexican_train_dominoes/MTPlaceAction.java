package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * Place Action - allows the user to place dominoes on their selected ImageViews
 *
 * @author Dylan Degrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class MTPlaceAction extends GameAction {

    private int trainNumber;
    private int selectedDomino;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     * @param numOfTrain sends in the value of the train being played on
     */
    public MTPlaceAction(GamePlayer player, int numOfTrain, int selectedDomino) {
        super(player);
        trainNumber = numOfTrain;
        this.selectedDomino = selectedDomino;
    }

    public MTPlaceAction(GamePlayer player){
        super(player);
    }

    public int getTrainNumber(){ return trainNumber; }
    public void setTrainNumber(int trainNum){ trainNumber = trainNum; }

    public int getSelectedDomino(){ return selectedDomino; }
    public void setSelectedDomino(int selDom){ selectedDomino = selDom; }

}
