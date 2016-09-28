package com.github.jmetzz.laboratory.swarm.pso.simple2d;


import java.util.*;

public class PSOProcess implements SwarmProcess {

    private final int swarmCapacity;
    private final int maxIteration;
    private final int dimension;
    private final double c1;
    private final double c2;
    private final double wUpperBound;
    private final double wLowerBound;

    private List<Particle> swarm;
    private Map<Particle, Double> particleBestFitness;
    private Map<Particle, Location<Double>> particleBestLocation;

    private double globalBestFitness;
    private Location<Double> globalBestLocation;

    private ProblemSet<Double> problem;

    public static Random random = new Random(System.currentTimeMillis());

    public PSOProcess(ProblemSet problem) {
        this(problem, DEFAULT_SWARM_SIZE, DEFAULT_MAX_ITERATION, DEFAULT_PROBLEM_DIMENSION, DEFAULT_C1, DEFAULT_C2, DEFAULT_W_UPPERBOUND, DEFAULT_W_LOWERBOUND);
    }

    public PSOProcess(ProblemSet problem, int swarmSize, int maxIteration, int dimension, double c1, double c2, double wUpperBound, double wLowerBound) {
        this.swarmCapacity = swarmSize;
        this.maxIteration = maxIteration;
        this.dimension = dimension;
        this.c1 = c1;
        this.c2 = c2;
        this.wUpperBound = wUpperBound;
        this.wLowerBound = wLowerBound;

        this.swarm = new ArrayList<>(swarmCapacity);
        this.particleBestFitness = new HashMap<>(swarmCapacity);

        this.particleBestLocation = new HashMap<>(swarmCapacity);
        this.globalBestFitness = Double.MAX_VALUE;

        this.problem = problem;
    }

    @Override
    public void execute() {
        initializeSwarm();

        for (Particle p : swarm) {
            particleBestFitness.put(p, p.getFitness());
            particleBestLocation.put(p, p.getLocation());
        }

        double err = Double.MAX_VALUE;
        double w;
        int iteration = 0;

        while (iteration < maxIteration && err > problem.getErrorTolerance()) {
            updateBestSolution();
            w = wUpperBound - (((double) iteration) / maxIteration) * (wUpperBound - wLowerBound);

            for (Particle p : swarm) {

                double factor1 = (random.nextDouble() * c1);
                double factor2 = (random.nextDouble() * c2);

                Velocity<Double> currentVelocity = p.getVelocity();
                Location<Double> currentLoc = p.getLocation();

                double velX = (w * currentVelocity.getX())
                        + factor1 * (particleBestLocation.get(p).getX() - currentLoc.getX())
                        + factor2 * (globalBestLocation.getX() - currentLoc.getX());

                double velY = (w * p.getVelocity().getY().doubleValue())
                        + factor1 * (particleBestLocation.get(p).getY() - currentLoc.getY())
                        + factor2 * (globalBestLocation.getY() - currentLoc.getY());

                Velocity<Double> newVelocity = new Velocity<>(velX, velY);
                p.setVelocity(newVelocity);
                p.move(velX, velY);
            }
            err = problem.evaluate(globalBestLocation) - 0;
            report(iteration, err, globalBestLocation, "");
            iteration++;
        }
        report(iteration, err, globalBestLocation, "Global solution found at iteration: ");

    }

    private void report(int iteration, double err, Location<Double> globalBestLocation, String header) {
        System.out.println(header);
        System.out.println("ITERATION " + iteration + ": ");
        System.out.println("     Best X: " + globalBestLocation.getX());
        System.out.println("     Best Y: " + globalBestLocation.getY());
        System.out.println("     Error: " + err);
    }

    private void updateBestSolution() {
        for (Particle p : swarm) {
            if (p.getFitness() < particleBestFitness.get(p)) {
                particleBestFitness.put(p, p.getFitness());
                particleBestLocation.put(p, p.getLocation());
            }

            if (p.getFitness() < globalBestFitness) {
                globalBestFitness = p.getFitness();
                globalBestLocation = p.getLocation();
            }

        }
    }


    @Override
    public void initializeSwarm() {
        while (swarm.size() < swarmCapacity) {
            Location<Double> location = problem.createRandomLocation();
            Velocity velocity = problem.createRandomVelocity();
            swarm.add(new Particle(location, velocity));
        }
    }

}
