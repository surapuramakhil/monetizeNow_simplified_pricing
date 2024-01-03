package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class PriceConfiguration {

    String productId;

    Boolean graduated;

    List<Tier> tiers;

    public PriceConfiguration(String productId, Boolean graduated, List<Tier> tiers) {
        this.productId = productId;
        this.graduated = graduated;
        this.tiers = tiers;
    }

    public String getProductId() {
        return productId;
    }

    public Boolean isGraduated() {
        return graduated;
    }

    public List<Tier> getTiers() {
        return tiers;
    }
}
