package com.example.degrood21.mexican_train_dominoes;

import android.view.View;

import com.example.degrood21.mexican_train_dominoes.game.GameMainActivity;
import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.LocalGame;
import com.example.degrood21.mexican_train_dominoes.game.config.GameConfig;
import com.example.degrood21.mexican_train_dominoes.game.config.GamePlayerType;

import java.util.ArrayList;

/**
 * Main Activity
 * Creates Permanent Set of Dominoes
 * by creating individual dominoes with Drawable ID, Right Value, Left Value
 * and adds them to setOfDominoes ArrayList
 *
 * @authors Dylan DeGrood, Callum Morham, Logan Crawford, Devin Smith
 */

public class MainActivity extends GameMainActivity implements View.OnClickListener {

    private static final int PORT_NUMBER = 2278;

    @Override
    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // MexicanTrain has three player types:  human and computer and smartComputer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                if(name.length() > 8) name = name.substring(0, 8);
                return new MTHumanPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                if(name.length() > 8) name = name.substring(0, 8);
                return new MTComputerPlayer(name, false);
            }});
        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            public GamePlayer createPlayer(String name) {
                if(name.length() > 8) name = name.substring(0, 8);
                return new MTComputerPlayer(name, true);
            }});

        // Create a game configuration class for MexicanTrain:
        GameConfig defaultConfig = new GameConfig(playerTypes, 4, 4, "MexicanTrain", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player type 1: a human player
        defaultConfig.addPlayer("Smart Computer", 2);//player type 3: a smart computer player
        defaultConfig.addPlayer("Computer", 1); // player type 2: a computer player

        defaultConfig.setRemoteData("Remote Human Player", "", 0);


        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame(int numberOfPlayers) {
        return new MTLocalGame(numberOfPlayers);
    }

}
