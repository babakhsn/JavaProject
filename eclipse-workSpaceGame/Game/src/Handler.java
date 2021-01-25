import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
//	this class is for handling all the objects of our game
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	private int target = 1;
	
	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	private boolean up = false, down = false, right = false, left = false;
	
//	updating every object 
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}

//	rendering every object in the game
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
//	adding and removing object
	public void addObject(GameObject tempObject) {
		object.add(tempObject);	
	}
	public void removeObject(GameObject tempObject) {
		object.remove(tempObject);
	}
	
	
	//this part is specifically used for player
////////////////////////////////////////////////////////////
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
/////////////////////////////////////////////////////
	
//this part is used for handling data on server side
////////////////////////////////////////////////////
	public void removePlayerMP(String username) {
		int index = 0;
		for(GameObject g : this.object) {
			if(g instanceof PlayerMP && ((PlayerMP) g).getUserName().contentEquals(username)) {
				break;
			}
			index++;
		}
		this.object.remove(index);
	}
	
	private int getPlayerMPIndex(String username) {
		int index = 0;
		for(GameObject g : object) {
			if(g instanceof PlayerMP && ((PlayerMP) g).getUserName().contentEquals(username)) {
				break;
			}
			index++;
		}
		return index;
	}
	public void movePlayer(String username, int x, int y) {
		int index = getPlayerMPIndex(username);
		this.object.get(index).x = x;
		this.object.get(index).y = y;
	}
//////////////////////////////////////////////////////////

}
