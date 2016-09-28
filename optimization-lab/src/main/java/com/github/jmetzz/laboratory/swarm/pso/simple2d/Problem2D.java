package com.github.jmetzz.laboratory.swarm.pso.simple2d;

import java.util.Random;

/**
 * this is the problem to be solved
 * to find an x and a y that minimize the function below:
 *      f(x, y) = (2.8125 - x + x * y^4)^2 + (2.25 - x + x * y^2)^2 + (1.5 - x + x*y)^2
 * where 1 <= x <= 4, and -1 <= y <= 1
 *
 */
public class Problem2D implements ProblemSet<Double> {
    public static final double LOC_BOUNDARY_X_LOW = 1;
    public static final double LOC_BOUNDARY_X_HIGH = 4;
    public static final double LOC_BOUNDARY_Y_LOW = -1;
    public static final double LOC_BOUNDARY_Y_HIGH = 1;
    public static final double VEL_LIMIT_LOW = -1;
    public static final double VEL_LIMIT_HIGH = 1;

    /* the smaller the tolerance, the more accurate the result, but the number of iteration is increased */
    private static final double DEFAULT_ERROR_TOLERANCE = 1E-20;

    private final double errorTolerance;

    private static Random random = new Random(System.currentTimeMillis());

    public Problem2D(){
        this(DEFAULT_ERROR_TOLERANCE);
    }

    public Problem2D(double errorTolerance) {
        this.errorTolerance = errorTolerance;
    }

    public double evaluate(Location<Double> location) {
        double result = 0;
        double x = location.getX().doubleValue(); // the "x" part of the location
        double y = location.getY().doubleValue(); // the "y" part of the location

        result = Math.pow(2.8125 - x + x * Math.pow(y, 4), 2) +
                Math.pow(2.25 - x + x * Math.pow(y, 2), 2) +
                Math.pow(1.5 - x + x * y, 2);

        return result;
    }

    public Location<Double> createRandomLocation(){
        Double x = new Double(LOC_BOUNDARY_X_LOW + random.nextDouble() * (LOC_BOUNDARY_X_HIGH - LOC_BOUNDARY_X_LOW));
        Double y = new Double(LOC_BOUNDARY_Y_LOW + random.nextDouble() * (LOC_BOUNDARY_Y_HIGH - LOC_BOUNDARY_Y_LOW));
        return new Location<>( x, y);
    }

    public Velocity<Double> createRandomVelocity(){
        Double x = new Double(VEL_LIMIT_LOW + random.nextDouble() * (VEL_LIMIT_HIGH - VEL_LIMIT_LOW));
        Double y = new Double(VEL_LIMIT_LOW + random.nextDouble() * (VEL_LIMIT_HIGH - VEL_LIMIT_LOW));
        return new Velocity<>( x, y);
    }

    @Override
    public double getErrorTolerance() {
        return DEFAULT_ERROR_TOLERANCE;
    }


}
