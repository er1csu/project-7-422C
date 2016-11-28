package clientServer;

public class ClientMain {
	public static void main (String [] args) {
		Client client = new Client();
				
		new Thread(new Runnable () {
			@Override
			public void run() {
				client.runme();
			}
		}).start();
		
	}
}
