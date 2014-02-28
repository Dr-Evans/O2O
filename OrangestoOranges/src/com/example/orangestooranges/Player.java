package com.example.orangestooranges;

import java.util.ArrayList;
import java.util.Random;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
	//variables
	int player_ID = 0; 
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
		orangePlayed = new CardOrange();
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
	
	//player from Parcel
	Player(Parcel in) {
		this();
		player_ID = in.readInt();
		in.readList(cardsWon, getClass().getClassLoader());
		username = in.readString();
		position = in.readInt();
		points = in.readInt();
		in.readList(hand, getClass().getClassLoader());
		isJudge = in.readByte() != 0;  //isJudge == true if byte != 0
		randCount = in.readInt();
		orangePlayed = (CardOrange)in.readParcelable(CardOrange.class.getClassLoader());
	}
	
	//method invoked by parcelable
	public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
		public Player createFromParcel(Parcel in) {
			return new Player(in);
		}
		
		public Player[] newArray(int size) {
			return new Player[size];
		}
	};
	
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
	
	public CardOrange getOrangePlayed() {
		return orangePlayed;
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
		removeOrange(randomNum);
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
	
	public String getUsername() {
		return username;
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(player_ID);
		dest.writeList(cardsWon);
		dest.writeString(username);
		dest.writeInt(position);
		dest.writeInt(points);
		dest.writeList(hand);
		dest.writeByte((byte) (isJudge ? 1 : 0)); //true = 1
		dest.writeInt(randCount); 
		dest.writeParcelable(orangePlayed, flags);
	}
	
	public void resetRound() {
		didRand = false;
		orangePlayed = null;
	}
}

