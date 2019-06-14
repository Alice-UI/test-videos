package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class VideoController implements Initializable {

	@FXML
	private MediaView mv;
	@FXML
	private Slider volumeSilder;
	@FXML
	private Slider seekSlider;
	@FXML
	private MediaPlayer mp;
	private Media me;
	private String filePath;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void play(ActionEvent event) {
		mp.play();
		mp.setRate(1);
	}

	public void pause(ActionEvent event) {
		mp.pause();
	}

	public void fast(ActionEvent event) {
		mp.setRate(2);
	}

	public void slow(ActionEvent event) {
		mp.setRate(.75);
	}

	public void reload(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.play();
	}

	public void fileChooser(ActionEvent event) {
		if (mp != null) {
			mp.dispose();
		}
		File file = new File("D:\\java_projects\\video\\src\\videos\\" + "lang.mp4");
			me = new Media(file.toURI().toString());
			mp = new MediaPlayer(me);
			mv.setMediaPlayer(mp);

			System.out.println(mp.audioSpectrumIntervalProperty().getValue());
			System.out.println(mp.getAudioSpectrumNumBands());


			DoubleProperty width = mv.fitWidthProperty();
			DoubleProperty height = mv.fitHeightProperty();
			width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
			height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
			final HBox hbox = new HBox(2);
			final int bands = mp.getAudioSpectrumNumBands();
			final Rectangle[] rect = new Rectangle[bands];
			for (int i = 0; i < rect.length; i++) {
				rect[i] = new Rectangle();
				rect[i].setFill(Color.CHARTREUSE);
				hbox.getChildren().add(rect[i]);


			mp.play();

			volumeSilder.setValue(mp.getVolume() * 100);
			volumeSilder.valueProperty().addListener(new InvalidationListener() {

				@Override
				public void invalidated(Observable observable) {
					mp.setVolume(volumeSilder.getValue() / 100);

				}
			});

			mp.setOnReady(new Runnable() {

				@Override
				public void run() {

					seekSlider.setMin(0.0);
					seekSlider.setMax(mp.getTotalDuration().toSeconds());
				}
			});

			mp.currentTimeProperty().addListener(new ChangeListener<Duration>() {

				@Override
				public void changed(ObservableValue<? extends Duration> observable, Duration duration,
						Duration current) {

					seekSlider.setValue(current.toSeconds());

				}

			});

		}

		seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mp.seek(Duration.seconds(seekSlider.getValue()));

			}
		});

	}

}
