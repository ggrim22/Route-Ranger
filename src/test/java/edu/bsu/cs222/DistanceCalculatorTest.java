package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistanceCalculatorTest {

    @Test
    public void checkIfDistanceIsAccurate() {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        double result = distanceCalculator.calculateDistanceKiloMeters(40.115843,-85.657507,40.191503,-85.4102546);
        Assertions.assertEquals(result, 22.554, .15);
    }

    @Test
    public void kilometersToMilesTest() {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        double testDistance = 8.04672;
        Assertions.assertEquals(5.0,distanceCalculator.kilometersToMiles(testDistance),.1);
    }

    @Test
    public void roundingTest() {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        double testDistance = 3025.3452234;
        Assertions.assertEquals(Double.toString(3025.3452),distanceCalculator.roundDistanceFourDecimal(testDistance));
    }

}
