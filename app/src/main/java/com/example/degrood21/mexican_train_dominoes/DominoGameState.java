package com.example.degrood21.mexican_train_dominoes;

import android.graphics.drawable.Drawable;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by degrood21 on 2/26/2018.
 */

public class DominoGameState {

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
    int player1Score, player2Score, player3Score, player4Score;
    int playerTurn, round;
    boolean player1Public, player2Public, player3Public, player4Public, rotationLeft, rotationRight;

    // Beginning of play
    public DominoGameState(ArrayList<Domino> allDominoes) {

        round = 12;
        //PileofDominoes = allDominoes;
        PileofDominoes = new ArrayList<>();
        Domino copyDom;
        for (int i = 0; i < allDominoes.size(); i++) {
            copyDom = allDominoes.get(i);
            PileofDominoes.add(i, allDominoes.get(i));

        }

        PublicTrain.add(0, allDominoes.get(round));
        PileofDominoes.remove(allDominoes.get(round));

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

    // Deep Copy Constructor
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

            Player1Train.add(i, newstateInstance.Player1Train.get(i));

        }
        Player2Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player2Train.size(); i++) {

            Player2Train.add(i, newstateInstance.Player2Train.get(i));

        }
        Player3Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player3Train.size(); i++) {

            Player3Train.add(i, newstateInstance.Player3Train.get(i));

        }
        Player4Train = new ArrayList<>();
        for (int i = 0; i < newstateInstance.Player4Train.size(); i++) {

            Player4Train.add(i, newstateInstance.Player4Train.get(i));

        }
        PublicTrain = new ArrayList<>();
        for (int i = 0; i < newstateInstance.PublicTrain.size(); i++) {

            PublicTrain.add(i, newstateInstance.PublicTrain.get(i));

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

        // Depending on which players game state instance is being sent in
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

    public boolean testAction( /*usually is sent a player ID as param */) {

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

    public boolean selectDomino(int id) { //Callum
        if (id == 0) {
            if (Player1Hand != null) {
                //if() domino id(for a 9) == c9_9 || c8_9 ||etc && player1train == c9_9 || c8_9 etc?
                //same for each player

                //check which trains are public

                //so check if domino matches one with the value in player1train || public
                //train ||any other player train that == public// see above line added after
                //if true return true, if false return false

                //then check if the end of the piecec that is pointed out is the one that matches

                //maybe check last two pieces and see whats in common then check for other end, if double write
                //edge case

                //do for all.
                return true;
            }
        } else if (id == 1) {
            if (Player2Hand != null) {
                return true;
            }
        } else if (id == 2) {
            if (Player3Hand != null) {
                return true;
            }
        } else if (id == 3) {
            if (Player4Hand != null) {
                return true;
            }
        }

        return false;
    }

    // placeDomino spans from Line 188 to 844 (656 Lines of Code)
    public boolean placeDomino(int playerID, Domino selectedDomino, int trainSelection) { //Dylan

        if (selectedDomino.leftSide == selectedDomino.rightSide) {
            //doublePlay(playerID, selectedDomino, trainSelection);
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
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (player2Public == true && trainSelection == 1) { // Player 2 Train
                if (Player2Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (player3Public == true && trainSelection == 2) { // Player 3 Train
                if (Player3Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (player4Public == true && trainSelection == 3) { // Player 4 Train
                if (Player4Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                }
            }
        } else if (playerID == 1) { // If Player 2
            if (player1Public == true && trainSelection == 0) { // Player 1 Train
                if (Player1Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (trainSelection == 1) { // Player 2 Train
                if (Player2Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (player3Public == true && trainSelection == 2) { // Player 3 Train
                if (Player3Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (player4Public == true && trainSelection == 3) { // Player 4 Train
                if (Player4Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                }
            }
        } else if (playerID == 2) { // If Player 3
            if (player1Public == true && trainSelection == 0) { // Player 1 Train
                if (Player1Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (player2Public == true && trainSelection == 1) { // Player 2 Train
                if (Player2Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (trainSelection == 2) { // Player 3 Train
                if (Player3Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (player4Public == true && trainSelection == 3) { // Player 4 Train
                if (Player4Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                }
            }
        } else if (playerID == 3) { // If Player 4
            if (player1Public == true && trainSelection == 0) { // Player 1 Train
                if (Player1Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).rightSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player1Train.get(Player1Train.size() - 1).leftSide != -1) {
                    if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    } else if (Player1Train.get(Player1Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player1Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (player2Public == true && trainSelection == 1) { // Player 2 Train
                if (Player2Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).rightSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player2Train.get(Player2Train.size() - 1).leftSide != -1) {
                    if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    } else if (Player2Train.get(Player2Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player2Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (player3Public == true && trainSelection == 2) { // Player 3 Train
                if (Player3Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).rightSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player3Train.get(Player3Train.size() - 1).leftSide != -1) {
                    if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    } else if (Player3Train.get(Player3Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player3Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (trainSelection == 3) { // Player 4 Train
                if (Player4Train == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).rightSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                } else if (Player4Train.get(Player4Train.size() - 1).leftSide != -1) {
                    if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    } else if (Player4Train.get(Player4Train.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        Player4Train.add(selectedDomino);
                        return true;
                    }
                }
            } else if (trainSelection == 4) { // Public Train
                if (PublicTrain == null) {
                    if (selectedDomino.leftSide == round) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (selectedDomino.rightSide == round) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                } else if (PublicTrain.get(PublicTrain.size() - 1).leftSide != -1) {
                    if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.rightSide) {
                        selectedDomino.rightSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    } else if (PublicTrain.get(PublicTrain.size() - 1).rightSide == selectedDomino.leftSide) {
                        selectedDomino.leftSide = -1;
                        PublicTrain.add(selectedDomino);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean doublePlay(int id) { //Logan
        if (id == 0) {
            //if () {

            //    return true;
            //}
        } else if (id == 1) {
            if (Player2Hand != null) {
                return true;
            }
        } else if (id == 2) {
            if (Player3Hand != null) {
                return true;
            }
        } else if (id == 3) {
            if (Player4Hand != null) {
                return true;
            }
        }

        return false;
    }

    public boolean drawAction(int id) { // Devin
        if (id == 0) {
            if (Player1Hand != null) {
                return true;
            }
        } else if (id == 1) {
            if (Player2Hand != null) {
                return true;
            }
        } else if (id == 2) {
            if (Player3Hand != null) {
                return true;
            }
        } else if (id == 3) {
            if (Player4Hand != null) {
                return true;
            }
        }

        return false;
    }

    public boolean quitGame(int id) {
        return true;
    }

    public boolean endTurn(int id) {
        return true;
    }

    public boolean restartGame() {
        return true;
    }

    public boolean choosePlayerType() {
        return true;
    }

    public boolean changePlayerName() {
        return true;
    }

    public void updateScore() {
    }

    public int randomDomino() { // Helps with giving all players 15 dominoes @ start

        Random ran = new Random();
        return ran.nextInt(PileofDominoes.size());

    }

    //only have ints/booleans at the moment
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

        return "\nRound: " + round + "\nPlayer Turn: " + playerTurn + "\nPlayer 1 Score: " +
                player1Score + "\nPlayer 2 Score: " + player2Score + "\nPlayer 3 Score: " + player3Score +
                "\nPlayer 4 Score: " + player4Score + "\nIs Player 1's Train Public: " + player1Public +
                "\nIs Player 2's Train Public: " + player2Public + "\nIs Player 3's Train Public: " +
                player3Public + "\nIs Player 4's Train Public: " + player4Public + "\n" + completePrint + "\n";
    }

}
