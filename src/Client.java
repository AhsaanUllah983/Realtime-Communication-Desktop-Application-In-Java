import java.net.*;
import java.util.Scanner;
import java.io.*; 

public class Client 
{ 
	String UserName = null;
	private Socket socket = null; 
	private DataOutputStream out = null; 
	private DataInputStream in	 = null; 
	
	public Client()
	{
		System.out.println("Activating Client . . . . \nEnter User Name : ");
		Scanner Sc = new Scanner (System.in);
		this.UserName = Sc.nextLine();

	}
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
	try
		{ 
			socket = new Socket(address, port); 
			System.out.println("Connected"); 

			FileInputStream	Read_File = new FileInputStream("C:\\Users\\Ahsaa\\Desktop\\Db Lab Task.txt"); 

			byte [] File_Bytes = new byte [2002];
			Read_File.read(File_Bytes,0,File_Bytes.length);

			OutputStream Send_File =socket.getOutputStream();
			
			Send_File.write(File_Bytes,0,File_Bytes.length);
			
			System.out.println("\nFile Sent\n"); 

		} 
		catch(Exception u)
		{ 
			System.out.println(u); 
		} 
	} 
	
	public void TCP_Client_MP4_Transfer(String address, int port) throws IOException 
	{

         socket = new Socket(address,port);
         
         File transferFile = new File ("C:\\Users\\Ahsaa\\Desktop\\i170133 AP Assignment 4.zip");
         
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
		Client client = new Client();
		client.TCP_Client_Text_Transfer("localhost", 50000);
	//	client.TCP_Client_File_Transfer("192.168.1.18", 5000);
	//	client.TCP_Client_MP4_Transfer("localhost", 50000);
	} 
} 
