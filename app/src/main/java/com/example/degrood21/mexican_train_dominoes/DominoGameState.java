package com.example.degrood21.mexican_train_dominoes;

import android.graphics.drawable.Drawable;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by degrood21 on 2/26/2018.
 */

public class DominoGameState {

    ArrayList<Drawable> PileofDominoes = new ArrayList<>();
    ArrayList<Drawable> Player1Hand = new ArrayList<>();
    ArrayList<Drawable> Player2Hand = new ArrayList<>();
    ArrayList<Drawable> Player3Hand = new ArrayList<>();
    ArrayList<Drawable> Player4Hand = new ArrayList<>();
    ArrayList<Drawable> Player1Train = new ArrayList<>();
    ArrayList<Drawable> Player2Train = new ArrayList<>();
    ArrayList<Drawable> Player3Train = new ArrayList<>();
    ArrayList<Drawable> Player4Train = new ArrayList<>();
    ArrayList<Drawable> PublicTrain = new ArrayList<>();
    int player1Score, player2Score, player3Score, player4Score;
    int playerTurn, round;
    boolean player1Public, player2Public, player3Public, player4Public;

    // Beginning of play
    public DominoGameState(ArrayList<Drawable> allDominoes){

        PileofDominoes = allDominoes;

        if(allDominoes.size() != 0){ // temporary until we get full set of drawable dominoes

            PublicTrain.add(0, allDominoes.get(12));

        }

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
    public DominoGameState(DominoGameState newstateInstance){

        PileofDominoes = new ArrayList<>();
        for(int i = 0; i < newstateInstance.PileofDominoes.size(); i++) {

            PileofDominoes.add(i, newstateInstance.PileofDominoes.get(i));

        }
        Player1Train = new ArrayList<>();
        for(int i = 0; i < newstateInstance.Player1Train.size(); i++) {

            Player1Train.add(i, newstateInstance.Player1Train.get(i));

        }
        Player2Train = new ArrayList<>();
        for(int i = 0; i < newstateInstance.Player2Train.size(); i++) {

            Player2Train.add(i, newstateInstance.Player2Train.get(i));

        }
        Player3Train = new ArrayList<>();
        for(int i = 0; i < newstateInstance.Player3Train.size(); i++) {

            Player3Train.add(i, newstateInstance.Player3Train.get(i));

        }
        Player4Train = new ArrayList<>();
        for(int i = 0; i < newstateInstance.Player4Train.size(); i++) {

            Player4Train.add(i, newstateInstance.Player4Train.get(i));

        }
        PublicTrain = new ArrayList<>();
        for(int i = 0; i < newstateInstance.PublicTrain.size(); i++) {

            PublicTrain.add(i, newstateInstance.PublicTrain.get(i));

        }
        player1Public = newstateInstance.player1Public;
        player2Public = newstateInstance.player2Public;
        player3Public = newstateInstance.player3Public;
        player4Public = newstateInstance.player4Public;
        round = newstateInstance.round;

        // Depending on which players game state instance is being sent in
        // it will deep copy accordingly
        if(newstateInstance.playerTurn == 0){ // player 1

            Player1Hand = new ArrayList<>();
            for(int i = 0; i < newstateInstance.Player1Hand.size(); i++) {

                Player1Hand.add(i, newstateInstance.Player1Hand.get(i));

            }
            playerTurn = newstateInstance.playerTurn;

        }
        else if(newstateInstance.playerTurn == 1){ // player 2

            Player2Hand = new ArrayList<>();
            for(int i = 0; i < newstateInstance.Player2Hand.size(); i++) {

                Player2Hand.add(i, newstateInstance.Player2Hand.get(i));

            }
            playerTurn = newstateInstance.playerTurn;

        }
        else if(newstateInstance.playerTurn == 2){ // player 3

            Player3Hand = new ArrayList<>();
            for(int i = 0; i < newstateInstance.Player3Hand.size(); i++) {

                Player3Hand.add(i, newstateInstance.Player3Hand.get(i));

            }
            playerTurn = newstateInstance.playerTurn;

        }
        else if(newstateInstance.playerTurn == 3){ // player 4

            Player4Hand = new ArrayList<>();
            for(int i = 0; i < newstateInstance.Player4Hand.size(); i++) {

                Player4Hand.add(i, newstateInstance.Player4Hand.get(i));

            }
            playerTurn = newstateInstance.playerTurn;

        }
    }

    public boolean testAction( /*usually is sent a player ID as param */ ){

        return true;

    }

    public void updateScore(){
    }

    //only have ints/booleans at the moment
    @Override
    public String toString() {
        return round + "\n" + playerTurn + "\n" + player1Score + "\n" + player2Score + "\n" + player3Score +
                "\n" + player4Score + "\n" + player1Public + "\n" + player2Public + "\n" +
                player3Public + "\n" + player4Public + "\n" ;
    }

}
