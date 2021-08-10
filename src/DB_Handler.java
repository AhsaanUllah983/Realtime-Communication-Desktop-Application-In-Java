
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.sql.Statement;
public class DB_Handler {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "12345";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "ap_project";
	
	/** The name of the table we are testing with */
	private final String tableName = "sometable";

	private Statement stmt;
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		System.out.println("trying to get connection!! ");
		conn = DriverManager.getConnection("jdbc:mysql://"
		+ this.serverName + ":" + this.portNumber + "/" 
		+ this.dbName,connectionProps);
		System.out.println(" Connection achieved!! ");
		return conn;
	}
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try 
	    {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public void MakeDB(Connection conn)
	{
		stmt = null;
		String sql = "CREATE DATABASE ap_project";
	      try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) 
	      
	    {
			e.printStackTrace();
		}
	}
	public void deleteAll(Connection conn)
	{
		Statement stmt=null;
		
			try {
				stmt=conn.createStatement();
				String sql = "delete  FROM Sometable";
			int a=	stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
	}
	public void SelectAll(Connection conn)
	{
		Statement stmt=null;
		try {
			stmt=conn.createStatement();
			String sql = "SELECT * FROM Sometable";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("hello");
			while(rs.next()){
				int id  = rs.getInt("ID");
		        
		         String name = rs.getString("NAME");
		         String food = rs.getString("food");
		         System.out.print("ID: " + id);
		         System.out.print(", name: " + name);
		         System.out.println(", food: " + food);
		         
		      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	     
	}
	
	public void run() {
		
		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("connection name is :: "+conn.getClass().getName());
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		//--------------------------------------------------------------
		
		try {
			
		String createString ="CREATE TABLE User (User_ID INTEGER(10) PRIMARY KEY AUTO_INCREMENT, User_Name varchar(30))";
		System.out.println(createString);
		this.executeUpdate(conn, createString);
		
		createString ="CREATE TABLE Friends (User_ID INTEGER(10), Friend_ID Integer(10), Friend_Name varchar(30))";		
		System.out.println(createString);
		this.executeUpdate(conn, createString);
		    	
		    
			//----------------------------------------------------------------------------------
			
	    } catch (Exception e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
	}
	
	public void Register(String U) {
		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("connection name is :: "+conn.getClass().getName());
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		//--------------------------------------------------------------
		

		// Create a table
		try {
			
			String input="INSERT INTO User (User_Name) "
					+"VALUES ('"+U+"')";
			
			System.out.println(input);
			this.executeUpdate(conn, input);
		
			
	    } 
		catch (Exception e)
		{
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
	}

	public void Add_Friend(String U, String F_Name) {
		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("connection name is :: "+conn.getClass().getName());
			System.out.println("Connected to database");
		} 
		catch (SQLException e)
		{
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		//--------------------------------------------------------------
		
		// Create a table
		
			int User_ID = 0;
			int Friend_ID = 0;
			String Friend_Name = null;
				
					try {
						stmt=conn.createStatement();
						String sql = "Select User_ID from User where User_Name = '"+U+"'";
						ResultSet rs = stmt.executeQuery(sql);
						while(rs.next())
						{
							   User_ID = rs.getInt("User_ID");
						}

						stmt=conn.createStatement();
						sql = "Select * from User where User_Name = '"+F_Name+"'";
						 rs = stmt.executeQuery(sql);
						while(rs.next())
						{
							   Friend_ID = rs.getInt("User_ID");
							   Friend_Name = rs.getString("User_Name");
							}
						
						String input="INSERT INTO Friends (User_ID,Friend_ID,Friend_Name) "
								+"VALUES ("+User_ID+","+Friend_ID+",'"+Friend_Name+"')";
						
						
						System.out.println(input);
						this.executeUpdate(conn, input);
						
						
						 input="INSERT INTO Friends (User_ID,Friend_ID,Friend_Name) "
								+"VALUES ("+Friend_ID+","+User_ID+",'"+U+"')";
						
						 System.out.println(input);
						 this.executeUpdate(conn, input);
						
					
			//----------------------------------------------------------------------------------
			
	    } catch (Exception e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
	}
	public boolean Confirm (Socket S,DataInputStream in)
	{
		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("connection name is :: "+conn.getClass().getName());
			System.out.println("Connected to database");
		} 
		catch (SQLException e)
		{
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return false;
		}
		//--------------------------------------------------------------
		
		// Create a table
		
			String U = null;
			int User_ID = 0;
			int Friend_ID = 0;
			String Friend_Name = null;
				
					try {
						
    					//--------------------------------------
    					String line = in.readUTF();
    					
    					String User_Name;
    					
						boolean check = true;
    					StringTokenizer st1 = new StringTokenizer(line, ":"); 
    				        while (st1.hasMoreTokens())
    				        {
    				        
    				        		User_Name = st1.nextToken();
    				        		
    				        		while (st1.hasMoreTokens())
    				        		{
    				        			User_Name = st1.nextToken();
				        				System.out.println("Tokenized User_Name ::"+U+"::");

    				        			
    				        			StringTokenizer st2 = new StringTokenizer (User_Name, " ");
    				        				
    				        			while(st2.hasMoreTokens())
    				        			{
    				        				U=st2.nextToken();
    				        				System.out.println("Tokenized User_Name ::"+U+"::");

    				        				break;
    				        			}					
    				        			break;
    				        		}
    				        		
    				        	}	
    				        		
    				        	//------------------
    				        	
    				        	 	
    				        
						//-------------------

						boolean bool = true;
						stmt=conn.createStatement();
						String sql = "Select User_Name from User where User_Name = '"+U+"'";
						ResultSet rs = stmt.executeQuery(sql);
						while(rs.next())
						{
							bool = false;
							
							return true;
				//			   User_ID = rs.getInt("User_ID");
							   
						}
						if (bool)
						{
							DataOutputStream out = new DataOutputStream(S.getOutputStream());
							out.writeUTF("You Are NOT Registered In Portal");
							S.close();
						}
						
						return false;
						
			//----------------------------------------------------------------------------------
			
	    } catch (Exception e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return false;
		}

		
	}
	/*
	public void Add_Cattle(Cattle C) {
		// Connect to MySQL
		Connection conn = Establish_Connection();
		


		// Create a table
		try {
			
			String input="INSERT INTO Stock (Name, Breed, Price) "
					+"VALUES ('"+C.getName()+"','"+C.getBreed()+ "',"+C.getPrice()+")";
			System.out.println(input);
			this.executeUpdate(conn, input);
		
		    	
		    
			//----------------------------------------------------------------------------------
			
	    } catch (Exception e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
	}
	public void Remove_Cattle(Cattle C) {
		// Connect to MySQL
		Connection conn = Establish_Connection();
		
		


		// Create a table
		try {
			
			String input="Delete from Stock where Name = '"+C.getName()+"'";
			System.out.println(input);
			this.executeUpdate(conn, input);
		
		    	
		    
			//----------------------------------------------------------------------------------
			
	    } catch (Exception e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
	}
	public void View_All_Cattle() {
		// Connect to MySQL
		Connection conn = Establish_Connection();
		
		


		// Create a table
		try {
				stmt=conn.createStatement();
				String sql = "SELECT * FROM Stock";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					String Name = rs.getString("Name");        
			         String Breed = rs.getString("Breed");
			         int Price = rs.getInt("Price");
			         
			         System.out.println("\n\nName : " + Name);
			         System.out.println("Breed: " + Breed);
			         System.out.println("Price : " + Price);
			         
			      }
			
		    	
		    
			//----------------------------------------------------------------------------------
			
	    } catch (Exception e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
	}*/

	public static void main(String[] args) {
		DB_Handler app = new DB_Handler();
		

		app.run();
	
		
		app.Register("Ahsaan");
		app.Register("Uzair");
		app.Register("Arbab");

		app.Add_Friend("Uzair", "Arbab");
		app.Add_Friend("Uzair", "Ahsaan");
		app.Add_Friend("Ahsaan", "Arbab");
		
		

		
		
	}
}
