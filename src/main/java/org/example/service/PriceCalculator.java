package org.example.service;

import org.example.domain.PriceConfiguration;
import org.example.domain.Tier;

import java.util.List;
import java.util.Objects;

public class PriceCalculator {

    PriceConfiguration priceConfiguration;

    public PriceCalculator(PriceConfiguration priceConfiguration) {

        validate(priceConfiguration);

        this.priceConfiguration = priceConfiguration;

    }

    /*
    Kindly read readme before validating it.
    Range.of ensure than your ranges are correct i.e, from value will always less than or equal to "to" value
     */
    void validate(PriceConfiguration priceConfiguration) {

        Boolean isValid = true;
        List<Tier> tiers = priceConfiguration.getTiers();

        if (!priceConfiguration.isGraduated() && tiers.stream().anyMatch(tier -> Objects.isNull(tier.getPriceModel()))) {
            throw new RuntimeException("priceModel has to be set for each tier in non graduated pricing");
        }

        if (tiers.get(0).getRange().getFrom() != 1) {
            isValid = false;
        }

        for (int i = 1; i < tiers.size(); i++) {

            if (!(tiers.get(i).getRange().getFrom() - tiers.get(i - 1).getRange().getTo() == 1)) {
                isValid = false;
                break;
            }

        }

        if (!isValid) {
            throw new RuntimeException("Tier ranges need to be corrected");
        }

    }

    Integer graduatedPricing(Integer quantity) {
        Integer total = 0;
        List<Tier> tiers = priceConfiguration.getTiers();

        if (quantity > tiers.get(tiers.size() - 1).getRange().getTo()) {
            throw new RuntimeException("Tier undefined for quantity:" + quantity + " In graduated model");
        }

        for (int i = 0; i < tiers.size(); i++) {
            Tier tier = tiers.get(i);

            if (quantity < tier.getRange().getTo()) {
                total += (quantity - tier.getRange().getFrom() + 1) * tier.getPrice();
                break;
            }

            total += (tier.getRange().getTo() - tier.getRange().getFrom() + 1) * tier.getPrice();
        }
        return total;
    }

    Integer volumeOrFlatPricing(Integer quantity) {
        List<Tier> tiers = priceConfiguration.getTiers();
        for (int i = 0; i < tiers.size(); i++) {
            Tier tier = tiers.get(i);
            if (quantity >= tier.getRange().getFrom() && quantity <= tier.getRange().getTo()) {
                switch (tier.getPriceModel()) {
                    case FLAT:
                        return tier.getPrice();
                    case VOLUME:
                        return quantity * tier.getPrice();
                }
            }

        }

        throw new RuntimeException("Tier undefined for quantity:" + quantity + "In flat/volume model");
    }

    public Integer calculate(Integer quantity) {

        boolean isGraduated = priceConfiguration.isGraduated();
        List<Tier> tiers = priceConfiguration.getTiers();


        if (isGraduated) {
            return graduatedPricing(quantity);
        }

        return volumeOrFlatPricing(quantity);
    }

}
