import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wizard extends GameObject {

	Handler handler;

	public Wizard(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		collision();
		
//		controlling the movement of player based on the keyboards
		if (handler.isUp())
			velY = -2;
		else if (!handler.isDown())
			velY = 0;

		if (handler.isDown())
			velY = 2;
		else if (!handler.isUp())
			velY = 0;

		if (handler.isRight())
			velX = 2;
		else if (!handler.isLeft())
			velX = 0;

		if (handler.isLeft())
			velX = -2;
		else if (!handler.isRight())
			velX = 0;

	}

//	checking if the player has collided with the blocks
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
			}

		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 16, 16);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
