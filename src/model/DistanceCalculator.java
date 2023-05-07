package model;

public class DistanceCalculator {
    public static final double R = 6371;

    public static double distance(double originLatitude, double originLongitude, double destinationLatitude, double destinationLongitude) {
        double latDistance = Math.toRadians(destinationLatitude - originLatitude);
        double lonDistance = Math.toRadians(destinationLongitude - originLongitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(originLatitude)) * Math.cos(Math.toRadians(destinationLatitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return distance;
    }

    public static void main(String[] args) {
        double originLatitude = -31.522361470421426;
        double originLongitude = -68.5546875;
        double destinationLatitude = -34.61512668346219;
        double destinationLongitude = -58.42529296875;

        double distance = distance(originLatitude, originLongitude, destinationLatitude, destinationLongitude);
    }
}
