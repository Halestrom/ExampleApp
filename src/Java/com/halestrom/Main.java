package com.halestrom;

import com.halestrom.API.ApiRequest;
import com.halestrom.PriceLines.LinearRegression;
import com.halestrom.PriceLines.PriceCurve;
import com.halestrom.PriceLines.PriceLine;
import com.halestrom.Visualisation.ChartFX;
import com.halestrom.exceptions.ApiCallFailedException;
import com.halestrom.exceptions.NotConnectedException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Halestrom
 */
public class Main extends Application
{


    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws ApiCallFailedException, NotConnectedException
    {
        String apiCall = "https://min-api.cryptocompare.com/data/v2/histoday?fsym=BTC&tsym=USD&limit=50&aggregate=1&e=CCCAGG";
        PriceLine lineReg = new LinearRegression(ApiRequest.submit(apiCall)); //  Linear Regression set up
        PriceLine priceCurve = new PriceCurve(ApiRequest.submit(apiCall));//  Price Curve set up
        ChartFX chart = new ChartFX();
        chart.start(lineReg, priceCurve);
    }
}
