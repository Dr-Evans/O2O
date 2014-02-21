package com.example.orangestooranges;

import android.os.Parcel;
import android.os.Parcelable;

public class CardOrange extends Card implements Parcelable {
	
	//default constructor
	CardOrange() {
		super();
	}
	
	//calls Card class superconstructor
	CardOrange(int _card_ID, String _ctopic, String _cdes) {
		super(_card_ID, _ctopic, _cdes);
	}
	
	//create Orange from parcel
	CardOrange(Parcel in) {
		card_ID = in.readInt();
		ctopic = in.readString();
		cdes = in.readString();
	}
	
	//method invoked by parcelable
	public static final Parcelable.Creator<CardOrange> CREATOR = new Parcelable.Creator<CardOrange>() {
		public CardOrange createFromParcel(Parcel in) {
			return new CardOrange(in);
		}
		
		public CardOrange[] newArray(int size) {
			return new CardOrange[size];
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
