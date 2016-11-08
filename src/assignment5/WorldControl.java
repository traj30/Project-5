/*  CRITTERS GUI WorldControl.java
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

import assignment5.Critter.CritterShape;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import java.io.PrintStream;

public class WorldControl
{
	@FXML
	private BorderPane borderPaneController;

	@FXML
	private GridPane gridPaneController;

	@FXML
	private HBox hBoxController;

	@FXML
	private Canvas canvasController;

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
	private TextArea statsTextAreaController;
	private PrintStream ps;

	public void initialize()
	{
		Console console = new Console(statsTextAreaController);
		ps = new PrintStream(console);
		System.setOut(ps);
		System.setErr(ps);
	}

	@FXML
	public void stepButtonEvent(ActionEvent event)
	{
		int steps = 0;
		try
		{
			steps = Integer.parseInt(stepsTextController.getText());
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e);
		}

		for(int i = 0; i < steps; i++)
		{
			Critter.worldTimeStep();
		}
		Critter.displayWorld(this);
	}

	@FXML
	public void makeButtonEvent(ActionEvent event)
	{
		int count = 0;
		String type = "";
		try
		{
			count = Integer.parseInt(makeTextController.getText());
			type = makeChoiceController.getText();
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e);
		}
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

	@FXML
	public void seedButtonEvent(ActionEvent event)
	{
		int seedValue = 0;
		try
		{
			seedValue = Integer.parseInt(seedTextController.getText());
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e);
		}
		Critter.setSeed(seedValue);
	}

	public boolean animation = false;
	@FXML
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
			Critter.displayWorld(this);
		}
		stepButtonController.setDisable(false);
		seedButtonController.setDisable(false);
		makeButtonController.setDisable(false);
		startButtonController.setDisable(false);
		quitButtonController.setDisable(false);

	}
	@FXML
	public void statsButtonEvent(ActionEvent event)
	{
		//TODO add the stats stuff
		
	}
	@FXML
	public void stopButtonEvent(ActionEvent event)
	{
		animation = false;
	}

	public void drawGraph()
	{
		System.out.println("`")
		GraphicsContext gc = canvasController.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		int spaceW = (int)canvasController.getWidth()/Params.world_width;
		for(int i = 0; i < Params.world_width; i++)
		{
			gc.strokeLine(i*spaceW, 0, i*spaceW, canvasController.getHeight());
		}
		int spaceH = (int)canvasController.getHeight()/Params.world_height;
		for(int i = 0; i < Params.world_height; i++)
		{
			gc.strokeLine(0,i*spaceH,canvasController.getWidth(),i*spaceH);
		}
2a	Q`1	}

	public void clearGraphics()
	{
		System.out.println("Clearing");
		GraphicsContext gc = canvasController.getGraphicsContext2D();
		gc.clearRect(0,0,canvasController.getWidth(),canvasController.getHeight());
		System.out.println("Done Clearing");
	}

	@FXML
	public void quitButtonController(ActionEvent event)
	{
		System.exit(1);
	}
	
	
	public void shapes(int x, int y, CritterShape c) {
		GraphicsContext gc = canvasController.getGraphicsContext2D();
		gc.clearRect(0,0,canvasController.getWidth(),canvasController.getHeight());
		
		if(c == Critter.CritterShape.SQUARE){
			 gc.fillRect(x * (canvasController.getWidth()/(Params.world_width * 2)), y * (canvasController.getHeight()/(Params.world_height)), (canvasController.getWidth()/(Params.world_width * 5)), (canvasController.getHeight())/(Params.world_height * 3));
			 gc.strokeRect(x * (canvasController.getWidth()/(Params.world_width * 2)), y * (canvasController.getHeight()/(Params.world_height)), (canvasController.getWidth()/(Params.world_width * 5)), (canvasController.getHeight())/(Params.world_height * 3));
		}
		
		else if(c == Critter.CritterShape.CIRCLE){
			 gc.fillOval(x * (canvasController.getWidth()/(Params.world_width * 2)), y * (canvasController.getHeight()/(Params.world_height)), (canvasController.getWidth()/(Params.world_width * 5)), (canvasController.getHeight())/(Params.world_height * 3));
			 gc.strokeOval(x * (canvasController.getWidth()/(Params.world_width * 2)), y * (canvasController.getHeight()/(Params.world_height)), (canvasController.getWidth()/(Params.world_width * 5)), (canvasController.getHeight())/(Params.world_height * 3));
			 }
		
		else if(c == Critter.CritterShape.TRIANGLE){
			double y_coor = y * (canvasController.getHeight()/(Params.world_height));
			double x_coor = x * (canvasController.getWidth()/(Params.world_width * 2));
			double w = (canvasController.getWidth()/(Params.world_width * 2));
			double h = (canvasController.getHeight()/(Params.world_height * 2));
			double[] x_coords = {x_coor, x_coor + (w/2),x_coor + w};
			double[] y_coords = {y_coor + h, y_coor,y_coor + h};
			gc.fillPolygon(x_coords, y_coords, 3);
			gc.strokePolygon(x_coords, y_coords, 3);
		}
		else if(c == Critter.CritterShape.DIAMOND){
			double y_coor = y * (canvasController.getHeight()/(Params.world_height));
			double x_coor = x * (canvasController.getWidth()/(Params.world_width * 2));
			double w = (canvasController.getWidth()/(Params.world_width * 2));
			double h = (canvasController.getHeight()/(Params.world_height * 2));
			double[] x_coords = {x_coor, x_coor + (w/2),x_coor + w, x_coor + (w/2)};
			double[] y_coords = {y_coor + (h/2), y_coor, y_coor + (h/2), y_coor + h};
			gc.fillPolygon(x_coords, y_coords, 4);
			gc.strokePolygon(x_coords, y_coords, 4);
		}
		else if(c == Critter.CritterShape.STAR){
			double y_coor = y * (canvasController.getHeight()/(Params.world_height));
			double x_coor = x * (canvasController.getWidth()/(Params.world_width * 2));
			double w = (canvasController.getWidth()/(Params.world_width * 2));
			double h = (canvasController.getHeight()/(Params.world_height * 2));
			double[] x_coords = {x_coor + (w * 9/16), x_coor + (w * 7/16), x_coor, x_coor + (w * 3/8), x_coor + (w * 1/4), x_coor + (w * 9/16), x_coor + (w * 7/8), x_coor + (w * 3/4), x_coor + w, x_coor + (w * 11/16)};
			double[] y_coords = {y_coor, y_coor + (h * 3/8), y_coor + (h * 3/8), y_coor + (h * 5/8), y_coor + h, y_coor + (h * 3/4), y_coor + h, y_coor + (h * 5/8), y_coor + (h * 3/8), y_coor + (h * 3/8)};
			gc.fillPolygon(x_coords, y_coords, 10);
			gc.strokePolygon(x_coords, y_coords, 10);
			}
		}

	
	
	
	
	
}
