package com.example.degrood21.mexican_train_dominoes;

import android.view.View;
import com.example.degrood21.mexican_train_dominoes.game.GameMainActivity;
import com.example.degrood21.mexican_train_dominoes.game.GamePlayer;
import com.example.degrood21.mexican_train_dominoes.game.LocalGame;
import com.example.degrood21.mexican_train_dominoes.game.config.GameConfig;
import com.example.degrood21.mexican_train_dominoes.game.config.GamePlayerType;
import java.util.ArrayList;

/**
 * Final Release Notes for Mexican Train Dominoes:
 *
 * - Meets all specified requirements for the final release
 * - All known and reported bugs have been fixed and game is finalized
 * - Code meets the Coding Standard
 * - 7 JUnit Tests have been created and passed
 *
 * - 3 Star Game has been approved by Nux
 *
 * - Smart AI is more than random (Dumb AI)
 *
 * - Game does only work in landscape mode not portrait
 *
 * - Game includes and has Network Play working
 *
 * - Game does not crash for any known reason as of now (4.25.18)
 *
 * - Extra Features:
 *      - Quit and Restart Buttons
 *      - Help Button that displays rules (question mark)
 *      - Notifications for Double Play (Notifies the player which train they must play on)
 *      - Notifies the Player when they select a domino, where they can play and on which train
 *
 */


/**
 * External Citation
 * Date: 20 April 2018
 * Problem: Needed the game framework
 * Resource: https://github.com/srvegdahl/SlapJack
 * Solution: We got the framework from SlapJack
 */


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

        // MexicanTrain has three player types:  human, computer and smartComputer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                if (name.length() > 8) name = name.substring(0, 9);//Limits the name length to 8 so it doesn't miss align the trains.
                return new MTHumanPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                if (name.length() > 8) name = name.substring(0, 8);
                return new MTComputerPlayer(name, false);
            }
        });
        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            public GamePlayer createPlayer(String name) {
                if (name.length() > 8) name = name.substring(0, 9);
                return new MTComputerPlayer(name, true);
            }
        });

        // Create a game configuration class for MexicanTrain:
        GameConfig defaultConfig = new GameConfig(playerTypes, 4, 4, "MexicanTrain", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player type 1: a human player
        defaultConfig.addPlayer("Smart AI", 2);//player type 3: a smart computer player
        defaultConfig.addPlayer("Dumb AI", 1); // player type 2: a computer player
        defaultConfig.addPlayer("Smart AI 2", 2); // player type 2: a computer player

        defaultConfig.setRemoteData("Remote Human Player", "", 0);


        return defaultConfig;
    }

    @Override
    /**
     * createLocalGame does exactly what the name implies
     *
     * @param numberOfPlayers the number of players in the ganme (4)
     */
    public LocalGame createLocalGame(int numberOfPlayers) {
        return new MTLocalGame(numberOfPlayers);
    }
}
