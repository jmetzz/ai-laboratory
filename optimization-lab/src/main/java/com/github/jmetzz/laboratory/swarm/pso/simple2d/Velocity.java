package com.github.jmetzz.laboratory.swarm.pso.simple2d;


public class Velocity<T extends Number> {

    private T x;
    private T y;

    public Velocity(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

}
