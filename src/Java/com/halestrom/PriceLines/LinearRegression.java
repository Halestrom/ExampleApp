package com.halestrom.PriceLines;

import com.halestrom.API.ParseApiReply;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LinearRegression implements PriceLine
{

    protected DatePricePair start;
    protected DatePricePair end;


    public LinearRegression(String apiReply)
    {
        double[] prices = ParseApiReply.priceJsonToDoubleArr(ParseApiReply.stringToJson(apiReply));
        long[] dates = ParseApiReply.dateJsonToUnixArr(ParseApiReply.stringToJson(apiReply));
        start = new DatePricePair(dates[1], pricePrediction(prices, 1));
        end = new DatePricePair(dates[dates.length - 1] + 86400, pricePrediction(prices, prices.length));

    }


    public static double sigmaNotationOnIndex(double[] arr, String variables)
    {
        // this overload should be used only in case prediction is based on index,
        // in other words prices are provided with the same interval
        double sigmaResult = 0;
        switch (variables)
        {
            case "x":  // Sigma x
                for (int j = 0; j < arr.length; j++)
                {
                    sigmaResult += j;
                }
                break;
            case "x*y":  // Sigma x*y
                for (int j = 0; j < arr.length; j++)
                {
                    sigmaResult += arr[j] * j;
                }
                break;
            case "x^2": // Sigma x*x
                for (int j = 0; j < arr.length; j++)
                {
                    sigmaResult += Math.pow(j, 2);
                }
                break;
            case "y": // Sigma y
                for (int j = 1; j < arr.length; j++)
                {
                    sigmaResult += arr[j];
                }
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return sigmaResult;
    }


    protected static double sigmaNotationOnUnixD()
    {
        throw new NotImplementedException();
    }


    public static double calculateSlope(double Sxy, double Sx, double Sy, double Sxx, double n)
    {
        double b;
        b = (n * Sxy - Sx * Sy) / (n * Sxx - Math.pow(Sx, 2));
        return b;
    }


    public static double calculateIntercept(double Sx, double Sy, double n, double b)
    {
        double a;
        a = (1 / n) * Sy - b * (1 / n) * Sx;
        return a;
    }


    public static double pricePrediction(double[] priceData, int index)
    {
        // this overload should be used only in case prediction is based on index,
        // in other words prices are provided with the same interval


        //y=bx+a where y is a  price, x is a day, b - slope(coefficient), a - intercept
        double n = priceData.length - 1;//amount of observations (first element is not taken into account)
        double Sx = sigmaNotationOnIndex(priceData, "x");// Sigma x
        double Sy = sigmaNotationOnIndex(priceData, "y");// Sigma y
        double Sxx = sigmaNotationOnIndex(priceData, "x^2");// Sigma x^2
        double Sxy = sigmaNotationOnIndex(priceData, "x*y");// Sigma x*y
        double b = calculateSlope(Sxy, Sx, Sy, Sxx, n);// coefficient
        double a = calculateIntercept(Sx, Sy, n, b);// intercept
        return b * index + a;
    }


    protected static double pricePrediction(double[] priceData, long[] UnixDates, long UnixDate)
    {
        throw new NotImplementedException();
    }


    @Override
    public int getSeriesAmount()
    {
        return 2;
    }


    @Override
    public String[] getAllDates()
    {
        String[] allDates = new String[2];
        allDates[0] = ParseApiReply.unixToDateStr(start.unixDate);
        allDates[1] = ParseApiReply.unixToDateStr(end.unixDate);
        return allDates;
    }


    @Override
    public double[] getAllPrices()
    {
        double[] allPrices = new double[2];
        allPrices[0] = start.price;
        allPrices[1] = end.price;
        return allPrices;
    }
}
