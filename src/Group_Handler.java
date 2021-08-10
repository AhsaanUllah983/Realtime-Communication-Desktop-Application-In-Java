
	import java.io.DataInputStream;
	import java.io.DataOutputStream;
	import java.io.IOException;
	import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Group_Handler extends Thread {

	boolean check = false;
	String User_Name = null;
	static int count = 2;

	//static DB_Handler DB = new DB_Handler();
	static ArrayList<String> User = new ArrayList<String>();
	static ArrayList<Socket> Sockets = new ArrayList<Socket>();
	private DataInputStream in; 
	private DataOutputStream out; 	
	private Socket socket; 
	      
	public Group_Handler(Socket socket, DataInputStream in)  
	    { 
	    	this.socket = socket; 
	        this.in = in; 
	        Sockets.add(socket);
	    	count +=0;
	    }

		public synchronized void run()  
	    {
	    	String line = "";
	
	    	
	//    	DB.Confirm(socket, in);
	    	
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
	    					
	    					//DB.Confirm(socket, in);
	    					
	    					
	    					line = in.readUTF();
	    				
	    					/*
	    					//--------------------------------------
	    					check = true;
	    					StringTokenizer st1 = new StringTokenizer(line, ":"); 
	    				        while (st1.hasMoreTokens())
	    				        {
	    				        	if (check)
	    				        	{
	    				        		User_Name = st1.nextToken();
	    				        		
	    				        		boolean bool = true;
	    				        		
	    				        		for (int i=0; i <User.size();i++)
	    				        		{
	    				        			if(User_Name.equals(User.get(i)))
	    				        			{
	    				        				bool = false;
	    				        			}
	    				        		}
	    				        		if (bool)
	    				        		{	
	    				        			User.add(User_Name);
	    				        			
	    				        			System.out.println(User_Name+"Connected Updated In DB");
	    				        			
	    				        		}
	    				        	//------------------
	    				        		check = false;
	    				        	}
	    				        	else
	    				        	{
	    				        		line = User_Name +":"+ st1.nextToken();
	    				        	} 	
	    				        }*/
	    				        
	    					System.out.println(line);
	    					
	    					//------------------------
	    					
	//    					System.out.println("Sending Messages To : ");
	    					
	    					for (int i=0;i<Sockets.size();i++)
	    					{
	    						if(socket.getPort()!=Sockets.get(i).getPort())
	    						{
	  //  							System.out.println(Sockets.get(i).getPort());
	    							
		    						DataOutputStream Out_1 = new DataOutputStream(Sockets.get(i).getOutputStream());
		    						Out_1.writeUTF(line);
		    						Out_1.flush();
		    					}		
	    					}
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
