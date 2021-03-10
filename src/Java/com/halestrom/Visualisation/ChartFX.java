package com.halestrom.Visualisation;

import com.halestrom.PriceLines.PriceLine;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ChartFX
{
    private final Stage thisStage = new Stage();


    private void setupPriceChart(PriceLine line, XYChart.Series Series)
    { // adding series for Price Chart
        String[] dates = line.getAllDates();
        double[] prices = line.getAllPrices();
        for (int i = 0; i < line.getSeriesAmount(); i++)
        {
            Series.getData().add(new XYChart.Data(dates[i], prices[i]));
        }
    }


    public void start(PriceLine linearRegr, PriceLine priceCurve)
    {
        //Defining the x axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        //Defining the y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price");

        //Creating the line chart
        final LineChart<String, Number> linechart = new LineChart<String, Number>(xAxis, yAxis);
        //Prepare XYChart.Series objects by setting data
        XYChart.Series priceSeries = new XYChart.Series();
        XYChart.Series linearRegrSeries = new XYChart.Series();


        //Charts Set up
        priceSeries.setName("BTC Price");
        linearRegrSeries.setName("Linear Regression");
        linechart.setPrefSize(900, 800);
        linechart.getData().addAll(priceSeries, linearRegrSeries);

        //Setting the data to Line chart
        setupPriceChart(linearRegr, linearRegrSeries);
        setupPriceChart(priceCurve, priceSeries);

        //Creating a Group object
        Group root = new Group(linechart);

        //Creating a scene object
        Scene scene = new Scene(root, 1000, 900);

        //Setting title to the Stage
        thisStage.setTitle("Line Chart");

        //Adding scene to the stage
        thisStage.setScene(scene);

        //Displaying the contents of the stage
        thisStage.show();
    }
}
