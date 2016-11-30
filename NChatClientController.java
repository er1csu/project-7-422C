package assignment7;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Controller for the NChatClient.
 * @author ericsu
 *
 */
public class NChatClientController implements javafx.fxml.Initializable {
	private NChatClient chatClient;
	
    @FXML
    private TextField chatInputField;

    @FXML
    private Button sendChatMessage;

    @FXML
    private TextArea mainChatDisplay;
    
    @FXML
    private TextField userNameInputField;
    
    public void setChatClient(NChatClient client) {
    	this.chatClient = client; 
    }
    
    public void sendMessage() {
    	System.out.println("test");
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		
	}

}
