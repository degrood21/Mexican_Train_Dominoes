package com.example.degrood21.mexican_train_dominoes.game.infoMsg;

/**
 * The state of the com.example.degrood21.mexican_train_dominoes.game. This class should be subclassed so that it holds
 * all state information for the particular com.example.degrood21.mexican_train_dominoes.game being implemented. For
 * example, if the com.example.degrood21.mexican_train_dominoes.game were chess, it would contain the contents of each
 * square on the board, which player's turn it was, etc.
 *
 * @author Steven R. Vegdahl 
 * @version July 2013
 */
public abstract class GameState extends GameInfo {

	// to satisfy the Serializable interface
	private static final long serialVersionUID = -5109179064333136954L;

}
