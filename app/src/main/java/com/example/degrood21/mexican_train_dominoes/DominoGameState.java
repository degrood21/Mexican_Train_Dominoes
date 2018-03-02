package com.example.degrood21.mexican_train_dominoes;

import android.graphics.drawable.Drawable;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by degrood21 on 2/26/2018.
 */

public class DominoGameState {

    ArrayList<Integer> PileofDominoes = new ArrayList<>();
    ArrayList<Integer> Player1Hand = new ArrayList<>();
    ArrayList<Integer> Player2Hand = new ArrayList<>();
    ArrayList<Integer> Player3Hand = new ArrayList<>();
    ArrayList<Integer> Player4Hand = new ArrayList<>();
    ArrayList<Integer> Player1Train = new ArrayList<>();
    ArrayList<Integer> Player2Train = new ArrayList<>();
    ArrayList<Integer> Player3Train = new ArrayList<>();
    ArrayList<Integer> Player4Train = new ArrayList<>();
    ArrayList<Integer> PublicTrain = new ArrayList<>();
    int player1Score, player2Score, player3Score, player4Score;
    int playerTurn, round;
    boolean player1Public, player2Public, player3Public, player4Public;

    // Beginning of play
    public DominoGameState(ArrayList<Integer> allDominoes) {

        round = 12;
        PileofDominoes = allDominoes;

        PublicTrain.add(0, allDominoes.get(round));

        playerTurn = 0; // 0 means it is player 1's turn
        player1Score = 0;
        player2Score = 0;
        player3Score = 0;
        player4Score = 0;
        player1Public = false;
        player2Public = false;
        player3Public = false;
        player4Public = false;

        //comment to test GitHub
    }

    // Deep Copy Constructor
    public DominoGameState(DominoGameState newstateInstance) {

        PileofDominoes = new ArrayList<>();
        for (int i = 0; i < newstateInstance.PileofDominoes.size(); i++) {

            PileofDominoes.add(i, newstateInstance.PileofDominoes.get(i));

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

    public boolean placeDomino(int id, int selectedDomino) { //Dylan
        if (id == 0) {
            if (Player1Train.get(Player1Train.size() - 1) == selectedDomino) {
                return true;
            }

            if (player2Public == true) {
            }

            if (player3Public == true) {
            }

            if (player4Public == true) {
            }

            if (Player1Train.get(Player1Train.size() - 1) == selectedDomino) {
                return true;
            }
            if (Player1Train.get(Player1Train.size() - 1) == selectedDomino) {
                return true;
            }
        }
        if (id == 1) {
            if (Player2Hand != null) {
                return true;
            }
        }
        if (id == 2) {
            if (Player3Hand != null) {
                return true;
            }
        }
        if (id == 3) {
            if (Player4Hand != null) {
                return true;
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

    //only have ints/booleans at the moment
    @Override
    public String toString() {
        return round + "\n" + playerTurn + "\n" + player1Score + "\n" + player2Score + "\n" + player3Score +
                "\n" + player4Score + "\n" + player1Public + "\n" + player2Public + "\n" +
                player3Public + "\n" + player4Public + "\n";
    }

}
