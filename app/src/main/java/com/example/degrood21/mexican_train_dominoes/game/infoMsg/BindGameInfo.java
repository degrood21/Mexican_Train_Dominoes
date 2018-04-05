package com.example.degrood21.mexican_train_dominoes.game.infoMsg;

import com.example.degrood21.mexican_train_dominoes.game.Game;

/**
 * A message from the com.example.degrood21.mexican_train_dominoes.game to a player that tells the player:
 * - who the com.example.degrood21.mexican_train_dominoes.game is
 * - what the player's ID number is (i.e., player 0, player 1, etc.)
 *
 * @author Steven R. Vegdahl 
 * @version July 2013
 */
public class BindGameInfo extends GameInfo {
	
	// to satisfy the Serializable interface
	private static final long serialVersionUID = 8819385408185187694L;
	
	// the com.example.degrood21.mexican_train_dominoes.game object
	Game game;
	
	// the player's ID number
	int playerNum;
	
	/**
	 * constructor
	 * 
	 * @param g
	 * 		the com.example.degrood21.mexican_train_dominoes.game
	 * @param pn
	 * 		the player number in the com.example.degrood21.mexican_train_dominoes.game
	 */
	public BindGameInfo(Game g, int pn) {
		game = g;
		playerNum = pn;
	}
	
	/**
	 * getter method, returning the com.example.degrood21.mexican_train_dominoes.game
	 * 
	 * @return
	 * 		the com.example.degrood21.mexican_train_dominoes.game
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * getter method, returning the player ID
	 * 
	 * @return
	 * 		the player ID
	 */
	public int getPlayerNum() {
		return playerNum;
	}
	
	/**
	 * Setter method for the com.example.degrood21.mexican_train_dominoes.game. It is expected that this method will be
	 * used only by ProxyPlayer and ProxyGame objects.
	 * 
	 * @param g
	 * 		the com.example.degrood21.mexican_train_dominoes.game
	 */
	public void setGame(Game g) {
		game = g;
	}

}
