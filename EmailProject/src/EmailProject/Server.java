package stupidUni;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static void main(String[] args) {
		System.out.println("Server started;)");
		ServerSocket server = null;
		try {
			server = new ServerSocket(3);

			while (true) {
				Socket socket = server.accept();
				MyThread th = new MyThread(socket);
				th.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	

	Client client;

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	static class MyThread extends Thread {

		Socket socket;
		DataInputStream in;
		DataOutputStream out;
//		ObjectInputStream objIn;
//		ObjectOutputStream objOut;
		
		ArrayList<Client> clients = new ArrayList<Client>();

		public MyThread(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			System.out.println("shit!!");
			
			

			try {
				String userName = null, passWord;
				System.out.println(1);
				in = new DataInputStream(this.socket.getInputStream());
				out = new DataOutputStream(this.socket.getOutputStream());
				
//			sending data to client to determine signing up or logging in
//				Signing Up
				outer:
				while(true) {
					String str = readData();
					if (str.contentEquals("Sign Up")) {
						sendData(str);
						String signUp = readData();
						String[] tmp = signUp.split("#");
						userName = tmp[0];
						passWord = tmp[1];
						
						if (Main.get(userName, passWord)) {
							System.out.println("This username is already registered!");
						} else {
							Main.post(userName, passWord);
						}
						sendData("Done");
						continue outer;
//				Logging In
					} else if (str.contentEquals("Log In")) {
						sendData(str);
						String logIn = readData();
						String[] tmp = logIn.split("#");
						userName = tmp[0];
						passWord = tmp[1];
						
						if (!Main.get(userName, passWord)) {
							System.out.println("This username is NOT registered!!");
							out.writeBoolean(false);
						} else {
							out.writeBoolean(true);
						}
						break;
					}
				}
				
//				After that we have Logged in
				String str2 = readData();
//				Checking Inbox
				if (str2.contentEquals("Inbox")) {
//					objOut.writeObject(Main.getInboxTable(userName));
					String tmpStr = readData();
					String[] tmpStr1 = tmpStr.split("#");
					if (tmpStr1[1].contentEquals("Delete")) {
						Main.deleteInboxMessage(userName, tmpStr1[0]);
//						sendData("repeat");
					} else if (tmpStr1[1].contentEquals("See Message")) {
						Main.getInboxMessage(tmpStr1[0], userName);
					}
//				Checking Sent Messages
				} else if (str2.contentEquals("Sent Messages")) {
//					objOut.writeObject(Main.getInboxTable(userName));
					String tmpStr = readData();
					String[] tmpStr1 = tmpStr.split("#");
					if (tmpStr1[1].contentEquals("Delete")) {
						Main.deleteSentMessage(userName, tmpStr1[0]);
//						sendData("repeat");
					} else if (tmpStr1[1].contentEquals("See Message")) {
						Main.getSentMessage(tmpStr1[0], userName);
					}
					
//				editing personal info
				} else if(str2.contentEquals("Edit Personal Info")){
					sendData(str2);
//				composing an email
				}else if(str2.contentEquals("Compose An Email")) {
					String tmpStr = readData();
					String[] tmpStr1 = tmpStr.split("#");
					if(Main.get(tmpStr1[0])){
						Main.saveToInboxMessages(tmpStr1[0], tmpStr1[1], tmpStr1[2]);
					}else {
						System.out.println("user does not exist");
					}
					Main.saveToSentMessages(userName, tmpStr1[1], tmpStr1[2]);
					
				}

			} catch (

			IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
//		public String read() {

//		}

		public void sendData(String data) {
			try {
				out.writeUTF(data);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		public String readData() {
			String str = null;
			try {
				str = in.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}

	}

}
