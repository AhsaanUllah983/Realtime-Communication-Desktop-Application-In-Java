import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Text_Reciever extends Thread {
	private DataInputStream in; 
	private DataOutputStream out; 
	private Socket socket;
	    
	    public Text_Reciever(Socket socket, DataInputStream in, DataOutputStream out)  
	    { 
	        this.socket = socket; 
	        this.in = in; 
	        this.out = out;    
	        
	    }
	    public void run()  

	    { 
	    	String Text="";
	        	
	      while (true) {
	        		
	        	try {
	        		//---------------------
	        		
	        		//---------------------
	        		in = new DataInputStream (socket.getInputStream());	        	
					//System.out.println("Recieving Message : ");

	        		Text = in.readUTF();	
		        	System.out.println(Text); 
		        	
				//	System.out.println("Recieved");
					} 
	        	catch (IOException e)
	        	{
					e.printStackTrace();
				} 	
	    }
	    } 
}
