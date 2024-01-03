package org.example.domain;

public class Tier {

    Range range;
    Integer price;
    Currency currency;

    PriceModel priceModel;

    public Tier(Range range, Integer price, Currency currency, PriceModel priceModel) {
        this.range = range;
        this.price = price;
        this.currency = currency;
        this.priceModel = priceModel;
    }

    public Tier(Range range, Integer price, Currency currency) {
        this.range = range;
        this.price = price;
        this.currency = currency;
    }

    public Range getRange() {
        return range;
    }

    public Integer getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public PriceModel getPriceModel() {
        return priceModel;
    }
}
