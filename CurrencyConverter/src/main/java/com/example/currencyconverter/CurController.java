package com.example.currencyconverter;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class CurController {
    @FXML
    private Label result;
    @FXML
    private ChoiceBox<String> currencyFromInput;
    @FXML
    private String currencyFrom;
    @FXML
    private ChoiceBox<String> currencyToInput;
    @FXML
    private String currencyTo;
    @FXML
    private TextField inputAmount;

    @FXML
    protected void onGoButtonClick() throws IOException {
        double amount = parseInput();

        // Valid input
        if (amount >= 0){
            double exchange = getExchange();
            double total = convert(exchange, amount);
            result.setText("You now have " + Math.round(total * 100.0) / 100.0 + " " + currencyTo);

        // Invalid input
        } else result.setText("Invalid input");
    }

    @FXML
    public void initialize(){
        initialiseChoiceBox();

        //Converts numeric input in amount text box to double
        inputAmount.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));

    }

    @FXML
    protected void initialiseChoiceBox() {
        //Adding list of currencies to each choice box
        ArrayList<String> currencyCodes = new ArrayList<>(Arrays.asList(
                "AUD",
                "CAD",
                "EUR",
                "GBP",
                "HKD",
                "JPY",
                "USD"));
        currencyToInput.getItems().addAll(currencyCodes);
        currencyFromInput.getItems().addAll(currencyCodes);

        //Setting default value
        currencyToInput.setValue("USD");
        currencyFromInput.setValue("EUR");
    }

    @FXML
    private double parseInput(){

        String input = inputAmount.getText();
        Double d;

        //Reject input if textbox null or can't be parsed as a double
        if (input == null) {
            return -1;
        }
        try {
            d = Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            return -1;
        }

        //Assigning value to currency choice
        currencyFrom = currencyFromInput.getValue();
        currencyTo = currencyToInput.getValue();

        return d;
    }

    @FXML
    private double convert(double exchangeRate, double amount){
        return amount * exchangeRate;
    }

    private double getExchange() throws IOException {     //gets the EUR -> GBP current rate atm
        String key = "834a41d702ef3d67646a4c98";
        URL url = new URL("https://v6.exchangerate-api.com/v6/" + key + "/latest/" + currencyFrom);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        int responseCode = con.getResponseCode();

        if (responseCode != 200){
            throw new RuntimeException("failed: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));    //used to read from json file from API
        String JSONinputLine;                       //each line is read one by one from the reader above
        StringBuffer json = new StringBuffer();     //input each line read from the json document from API into a full buffered String as we don't know size

        //* we read the retrieved json from the stream */
        while ((JSONinputLine = in.readLine()) != null){
            json.append(JSONinputLine);
        }
        in.close();

        JSONObject object = new JSONObject(json.toString());
        return object.getJSONObject("conversion_rates").getDouble(currencyTo);

    }
}