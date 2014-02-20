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
		
}
