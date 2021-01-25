public class Main {
	
//	online mode
	
	public static void main(String[] args) {
		Game game = new Game();
//		JOptionPane.showConfirmDialog(new Game(game), "Do you want to start the second game?");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Game(game);
	}

}
