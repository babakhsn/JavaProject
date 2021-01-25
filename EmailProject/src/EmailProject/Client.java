package stupidUni;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

final class Client {

	Socket socket;
	DataInputStream in;
//	ObjectInputStream objIn;
//	ObjectOutputStream objOut;
	DataOutputStream out;
	private String name, gender, userName, passWord;
	private int dateOfBirth, number;
	private ArrayList<Message> sentMessages = new ArrayList<Message>();
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	private ArrayList<Message> inboxMessages = new ArrayList<Message>();

	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public ArrayList<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(ArrayList<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public ArrayList<Message> getInboxMessages() {
		return inboxMessages;
	}

	public void setInboxMessages(ArrayList<Message> inboxMessages) {
		this.inboxMessages = inboxMessages;
	}

	public Client() {
		try {
//			System.out.println("Client Created2");
			socket = new Socket("127.0.0.1", 3);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
//			objIn = new ObjectInputStream(socket.getInputStream());
//			objOut = new ObjectOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Client(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(int dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	
//	public Client(String userName, String passWord) throws IOException {
//		this.send(userName + "#" + passWord);
//	}

	public void send(String str) {
		try {
			out.writeUTF(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read() {
		String ans = "";
		try {
			ans = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ans;
	}

}
