package project3;

public class Main {

	public static void main(String[] args) {
		Dorm kalekirikhan = new Dorm("dorm", "orgy");
		Block koskale = new Block(kalekirikhan, 1);
		Block tokhmsag = new Block(kalekirikhan, 2);
		Block nahvi = new Block(kalekirikhan, 3);
		
		Dorm d = new Dorm("dd", "male");
		Block b = new Block(d, 1);
//		System.out.println(d.block.get(0));
		for(int i = 0; i < kalekirikhan.block.size(); i++) {
			System.out.println(kalekirikhan.block.get(i));
		}
		Room k1 = new Room(1, koskale);
		Room k2 = new Room(2, koskale);
		Room T1 = new Room(1, tokhmsag);
		Room T2 = new Room(2, tokhmsag);
		Room N1 = new Room(1, nahvi);
		Room N2 = new Room(2, nahvi);
		for(int i = 0; i < koskale.roomArray.size(); i++) {
			System.out.println(koskale.roomArray.get(i));
		}

		
	}

}
