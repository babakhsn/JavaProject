import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	
	private Handler handler;

	public Bullet(int x, int y, ID id, Handler handler, float velX, float velY) {
		super(x, y, id);
		this.handler = handler;
		
		this.velX = velX;
		this.velY = velY;
		this.setVelX(velX);
		this.setVelY(velY);
	}

	@Override
	public void tick() {
//		velY = 20;
//		velX = 20;
		
//		if(handler.isUp()){
//			velX = 0;
//			velY = -20;
//		}else if(handler.isDown()) {
//			velX = 0;
//			velY = 20;
//		}else if(handler.isLeft()) {
//			velX = -20;
//			velY = 0;
//		}else if(handler.isRight()) {
//			velX = 20;
//			velY = 0;
//		}
		
		x += velX;
		y += velY;
		
//		if bullet hits a block, remove it 
		
		for( int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, 6, 6);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 6, 6);
	}

}
