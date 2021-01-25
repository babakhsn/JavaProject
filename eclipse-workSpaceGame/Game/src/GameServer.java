import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class GameServer extends Thread {

	private DatagramSocket socket;
	private Game game;
	private ArrayList<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
//    private Handler handler;

	public GameServer(Game game) {
		this.game = game;
		try {
			this.socket = new DatagramSocket(1331);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
//			parsing the data that comes from client
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
//	parsing the packet and finding out what port we should use
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		Packet.PacketTypes type = Packet.lookupPacket(message.substring(0, 1));
		Packet packet = null;
		PlayerMP player = null;
		switch (type) {
		default:
	    case INVALID:
	            break;
		case LOGIN:
			packet = new Packet1Login(data);
			System.out.println("[" + address.getHostAddress() + ":" + port + "] "
					+ ((Packet1Login) packet).getUsername() + " has connected...");
			player = new PlayerMP(100, 100, ID.Player, ((Packet1Login) packet).getUsername(), address, port);
			this.addConnection(player, (Packet1Login) packet);
			break;

		case MOVE:
			packet = new Packet3Move(data);
			this.handleMove(((Packet3Move) packet));
			break;
		case DISCONNECT:
			packet = new Packet2Diconnect(data);
			System.out.println("[" + address.getHostAddress() + ":" + port + "] "
					+ ((Packet2Diconnect) packet).getUsername() + " has left...");
			this.removeConnection((Packet2Diconnect) packet);
			break;
		}
	}
//	removing the connection of a player 
	private void removeConnection(Packet2Diconnect packet) {
		this.connectedPlayers.remove(getPlayerMPIndex(packet.getUsername()));
		packet.writeData(this);
	}

//	finding the index of a player in arraylist by their username
	public int getPlayerMPIndex(String username) {
		int index = 0;
		for (PlayerMP player : this.connectedPlayers) {
			if (player.getUserName().contentEquals(username)) {
				break;
			}
			index++;
		}
		return index;
	}

//	finding the object of a player in arraylist by their username
	public PlayerMP getPlayerMP(String username) {
		for (PlayerMP player : this.connectedPlayers) {
			if (player.getUserName().contentEquals(username)) {
				return player;
			}
		}
		return null;
	}

//	making a new connection for a new player if it has not been in the arraylist
	public void addConnection(PlayerMP player, Packet1Login packet) {
		boolean alreadyConnected = false;
		for (PlayerMP p : this.connectedPlayers) {
			if (player.getUserName().contentEquals(p.getUserName())) {
				if (p.ipAddress == null) {
					p.ipAddress = player.ipAddress;
				}
				if (p.port == -1) {
					p.port = player.port;
				}
				alreadyConnected = true;
			} else {
				sendData(packet.getData(), p.ipAddress, p.port);
				packet = new Packet1Login(p.getUserName());
				sendData(packet.getData(), player.ipAddress, player.port);
			}
		}
		if (!alreadyConnected) {
			this.connectedPlayers.add(player);
		}

	}
	
//	sending datagrampacket to client
	public void sendData(byte[] data, InetAddress ipAddress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	sending data of all of the players to all the clients
	public void sendDataToAllClients(byte[] data) {
		for (PlayerMP p : connectedPlayers) {
			sendData(data, p.ipAddress, p.port);
		}

	}
	
//	handling the movement of players based on the packets tha have been sent
	private void handleMove(Packet3Move packet) {
		if (getPlayerMP(packet.getUsername()) != null) {
			int index = getPlayerMPIndex(packet.getUsername());
			this.connectedPlayers.get(index).x = packet.getX();
			this.connectedPlayers.get(index).y = packet.getY();

			packet.writeData(this);

		}

	}
}
