import org.example.domain.*;
import org.example.service.PriceCalculator;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class PriceConfigurationValidationTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    void validConfig() {

        List<Tier> tiers = new ArrayList<>();
        Tier tier = new Tier(Range.of(1, 10), 100, Currency.USD, PriceModel.FLAT);
        tiers.add(tier);
        tier = new Tier(Range.of(11, 20), 150, Currency.USD, PriceModel.FLAT);
        tiers.add(tier);

        PriceConfiguration priceConfiguration = new PriceConfiguration("product", false, tiers);

        PriceCalculator priceCalculator = new PriceCalculator(priceConfiguration);

    }

    @Test()
    void inValidNonContinuous() {

        List<Tier> tiers = new ArrayList<>();
        Tier tier = new Tier(Range.of(1, 9), 100, Currency.USD, PriceModel.FLAT);
        tiers.add(tier);
        tier = new Tier(Range.of(11, 20), 150, Currency.USD, PriceModel.FLAT);
        tiers.add(tier);

        PriceConfiguration priceConfiguration = new PriceConfiguration("product", false, tiers);

        assertThrows(RuntimeException.class, () -> {
            PriceCalculator priceCalculator = new PriceCalculator(priceConfiguration);
        });

    }

    @Test()
    void inValidOverlap() {

        List<Tier> tiers = new ArrayList<>();
        Tier tier = new Tier(Range.of(1, 15), 100, Currency.USD, PriceModel.FLAT);
        tiers.add(tier);
        tier = new Tier(Range.of(11, 20), 150, Currency.USD, PriceModel.FLAT);
        tiers.add(tier);

        PriceConfiguration priceConfiguration = new PriceConfiguration("product", false, tiers);

        assertThrows(RuntimeException.class, () -> {
            PriceCalculator priceCalculator = new PriceCalculator(priceConfiguration);
        });

    }

    @Test
    void graduatedInit(){

        List<Tier> tiers = new ArrayList<>();
        Tier tier = new Tier(Range.of(1, 15), 10, Currency.USD);
        tiers.add(tier);
        tier = new Tier(Range.of(16, 20), 150, Currency.USD);
        tiers.add(tier);

        PriceConfiguration priceConfiguration = new PriceConfiguration("product", true, tiers);

        PriceCalculator priceCalculator = new PriceCalculator(priceConfiguration);
    }

    @Test
    void flatVolumeInitWithOutPricing(){

        List<Tier> tiers = new ArrayList<>();
        Tier tier = new Tier(Range.of(1, 15), 10, Currency.USD);
        tiers.add(tier);
        tier = new Tier(Range.of(16, 20), 150, Currency.USD);
        tiers.add(tier);

        PriceConfiguration priceConfiguration = new PriceConfiguration("product", false, tiers);

        assertThrows(RuntimeException.class,()->{
            PriceCalculator priceCalculator = new PriceCalculator(priceConfiguration);
        });

    }

}
