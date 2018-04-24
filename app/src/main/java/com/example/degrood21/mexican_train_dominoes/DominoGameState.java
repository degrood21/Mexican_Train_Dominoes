package com.example.degrood21.mexican_train_dominoes;

import com.example.degrood21.mexican_train_dominoes.game.infoMsg.GameState;

import java.util.ArrayList;
import java.util.Random;

/**
 * DominoGameState describes and changes each GameState depending on player
 *
 * @authors Dylan DeGrood, Devin Smith, Callum Morham, Logan Crawford
 */

public class DominoGameState extends GameState {
    private static final long serialVersionUID = -41820181234569681L;

    //Initiates all ArrayLists that correspond to hands, trains, and pile
    ArrayList<ArrayList<Domino>> hand = new ArrayList<>();
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
    ArrayList<Domino> currentHand = new ArrayList<Domino>(); //helper ArrayList for checkPlayable method
    ArrayList<Domino> currentTrain = new ArrayList<Domino>(); //helper ArrayList for checkPlayable method
    private int numPlayers; //contains number of players for game
    int player1Score, player2Score, player3Score, player4Score;
    int playerTurn, round, doublePlayTrain, doublePlayDomino;
    boolean roundOver;
    boolean doublePlay;
    Boolean player1Public, player2Public, player3Public, player4Public;
    ArrayList<Boolean> playerPublic = new ArrayList<>();

    /**
     * Default cstor
     * Sets all instance variables above accordingly
     */
    public DominoGameState(int numberOfPlayers, int currentRound) {
        numPlayers = numberOfPlayers;//used to determine how many players are playing
        ArrayList<Domino> allDominoes = new ArrayList<Domino>();
        DominoPile pile = new DominoPile();
        allDominoes = pile.setOfDominoes;
        PileofDominoes = new ArrayList<>();

        round = currentRound;
        for (int i = 0; i < allDominoes.size(); i++) {
            int pID = allDominoes.get(i).getPictureID();
            int rSide = allDominoes.get(i).getRightSide();
            int lSide = allDominoes.get(i).getLeftSide();
            Domino copy = new Domino(pID, rSide, lSide);
            PileofDominoes.add(i, copy);

        }

        PublicTrain = new ArrayList<>();

        for (int i = 0; i < allDominoes.size() - 1; i++) {
            if (PileofDominoes.get(i).leftSide == round && PileofDominoes.get(i).rightSide == round) {
                allDominoes.get(i).leftSide = -1;
                PublicTrain.add(0, allDominoes.get(i));
                PileofDominoes.remove(i);
            }
        }

        Player1Train = new ArrayList<>();
        Player2Train = new ArrayList<>();
        Player3Train = new ArrayList<>();
        Player4Train = new ArrayList<>();

        Player1Hand = new ArrayList<>();
        Player2Hand = new ArrayList<>();
        Player3Hand = new ArrayList<>();
        Player4Hand = new ArrayList<>();

        //add each individual hand into the arraylist of hand.
        hand.add(Player1Hand);
        hand.add(Player2Hand);
        hand.add(Player3Hand);
        hand.add(Player4Hand);

        //shuffle and deal out dominoes into respectable hands.
        dealAction();

        roundOver = false;

        playerTurn = 0; // 0 means it is player 1's turn

        if (round == 12) {
            player1Score = 0;
            player2Score = 0;
            player3Score = 0;
            player4Score = 0;
        }
        player1Public = false;
        player2Public = false;
        player3Public = false;
        player4Public = false;
        //add the boolean of each player's train to the arraylist of playerPublic
        playerPublic.add(player1Public);
        playerPublic.add(player2Public);
        playerPublic.add(player3Public);
        playerPublic.add(player4Public);

    }

    /**
     * Deep Copy cstor
     * takes in a DominoGameState instance and deep copies all variables
     *
     * @param newstateInstance newstateInstance is the state being passed in to copy
     */
    public DominoGameState(DominoGameState newstateInstance) {//for Deep Copy, saves for later

        //set the number of players in the game
        numPlayers = newstateInstance.numPlayers;
        PileofDominoes = new ArrayList<>();
        for (int i = 0; i < newstateInstance.PileofDominoes.size(); i++) {
            int pID = newstateInstance.PileofDominoes.get(i).getPictureID();//gets the picture id of the domino
            int rSide = newstateInstance.PileofDominoes.get(i).getRightSide();//gets the right side of the domino
            int lSide = newstateInstance.PileofDominoes.get(i).getLeftSide();//gets the left side of the domino
            Domino copy = new Domino(pID, rSide, lSide);
            PileofDominoes.add(i, copy);//adds dominoes to the pile

        }
        Player1Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player1Train.size(); i++) {
            int pID = newstateInstance.Player1Train.get(i).getPictureID();//gets the picture id of the domino
            int rSide = newstateInstance.Player1Train.get(i).getRightSide();//gets the right side of the domino
            int lSide = newstateInstance.Player1Train.get(i).getLeftSide();//gets the left side of the domino
            Domino copy = new Domino(pID, rSide, lSide);
            Player1Train.add(i, copy);//adds domino to the train

        }
        Player2Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player2Train.size(); i++) {
            int pID = newstateInstance.Player2Train.get(i).getPictureID();//gets the picture id of the domino
            int rSide = newstateInstance.Player2Train.get(i).getRightSide();//gets the right side of the domino
            int lSide = newstateInstance.Player2Train.get(i).getLeftSide();//gets the left side of the domino
            Domino copy = new Domino(pID, rSide, lSide);
            Player2Train.add(i, copy);//adds domino to the train

        }
        Player3Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player3Train.size(); i++) {
            int pID = newstateInstance.Player3Train.get(i).getPictureID();//gets the picture id of the domino
            int rSide = newstateInstance.Player3Train.get(i).getRightSide();//gets the right side of the domino
            int lSide = newstateInstance.Player3Train.get(i).getLeftSide();//gets the left side of the domino
            Domino copy = new Domino(pID, rSide, lSide);
            Player3Train.add(i, copy);//adds domino to the train

        }
        Player4Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player4Train.size(); i++) {
            int pID = newstateInstance.Player4Train.get(i).getPictureID();//gets the picture id of the domino
            int rSide = newstateInstance.Player4Train.get(i).getRightSide();//gets the right side of the domino
            int lSide = newstateInstance.Player4Train.get(i).getLeftSide();//gets the left side of the domino
            Domino copy = new Domino(pID, rSide, lSide);
            Player4Train.add(i, copy);//adds domino to the train

        }
        PublicTrain = new ArrayList<>();
        for (int i = 0; i < newstateInstance.PublicTrain.size(); i++) {
            int pID = newstateInstance.PublicTrain.get(i).getPictureID();//gets the picture id of the domino
            int rSide = newstateInstance.PublicTrain.get(i).getRightSide();//gets the right side of the domino
            int lSide = newstateInstance.PublicTrain.get(i).getLeftSide();//gets the left side of the domino
            Domino copy = new Domino(pID, rSide, lSide);
            PublicTrain.add(i, copy);//adds domino to the train

        }
        //checks if trains are public and sets them accordinglly
        playerPublic.add(0, newstateInstance.playerPublic.get(0));
        playerPublic.add(1, newstateInstance.playerPublic.get(1));
        playerPublic.add(2, newstateInstance.playerPublic.get(2));
        playerPublic.add(3, newstateInstance.playerPublic.get(3));

        //checks the scores and sets them at the end of each round
        player1Score = newstateInstance.player1Score;
        player2Score = newstateInstance.player2Score;
        player3Score = newstateInstance.player3Score;
        player4Score = newstateInstance.player4Score;

        round = newstateInstance.round;

        doublePlay = newstateInstance.doublePlay;
        doublePlayTrain = newstateInstance.doublePlayTrain;
        doublePlayDomino = newstateInstance.doublePlayDomino;

        // Depending on which players com.example.degrood21.mexican_train_dominoes.game state instance is being sent in
        // it will deep copy accordingly
        Player1Hand = new ArrayList<>();
        for (int i = 0; i < newstateInstance.hand.get(0).size(); i++) {
            Player1Hand.add(i, newstateInstance.hand.get(0).get(i));//adds the dominoes to the players hand
        }
        hand.add(0, Player1Hand);
        playerTurn = newstateInstance.playerTurn;

        Player2Hand = new ArrayList<>();
        for (int i = 0; i < newstateInstance.hand.get(1).size(); i++) {
            Player2Hand.add(i, newstateInstance.hand.get(1).get(i));//adds the dominoes to the players hand
        }
        hand.add(1, Player2Hand);
        playerTurn = newstateInstance.playerTurn;

        Player3Hand = new ArrayList<>();
        for (int i = 0; i < newstateInstance.hand.get(2).size(); i++) {
            Player3Hand.add(i, newstateInstance.hand.get(2).get(i));//adds the dominoes to the players hand
        }
        hand.add(2, Player3Hand);
        playerTurn = newstateInstance.playerTurn;

        Player4Hand = new ArrayList<>();
        for (int i = 0; i < newstateInstance.hand.get(3).size(); i++) {
            Player4Hand.add(i, newstateInstance.hand.get(3).get(i));//adds the dominoes to the players hand
        }
        hand.add(3, Player4Hand);
        playerTurn = newstateInstance.playerTurn;
    }

    /**
     * dealAction method
     * <p>
     * deals 15 dominoes to each player for start of com.example.degrood21.mexican_train_dominoes.game
     *
     * @return if dealt correctly return true
     */
    public boolean dealAction() {//deals to each player at the beginning of the round

        for (int i = 0; i < 15; i++) {//adds 15 dominoes to each hand randomly
            int dom = randomDomino();
            hand.get(0).add(PileofDominoes.get(dom));
            PileofDominoes.remove(dom);//removes them from the pile
        }

        for (int i = 0; i < 15; i++) {//adds 15 dominoes to each hand randomly
            int dom = randomDomino();
            hand.get(1).add(PileofDominoes.get(dom));
            PileofDominoes.remove(dom);//removes them from the pile
        }
        for (int i = 0; i < 15; i++) {//adds 15 dominoes to each hand randomly
            int dom = randomDomino();
            hand.get(2).add(PileofDominoes.get(dom));
            PileofDominoes.remove(dom);//removes them from the pile
        }
        for (int i = 0; i < 15; i++) {//adds 15 dominoes to each hand randomly
            int dom = randomDomino();
            hand.get(3).add(PileofDominoes.get(dom));
            PileofDominoes.remove(dom);//removes them from the pile
        }
        return true;

    }

    /**
     * placeDomino method (NEEDS TO BE SIMPLIFIED)
     * <p>
     * places the selected domino passed in correctly and accordingly
     *
     * @param playerID       player who's turn it currently is
     * @param selectedDomino the selected domino that is trying to be placed on train
     * @param trainSelection the train that the domino is trying to be placed on
     * @return returns true if the domino was succesfully placed on corresponding train
     */
    public boolean placeDomino(int playerID, Domino selectedDomino, int trainSelection) {

        if (selectedDomino.leftSide == selectedDomino.rightSide) {//Checks for a double

            //checks if the size is 0 in which case you can't play a double on it
            if (trainSelection == 0 && Player1Train.size() == 0) {
                return false;
            }
            if (trainSelection == 1 && Player2Train.size() == 0) {
                return false;
            }
            if (trainSelection == 2 && Player3Train.size() == 0) {
                return false;
            }
            if (trainSelection == 3 && Player4Train.size() == 0) {
                return false;
            }
            if (trainSelection == 4 && PublicTrain.size() == 0) {
                return false;
            }

            if (doublePlay(playerID, selectedDomino, trainSelection)) {
                playerTurn--;
                return true;
            } else {
                return false;
            }
        } else if (playerTurn == playerID) { // If Player 1
            if ((playerPublic.get(0) == true && trainSelection == 0) || (playerID == 0 && trainSelection == 0)) { // Player 1 Train
                if (Player1Train.size() == 0) {//if the size is 0 so the value is round
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;//sets the side that matches to -1
                        Player1Train.add(selectedDomino);//adds the domino to the train
                        for (int i = 0; i < hand.get(playerID).size(); i++) {//loops through the hand searching for the domino
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);//removes the domino from the hand
                            }
                        }
                        // does not need to return false in for loop since we check playable in humanPlayer
                        return true;
                    } else if (selectedDomino.rightSide == round) {//if its the first domino of the train
                        selectedDomino.rightSide = -1;//sets right side to -1 to match it with last domino in train
                        Player1Train.add(selectedDomino);//adds the domino to the train
                        for (int i = 0; i < hand.get(playerID).size(); i++) {//loops through hand searching for the domino
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);//removes the domino
                            }
                        }
                        return true;//returns true if the domino was placed
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {//if size != 0 we check for it equalling
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {//the last domino in train
                        selectedDomino.rightSide = -1;//sets to -1
                        Player1Train.add(selectedDomino);
                        //All code in this method after this simply repeats but for each individual players hand and train, the comments
                        //would be the same throughout
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if ((playerPublic.get(1) == true && trainSelection == 1) || (playerID == 1 && trainSelection == 1)) { // Player 2 Train
                if (Player2Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if ((playerPublic.get(2) == true && trainSelection == 2) || (playerID == 2 && trainSelection == 2)) { // Player 3 Train
                if (Player3Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                }
            } else if ((playerPublic.get(3) == true && trainSelection == 3) || (playerID == 3 && trainSelection == 3)) { // Player 4 Train
                if (Player4Train.size() == 0) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
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
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).leftSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        for (int i = 0; i < hand.get(playerID).size(); i++) {
                            if (hand.get(playerID).get(i).pictureID == selectedDomino.pictureID) {
                                hand.get(playerID).remove(i);
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;//domino was not placed
    }

    /**
     * doublePlay method (ALSO NEEDS TO BE SIMPLIFIED)
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
        if (playerTurn == id) { // If Player 1
            if ((playerPublic.get(0) == true && trainSelection == 0) || (id == 0 && trainSelection == 0)) { // Player 1 Train
                if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) { //check end of train
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == playedDouble.rightSide) {//if played double matches right side of train
                        playedDouble.rightSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide; //set doubleplaydomino to value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == playedDouble.leftSide) {//check left side of double
                        playedDouble.leftSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {//check end of train
                    if (Player1Train.get(Player1Train.size() - 1).leftSide == playedDouble.rightSide) {//check right side of double
                        playedDouble.rightSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaydomino to value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).leftSide == playedDouble.leftSide) {//check left side of double
                        playedDouble.leftSide = -1;
                        Player1Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if ((playerPublic.get(1) == true && trainSelection == 1) || (id == 1 && trainSelection == 1)) { // Player 2 Train
                if (Player2Train.size() == 0) {//check if train is empty
                    if (playedDouble.leftSide == round) {//does our domino match that of the round to start the train
                        playedDouble.leftSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {//check end of train
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == playedDouble.rightSide) {//check if our domino matches the train
                        playedDouble.rightSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaydomino to value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == playedDouble.leftSide) {//check end of train
                        playedDouble.leftSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {//check end of train
                    if (Player2Train.get(Player2Train.size() - 1).leftSide == playedDouble.rightSide) {//check if our domino matches the train
                        playedDouble.rightSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaydomino to value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).leftSide == playedDouble.leftSide) {//check end of train
                        playedDouble.leftSide = -1;
                        Player2Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if ((playerPublic.get(2) == true && trainSelection == 2) || (id == 2 && trainSelection == 2)) { // Player 3 Train
                if (Player3Train.size() == 0) {//check if train is empty
                    if (playedDouble.leftSide == round) {//does our domino match that of the round to start a new train?
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (playedDouble.rightSide == round) {//check the rightside of our domino to see if it matches the round to start a new train
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {//check end of train
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == playedDouble.rightSide) {//compare the rightside of our domino with the end of the selected train
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == playedDouble.leftSide) {//compare the leftside of our domino with the end of train
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {//check end of train
                    if (Player3Train.get(Player3Train.size() - 1).leftSide == playedDouble.rightSide) {//compare rightside of domino with end of train
                        playedDouble.rightSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).leftSide == playedDouble.leftSide) {//compare leftside of domino with end of train
                        playedDouble.leftSide = -1;
                        Player3Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if ((playerPublic.get(3) == true && trainSelection == 3) || (id == 3 && trainSelection == 3)) { // Player 4 Train
                if (Player4Train.size() == 0) {//check if train is empty
                    if (playedDouble.leftSide == round) {//does leftside of domino match the value of the round
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (playedDouble.rightSide == round) {//does rightside of domino match the value of the round
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {//check end of train
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == playedDouble.rightSide) {//does rightside of domino match end of train
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaymethod to the value of played domino
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == playedDouble.leftSide) {//does leftside of domino match end of train
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {//check end of train
                    if (Player4Train.get(Player4Train.size() - 1).leftSide == playedDouble.rightSide) {//compare rightside of domino to end of train
                        playedDouble.rightSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).leftSide == playedDouble.leftSide) {//compare leftside of domino to end of train
                        playedDouble.leftSide = -1;
                        Player4Train.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {//check end of train
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == playedDouble.rightSide) {//compare rightside of domino to end of train
                        playedDouble.rightSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == playedDouble.leftSide) {//compare leftside of domino to end of train
                        playedDouble.leftSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {//check end of train
                    if (PublicTrain.get(PublicTrain.size() - 1).leftSide == playedDouble.rightSide) {//compare rightside of domino to end of train
                        playedDouble.rightSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.leftSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
                            }
                        }
                        doublePlayTrain = trainSelection;
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide == playedDouble.leftSide) {//compare leftside of domino to end of train
                        playedDouble.leftSide = -1;
                        PublicTrain.add(playedDouble);
                        doublePlay = true;
                        doublePlayDomino = playedDouble.rightSide;//set doubleplaydomino to the value of played double
                        for (int i = 0; i < hand.get(id).size(); i++) {
                            if (hand.get(id).get(i).pictureID == playedDouble.pictureID) {
                                hand.get(id).remove(i);//remove played domino from player's hand
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

    /**
     * drawAction -- draws a random domino from the PileOfDominos and adds it to the players hand
     *
     * @param id players id
     * @return true if you drew a card
     */

    public boolean drawAction(int id) {

        Random rand = new Random();//create new random instance
        //first check if there are no dominoes in the draw pile
        if (PileofDominoes == null || PileofDominoes.size() == 0) {
            return false;
        } else { //if dominoes still remain in the pile then assign a random domino to the correct player id.
            int randomIndex = rand.nextInt(PileofDominoes.size());
            if (id == 0) {
                Player1Hand.add(PileofDominoes.get(randomIndex));//adds domino to the players hand
                PileofDominoes.remove(randomIndex);//removes the domino from the pile
                playerPublic.set(0, true);//sets train to public since you couldnt play on it
                return true;
            } else if (id == 1) {
                Player2Hand.add(PileofDominoes.get(randomIndex));//adds domino to the players hand
                PileofDominoes.remove(randomIndex);//removes the domino from the pile
                playerPublic.set(1, true);//sets train to public since you couldnt play on it
                return true;
            } else if (id == 2) {
                Player3Hand.add(PileofDominoes.get(randomIndex));//adds domino to the players hand
                PileofDominoes.remove(randomIndex);//removes the domino from the pile
                playerPublic.set(2, true);//sets train to public since you couldnt play on it
                return true;
            } else if (id == 3) {
                Player4Hand.add(PileofDominoes.get(randomIndex));//adds domino to the players hand
                PileofDominoes.remove(randomIndex);//removes the domino from the pile
                playerPublic.set(3, true);//sets train to public since you couldnt play on it
                return true;
            }
            return false;
        }
    }

    /**
     * checkPlayable -- checks to see if a player can at least play one domino in hand
     * <p>
     * Only to be used for start of every turn and for draw action
     *
     * @param id             player ID that is being checked
     * @param trainSelection most always will be 0 since recursion checks all trains
     * @return true if player can play at least on domino
     */
    public boolean checkPlayable(int id, int trainSelection) {//trainSelection always starts at 0
        if (trainSelection >= 5) {//checks all trains, if trainSelection 5 you can't play on any
            return false;
        }

        //sets current hand to the players hand
        currentHand = hand.get(id);

        //sets the train to current train, then checks if it's public to see if you can play on it, also restricts playing on private trains
        if (trainSelection == 0 && playerPublic.get(0) || (trainSelection == id && id == 0)) {
            currentTrain = Player1Train;
        } else if (trainSelection == 1 && playerPublic.get(1) || (trainSelection == id && id == 1)) {
            currentTrain = Player2Train;
        } else if (trainSelection == 2 && playerPublic.get(2) || (trainSelection == id && id == 2)) {
            currentTrain = Player3Train;
        } else if (trainSelection == 3 && playerPublic.get(3) || (trainSelection == id && id == 3)) {
            currentTrain = Player4Train;
        } else if (trainSelection == 4) {
            currentTrain = PublicTrain;
        } else {
            currentTrain = null;//if the train is private and its not the train players turn
        }

        if (currentTrain != null) {
            for (int i = 0; i < currentHand.size(); i++) {//loops through each players hand based on size
                if (currentTrain.size() == 0) {//if train hasnt been started
                    if (currentHand.get(i).rightSide == round || currentHand.get(i).leftSide == round) {
                        return true;//trys to play on round
                    }

                } else if (doublePlay) {//checks if double play is true to restrict you from checking trains that don't satisfy the double
                    if (currentHand.get(i).rightSide == doublePlayDomino
                            || currentHand.get(i).leftSide == doublePlayDomino) {
                        return true;//you can play on that double
                    }
                } else {//Train size greater than 0
                    if (currentHand.get(i).rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                            || currentHand.get(i).rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                        return true;//checks right side of domino against the end of a train and returns as possible
                    } else if (currentHand.get(i).leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                            || currentHand.get(i).leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                        return true;//checks left side of domino against the end of a train and returns if possible
                    }
                }
            }
        }
        return checkPlayable(id, ++trainSelection);//recursively goes through again on the next train
    }

    /**
     * playableTrains -- Checks to see if a player's domino can be played on a specific train
     *
     * @param id             current player
     * @param selectedDomino domino that wants to be played by player
     * @param train          specific train to check if domino can be played here
     * @return true if that domino can be played on that train
     */
    public boolean playableTrains(int id, Domino selectedDomino, int train) {

        boolean returnCheck = false;//sets a boolean which will be returned in the end
        int trainSelection;
        trainSelection = train; //if Double Play is true then set trainSelection to doublePlayTrain otherwise set it to train

        /*
        sets the current trains to the respective players train
         */
        if (doublePlay) {
            trainSelection = doublePlayTrain;
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


        /*
        Tests the trains publicness and if they can be played on
        by the players hand who called the method

        players can always play on their own train
         */
        if ((playerPublic.get(0) == true && trainSelection == 0) || (id == 0 && trainSelection == 0)) {//checks if player 1's train is public
            if (currentTrain.size() == 0 && (selectedDomino.rightSide == round
                    || selectedDomino.leftSide == round)) {
                returnCheck = true;//first domino of the round, played on round number
            } else if (currentTrain.size() == 0) {
                returnCheck = false;//if size = 0 and the domino isnt equal to round then returns false
            } else if (selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;//players right side of domino can play on the train
            } else if (selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;//players left side of domino can play on the train

            }
        } else if ((playerPublic.get(1) == true && trainSelection == 1) || (id == 1 && trainSelection == 1)) {//checks if player 1's train is public
            if (currentTrain.size() == 0 && (selectedDomino.rightSide == round
                    || selectedDomino.leftSide == round)) {
                returnCheck = true;//first domino of the round, played on round number
            } else if (currentTrain.size() == 0) {
                returnCheck = false;//if size = 0 and the domino isnt equal to round then returns false
            } else if (selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;//players right side of domino can play on the train
            } else if (selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;//players left side of domino can play on the train

            }
        } else if ((playerPublic.get(2) == true && trainSelection == 2) || (id == 2 && trainSelection == 2)) {//checks if player 1's train is public
            if (currentTrain.size() == 0 && (selectedDomino.rightSide == round
                    || selectedDomino.leftSide == round)) {
                returnCheck = true;//first domino of the round, played on round number
            } else if (currentTrain.size() == 0) {
                returnCheck = false;//if size = 0 and the domino isnt equal to round then returns false
            } else if (selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;//players right side of domino can play on the train
            } else if (selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;//players left side of domino can play on the train

            }
        } else if ((playerPublic.get(3) == true && trainSelection == 3) || (id == 3 && trainSelection == 3)) {//checks if player 1's train is public
            if (currentTrain.size() == 0 && (selectedDomino.rightSide == round
                    || selectedDomino.leftSide == round)) {
                returnCheck = true;//first domino of the round, played on round number
            } else if (currentTrain.size() == 0) {
                returnCheck = false;//if size = 0 and the domino isnt equal to round then returns false
            } else if (selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;//players right side of domino can play on the train
            } else if (selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;//players left side of domino can play on the train

            }
        } else if ((trainSelection == 4)) {//public train is always public and not owned by a player
            if (currentTrain.size() == 1 && (selectedDomino.rightSide == round//the first domino in the array is the round
                    || selectedDomino.leftSide == round)) {
                returnCheck = true;//first domino of the round, played on round number
            } else if (currentTrain.size() == 1) {
                returnCheck = false;//if size = 0 and the domino isnt equal to round then returns false
            } else if (selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.rightSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;//players right side of domino can play on the train
            } else if (selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).rightSide
                    || selectedDomino.leftSide == currentTrain.get(currentTrain.size() - 1).leftSide) {
                returnCheck = true;//players left side of domino can play on the train

            }
        }
        return returnCheck;//returns a boolean either true or false
    }

    /**
     * doubleEndOfTrain -- Written for an edge case if a double is played but there are no more dominoes
     * that could satisfy the double in any players hand or the pile. then sets it false so players can
     * continue the round
     *
     * @param id always set to 0 to start the recursive function
     * @return doesnt matter, players always continue as normally
     */
    public boolean doubleEndOfTrain(int id) {//always send in ID 0
        if (doublePlay) {//if doubleplay if true check if its the last of its number and override
            //the force doubleplay
            //sets current hand equal to the players/pile's hand array
            if (id < 4) {
                currentHand = hand.get(id);
            } else {
                currentHand = PileofDominoes;
            }

            for (int i = 0; i < currentHand.size(); i++) {
                if (currentHand.get(i).rightSide == doublePlayDomino//can play right side on the double domino
                        || currentHand.get(i).leftSide == doublePlayDomino) {//can play left side on the double domino
                    return true;//you can play on the double
                }
            }
            if (id >= 4) {
                doublePlay = false;//sets double to false
                return true;//sets double play boolean to false then should continue your turn
                // as usual but without forcing you to play on double
            }
            return doubleEndOfTrain(++id);//recursions to check the next hand or pile for the doublePlayDomino value
        }
        return true;//returns as if its not double play
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
}
