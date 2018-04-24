package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.LocalGame;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;

/**
 * This is the LocalGame class for our Mexican Train Dominoes game. This class
 * defines and enforces the game rules, handles interactions between players of the game.
 *
 * @author Dylan Degrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class MTLocalGame extends LocalGame {
    //Instance Variables
    //Instance variable for the state of the game
    DominoGameState state;
    private int playerNum, trainNum, selectedDomino = 0;

    /**
     * Constructor for the MTLocalGame
     *
     * @param numberOfPlayers
     */
    public MTLocalGame(int numberOfPlayers) {
        state = new DominoGameState(numberOfPlayers, 12);
    }

    /**
     * checks whether the game is over, if so return a string with result.
     *
     * @return the end-of-game message, or null if game is not over.
     */
    @Override
    protected String checkIfGameOver() {
        //if it's the final round, then check player scores to see who won the game.
        if (state.round == 0) {
            //check if any of the players' hands have ran out of dominoes, ending the final round of the game
            //resulting in the end of the game.
            if (state.hand.get(0).size() == 0 || state.hand.get(1).size() == 0 || state.hand.get(2).size() == 0
                    || state.hand.get(3).size() == 0) {
                //if player one's score is higher than the others...
                if (state.player1Score < state.player2Score && state.player1Score < state.player3Score
                        && state.player1Score < state.player4Score) {
                    return this.playerNames[0] + " is the Winner!" + " With a Score of: " + state.player1Score; //then player one won the game.
                    //if player two's score is higher than the others...
                } else if (state.player2Score < state.player1Score && state.player2Score < state.player3Score
                        && state.player2Score < state.player4Score) {
                    return this.playerNames[1] + " is the Winner!" + " With a Score of: " + state.player2Score;//then player two won the game.
                    //if player three's score is higher than the others...
                } else if (state.player3Score < state.player1Score && state.player3Score < state.player2Score
                        && state.player3Score < state.player4Score) {
                    return this.playerNames[2] + " is the Winner!" + " With a Score of: " + state.player3Score;//then player three won the game.
                    //if player four's score is higher than the others...
                } else if (state.player4Score < state.player1Score && state.player4Score < state.player2Score
                        && state.player4Score < state.player3Score) {
                    return this.playerNames[3] + " is the Winner!" + " With a Score of: " + state.player4Score;//then player four won the game.
                }
            } else {
                //All players still have dominoes in their hands, the game goes on.
                return null;
            }
        }
        return null;
    }

    /**
     * Sends the updated state to the given player.
     *
     * @param p the player which is sent the updated state.
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //If there is no updated state, return
        if (state == null) return;

        //make a copy of the state and null out all dominoes that are irrelevant to the player.
        DominoGameState Current = new DominoGameState(state);//copy of state

        //send modified copy of state to the player.
        p.sendInfo(Current);
    }

    /**
     * Whether a player is allowed to move
     *
     * @param playerIdx the player's player-number (ID)
     * @return
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx < 0 || playerIdx > 3) {
            //if our player-number is out of range, return false.
            return false;
        } else if (playerIdx == state.playerTurn) {//sets the turn to the playerId
            return true;
        }
        return false;
    }

    /**
     * makes a move on behalf of a player
     *
     * @param action The move that the player has sent to the com.example.degrood21.mexican_train_dominoes.game
     * @return
     */
    @Override
    protected boolean makeMove(GameAction action) {

        //calls the action based on which action got called
        if (action instanceof MTPlaceAction) {
            return placeAction(action);
        } else if (action instanceof MTDrawAction) {
            return drawAction(action);
        } else if (action instanceof MTComputerPlayAction) {
            return computerPlayAction(action);
        } else if (action instanceof roundOverAction) {
            return roundOver(action);
        } else if (action instanceof MTSmartPlayAction) {
            return smartComputerPlayAction(action);
        } else {
            return false;//no action was called
        }

    }

    protected String checkIfRoundOver() {
        return null;
    }

    /**
     * Helps to determine whether to draw when in Double Play
     * <p>
     * Meaning that the Human player after playing a double, must play a matching domino or must draw if they cannot
     */
    public void doubleHelper() {

        if (playerNum == state.playerTurn) {//if it is the players turn
            if (state.placeDomino(playerNum, state.hand.get(playerNum).get(selectedDomino), state.doublePlayTrain)) {//tries to place domino
                state.doublePlay = false;//sets double play to false
                if(state.playerTurn == state.doublePlayTrain) {//checks if its your turn and you're playing on the double play train
                    state.playerPublic.set(playerNum, false);//if its your turn sets you train to private
                }
                state.playerTurn++;//increments the turn
                if (state.playerTurn > 3) {
                    state.playerTurn = 0;
                }

            } else {
                state.drawAction(playerNum);//if you can't play then you have to draw
                if (state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), state.doublePlayTrain)) {
                    state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), state.doublePlayTrain);
                    if(state.playerTurn == state.doublePlayTrain) {
                        state.playerPublic.set(playerNum, false);
                    }
                    state.playerTurn++;
                    if (state.playerTurn > 3) {
                        state.playerTurn = 0;
                    }
                } else {
                    state.playerPublic.set(playerNum, true);
                    state.playerTurn++;
                    if (state.playerTurn > 3) {
                        state.playerTurn = 0;
                    }
                }
            }
        }

    }

    /**
     * Place Action method is used for human players to place their domino on the train
     *
     * @param action takes in a gameAction
     * @return returns true if the domino was placed otherwise false
     */
    public boolean placeAction(GameAction action) {

        MTPlaceAction PA = (MTPlaceAction) action;

        if (state.playerTurn == this.getPlayerIdx(PA.getPlayer())) {
            selectedDomino = PA.getSelectedDomino();//gets selected domino
            trainNum = PA.getTrainNumber();//gets the train you're trying to place on
            playerNum = this.getPlayerIdx(PA.getPlayer());//gets the player turn
            if (state.doublePlay) {//checks for double play
                doubleHelper();//calls the double helper
            } else if (state.playableTrains(state.playerTurn, state.hand.get(playerNum).get(selectedDomino), playerNum) && trainNum == playerNum) {
                if (state.placeDomino(state.playerTurn, state.hand.get(playerNum).get(selectedDomino), playerNum)) {

                    state.playerPublic.set(playerNum, false);//sets their train to private if they played on it
                    state.playerTurn++;//increments the turn
                    if (state.playerTurn > 3) {
                        state.playerTurn = 0;
                    }
                }
            } else if (state.playableTrains(state.playerTurn, state.hand.get(playerNum).get(selectedDomino), trainNum)) {
                if (state.placeDomino(state.playerTurn, state.hand.get(playerNum).get(selectedDomino), trainNum)) {

                    state.playerTurn++;//increments the turn
                    if (state.playerTurn > 3) {
                        state.playerTurn = 0;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Draw Action method helps the users and computers draw when the draw button is pressed
     * and they're unable to play on their own
     *
     * @param action -takes in a Game Action
     * @return boolean of true or false
     */
    public boolean drawAction(GameAction action) {

        MTDrawAction DA = (MTDrawAction) action;

        if (state.playerTurn == this.getPlayerIdx(DA.getPlayer())) {//checks if it's their turn
            playerNum = this.getPlayerIdx(DA.getPlayer());//sets player num equal to player turn
            if (state.PileofDominoes.size() == 0 && !state.checkPlayable(playerNum, 0)) {
                state.playerPublic.set(playerNum, true);
                state.playerTurn++;//increments the players turn
                if(state.playerTurn > 3){
                    state.playerTurn = 0;
                }
            } else if (!state.checkPlayable(playerNum, 0) && state.hand.get(playerNum).size() <= 20) {
                state.drawAction(playerNum);
                //if its double play you can obly play that new domino on the doubleplaytrain
                if (state.doublePlay) {
                    if (state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), state.doublePlayTrain)) {
                        //place domino
                        state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), state.doublePlayTrain);
                        state.doublePlay = false;
                    }
                    state.playerTurn++;
                    if (state.playerTurn > 3) {
                        state.playerTurn = 0;
                    }
                }
                //if you can now play any domino on any public train play it
                else if (state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 0)
                        || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 1)
                        || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 2)
                        || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 3)
                        || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 4)) {
                    //trys to play on all trains with your new domino
                    if (state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), playerNum)) {
                        state.playerPublic.set(playerNum, false);
                        state.playerTurn++;
                        if (state.playerTurn > 3) {
                            state.playerTurn = 0;
                        }
                        //sets your train to false since you played on your own train
                    } else {
                        state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 0);
                        state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 1);
                        state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 2);
                        state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 3);
                        state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 4);
                        //sets your train to false since you played
                        state.playerPublic.set(playerNum, true);
                        state.playerTurn++;
                        if (state.playerTurn > 3) {
                            state.playerTurn = 0;
                        }
                    }
                } else {
                    state.playerPublic.set(playerNum, true);
                    state.playerTurn++;
                    if (state.playerTurn > 3) {
                        state.playerTurn = 0;
                    }
                }
            }
            if (state.hand.get(playerNum).size() == 20 && players[playerNum].requiresGui()) {
                state.playerTurn++;
                if (state.playerTurn > 3) {
                    state.playerTurn = 0;
                }
            }
            return true;
        }
        return false;
    }

    public boolean computerPlayAction(GameAction action) {

        MTComputerPlayAction CPA = (MTComputerPlayAction) action;

        if (state.playerTurn == this.getPlayerIdx(CPA.getPlayer())) {
            playerNum = this.getPlayerIdx(CPA.getPlayer());
            if (state.hand.get(playerNum).size() > 0) {//if you have a domino in hand
                for (int i = 0; i < state.hand.get(playerNum).size(); i++) {//loops through hand
                    if (state.playableTrains(playerNum, state.hand.get(playerNum).get(i), 0)//looks to see if you can play on any train
                            || state.playableTrains(playerNum, state.hand.get(playerNum).get(i), 1)
                            || state.playableTrains(playerNum, state.hand.get(playerNum).get(i), 2)
                            || state.playableTrains(playerNum, state.hand.get(playerNum).get(i), 3)
                            || state.playableTrains(playerNum, state.hand.get(playerNum).get(i), 4)) {

                        if (state.doublePlay) {//checks if doubleplay is active
                            if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), state.doublePlayTrain)) {
                                state.doublePlay = false;//sets doubleplay to false
                                state.playerTurn++;//increment turn
                            } else {
                                state.playerPublic.set(playerNum, true);
                                //state.playerTurn++;
                            }
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), playerNum)) {//places on own train
                            state.playerPublic.set(playerNum, false);
                            state.playerTurn++;
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), 0)) {//places on train 0
                            state.playerTurn++;
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), 1)) {//places on train 1
                            state.playerTurn++;
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), 2)) {//places on train 2
                            state.playerTurn++;
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), 3)) {//places on train 3
                            state.playerTurn++;
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), 4)) {//places on public train
                            state.playerTurn++;
                        }

                        if (state.playerTurn > 3) {//checks turn value for next player
                            state.playerTurn = 0;
                        }
                        break;
                    }

                }
                if (state.playerTurn == playerNum) {//if its still your turn then you couldnt play
                    if (state.drawAction(playerNum)) {//draws
                        if (state.doublePlay) {
                            if (state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), state.doublePlayTrain)) {
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), state.doublePlayTrain);
                                state.doublePlay = false;
                            }
                            state.playerTurn++;
                            if (state.playerTurn > 3) {//check this
                                state.playerTurn = 0;
                            }
                        }
                        //if you can now play any domino on any public train play it
                        else if (state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 0)
                                || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 1)
                                || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 2)
                                || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 3)
                                || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 4)) {
                            //trys to play on all trains with your ned domino
                            if (state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), playerNum)) {
                                state.playerPublic.set(playerNum, false);
                                state.playerTurn++;
                                if (state.playerTurn > 3) {
                                    state.playerTurn = 0;
                                }
                                //sets your train to false since you played on your own train
                            } else {
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 0);
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 1);
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 2);
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 3);
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 4);
                                //sets your train to false since you played
                                state.playerPublic.set(playerNum, true);
                                state.playerTurn++;
                                if (state.playerTurn > 3) {
                                    state.playerTurn = 0;
                                }
                            }
                        } else if (playerNum == state.playerTurn) {
                            state.playerPublic.set(playerNum, true);
                            state.playerTurn++;
                            if (state.playerTurn > 3) {
                                state.playerTurn = 0;
                            }
                        }
                    } else {//if you can't draw at all
                        state.playerPublic.set(playerNum, true);
                        state.playerTurn++;
                        if (state.playerTurn > 3) {
                            state.playerTurn = 0;
                        }
                    }
                }
                return true;
            }

        }
        return false;
    }

    public boolean smartComputerPlayAction(GameAction action) {

        MTSmartPlayAction SCPA = (MTSmartPlayAction) action;

        if (state.playerTurn == this.getPlayerIdx(SCPA.getPlayer())) {
            playerNum = this.getPlayerIdx(SCPA.getPlayer());
            if (state.hand.get(playerNum).size() > 0) {//if you have a domino in hand
                for (int i = 0; i < state.hand.get(playerNum).size(); i++) {
                    if (state.playerPublic.get(playerNum)) {//if your train is public
                        if (state.playableTrains(playerNum, state.hand.get(playerNum).get(i), playerNum)) {
                            //if you can play on your own train
                            if (state.doublePlay) {
                                if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), state.doublePlayTrain)) {
                                    state.doublePlay = false;
                                    state.playerTurn++;
                                } else {
                                    state.playerPublic.set(playerNum, true);
                                }
                            } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), playerNum)) {
                                state.playerPublic.set(playerNum, false);
                                state.playerTurn++;//plays on own train
                            }
                        }
                    }
                }
                for (int i = 0; i < state.hand.get(playerNum).size(); i++) {//loops through hand
                    if (state.playableTrains(playerNum, state.hand.get(playerNum).get(i), 0)//looks to see if you can play on any train
                            || state.playableTrains(playerNum, state.hand.get(playerNum).get(i), 1)
                            || state.playableTrains(playerNum, state.hand.get(playerNum).get(i), 2)
                            || state.playableTrains(playerNum, state.hand.get(playerNum).get(i), 3)
                            || state.playableTrains(playerNum, state.hand.get(playerNum).get(i), 4)) {

                        if (state.doublePlay) {//checks if doubleplay is active
                            if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), state.doublePlayTrain)) {
                                state.doublePlay = false;//sets doubleplay to false
                                state.playerTurn++;//increment turn
                            } else {
                                state.playerPublic.set(playerNum, true);
                                //state.playerTurn++;
                            }
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), playerNum)) {//places on own train
                            state.playerPublic.set(playerNum, false);
                            state.playerTurn++;
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), 0)) {//places on train 0
                            state.playerTurn++;
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), 1)) {//places on train 1
                            state.playerTurn++;
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), 2)) {//places on train 2
                            state.playerTurn++;
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), 3)) {//places on train 3
                            state.playerTurn++;
                        } else if (state.placeDomino(playerNum, state.hand.get(playerNum).get(i), 4)) {//places on public train
                            state.playerTurn++;
                        }

                        if (state.playerTurn > 3) {//checks turn value for next player
                            state.playerTurn = 0;
                        }
                        //game.sendAction(new MTSelectAction(this));
                        break;
                    }

                }
                if (state.playerTurn == playerNum) {//if its still your turn then you couldnt play
                    if (state.drawAction(playerNum)) {//draws
                        if (state.doublePlay) {
                            if (state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), state.doublePlayTrain)) {
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), state.doublePlayTrain);
                                state.doublePlay = false;
                            }
                            state.playerTurn++;
                            if (state.playerTurn > 3) {//check this
                                state.playerTurn = 0;
                            }
                        }
                        //if you can now play any domino on any public train play it
                        else if (state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 0)
                                || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 1)
                                || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 2)
                                || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 3)
                                || state.playableTrains(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 4)) {
                            //trys to play on all trains with your ned domino
                            if (state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), playerNum)) {
                                state.playerPublic.set(playerNum, false);
                                state.playerTurn++;
                                if (state.playerTurn > 3) {
                                    state.playerTurn = 0;
                                }
                                //sets your train to false since you played on your own train
                            } else {
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 0);
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 1);
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 2);
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 3);
                                state.placeDomino(playerNum, state.hand.get(playerNum).get(state.hand.get(playerNum).size() - 1), 4);
                                //sets your train to false since you played
                                state.playerPublic.set(playerNum, true);
                                state.playerTurn++;
                                if (state.playerTurn > 3) {
                                    state.playerTurn = 0;
                                }
                            }
                        } else if (playerNum == state.playerTurn) {
                            state.playerPublic.set(playerNum, true);
                            state.playerTurn++;
                            if (state.playerTurn > 3) {
                                state.playerTurn = 0;
                            }
                        }
                    } else {//if you can't draw at all
                        state.playerPublic.set(playerNum, true);
                        state.playerTurn++;
                        if (state.playerTurn > 3) {
                            state.playerTurn = 0;
                        }
                    }
                    //game.sendAction(new MTDrawAction(this));
                }
                return true;
            }

        }
        return false;

    }

    public boolean roundOver(GameAction action) {

        roundOverAction rdOver = (roundOverAction) action;
        if (rdOver.checkRoundOver()) {

            if (state.PileofDominoes.size() == 0) {
                if ((!state.checkPlayable(0, 0) && !state.checkPlayable(1, 0)
                        && !state.checkPlayable(2, 0) && !state.checkPlayable(3, 0))) {

                    countScores();

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
                            state.round--;
                            //state.roundOver = true;
                            //myText.setText("Player 1" + " won the Round." + " Score: " + state.player1Score);
                        } else {
                            state.round--;
                            //state.roundOver = true;
                            //myText.setText("Player 2" + " won the Round." + " Score: " + state.player2Score);
                        }
                    } else {
                        if (less2 == state.player3Score) {
                            state.round--;
                            //state.roundOver = true;
                            //myText.setText("Player 3" + " won the Round." + " Score: " + state.player3Score);
                        } else {
                            state.round--;
                            //state.roundOver = true;
                            //myText.setText("Player 4" + " won the Round." + " Score: " + state.player4Score);
                        }
                    }
                }

            } else if (state.round > 0) {
                //Check if any of the players hands have reached 0, meaning they have ran out of dominoes
                //in their hand and won the round.
                if (state.hand.get(0).size() == 0) {
                    countScores();
                    state.round--;
                    //state.roundOver = true;
                    //myText.setText("Player 1" + " won the Round." + " Score: " + state.player1Score); //player one ran out of dominoes and won the round.
                } else if (state.hand.get(1).size() == 0) {
                    countScores();
                    state.round--;
                    //state.roundOver = true;
                    //myText.setText("Player 2" + " won the Round." + " Score: " + state.player2Score);//player two ran out of dominoes and won the round.
                } else if (state.hand.get(2).size() == 0) {
                    countScores();
                    state.round--;
                    //state.roundOver = true;
                    //myText.setText("Player 3" + " won the Round." + " Score: " + state.player3Score);//player three ran out of dominoes and won the round.
                } else if (state.hand.get(3).size() == 0) {
                    countScores();
                    state.round--;
                    //state.roundOver = true;
                    //myText.setText("Player 4" + " won the Round." + " Score: " + state.player4Score); //player four ran out of dominoes and won the round.
                } else {
                    //All players still have dominoes in their hands, the game goes on.
                }
            }

            DominoGameState newRound = new DominoGameState(4, state.round);
            newRound.player1Score = state.player1Score;
            newRound.player2Score = state.player2Score;
            newRound.player3Score = state.player3Score;
            newRound.player4Score = state.player4Score;
            this.state = newRound;

            return true;

            //sendInfo(this.state);

        }

        return false;


    }

    public void countScores() {

        int newPlayer1Score = 0,newPlayer2Score = 0,newPlayer3Score = 0,newPlayer4Score = 0;
        for (int i = 0; i < state.hand.get(0).size(); i++) {
            newPlayer1Score += state.hand.get(0).get(i).rightSide + state.hand.get(0).get(i).leftSide;
        }
        for (int i = 0; i < state.hand.get(1).size(); i++) {
            newPlayer2Score += state.hand.get(1).get(i).rightSide + state.hand.get(1).get(i).leftSide;
        }
        for (int i = 0; i < state.hand.get(2).size(); i++) {
            newPlayer3Score += state.hand.get(2).get(i).rightSide + state.hand.get(2).get(i).leftSide;
        }
        for (int i = 0; i < state.hand.get(3).size(); i++) {
            newPlayer4Score += state.hand.get(3).get(i).rightSide + state.hand.get(3).get(i).leftSide;
        }
        newPlayer1Score = newPlayer1Score/2;
        newPlayer2Score = newPlayer2Score/2;
        newPlayer3Score = newPlayer3Score/2;
        newPlayer4Score = newPlayer4Score/2;

        state.player1Score += newPlayer1Score;
        state.player2Score += newPlayer2Score;
        state.player3Score += newPlayer3Score;
        state.player4Score += newPlayer4Score;

    }

}
