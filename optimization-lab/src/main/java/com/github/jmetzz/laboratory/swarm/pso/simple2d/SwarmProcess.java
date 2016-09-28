package com.github.jmetzz.laboratory.swarm.pso.simple2d;


public interface SwarmProcess {

    int DEFAULT_SWARM_SIZE = 30;
    int DEFAULT_MAX_ITERATION = 100;
    int DEFAULT_PROBLEM_DIMENSION = 2;
    double DEFAULT_C1 = 2.0;
    double DEFAULT_C2 = 2.0;
    double DEFAULT_W_UPPERBOUND = 1.0;
    double DEFAULT_W_LOWERBOUND = 0.0;

    void initializeSwarm();
    void execute();
}
