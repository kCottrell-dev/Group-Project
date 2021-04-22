# Subway Ordering System

The application will be an automated Ordering System for Subway. It will use a GUI to interact with the user. A user chooses:

Size of sandwich
Type of bread
Type of meat
Type of cheese
Up to three veggies
Type of Sauce
Salt & Pepper (Y/N)
A summary display shows all of the options chosen by the user.

Basically we are creating an order system similiar to a system used by Subway
on the online ordering website.

## Example Output


![Sample Output](README.jpg)

## Analysis Steps

We analyzed the requirements by looking at the list given to us on the canvas page. We know that
we need to create a GUI and to create various functions to make our program function like a subway ordering system
we have to use multiple different javafx functions in order to build this project while we also used scenebuilder

### Design

```
public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sandwich order");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }
```

### Testing

Step 1: import FXML

```
  @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
       
    }
```

Step 2: Create Controller

```
public class Controller implements Initializable
{
    public int veggieLimit = 3;

    // Get display text
    @FXML
    private Text sizeDisplay;

    @FXML
    private Text breadDisplay;

    @FXML
    private Text cheeseDisplay;

    @FXML
    private Text meatDisplay;

    @FXML
    private Text sauceDisplay;

```
Step 3: Create Choicebox Strings

```
 private final String[] breadChoices = {"white", "wheat", "italian"};
    private final String[] cheeseChoices = {"american", "colby jack", "cheddar"};
    private final String[] meatChoices = {"ham", "peperoni", "turkey"};
    private final String[] sauceChoices = {"italian", "ranch", "mayonnaise"};
```
Step 4: Initailize Checkboxes

```
 private ArrayList<CheckBox> veggiesBoxes = new ArrayList<CheckBox>();
    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unSelectedCheckBoxes = FXCollections.observableSet();
    // Size of selected check box set, or number of veggies selected
    private IntegerBinding numOfSelected = Bindings.size(selectedCheckBoxes);
```
Step 5:Create Checkbox Function
```
private void configureCheckBox(CheckBox checkbox)
    {
        if (checkbox.isSelected())
        {
            // Add checkbox to selected set
            selectedCheckBoxes.add(checkbox);
        }
        else
        {
            // Add checkbox to unselected set
            unSelectedCheckBoxes.add(checkbox);
        }

        // Set selected event listener to checkbox
        checkbox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected)
            {
                // Remove from unselected set and add to selected set
                unSelectedCheckBoxes.remove(checkbox);
                selectedCheckBoxes.add(checkbox);
            }
            else
            {
                // Remove to selected set and add to unselected set
                selectedCheckBoxes.remove(checkbox);
                unSelectedCheckBoxes.add(checkbox);
            }
        });
    }
```
Step 6: Intialize Controller and add choices

```
public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // Configure checkboxes
        configureCheckBox(tomatoBox);
        configureCheckBox(oliveBox);
        configureCheckBox(pickleBox);
        configureCheckBox(lettuceBox);
        configureCheckBox(redPepperBox);
        configureCheckBox(cucumberBox);


        numOfSelected.addListener((obs, oldCount, newCount) -> {
            // If selected checkboxes >= 3
            if (newCount.intValue() >= veggieLimit)
            {
                // Disable all other unselected check boxes
                unSelectedCheckBoxes.forEach(cb -> cb.setDisable(true));
            }
            else
            {
                // Check boxes are not disabled
                unSelectedCheckBoxes.forEach((cb -> cb.setDisable(false)));
            }
        });

        // Add all choices to choice boxes from array of choices
        breadChoiceBox.getItems().addAll(breadChoices);
        cheeseChoiceBox.getItems().addAll(cheeseChoices);
        meatChoiceBox.getItems().addAll(meatChoices);
        sauceChoiceBox.getItems().addAll(sauceChoices);
    }
```
Step 7: Order Button Method

```
private void submitOrder()
    {
        // Add all checkboxes to arrayList
        veggiesBoxes.add(tomatoBox);
        veggiesBoxes.add(oliveBox);
        veggiesBoxes.add(pickleBox);
        veggiesBoxes.add(lettuceBox);
        veggiesBoxes.add(redPepperBox);
        veggiesBoxes.add(cucumberBox);

        // Get sandwich size
        RadioButton sizeButton = (RadioButton) sizeToggleGroup.getSelectedToggle();
        String sandwichSize = sizeButton.getText();

        // Get bread choice
        String breadChoice = breadChoiceBox.getSelectionModel().getSelectedItem();

        // Get bread choice
        String cheeseChoice = cheeseChoiceBox.getSelectionModel().getSelectedItem();

        // Get meat choice
        String meatChoice = meatChoiceBox.getSelectionModel().getSelectedItem();

        // Get sauce choice
        String sauceChoice = sauceChoiceBox.getSelectionModel().getSelectedItem();

        // Get salt choice
        RadioButton saltButton = (RadioButton) saltToggleGroup.getSelectedToggle();
        String saltChoice = saltButton.getText();

        // Get pepper choice
        RadioButton pepperButton = (RadioButton) pepperToggleGroup.getSelectedToggle();
        String pepperChoice = pepperButton.getText();

        // Array to hold veggies
        ArrayList<String> vegetables = new ArrayList<String>();

        // Loop through arrayList
        for (int i = 0; i < veggiesBoxes.size(); i++)
        {
            // If checkbox is selected
            if (veggiesBoxes.get(i).isSelected())
            {
                // Append its string value veggies array
                vegetables.add(veggiesBoxes.get(i).getText());
            }
        }
```
Step 8: Create Subway Object
```
        Subway subway = new Subway(sandwichSize, breadChoice, meatChoice, cheeseChoice, sauceChoice, vegetables, saltChoice, pepperChoice);

        // Set display text to reflect users order
        sizeDisplay.setText(subway.getSize());
        breadDisplay.setText(subway.getBread());
        cheeseDisplay.setText(subway.getCheese());
        meatDisplay.setText(subway.getMeat());
        sauceDisplay.setText(subway.getSauce());
        saltDisplay.setText(subway.getSalt());
        pepperDisplay.setText(subway.getPepper());

        // Loop through vegetable array and add string value to vegetable string
        String vegetableString = "";
        for (String vegetable : subway.getVegetables()) {
            vegetableString += vegetable + " ";
            veggiesDisplay.setText(vegetableString);
        }
    }
```
Step 9: Create scene and lanuch GUI
```
     primaryStage.setTitle("Sandwich order");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
```



You can use it for a little demo as to what an ordering system would look like
online. You can get information on how they program their online ordering, this
help you in the future programming systems that order.

## Notes

Testing instructions: 
Step 1: User will select bread size.
![bread size](https://user-images.githubusercontent.com/63605640/115654688-40a35580-a2f7-11eb-8364-7b54a62292be.JPG)
Step 2: User will select the bread choice of their desire.(Italian, White, Wheat)
Step 3: User will select sandwhich meat for sandwhich.(Peperoni, Turkey, Ham)
Step 4: User will select cheese type. (American, Colby Jack, Cheddar)
Step 5: User selects sauce of their choice. (Ranch, Italian, Mayonnaise)
Step 6: User will select up to 3 veggie choices from our selection to add to sandwhich.
![veggies](https://user-images.githubusercontent.com/63605640/115655052-06868380-a2f8-11eb-85a8-efa0312e51e6.JPG)
Step 7: User will select if they want Salt and/or Pepper by selecting yes or no.
![seasonings](https://user-images.githubusercontent.com/63605640/115655129-3d5c9980-a2f8-11eb-897e-6ca4999e1a16.JPG)
Step 8: User will submit their order via submit button and recieve a Order Summary at the bottom of the page showing all of their selections for their sandwhich.
![summary](https://user-images.githubusercontent.com/63605640/115655302-a512e480-a2f8-11eb-9cb1-c8af87f87f46.JPG)
![final](https://user-images.githubusercontent.com/63605640/115655461-f9b65f80-a2f8-11eb-89f2-2e3b23dc0483.JPG)


(The Order Form will not allow user to submit without choosing atleast one selection from each option.)



## Do not change content below this line
## Adapted from a README Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
