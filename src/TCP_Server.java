// A Java program for a Server 
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*; 

public class TCP_Server 
{ 
	//initialize socket and input stream 
	private Socket socket = null; 
	private ServerSocket server = null; 
	private DataInputStream in	 = null; 
	private DataOutputStream out	 = null; 
	String Capital_Sentence = null;

	// constructor with port 
	public void TCP_Server_Text_Reciever(int port) 
	{ 
		// starts server and waits for a connection 
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Server started"); 


			socket = server.accept(); 
			System.out.println("Client accepted : "+socket); 

			// takes input from the client socket 
			in = new DataInputStream( 
				new BufferedInputStream(socket.getInputStream()));
			
			// sends output to the Client 
				out = new DataOutputStream(socket.getOutputStream()); 

			String line = ""; 

			// reads message from client until "Over" is sent 
			while (!line.equals("Over")) 
			{ 
				try
				{ 
					line = in.readUTF(); 
					System.out.println(line); 
					
					Capital_Sentence = line.toUpperCase();

			//		Scanner SC = new Scanner (System.in);
			//		Capital_Sentence=SC.nextLine();
					
					out.writeUTF(Capital_Sentence);
				} 
				catch(IOException i) 
				{ 
					System.out.println(i); 
				} 
			} 
			System.out.println("Closing connection"); 

			// close connection 
			socket.close(); 
			in.close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 
	public void TCP_Server_File_Reciever(int port) 
	{ 
		// starts server and waits for a connection 
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Server started"); 


			socket = server.accept(); 
			System.out.println("Client accepted"); 

			// takes file from the client so 
			InputStream Recieving_File =socket.getInputStream();
			
			// Converting Into Bytes
		
			byte [] File_Bytes = new byte [2002];
			Recieving_File.read(File_Bytes,0,File_Bytes.length);
			
			
			// SavingFile To the Directory 
				FileOutputStream Saving_File = new FileOutputStream("C:\\Users\\Ahsaa\\Desktop\\123\\Uzair.txt"); 
				Saving_File.write(File_Bytes,0,File_Bytes.length);

				System.out.println("File Saved In Directory"); 

			// close connection 
		//	socket.close(); 
		//	in.close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 
	public void TCP_Server_MP4_Reciever(int port) throws IOException
	{
	
		int filesize=1022386;     
		int bytesRead;
		int currentTot = 0;
		
        server = new ServerSocket (port);
        System.out.println("Server Started\n");

        socket = server.accept();

		System.out.println("Accepted Connection");

        
		byte [] bytearray  = new byte [filesize];
        
		InputStream Reading_File = socket.getInputStream();
        
		FileOutputStream Saving_File = new FileOutputStream("C:\\Users\\Ahsaa\\Desktop\\XML\\file.zip");
        
		BufferedOutputStream Buffered_Saver = new BufferedOutputStream(Saving_File);
        
		bytesRead = Reading_File.read(bytearray,0,bytearray.length);
        
		currentTot = bytesRead;
 
			System.out.println("Here Please . . . . . ");
		
        do  {
        	bytesRead = Reading_File.read(bytearray, currentTot, (bytearray.length-currentTot));
			//System.out.println("Out Of Loop");
        	
        	if(bytesRead >= 0)
        		{
        		currentTot += bytesRead;
    			System.out.println(currentTot);
        		}
        
        	
        	} while(bytesRead > -1);
 
        Buffered_Saver.write(bytearray, 0 , currentTot);
        Buffered_Saver.flush();
        Buffered_Saver.close();
    	
        System.out.println("File Recieved Successfully");
        socket.close();
	}
	public void Main(int port)
	{		// starts server and waits for a connection 
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Server started"); 
			
				while (true)
				{
					socket = server.accept(); 
					System.out.println("Client accepted : "+socket); 
					
					System.out.println("InetAddress : "+socket.getInetAddress()+"\nPort : "+socket.getPort()+"\nLocal Port : "+socket.getLocalPort()+"\n"); 						
					
					in = new DataInputStream (new BufferedInputStream(socket.getInputStream()));
						
					
					out = new DataOutputStream(socket.getOutputStream()); 


		
					Thread thread = new ClientHandler(socket,in,out);
					thread.start();
					
				}
		} 
		catch(IOException i) 
		{ 
			return;

		}     
	}

	
	@SuppressWarnings("resource")
	public void Friend_Chat(int port)
	{	try
		{ 		
			server = new ServerSocket(port); 
			System.out.println("Server started"); 
			
				while (true)
				{
					socket = server.accept(); 
					System.out.println("Client accepted : "+socket); 		
					System.out.println("InetAddress : "+socket.getInetAddress()+"\nPort : "+socket.getPort()+"\nLocal Port : "+socket.getLocalPort()+"\n"); 						
					
					in = new DataInputStream (new BufferedInputStream(socket.getInputStream()));
					out = new DataOutputStream(socket.getOutputStream()); 
					

						Thread thread = new Friends_ClientHandler(socket,in);
						thread.start();

					
					
				//	Thread thread = new Friends_ClientHandler(socket,in,out);
				//	thread.start();
					
				}
		} 
		catch(IOException i) 
		{ 
			return;

		} 
	}
	public void Group_Chat(int port)
	{	try
		{ 		
			server = new ServerSocket(port); 
			System.out.println("Server started"); 
			
				while (true)
				{
					socket = server.accept();
					System.out.println("Client accepted : "+socket); 		
					System.out.println("InetAddress : "+socket.getInetAddress()+"\nPort : "+socket.getPort()+"\nLocal Port : "+socket.getLocalPort()+"\n"); 						
					
					in = new DataInputStream (new BufferedInputStream(socket.getInputStream()));
					out = new DataOutputStream(socket.getOutputStream()); 
					

						Thread thread = new Group_Handler(socket,in);
						thread.start();
						
				}
		} 
		catch(IOException i) 
		{ 
			return;

		} 
	}	
	
	
	
	

	
	public static void main(String args[]) throws IOException 
	{ 
		TCP_Server server = new TCP_Server();
		//	server.TCP_Server_Text_Reciever(50000);
		//server.TCP_Server_File_Reciever(5000);
		//	server.TCP_Server_MP4_Reciever(50000);
		//		server.Main(50000);
		// server.Friend_Chat(50000);
		  server.Group_Chat(50000);
		
	} 
} 
