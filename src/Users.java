import java.net.Socket;


	public class Users {

	public	Socket Sockets;

	public	String User_Name;

	public Users (Socket S, String U)
	{
		this.Sockets=S;
		this.User_Name=U;
	}

}
