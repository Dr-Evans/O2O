package com.example.orangestooranges;

public class Card {
	//variables
	public int card_ID; //unique ID of card
	public String ctopic; //card topic and main subject
	public String cdes; //extended card description
	
	Card() {}
	
	Card(int _card_ID, String _ctopic, String _cdes) {
		this.card_ID = _card_ID;
		this.ctopic = _ctopic;
		this.cdes = _cdes;
	}
	
	public int getID() {
		return this.card_ID;
	}
	
	public String getCtopic() {
		return this.ctopic;
	}
	
	public String getCdes() {
		return this.cdes;
	}
	
}
