package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistanceCalculatorTest {

    @Test
    public void checkIfDistanceIsAccurate() {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        double result = distanceCalculator.calculateDistanceKiloMeters();
        Assertions.assertEquals(result,);
    }
}
