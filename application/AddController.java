package application;

	import java.io.File;
import java.net.URL;
	import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

	public class AddController implements Initializable {

	    @FXML
	    private Button addV;

	    @FXML
	    private Button addT;
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
		
		}
	    
	    public void fileChooser(ActionEvent event) {
		
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("(*.mp4)", "*.mp4"),
				new ExtensionFilter("(*.mkv)", "*.mkv"), new ExtensionFilter("(*.mp3)", "*.mp3"));

		File file = fc.showOpenDialog(null);

		String filePath;
		filePath = file.getAbsolutePath().toString();
		if (filePath != null) {
			//File file = fileChooser.showSaveDialog(primaryStage);
		};

		}
	}

