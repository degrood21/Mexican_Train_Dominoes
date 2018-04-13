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

    /**
     * Constructor for the MTLocalGame
     *
     * @param numberOfPlayers
     */
    public MTLocalGame(int numberOfPlayers) {

        state = new DominoGameState(numberOfPlayers);

    }

    /**
     * checks whether the game is over, if so return a string with result.
     *
     * @return the end-of-game message, or null if game is not over.
     */
    @Override
    protected String checkIfGameOver() {
        //If the round is not the last, then decide who won the round and reset.
        if (state.PileofDominoes.size() == 0) {
            if (/*(!state.checkPlayable(0, 0) && !state.checkPlayable(1, 0)
                    && !state.checkPlayable(2, 0) && !state.checkPlayable(3, 0))
                    || */(state.doublePlay && state.player1Public && state.player2Public && state.player3Public && state.player4Public) || state.PileofDominoes.size() == 0) {
                for (int i = 0; i < state.Player1Hand.size(); i++) {
                    state.player1Score += state.Player1Hand.get(i).rightSide + state.Player1Hand.get(i).leftSide;
                }
                for (int i = 0; i < state.Player2Hand.size(); i++) {
                    state.player2Score += state.Player2Hand.get(i).rightSide + state.Player2Hand.get(i).leftSide;
                }
                for (int i = 0; i < state.Player3Hand.size(); i++) {
                    state.player3Score += state.Player3Hand.get(i).rightSide + state.Player3Hand.get(i).leftSide;
                }
                for (int i = 0; i < state.Player4Hand.size(); i++) {
                    state.player4Score += state.Player4Hand.get(i).rightSide + state.Player4Hand.get(i).leftSide;
                }

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
                        return "Player 1" + " won the Round." + " Score: " + state.player1Score;
                    } else {
                        return "Player 2" + " won the Round." + " Score: " + state.player2Score;
                    }
                } else {
                    if (less2 == state.player3Score) {
                        return "Player 3" + " won the Round." + " Score: " + state.player3Score;
                    } else {
                        return "Player 4" + " won the Round." + " Score: " + state.player4Score;
                    }
                }
            }

        }
        if (state.round > 0) {
            //Check if any of the players hands have reached 0, meaning they have ran out of dominoes
            //in their hand and won the round.
            if (state.Player1Hand.size() == 0) {
                return "Player 1" + " won the Round." + " Score: " + state.player1Score; //player one ran out of dominoes and won the round.
            } else if (state.Player2Hand.size() == 0) {
                return "Player 2" + " won the Round." + " Score: " + state.player2Score;//player two ran out of dominoes and won the round.
            } else if (state.Player3Hand.size() == 0) {
                return "Player 3" + " won the Round." + " Score: " + state.player3Score;//player three ran out of dominoes and won the round.
            } else if (state.Player4Hand.size() == 0) {
                return "Player 4" + " won the Round." + " Score: " + state.player4Score; //player four ran out of dominoes and won the round.
            } else {
                //All players still have dominoes in their hands, the game goes on.
                return null;
            }
        }
        //if it's the final round, then check player scores to see who won the game.
        if (state.round == 0) {
            //check if any of the players' hands have ran out of dominoes, ending the final round of the game
            //resulting in the end of the game.
            if (state.Player1Hand.size() == 0 || state.Player2Hand.size() == 0 || state.Player3Hand.size() == 0
                    || state.Player4Hand.size() == 0) {
                //if player one's score is higher than the others...
                if (state.player1Score > state.player2Score && state.player1Score > state.player3Score
                        && state.player1Score > state.player4Score) {
                    return this.playerNames[0] + " is the Winner!"; //then player one won the game.
                    //if player two's score is higher than the others...
                } else if (state.player2Score > state.player1Score && state.player2Score > state.player3Score
                        && state.player2Score > state.player4Score) {
                    return this.playerNames[1] + " is the Winner!";//then player two won the game.
                    //if player three's score is higher than the others...
                } else if (state.player3Score > state.player1Score && state.player3Score > state.player2Score
                        && state.player3Score > state.player4Score) {
                    return this.playerNames[2] + " is the Winner!";//then player three won the game.
                    //if player four's score is higher than the others...
                } else if (state.player4Score > state.player1Score && state.player4Score > state.player2Score
                        && state.player4Score > state.player3Score) {
                    return this.playerNames[3] + " is the Winner!";//then player four won the game.
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
        p.sendInfo(state);
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
        } else if (playerIdx == state.playerTurn) {
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
        if (checkIfGameOver() == null) {
            return false;
        }
        return true;
    }

    public String checkIfRoundIsOver() {

        if (state.PileofDominoes.size() == 0) {
            if (/*(!state.checkPlayable(0, 0) && !state.checkPlayable(1, 0)
                    && !state.checkPlayable(2, 0) && !state.checkPlayable(3, 0))
                    || */(state.doublePlay && state.player1Public && state.player2Public && state.player3Public && state.player4Public) || state.PileofDominoes.size() == 0) {
                for (int i = 0; i < state.Player1Hand.size(); i++) {
                    state.player1Score += state.Player1Hand.get(i).rightSide + state.Player1Hand.get(i).leftSide;
                }
                for (int i = 0; i < state.Player2Hand.size(); i++) {
                    state.player2Score += state.Player2Hand.get(i).rightSide + state.Player2Hand.get(i).leftSide;
                }
                for (int i = 0; i < state.Player3Hand.size(); i++) {
                    state.player3Score += state.Player3Hand.get(i).rightSide + state.Player3Hand.get(i).leftSide;
                }
                for (int i = 0; i < state.Player4Hand.size(); i++) {
                    state.player4Score += state.Player4Hand.get(i).rightSide + state.Player4Hand.get(i).leftSide;
                }

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
                        return "Player 1" + " won the Round." + " Score: " + state.player1Score;
                    } else {
                        return "Player 2" + " won the Round." + " Score: " + state.player2Score;
                    }
                } else {
                    if (less2 == state.player3Score) {
                        return "Player 3" + " won the Round." + " Score: " + state.player3Score;
                    } else {
                        return "Player 4" + " won the Round." + " Score: " + state.player4Score;
                    }
                }
            }

        } else if (state.round > 0) {
            //Check if any of the players hands have reached 0, meaning they have ran out of dominoes
            //in their hand and won the round.
            if (state.Player1Hand.size() == 0) {
                return "Player 1" + " won the Round." + " Score: " + state.player1Score; //player one ran out of dominoes and won the round.
            } else if (state.Player2Hand.size() == 0) {
                return "Player 2" + " won the Round." + " Score: " + state.player2Score;//player two ran out of dominoes and won the round.
            } else if (state.Player3Hand.size() == 0) {
                return "Player 3" + " won the Round." + " Score: " + state.player3Score;//player three ran out of dominoes and won the round.
            } else if (state.Player4Hand.size() == 0) {
                return "Player 4" + " won the Round." + " Score: " + state.player4Score; //player four ran out of dominoes and won the round.
            } else {
                //All players still have dominoes in their hands, the game goes on.
                return null;
            }
        }
        return null;
    }
}
