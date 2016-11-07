import 

public class WorldControl
{
	@FXML
	private BorderPane borderPaneController;

	@FXML
	private GridPane gridPaneController;

	@FXML
	private HBox hBoxController;

	@FXML
	private Button stepButtonController;

	@FXML
	private Button seedButtonController;
	
	@FXML
	private Button makeButtonController;

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
	private ChoiceBox makeChoiceController;

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

	private void stepButtonEvent(ActionEvent event)
	{
		int steps = 0;

		steps = Integer.parseInt(stepsTextController.getText());

		for(int i = 0; i < steps; i++)
		{
			Critter.doWorldTimeStep();
		}
		Critter.displayWorld();
	}

	private void makeButtonEvent(ActionEvent event)
	{
		int count = 0;
		String type;
		count = Integer.parseInt(makeTextController.getText());
		type = makeCritterController.getText();
		for(int i = 0; i < count; i++)
		{
			makeCritter(type);
		}
	}

	private void seedButtonEvent(ActionEvent event)
	{
		int seedValue = 0;
		seedValue = Integer.parseInt(seedTextController.getText());
		Critter.setSeed(seedValue);
	}
	private boolean animation = false;

	private void startButtonEvent(ActionEvent event)
	{
		double count = 0;
		animation = true;
		count = animationSliderController.getValue();
		stepButtonController.setDisable(true);
		seedButtonController.setDisable(true);
		makeButtonController.setDisable(true);
		while(animation)
		{
			for(int i = 0; i < count; i++)
			{
				Critter.doWorldTimeStep();
			}
			Critter.displayWorld();
		}
		stepButtonController.setDisable(false);
		seedButtonController.setDisable(false);
		makeButtonController.setDisable(false);
	}

	private void stopButtonEvent(ActionEvent event)
	{
		animation = false;
	}

	private void quitButtonController(ActionEvent event)
	{
		System.exit(1);
	}
}
