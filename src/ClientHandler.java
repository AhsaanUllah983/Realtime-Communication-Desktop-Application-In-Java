import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

class ClientHandler extends Thread  
{ 
	static ArrayList<Socket> Sockets = new ArrayList<Socket>();
	
	
    final DataInputStream in; 
    final DataOutputStream out; 
    final Socket socket; 
    static int count = 2;
     
  
    // Constructor 
    public ClientHandler(Socket socket, DataInputStream in, DataOutputStream out)  
    { 
        this.socket = socket; 
        this.in = in; 
        this.out = out; 
        Sockets.add(socket);
        count+=0;
        
    }
    
    
  
    @Override
    public void run()  
    { 
    	System.out.println("Count Here : "+count);
    	
    	if (count==2)
    	{
        while (true)  
        { 
            try { 
  
    			String line = ""; 
    			while (!line.equals("Over")) 
    			{ 
    				try
    				{ 
    					line = in.readUTF(); 
    					System.out.println(line); 
    					//-----------------------------
    					
    					
    					
    					
    					
    					
    					
    					//-----------------------------
    					String Capital_Sentence = line.toUpperCase();	
    					out.writeUTF(Capital_Sentence);
    				} 
    				catch(IOException i) 
    				{ 
    					System.out.println(i); 
    				} 
    			} 
    			System.out.println("Closing Connection : "+socket); 
    			socket.close(); 
    			in.close(); 
            } 
            catch (IOException e)
            { 
                e.printStackTrace(); 
            } 
        }     
    }
    } 
} 
