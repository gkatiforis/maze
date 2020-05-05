package com.katiforis.assignment.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a point
 */
public class Point implements Serializable {
    int x;
    int y;
    char value;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;
        return value == point.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, value);
    }
}
