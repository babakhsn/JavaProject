package project3;

import java.util.ArrayList;

public class Dorm {

	private String name, type; // type can be enum
	private Manager manager;
	private int numBlock, numMembers;
	ArrayList<Block> block = new ArrayList<Block>();
	
	public Dorm(String name, String type){
		this.name = name;
		this.type = type;
	}
	
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
//	public ArrayList<Block> getBlock() {
//		return block;
//	}
//	public void setBlock(ArrayList<Block> block) {
//		this.block = block;
//	}
	
	
	
	public void managerVar (String Name, String passWord) {
		manager = new Manager(Name, passWord);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public void setNumMembers(int numMembers) {
		this.numMembers = numMembers;
	}
	public int getNumMembers() {
		return numMembers;
	}
	public void setNumBlock(int numBlock) {
		this.numBlock = numBlock;
	}
	public int getNumBlock() {
		return numBlock;
	}
//	public ArrayList<Block> getMemArray() {
//		return memArray;
//	}
//	public void setMemArray(ArrayList<Block> memArray) {
//		this.memArray = memArray;
//	}
	@Override
	public String toString() {
		return "Dorm [name=" + name + ", type=" + type + "]";
	}
	
//	public static void main(String[] args) {
//		Dorm dorm = new Dorm("babak", "male");
//		dorm.managerVar("babak", "fuckyou");
//		
//	}
	



}
