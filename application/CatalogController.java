package application;

	import java.io.IOException;
import java.net.URL;
	import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

	public class CatalogController implements Initializable {

	    @FXML
	    private Button videoName;
	   
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	
		}
	    
	    public void view(ActionEvent event) {
	    	//videoName.getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/Video.fxml"));
			try {
				loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
	    }
}
