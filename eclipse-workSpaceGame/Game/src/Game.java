import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JOptionPane;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private boolean isRunning = false;
	private Thread thread;
	public Handler handler;
	private BufferedImage level = null;
	public GameClient socketClient;
	public GameServer socketServer;
	public PlayerMP player;
	private Game game1;
	static Game game;

	public Game(Game game) {
		this.game1 = game;
		new Window(1024, 576, "Wizard Game", this);

		handler = new Handler();
		start();

		this.addKeyListener(new KeyInput(handler));

		BufferedImageLoader loader = new BufferedImageLoader();
//		level = loader.loadImage("res/Game.png");

		loadLevel();

	}

	public Game() {

		new Window(500, 500, "Wizard Game", this);

		handler = new Handler();
		start();

		this.addKeyListener(new KeyInput(handler));

		BufferedImageLoader loader = new BufferedImageLoader();
//		level = loader.loadImage("res/Game.png");

		loadLevel();

	}

	public static void main(String[] args) {

//		offline mode
		new Game();

	}

	private synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
//		checking if the player wants to run the server
		if (JOptionPane.showConfirmDialog(this, "Do you want to run the server?") == 0) {
			socketServer = new GameServer(this);
			socketServer.start();
		}
		socketClient = new GameClient(this, "localhost");
		socketClient.start();

	}

	private synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	Our Game Loop
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (isRunning) {
				render(); // this part is a little bit different
			}
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

//	updating every object in the game
	public void tick() {

		handler.tick();

	}

//	render everything in the game
	public synchronized void render() {

//		creating bufferstrategy for using graphics
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		//////////////////////////////// anything between these line is drawn in the
		//////////////////////////////// game

		g.setColor(Color.gray);
		g.fillRect(0, 0, 500, 500);

		handler.render(g);

		////////////////////////////////
		g.dispose();
		bs.show();

	}

	private void loadLevel() {
		int tmp1 = 0;
//		if a game1 is not empty,then the server is running
		if (this.game1 == null) {
			System.out.println(game1 == null);
			game = this;
//			creating borders of the game
			for (int xx = 0; xx < 1024; xx += 32) {
				for (int yy = 0; yy < 576; yy += 576 - 70) {
					handler.addObject(new Block(xx, yy, ID.Block));
				}
			}
			Random r = new Random();
			for (int yy = 0; yy < 576; yy += 32) {
				for (int xx = 0; xx < 1024; xx += 1024 - 47) {
					handler.addObject(new Block(xx, yy, ID.Block));
				}
			}

//			creating blocks, enemies and crates of the game randomly
			int tmp = 0;

			for (int xx = 1; xx < 29; xx += 3) {
				for (int yy = 1; yy < 13; yy++) {
					int rand_int = r.nextInt(30);
					if (rand_int < 18 && rand_int > 1) {
						handler.addObject(new Block(xx * 32, yy * 32, ID.Block));
					}
					if (socketServer == null && this.game1 == null) {
						if (rand_int > 26 && tmp < 3) {
							handler.addObject(new Enemy((xx + 1) * 32, yy * 32, ID.Enemy, handler));
							tmp++;
						}
						if (rand_int > 20 && rand_int < 25 && xx % 4 == 0) {
							handler.addObject(new Crate(xx * 32, yy * 32, ID.Crate));
						}
						if (rand_int == 0 && tmp1 != 1) {
							player = new PlayerMP(xx * 32, yy * 32, ID.Player, handler,
									JOptionPane.showInputDialog(this, "Please enter a username"), null, -1);
							tmp1 = 1;
						}
					}
				}
			}

			if (tmp < 3 && socketServer == null && this.game1 == null) {
				handler.addObject(new Enemy(800, 300, ID.Enemy, handler));
			}
		}
//		if game1 is not null, use all the blocks in that game for this game
		else {
			for (int i = 0; i < this.game1.handler.object.size(); i++) {
				GameObject obj = this.game1.handler.object.get(i);
				if (obj.getId() == ID.Block) {
					handler.addObject(new Block(obj.getX(), obj.getY(), ID.Block));
				}else if(obj.getId() == ID.Crate) {
					handler.addObject(new Crate(obj.getX(), obj.getY(), ID.Crate));
				}
			}
		}

//		making sure the player is being created!!
		if (tmp1 != 1) {
			player = new PlayerMP(100, 100, ID.Player, handler,
					JOptionPane.showInputDialog(this, "Please enter a username"), null, -1);
		}
		handler.addObject(player);

//		sending player data to server to add connection to the server
		Packet1Login loginPacket = new Packet1Login(player.getUserName());
		if (socketServer != null) {
			socketServer.addConnection(player, loginPacket);
		}
		loginPacket.writeData(socketClient);

	}

//	}

}
