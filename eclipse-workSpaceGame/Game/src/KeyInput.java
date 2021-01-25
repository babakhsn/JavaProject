import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

//	handling keyboard
//	hint:
//	W,S,D,A for movement
//	F for firing
	
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
				
			if (tempObject.getId() == ID.Player) {
				
				if (key == KeyEvent.VK_W)
					handler.setUp(true);
					handler.setTarget(1);
				if (key == KeyEvent.VK_S)
					handler.setDown(true);
					handler.setTarget(2);
				if (key == KeyEvent.VK_A)
					handler.setLeft(true);
					handler.setTarget(3);
				if (key == KeyEvent.VK_D)
					handler.setRight(true);
					handler.setTarget(4);
				if(key == KeyEvent.VK_F)
					if(handler.isUp() || handler.getTarget() == 1) {
						handler.addObject(new Bullet(tempObject.getX() + 8, tempObject.getY() + 8, ID.Bullet, handler, 0,-20));

					}else if(handler.isDown() || handler.getTarget() == 2) {
						handler.addObject(new Bullet(tempObject.getX() + 8, tempObject.getY() + 8, ID.Bullet, handler, 0,20));

					}else if(handler.isLeft() || handler.getTarget() == 3) {
						handler.addObject(new Bullet(tempObject.getX() + 8, tempObject.getY() + 8, ID.Bullet, handler, -20,0));

					}else if(handler.isRight() || handler.getTarget() == 4) {
						handler.addObject(new Bullet(tempObject.getX() + 8, tempObject.getY() + 8, ID.Bullet, handler, 20,0));

					}
				
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W)
					handler.setUp(false);
				if (key == KeyEvent.VK_S)
					handler.setDown(false);
				if (key == KeyEvent.VK_A)
					handler.setLeft(false);
				if (key == KeyEvent.VK_D)
					handler.setRight(false);
				
			}
		}

	}

}
