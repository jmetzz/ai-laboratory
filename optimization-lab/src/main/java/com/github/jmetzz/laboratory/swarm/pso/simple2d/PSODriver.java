package com.github.jmetzz.laboratory.swarm.pso.simple2d;


public class PSODriver {

    public static void main(String[] args) {
        new PSOProcess(new Problem2D()).execute();
    }
}
