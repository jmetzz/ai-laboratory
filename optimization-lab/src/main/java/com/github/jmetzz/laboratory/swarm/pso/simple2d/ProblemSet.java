package com.github.jmetzz.laboratory.swarm.pso.simple2d;


public interface ProblemSet<T extends Number> {
    double evaluate(Location<T> location);
    Location<T> createRandomLocation();
    Velocity<T> createRandomVelocity();
    double getErrorTolerance();
}
