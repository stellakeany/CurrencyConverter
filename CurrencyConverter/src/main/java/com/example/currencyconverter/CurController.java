package com.example.currencyconverter;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class CurController {
    @FXML
    private Label result;
    @FXML
    private ChoiceBox<String> currencyFrom;
    @FXML
    private ChoiceBox<String> currencyTo;

    @FXML
    protected void onGoButtonClick() {
        result.setText("You now have");
    }
}