
public class Packet3Move extends Packet{
	
	public Packet3Move(int packetId) {
		super(packetId);
	}

	private String username;
	private int x, y;

	public Packet3Move(byte[] data) {
		super(3);
		this.username =readData(data);
		String[] dataArray = readData(data).split(",");
		this.username = dataArray[0];
		this.x = Integer.parseInt(dataArray[1]);
		this.y = Integer.parseInt(dataArray[2]);
	    }
	
	public Packet3Move(String username, int x, int y) {
        super(3);
        this.username = username;
        this.x = x;
        this.y = y;
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
		return ("3" + this.username + "," + this.x + "," + this.y).getBytes();
	}

	public String getUsername() {
		return this.username;
	}

	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}

}
