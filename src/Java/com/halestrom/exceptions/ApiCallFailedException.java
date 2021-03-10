package com.halestrom.exceptions;

public class ApiCallFailedException extends Exception
{
    public ApiCallFailedException(String errorMessage)
    {
        super(errorMessage);
    }
}
