package com.katiforis.assignment.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a sequence of points
 */
public class Route {
    private List<Point> points;

    public Route() {
        points = new ArrayList<>();
    }
    public Route(List<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        if(points.isEmpty()){
            return "Empty route";
        }
        return String.join(", ",
                points.stream()
                .map(Point::toString)
                .collect(Collectors.joining()));
    }
}
