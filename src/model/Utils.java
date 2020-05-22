package model;

import java.util.Random;

//A utility class for various vector operations.
class Utils {

    static Random rand = new Random();

    static double[] getWalkVector() {

        double[] unscaled = new double[]{rand.nextGaussian(), rand.nextGaussian()};
        return scale(unscaled, Cfg.SD_OF_WALK_DIST);
    }

    static double[] add(double[] a, double[] b) {
        if (a.length != b.length) {
            System.out.println("Attempted to add vectors of different dimensions");
            return null;
        }
        double[] c = new double[a.length];

        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    static double[] scale(double[] a, double k) {

        double[] b = new double[a.length];

        for (int i = 0; i < b.length; i++) {
            b[i] = a[i] * k;
        }
        return b;
    }

    static double get2DDistance(double[] a, double[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

}
