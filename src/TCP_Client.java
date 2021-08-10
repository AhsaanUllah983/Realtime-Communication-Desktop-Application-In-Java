// A Java program for a Client 
import java.net.*;
import java.util.Scanner;

///import com.sun.security.ntlm.Client;

import java.io.*; 

public class TCP_Client 
{ 
	// initialize socket and input output streams 
//Text Streams
	String UserName = null;
	private Socket socket		 = null; 
	private DataOutputStream out	 = null; 
	private DataInputStream in	 = null; 
	
//	File Streams

	public TCP_Client()
	{
		System.out.println("Activating Client . . . . \nEnter User Name : ");
		Scanner Sc = new Scanner (System.in);
		this.UserName = Sc.nextLine();
		
		
		
	}
	
	// constructor to put IP address and port 
	public void TCP_Client_Text_Transfer(String address, int port) 
	{ 

		try
		{ 
			socket = new Socket(address, port); 
			System.out.println("Connected To Server"); 
			
			out = new DataOutputStream(socket.getOutputStream());			
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			
//			System.out.println("Streams Created By Client");
						
			Thread Sender = new Text_Sender(socket,in,out,UserName);	//User_Name
			Sender.start();
//			System.out.println("Sender Thread Created");
			
			Thread Reciever = new Text_Reciever(socket,in,out);
			Reciever.start();
//			System.out.println("Reciever Thread Created");
		} 
		catch(Exception u)
		{ 
			System.out.println(u); 
		} 

	} 
	public void TCP_Client_File_Transfer(String address, int port) 
	{ 

		// establish a connection 
		try
		{ 
			socket = new Socket(address, port); 
			System.out.println("Connected"); 

			// takes file from PC 
			FileInputStream	Read_File = new FileInputStream("C:\\Users\\Ahsaa\\Desktop\\Db Lab Task.txt"); 

			// creating Bytes Of file
			byte [] File_Bytes = new byte [2002];
			Read_File.read(File_Bytes,0,File_Bytes.length);

			// sends File to the server
			
			OutputStream Send_File =socket.getOutputStream();
			
			Send_File.write(File_Bytes,0,File_Bytes.length);
			
			System.out.println("\nFile Sent\n"); 
			
		

		} 
		catch(Exception u)
		{ 
			System.out.println(u); 
		} 
		

		// close the connection 
		try
		{ 
		//	input.close(); 
		//	out.close(); 
		//	socket.close(); 
		} 
		catch(Exception i) 
		{ 
			System.out.println(i); 
		} 
	} 
	
	public void TCP_Client_MP4_Transfer(String address, int port) throws IOException 
	{

         socket = new Socket(address,port);
         
         File transferFile = new File ("C:\\Users\\Ahsaa\\Desktop\\123.mp3");
         
         byte [] bytearray  = new byte [(int)transferFile.length()];
        
         FileInputStream Reading_File = new FileInputStream(transferFile);
        
         BufferedInputStream Buffered_Reader = new BufferedInputStream(Reading_File);
        
         Buffered_Reader.read(bytearray,0,bytearray.length);
        
         OutputStream Write_Stream = socket.getOutputStream();
        
         System.out.println("Sending Files...");
        
         Write_Stream.write(bytearray,0,bytearray.length);
        
         Write_Stream.flush();

         socket.close();
        
         System.out.println("File Transfer Complete");
	
	}

	public static void main(String args[]) throws IOException 
	{ 
		TCP_Client client = new TCP_Client();
		client.TCP_Client_Text_Transfer("localhost", 50000);
	//	client.TCP_Client_File_Transfer("192.168.1.18", 5000);
	//	client.TCP_Client_MP4_Transfer("192.168.1.18", 50000);
	} 
} 
