import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	int choose = 0;
	int hp = 3;
	
	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		choose = r.nextInt(10);
		
//		loop through every object
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
//			if enemy object hits a block, make it come back
			if(tempObject.getId() == ID.Block) {
				if(getBoundsBig().intersects(tempObject.getBounds())) {
					velX += -1*velX;
					velY += -1*velY;
					velX *= -4;
					velY *= -4;
				}else if(choose == 0) {
					velX = (r.nextInt(3) - 1);
					velY = (r.nextInt(3) - 1);
				}
			}
			
//			if bullet hits an enemy, remove the bullet object and make enemies life shorter
			if(tempObject.getId() == ID.Bullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
					hp -= 1;
					handler.removeObject(tempObject);
				}
				
			}
		}
//		if health point was lower than 0, remove this enemy
		if(hp <= 0) handler.removeObject(this);
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	
	public Rectangle getBoundsBig() {
		return new Rectangle(x - 8, y - 8 , 32, 32);
	}

}
