package edu.bsu.cs222;

public class DistanceCalculator {
    private static final int EARTH_RADIUS = 6378;
    public double calculateDistanceKiloMeters(double lat1, double lon1, double lat2, double lon2) {
        /*
         *    Title: Equirectangular Distance Approximation
         *    Author: Harpal Singh
         *    Date: 10/10/2024
         *    Availability: https://www.baeldung.com/java-find-distance-between-points
         *
         */
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double lon1Rad = Math.toRadians(lon1);
        double lon2Rad = Math.toRadians(lon2);

        double x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
        double y = (lat2Rad - lat1Rad);

        return (Math.sqrt((x * x) + (y * y)) * EARTH_RADIUS);
    }

    protected double kilometersToMiles(double distance) {
        return (double) Math.round(distance * .621 * 100) /100;
    }
}
