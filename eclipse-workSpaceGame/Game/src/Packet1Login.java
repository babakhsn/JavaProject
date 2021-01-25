
public class Packet1Login extends Packet {

	public Packet1Login(int packetId) {
		super(packetId);
	}

	private String username;
//	private int x, y;

	public Packet1Login(byte[] data) {
		super(1);
		this.username = readData(data);
//	        String[] dataArray = readData(data).split(",");
//	        this.username = dataArray[0];
//	        this.x = Integer.parseInt(dataArray[1]);
//	        this.y = Integer.parseInt(dataArray[2]);
	}

	public Packet1Login(String username) {
		super(1);
		this.username = username;
//        this.x = x;
//        this.y = y;
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData() {
		return ("1" + this.username).getBytes();
	}

	public String getUsername() {
		return this.username;
	}

}
