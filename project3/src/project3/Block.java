package project3;

import java.util.ArrayList;

public class Block {
	private Dorm dorm;
	private int blockNum, numFloors;
	ArrayList<Room> roomArray = new ArrayList<Room>();

	public Block(Dorm dorm, int blockNum) {
		super();
		this.dorm = dorm;
		this.blockNum = blockNum;
		dorm.block.add(this);
	}
	
	@Override
	public String toString() {
		return "Block [dorm=" + dorm + ", blockNum=" + blockNum + "]";
	}

	

	public Dorm getDorm() {
		return dorm;
	}

	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
		this.dorm.block.add(this);
	}

	public void setBlockNum(int blockNum) {
		this.blockNum = blockNum;
	}

	public int getBlockNum() {
		return blockNum;
	}

	public void setNumFloors(int numFloors) {
		this.numFloors = numFloors;
	}

	public int getNumFloors() {
		return numFloors;
	}

//	public ArrayList<Room> getRoomArray() {
//		return roomArray;
//	}
//	public void setRoomArray(ArrayList<Room> roomArray) {
//		this.roomArray = roomArray;
//	}

}
