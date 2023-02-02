package stupidUni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {

	static boolean flag = true;

	public static void main(String[] args) throws Exception {
		getConnection();
//        createTable();
//        post("ali", "4321");
		get("babak", "1234");
	}

	public static void saveToSentMessages(String userName, String title, String message) {
		
			Connection con;
			try {
				con = getConnection();
				PreparedStatement statement = con
						.prepareStatement("INSERT INTO "+userName+ " (SentTitle , sentMessages) VALUES ('" + title + "', '" + message + "')");
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
}
	
	public static void saveToInboxMessages(String userName, String title, String message) {
		
		Connection con;
		try {
			con = getConnection();
			PreparedStatement statement = con
					.prepareStatement("INSERT INTO "+userName+ " (InboxTitle, Inbox) VALUES ('" + title + "', '" + message + "')");
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
}

	public static void deleteSentMessage(String userName, String Title) {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("DELETE FROM " + userName + " WHERE SentTitle =" + Title);
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Function Complete.");
		}
		;
	}

	public static void deleteInboxMessage(String userName, String Title) {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("DELETE FROM " + userName + " WHERE InboxTitle =" + Title);
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Function Complete.");
		}
		;
	}

	public static String getSentMessage(String Title, String userName) {
		Connection con;
		String message = null;
		try {
			con = getConnection();
			PreparedStatement statement = con
					.prepareStatement("SELECT sentMessages FROM" + userName + " WHERE SentTitle = '" + Title + "'");

			ResultSet result = statement.executeQuery();

			result.next();

			message = result.getString("sentMessages");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
//       PreparedStatement statement = con.prepareStatement("SELECT userName,passWord FROM Email1 ORDER BY userName DESC LIMIT 1");

	}

	public static String getInboxMessage(String Title, String userName) {
		Connection con;
		String message = null;
		try {
			con = getConnection();
			PreparedStatement statement = con
					.prepareStatement("SELECT Inbox FROM" + userName + " WHERE Title = '" + Title + "'");

			ResultSet result = statement.executeQuery();

			result.next();

			message = result.getString("Inbox");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
//       PreparedStatement statement = con.prepareStatement("SELECT userName,passWord FROM Email1 ORDER BY userName DESC LIMIT 1");

	}

	private static void createInboxTable(String username) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS " + username
					+ "(InboxTitle varchar(100) NOT NULL, Inbox varchar(255) NOT NULL, SentTitle varchar(100) NOT NULL, sentMessages varchar(255) NOT NULL)");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Function Complete.");
		}
		;
	}

	public static ArrayList<String> getInboxTable(String userName) throws Exception {
		ArrayList<String> array = null;
		try {

			Connection con = getConnection();
//          PreparedStatement statement = con.prepareStatement("SELECT userName,passWord FROM Email1 ORDER BY userName DESC LIMIT 1");
			PreparedStatement statement = con.prepareStatement("SELECT InboxTitle FROM " + userName);

			ResultSet result = statement.executeQuery();

			array = new ArrayList<String>();
//            while(result.next()){
//            	String a = result.getString("first");
//                System.out.print(result.getString("userName"));
//                System.out.print(" ");
//                System.out.println(result.getString("passWord"));
//                array.add(result.getString("last"));
//            }
			while (result.next()) {
				array.add(result.getString("InboxTitle"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;

	}

	public static boolean get(String userName, String passWord) throws Exception {
		boolean flag = false;
		try {
			Connection con = getConnection();
//          PreparedStatement statement = con.prepareStatement("SELECT userName,passWord FROM Email1 ORDER BY userName DESC LIMIT 1");
			PreparedStatement statement = con
					.prepareStatement("SELECT userName,passWord FROM Email1 WHERE userName = '" + userName + "'");

			ResultSet result = statement.executeQuery();

			result.next();
			if (result.getString("passWord").contentEquals(passWord)) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}
	
	public static boolean get(String userName) throws Exception {
		boolean flag = false;
		try {
			Connection con = getConnection();
//          PreparedStatement statement = con.prepareStatement("SELECT userName,passWord FROM Email1 ORDER BY userName DESC LIMIT 1");
			PreparedStatement statement = con
					.prepareStatement("SELECT userName FROM Email1 WHERE userName = '" + userName + "'");

			ResultSet result = statement.executeQuery();

			result.next();
			if (result.getString("userName").contentEquals(userName)) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}


	public static void post(String userName, String passWord) throws Exception {
//        String var1 = "John";
//        String var2 = "Miller";
		try {
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement(
					"INSERT INTO Email1 (userName, passWord) VALUES ('" + userName + "', '" + passWord + "')");
//            PreparedStatement posted = con.prepareStatement("INSERT INTO Email1 (userName, passWord) VALUES ('"+"babak"+"', '"+"1234"+"')");

			posted.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Insert Completed.");
		}

		createInboxTable(userName);
	}

	public static void createTable() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Email1(userName varchar(255) NOT NULL, passWord varchar(255) NOT NULL, name varchar(50), PRIMARY KEY(userName))");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Function Complete.");
		}
		;

	}

	public static Connection getConnection() throws Exception {
		try {
//			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/test3";
			String username = "root";
			String password = "@#@#@#";
//			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
