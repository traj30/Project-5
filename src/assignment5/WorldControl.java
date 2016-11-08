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
		Critter.displayWorld();
	}

	public void makeButtonEvent(ActionEvent event)
	{
		int count = 0;
		String type = "";
		try
		{
			count = Integer.parseInt(stepsTextController.getText());
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

	public void seedButtonEvent(ActionEvent event)
	{
		int seedValue = 0;
		try
		{
			seedValue = Integer.parseInt(stepsTextController.getText());
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e);
		}
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
	
	
	public static void shapes(int x, int y, Critter c) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
		
		if(c.viewShape() == Critter.CritterShape.SQUARE){
			 gc.fillRect(x * (canvas.getWidth()/(Params.world_width * 2)), y * (canvas.getHeight()/(Params.world_height)), (canvas.getWidth()/(Params.world_width * 5)), (canvas.getHeight())/(Params.world_height * 3));
			 gc.strokeRect(x * (canvas.getWidth()/(Params.world_width * 2)), y * (canvas.getHeight()/(Params.world_height)), (canvas.getWidth()/(Params.world_width * 5)), (canvas.getHeight())/(Params.world_height * 3));
		}
		
		else if(c.viewShape() == Critter.CritterShape.CIRCLE){
			 gc.fillOval(x * (canvas.getWidth()/(Params.world_width * 2)), y * (canvas.getHeight()/(Params.world_height)), (canvas.getWidth()/(Params.world_width * 5)), (canvas.getHeight())/(Params.world_height * 3));
			 gc.strokeOval(x * (canvas.getWidth()/(Params.world_width * 2)), y * (canvas.getHeight()/(Params.world_height)), (canvas.getWidth()/(Params.world_width * 5)), (canvas.getHeight())/(Params.world_height * 3));
			 }
		
		else if(c.viewShape() == Critter.CritterShape.TRIANGLE){
			double y_coor = y * (canvas.getHeight()/(Params.world_height));
			double x_coor = x * (canvas.getWidth()/(Params.world_width * 2));
			double w = (canvas.getWidth()/(Params.world_width * 2));
			double h = (canvas.getHeight()/(Params.world_height * 2));
			double[] x_coords = {x_coor, x_coor + (w/2),x_coor + w};
			double[] y_coords = {y_coor + h, y_coor,y_coor + h};
			gc.fillPolygon(x_coords, y_coords, 3);
			gc.strokePolygon(x_coords, y_coords, 3);
		}
		else if(c.viewShape() == Critter.CritterShape.DIAMOND){
			double y_coor = y * (canvas.getHeight()/(Params.world_height));
			double x_coor = x * (canvas.getWidth()/(Params.world_width * 2));
			double w = (canvas.getWidth()/(Params.world_width * 2));
			double h = (canvas.getHeight()/(Params.world_height * 2));
			double[] x_coords = {x_coor, x_coor + (w/2),x_coor + w, x_coor + (w/2)};
			double[] y_coords = {y_coor + (h/2), y_coor, y_coor + (h/2), y_coor + h};
			gc.fillPolygon(x_coords, y_coords, 4);
			gc.strokePolygon(x_coords, y_coords, 4);
		}
		else if(c.viewShape() == Critter.CritterShape.STAR){
			double y_coor = y * (canvas.getHeight()/(Params.world_height));
			double x_coor = x * (canvas.getWidth()/(Params.world_width * 2));
			double w = (canvas.getWidth()/(Params.world_width * 2));
			double h = (canvas.getHeight()/(Params.world_height * 2));
			double[] x_coords = {x_coor + (w * 9/16), x_coor + (w * 7/16), x_coor, x_coor + (w * 3/8), x_coor + (w * 1/4), x_coor + (w * 9/16), x_coor + (w * 7/8), x_coor + (w * 3/4), x_coor + w, x_coor + (w * 11/16)};
			double[] y_coords = {y_coor, y_coor + (h * 3/8), y_coor + (h * 3/8), y_coor + (h * 5/8), y_coor + h, y_coor + (h * 3/4), y_coor + h, y_coor + (h * 5/8), y_coor + (h * 3/8), y_coor + (h * 3/8)};
			gc.fillPolygon(x_coords, y_coords, 10);
			gc.strokePolygon(x_coords, y_coords, 10);
			}
		}

	
	
	
	
	
}
