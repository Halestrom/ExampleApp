package com.halestrom.API;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseApiReply
{

    public static String stringToJson(String apiReply)
    {

        int strStartIndex = apiReply.indexOf("[");
        int strEndIndex = apiReply.indexOf("]") + 1;
        String json = apiReply.substring(strStartIndex, strEndIndex);
        return json;


    }


    public static double[] priceJsonToDoubleArr(String responseContent)
    {
        JSONArray apiResponse = new JSONArray(responseContent);
        double[] resPriceArr = new double[apiResponse.length()];
        for (int i = 0; i < apiResponse.length(); i++)
        {
            JSONObject responseLine = apiResponse.getJSONObject(i);
            resPriceArr[i] = responseLine.getDouble("close");
        }

        return resPriceArr;
    }


    public static long[] dateJsonToUnixArr(String responseContent)
    {
        JSONArray apiResponse = new JSONArray(responseContent);
        long[] resPriceArr = new long[apiResponse.length()];
        for (int i = 0; i < apiResponse.length(); i++)
        {
            JSONObject responseLine = apiResponse.getJSONObject(i);
            resPriceArr[i] = responseLine.getLong("time");
        }

        return resPriceArr;
    }


    public static String[] unixArrToDateStrArr(long[] unixArr)
    {
        String[] dateArr = new String[unixArr.length];
        for (int i = 0; i < unixArr.length; i++)
        {
            dateArr[i] = unixToDateStr(unixArr[i]);
        }
        return dateArr;
    }


    public static String unixToDateStr(long unixDate)
    {
        String date = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date(unixDate * 1000));
        return date;
    }


}
