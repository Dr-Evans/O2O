package com.example.orangestooranges;

import android.os.Parcel;
import android.os.Parcelable;

public class CardBlue extends Card implements Parcelable {
	
	//default constructor
	CardBlue() {
		super();
	}
	
	//calls Card class superconstructor
	CardBlue(int _card_ID, String _ctopic, String _cdes) {
		super(_card_ID, _ctopic, _cdes);
	}
	
	//create Orange from parcel
	CardBlue(Parcel in) {
		card_ID = in.readInt();
		ctopic = in.readString();
		cdes = in.readString();
	}
	
	//method invoked by parcelable
	public static final Parcelable.Creator<CardBlue> CREATOR = new Parcelable.Creator<CardBlue>() {
		public CardBlue createFromParcel(Parcel in) {
			return new CardBlue(in);
		}
		
		public CardBlue[] newArray(int size) {
			return new CardBlue[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(card_ID);
		dest.writeString(ctopic);
		dest.writeString(cdes);
	}
	
}
