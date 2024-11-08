package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeoCalculatorTest {

    @Test
    public void checkIfDistanceIsAccurate() {
        GeoCalculator geoCalculator = new GeoCalculator();
        double result = geoCalculator.calculateDistanceKiloMeters(40.115843,-85.657507,40.191503,-85.4102546);
        Assertions.assertEquals(result, 22.554, .15);
    }

    @Test
    public void kilometersToMilesTest() {
        GeoCalculator geoCalculator = new GeoCalculator();
        double testDistance = 8.04672;
        Assertions.assertEquals(5.0, geoCalculator.kilometersToMiles(testDistance),.1);
    }

    @Test
    public void roundingTest() {
        GeoCalculator geoCalculator = new GeoCalculator();
        double testDistance = 3025.3452234;
        Assertions.assertEquals(Double.toString(3025.3452), geoCalculator.roundDistanceFourDecimal(testDistance));
    }

}
