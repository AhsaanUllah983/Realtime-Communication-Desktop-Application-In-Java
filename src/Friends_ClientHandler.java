
	import java.io.DataInputStream;
	import java.io.DataOutputStream;
	import java.io.IOException;
	import java.net.Socket;
import java.util.ArrayList;


public class Friends_ClientHandler extends Thread {

	static int count = 2;
	static ArrayList<Socket> Sockets = new ArrayList<Socket>();
	private DataInputStream in; 
	private DataOutputStream out; 	
	private Socket socket; 

	// Constructor 
	    public Friends_ClientHandler(Socket socket, DataInputStream in)  
	    {
	    	this.socket = socket; 
	        this.in = in; 
	        Sockets.add(socket);
	    	count +=0;
	     } 
	  
	    public synchronized void run()  
	    {
	    	String line = "";
	    	
	    	for (int i=0;i<Sockets.size();i++)
	    	{
	    		System.out.println(Sockets.get(i));	    		
	    	}
	    	
	       while (true)  
	        {
	    	   if (count>=2)
	    	   {
	    		   while (!line.equals("Over")) 
	    			{
	    				try
	    				{
	    					line = in.readUTF();
	    					System.out.println(line); 	    			
	    					
	    					//------------------------
	    					
	    					if(socket.getPort()==Sockets.get(0).getPort())
	    					{
	    						DataOutputStream Out_1 = new DataOutputStream(Sockets.get(1).getOutputStream());
	    						Out_1.writeUTF(line);
	    						Out_1.flush();
	    					}
	    					else if(socket.getPort()==Sockets.get(1).getPort())
	    					{
	    						DataOutputStream Out_2 = new DataOutputStream(Sockets.get(0).getOutputStream());
	    						Out_2.writeUTF(line);
	    						Out_2.flush();
	    					}
	    					else
	    					{
	    						System.out.println("No Word");
	    					}
	    					//------------------------
	    				} 
	    				catch(IOException i) 
	    				{ 
	    					System.out.println(i); 
	    				} 
	    			} 
	    		try {
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
