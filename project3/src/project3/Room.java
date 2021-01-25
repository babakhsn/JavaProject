package project3;

import java.util.ArrayList;

public class Room {
	private int numRoom, floor, roomCap;
	ArrayList<Students> roomMem = new ArrayList<Students>(); //members of the room
	private long roomRent;
	private Block block;
	private Students roomMGR; //room manager
	
	public Room(int numRoom, Block block) {
		super();
		this.numRoom = numRoom;
		this.block = block;
		block.roomArray.add(this);
	}
	
	public Block getBlock() {
		return block;
	}
	public void setBlock(Block block) {
		this.block = block;
	}
	public Students getRoomMGR() {
		return roomMGR;
	}
	public void setRoomMGR(Students roomMGR) {
		this.roomMGR = roomMGR;
	}
	public void setNumRoom(int numRoom) {
		this.numRoom = numRoom;
	}
	public int getNumRoom() {
		return numRoom;
	}
	public void setRoomCap(int roomCap) {
		this.roomCap = roomCap;
	}
	public int getRoomCap() {
		return roomCap;
	}


//	public ArrayList<Students> getRoomMem() {
//		return roomMem;
//	}
//	public void setRoomMem(ArrayList<Students> roomMem) {
//		this.roomMem = roomMem;
//	}
	public void setRoomRent(long roomRent) {
		this.roomRent = roomRent;
	}
	public long getRoomRent() {
		return roomRent;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getFloor() {
		return floor;
	}
	@Override
	public String toString() {
		return "Room [numRoom=" + numRoom + ", block=" + block + "]";
	}
	
	
}
