import org.example.domain.*;
import org.example.service.PriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PriceCalculationTesting {

    @Test
    void flatPricingTesting(){

        List<Tier> tiers = new ArrayList<>();
        Tier tier = new Tier(Range.of(1, 10), 100, Currency.USD, PriceModel.FLAT);
        tiers.add(tier);
        tier = new Tier(Range.of(11, 20), 150, Currency.USD, PriceModel.FLAT);
        tiers.add(tier);

        PriceConfiguration priceConfiguration = new PriceConfiguration("product", false, tiers);

        PriceCalculator priceCalculator = new PriceCalculator(priceConfiguration);
        Assertions.assertEquals(Integer.valueOf(100), priceCalculator.calculate(5));
        Assertions.assertEquals(Integer.valueOf(150), priceCalculator.calculate(5));
    }

    @Test
    void volumePricingTesting(){

        List<Tier> tiers = new ArrayList<>();
        Tier tier = new Tier(Range.of(1, 10), 10, Currency.USD, PriceModel.VOLUME);
        tiers.add(tier);

        PriceConfiguration priceConfiguration = new PriceConfiguration("product", false, tiers);

        PriceCalculator priceCalculator = new PriceCalculator(priceConfiguration);
        Assertions.assertEquals(Integer.valueOf(20), priceCalculator.calculate(2));
        Assertions.assertEquals(Integer.valueOf(50), priceCalculator.calculate(5));

    }

    @Test
    void flatVolumePricingTesting(){

        List<Tier> tiers = new ArrayList<>();
        Tier tier = new Tier(Range.of(1, 15), 10, Currency.USD, PriceModel.VOLUME);
        tiers.add(tier);
        tier = new Tier(Range.of(16, 20), 150, Currency.USD, PriceModel.FLAT);
        tiers.add(tier);

        PriceConfiguration priceConfiguration = new PriceConfiguration("product", false, tiers);

        PriceCalculator priceCalculator = new PriceCalculator(priceConfiguration);
        Assertions.assertEquals(Integer.valueOf(20), priceCalculator.calculate(2));
        Assertions.assertEquals(Integer.valueOf(50), priceCalculator.calculate(5));
        Assertions.assertEquals(Integer.valueOf(150), priceCalculator.calculate(15));
        Assertions.assertEquals(Integer.valueOf(150), priceCalculator.calculate(18));

    }

    @Test
    void graduatedPricingTesting(){

        List<Tier> tiers = new ArrayList<>();
        Tier tier = new Tier(Range.of(1, 15), 10, Currency.USD);
        tiers.add(tier);
        tier = new Tier(Range.of(16, 20), 8, Currency.USD);
        tiers.add(tier);
        tier = new Tier(Range.of(21, 40), 6, Currency.USD);
        tiers.add(tier);

        PriceConfiguration priceConfiguration = new PriceConfiguration("product", true, tiers);

        PriceCalculator priceCalculator = new PriceCalculator(priceConfiguration);
        Assertions.assertEquals(Integer.valueOf(20), priceCalculator.calculate(2));
        Assertions.assertEquals(Integer.valueOf(15 * 10 + 5 * 8), priceCalculator.calculate(20));
        Assertions.assertEquals(Integer.valueOf((15 * 10) + (5 * 8)+ (15 * 6)), priceCalculator.calculate(35));

    }

}
