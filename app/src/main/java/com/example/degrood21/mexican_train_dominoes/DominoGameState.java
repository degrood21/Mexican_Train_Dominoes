package com.example.degrood21.mexican_train_dominoes;

import android.graphics.drawable.Drawable;
import android.widget.EditText;

import com.example.degrood21.mexican_train_dominoes.game.infoMsg.GameState;

import java.util.ArrayList;
import java.util.Random;

/**
 * DominoGameState describes and changes each GameState depending on player
 *
 * @authors Dylan DeGrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class DominoGameState extends GameState {

    ArrayList<Domino> PileofDominoes = new ArrayList<>();
    ArrayList<Domino> Player1Hand = new ArrayList<>();
    ArrayList<Domino> Player2Hand = new ArrayList<>();
    ArrayList<Domino> Player3Hand = new ArrayList<>();
    ArrayList<Domino> Player4Hand = new ArrayList<>();
    ArrayList<Domino> Player1Train = new ArrayList<>();
    ArrayList<Domino> Player2Train = new ArrayList<>();
    ArrayList<Domino> Player3Train = new ArrayList<>();
    ArrayList<Domino> Player4Train = new ArrayList<>();
    ArrayList<Domino> PublicTrain = new ArrayList<>();
    ArrayList<Domino> currentHand = new ArrayList<Domino>();
    ArrayList<Domino> currentTrain = new ArrayList<Domino>();
    int player1Score, player2Score, player3Score, player4Score;
    int playerTurn, round, doublePlayTrain, doublePlayDomino;
    boolean player1Public, player2Public, player3Public, player4Public, doublePlay;

    /**
     * Default cstor
     * Sets all instance variables above accordingly
     */
    public DominoGameState() {

        ArrayList<Domino> allDominoes = new ArrayList<Domino>();
        DominoPile pile = new DominoPile();
        allDominoes = pile.setOfDominoes;

        round = 12;
        //PileofDominoes = new ArrayList<>();
        for (int i = 0; i < allDominoes.size(); i++) {
            int pID = allDominoes.get(i).getPictureID();
            int rSide = allDominoes.get(i).getRightSide();
            int lSide = allDominoes.get(i).getLeftSide();
            Domino copy = new Domino(pID, rSide, lSide);
            PileofDominoes.add(i, copy);

        }
        for (int i = 0; i < allDominoes.size() - 1; i++) {
            if (PileofDominoes.get(i).leftSide == 12 && PileofDominoes.get(i).rightSide == 12) {
                allDominoes.get(i).leftSide = -1;
                PublicTrain.add(0, allDominoes.get(i));
                PileofDominoes.remove(i);
            }
        }

        dealAction();


        playerTurn = 0; // 0 means it is player 1's turn
        player1Score = 0;
        player2Score = 0;
        player3Score = 0;
        player4Score = 0;
        player1Public = false;
        player2Public = false;
        player3Public = false;
        player4Public = false;

    }

    /**
     * Deep Copy cstor
     * takes in a DominoGameState instance and deep copies all variables
     *
     * @param newstateInstance newstateInstance is the state being passed in to copy
     */
    public DominoGameState(DominoGameState newstateInstance) {

        PileofDominoes = new ArrayList<>();
        for (int i = 0; i < newstateInstance.PileofDominoes.size(); i++) {
            int pID = newstateInstance.PileofDominoes.get(i).getPictureID();
            int rSide = newstateInstance.PileofDominoes.get(i).getRightSide();
            int lSide = newstateInstance.PileofDominoes.get(i).getLeftSide();
            Domino copy = new Domino(pID, rSide, lSide);
            PileofDominoes.add(i, copy);

        }
        Player1Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player1Train.size(); i++) {

            int pID = newstateInstance.Player1Train.get(i).getPictureID();
            int rSide = newstateInstance.Player1Train.get(i).getRightSide();
            int lSide = newstateInstance.Player1Train.get(i).getLeftSide();
            Domino copy = new Domino(pID, rSide, lSide);
            Player1Train.add(i, copy);

        }
        Player2Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player2Train.size(); i++) {

            int pID = newstateInstance.Player2Train.get(i).getPictureID();
            int rSide = newstateInstance.Player2Train.get(i).getRightSide();
            int lSide = newstateInstance.Player2Train.get(i).getLeftSide();
            Domino copy = new Domino(pID, rSide, lSide);
            Player2Train.add(i, copy);

        }
        Player3Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player3Train.size(); i++) {

            int pID = newstateInstance.Player3Train.get(i).getPictureID();
            int rSide = newstateInstance.Player3Train.get(i).getRightSide();
            int lSide = newstateInstance.Player3Train.get(i).getLeftSide();
            Domino copy = new Domino(pID, rSide, lSide);
            Player3Train.add(i, copy);

        }
        Player4Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player4Train.size(); i++) {

            int pID = newstateInstance.Player4Train.get(i).getPictureID();
            int rSide = newstateInstance.Player4Train.get(i).getRightSide();
            int lSide = newstateInstance.Player4Train.get(i).getLeftSide();
            Domino copy = new Domino(pID, rSide, lSide);
            Player4Train.add(i, copy);

        }
        PublicTrain = new ArrayList<>();
        for (int i = 0; i < newstateInstance.PublicTrain.size(); i++) {

            int pID = newstateInstance.PublicTrain.get(i).getPictureID();
            int rSide = newstateInstance.PublicTrain.get(i).getRightSide();
            int lSide = newstateInstance.PublicTrain.get(i).getLeftSide();
            Domino copy = new Domino(pID, rSide, lSide);
            PublicTrain.add(i, copy);

        }
        player1Public = newstateInstance.player1Public;
        player2Public = newstateInstance.player2Public;
        player3Public = newstateInstance.player3Public;
        player4Public = newstateInstance.player4Public;

        player1Score = newstateInstance.player1Score;
        player2Score = newstateInstance.player2Score;
        player3Score = newstateInstance.player3Score;
        player4Score = newstateInstance.player4Score;

        round = newstateInstance.round;

        // Depending on which players com.example.degrood21.mexican_train_dominoes.game state instance is being sent in
        // it will deep copy accordingly
        if (newstateInstance.playerTurn == 0) { // player 1

            Player1Hand = new ArrayList<>();
            for (int i = 0; i < newstateInstance.Player1Hand.size(); i++) {

                Player1Hand.add(i, newstateInstance.Player1Hand.get(i));

            }
            playerTurn = newstateInstance.playerTurn;

        } else if (newstateInstance.playerTurn == 1) { // player 2

            Player2Hand = new ArrayList<>();
            for (int i = 0; i < newstateInstance.Player2Hand.size(); i++) {

                Player2Hand.add(i, newstateInstance.Player2Hand.get(i));

            }
            playerTurn = newstateInstance.playerTurn;

        } else if (newstateInstance.playerTurn == 2) { // player 3

            Player3Hand = new ArrayList<>();
            for (int i = 0; i < newstateInstance.Player3Hand.size(); i++) {

                Player3Hand.add(i, newstateInstance.Player3Hand.get(i));

            }
            playerTurn = newstateInstance.playerTurn;

        } else if (newstateInstance.playerTurn == 3) { // player 4

            Player4Hand = new ArrayList<>();
            for (int i = 0; i < newstateInstance.Player4Hand.size(); i++) {

                Player4Hand.add(i, newstateInstance.Player4Hand.get(i));

            }
            playerTurn = newstateInstance.playerTurn;

        }
    }

    /**
     * dealAction method
     * <p>
     * deals 15 dominoes to each player for start of com.example.degrood21.mexican_train_dominoes.game
     *
     * @return if dealt correctly return true
     */
    public boolean dealAction( /*usually is sent a player ID as param */) {

        for (int i = 0; i < 15; i++) {
            int dom = randomDomino();
            Player1Hand.add(PileofDominoes.get(dom));
            PileofDominoes.remove(dom);
        }
        for (int i = 0; i < 15; i++) {
            int dom = randomDomino();
            Player2Hand.add(PileofDominoes.get(dom));
            PileofDominoes.remove(dom);
        }
        for (int i = 0; i < 15; i++) {
            int dom = randomDomino();
            Player3Hand.add(PileofDominoes.get(dom));
            PileofDominoes.remove(dom);
        }
        for (int i = 0; i < 15; i++) {
            int dom = randomDomino();
            Player4Hand.add(PileofDominoes.get(dom));
            PileofDominoes.remove(dom);
        }
        return true;

    }

    /**
     * selectDomino method
     * <p>
     * selects the Domino to pass in placeDomino
     *
     * @param id             player id who's turn it currently is
     * @param domino         the selected domino wanting to be placed
     * @param trainSelection the train that is able to be played on
     * @return returns true if domino is correctly selected and placed
     */
    public boolean selectDomino(int id, Domino domino, int trainSelection) {
        if (id == 0) {
            if (Player1Hand != null) {
                if (doublePlay && trainSelection == doublePlayTrain && (domino.leftSide == doublePlayDomino || domino.rightSide == doublePlayDomino)) {
                    placeDomino(id, domino, trainSelection);//calls placed domino for a double
                }
                if (placeDomino(id, domino, trainSelection)) {
                    return true;

                }//calls place domino for a non double
                else return false;
            }
        } else if (id == 1) {
            if (Player2Hand != null) {
                if (doublePlay && trainSelection == doublePlayTrain && (domino.leftSide == doublePlayDomino || domino.rightSide == doublePlayDomino)) {
                    placeDomino(id, domino, trainSelection);//calls placed domino for a double
                }
                if (placeDomino(id, domino, trainSelection)) {
                    return true;
                }//calls place domino for a non double
                else return false;
            }
        } else if (id == 2) {
            if (Player3Hand != null) {
                if (doublePlay && trainSelection == doublePlayTrain && (domino.leftSide == doublePlayDomino || domino.rightSide == doublePlayDomino)) {
                    placeDomino(id, domino, trainSelection);//calls placed domino for a double
                }
                if (placeDomino(id, domino, trainSelection)) {
                    return true;
                }//calls place domino for a non double
                else return false;
            }
        } else if (id == 3) {
            if (Player4Hand != null) {
                if (doublePlay && trainSelection == doublePlayTrain && (domino.leftSide == doublePlayDomino || domino.rightSide == doublePlayDomino)) {
                    placeDomino(id, domino, trainSelection);//calls placed domino for a double
                }
                if (placeDomino(id, domino, trainSelection)) {
                    return true;
                }//calls place domino for a non double
                else return false;
            }
        }

        return false;
    }

    /**
     * placeDomino method
     * <p>
     * places the selected domino passed in correctly and accordingly
     *
     * @param playerID       player who's turn it currently is
     * @param selectedDomino the selected domino that is trying to be placed on train
     * @param trainSelection the train that the domino is trying to be placed on
     * @return returns true if the domino was succesfully placed on corresponding train
     */
    public boolean placeDomino(int playerID, Domino selectedDomino, int trainSelection) { //Dylan

        if (selectedDomino.leftSide == selectedDomino.rightSide) {
            if (doublePlay(playerID, selectedDomino, trainSelection)) {

                return true;

            } else {
                return false;
            }
        } else if (playerID == 0) { // If Player 1
            if (trainSelection == 0) { // Player 1 Train
                if (Player1Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        // does not need to return false in for loop since we check playable in humanPlayer
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (player2Public == true && trainSelection == 1) { // Player 2 Train
                if (Player2Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (player3Public == true && trainSelection == 2) { // Player 3 Train
                if (Player3Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (player4Public == true && trainSelection == 3) { // Player 4 Train
                if (Player4Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            }
        } else if (playerID == 1) { // If Player 2
            if (player1Public == true && trainSelection == 0) { // Player 1 Train
                if (Player1Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (trainSelection == 1) { // Player 2 Train
                if (Player2Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (player3Public == true && trainSelection == 2) { // Player 3 Train
                if (Player3Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (player4Public == true && trainSelection == 3) { // Player 4 Train
                if (Player4Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            }
        } else if (playerID == 2) { // If Player 3
            if (player1Public == true && trainSelection == 0) { // Player 1 Train
                if (Player1Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (player2Public == true && trainSelection == 1) { // Player 2 Train
                if (Player2Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (trainSelection == 2) { // Player 3 Train
                if (Player3Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (player4Public == true && trainSelection == 3) { // Player 4 Train
                if (Player4Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            }
        } else if (playerID == 3) { // If Player 4
            if (player1Public == true && trainSelection == 0) { // Player 1 Train
                if (Player1Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (player2Public == true && trainSelection == 1) { // Player 2 Train
                if (Player2Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (player3Public == true && trainSelection == 2) { // Player 3 Train
                if (Player3Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (trainSelection == 3) { // Player 4 Train
                if (Player4Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == selectedDomino.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * doublePlay method
     * <p>
     * if doublePlay is called due to double domino
     * the double domino will be placed accordingly and
     * variables doublePlay will turn true, doublePlayDomino
     * will be set to playable side, and doublePlayTrain will be set to
     * train that double was played on
     *
     * @param id             player who's turn it is currently is
     * @param playedDouble   double that is being played
     * @param trainSelection the train that double is being played on
     * @return returns true if double was placed succesfully
     */
    public boolean doublePlay(int id, Domino playedDouble, int trainSelection) {
        if (id == 0) { // If Player 1
            if (trainSelection == 0) { // Player 1 Train
                if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (player2Public == true && trainSelection == 1) { // Player 2 Train
                if (Player2Train.size() == 0) {
                    if (playedDouble.leftSide == round) {
                        playedDouble.leftSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (player3Public == true && trainSelection == 2) { // Player 3 Train
                if (Player3Train.size() == 0) {
                    if (playedDouble.leftSide == round) {
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (playedDouble.rightSide == round) {
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (player4Public == true && trainSelection == 3) { // Player 4 Train
                if (Player4Train.size() == 0) {
                    if (playedDouble.leftSide == round) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (playedDouble.rightSide == round) {
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player1Hand.size(); i++) {
                            if (Player1Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player1Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            }
        } else if (id == 1)

        { // If Player 2
            if (player1Public == true && trainSelection == 0) { // Player 1 Train
                if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (trainSelection == 1) { // Player 2 Train
                if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (player3Public == true && trainSelection == 2) { // Player 3 Train
                if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (player4Public == true && trainSelection == 3) { // Player 4 Train
                if (Player4Train.size() == 0) {
                    if (playedDouble.leftSide == round) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (playedDouble.rightSide == round) {
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player2Hand.size(); i++) {
                            if (Player2Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player2Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            }
        } else if (id == 2)

        { // If Player 3
            if (player1Public == true && trainSelection == 0) { // Player 1 Train
                if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (player2Public == true && trainSelection == 1) { // Player 2 Train
                if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (trainSelection == 2) { // Player 3 Train
                if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (player4Public == true && trainSelection == 3) { // Player 4 Train
                if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player3Hand.size(); i++) {
                            if (Player3Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player3Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            }
        } else if (id == 3)

        { // If Player 4
            if (player1Public == true && trainSelection == 0) { // Player 1 Train
                if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (player2Public == true && trainSelection == 1) { // Player 2 Train
                if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (player3Public == true && trainSelection == 2) { // Player 3 Train
                if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (trainSelection == 3) { // Player 4 Train
                if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).leftSide == playedDouble.rightSide) {
                        playedDouble.rightSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide == playedDouble.leftSide) {
                        playedDouble.leftSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;
                        for (int i = 0; i < Player4Hand.size(); i++) {
                            if (Player4Hand.get(i).pictureID == playedDouble.pictureID) {
                                Player4Hand.remove(i);
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
        drawAction takes a random index from PileofDominos and adds it to the current player's hand
     */
    public boolean drawAction(int id) {

        Random rand = new Random();

        if (PileofDominoes == null || PileofDominoes.size() == 0) {
            return false;
        } else {
            int randomIndex = rand.nextInt(PileofDominoes.size());
            if (id == 0) {
                Player1Hand.add(PileofDominoes.get(randomIndex));
                PileofDominoes.remove(randomIndex);
                player1Public = true;
                return true;
            } else if (id == 1) {
                Player2Hand.add(PileofDominoes.get(randomIndex));
                PileofDominoes.remove(randomIndex);
                player2Public = true;
                return true;
            } else if (id == 2) {
                Player3Hand.add(PileofDominoes.get(randomIndex));
                PileofDominoes.remove(randomIndex);
                player3Public = true;
                return true;
            } else if (id == 3) {
                Player4Hand.add(PileofDominoes.get(randomIndex));
                PileofDominoes.remove(randomIndex);
                player4Public = true;
                return true;
            }
            return false;
        }
    }

    /**
     * Only to be used for start of every turn and for draw action
     *
     * @param id
     * @param trainSelection
     * @return
     */
    public boolean checkPlayable(int id, int trainSelection) {
        if (trainSelection >= 5) {
            return false;
        }

        if (id == 0) {
            currentHand = Player1Hand;
        } else if (id == 1) {
            currentHand = Player2Hand;
        } else if (id == 2) {
            currentHand = Player3Hand;
        } else if (id == 3) {
            currentHand = Player4Hand;
        }

        if (trainSelection == 0) {
            currentTrain = Player1Train;
        } else if (trainSelection == 1) {
            currentTrain = Player2Train;
        } else if (trainSelection == 2) {
            currentTrain = Player3Train;
        } else if (trainSelection == 3) {
            currentTrain = Player4Train;
        } else if (trainSelection == 4) {
            currentTrain = PublicTrain;
        }


        for (int i = 0; i < currentHand.size(); i++) {

            if (currentTrain.size() == 0) {
                if(currentHand.get(i).rightSide == round || currentHand.get(i).leftSide == round){
                    return true;
                }
                else{
                    return false;
                }
            }
            if (currentHand.get(i).rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || currentHand.get(i).rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                return true;
            } else if (currentHand.get(i).leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || currentHand.get(i).leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                return true;
            }

        }
        return checkPlayable(id, trainSelection++);

    }

    public boolean playableTrains(int id, Domino selectedDomino, int trainSelection) {

        boolean returnCheck = false;

        if (trainSelection == 0) {
            currentTrain = Player1Train;
        } else if (trainSelection == 1) {
            currentTrain = Player2Train;
        } else if (trainSelection == 2) {
            currentTrain = Player3Train;
        } else if (trainSelection == 3) {
            currentTrain = Player4Train;
        } else if (trainSelection == 4) {
            currentTrain = PublicTrain;
        }


        if ((player1Public == true && trainSelection == 0) || (id == playerTurn && trainSelection == 0)) {
            if (currentTrain.size() == 0 && (selectedDomino.rightSide == round
                    || selectedDomino.leftSide == round)) {
                returnCheck = true;
            } else if (currentTrain.size() == 0) {
                returnCheck = false;
            } else if (selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;
            } else if (selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;

            }
        } else if ((player2Public == true && trainSelection == 1) || (id == playerTurn && trainSelection == 1)) {
            if (currentTrain.size() == 0 && (selectedDomino.rightSide == round
                    || selectedDomino.leftSide == round)) {
                returnCheck = true;
            } else if (currentTrain.size() == 0) {
                returnCheck = false;
            } else if (selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;
            } else if (selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;

            }
        } else if ((player3Public == true && trainSelection == 2) || (id == playerTurn && trainSelection == 2)) {
            if (currentTrain.size() == 0 && (selectedDomino.rightSide == round
                    || selectedDomino.leftSide == round)) {
                returnCheck = true;
            } else if (currentTrain.size() == 0) {
                returnCheck = false;
            } else if (selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;
            } else if (selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;

            }
        } else if ((player4Public == true && trainSelection == 3) || (id == playerTurn && trainSelection == 3)) {
            if (currentTrain.size() == 0 && (selectedDomino.rightSide == round
                    || selectedDomino.leftSide == round)) {
                returnCheck = true;
            } else if (currentTrain.size() == 0) {
                returnCheck = false;
            } else if (selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;
            } else if (selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;

            }
        }
        return returnCheck;
    }

    /**
     * randomDomino method
     * <p>
     * randomly creates an int depending on PileofDominoes size
     *
     * @return the int created above
     */
    public int randomDomino() { // Helps with giving all players 15 dominoes @ start

        Random ran = new Random();
        return ran.nextInt(PileofDominoes.size());

    }

    /**
     * toString method
     * <p>
     * creates string to be printed out with all variables discussed above
     *
     * @return the String created with variables
     */
    @Override
    public String toString() {
        String completePrint = "";
        completePrint = "Dominoes in Player 1 Train: \n";
        for (int i = 0; i < Player1Train.size(); i++) {
            completePrint += "Domino: "
                    + Player1Train.get(i).leftSide + "," + Player1Train.get(i).rightSide + "\n";
        }
        completePrint += "Dominoes in Player 2 Train: \n";
        for (int i = 0; i < Player2Train.size(); i++) {
            completePrint += "Domino: "
                    + Player2Train.get(i).leftSide + "," + Player2Train.get(i).rightSide + "\n";
        }
        completePrint += "Dominoes in Player 3 Train: \n";
        for (int i = 0; i < Player3Train.size(); i++) {
            completePrint += "Domino: "
                    + Player3Train.get(i).leftSide + "," + Player3Train.get(i).rightSide + "\n";
        }
        completePrint += "Dominoes in Player 4 Train: \n";
        for (int i = 0; i < Player4Train.size(); i++) {
            completePrint += "Domino: "
                    + Player4Train.get(i).leftSide + "," + Player4Train.get(i).rightSide + "\n";
        }
        completePrint += "Dominoes in Public Train: \n";
        for (int i = 0; i < PublicTrain.size(); i++) {
            completePrint += "Domino: "
                    + PublicTrain.get(i).leftSide + "," + PublicTrain.get(i).rightSide + "\n";
        }
        completePrint += "Dominoes left in Pile: \n";
        for (int i = 0; i < PileofDominoes.size(); i++) {
            completePrint += "Domino: "
                    + PileofDominoes.get(i).leftSide + "," + PileofDominoes.get(i).rightSide + "\n";
        }
        completePrint += "Dominoes in Player 1's Hand: \n";
        for (int i = 0; i < Player1Hand.size(); i++) {
            completePrint += "Domino: "
                    + Player1Hand.get(i).leftSide + "," + Player1Hand.get(i).rightSide + "\n";
        }
        completePrint += "Dominoes in Player 2's Hand: \n";
        for (int i = 0; i < Player2Hand.size(); i++) {
            completePrint += "Domino: "
                    + Player2Hand.get(i).leftSide + "," + Player2Hand.get(i).rightSide + "\n";
        }
        completePrint += "Dominoes in Player 3's Hand: \n";
        for (int i = 0; i < Player3Hand.size(); i++) {
            completePrint += "Domino: "
                    + Player3Hand.get(i).leftSide + "," + Player3Hand.get(i).rightSide + "\n";
        }
        completePrint += "Dominoes in Player 4's Hand: \n";
        for (int i = 0; i < Player4Hand.size(); i++) {
            completePrint += "Domino: "
                    + Player4Hand.get(i).leftSide + "," + Player4Hand.get(i).rightSide + "\n";
        }

        return "\nRound: " + round + "\nPlayer Turn: " + playerTurn + "\nPlayer 1 Score: " +
                player1Score + "\nPlayer 2 Score: " + player2Score + "\nPlayer 3 Score: " + player3Score +
                "\nPlayer 4 Score: " + player4Score + "\nIs Player 1's Train Public: " + player1Public +
                "\nIs Player 2's Train Public: " + player2Public + "\nIs Player 3's Train Public: " +
                player3Public + "\nIs Player 4's Train Public: " + player4Public + "\n" + completePrint + "\n";
    }

}
