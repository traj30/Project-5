/*  WorldControl Class
 *  Jake Klovenski
 *  jdk2595
 *  <Put yo credentials here>
 *
 *
 */
package assignment5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WorldControl
{
	@FXML
	private BorderPane borderPaneController;

	@FXML
	private GridPane gridPaneController;

	@FXML
	private HBox hBoxController;

	@FXML
	private static Canvas canvas;

	@FXML
	private Button stepButtonController;

	@FXML
	private Button seedButtonController;
	
	@FXML
	private Button makeButtonController;

	@FXML
	private Button statsButtonController;

	@FXML
	private Button startButtonController;

	@FXML
	private Button stopButtonController;

	@FXML
	private Button quitButtonController;

	@FXML
	private TextField stepsTextController;

	@FXML
	private TextField seedTextController;

	@FXML
	private TextField makeTextController;

	@FXML
	private TextField  makeChoiceController;

	@FXML
	private TextField statsTextController;

	@FXML
	private Slider animationSliderController;

	@FXML
	private Label statsLabel1Controller;

	@FXML
	private Label statsLabel2Controller;

	@FXML
	private Label statsLabel3Controller;

	@FXML
	private Label statsLabel4Controller;

	public void stepButtonEvent(ActionEvent event)
	{
		int steps = 0;

		steps = Integer.parseInt(stepsTextController.getText());

		for(int i = 0; i < steps; i++)
		{
			Critter.worldTimeStep();
		}
		Critter.displayWorld();
	}

	public void makeButtonEvent(ActionEvent event)
	{
		int count = 0;
		String type;
		count = Integer.parseInt(makeTextController.getText());
		type = makeChoiceController.getText();
		try
		{
			for(int i = 0; i < count; i++)
			{
				Critter.makeCritter(type);
			}
		}
		catch(InvalidCritterException e)
		{
			System.out.println(e);
		}
	}

	public void seedButtonEvent(ActionEvent event)
	{
		int seedValue = 0;
		seedValue = Integer.parseInt(seedTextController.getText());
		Critter.setSeed(seedValue);
	}

	public boolean animation = false;
	public void startButtonEvent(ActionEvent event)
	{
		double count = 0;
		animation = true;
		count = animationSliderController.getValue();
		stepButtonController.setDisable(true);
		seedButtonController.setDisable(true);
		makeButtonController.setDisable(true);
		startButtonController.setDisable(true);
		quitButtonController.setDisable(true);
		while(animation)
		{
			for(int i = 0; i < count; i++)
			{
				Critter.worldTimeStep();
			}
			Critter.displayWorld();
		}
		stepButtonController.setDisable(false);
		seedButtonController.setDisable(false);
		makeButtonController.setDisable(false);
		startButtonController.setDisable(false);
		quitButtonController.setDisable(false);

	}

	public void statsButtonEvent(ActionEvent event)
	{
		//TODO add the stats stuff
	}

	public void stopButtonEvent(ActionEvent event)
	{
		animation = false;
	}

	public static void drawGraph()
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		int spaceW = (int)canvas.getWidth()/Params.world_width;
		for(int i = 0; i < Params.world_width; i++)
		{
			gc.strokeLine(i*spaceW, 0, i*spaceW, canvas.getHeight());
		}
		int spaceH = (int)canvas.getHeight()/Params.world_height;
		for(int i = 0; i < Params.world_height; i++)
		{
			gc.strokeLine(0,i*spaceH,canvas.getWidth(),i*spaceH);
		}
	}

	public static void clearGraphics()
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
	}

	public void quitButtonController(ActionEvent event)
	{
		System.exit(1);
	}
}
