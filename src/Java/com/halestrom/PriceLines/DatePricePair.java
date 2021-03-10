package com.halestrom.PriceLines;

class DatePricePair
{
    protected long unixDate;
    protected double price;
    protected DatePricePair next;
    protected DatePricePair previous;


    DatePricePair(long unixDate, double price)
    {
        this.unixDate = unixDate;
        this.price = price;
    }
}
