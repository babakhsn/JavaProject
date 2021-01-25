
public abstract class Packet {
	
//	an abstract class for packets
	
	public static enum PacketTypes {
        LOGIN(1), DISCONNECT(2), MOVE(3), INVALID(5), BULLET(4);

        private int packetId;

        private PacketTypes(int packetId) {
            this.packetId = packetId;
        }

        public int getId() {
            return packetId;
        }
    }
	
	public byte packetId;

    public Packet(int packetId) {
        this.packetId = (byte) packetId;
    }
    
    public abstract byte[] getData();
//    writing data from client to server
    public abstract void writeData(GameClient client);
//	writing data from server to client
    public abstract void writeData(GameServer server);

//  reading the data to distinguish between the type of the packet and the data of the packet
    public String readData(byte[] data) {
        String message = new String(data).trim();
        return message.substring(1);
    }
    
//    finding out type of the packet based on the String id
    public static PacketTypes lookupPacket(String packetId) {
    	try {
    		return lookupPacket(Integer.parseInt(packetId));
    		
    	}catch(Exception e) {
    		return PacketTypes.INVALID;
    	}
    }

    public static PacketTypes lookupPacket(int id) {
        for (PacketTypes p : PacketTypes.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
       return null;
    }

}
