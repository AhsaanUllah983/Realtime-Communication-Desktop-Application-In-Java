import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


	
	public class Text_Sender extends Thread {
		  private DataInputStream in; 
		  private DataOutputStream out; 
		  private Socket socket; 
		  String User_Name;
		    Scanner input;
		  
		    
		    public Text_Sender(Socket socket, DataInputStream in, DataOutputStream out,String User_Name)  
		    { 
		        this.socket = socket; 
		        this.in = in; 
		        this.out = out;
		        this.User_Name=User_Name;
		         input = new Scanner(System.in); 
		    }
		    public synchronized void run()  
		    { 
				String Over = "";
				String line = "";
				while (!Over.equals("Over")) 
				{ 
					try
					{
						
					line = User_Name+" : ";
					Over = input.nextLine();
					line = line + Over;
				
					out.writeUTF(line);
					out.flush();
	
					} 
					catch(IOException i) 
					{ 
						System.out.println(i); 
					} 
				} 
	    
		    } 
	}