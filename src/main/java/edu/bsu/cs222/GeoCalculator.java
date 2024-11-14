package edu.bsu.cs222;
import java.io.IOException;
import java.util.Objects;

public class GeoCalculator {
    private static final double EARTH_RADIUS = 6371.0;

    public double calculateDistanceKiloMeters(double lat1, double lon1, double lat2, double lon2) {
        /*
         *    Title: Equirectangular Distance Approximation
         *    Author: Harpal Singh
         *    Date: 10/10/2024
         *    Availability: https://www.baeldung.com/java-find-distance-between-points
         */
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double lon1Rad = Math.toRadians(lon1);
        double lon2Rad = Math.toRadians(lon2);

        double x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
        double y = (lat2Rad - lat1Rad);

        return (Math.sqrt((x * x) + (y * y)) * EARTH_RADIUS) ;
    }

    protected double kilometersToMiles(double distance) {
        return (distance * .621);
    }

    protected  String roundDistanceFourDecimal(double distance) {
        return String.format("%.4f",distance);
    }

    protected double[] calculateCenterLatAndLon(double lon1, double lat1, double lon2, double lat2){
        double dLon = Math.toRadians(lon2 - lon1);


        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);

        double Bx = Math.cos(lat2) * Math.cos(dLon);
        double By = Math.cos(lat2) * Math.sin(dLon);
        double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
        double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

        return new double[]{Math.toDegrees(lat3), Math.toDegrees(lon3)};
    }

    protected String calculateZoomLevel(double distance) {
        double[] thresholds = {0.625, 1.25, 2.5, 5, 10, 25, 50, 100, 200, 400, 800, 1500, 3000, 5000};
        int maxReturnValue = 14;

        for (int i = 0; true; i++) {
            if (distance < thresholds[i]) {
                return String.valueOf(maxReturnValue - i);
            }
        }
    }

    protected String unitConverter(String comboBoxSelection, double rawDistance) throws IOException, InterruptedException {
        double distance = calculateDistance(comboBoxSelection, rawDistance);
        return new GeoCalculator().roundDistanceFourDecimal(distance);
    }

    protected double calculateDistance(String comboBoxSelection, double rawDistance) {
        return Objects.equals(comboBoxSelection, "Miles")?
                new GeoCalculator().kilometersToMiles(rawDistance)
                : rawDistance;
    }

}
