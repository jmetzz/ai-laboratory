package com.github.jmetzz.laboratory.swarm.pso.simple2d;

import static java.lang.Math.pow;

public class Particle<T extends Number> {
    private Location<T> location;

    private Velocity<T> velocity;

    private double fitness;

    public Particle(Location<T> location, Velocity<T> velocity) {
        this.location =  location;
        this.velocity = velocity;
        calculateFitness();
    }

    private void calculateFitness() {
        double x = location.getX().doubleValue();
        double y = location.getY().doubleValue();
        fitness = pow((2.8125) - x + x * pow(y, 4), 2)
                + pow((2.25 - x + x * pow(y, 2)), 2)
                + pow((1.5 - x + x * y), 2);
    }

    public double getFitness() {
        return fitness;
    }

    public Location<T> getLocation() {
        return location;
    }

    public Velocity<T> getVelocity() {
        return velocity;
    }


    public void setVelocity(Velocity<T> velocity) {
        this.velocity = velocity;
    }

    public void setLocation(Location<T> location) {
        this.location = location;
        calculateFitness();
    }

    public void move(T deltaX, T deltaY) {
        Location newLocation = new Location<>(this.location.getX().doubleValue() + deltaX.doubleValue(), this.location.getY().doubleValue() + deltaY.doubleValue() );
        setLocation(newLocation);
    }
}
