import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowHandler implements WindowListener{
	
//	if online players disconnect, this class is used
	
	private final Game game;
	
	public WindowHandler(Game game){
		this.game = game;
//		this.game.Frame.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Packet2Diconnect packet = new Packet2Diconnect(this.game.player.getUserName());
		packet.writeData(this.game.socketClient);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
