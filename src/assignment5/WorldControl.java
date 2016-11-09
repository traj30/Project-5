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

import java.lang.reflect.Method;
import java.util.List;

import assignment5.Critter;
import assignment5.InvalidCritterException;
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

public class WorldControl {
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
	private TextField makeChoiceController;

	@FXML
	private TextField statsTextController;

	@FXML
	private Slider animationSliderController;

	@FXML
	private TextArea statsTextAreaController;
	private PrintStream ps;
	private String statsString = "Algae";

	public void initialize() {
		Console console = new Console(statsTextAreaController);
		ps = new PrintStream(console);
		System.setOut(ps);
		System.setErr(ps);
		Critter.displayWorld(this);
	}

	private static String myPackage;    // package of Critter file.  Critter cannot be in default pkg.

	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	public void stepButtonEvent(ActionEvent event) {
		int steps = 0;
		try{
			steps = Integer.parseInt(stepsTextController.getText());
		}
		catch(Exception e)
		{
			steps = 1;
		}
		for (int i = 0; i < steps; i++) {
			Critter.worldTimeStep();
		}
		Critter.displayWorld(this);
		System.out.println(steps +" steps");
		statsPlease();
	}

	@FXML
	public void makeButtonEvent(ActionEvent event) {
		int count = 0;
		String type = "";
		try {
			count = Integer.parseInt(makeTextController.getText());
			type = makeChoiceController.getText();
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			for (int i = 0; i < count; i++) {
				Critter.makeCritter(type);
			}
		} catch (InvalidCritterException e) {
			System.out.println(e);
		}
		Critter.displayWorld(this);
		System.out.println("" + count + " " + type + " made");
		statsPlease();
	}

	@FXML
	public void seedButtonEvent(ActionEvent event) {
		int seedValue = 0;
		try {
			seedValue = Integer.parseInt(seedTextController.getText());
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		Critter.setSeed(seedValue);
		System.out.println("Changing the seed to " + seedValue);
	}

	public boolean animation = false;

	@FXML
	public void startButtonEvent(ActionEvent event) {
		double count = 0;
		animation = true;
		count = animationSliderController.getValue();
		stepButtonController.setDisable(true);
		seedButtonController.setDisable(true);
		makeButtonController.setDisable(true);
		startButtonController.setDisable(true);
		quitButtonController.setDisable(true);
		while (animation) {
			for (int i = 0; i < count; i++) {
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
	public void statsButtonEvent(ActionEvent event) {
		String type;
		type = statsTextController.getText();
		try {
			List<Critter> bugs = Critter.getInstances(type);
			Class clazz = Class.forName(myPackage + "." + type);
			Class<?>[] types = {List.class};
			Method method = clazz.getMethod("runStats", types);
			method.invoke(null, bugs);
			statsString = type;
		} catch (InvalidCritterException e1) {
			wrongInput(type);
		} catch (ArrayIndexOutOfBoundsException e2) {
			wrongInput(type);
		} catch (ClassNotFoundException e3) {
			wrongInput(type);
		} catch (Exception e4) {
			wrongInput(type);
		}
	}

	private void statsPlease()
	{
		String type = statsString;
		try {
			List<Critter> bugs = Critter.getInstances(type);
			Class clazz = Class.forName(myPackage + "." + type);
			Class<?>[] types = {List.class};
			Method method = clazz.getMethod("runStats", types);
			method.invoke(null, bugs);
			statsString = type;
		} catch (InvalidCritterException e1) {
			wrongInput(type);
		} catch (ArrayIndexOutOfBoundsException e2) {
			wrongInput(type);
		} catch (ClassNotFoundException e3) {
			wrongInput(type);
		} catch (Exception e4) {
			wrongInput(type);
		}
	}

	private static void wrongInput(String wrong) {
		System.out.print("error processing: ");
		System.out.println(wrong);
	}

	@FXML
	public void stopButtonEvent(ActionEvent event) {
		animation = false;
	}

	public void drawGraph() {
		GraphicsContext gc = canvasController.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		double spaceW = canvasController.getWidth() / Params.world_width;
		double spaceH = (int) canvasController.getHeight() / Params.world_height;
		for (int i = 0; i < Params.world_height * spaceH; i++) {
			gc.strokeLine(i * spaceW, 0, i * spaceW, canvasController.getHeight());
		}
		for (int i = 0; i < Params.world_width * spaceW; i++) {
			gc.strokeLine(0, i * spaceH, canvasController.getWidth(), i * spaceH);
		}
	}

	public void clearGraphics() {
		GraphicsContext gc = canvasController.getGraphicsContext2D();
		gc.clearRect(0, 0, canvasController.getWidth(), canvasController.getHeight());
	}

	@FXML
	public void quitButtonController(ActionEvent event) {
		System.exit(1);
	}

	public void shapes1(int x, int y, CritterShape c, Color fillColor, Color outLineColor) {
		GraphicsContext gc = canvasController.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.setFill(fillColor);
		gc.setStroke(outLineColor);
		double x_pos = x * (canvasController.getWidth() / Params.world_width);
		double y_pos = y * (canvasController.getHeight() / Params.world_height);

		if (c == Critter.CritterShape.SQUARE) {
			gc.fillRect(x_pos, y_pos, (canvasController.getWidth() / (Params.world_width * 5)), (canvasController.getHeight()) / (Params.world_height * 3));
			gc.strokeRect(x_pos, y_pos, (canvasController.getWidth() / (Params.world_width * 5)), (canvasController.getHeight()) / (Params.world_height * 3));
		} else if (c == Critter.CritterShape.CIRCLE) {
			gc.fillOval(x_pos, y_pos, (canvasController.getWidth() / (Params.world_width * 5)), (canvasController.getHeight()) / (Params.world_height * 3));
			gc.strokeOval(x_pos, y_pos, (canvasController.getWidth() / (Params.world_width * 5)), (canvasController.getHeight()) / (Params.world_height * 3));
		} else if (c == Critter.CritterShape.TRIANGLE) {
			double y_coor = y * (canvasController.getHeight() / (Params.world_height));
			double x_coor = x * (canvasController.getWidth() / (Params.world_width * 2));
			double w = (canvasController.getWidth() / (Params.world_width * 2));
			double h = (canvasController.getHeight() / (Params.world_height * 2));
			double[] x_coords = {x_coor, x_coor + (w / 2), x_coor + w};
			double[] y_coords = {y_coor + h, y_coor, y_coor + h};
			gc.fillPolygon(x_coords, y_coords, 3);
			gc.strokePolygon(x_coords, y_coords, 3);
		} else if (c == Critter.CritterShape.DIAMOND) {
			double y_coor = y * (canvasController.getHeight() / (Params.world_height));
			double x_coor = x * (canvasController.getWidth() / (Params.world_width * 2));
			double w = (canvasController.getWidth() / (Params.world_width * 2));
			double h = (canvasController.getHeight() / (Params.world_height * 2));
			double[] x_coords = {x_coor, x_coor + (w / 2), x_coor + w, x_coor + (w / 2)};
			double[] y_coords = {y_coor + (h / 2), y_coor, y_coor + (h / 2), y_coor + h};
			gc.fillPolygon(x_coords, y_coords, 4);
			gc.strokePolygon(x_coords, y_coords, 4);
		} else if (c == Critter.CritterShape.STAR) {
			double y_coor = y * (canvasController.getHeight() / (Params.world_height));
			double x_coor = x * (canvasController.getWidth() / (Params.world_width * 2));
			double w = (canvasController.getWidth() / (Params.world_width * 2));
			double h = (canvasController.getHeight() / (Params.world_height * 2));
			double[] x_coords = {x_coor + (w * 9 / 16), x_coor + (w * 7 / 16), x_coor, x_coor + (w * 3 / 8), x_coor + (w * 1 / 4), x_coor + (w * 9 / 16), x_coor + (w * 7 / 8), x_coor + (w * 3 / 4), x_coor + w, x_coor + (w * 11 / 16)};
			double[] y_coords = {y_coor, y_coor + (h * 3 / 8), y_coor + (h * 3 / 8), y_coor + (h * 5 / 8), y_coor + h, y_coor + (h * 3 / 4), y_coor + h, y_coor + (h * 5 / 8), y_coor + (h * 3 / 8), y_coor + (h * 3 / 8)};
			gc.fillPolygon(x_coords, y_coords, 10);
			gc.strokePolygon(x_coords, y_coords, 10);
		}
	}
	public void shapes(int x, int y, CritterShape c, Color fillColor, Color outLineColor) {
		double col_width = canvasController.getWidth()/Params.world_width;
		double row_height = canvasController.getHeight()/Params.world_height;
		double cOffSet = col_width/2;
		double rOffSet = row_height/2;
		GraphicsContext gc = canvasController.getGraphicsContext2D();
		gc.setFill(fillColor);
		gc.setStroke(outLineColor);

		switch(c)
		{
			case SQUARE:
				gc.fillRect(x*col_width + col_width*.25, y*row_height + row_height*.25,col_width*.5,col_width*.5);
				gc.strokeRect(x*col_width + col_width*.25, y*row_height + row_height*.25,col_width*.5,col_width*.5);
				break;
			case CIRCLE:
				gc.fillOval(x*col_width + col_width*.25, y*row_height + row_height*.25,col_width*.5,col_width*.5);
				gc.strokeOval(x*col_width + col_width*.25, y*row_height + row_height*.25,col_width*.5,col_width*.5);
				break;
			case TRIANGLE:
				double[] x_coords1 = {x*col_width + cOffSet/2,x*col_width + cOffSet, x*col_width + 3*cOffSet/2 };
				double[] y_coords1 = {y*row_height + 3*rOffSet/2,y*row_height + rOffSet/2, y*row_height + 3*rOffSet/2};
				gc.fillPolygon(x_coords1,y_coords1,3);
				gc.strokePolygon(x_coords1,y_coords1,3);
				break;
			case DIAMOND:
				double[] x_coords2 = {x*col_width + cOffSet/2, x*col_width + cOffSet, x*col_width + 3*cOffSet/2, x*col_width + cOffSet};
				double[] y_coords2 = {y*row_height + rOffSet, y*row_height + rOffSet/2, y*row_height + rOffSet, y*row_height + 3*rOffSet/2};
				gc.fillPolygon(x_coords2,y_coords2,4);
				gc.strokePolygon(x_coords2,y_coords2,4);
				break;
			case STAR:
				double[] x_coords3 = {x*col_width + cOffSet/2,x*col_width + cOffSet, x*col_width + 3*cOffSet/2};
				double[] y_coords3 = {y*row_height + 4*rOffSet/3,y*row_height + rOffSet/2, y*row_height + 4*rOffSet/3};
				double[] x_coords4 = {x*col_width + cOffSet/2,x*col_width + cOffSet, x*col_width + 3*cOffSet/2};
				double[] y_coords4 = {y*row_height + 2*rOffSet/3,y*row_height + 3*rOffSet/2, y*row_height + 2*rOffSet/3};
				gc.strokePolygon(x_coords3,y_coords3,3);
				gc.strokePolygon(x_coords4,y_coords4,3);
				gc.fillPolygon(x_coords3,y_coords3,3);
				gc.fillPolygon(x_coords4,y_coords4,3);

				break;
		}
	}
}
	
	
