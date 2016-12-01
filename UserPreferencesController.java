package assignment7;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserPreferencesController {
	private String userName = "Anonymous: ";
	
	@FXML
    private Label userNameLabl;

    @FXML
    private TextField inputUserName;
    
    public String getClientUserName() {
    	String user = inputUserName.getText();
    	if (user.equals("")) {
    		return "Anonymous: "; 		   		
    	} else {
    		this.userName = inputUserName.getText();
    		return this.userName + ": ";
    	}
    }
    
    public void setClientUserName(String name) {
    	this.userName = name;
    }
    
    @FXML
    void applyUserSettings(ActionEvent event) {
    	this.userName = inputUserName.getText();
    	
    }
    
}
