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

    }

    // Deep Copy Constructor
    public DominoGameState(DominoGameState newstateInstance){

        PileofDominoes = newstateInstance.PileofDominoes;
        Player1Train = newstateInstance.Player1Train;
        Player2Train = newstateInstance.Player2Train;
        Player3Train = newstateInstance.Player3Train;
        Player4Train = newstateInstance.Player4Train;
        player1Public = newstateInstance.player1Public;
        player2Public = newstateInstance.player2Public;
        player3Public = newstateInstance.player3Public;
        player4Public = newstateInstance.player4Public;
        PublicTrain = newstateInstance.PublicTrain;
        round = newstateInstance.round;


        // Depending on which players game state instance is being sent in
        // it will deep copy accordingly
        if(newstateInstance.playerTurn == 0){ // player 1

            playerTurn = newstateInstance.playerTurn;
            Player1Hand = newstateInstance.Player1Hand;

        }
        else if(newstateInstance.playerTurn == 1){ // player 2

            playerTurn = newstateInstance.playerTurn;
            Player2Hand = newstateInstance.Player2Hand;

        }
        else if(newstateInstance.playerTurn == 2){ // player 3

            playerTurn = newstateInstance.playerTurn;
            Player3Hand = newstateInstance.Player3Hand;


        }
        else if(newstateInstance.playerTurn == 3){ // player 4

            playerTurn = newstateInstance.playerTurn;
            Player4Hand = newstateInstance.Player4Hand;

        }
    }

    public boolean testAction( /*usually is sent a player ID as param */ ){

        return true;

    }

    //only have ints/booleans at the moment
    @Override
    public String toString() {
        return round + "\n" + playerTurn + "\n" + player1Score + "\n" + player2Score + "\n" + player3Score +
                "\n" + player4Score + "\n" + player1Public + "\n" + player2Public + "\n" +
                player3Public + "\n" + player4Public + "\n" ;
    }

}
