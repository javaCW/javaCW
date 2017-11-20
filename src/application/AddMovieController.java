package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddMovieController {

	@FXML
	private ImageView imageView;
	@FXML
	private TextField movieName;
	@FXML
	private TextArea movieDescription;
	@FXML
	private TextArea movieTimings;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;
	
	private final FileChooser fileChooser = new FileChooser();
	private File file;
	
	public void addMovieTransition(ActionEvent event) throws IOException {
		String movieName = this.movieName.getText();
		String movieDescription = this.movieDescription.getText();
		String movieTimings = this.movieTimings.getText();
		String filePath = this.file.getAbsolutePath();
		String startDate = this.startDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String endDate = this.endDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		FileWriter fw = new FileWriter(new File("movies.txt"), true);
		fw.write(movieName+"\n");
		fw.write(movieDescription+"\n");
		fw.write(movieTimings+"\n");
		fw.write(filePath+"\n");
		fw.write(startDate+"\n");
		fw.write(endDate+"\n");
		fw.close();
		
	}
	
	
	public void uploadImages(ActionEvent event) throws IOException {
		file = fileChooser.showOpenDialog(new Stage());
		System.out.println(file.getAbsolutePath());
		BufferedImage bf = ImageIO.read(file);
		Image image = SwingFXUtils.toFXImage(bf, null);
		
		imageView.setImage(image);
		
	}

}
