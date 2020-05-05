package com.katiforis.assignment.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a maze point
 */
public class MazePoint extends Point implements Serializable {
    private boolean isVisited;

    public MazePoint(int x, int y, char value) {
        super(x, y, value);
        this.isVisited = false;
    }

    public MazePoint(int x, int y) {
        super(x, y);
        this.isVisited = false;
    }

    boolean isVisited() {
        return isVisited;
    }

    void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public String toString() {
        if(value == Maze.START_POINT)
            return "(" + x + ":" + y + " (" + Maze.START_POINT + "))";
        else if(value == Maze.EXIT_POINT)
            return "(" + x + ":" + y + " (" + Maze.EXIT_POINT + "))";
        return "(" + x + ":" + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MazePoint mazePoint = (MazePoint) o;

        if (isVisited != mazePoint.isVisited) return false;
        if (x != mazePoint.x) return false;
        if (y != mazePoint.y) return false;
        return value == mazePoint.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isVisited);
    }
}
