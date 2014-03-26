package com.example.serversocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import android.os.Handler;
import android.os.Message;

public class Server {
	
	Thread t;
	ServerSocket server;
	DataDisplay dataDisplay;
	

	private static ArrayList<Integer> matchIDs;
	private static ArrayList<Integer> playerIDs;
	private static ArrayList<Integer> blues;
	private static ArrayList<Integer> oranges;
	private static ArrayList<Integer> playerPosition;
	private static int maxScore;			//This may change to an array of maxScores for each match depending of matchID.
	private static int numPlayers;			//This may change to an array of numPlayers for each match depending of matchID.
	
	//fake constructor
	public Server(){
		
		matchIDs = new ArrayList<Integer>();
		for(int i=1 ; i<4 ; i++) matchIDs.add(i);		//Arbitrary values
		
		playerIDs = new ArrayList<Integer>();
		for(int i=0 ; i<3 ; i++) playerIDs.add(i);		//Arbitrary valuesS
		
		blues = new ArrayList<Integer>();				//4 blues. blues[1:4]. Modify according to database.  
		for(int i=0;i<=4;i++) blues.add(0);
		
		oranges = new ArrayList<Integer>();				//8 oranges. Oranges[1:8]. Modify according to database.
		for(int i=0;i<=8;i++) oranges.add(0);
		
		playerPosition = new ArrayList<Integer>();	
		playerPosition.add(0);
		
		maxScore = 2;				
		numPlayers = 1;
		
		
	}
	
	//REQUEST HANDLERS
	
	private int getMatchID(int index){
		return matchIDs.get(index);
	}
	
	private int getNumberPlayers(){
		return numPlayers;
	}
	
	private int getPlayerID(int index){
		return playerIDs.get(index);
	}
	
	private int getBlue(){
		Random rand = new Random();
		int randomNum;
		do{
			randomNum = 1+rand.nextInt(4);		//Hard-coded 8. Number of total blues in deck
		}while( blues.get(randomNum) == 1 );
		
		blues.set(randomNum, 1);				//mark blue as being used (from 0 to 1)
		return randomNum;						//return index of fetched blue
	}
	
	private int getOrange(){
		Random rand = new Random();
		int randomNum;
		do{
			randomNum = 1+rand.nextInt(8);		//Hard-coded 8. Number of total oranges in deck
		}while( oranges.get(randomNum) == 1 );
		
		oranges.set(randomNum, 1);				//mark orange as being used
		return randomNum;						//return index of fetched orange
	}
	
	private int getPlayerPosition(int index){
		return playerPosition.get(0);
	}
	
	private int getMaxscore(){
		return maxScore;
	}
	
	
	
	//Event listener
	public void setEventListener(DataDisplay theDataDisplay)
	{
		dataDisplay=theDataDisplay;
	}
	
	//Server functionality
	public void startNetwork()
	{
		 //thread declaration
		 t=new Thread(new Runnable() {
			 public void run()
		     {
				//Create thread-unique streams
				ObjectOutputStream oos = null;
				ObjectInputStream ois = null;
				int result = 0;
				
				//Establish server port 
				try {	
					 //open port 
					 server=new ServerSocket(6789);
				
				} catch (IOException e) 
				{
					//port unavailable
					Message msg3= Message.obtain();
					msg3.obj="Could not listen to port: 6789";
					messageHandler.sendMessage(msg3);    
				} 
					 
				//Wait for a client to connect to port
				while (true) {
				try{
					//Wait for a client message
					Message waitMsg= Message.obtain();
					waitMsg.obj="Waiting for a Client...";
					messageHandler.sendMessage(waitMsg);
					
					
					//Is there client waiting to connect?
					Socket connectedSocket =server.accept();
						
					//reference to client's input Stream. This is data coming from client.
					ois =new ObjectInputStream(connectedSocket.getInputStream());
					String strMessage=(String)ois.readObject();
					
					
					//TAKE ACTION
					
					//First char passed by client is the command char! 
					char command = strMessage.charAt(0);
					System.out.println("command: " + command);
		
					//Rest of String is integer value 
					strMessage = strMessage.substring(1);
					int ref = 0;
					try{
					ref = Integer.parseInt(strMessage);
					
					}catch(Exception e){
						e.printStackTrace();
					}	
					
					
					switch( command ){
						case 'a': //matchIDs
							result = getMatchID(ref);
							break;
						case 'b': //numplayers
							result = getNumberPlayers();
							break;
						case 'c': //playerid
							result = getPlayerID(ref);
							break;
						case 'd': //blues
							result = getBlue();
							break;
						case 'e': //orange
							result = getOrange();
							break;
						case 'f': //position
							result = getPlayerPosition(ref);
							break;	
						case 'g': //maxscore
							result = getMaxscore();
							break;
						}
					 
					
					//reference to server's output Stream. This is data going to client.
					oos =new ObjectOutputStream(connectedSocket.getOutputStream());
					oos.writeObject(Integer.toString(result));
					     
					    
						  
					}
					catch (Exception e) 
					{
						 Message msg3= Message.obtain();
						 msg3.obj=e.getMessage();
						 messageHandler.sendMessage(msg3); 
					}finally{
						
						try{
						//close stuff
							ois.close();
							oos.close();
							oos.flush();
							//server.close();
						}catch(IOException ioe){
		            		ioe.printStackTrace();
		            	} 
					}
							 
				 }//end while			 
		      }//end run

			
		 });//end thread
		 
		 //Run worker thread in background	 
		 t.start();
	}//end startNetwork
	
	//UI thread text refresher 
	Handler messageHandler = new Handler() {
		@Override
		public void handleMessage(Message status) {
			dataDisplay.Display(status.obj.toString());
			
		}
	};

}//end class
