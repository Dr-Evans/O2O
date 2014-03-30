package com.example.orangestooranges;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
import android.os.Handler;
import android.os.Message;
*/

public class Client {
	
	Socket clientSocket;
	int result;
	
	public int requestHandler(final String info) {
		
	
		Thread t=new Thread(new Runnable() {
			 
			public void run()
		       {
		  
		          try 
		           {
		        	
		        	//Establish server connection
		        	clientSocket= new Socket("127.0.0.1",6789);
					
		        	//Initialize output stream to send data to server and send
		        	ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
				    oos.writeObject( info );
		        	
			
				    		
				    //initialize input stream to fetch data from server and fetch
				    ObjectInputStream ois =new ObjectInputStream(clientSocket.getInputStream());
				    String strMessage = (String)ois.readObject();
				  
				 
				    result = Integer.parseInt(strMessage); 
				  
				     
	                //close stuff
	                oos.close();
				    ois.close();
				   } 
		           catch (Exception e) 
		           {
						e.printStackTrace();
		           }
			 		
		          	try{
			 		clientSocket.close();
			 		}catch(Exception e) 
			        {
						e.printStackTrace();
					}
		
		
		       }//end run	  
		});
		 
		//Start network request
		 t.start();
		 
		 //block until done
		 try {
			t.join();	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			 	
		 //return request 
		 return result;
		 
	}//end requestHandler

	
	
	
	//REQUEST MAKERS
	public int reqMatchID(int data){
		String info = "a"+ Integer.toString(data) ;
		return requestHandler(info);
	}
	
	public int reqNumPlayers(){
		String info = "b";
		return requestHandler(info);
	}
	
	public int reqPlayerID(int data){
		String info = "c"+ Integer.toString(data);
		return requestHandler(info);
	}
	
	public int reqBlue(){
		String info = "d";
		return requestHandler(info);
	}
	
	public int reqOrange(){
		String info = "e";
		return requestHandler(info);
	}
	
	public int reqPosition(int data){
		String info = "f"+ Integer.toString(data);
		return requestHandler(info);	
	}
	
	public int reqMaxScore(){
		String info = "g";
		return requestHandler(info);
	}
		
}
