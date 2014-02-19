package com.example.orangestooranges;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	//variables
	int player_ID; 
	private ArrayList<CardBlue> cardsWon; //
	String username; //from database
	private int position; //position at "table"
	private int points; //number of rounds the player has won
	private ArrayList<CardOrange> hand;
	private boolean isJudge; //true if judge for the round
	private int randCount; //number of times consecutively randomed
	private boolean didRand;
	private CardOrange orangePlayed;
	
	//Constructors
	Player() {
		cardsWon = new ArrayList<CardBlue>();
		hand = new ArrayList<CardOrange>();
		points = 0;
	}
	
	Player(int player_ID, String username, int position, boolean isJudge) {
		this.player_ID = player_ID;
		cardsWon = new ArrayList<CardBlue>();
		this.username = username;
		this.position = position;
		points = 0;
		hand = new ArrayList<CardOrange>();
		this.isJudge = isJudge;
		randCount = 0;
		didRand = false;
	}
	
	//update points and add blue to array of blues won
	public void updatePoints(CardBlue blue) {
		if(didRand == true) {
			return;
		}
		cardsWon.add(blue);
		points++;
	}
	
	public void addOrange(CardOrange orange) {
		hand.add(orange);
	}
	
	//sets the cardPlayed to the card in hand at given index
	public void setOrangePlayed(int card_index) {
		orangePlayed = hand.get(card_index);
		this.removeOrange(card_index);
	}
	
	//returns last orange played if not judge, else null
	public CardOrange lastPlayed() {
		if(isJudge == false) {
			return orangePlayed;
		} else 
			return null;
	}
	
	//called if a card isn't selected in time; should select from 0-handSize-1; randomly selects card
	public CardOrange selectRand() {
		didRand = true;
		Random rand = new Random();
		int randomNum = rand.nextInt(hand.size());
		orangePlayed = hand.get(randomNum);
		return hand.get(randomNum);
	}		
	
	//selects an orange and removes from hand
	public void removeOrange(int card_index) {
		hand.remove(card_index);
	}
	
	//makes player judge
	public void makeJudge() {
		isJudge = true;
	}
	
	//makes player a non-judge
	public void unmakeJudge() {
		isJudge = false;
	}
	
	//relevant get and set methods
	public int getPoints() {
		return points; 
	}
	
	public int getPosition() {
		return position;
	}
	
	public ArrayList<CardOrange> getHand() {
		return hand;
	}
	
	public CardOrange getOrange(int index) {
		return hand.get(index);
	}
	
	public ArrayList<CardBlue> getCardsWon() {
		return cardsWon;
	}
	
	public boolean getJudge() {
		return isJudge;
	}
	
	public int getRandCount() {
		return randCount;
	}
	
	public void setRandCount(int randCount) {
		this.randCount = randCount;
	}
	
}
