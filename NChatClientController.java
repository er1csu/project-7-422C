package assignment7;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Controller for the NChatClient.
 * 
 * @author ericsu
 *
 */
public class NChatClientController implements javafx.fxml.Initializable {
	
	private String userName = "Anonymous: ";
	
	private Color chatColor;
	
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
    
    public String getClientUserName() {
    	String user = userNameInputField.getText();
    	if (user.equals("")) {
    		return "Anonymous: "; 		   		
    	} else {
    		this.userName = userNameInputField.getText();
    		return this.userName + ": ";
    	}
    }
    
    public void setClientUserName(String name) {
    	this.userName = name;
    }
    
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
    	String messageText = this.chatInputField.getText();
    	this.chatClient.getChatWriter().println(messageText);
    	this.chatClient.getChatWriter().flush();
    	this.chatInputField.setText("");
    	this.chatInputField.requestFocus();
    }
    
    public void setStyle() {
		sendChatMessage.setStyle("-fx-background-color: rgb(14,122,254); -fx-text-fill: rgb(255,255,255);");
	}
    
    @FXML
    void launchAboutMenu(ActionEvent event) {
    	
    }

    @FXML
    void quitClient(ActionEvent event) {
    	System.exit(0);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setStyle();
	}

}
