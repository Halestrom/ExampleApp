package com.halestrom.PriceLines;

import com.halestrom.API.ParseApiReply;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PriceCurve implements PriceLine
{
    DatePricePair head = null;
    DatePricePair tail = null;
    int pairsCount = 0;


    public PriceCurve(String apiReply)
    {
        double[] price = ParseApiReply.priceJsonToDoubleArr(ParseApiReply.stringToJson(apiReply));
        long[] dates = ParseApiReply.dateJsonToUnixArr(ParseApiReply.stringToJson(apiReply));

        //adding price and date using nodes
        if (price.length == dates.length)
        {
            DatePricePair temp = null;
            for (int i = 0; i < dates.length; i++)
            {
                if (i == 0)
                {
                    head = new DatePricePair(dates[i], price[i]);
                    head.previous = null;
                    temp = head;
                    pairsCount++;
                } else if (i == dates.length - 1)
                {
                    tail = new DatePricePair(dates[i], price[i]);
                    tail.next = null;
                    temp.next = tail;
                    tail.previous = temp;
                    pairsCount++;
                } else
                {
                    temp.next = new DatePricePair(dates[i], price[i]);
                    temp.next.previous = temp;
                    temp = temp.next;
                    pairsCount++;
                }

            }
        }
    }


    public void printPriceCurve()
    {
        DatePricePair temp = head;
        for (int i = 0; i < pairsCount; i++)
        {
            System.out.println("Date: " + temp.unixDate + " Price " + temp.price);
            temp = temp.next;
        }
    }


    public void addPair()
    {
        throw new NotImplementedException();
    }


    public void removePairByDate()
    {
        throw new NotImplementedException();
    }


    @Override
    public int getSeriesAmount()
    {
        return pairsCount;
    }


    @Override
    public String[] getAllDates()
    {
        DatePricePair temp = head;
        String[] allDates = new String[pairsCount];
        for (int i = 0; i < pairsCount; i++)
        {
            allDates[i] = ParseApiReply.unixToDateStr(temp.unixDate);
            temp = temp.next;
        }
        return allDates;
    }


    @Override
    public double[] getAllPrices()
    {
        DatePricePair temp = head;
        double[] allPrices = new double[pairsCount];
        for (int i = 0; i < pairsCount; i++)
        {
            allPrices[i] = temp.price;
            temp = temp.next;
        }
        return allPrices;
    }
}
