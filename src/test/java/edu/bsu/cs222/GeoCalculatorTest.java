package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GeoCalculatorTest {

    @Test
    void testCalculateDistanceKiloMeters_samePoint() {
        GeoCalculator calculator = new GeoCalculator();
        double distance = calculator.calculateDistanceKiloMeters(0, 0, 0, 0);
        Assertions.assertEquals(0, distance, 0.001);
    }

    @Test
    public void checkIfDistanceIsAccurate() {
        GeoCalculator geoCalculator = new GeoCalculator();
        double result = geoCalculator.calculateDistanceKiloMeters(40.115843,-85.657507,40.191503,-85.4102546);
        Assertions.assertEquals(result, 22.554, .15);
    }

    @Test
    void testKilometersToMiles() {
        GeoCalculator calculator = new GeoCalculator();
        Assertions.assertEquals(0.621, calculator.kilometersToMiles(1), 0.001);
        Assertions.assertEquals(621.0, calculator.kilometersToMiles(1000), 0.1);
    }

    @Test
    void testRoundDistanceFourDecimal() {
        GeoCalculator calculator = new GeoCalculator();
        Assertions.assertEquals("1.2345", calculator.roundDistanceFourDecimal(1.2345));
        Assertions.assertEquals("0.0000", calculator.roundDistanceFourDecimal(0));
        Assertions.assertEquals("1234.5679", calculator.roundDistanceFourDecimal(1234.56789));
    }

    @Test
    void testCalculateCenterLatAndLon() {
        GeoCalculator calculator = new GeoCalculator();
        double[] midpoint = calculator.calculateCenterLatAndLon(-74.0060, 40.7128, -118.2437, 34.0522); // NYC to LA
        Assertions.assertEquals(39.510307565757, midpoint[0], 0.01); // Approximate latitude
        Assertions.assertEquals(-97.16013188872247, midpoint[1], 0.01); // Approximate longitude
    }

    @Test
    void testCalculateZoomLevel() {
        GeoCalculator calculator = new GeoCalculator();
        Assertions.assertEquals("14", calculator.calculateZoomLevel(0.4));
        Assertions.assertEquals("10", calculator.calculateZoomLevel(9));
        Assertions.assertEquals("4", calculator.calculateZoomLevel(400));
        Assertions.assertEquals("1", calculator.calculateZoomLevel(4999));
        Assertions.assertEquals("0", calculator.calculateZoomLevel(6000));
    }

    @Test
    void testUnitConverter() throws IOException, InterruptedException {
        GeoCalculator calculator = new GeoCalculator();
        Assertions.assertEquals("0.0000", calculator.unitConverter("Miles", 0.0));
        Assertions.assertEquals("3.1050", calculator.unitConverter("Miles", 5.0));
        Assertions.assertEquals("3105.0000", calculator.unitConverter("Miles", 5000));
        Assertions.assertEquals("0.0000", calculator.unitConverter("Kilometers", 0));
        Assertions.assertEquals("5.0000", calculator.unitConverter("Kilometers", 5.0));
        Assertions.assertEquals("3105.0000", calculator.unitConverter("Kilometers", 3105));
    }



}
