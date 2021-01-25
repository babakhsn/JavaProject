
public class Packet2Diconnect extends Packet{
	
	public Packet2Diconnect(int packetId) {
		super(packetId);
	}

	private String username;
//	private int x, y;

	public Packet2Diconnect(byte[] data) {
		super(2);
		this.username =readData(data);
//	        String[] dataArray = readData(data).split(",");
//	        this.username = dataArray[0];
//	        this.x = Integer.parseInt(dataArray[1]);
//	        this.y = Integer.parseInt(dataArray[2]);
	    }
	
	public Packet2Diconnect(String username) {
        super(2);
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
		return ("2" + this.username).getBytes();
	}

	public String getUsername() {
		return this.username;
	}

}
