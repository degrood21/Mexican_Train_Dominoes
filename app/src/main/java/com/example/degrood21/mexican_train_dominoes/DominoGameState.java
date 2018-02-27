package com.example.degrood21.mexican_train_dominoes;

import android.graphics.drawable.Drawable;

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

    public DominoGameState(ArrayList<Drawable> allDominoes){

        PileofDominoes = allDominoes;
        PublicTrain.add(0,allDominoes.get(12));
        round = 12;
        playerTurn = 0; //0 means it is player 1's turn
        player1Score = 0;
        player2Score = 0;
        player3Score = 0;
        player4Score = 0;
        player1Public = false;
        player2Public = false;
        player3Public = false;
        player4Public = false;

    }
}
