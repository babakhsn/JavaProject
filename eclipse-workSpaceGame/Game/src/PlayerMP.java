import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.InetAddress;

public class PlayerMP extends GameObject{
	
//	similar to wizard but for online players
	public InetAddress ipAddress;
	public int port;
	Handler handler;
	String userName;
	int hp = 100;

	public PlayerMP(int x, int y, ID id, Handler handler, String userName, InetAddress ipAddress, int port) {
		super(x, y, id);
		this.handler = handler;
		this.ipAddress = ipAddress;
		this.port = port;
		this.userName = userName;
	}
	
	public PlayerMP(int x, int y, ID id, String userName, InetAddress ipAddress, int port) {
		super(x, y, id);
		this.ipAddress = ipAddress;
		this.port = port;
		this.userName = userName;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		if(handler != null) {
			collision();

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
			
			Packet3Move packet = new Packet3Move(this.getUserName(), this.x, this.y);
			packet.writeData(Game.game.socketClient);
		}
		
			
		

	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
			}
			if(tempObject.getId() == ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					hp -= 1;
					if(hp <= 0) {
						handler.removeObject(this);
					}
				}
			
			}
			if(tempObject.getId() == ID.Crate) {
				if(getBounds().intersects(tempObject.getBounds())) {
					if(hp < 100 ) {
						hp += 20;
						handler.removeObject(tempObject);
						if(hp > 100) {
							hp = 100;
						}
					}
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

	public String getUserName() {
		return this.userName;
	}

}
