package com.example.currencyconverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CurrencyConverterApplication extends Application {

    private static final CurController controller = new CurController();
    private String inputAmount;
    private String currencyTo;
    private String currencyFrom;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CurrencyConverterApplication.class.getResource("CurCon-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Currency Converter");

        
        /*initialiseChoiceBox();

        Button button = controller.getCalculateButton();
        button.setOnAction(e -> parseChoice());*/

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private static void initialiseChoiceBox(){
        ChoiceBox<String> to = controller.getCurrencyTo();
        ChoiceBox<String> from = controller.getCurrencyFrom();

        ArrayList<String> currencyCodes = new ArrayList<>(Arrays.asList(
                "AUD",
                "CAD",
                "EUR",
                "GBP",
                "HKD",
                "JPY",
                "USD"));

        to.getItems().addAll(currencyCodes);
        from.getItems().addAll(currencyCodes);
    }

    private void parseChoice(){
        this.currencyFrom = controller.getCurrencyFrom().getValue();
        this.currencyTo = controller.getCurrencyTo().getValue();
        this.inputAmount = controller.getInputAmount().getText();

        System.out.printf(currencyFrom);
        System.out.printf(currencyTo);
        System.out.println(inputAmount);
    }
}