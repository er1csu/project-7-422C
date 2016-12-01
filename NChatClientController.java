package assignment7;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Controller for the NChatClient.
 * 
 * @author ericsu
 *
 */
public class NChatClientController implements javafx.fxml.Initializable {
		
	private UserPreferencesController prefController;
	private Stage prefStage;
	
	public Stage getPrefStage() {
		return this.prefStage;
	}
	
	@FXML
    private MenuItem prefMenuItem;
	
	@FXML
    void showPrefMenu(ActionEvent event) throws Exception {
		prefStage.show();
    }
	
	public UserPreferencesController getPrefCon() {
		return this.prefController;
	}
	
	@FXML
    private MenuItem quitClientMenuItem;
	
    @FXML
    private MenuItem aboutMenu;
	
	private NChatClient chatClient;
	
    @FXML
    private TextField chatInputField;

    @FXML
    private Button sendChatMessage;

    @FXML
    private TextArea mainChatDisplay;
    
    @FXML
    private TextField userNameInputField;
    
    
    
    public TextArea getMainChatDisplay() {
    	return this.mainChatDisplay;
    }
    
    public void setChatClient(NChatClient client) {
    	this.chatClient = client; 
    }
    
    public String getChatInputMessage() {
    	return this.chatInputField.getText();
    }
    
    public void sendMessage() {
    	URL resource = getClass().getResource("ding.wav");
    	AudioClip ding = new AudioClip(resource.toString());
    	ding.play();
    	Date date = new Date();
    	String userName = getPrefCon().getClientUserName();
    	String messageText = "[" + date.toString().split(" ")[3] + "] ";
    	if (this.chatInputField.getText().length() == 0) {
    		this.chatInputField.setText("<No_text_entered>");
    	}
    	messageText += userName + ": ";
    	messageText += this.chatInputField.getText();
    	this.chatClient.getChatWriter().println(messageText);
    	this.chatClient.getChatWriter().flush();
    	this.chatInputField.setText("");
    	this.chatInputField.requestFocus();
    }
    
    public void setStyle() {
		sendChatMessage.setStyle("-fx-background-color: rgb(14,122,254); -fx-text-fill: rgb(255,255,255);");
	}
	public TextField getChatInputField() {
		return this.chatInputField;
	}


    @FXML
    void launchAboutMenu(ActionEvent event) {
    	FlowPane pane = new FlowPane();
    	pane.getChildren().add(new Label("Created by Eric Su & Stephen Ma"));
    	Stage stage = new Stage();
    	stage.setTitle("About");
    	stage.setHeight(300);
    	stage.setWidth(300);
    	stage.setResizable(false);
    	Scene scene = new Scene(pane);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void quitClient(ActionEvent event) {
    	System.exit(0);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setStyle();
		FXMLLoader fxmlLoader = new FXMLLoader();
    	GridPane prefWindow = null;
		try {
			prefWindow = fxmlLoader.load(getClass().getResource("PreferencesController.fxml").openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Scene scene1 = new Scene(prefWindow);
        prefStage = new Stage();
        prefStage.setScene(scene1);
        prefStage.setTitle("Preferences");
        
        this.prefController = (UserPreferencesController) fxmlLoader.getController();
        this.prefController.parentCon = prefStage;
	}

}
