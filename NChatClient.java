package assignment7;

import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.*;

public class NChatClient extends Application implements Observer {
	Observable observable;
	private BufferedReader reader;
	private PrintWriter writer;
	private VBox mainWindow;
	private NChatClientController chatWindowController;
	
	
	@Override
	public void update(Observable o, Object arg) {
		String message = chatWindowController.getChatInputMessage();
		writer.println(message);
		writer.flush();
		
	}

//	public void run() throws Exception {
//		setUpNetworking();
//	}
	public PrintWriter getChatWriter() {
		return this.writer;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
    	mainWindow = fxmlLoader.load(getClass().getResource("NChatClient.fxml").openStream());
        Scene scene1 = new Scene(mainWindow);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Chat");
        primaryStage.show();
		this.chatWindowController = (NChatClientController) fxmlLoader.getController();
		this.chatWindowController.setChatClient(this);
        try {
        	setUpNetworking();
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	private void setUpNetworking() throws Exception {
		@SuppressWarnings("resource")
		Socket sock = new Socket("127.0.0.1", 4242);
		InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(streamReader);
		writer = new PrintWriter(sock.getOutputStream());
		System.out.println("networking established");
		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();
	}


	public static void main(String[] args) {
		launch(args);
//		try {
//			new NChatClient().run();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	class IncomingReader implements Runnable {
		public void run() {
			String message;
			try {
				while ((message = reader.readLine()) != null) {	
					TextArea mainDisplay = chatWindowController.getMainChatDisplay();			
					mainDisplay.appendText(message + "\n");
					String result = mainDisplay.getText();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}



	
}
