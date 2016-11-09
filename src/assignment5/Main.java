/* CRITTERS GUI Main.java
 * EE422C Project 4b submission by
 * Replace <...> with your actual data.
 * Jake Klovenski
 * jdk2595
 * 16445
 * Tiraj Parikh
 * trp589
 * 16460
 * Slip days used: <0>
 * Fall 2016
 */

package assignment5;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application{

	public static String gui = "DisplayWorldFX.fxml";
	public static String stageName = "Critters";

	public void start (Stage stage)
	{
		URL loc = getClass().getResource(gui);
		FXMLLoader loader = new FXMLLoader(loc);
		WorldControl world = new WorldControl();
		loader.setController(world);
		try
		{
			Parent root = loader.load();
			Scene scene = new Scene(root,1080,940);
			stage.setTitle(stageName);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			System.out.println("hello");
			System.out.println(e);
			System.out.println(loc);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
