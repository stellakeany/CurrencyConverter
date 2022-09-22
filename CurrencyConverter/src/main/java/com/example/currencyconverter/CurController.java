package com.example.currencyconverter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CurController {
    @FXML
    private Label result;
    @FXML
    private ChoiceBox<String> currencyFrom;
    @FXML
    private ChoiceBox<String> currencyTo;
    @FXML
    private TextField inputAmount;
    @FXML
    private Button calculateButton;

    @FXML
    protected void onGoButtonClick() {

        result.setText("You now have");
    }

    public ChoiceBox<String> getCurrencyFrom() {
        return currencyFrom;
    }

    public ChoiceBox<String> getCurrencyTo() {
        return currencyTo;
    }

    public TextField getInputAmount() {
        return inputAmount;
    }

    public Button getCalculateButton() {
        return calculateButton;
    }
}