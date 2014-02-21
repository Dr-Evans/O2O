package com.example.orangestooranges;

import java.util.ArrayList;

public class Match {
	//attributes
	ArrayList<Player> players; //array of players
	private int numPlayers; //number of players
	int round; //current round
	int maxScore; //max score needed to win
	boolean isOver; //false until, for whatever reason, game is over
	int isJudge; //index of judge
	ArrayList<CardOrange> inPlay; //arrayList of oranges in play for round
	int match_ID; //ID unique to this match
	CardBlue roundBlue;
	int winnerIndex;
	
	//constructors
	Match() {} //default
	
	Match(int numPlayers, int maxScore, int match_ID) {
		this.numPlayers = numPlayers;
		this.maxScore = maxScore;
		this.match_ID = match_ID;
		round = 0;
		isOver = false;
		isJudge = 0;
		roundBlue = new CardBlue();
		players = new ArrayList<Player>();
		inPlay = new ArrayList<CardOrange>();
		for(int i = 0; i < numPlayers; i++) {
			inPlay.add(null);
		}
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public void setInPlay(CardOrange yourOrange, int playerIndex) {
		inPlay.set(playerIndex, yourOrange);
	}
	
	public void setRoundBlue(CardBlue roundBlue) {
		this.roundBlue = roundBlue;
	}
	
	public void addPlayer(Player player) {
		if(players.size() < numPlayers) 
			players.add(player);
	}
	
	public CardBlue getRoundBlue() {
		return this.roundBlue;
	}
	
	public Player getPlayer(int index) {
		return players.get(index);
	}
	
	/* will set the winner of the round; will reset to -1 at end of each round, and if judge doesn't select
	 * a winner, it will award points to every player that did not random
	 */
	
	public void handWinner(CardBlue blueWon) {
		if(winnerIndex != -1) {
			players.get(winnerIndex).updatePoints(blueWon);
		} else {
			for(int i = 0; i < numPlayers; i++) {
				players.get(i).updatePoints(blueWon);
			}
		}
	}
	
	//makes current judge a player, and 'judges' next judge
	public void makeJudge() {
		players.get(isJudge).unmakeJudge();
		if(isJudge == (numPlayers-1)) {
			isJudge = 0;
		} else
			isJudge++;
		players.get(isJudge).makeJudge();
	}
		
}
