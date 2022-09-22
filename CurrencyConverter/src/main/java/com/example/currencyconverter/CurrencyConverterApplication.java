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

    public static void APIWork{     //gets the EUR -> GBP current rate atm
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
        Double exchange_value = object.getJSONObject("conversion_rates").getDouble("GBP");
        System.out.println(exchange_value);

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