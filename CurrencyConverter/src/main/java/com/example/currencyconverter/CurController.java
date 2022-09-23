package com.example.currencyconverter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
            double exchange = getExchange(currencyTo, currencyFrom);
            double total = convert(exchange, amount);
            result.setText("You now have " + total + " " + currencyTo);

        // Invalid input
        } else result.setText("Invalid input");
    }

    @FXML
    public void initialize(){
        initialiseChoiceBox();
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
    private int parseInput(){
        //TODO
        //parses textbox input
        //returns -1 if textbox input invalid
        return -1;
    }

    @FXML
    private double convert(double exchangeRate, double amount){
    //TODO
        //returns amount once converted
        return -1;
    }

    private double getExchange(String to, String from) throws IOException {     //gets the EUR -> GBP current rate atm
        String key = "834a41d702ef3d67646a4c98";
        URL url = new URL("https://v6.exchangerate-api.com/v6/" + key + "/latest/EUR");
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
        return object.getJSONObject("conversion_rates").getDouble("GBP");

    }
}