import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GameClient extends Thread {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
//    private Handler handler;

	public GameClient(Game game, String ipAddress) {
		this.game = game;
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
//			receiving datapacket from server
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
//			parsing the data that has been sent from the server
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
	
//	parsing the data based on the packet
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
					+ ((Packet1Login) packet).getUsername() + " has joined the game...");
			player = new PlayerMP(100, 100, ID.Player, ((Packet1Login) packet).getUsername(), address, port);
			game.handler.addObject(player);
			break;
		case DISCONNECT:
			packet = new Packet2Diconnect(data);
			System.out.println("[" + address.getHostAddress() + ":" + port + "] "
					+ ((Packet2Diconnect) packet).getUsername() + " has left the world...");
			game.handler.removePlayerMP(((Packet2Diconnect) packet).getUsername());
			break;
		case MOVE:
			packet = new Packet3Move(data);
			handleMove((Packet3Move) packet);
			break;
		}
	}

//	handling the movement of different players
	private void handleMove(Packet3Move packet) {
		this.game.handler.movePlayer(packet.getUsername(), packet.getX(), packet.getY());
	}
	
//	sending data to server with datagrampacket
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
