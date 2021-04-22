package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    @FXML
    private Text veggiesDisplay;

    @FXML
    private Text saltDisplay;
    @FXML
    private Text pepperDisplay;

    // Get veggies checkboxes
    @FXML
    private CheckBox tomatoBox;

    @FXML
    private CheckBox oliveBox;

    @FXML
    private CheckBox pickleBox;

    @FXML
    private CheckBox lettuceBox;

    @FXML
    private CheckBox redPepperBox;

    @FXML
    private CheckBox cucumberBox;

    @FXML
    private ChoiceBox<String> breadChoiceBox;

    @FXML
    private ChoiceBox<String> cheeseChoiceBox;

    @FXML
    private ChoiceBox<String> meatChoiceBox;

    @FXML
    private ChoiceBox<String> sauceChoiceBox;

    // Arrays to hold choiceBox values
    private final String[] breadChoices = {"white", "wheat", "italian"};
    private final String[] cheeseChoices = {"american", "colby jack", "cheddar"};
    private final String[] meatChoices = {"ham", "peperoni", "turkey"};
    private final String[] sauceChoices = {"italian", "ranch", "mayonnaise"};

    // Sets to hold the unselected and selected veggie check boxes
    private ArrayList<CheckBox> veggiesBoxes = new ArrayList<CheckBox>();
    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unSelectedCheckBoxes = FXCollections.observableSet();
    // Size of selected check box set, or number of veggies selected
    private IntegerBinding numOfSelected = Bindings.size(selectedCheckBoxes);


    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private ToggleGroup saltToggleGroup;

    @FXML
    private ToggleGroup pepperToggleGroup;

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

    @Override
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

    @FXML
    private void submitOrder()
    {
        String veggieString = "";

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

        // Loop through arrayList
        for (CheckBox cb : veggiesBoxes)
        {
            // If checkbox is selected
            if (cb.isSelected())
            {
                // Append its string value to the veggie string builder
                veggieString += cb.getText() + " ";
            }
        }

        // Set display text to reflect users order
        sizeDisplay.setText(sandwichSize);
        breadDisplay.setText(breadChoice);
        cheeseDisplay.setText(cheeseChoice);
        meatDisplay.setText(meatChoice);
        sauceDisplay.setText(sauceChoice);
        saltDisplay.setText(saltChoice);
        pepperDisplay.setText(pepperChoice);
        veggiesDisplay.setText(veggieString);

    }
}
