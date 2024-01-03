package org.example;

import org.example.domain.*;
import org.example.service.PriceCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Tier> tiers = new ArrayList<>();
        Tier tier = new Tier(Range.of(1,10),100, Currency.USD, PriceModel.FLAT);
        tiers.add(tier);
        tier = new Tier(Range.of(11,20),150,Currency.USD,PriceModel.FLAT);
        tiers.add(tier);

        PriceConfiguration priceConfiguration = new PriceConfiguration("product",false,tiers);

        PriceCalculator priceCalculator = new PriceCalculator(priceConfiguration);

        Scanner s = new Scanner(System.in);

        Integer quantity = s.nextInt();

        System.out.println(priceCalculator.calculate(quantity));

    }

}