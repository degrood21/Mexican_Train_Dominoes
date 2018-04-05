package com.example.degrood21.mexican_train_dominoes.game.infoMsg;

/**
 * A message from the com.example.degrood21.mexican_train_dominoes.game to a player that tells the player that
 * the com.example.degrood21.mexican_train_dominoes.game is over.
 *
 * @author Steven R. Vegdahl 
 * @version July 2013
 */
public class GameOverInfo extends GameInfo {
	
	// to satisfy the Serializable interface
	private static final long serialVersionUID = -8005304466588509849L;
	
	// the message that gives the com.example.degrood21.mexican_train_dominoes.game's result
	private String message;
	
	/**
	 * constructor
	 * 
	 * @param msg
	 * 		a message that tells the result of the com.example.degrood21.mexican_train_dominoes.game
	 */
	public GameOverInfo(String msg) {
		this.message = msg;
	}
	
	/**
	 * getter method for the message
	 * 
	 * @return
	 * 		the message, telling the result of the com.example.degrood21.mexican_train_dominoes.game
	 */
	public String getMessage() {
		return message;
	}
}
