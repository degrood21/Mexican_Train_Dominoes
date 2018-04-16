package com.example.degrood21.mexican_train_dominoes.game;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameAction;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.GameOverAckAction;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.MyNameIsAction;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.ReadyAction;
import com.example.degrood21.mexican_train_dominoes.game.actionMsg.TimerAction;
import com.example.degrood21.mexican_train_dominoes.game.infoMsg.BindGameInfo;
import com.example.degrood21.mexican_train_dominoes.game.infoMsg.GameOverInfo;
import com.example.degrood21.mexican_train_dominoes.game.infoMsg.IllegalMoveInfo;
import com.example.degrood21.mexican_train_dominoes.game.infoMsg.NotYourTurnInfo;
import com.example.degrood21.mexican_train_dominoes.game.infoMsg.StartGameInfo;
import com.example.degrood21.mexican_train_dominoes.game.util.GameTimer;
import com.example.degrood21.mexican_train_dominoes.game.util.Tickable;

/**
 * A class that knows how to play the com.example.degrood21.mexican_train_dominoes.game. The data in this class represent the
 * state of a com.example.degrood21.mexican_train_dominoes.game. The state represented by an instance of this class can be a
 * complete state (as might be used by the main com.example.degrood21.mexican_train_dominoes.game activity) or a partial
 * state as it would be seen from the perspective of an individual player.
 * 
 * Each com.example.degrood21.mexican_train_dominoes.game has a unique state definition, so this abstract base class has
 * little inherent functionality.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew Nuxoll
 * @version July 2013
 */
public abstract class LocalGame implements Game, Tickable {
	
	// the stage that the com.example.degrood21.mexican_train_dominoes.game is in
	private GameStage gameStage = GameStage.BEFORE_GAME;
	
	// the handler for the com.example.degrood21.mexican_train_dominoes.game's thread
	private Handler myHandler;
		
	// the players in the com.example.degrood21.mexican_train_dominoes.game, in order of  player number
	protected GamePlayer[] players;
	
	// whether the com.example.degrood21.mexican_train_dominoes.game's thread is running
	private boolean running = false;
	
	// the players' names, paralleling the 'players' array
	protected String[] playerNames;
	private int playerNameCount = 0; // number of players who have told us their name
	
	// the players are ready to start
	private boolean[] playersReady;
	private int playerReadyCount = 0; // number of players who are ready to start the com.example.degrood21.mexican_train_dominoes.game
	
	// the players which have acknowledged that the com.example.degrood21.mexican_train_dominoes.game is over
	private boolean[] playersFinished;
	private int playerFinishedCount = 0; // number of player who have so acknowledged
	
	// this com.example.degrood21.mexican_train_dominoes.game's timer and timer action
	private GameTimer myTimer = new GameTimer(this);
	
	/**
	 * Returns the com.example.degrood21.mexican_train_dominoes.game's timer
	 * 
	 * @return the com.example.degrood21.mexican_train_dominoes.game's timer
	 */
	protected final GameTimer getTimer() {
		return myTimer;
	}
	
	/**
	 * starts the com.example.degrood21.mexican_train_dominoes.game
	 * 
	 * @param players
	 * 			the list of players who are playing in the com.example.degrood21.mexican_train_dominoes.game
	 */
	public void start(GamePlayer[] players) {
		// if the com.example.degrood21.mexican_train_dominoes.game has already started, don't restart
		if (this.players != null) return;
		
		// create/store a copy of the player array
		this.players = (GamePlayer[])players.clone();
		
		// create an array for the players' names; these names will be
		// filled during the initial message-protocol between the com.example.degrood21.mexican_train_dominoes.game
		// and players
		this.playerNames = new String[players.length];
		
		// start the thread for this com.example.degrood21.mexican_train_dominoes.game
		synchronized(this) {
			// if already started, don't restart
			if (running) return;
			running = true; // mark as running
			
			// create a thread that loops, waiting for actions;
			// start the thread
			Runnable runnable = new Runnable() {
				public void run() {
					Looper.prepare();
					myHandler = new MyHandler(LocalGame.this);
					Looper.loop();
				}
			};
			Thread thread = new Thread(runnable);
			thread.setName("Local Game");
			thread.start();
		}
		
		// at this point the com.example.degrood21.mexican_train_dominoes.game is running, so set our com.example.degrood21.mexican_train_dominoes.game stage to be that of
		// waiting for the players to tell us their names
		gameStage = GameStage.WAITING_FOR_NAMES;
		
		// start each player, telling them each who their com.example.degrood21.mexican_train_dominoes.game and playerID are
		for (int i = 0; i < players.length; i++) {
			players[i].start();
			players[i].sendInfo(new BindGameInfo(this, i));
		}
	}
	
	/**
	 * Notify the given player that its state has changed. This should involve sending
	 * a GameInfo object to the player. If the com.example.degrood21.mexican_train_dominoes.game is not a perfect-information com.example.degrood21.mexican_train_dominoes.game
	 * this method should remove any information from the com.example.degrood21.mexican_train_dominoes.game that the player is not
	 * allowed to know.
	 * 
	 * @param p
	 * 			the player to notify
	 */
	protected abstract void sendUpdatedStateTo(GamePlayer p);
	
	/**
	 * Notify all players that the com.example.degrood21.mexican_train_dominoes.game's state has changed. Typically this simply
	 * calls the 'notifyStateChanged' method for each player.
	 */
	protected final void sendAllUpdatedState() {
		for (GamePlayer p : players) {
			sendUpdatedStateTo(p);
		}
	}
	
	/**
	 * Determines the numeric player ID (0 through whatever) for the given player.
	 * @param p
	 * 			the player whose player ID we want
	 * @return
	 * 			the player's ID, or -1 if the player is not a player in this com.example.degrood21.mexican_train_dominoes.game
	 */
	protected final int getPlayerIdx(GamePlayer p) {
		for (int i = 0; i < players.length; i++) {
			if (p == players[i]) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Invoked whenever the com.example.degrood21.mexican_train_dominoes.game's thread receives a message (e.g., from a player
	 * or from a timer).
	 * 
	 * @param msg
	 * 			the message that was received
	 */
	private void receiveMessage(Message msg) {
		if (msg.obj instanceof GameAction) { // ignore if not GameAction
			GameAction action = (GameAction)msg.obj;

			// CASE 1: the com.example.degrood21.mexican_train_dominoes.game is at the stage where we we waiting for
			// players to tell us their names. In this case, we expect
			// a MyNameIsAction object. Once each player have told us its
			// name, we move on to the next stage.

			if (action instanceof MyNameIsAction &&
					gameStage == GameStage.WAITING_FOR_NAMES) {
				MyNameIsAction mnis = (MyNameIsAction) action;
				Log.i("LocalGame", "received 'myNameIs' ("+mnis.getName()+")");

				// mark that player as having given us its name
				int playerIdx = getPlayerIdx(mnis.getPlayer());
				if (playerIdx >= 0 && playerNames[playerIdx] == null) {
					playerNames[playerIdx] = mnis.getName(); // store player name
					playerNameCount++;
				}

				// If all players have told us their name, then move onto the next
				// com.example.degrood21.mexican_train_dominoes.game stage, and send a message to each player that the com.example.degrood21.mexican_train_dominoes.game is
				// about to start
				if (playerNameCount >= playerNames.length) {
					Log.i("LocalGame", "broadcasting player names");
					gameStage = GameStage.WAITING_FOR_READY;
					playersReady = new boolean[players.length]; // array to keep track of players responding
					for (GamePlayer p : players) {
						p.sendInfo(
								new StartGameInfo((String[])playerNames.clone()));
					}
				}
			}
			else if (action instanceof ReadyAction &&
					gameStage == GameStage.WAITING_FOR_READY) {

				// CASE 2: we have told all players that the com.example.degrood21.mexican_train_dominoes.game is about to start;
				// we are now processing ReadyAction messages from each player to
				// acknowledge this.
				ReadyAction ra = (ReadyAction)action;

				// mark the given player as being ready
				int playerIdx = getPlayerIdx(ra.getPlayer());
				Log.i("LocalGame", "got 'ready' ("+playerNames[playerIdx]+")");
				if (playerIdx >= 0 && !playersReady[playerIdx]) {
					playersReady[playerIdx] = true;
					playerReadyCount++;
				}

				// if all players are ready, set the com.example.degrood21.mexican_train_dominoes.game stage to "during com.example.degrood21.mexican_train_dominoes.game", and
				// send each player the initial state
				if (playerReadyCount >= playerNames.length) {
					gameStage = GameStage.DURING_GAME;
					Log.i("LocalGame", "broadcasting initial state");
					// send each player the initial state of the com.example.degrood21.mexican_train_dominoes.game
					sendAllUpdatedState();
				}
			}
			else if (action instanceof TimerAction && gameStage == GameStage.DURING_GAME) {

				// CASE 3: it's during the com.example.degrood21.mexican_train_dominoes.game, and we get a timer action

				// Only perform the "tick" if it was our timer; otherwise, just post the message
				if (((TimerAction)action).getTimer() == myTimer) {
					this.timerTicked();
				}
				else {
					this.checkAndHandleAction(action);
				}
			}
			else if (action instanceof GameAction && gameStage == GameStage.DURING_GAME) {

				// CASE 4: it's during the com.example.degrood21.mexican_train_dominoes.game, and we get an action from a player
				this.checkAndHandleAction(action);
			}
			else if (action instanceof GameOverAckAction && gameStage == GameStage.GAME_OVER) {

				// CASE 5: the com.example.degrood21.mexican_train_dominoes.game is over, and we are waiting for each player to
				// acknowledge this
				int playerIdx = getPlayerIdx(action.getPlayer());
				if (playerIdx >= 0 && !playersFinished[playerIdx]) {
					playersFinished[playerIdx] = true;
					playerFinishedCount++;
				}
			}
		}
	}
	
	/**
	 * Handles an action that is sent to the com.example.degrood21.mexican_train_dominoes.game, checking to ensure
	 * checkIfGameOver player is allowed to move, that the move is legal, etc.
	 *
	 * @param action
	 * 			the action that was sent
	 */
	private final void checkAndHandleAction(GameAction action) {
		
		// get the player and player ID
		GamePlayer player = action.getPlayer();
		int playerId = getPlayerIdx(player);
		
		// if the player is NOT a player who is presently allowed to
		// move, send the player a message
		if (!canMove(playerId)) {;
			player.sendInfo(new NotYourTurnInfo());
			return;
		}

		// attempt to make the move; if the move was not a legal one,
		// send the player a message to that effect
		if (!makeMove(action)) {
			player.sendInfo(new IllegalMoveInfo());
			return;
		}

		// The move was a legal one, so presumably the state of the com.example.degrood21.mexican_train_dominoes.game was
		// changed. Send all players the updated state. 
		sendAllUpdatedState();

		// determine whether there is a winner; if so, finish up the round
		String roundOverMsg = checkIfRoundOver();
		if (roundOverMsg != null){
			finishUpRound(roundOverMsg);
		}
		
		// determine whether there is a winner; if so, finish up the com.example.degrood21.mexican_train_dominoes.game
		String overMsg = checkIfGameOver();
		if (overMsg != null) {
			finishUpGame(overMsg);
		}

	}

	/**
	 * Tell whether the given player is allowed to make a move at the
	 * present point in the com.example.degrood21.mexican_train_dominoes.game.
	 * 
	 * @param playerIdx
	 * 		the player's player-number (ID)
	 * @return
	 * 		true iff the player is allowed to move
	 */
	protected abstract boolean canMove(int playerIdx);
	
	/**
	 * Check if the com.example.degrood21.mexican_train_dominoes.game is over. It is over, return a string that tells
	 * who the winner(s), if any, are. If the com.example.degrood21.mexican_train_dominoes.game is not over, return null;
	 * 
	 * @return
	 * 			a message that tells who has won the com.example.degrood21.mexican_train_dominoes.game, or null if the
	 * 			com.example.degrood21.mexican_train_dominoes.game is not over
	 */
	protected abstract String checkIfGameOver();

	protected  abstract String checkIfRoundOver();

	/**
	 * Finishes up the com.example.degrood21.mexican_train_dominoes.game
	 * 
	 * @param msg
	 * 			The message that tells who, if anyone, won the com.example.degrood21.mexican_train_dominoes.game
	 */
	private final void finishUpGame(String msg) {
		
		// set the com.example.degrood21.mexican_train_dominoes.game-stage to "over"
		gameStage = GameStage.GAME_OVER;
		
		// set up the array and count so that we can keep track of
		// whether everyone has replied
		playersFinished = new boolean[players.length];
		playerFinishedCount = 0;
		
		// send all players a "com.example.degrood21.mexican_train_dominoes.game over" message
		for (GamePlayer p : players) {
			p.sendInfo(new GameOverInfo(msg));
		}
	}

	private final void finishUpRound(String msg){

		for (GamePlayer p : players) {
			p.sendInfo(new GameOverInfo(msg));
		}

	}
	
	/**
	 * Makes a move on behalf of a player.
	 * 
	 * @param action
	 * 			The move that the player has sent to the com.example.degrood21.mexican_train_dominoes.game
	 * @return
	 * 			Tells whether the move was a legal one.
	 */
	protected abstract boolean makeMove(GameAction action);
	
	/**
	 * sends a given action to the Game object  
	 * 
	 * @param action
	 *            the action to send
	 */
	public final void sendAction(GameAction action) {
		// wait until handler is set
		while (myHandler == null) Thread.yield();
		
		// package the action into a message and send it to the handler
		Message msg = new Message();
		msg.obj = action;
		myHandler.dispatchMessage(msg);
	}
	
	/**
	 * sends a timer action to the com.example.degrood21.mexican_train_dominoes.game
	 */
	public final void tick(GameTimer timer) {
		sendAction(new TimerAction(timer));
	}
	
	/**
	 * Invoked whenever the com.example.degrood21.mexican_train_dominoes.game's timer has ticked. It is expected
	 * that this will be overridden in many games.
	 */
	protected void timerTicked() {
		// default behavior is to do nothing
	}
	
	// an enum-class that itemizes the com.example.degrood21.mexican_train_dominoes.game stages
	private static enum GameStage {
		BEFORE_GAME, WAITING_FOR_NAMES, WAITING_FOR_READY, DURING_GAME, GAME_OVER
	}
	
	// a handler class for the com.example.degrood21.mexican_train_dominoes.game's thread
	private static class MyHandler extends Handler {
		// the com.example.degrood21.mexican_train_dominoes.game
		private LocalGame game;
		
		// constructor; parameter is expected to be this com.example.degrood21.mexican_train_dominoes.game
		public MyHandler(LocalGame game) {
			this.game = game;
		}
		
		// callback when message is received; invoke the 'gameReceived' message
		public void handleMessage(Message msg) {
			game.receiveMessage(msg);
		}
	}

}// class LocalGame
