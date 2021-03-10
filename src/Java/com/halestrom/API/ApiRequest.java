package com.halestrom.API;

import com.halestrom.exceptions.ApiCallFailedException;
import com.halestrom.exceptions.NotConnectedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiRequest
{
    private static HttpURLConnection connection;
    private static URL url;
    final static private int connectionNotSuccesfulStatus = 300;
    final static private int timeoutPeriod = 5000;
    final static private String succesfulApiReplyMarker = "\"Response\":\"Success\"";


    public static String submit(String apiCall) throws ApiCallFailedException, NotConnectedException
    {

        BufferedReader reader;
        String responseLine;
        StringBuffer responseContent = new StringBuffer();


        {
            try
            {
                url = new URL(apiCall);
                connection = (HttpURLConnection) url.openConnection();
                //connection SetUp
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(timeoutPeriod);
                connection.setReadTimeout(timeoutPeriod);
                int connectionStatus = connection.getResponseCode();

                // if not connected, get Error code
                if (connectionStatus >= connectionNotSuccesfulStatus)
                {
                    throw new NotConnectedException("Connection has failed with " + Integer.toString(connectionStatus) + " error code");

                } // if connected, get Reply
                else
                {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((responseLine = reader.readLine()) != null)
                    {
                        responseContent.append(responseLine);
                    }
                    //check if reply is correct
                    if (!responseContent.toString().contains(succesfulApiReplyMarker))
                    {
                        throw new ApiCallFailedException(responseContent.toString());
                    } else if (responseContent.toString() == null)
                    {
                        throw new ApiCallFailedException("API call has failed with no API reply");
                    }
                }

            } catch (IOException e)
            {
                e.printStackTrace();
            } finally
            {
                connection.disconnect();
            }
        }
        return responseContent.toString();
    }


}
