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

public class Main extends Application{

	public static String gui = "DisplayWorldFX.fxml";
	public static String stageName = "Critters";

	public void start (Stage stage)
	{
		URL loc = getClass.getResource(gui);
		FXMLLoader loader = new FXMLLoader(loc);
		CritterWorld cWorld = new CritterWorld();
		loader.setController(cWorld);

		Parent root = loader.load();
		Scene scene = new Scene(root,1080,940);
		stage.setTitle(stageName);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
