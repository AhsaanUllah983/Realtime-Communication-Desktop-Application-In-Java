
public class User {
	int User_ID;
	String Name ;
	int Port_No;

	public User (int ID,String Name,int Port)
	{
		this.Name=Name;
		this.User_ID=ID;
		this.Port_No=Port;
	}
	public String getName()
	{
		return this.Name;		
	}
	public int getID()
	{
		return this.User_ID;
	}
	public int getPort()
	{
		return this.Port_No;	
	}
	

}
